package com.study.pojo.property;

import java.io.Serializable;

import com.study.pojo.product.Product;
import lombok.Data;

/**
 * propertyvalue
 * @author 
 */
@Data
public class PropertyValue implements Serializable {
    private Integer id;

    private Integer pid;

    private Integer ptid;

    private String value;

    private Property property;

    private Product product;

    private static final long serialVersionUID = 1L;
}