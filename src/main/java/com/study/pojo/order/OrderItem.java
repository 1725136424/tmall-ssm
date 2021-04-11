package com.study.pojo.order;

import java.io.Serializable;

import com.study.pojo.product.Product;
import com.study.pojo.user.User;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * orderitem
 * @author 
 */
@Data
public class OrderItem implements Serializable {
    private Integer id;

    private Integer pid;

    private Integer oid;

    private Integer uid;

    private Integer num;

    private Product product;

    private Order order;

    private User user;

    private static final long serialVersionUID = 1L;
}