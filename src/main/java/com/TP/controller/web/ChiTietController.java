package com.TP.controller.web;

import com.TP.DTO.DanhMucDTO;
import com.TP.IService.IDanhMuc;
import com.TP.IService.IReviewService;
import com.TP.IService.ISanPham;
import com.TP.entity.GioHang;
import com.TP.entity.Review;
import com.TP.entity.SanPham;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/chitiet")
@SessionAttributes("giohang")
public class ChiTietController {

	@Autowired
	ISanPham sanPhamService;

	@Autowired
	IDanhMuc danhMucService;

	@Autowired
	IReviewService reviewService;

	@GetMapping(path = "/{masanpham}", produces = "text/plain; charset=utf-8")
	public String Default(@PathVariable int masanpham, ModelMap modelMap, HttpSession httpSession) {

		SanPham sanPham = sanPhamService.LayDanhSachChiTietSanPhamTheoMa(masanpham);

		if(null != httpSession.getAttribute("giohang")) {
			List<GioHang> listGioHangs = (List<GioHang>) httpSession.getAttribute("giohang");
			modelMap.addAttribute("soluongsanphamgiohang", listGioHangs.size());
		}

		modelMap.addAttribute("chiTietSanPham", sanPham);
		List<DanhMucDTO> danhMucDTOs = danhMucService.CreateMenu();
		modelMap.addAttribute("danhmucDTO", danhMucDTOs);
		List<Review> reviews= reviewService.findByroductId(masanpham);
		modelMap.addAttribute("reviews", reviews);
		modelMap.addAttribute("averageRating",
				(double) Math.round(reviewService.averageRatingByProductId(masanpham)*100)/100);
		return "web/chitiet";
	}
}