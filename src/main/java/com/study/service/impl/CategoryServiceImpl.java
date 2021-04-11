package com.study.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.dao.category.CategoryDao;
import com.study.dao.product.ProductDao;
import com.study.pojo.category.Category;
import com.study.pojo.category.CategoryExample;
import com.study.pojo.entity.Page;
import com.study.pojo.product.Product;
import com.study.pojo.product.ProductExample;
import com.study.service.CategoryService;
import com.study.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ProductService productService;

    @Override
    public List<Category> findAll(Page page) {
        if (page != null) {
            PageHelper.offsetPage(page.getStart(), page.getCount());
        } else {
            PageHelper.offsetPage(0, Integer.MAX_VALUE);
        }
        CategoryExample categoryQuery = new CategoryExample();
        categoryQuery.setOrderByClause("id desc");
        List<Category> categories = categoryDao.selectByExample(categoryQuery);
        PageInfo<Category> pageInfo = new PageInfo<>(categories);
        if (page != null) {
            page.setTotal((int) pageInfo.getTotal());
        }
        return categories;
    }

    @Override
    public List<Category> findAll() {
      return findAll(null);
    }


    @Override
    public Category findById(Integer id) {
        return categoryDao.selectByPrimaryKey(id);
    }

    @Override
    public void save(Category category) {
        categoryDao.insertReturnKey(category);
    }

    @Override
    public void update(Category category) {
        categoryDao.updateByPrimaryKey(category);
    }

    @Override
    public void delete(Integer id) {
        categoryDao.deleteByPrimaryKey(id);
    }

    @Override
    public void fillProduct(List<Category> categories) {
        for (Category category : categories) {
            fillProduct(category);
        }
    }

    @Override
    public void fillProduct(Category category) {
        List<Product> products = productService.findByCid(category.getId());
        category.setProducts(products);
    }

    @Override
    public void fillProductByRows(List<Category> categories) {
        for (Category category : categories) {
            fillProductByRows(category);
        }
    }

    @Override
    public void fillProductByRows(Category category) {
        // 一行8个
        Integer defaultRows = 8;
        List<Product> products = productService.findByCid(category.getId());
        List<List<Product>> lists = new ArrayList<>();
        ArrayList<Product> row = null;
        if (products.size() >= defaultRows) {
            for (int i =  0; i < products.size() ;i++) {
                if (i % defaultRows == 0) {
                    lists.add(row);
                    row = new ArrayList<>();
                } else {
                    row.add(products.get(i));
                }
            }
        } else {
            lists.add(products);
        }
        category.setProductsByRow(lists);
    }
}
