package com.study.pojo.category;

import java.io.Serializable;
import java.util.List;

import com.study.pojo.product.Product;
import lombok.Data;

/**
 * category
 * @author 
 */
@Data
public class Category implements Serializable {
    private Integer id;

    private String name;

    private List<Product> products;

    private List<List<Product>> productsByRow;

    private static final long serialVersionUID = 1L;
}