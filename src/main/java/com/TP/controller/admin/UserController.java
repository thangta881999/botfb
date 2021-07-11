package com.TP.controller.admin;

import com.TP.DTO.MyUser;
import com.TP.IService.IRole;
import com.TP.IService.IUser;
import com.TP.entity.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class UserController {

    @Autowired
    IUser userService;

    @Autowired
    IRole roleService;


    @GetMapping
    private String Default(ModelMap modelMap) {

        List<MyUser> listUsers = userService.findAll(0,5);
        List<MyUser> allUsers = userService.findAll(-1,-1);

        List<RoleEntity> danhSachRole = (List<RoleEntity>) roleService.LayDanhSachRole();
        List<RoleEntity> allRoles= roleService.findAll(-1,-1);
        double tongPages = Math.ceil((double) allUsers.size() / 2);

        modelMap.addAttribute("danhsachUser",listUsers);
        modelMap.addAttribute("danhsachallRole",allUsers);
        modelMap.addAttribute("tongsoPages", tongPages);
        modelMap.addAttribute("danhsachRole",listUsers);

        return "admin/themnguoidung";
    }
}
