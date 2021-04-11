package com.study.service.impl;

import com.study.dao.order.OrderItemDao;
import com.study.pojo.order.OrderItem;
import com.study.pojo.order.OrderItemExample;
import com.study.pojo.user.User;
import com.study.service.OrderItemService;
import com.study.service.OrderService;
import com.study.service.ProductService;
import com.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemDao orderItemDao;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Override
    public void fillProduct(OrderItem orderItem) {
        Integer pid = orderItem.getPid();
        orderItem.setProduct(productService.findById(pid));
    }

    @Override
    public void fillProduct(List<OrderItem> orderItem) {
        for (OrderItem item : orderItem) {
            fillProduct(item);
        }
    }

    @Override
    public void fillOrder(OrderItem orderItem) {
        Integer oid = orderItem.getOid();
        orderItem.setOrder(orderService.findById(oid));
    }

    @Override
    public void fillOrder(List<OrderItem> orderItem) {
        for (OrderItem item : orderItem) {
            fillOrder(item);
        }
    }

    @Override
    public void fillUser(OrderItem orderItem) {
        Integer uid = orderItem.getUid();
        orderItem.setUser(userService.findById(uid));
    }

    @Override
    public void fillUser(List<OrderItem> orderItem) {
        for (OrderItem item : orderItem) {
            fillUser(item);
        }
    }

    @Override
    public List<OrderItem> findByOid(Integer oid) {
        OrderItemExample orderItemExample = new OrderItemExample();
        orderItemExample.setOrderByClause("id desc");
        OrderItemExample.Criteria criteria = orderItemExample.createCriteria();
        criteria.andOidEqualTo(oid);
        return orderItemDao.selectByExample(orderItemExample);
    }

    @Override
    public Integer findSoldCountByPid(Integer id) {
        OrderItemExample orderItemExample = new OrderItemExample();
        OrderItemExample.Criteria criteria = orderItemExample.createCriteria();
        criteria.andOidIsNotNull().andPidEqualTo(id);
        List<OrderItem> orderItems = orderItemDao.selectByExample(orderItemExample);
        Integer totalCount = 0;
        for (OrderItem orderItem : orderItems) {
            totalCount += orderItem.getNum();
        }
        return totalCount;
    }

    @Override
    public List<OrderItem> findByUidWithNotOrder(Integer uid) {
        OrderItemExample orderItemExample = new OrderItemExample();
        orderItemExample.setOrderByClause("id desc");
        OrderItemExample.Criteria criteria = orderItemExample.createCriteria();
        criteria.andUidEqualTo(uid).andOidIsNull();
        return orderItemDao.selectByExample(orderItemExample);
    }

    @Override
    public void save(OrderItem orderItem) {
        orderItemDao.insertSelective(orderItem);
    }

    @Override
    public OrderItem findById(Integer id) {
        return orderItemDao.selectByPrimaryKey(id);
    }

    @Override
    public OrderItem addOrderItem(OrderItem orderItem, User user) {
        // 查询该用户未生成订单的订单项 即在购物车中的订单项
        List<OrderItem> orderItems = findByUidWithNotOrder(user.getUid());
        // 查询购物车订单项中是否有相同的商品
        boolean isExist = false;
        for (OrderItem item : orderItems) {
            if (item.getPid().equals(orderItem.getPid())) {
                // 当前订单项数量增加
                item.setNum(item.getNum() + orderItem.getNum());
                orderItem = item;
                isExist = true;
                break;
            }
        }
        if (!isExist) {
            // 购物车中没有相同的订单 -> 保存订单项
            orderItem.setUid(user.getUid());
            save(orderItem);
        } else {
            // 更新
            update(orderItem);
        }
        return orderItem;
    }

    @Override
    public void update(OrderItem orderItem) {
        orderItemDao.updateByPrimaryKeySelective(orderItem);
    }

    @Override
    public void updateByPid(OrderItem orderItem) {
        OrderItemExample orderItemExample = new OrderItemExample();
        OrderItemExample.Criteria criteria = orderItemExample.createCriteria();
        criteria.andPidEqualTo(orderItem.getPid());
        orderItemDao.updateByExampleSelective(orderItem, orderItemExample);
    }

    @Override
    public void delete(Integer oid) {
        orderItemDao.deleteByPrimaryKey(oid);
    }
}
