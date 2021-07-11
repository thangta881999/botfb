package com.TP.api.web;

import com.TP.DTO.TinhTrangEnum;
import com.TP.IService.IChiTietHoaDon;
import com.TP.IService.IChiTietSanPham;
import com.TP.IService.IHoaDon;
import com.TP.IService.IUser;
import com.TP.Respone.HoaDonResponse;
import com.TP.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/")
public class OrderAPI {

	@Autowired
	IHoaDon hoaDonService;

	@Autowired
	IChiTietHoaDon chiTietHoaDonService;
	@Autowired
	IUser userService;
	@Autowired

	IChiTietSanPham chiTietSanPhamService;
	@GetMapping("bill/checkbilluserdeliverd")
	public String checkBillUserDeliverd(@RequestParam(value = "userId") int userId,@RequestParam(value = "masanpham") int masanpham)
	{
		String tenkhachhang=userService.findUserById(userId).getFullName();
		return hoaDonService.CheckBillUserDelivered(masanpham, tenkhachhang) ?"true":"false";
	}
	@PostMapping(path = "order")

	public HoaDonResponse Insert(@ModelAttribute @Valid HoaDon hoaDon, BindingResult result, HttpSession httpSession) {
		HoaDonResponse response = new HoaDonResponse();
		Map<String, String> errors = new HashMap<String, String>();
		if (null != httpSession.getAttribute("giohang")) {
			if (result.hasErrors()) {

				errors = result.getFieldErrors().stream()
						.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

				response.setValidated(false);
				response.setErrorMessages(errors);
				return response;
			}

			response.setValidated(true);
			List<GioHang> listGioHangs = (List<GioHang>) httpSession.getAttribute("giohang");

			if (listGioHangs.size() < 0) {
				response.setValidated(false);
				return response;
			}
			if (listGioHangs.size() > 0) {
				for (GioHang gioHang : listGioHangs) {

					ChiTietSanPham ctsp = chiTietSanPhamService.findById(gioHang.getMachitiet());
					if (gioHang.getSoluong() > ctsp.getSoluong()) {

						errors.put(String.valueOf(gioHang.getMachitiet()),
								"Số lượng sản phẩm còn lại: " + String.valueOf(ctsp.getSoluong()));
						response.setValidated(false);
						response.setErrorMessages(errors);
						response.setValidated(false);

					}
				}
				if (response.isValidated()==false)
				{
					return response;
				}
				
			}
			hoaDon.setTinhtrang(TinhTrangEnum.INP);
			hoaDon.setThanhtoan(false);
			int idHoaDon = hoaDonService.save(hoaDon);
			if (idHoaDon > 0) {
				response.setId(idHoaDon);
				Set<ChiTietHoaDon> listChiTietHoaDon = new HashSet<ChiTietHoaDon>();

				for (GioHang gioHang : listGioHangs) {

					ChiTietSanPham ctsp = chiTietSanPhamService.findById(gioHang.getMachitiet());
					
						ChiTietHoaDonId chiTietHoaDonId = new ChiTietHoaDonId();
						chiTietHoaDonId.setMachitietsanpham(gioHang.getMachitiet());
						chiTietHoaDonId.setMahoadon(hoaDon.getMahoadon());

						ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
						chiTietHoaDon.setChiTietHoaDonId(chiTietHoaDonId);
						chiTietHoaDon.setGiatien(gioHang.getGiatien());
						chiTietHoaDon.setSoluong(gioHang.getSoluong());

						chiTietHoaDonService.ThemChiTietHoaDon(chiTietHoaDon);

						ChiTietSanPham chiTietSanPham = new ChiTietSanPham();

						chiTietSanPham.setMachitietsanpham(gioHang.getMachitiet());
						chiTietSanPham.setSoluong(gioHang.getSoluong());

						chiTietSanPhamService.save(chiTietSanPham);

				}
			}

		}

		return response;
	}
}
