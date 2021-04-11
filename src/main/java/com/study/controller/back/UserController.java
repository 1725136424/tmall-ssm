package com.study.controller.back;

import com.study.pojo.entity.Page;
import com.study.pojo.user.User;
import com.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/admin_user_list")
    public String list(Page page, Model model) {
        List<User> users = userService.findAll(page);
        model.addAttribute("page", page);
        model.addAttribute("user", users);
        return "admin/listUser";
    }
}
