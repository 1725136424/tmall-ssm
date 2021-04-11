package com.study.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.dao.product.ProductDao;
import com.study.pojo.entity.Page;
import com.study.pojo.product.Product;
import com.study.pojo.product.ProductExample;
import com.study.pojo.product.ProductImage;
import com.study.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductImageService productImageService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private ReviewService reviewService;

    @Override
    public List<Product> findByCid(Page page, Integer cid) {
        if (page != null) {
            PageHelper.offsetPage(page.getStart(), page.getCount());
        }
        ProductExample productExample = new ProductExample();
        productExample.setOrderByClause("id desc");
        ProductExample.Criteria criteria = productExample.createCriteria();
        criteria.andCidEqualTo(cid);
        List<Product> products = productDao.selectByExample(productExample);
        PageInfo<Product> pageInfo = new PageInfo<>(products);
        page.setTotal((int) pageInfo.getTotal());
        page.setParam("&cid=" + products.get(0).getCid());
        return products;
    }

    @Override
    public void update(Product product) {
        productDao.updateByPrimaryKeySelective(product);
    }

    @Override
    public void save(Product product) {
        productDao.insertSelective(product);
    }

    @Override
    public Product findById(Integer id) {
        return productDao.selectByPrimaryKey(id);
    }

    @Override
    public void delete(Integer id) {
        productDao.deleteByPrimaryKey(id);
    }

    @Override
    public void fillFirstImage(List<Product> products) {
        for (Product product : products) {
            fillFirstImage(product);
        }
    }

    @Override
    public void fillFirstImage(Product product) {
        List<ProductImage> productImages = productImageService
                .findByPidAndType(product.getId(),
                        ProductImageService.type_single);
        if (productImages != null && productImages.size() > 0) {
            product.setFirstProductImage(productImages.get(0));
        }
    }

    @Override
    public void fillCategory(Product product) {
        Integer cid = product.getCid();
        product.setCategory(categoryService.findById(cid));
    }

    @Override
    public void fillCategory(List<Product> products) {
        for (Product product : products) {
            fillCategory(product);
        }
    }

    @Override
    public List<Product> findByCid(Integer id) {
        ProductExample productExample = new ProductExample();
        productExample.setOrderByClause("id desc");
        ProductExample.Criteria criteria = productExample.createCriteria();
        criteria.andCidEqualTo(id);
        return productDao.selectByExample(productExample);
    }

    @Override
    public void fillProductImagesByType(Product product, String type) throws Exception {
        String subType = StringUtils.capitalize(StringUtils.substringAfterLast(type, "_"));
        String method = "setProduct" + subType + "Images";
        List<ProductImage> productImages = productImageService.findByPidAndType(product.getId(), type);
        Method resMethod = product.getClass().getDeclaredMethod(method, List.class);
        resMethod.invoke(product, productImages);
    }

    @Override
    public void fillSaleCount(Product product) {
        product.setSaleCount(orderItemService.findSoldCountByPid(product.getId()));
    }

    @Override
    public void fillReviewCount(Product product) {
        product.setReviewCount(reviewService.findCountByPid(product.getId()));
    }

    @Override
    public void fillSaleCount(List<Product> products) {
        for (Product product : products) {
            fillSaleCount(product);
        }
    }

    @Override
    public void fillReviewCount(List<Product> products) {
        for (Product product : products) {
            fillReviewCount(product);
        }
    }

    @Override
    public void sortByCondition(List<Product> products, String condition) {
        switch (condition) {
            case "all":
                products.sort((o1, o2) -> o2.getReviewCount() * o2.getSaleCount()
                        - o1.getReviewCount() * o1.getSaleCount());
                break;
            case "review":
                products.sort(((o1, o2) -> o2.getReviewCount() - o1.getReviewCount()));
                break;
            case "date":
                products.sort(((o1, o2) -> o2.getCreateDate().compareTo(o1.getCreateDate())));
                break;
            case "saleCount":
                products.sort(((o1, o2) -> o2.getSaleCount() - o1.getSaleCount()));
                break;
            case "price":
                products.sort(((o1, o2) -> o2.getPromotePrice().compareTo(o1.getPromotePrice())));
                break;
        }
    }

    @Override
    public List<Product> findByKeywords(String keywords) {
        ProductExample productExample = new ProductExample();
        productExample.setOrderByClause("id desc");
        ProductExample.Criteria criteria = productExample.createCriteria();
        criteria.andNameLike("%"+ keywords +"%");
        return productDao.selectByExample(productExample);
    }
}
