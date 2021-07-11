package com.TP.controller.admin;

import com.TP.DTO.HoaDonDTO;
import com.TP.IService.IHoaDon;
import com.TP.entity.ChiTietHoaDon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin/bill")
public class DonHangController {
	@Autowired 
	IHoaDon hoaDonService;
	@GetMapping
	private String Default(ModelMap modelMap) {
	
		List<HoaDonDTO> allBills = hoaDonService.findAll(-1, -1);
		List<HoaDonDTO> bills = hoaDonService.findAll(0, 6);
			
		double tongPages = Math.ceil((double) allBills.size() / 6);
	
		modelMap.addAttribute("bills",bills);
		modelMap.addAttribute("tongsoPages", tongPages);
		return "admin/donhang"; 
	}
	
	@GetMapping(value = "/{mahoadon}/details")
	private String DetailsofBill(@PathVariable int mahoadon,ModelMap modelMap) {
	
		List<HoaDonDTO> allBills = hoaDonService.findAll(-1, -1);
		HoaDonDTO dto= hoaDonService.findById(mahoadon);
		Set<ChiTietHoaDon> chiTietHoaDons = dto.getDanhsachChiTietHoaDon();
	
		modelMap.addAttribute("details",chiTietHoaDons);
		return "admin/chitiethoadon"; 
	}
}
