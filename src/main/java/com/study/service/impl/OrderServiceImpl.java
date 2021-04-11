package com.study.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.dao.order.OrderDao;
import com.study.dao.order.OrderItemDao;
import com.study.pojo.entity.Page;
import com.study.pojo.order.Order;
import com.study.pojo.order.OrderExample;
import com.study.pojo.order.OrderItem;
import com.study.pojo.order.OrderItemExample;
import com.study.pojo.product.Product;
import com.study.service.OrderItemService;
import com.study.service.OrderService;
import com.study.service.ProductService;
import com.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderItemService orderItemService;


    @Override
    public List<Order> findAll(Page page) {
        OrderExample orderExample = new OrderExample();
        orderExample.setOrderByClause("id desc");
        if (page != null) {
            PageHelper.offsetPage(page.getStart(), page.getCount());
        }
        List<Order> orders = orderDao.selectByExample(orderExample);
        // 用户 订单项
        PageInfo<Order> pageInfo = new PageInfo<>(orders);
        page.setTotal((int) pageInfo.getTotal());
        return orders;
    }

    @Override
    public void fillTotalAndTotalNumber(List<Order> orders) {
        for (Order order : orders) {
            fillTotalAndTotalNumber(order);
        }
    }

    @Override
    public void fillTotalAndTotalNumber(Order order) {
        List<OrderItem> orderItems = order.getOrderItems();
        Integer totalNumber = 0;
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItem orderItem : orderItems) {
            Integer num = orderItem.getNum();
            totalNumber += num;
            BigDecimal originalPrice = orderItem.getProduct().getPromotePrice();
            total = total.add(originalPrice);
        }
        order.setTotal(total.multiply(new BigDecimal(totalNumber)));
        order.setTotalNumber(totalNumber);
    }

    @Override
    public void fillFirstImage(List<Order> orders) {
        for (Order order : orders) {
            fillFirstImage(order);
        }
    }

    @Override
    public void fillFirstImage(Order order) {
        List<OrderItem> orderItems = order.getOrderItems();
        for (OrderItem orderItem : orderItems) {
            Product product = orderItem.getProduct();
            productService.fillFirstImage(product);
        }
    }

    @Override
    public void delivery(Integer id) {
        Order order = orderDao.selectByPrimaryKey(id);
        order.setStatus(OrderService.waitConfirm);
        order.setDeliveryDate(new Date());
        orderDao.updateByPrimaryKeySelective(order);
    }

    @Override
    public void fillUser(Order order) {
        Integer uid = order.getUid();
        order.setUser(userService.findById(uid));
    }

    @Override
    public void fillUser(List<Order> order) {
        for (Order order1 : order) {
            fillUser(order1);
        }
    }

    @Override
    public void fillOrderItems(List<Order> orders) {
        for (Order order : orders) {
            fillOrderItems(order);
        }
    }

    @Override
    public void fillOrderItems(Order order) {
        List<OrderItem> orderItems = orderItemService.findByOid(order.getId());
        order.setOrderItems(orderItems);
    }

    @Override
    public Order findById(Integer oid) {
        return orderDao.selectByPrimaryKey(oid);
    }

    @Override
    public void save(Order order) {
        orderDao.insertSelective(order);
    }

    @Override
    public void update(Order order) {
        orderDao.updateByPrimaryKeySelective(order);
    }

    @Override
    public List<Order> findByUidAndExcluding(Integer uid, String excluding) {
        OrderExample orderExample = new OrderExample();
        orderExample.setOrderByClause("id desc");
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andUidEqualTo(uid).andStatusNotEqualTo(delete);
        return orderDao.selectByExample(orderExample);
    }

    @Override
    public void confirmed(Integer id) {
        Order order = orderDao.selectByPrimaryKey(id);
        order.setStatus(OrderService.waitReview);
        order.setConfirmDate(new Date());
        orderDao.updateByPrimaryKey(order);
    }

    @Override
    public void delete(Integer id) {
        Order order = orderDao.selectByPrimaryKey(id);
        order.setStatus(OrderService.delete);
        orderDao.updateByPrimaryKey(order);
    }

    @Override
    public void finish(Integer oid, String finish) {
        Order order = orderDao.selectByPrimaryKey(oid);
        order.setStatus(finish);
        orderDao.updateByPrimaryKey(order);
    }
}
