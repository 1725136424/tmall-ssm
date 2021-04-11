package com.study.controller.back;

import com.study.pojo.product.Product;
import com.study.pojo.property.PropertyValue;
import com.study.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("")
public class PropertyValueController {

    @Autowired
    private PropertyValueService propertyValueService;

    @RequestMapping("admin_propertyValue_edit")
    public String edit(Integer pid, Model model) {
        List<PropertyValue>  propertyValues = propertyValueService.findByPid(pid);
        propertyValueService.fillProperty(propertyValues);
        propertyValueService.fileProduct(propertyValues);
        Product product = null;
        if (propertyValues.size() > 0) {
          product =   propertyValues.get(0).getProduct();
        }
        model.addAttribute("product", product);
        model.addAttribute("productValues", propertyValues);
        return "admin/editPropertyValue";
    }

    @RequestMapping("/admin_propertyValue_update")
    @ResponseBody
    public String update(PropertyValue propertyValue) {
        try {
            propertyValueService.update(propertyValue);
            return "success";
        } catch (Exception e) {
            return "fail";
        }
    }
}
