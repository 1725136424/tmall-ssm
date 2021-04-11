package com.study.pojo.product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.study.pojo.category.Category;
import lombok.Data;

/**
 * product
 * @author 
 */
@Data
public class Product implements Serializable {
    private Integer id;

    private String name;

    private String subTitle;

    private BigDecimal originalPrice;

    private BigDecimal promotePrice;

    private Integer stock;

    private Integer cid;

    private Date createDate;

    private Category category;

    private ProductImage firstProductImage;

    private List<ProductImage> productSingleImages;

    private List<ProductImage> productDetailImages;

    private Integer saleCount;

    private Integer reviewCount;

    private static final long serialVersionUID = 1L;
}