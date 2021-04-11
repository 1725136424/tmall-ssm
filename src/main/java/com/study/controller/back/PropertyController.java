package com.study.controller.back;

import com.study.pojo.category.Category;
import com.study.pojo.entity.Page;
import com.study.pojo.property.Property;
import com.study.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @RequestMapping("/admin_property_list")
    public String findAll(Model model, Page page, Integer cid) {
        List<Property> properties = propertyService.findByCid(page, cid);
        propertyService.fillCategory(properties);
        page.setParam("&cid=" + properties.get(0).getCategory().getId());
        Category category = null;
        if (properties != null && properties.size() > 0) {
            category = properties.get(0).getCategory();
        }
        model.addAttribute("properties", properties);
        model.addAttribute("category", category);
        model.addAttribute("page", page);
        return "admin/listProperty";
    }

    @RequestMapping("/admin_property_save")
    public String save(Property property, Model model) {
        if (property.getId() != null) {
            propertyService.update(property);
        } else {
            propertyService.save(property);
        }
        return "redirect:/admin_property_list?cid=" + property.getCid();
    }

    @RequestMapping("/admin_property_delete")
    public String delete(Integer id, Model model) {
        Property resProperty = propertyService.findById(id);
        propertyService.delete(id);
        return "redirect:/admin_property_list?cid=" + resProperty.getCid();
    }

    @RequestMapping("/admin_property_update")
    public String update(Integer id, Model model) {
        Property property = propertyService.findById(id);
        propertyService.fillCategory(property);
        model.addAttribute("property", property);
        return "admin/editProperty";
    }

}
