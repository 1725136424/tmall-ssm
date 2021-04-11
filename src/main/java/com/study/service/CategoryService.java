package com.study.service;


import com.study.pojo.category.Category;
import com.study.pojo.entity.Page;

import java.util.List;

public interface CategoryService {

    List<Category> findAll(Page page);

    List<Category> findAll();

    Category findById(Integer id);

    void save(Category category);

    void update(Category category);

    void delete(Integer id);

    void fillProduct(List<Category> categories);

    void fillProduct(Category category);

    void fillProductByRows(List<Category> categories);

    void fillProductByRows(Category category);
}
