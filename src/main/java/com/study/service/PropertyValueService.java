package com.study.service;

import com.study.pojo.property.PropertyValue;

import java.util.List;

public interface PropertyValueService {

    List<PropertyValue> findByPid(Integer pid);

    void update(PropertyValue propertyValue);

    void fillProperty(PropertyValue propertyValue);

    void fillProperty(List<PropertyValue> propertyValues);

    void fileProduct(PropertyValue propertyValue);

    void fileProduct(List<PropertyValue> propertyValues);
}
