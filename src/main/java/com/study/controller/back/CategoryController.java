package com.study.controller.back;


import com.study.pojo.category.Category;
import com.study.pojo.entity.Page;
import com.study.service.CategoryService;
import com.study.util.ImageUtil;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/admin_category_list")
    public String findAll(Model model, Page page) {
        List<Category> categories = categoryService.findAll(page);
        model.addAttribute("categories", categories);
        model.addAttribute("page", page);
        return "admin/listCategory";
    }

    @RequestMapping("/admin_category_save")
    public String save(MultipartFile file, Category category, HttpSession session) throws IOException {
        if (category.getId() != null) {
            categoryService.update(category);
        } else {
            categoryService.save(category);
        }
        String targetPath = "/img/category/" + category.getId() + ".jpg";
        ImageUtil.uploadImage(file, session, targetPath);
        return "redirect:/admin_category_list";
    }

    @RequestMapping("/admin_category_delete")
    public String findAll(Integer id) {
        categoryService.delete(id);
        return "redirect:/admin_category_list";
    }

    @RequestMapping("/admin_category_edit")
    public String edit(Integer id, Model model) {
        Category resCategory = categoryService.findById(id);
        model.addAttribute("category", resCategory);
        return "admin/editCategory";
    }
}
