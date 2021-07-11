package com.TP.controller.admin;

import com.TP.IService.IRole;
import com.TP.entity.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/roles")
public class RoleController {

    @Autowired
    IRole roleService;


    @GetMapping
    private String Default(ModelMap modelMap) {

        List<RoleEntity> danhSachRole = (List<RoleEntity>) roleService.LayDanhSachRole();
        List<RoleEntity> allRoles= roleService.findAll(-1,-1);
        double tongPages = Math.ceil((double) allRoles.size() / 2);

        modelMap.addAttribute("danhsachRole",danhSachRole);
        modelMap.addAttribute("tongsoPages", tongPages);
        return "admin/phanquyen";
    }
}
