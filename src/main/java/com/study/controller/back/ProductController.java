package com.study.controller.back;

import com.study.pojo.category.Category;
import com.study.pojo.entity.Page;
import com.study.pojo.product.Product;
import com.study.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/admin_product_list")
    public String findAll(Model model, Page page, Integer cid) {
        List<Product> products = productService.findByCid(page, cid);
        productService.fillCategory(products);
        productService.fillFirstImage(products);
        model.addAttribute("products", products);
        Category category = null;
        if (products != null && products.size() > 0) {
            category = products.get(0).getCategory();
        }
        model.addAttribute("category", category);
        model.addAttribute("page", page);
        return "admin/listProduct";
    }

    @RequestMapping("/admin_product_save")
    public String save(Product product) {
        if (product.getId() != null) {
            productService.update(product);
        } else {
            productService.save(product);
        }
        return "redirect:/admin_product_list?cid=" + product.getCid();
    }

    @RequestMapping("/admin_product_delete")
    public String delete(Integer id) {
        Product resProduct = productService.findById(id);
        productService.delete(id);
        return "redirect:/admin_product_list?cid=" + resProduct.getCid();
    }

    @RequestMapping("/admin_product_update")
    public String update(Integer id, Model model) {
        Product product = productService.findById(id);
        productService.fillCategory(product);
        productService.fillFirstImage(product);
        model.addAttribute("product", product);
        return "admin/editProduct";
    }

}
