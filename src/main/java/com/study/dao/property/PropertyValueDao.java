package com.study.dao.property;

import com.study.pojo.property.PropertyValue;
import com.study.pojo.property.PropertyValueExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PropertyValueDao {
    long countByExample(PropertyValueExample example);

    int deleteByExample(PropertyValueExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PropertyValue record);

    int insertSelective(PropertyValue record);

    List<PropertyValue> selectByExample(PropertyValueExample example);

    PropertyValue selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PropertyValue record, @Param("example") PropertyValueExample example);

    int updateByExample(@Param("record") PropertyValue record, @Param("example") PropertyValueExample example);

    int updateByPrimaryKeySelective(PropertyValue record);

    int updateByPrimaryKey(PropertyValue record);
}