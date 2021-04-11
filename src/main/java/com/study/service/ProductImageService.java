package com.study.service;


import com.study.pojo.product.ProductImage;

import java.util.List;

public interface ProductImageService {

    String type_single = "type_single";
    String type_detail = "type_detail";


    List<ProductImage> findByPidAndType(Integer pid, String type);

    void save(ProductImage productImage);

    ProductImage findById(Integer id);

    void delete(Integer id);
}
