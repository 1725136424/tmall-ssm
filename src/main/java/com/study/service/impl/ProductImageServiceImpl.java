package com.study.service.impl;

import com.study.dao.product.ProductImageDao;
import com.study.pojo.product.ProductImage;
import com.study.pojo.product.ProductImageExample;
import com.study.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    private ProductImageDao productImageDao;

    @Override
    public List<ProductImage> findByPidAndType(Integer pid, String type) {
        ProductImageExample productImageExample = new ProductImageExample();
        productImageExample.setOrderByClause("id desc");
        ProductImageExample.Criteria criteria = productImageExample.createCriteria();
        criteria.andPidEqualTo(pid).andTypeEqualTo(type);
        return productImageDao.selectByExample(productImageExample);
    }

    @Override
    public void save(ProductImage productImage) {
        productImageDao.insertSelective(productImage);
    }

    @Override
    public ProductImage findById(Integer id) {
        return productImageDao.selectByPrimaryKey(id);
    }

    @Override
    public void delete(Integer id) {
        productImageDao.deleteByPrimaryKey(id);
    }
}
