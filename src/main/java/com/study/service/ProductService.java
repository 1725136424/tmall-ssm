package com.study.service;

import com.study.pojo.entity.Page;
import com.study.pojo.product.Product;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface ProductService {
    List<Product> findByCid(Page page, Integer cid);

    void update(Product product);

    void save(Product product);

    Product findById(Integer id);

    void delete(Integer id);

    void fillFirstImage(List<Product> products);

    void fillFirstImage(Product products);

    void fillCategory(Product product);

    void fillCategory(List<Product> products);

    List<Product> findByCid(Integer id);

    void fillProductImagesByType(Product product, String type_single) throws Exception;

    void fillSaleCount(Product product);

    void fillSaleCount(List<Product> products);

    void fillReviewCount(Product product);

    void fillReviewCount(List<Product> products);

    void sortByCondition(List<Product> products, String condition);

    List<Product> findByKeywords(String keywords);
}
