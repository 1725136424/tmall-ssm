package com.study.service.impl;

import com.study.dao.property.PropertyValueDao;
import com.study.pojo.property.PropertyValue;
import com.study.pojo.property.PropertyValueExample;
import com.study.service.ProductService;
import com.study.service.PropertyService;
import com.study.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyValueServiceImpl implements PropertyValueService {

    @Autowired
    private PropertyValueDao propertyValueDao;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private ProductService productService;

    @Override
    public List<PropertyValue> findByPid(Integer pid) {
        PropertyValueExample propertyValueExample = new PropertyValueExample();
        propertyValueExample.setOrderByClause("id desc");
        PropertyValueExample.Criteria criteria = propertyValueExample.createCriteria();
        criteria.andPidEqualTo(pid);
        return propertyValueDao.selectByExample(propertyValueExample);
    }

    @Override
    public void update(PropertyValue propertyValue) {
        propertyValueDao.updateByPrimaryKeySelective(propertyValue);
    }

    @Override
    public void fillProperty(PropertyValue propertyValue) {
        Integer ptid = propertyValue.getPtid();
        propertyValue.setProperty(propertyService.findById(ptid));
    }

    @Override
    public void fillProperty(List<PropertyValue> propertyValues) {
        for (PropertyValue propertyValue : propertyValues) {
            fillProperty(propertyValue);
        }
    }

    @Override
    public void fileProduct(PropertyValue propertyValue) {
        Integer pid = propertyValue.getPid();
        propertyValue.setProduct(productService.findById(pid));
    }

    @Override
    public void fileProduct(List<PropertyValue> propertyValues) {
        for (PropertyValue propertyValue : propertyValues) {
            fileProduct(propertyValue);
        }
    }
}
