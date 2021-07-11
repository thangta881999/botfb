package com.TP.api.admin;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.TP.DTO.SanPhamDTO;
import com.TP.IService.ISanPham;
import com.TP.Respone.ValidRespone;
import com.TP.entity.ChiTietSanPham;
import com.TP.entity.DanhMucSanPham;
import com.TP.entity.MauSanPham;
import com.TP.entity.SanPham;
import com.TP.entity.SizeSanPham;
import com.TP.helper.Helper;

@RestController(value = "APIOfAdmin")
@RequestMapping("admin/api/")
public class APIProduct {

	@Autowired 
	ISanPham sanPhamService;
	
	@GetMapping(path = "products", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String GetProducts(@RequestParam int offset,@RequestParam int limit) {
	
		List<SanPhamDTO> listSanPhams = sanPhamService.findAll(offset,limit);
		
		String html = "";
		
		for (SanPhamDTO sanPham : listSanPhams) {
			html += "<tr>";
			html += "<td> \n" + 
					"	<div class=\"checkbox\">\n" + 
					"			<label><input class=\"checkboxsanpham\" type=\"checkbox\" value=\" "+ sanPham.getMasanpham() +"\"></label>\n" + 
					"	</div>\n" + 

					"</td>";
			
			html += "<td class=\"tensp\">" + sanPham.getTensanpham() + "</td>\n" + 
					"<td class=\"hinhsanhpham\"> <img style=\"width:50px;height:50px\" src=\"/resources/images/sanpham/"+sanPham.getHinhsanpham()+"\" /> </td>"
					+
					"<td class=\"giatien\">" + sanPham.getGiatien() + "</td>\n" + 
					"<td class=\"gianhcho\">" + sanPham.getDanhcho() + "</td>" +
			 "<td class=\"btn btn-action capnhatsanpham\" style=\"color:white\"  data-id=\" "+ sanPham.getMasanpham() + " \">"
			 		+ "<i class=\"fa fa-pencil-square-o \" style=\"color:white\"></i> "
			 		+ "</td>";
		
			html += "</tr>";
		}
		
		return html;
	}
	@GetMapping("/XoaSanPham")
	@ResponseBody
	public String XoaSanPhamTheoMaSanPham(@RequestParam int masp)
	{
		sanPhamService.XoaSanPhamTheoMaSanPham(masp);
		return "true";
	}
	@PostMapping("UploadFile")
	@ResponseBody
	public String uploadfile(MultipartHttpServletRequest request) {
		//lay duong dan that su treb server
		// khi build project tren server tomcat thi n se tao ra ban copy, ban copy co duong
		// vd: ../wtpwebapps/duong dan project
		// khi save thi luu tren server
		String path_save_file= Helper.getPathSaveImg();
		Iterator<String> lstNames = request.getFileNames();
		MultipartFile mpf = request.getFile(lstNames.next());
		
		File file = new File(path_save_file +"/"+ mpf.getOriginalFilename());
		
		try {
			mpf.transferTo(file);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	@PostMapping(value="products",  produces = { MediaType.APPLICATION_JSON_VALUE })
	 public ValidRespone themsanpham(@Valid @RequestBody SanPham dataJson, BindingResult result)
	{
		ValidRespone response= new ValidRespone();
        if (result.hasErrors()) {
 
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(
                            Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
                    );
 
            response.setValidated(false);
            response.setErrorMessages(errors);
        } else {
            response.setValidated(true);
            sanPhamService.themSanPham(dataJson); 
        }
        return response;
		/* sanPhamService.themSanPham(dataJson); */
	}
	
	@PostMapping(value="capnhatsanpham",  produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	 public ValidRespone update(@Valid @RequestBody SanPham dataJson, BindingResult result)
	 {
		ValidRespone response= new ValidRespone();
	    if (result.hasErrors()) {
	    	 
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(
                            Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
                    );
 
            response.setValidated(false);
            response.setErrorMessages(errors);
        } else {
 
            response.setValidated(true);
            sanPhamService.capnhatSanPham(dataJson);
        }
        return response;
	}
	@PostMapping(path="laysanphamtheoma",produces="application/json;charset=utf-8")
	@ResponseBody
	public SanPhamDTO LaySanPhamTheoMa(@RequestParam int id)
	{
		
		SanPhamDTO spDTO= new SanPhamDTO();
		SanPham sp = sanPhamService.LayDanhSachChiTietSanPhamTheoMa(id);
		spDTO.setMasanpham(sp.getMasanpham());
		spDTO.setTensanpham(sp.getTensanpham());
		spDTO.setGiatien(sp.getGiatien());
		spDTO.setMota(sp.getMota());
		spDTO.setHinhsanpham(sp.getHinhsanpham());
		spDTO.setDanhcho(sp.getDanhcho());
		DanhMucSanPham danhMucSanPham = new DanhMucSanPham();
		danhMucSanPham.setMadanhmuc(sp.getDanhMucSanPham().getMadanhmuc());
		danhMucSanPham.setTendanhmuc(sp.getDanhMucSanPham().getTendanhmuc());
		spDTO.setDanhMucSanPham(danhMucSanPham);
		
		Set<ChiTietSanPham> chiTietSanPhams = new HashSet<ChiTietSanPham>();
		for (ChiTietSanPham value : sp.getChiTietSanPham())
		{
			ChiTietSanPham chitietSanpham = new ChiTietSanPham();
			chitietSanpham.setMachitietsanpham(value.getMachitietsanpham());
			
			MauSanPham mauSanPham = new MauSanPham();
			mauSanPham.setMamau(value.getMauSanPham().getMamau());
			mauSanPham.setTenmau(value.getMauSanPham().getTenmau());
			
			chitietSanpham.setMauSanPham(mauSanPham);
			
			SizeSanPham sizeSanPham = new SizeSanPham();
			sizeSanPham.setMasize(value.getSizeSanPham().getMasize());
			sizeSanPham.setSize(value.getSizeSanPham().getSize());
			
			chitietSanpham.setSizeSanPham(sizeSanPham);
			chitietSanpham.setSoluong(value.getSoluong());
			chiTietSanPhams.add(chitietSanpham);
		}
		spDTO.setChiTietSanPham(chiTietSanPhams);
		return spDTO;
	}
}
