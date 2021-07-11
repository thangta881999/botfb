package com.TP.controller.admin;

import com.TP.DTO.HoaDonDTO;
import com.TP.IService.IHoaDon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")

public class DashboardController {
	
	@Autowired
	IHoaDon hoaDonService;
	
	@GetMapping
		public String Default(ModelMap modelMap) {
		
		Long billsuccess= hoaDonService.getBillsuccess();
		Long billprocessing = hoaDonService.getBillprocessing();
		int revenue = hoaDonService.getTotalrevenue();
		List<HoaDonDTO> hd=hoaDonService.findAll(-1, -1);
		int totalBill = hd.size();
		modelMap.addAttribute("billsuccess",billsuccess);
		modelMap.addAttribute("billprocessing",billprocessing);
		modelMap.addAttribute("totalBill",totalBill);
		modelMap.addAttribute("revenue",revenue);
		modelMap.addAttribute("monthlyrevenue",hoaDonService.getMonthlyrevenue());
		modelMap.addAttribute("productSellBycat",hoaDonService.getProductSellbyCat());
		return "admin/dashboard";
	}
}
