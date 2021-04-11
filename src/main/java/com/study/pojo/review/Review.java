package com.study.pojo.review;

import java.io.Serializable;
import java.util.Date;

import com.study.pojo.user.User;
import lombok.Data;

/**
 * review
 * @author 
 */
@Data
public class Review implements Serializable {
    private Integer id;

    private String content;

    private Integer uid;

    private Integer pid;

    private Date createDate;

    private User user;

    private static final long serialVersionUID = 1L;
}