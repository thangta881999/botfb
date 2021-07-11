package com.TP.controller.web;

import com.TP.DTO.DanhMucDTO;
import com.TP.IService.IDanhMuc;
import com.TP.entity.GioHang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/contact")
@SessionAttributes("giohang")
public class ContactController {

	@Autowired
	IDanhMuc danhMucService;

	@GetMapping
	public String Default(ModelMap modelMap, HttpSession httpSession) {
		if (null != httpSession.getAttribute("giohang")) {
			List<GioHang> listGioHangs = (List<GioHang>) httpSession.getAttribute("giohang");
			modelMap.addAttribute("soluongsanphamgiohang", listGioHangs.size());
		}
		List<DanhMucDTO> danhMucDTOs = danhMucService.CreateMenu();
		modelMap.addAttribute("danhmucDTO", danhMucDTOs);
	
		return "web/contact";
	}
}
