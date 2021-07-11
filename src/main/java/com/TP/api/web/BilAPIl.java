package com.TP.api.web;

import com.TP.DTO.HoaDonDTO;
import com.TP.IService.IChiTietHoaDon;
import com.TP.IService.IHoaDon;
import com.TP.converter.HoaDonConverter;
import com.TP.entity.HoaDon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "APIBillOfUser")
@RequestMapping("user/api/")
public class BilAPIl {

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
	public List<HoaDonDTO> findAll(@RequestParam int userId,@RequestParam int offset, @RequestParam int limit)
	{
		return hoaDonService.findAllByUserId(userId,offset, limit);
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
