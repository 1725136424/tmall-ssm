package com.study.service;

import com.study.pojo.entity.Page;
import com.study.pojo.property.Property;

import java.util.List;

public interface PropertyService {
    List<Property> findByCid(Page page, Integer cid);

    void save(Property property);

    void delete(Integer id);

    Property findById(Integer id);

    void update(Property property);

    void fillCategory(Property property);

    void fillCategory(List<Property> properties);
}
