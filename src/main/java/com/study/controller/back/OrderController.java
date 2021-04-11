package com.study.controller.back;


import com.study.pojo.entity.Page;
import com.study.pojo.order.Order;
import com.study.pojo.order.OrderItem;
import com.study.pojo.product.Product;
import com.study.service.OrderItemService;
import com.study.service.OrderService;
import com.study.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @RequestMapping("/admin_order_list")
    public String list(Page page, Model model) {
        List<Order> orders = orderService.findAll(page);
        orderService.fillUser(orders);
        orderService.fillOrderItems(orders);
        for (Order order : orders) {
            List<OrderItem> orderItems = order.getOrderItems();
            // 填充订单项下的数据
            orderItemService.fillUser(orderItems);
            orderItemService.fillOrder(orderItems);
            orderItemService.fillProduct(orderItems);
        }
        orderService.fillTotalAndTotalNumber(orders);
        orderService.fillFirstImage(orders);
        model.addAttribute("page", page);
        model.addAttribute("orders", orders);
        return "admin/listOrder";
    }


    @RequestMapping("/admin_order_delivery")
    public String delivery(Integer id) {
        orderService.delivery(id);
        return "redirect:/admin_order_list";
    }

}
