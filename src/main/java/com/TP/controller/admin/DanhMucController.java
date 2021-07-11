package com.TP.controller.admin;

import com.TP.IService.IDanhMuc;
import com.TP.entity.DanhMucSanPham;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/categories")
public class DanhMucController {
	
	@Autowired
	IDanhMuc danhMucService;
	
	
	@GetMapping
	private String Default(ModelMap modelMap) {
	
		List<DanhMucSanPham> danhMucSanPhams= danhMucService.findAll(0,6);
		List<DanhMucSanPham> allcategories= danhMucService.findAll(-1,-1);
		double tongPages = Math.ceil((double) allcategories.size() / 6);
	
		modelMap.addAttribute("danhmuc",danhMucSanPhams);
		modelMap.addAttribute("tongsoPages", tongPages);
		return "admin/danhmuc"; 
	}
}
