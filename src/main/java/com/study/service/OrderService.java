package com.study.service;

import com.study.pojo.entity.Page;
import com.study.pojo.order.Order;

import java.util.List;

public interface OrderService {
    String waitPay = "waitPay";
    String waitDelivery = "waitDelivery";
    String waitConfirm = "waitConfirm";
    String waitReview = "waitReview";
    String finish = "finish";
    String delete = "delete";

    List<Order> findAll(Page page);

    void fillTotalAndTotalNumber(List<Order> orders);

    void fillTotalAndTotalNumber(Order order);

    void fillFirstImage(List<Order> orders);

    void fillFirstImage(Order order);

    void fillUser(Order order);

    void fillUser(List<Order> order);

    void fillOrderItems(List<Order> order);

    void fillOrderItems(Order order);

    void delivery(Integer id);

    Order findById(Integer oid);

    void save(Order order);

    void update(Order order);

    List<Order> findByUidAndExcluding(Integer uid, String delete);

    void confirmed(Integer id);

    void delete(Integer id);

    void finish(Integer oid, String finish);
}
