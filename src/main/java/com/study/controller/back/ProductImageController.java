package com.study.controller.back;

import com.study.pojo.product.Product;
import com.study.pojo.product.ProductImage;
import com.study.service.ProductImageService;
import com.study.service.ProductService;
import com.study.util.ImageUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("")
public class ProductImageController {

    @Autowired
    private ProductImageService productImageService;

    @Autowired
    private ProductService productService;

    @RequestMapping("/admin_productImage_list")
    public String list(Integer pid, Model model) {
        Product product = productService.findById(pid);
        List<ProductImage> detailProductImage = productImageService.findByPidAndType(pid, ProductImageService.type_detail);
        List<ProductImage> singleProductImage = productImageService.findByPidAndType(pid, ProductImageService.type_single);
        model.addAttribute("detailProductImage", detailProductImage);
        model.addAttribute("singleProductImage", singleProductImage);
        model.addAttribute("product", product);
        return "admin/listProductImage";
    }

    @RequestMapping("/admin_productImage_save")
    public String list(MultipartFile file, HttpSession session, ProductImage productImage) throws IOException {
        productImageService.save(productImage);
        String type = StringUtils
                .capitalize(StringUtils.substringAfterLast(productImage.getType(), "_"));
        String targetPath = "/img/product" + type + "/" + productImage.getId() + ".jpg";
        ImageUtil.uploadImage(file, session, targetPath);
        return "redirect:/admin_productImage_list?pid=" + productImage.getPid();
    }

    @RequestMapping("/admin_productImage_delete")
    public String delete(Integer id, Model model) {
        ProductImage productImage = productImageService.findById(id);
        productImageService.delete(id);
        return "redirect:/admin_productImage_list?pid=" + productImage.getPid();
    }
}
