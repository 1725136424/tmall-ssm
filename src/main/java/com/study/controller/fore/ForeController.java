package com.study.controller.fore;

import com.study.pojo.category.Category;
import com.study.pojo.order.Order;
import com.study.pojo.order.OrderItem;
import com.study.pojo.product.Product;
import com.study.pojo.property.PropertyValue;
import com.study.pojo.review.Review;
import com.study.pojo.user.User;
import com.study.service.*;
import com.sun.org.apache.regexp.internal.RE;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("")
public class ForeController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private PropertyValueService propertyValueService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/forehome")
    public String home(Model model) {
        // 获取所有分类
        List<Category> categories = categoryService.findAll();
        categoryService.fillProduct(categories);
        for (Category category : categories) {
            // 填充分类下产品数据
            List<Product> products = category.getProducts();
            productService.fillCategory(products);
            productService.fillFirstImage(products);
        }
        categoryService.fillProductByRows(categories);
        model.addAttribute("cs", categories);
        return "fore/home";
    }

    @RequestMapping("/foreregister")
    public String register(User user, Model model) {
        // 转义用户名
        user.setUsername(HtmlUtils.htmlEscape(user.getUsername()));
        User resUser = userService.findByUsername(user.getUsername());
        if (resUser != null) {
            model.addAttribute("msg", "用户名已经被使用,不能使用");
            model.addAttribute("user", null);
            return "fore/register";
        }
        userService.save(user);
        return "redirect:/registerSuccessPage";
    }

    @RequestMapping("/forelogin")
    public String login(User user, Model model, HttpSession session) {
        // 转义用户名
        user.setUsername(HtmlUtils.htmlEscape(user.getUsername()));
        User resUser = userService.findByUsernameAndPWD(user);
        if (resUser == null) {
            model.addAttribute("msg", "用户名或者密码错误");
            return "fore/login";
        }
        session.setAttribute("user", resUser);
        return "redirect:/forehome";
    }

    @RequestMapping("/forelogout")
    public String logout(HttpSession httpSession) {
        // 退出登入
        httpSession.removeAttribute("user");
        return "redirect:/forehome";
    }

    @RequestMapping("/foreproduct")
    public String productDetail(Integer pid, Model model) throws Exception {
        // 获取产品对象
        Product product = productService.findById(pid);
        productService.fillProductImagesByType(product, ProductImageService.type_single);
        productService.fillProductImagesByType(product, ProductImageService.type_detail);
        productService.fillSaleCount(product);
        productService.fillReviewCount(product);
        productService.fillFirstImage(product);
        productService.fillCategory(product);
        // 获取属性值对象
        List<PropertyValue> propertyValues = propertyValueService.findByPid(pid);
        propertyValueService.fillProperty(propertyValues);
        // 获取评价对象
        List<Review> reviews = reviewService.findByPid(pid);
        model.addAttribute("reviews", reviews);
        model.addAttribute("p", product);
        model.addAttribute("pvs", propertyValues);
        return "fore/product";
    }

    @RequestMapping("/forecheckLogin")
    @ResponseBody
    public String checkLogin(HttpSession session) {
        // 判断是否登录
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "fail";
        } else {
            return "success";
        }
    }

    @RequestMapping("/foreloginAjax")
    @ResponseBody
    public String loginAjax(User user, HttpSession session) {
        user.setUsername(HtmlUtils.htmlEscape(user.getUsername()));
        User resUser = userService.findByUsernameAndPWD(user);
        if (resUser == null) {
            return "fail";
        } else {
            session.setAttribute("user", resUser);
            return "success";
        }
    }

    /**
     * @param cid
     * @param model
     * @param sort  all review date saleCount price
     * @return
     */
    @RequestMapping("/forecategory")
    public String category(Integer cid, Model model, String sort) {
        Category category = categoryService.findById(cid);
        categoryService.fillProduct(category);
        List<Product> products = category.getProducts();
        productService.fillFirstImage(products);
        productService.fillReviewCount(products);
        productService.fillSaleCount(products);
        // 排序
        if (sort != null) {
            productService.sortByCondition(products, sort);
        }
        model.addAttribute("c", category);
        return "fore/category";
    }

    @RequestMapping("/foresearch")
    public String search(String keyword, Model model) {
        List<Product> products = productService.findByKeywords(keyword);
        productService.fillSaleCount(products);
        productService.fillReviewCount(products);
        productService.fillFirstImage(products);
        model.addAttribute("ps", products);
        return "fore/searchResult";
    }

    @RequestMapping("/forebuyone")
    public String buyOne(OrderItem orderItem, HttpSession session) {
        // 立即购买 生成订单项 id pid uid oid num
        User user = (User) session.getAttribute("user");
        orderItem = orderItemService.addOrderItem(orderItem, user);
        return "redirect:/forebuy?oiid=" + orderItem.getId();
    }

    @RequestMapping("/forebuy")
    public String buy(Integer[] oiid, Model model, HttpSession session) {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;
        for (Integer id : oiid) {
            OrderItem orderItem = orderItemService.findById(id);
            // 获取当前订单项的用户信息 商品信息
            // 填充商品
            orderItemService.fillProduct(orderItem);
            Product product = orderItem.getProduct();
            productService.fillFirstImage(product);
            total = total.add(product.getPromotePrice().multiply(new BigDecimal(orderItem.getNum())));
            // 填充用户
            orderItemService.fillUser(orderItem);
            orderItems.add(orderItem);
        }
        // 把需要生成订单的订单项放入session域中 后期生成订单获取
        session.setAttribute("ois", orderItems);
        model.addAttribute("total", total);
        return "fore/buy";
    }

    @RequestMapping("/foreaddCart")
    @ResponseBody
    public String addCart(OrderItem orderItem, HttpSession session) {
        try {
            User user = (User) session.getAttribute("user");
            orderItemService.addOrderItem(orderItem, user);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping("/forecart")
    public String cart(HttpSession session, Model model) {
        // 查询购物车页面
        User user = (User) session.getAttribute("user");
        List<OrderItem> orderItems = orderItemService.findByUidWithNotOrder(user.getUid());
        orderItemService.fillProduct(orderItems);
        for (OrderItem orderItem : orderItems) {
            Product product = orderItem.getProduct();
            productService.fillFirstImage(product);
        }
        model.addAttribute("ois", orderItems);
        return "fore/cart";
    }

    @RequestMapping("/forechangeOrderItem")
    @ResponseBody
    public String changeOrderItem(OrderItem orderItem) {
        try {
            // 改变订单数量 -> 根据商品id
            orderItemService.updateByPid(orderItem);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }

    }

    @RequestMapping("/foredeleteOrderItem")
    @ResponseBody
    public String deleteOrderItem(@RequestParam("oiid") Integer oid) {
        try {
            // 删除订单项
            orderItemService.delete(oid);
            return "success";
        } catch (Exception e) {
            return "fail";
        }
    }

    @RequestMapping("/forecreateOrder")
    public String createOrder(Order order, HttpSession session) {
        // 创建订单
        User user = (User) session.getAttribute("user");
        // 生成随机订单号
        String orderCode = new SimpleDateFormat("yyyyMMddhhmmsss").format(new Date())
                + RandomUtils.nextInt(10000);
        Date orderCreateDate = new Date();
        order.setOrderCode(orderCode);
        order.setCreateDate(orderCreateDate);
        order.setUid(user.getUid());
        order.setStatus(OrderService.waitPay);
        orderService.save(order);
        // 为订单项添加订单id属性
        List<OrderItem> orderItems = (List<OrderItem>) session.getAttribute("ois");
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItem orderItem : orderItems) {
            orderItem.setOid(order.getId());
            orderItemService.update(orderItem);
            total = total.add(orderItem.getProduct().getPromotePrice().multiply(new BigDecimal(orderItem.getNum())));
        }
        return "redirect:forealipay?oid=" + order.getId() + "&total=" + total;
    }

    @RequestMapping("/forepayed")
    public String payed(Integer oid, BigDecimal total, Model model) {
        // 获取订单 修改状态
        Order order = orderService.findById(oid);
        order.setPayDate(new Date());
        order.setStatus(OrderService.waitDelivery);
        orderService.update(order);
        model.addAttribute("o", order);
        model.addAttribute("total", total);
        return "fore/payed";
    }

    @RequestMapping("/forebought")
    public String bought(HttpSession session, Model model) {
        // 查询用户没有删除的订单集合
        User user = (User) session.getAttribute("user");
        List<Order> orders = orderService.findByUidAndExcluding(user.getUid(), OrderService.delete);
        orderService.fillOrderItems(orders);
        for (Order order : orders) {
            List<OrderItem> orderItems = order.getOrderItems();
            orderItemService.fillProduct(orderItems);
        }
        orderService.fillTotalAndTotalNumber(orders);
        orderService.fillFirstImage(orders);
        model.addAttribute("os", orders);
        return "fore/bought";
    }

    @RequestMapping("/foreconfirmPay")
    public String confirmPay(@RequestParam("oid") Integer id, Model model) {
        Order order = orderService.findById(id);
        orderService.fillOrderItems(order);
        List<OrderItem> orderItems = order.getOrderItems();
        orderItemService.fillProduct(orderItems);
        orderService.fillTotalAndTotalNumber(order);
        orderService.fillFirstImage(order);
        model.addAttribute("o", order);
        return "fore/confirmPay";
    }

    @RequestMapping("/foreorderConfirmed")
    public String orderConfirmed(@RequestParam("oid") Integer id) {
        orderService.confirmed(id);
        return "fore/orderConfirmed";
    }

    @RequestMapping("/foredeleteOrder")
    @ResponseBody
    public String deleteOrder(@RequestParam("oid") Integer id) {
        try {
            orderService.delete(id);
            return "success";
        } catch (Exception e) {
            return "fail";
        }
    }


    @RequestMapping("/forereview")
    public String review(@RequestParam("oid") Integer id, Model model) {
        // 获取当前订单
        Order order = orderService.findById(id);
        orderService.fillOrderItems(order);
        OrderItem orderItem = order.getOrderItems().get(0);
        orderItemService.fillProduct(orderItem);
        Product product = orderItem.getProduct();
        productService.fillFirstImage(product);
        productService.fillReviewCount(product);
        productService.fillSaleCount(product);
        // 获取当前商品对应的评价
        List<Review> reviews = reviewService.findByPid(product.getId());
        model.addAttribute("p", product);
        model.addAttribute("o", order);
        model.addAttribute("reviews", reviews);
        return "fore/review";
    }

    @RequestMapping("/foredoreview")
    public String doReview(Review review, @RequestParam("oid") Integer oid, HttpSession session) {
        // 修改订单状态
        orderService.finish(oid, OrderService.finish);
        // 获取当前用户
        User user = (User) session.getAttribute("user");
        review.setUid(user.getUid());
        review.setCreateDate(new Date());
        reviewService.save(review);
        return "redirect:forereview?oid="+oid+"&showonly=true";
    }
}
