package com.TP.api.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.TP.DTO.DanhMucDTO;
import com.TP.IService.IDanhMuc;
import com.TP.Respone.ValidRespone;
import com.TP.converter.DanhMucConverter;
import com.TP.entity.DanhMucSanPham;

@RestController(value = "APIOCategoriesfAdmin")
@RequestMapping("admin/api/")
public class APICategories { 
	@Autowired 
	IDanhMuc danhMucService;
	@Autowired
	DanhMucConverter converter;
	
	@GetMapping(path = "categories", produces="application/json; charset=utf-8")
	@ResponseBody
	public List<DanhMucDTO> findAll(@RequestParam int offset,@RequestParam int limit) {
		List<DanhMucDTO> danhmucs= new ArrayList<DanhMucDTO>();
		List<DanhMucSanPham> entitys= new ArrayList<DanhMucSanPham>();
		entitys=danhMucService.findAll(offset, limit);
		for (int i=0; i<entitys.size();i++)
		{
			danhmucs.add(converter.toDTO(entitys.get(i))); 
		}
		return danhmucs;
	}
	@GetMapping("/XoaDanhMuc")
	@ResponseBody
	public String delete(@RequestParam int madm)
	{
		danhMucService.deleteAll(madm);
		return "true";
	}
	@GetMapping("/LayDanhMucTheoMa")
	@ResponseBody
	public DanhMucDTO findById(@RequestParam int madm)
	{
		return danhMucService.findById(madm);
	}
	@PostMapping(value="categories",  produces = { MediaType.APPLICATION_JSON_VALUE })
	 public ValidRespone themsanpham(@Valid @RequestBody DanhMucSanPham dataJson, BindingResult result)
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
           danhMucService.save(dataJson); 
       }
       return response;
		/* sanPhamService.themSanPham(dataJson); */
	}
	@PutMapping(value="categories",  produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	 public ValidRespone update(@Valid @RequestBody DanhMucSanPham dataJson, BindingResult result)
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
            danhMucService.save(dataJson);
        }
        return response;
	}

}
