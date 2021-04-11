package com.study.service;


import com.study.pojo.order.Order;
import com.study.pojo.order.OrderItem;
import com.study.pojo.user.User;

import java.util.List;

public interface OrderItemService {

    void fillProduct(OrderItem orderItem);

    void fillProduct(List<OrderItem> orderItem);

    void fillOrder(OrderItem orderItem);

    void fillOrder(List<OrderItem> orderItem);

    void fillUser(OrderItem orderItem);

    void fillUser(List<OrderItem> orderItem);

    List<OrderItem> findByOid(Integer id);

    Integer findSoldCountByPid(Integer id);

    List<OrderItem> findByUidWithNotOrder(Integer uid);

    void save(OrderItem orderItem);

    OrderItem findById(Integer id);

    OrderItem addOrderItem(OrderItem orderItem, User user);

    void update(OrderItem orderItem);

    void updateByPid(OrderItem orderItem);

    void delete(Integer oid);
}
