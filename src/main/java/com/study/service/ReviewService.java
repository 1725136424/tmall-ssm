package com.study.service;

import com.study.pojo.review.Review;

import java.util.List;

public interface ReviewService {

    Integer findCountByPid(Integer pid);

    List<Review> findByPid(Integer pid);

    void save(Review review);
}
