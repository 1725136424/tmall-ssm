package com.study.service.impl;

import com.study.dao.review.ReviewDao;
import com.study.pojo.review.Review;
import com.study.pojo.review.ReviewExample;
import com.study.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewDao reviewDao;

    @Override
    public Integer findCountByPid(Integer pid) {
        ReviewExample reviewExample = new ReviewExample();
        ReviewExample.Criteria criteria = reviewExample.createCriteria();
        criteria.andPidEqualTo(pid);
        return Integer.parseInt(String.valueOf(reviewDao.countByExample(reviewExample)));
    }

    @Override
    public List<Review> findByPid(Integer pid) {
        ReviewExample reviewExample = new ReviewExample();
        ReviewExample.Criteria criteria = reviewExample.createCriteria();
        criteria.andPidEqualTo(pid);
        return reviewDao.selectByExample(reviewExample);
    }

    @Override
    public void save(Review review) {
        reviewDao.insertSelective(review);
    }
}
