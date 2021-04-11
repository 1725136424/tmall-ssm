package com.study.pojo.product;

import java.io.Serializable;
import lombok.Data;

/**
 * productimage
 * @author 
 */
@Data
public class ProductImage implements Serializable {
    private Integer id;

    private Integer pid;

    private String type;

    private static final long serialVersionUID = 1L;
}