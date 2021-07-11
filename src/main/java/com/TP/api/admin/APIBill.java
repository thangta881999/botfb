package com.TP.api.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.TP.DTO.HoaDonDTO;
import com.TP.IService.IChiTietHoaDon;
import com.TP.IService.IHoaDon;
import com.TP.converter.HoaDonConverter;
import com.TP.entity.HoaDon;

@RestController(value = "APIBillOfAdmin")
@RequestMapping("admin/api/")
public class APIBill {

	@Autowired
	IHoaDon hoaDonService;
	@Autowired
	HoaDonConverter converter;

	@Autowired
	IChiTietHoaDon chiTietHoaDonService;
	
	
	@GetMapping(path = "bill/{id}",produces="application/json; charset=utf-8")
	@ResponseBody
	public HoaDonDTO findById(@PathVariable int id)
	{
		return hoaDonService.findById(id);
	}
	@GetMapping(path = "bill",produces="application/json; charset=utf-8")
	@ResponseBody
	public List<HoaDonDTO> findAll(@RequestParam int offset, @RequestParam int limit)
	{
		return hoaDonService.findAll(offset, limit);
	}
	
	@PutMapping(value="bill")
	@ResponseBody
	 public String update( @RequestBody HoaDon hoaDon)
	 {
		/*
		 * HoaDonDTO dto= hoaDonService.findById(hoaDon.getMahoadon()); if
		 * (hoaDon.getTinhtrang()!=null) { dto.setTinhtrang(hoaDon.getTinhtrang()); } if
		 * (hoaDon.getGhichu()!=null) { dto.setGhichu(hoaDon.getGhichu()); }
		 */
	
		/* return String.valueOf(hoaDonService.save(converter.toEntity(dto))); */
		return String.valueOf(hoaDonService.save(hoaDon));
	}
	@DeleteMapping(value="bill")
	public String deleteById(@RequestBody int mahoadon)
	{
		hoaDonService.deleteById(mahoadon);
		return "true";
	}

}
