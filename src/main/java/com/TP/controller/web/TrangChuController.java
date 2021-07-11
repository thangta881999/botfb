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

 import javax.servlet.http.HttpSession;
 import java.io.IOException;
 import java.util.List;

 @Controller
@RequestMapping("/") 
public class TrangChuController {
	/*
	 * @Autowired SanPhamService sanPhamService;
	 */
	
	@Autowired
	ISanPham sanPhamService;
	
	@Autowired
	IDanhMuc danhMucService;
	@GetMapping
	public String Default( ModelMap modelMap, HttpSession httpSession) throws IOException {
	
		if(null != httpSession.getAttribute("giohang")) {
			List<GioHang> listGioHangs = (List<GioHang>) httpSession.getAttribute("giohang");
			modelMap.addAttribute("soluongsanphamgiohang", listGioHangs.size());
		}
		
		List<SanPhamDTO> listSanPhams = sanPhamService.findAll(0,40);
		List<SanPhamDTO> allSanPhams = sanPhamService.findAll(-1,-1);
		double tongPages = Math.ceil((double) allSanPhams.size() / 40);
		
		
		modelMap.addAttribute("listSanPhams",listSanPhams);
		modelMap.addAttribute("listallSanpham", allSanPhams);
		modelMap.addAttribute("tongsoPages", tongPages);
		List<DanhMucDTO> danhMucDTOs = danhMucService.CreateMenu();
		modelMap.addAttribute("danhmucDTO", danhMucDTOs);
	
		return "web/trangchu1";
	}

}
