package com.TP.controller.web;

import com.TP.IService.IUser;
import com.TP.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUser userService;
    @GetMapping( "/{userId}")
    public String Default(@PathVariable int userId, ModelMap modelMap) {
        UserEntity user =  userService.findUserById(userId);
        modelMap.addAttribute("user",user);
        return "web/user";
    }
}