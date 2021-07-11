package com.TP.controller.web;

import com.TP.DTO.DanhMucDTO;
import com.TP.DTO.SanPhamDTO;
import com.TP.IService.IDanhMuc;
import com.TP.IService.ISanPham;
import com.TP.entity.GioHang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController
{
		@Autowired
		ISanPham sanPhamService;
		@Autowired
		IDanhMuc danhMucService;
		
		@GetMapping
		public String Default(@RequestParam String keyword,@RequestParam(defaultValue = "0")  int offset,@RequestParam(defaultValue = "20") int limit, ModelMap modelMap,HttpSession httpSession ) {
			if(null != httpSession.getAttribute("giohang")) {
				List<GioHang> listGioHangs = (List<GioHang>) httpSession.getAttribute("giohang");
				modelMap.addAttribute("soluongsanphamgiohang", listGioHangs.size());
			}
			List<DanhMucDTO> danhMucDTOs = danhMucService.CreateMenu();
			modelMap.addAttribute("danhmucDTO", danhMucDTOs);
		
			modelMap.addAttribute("keyword", keyword);
			
			List<SanPhamDTO> dtos = sanPhamService.search(keyword,offset,limit);
			modelMap.addAttribute("listSanPhams", dtos);
			
			return "web/search";
		}
}