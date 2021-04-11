package com.study.pojo.property;

import java.io.Serializable;

import com.study.pojo.category.Category;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * property
 * @author 
 */
@Getter
@Setter
public class Property implements Serializable {
    private Integer id;

    private Integer cid;

    private Category category;

    private String name;

    private static final long serialVersionUID = 1L;
}