package com.study.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.dao.category.CategoryDao;
import com.study.dao.property.PropertyDao;
import com.study.pojo.category.Category;
import com.study.pojo.entity.Page;
import com.study.pojo.property.Property;
import com.study.pojo.property.PropertyExample;
import com.study.service.CategoryService;
import com.study.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyDao propertyDao;

    @Autowired
    private CategoryService categoryService;

    @Override
    public List<Property> findByCid(Page page, Integer cid) {
        if (page != null) {
            PageHelper.offsetPage(page.getStart(), page.getCount());
        }
        PropertyExample propertyQuery = new PropertyExample();
        propertyQuery.setOrderByClause("id desc");
        PropertyExample.Criteria criteria = propertyQuery.createCriteria();
        criteria.andCidEqualTo(cid);
        List<Property> properties = propertyDao.selectByExample(propertyQuery);
        PageInfo<Property> pageInfo = new PageInfo<>(properties);
        page.setTotal((int) pageInfo.getTotal());
        return properties;
    }

    @Override
    public void save(Property property) {
        propertyDao.insertSelective(property);
    }

    @Override
    public void delete(Integer id) {
        propertyDao.deleteByPrimaryKey(id);
    }

    @Override
    public Property findById(Integer id) {
       return propertyDao.selectByPrimaryKey(id);
    }

    @Override
    public void update(Property property) {
        propertyDao.updateByPrimaryKeySelective(property);
    }

    @Override
    public void fillCategory(Property property) {
        Integer cid = property.getCid();
        property.setCategory(categoryService.findById(cid));
    }

    @Override
    public void fillCategory(List<Property> properties) {
        for (Property property : properties) {
            fillCategory(property);
        }
    }
}
