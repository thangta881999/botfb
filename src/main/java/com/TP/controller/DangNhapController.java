package com.TP.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.TP.entity.UserEntity;
import com.TP.service.UserService;

@Controller
@RequestMapping("dangnhap")
public class DangNhapController {

	@Autowired
	UserService UserService;
	
	@GetMapping
	public String Default() {
		return "dangnhap";
	}	
	
	@PostMapping
	public String DangKy(@RequestParam String username, @RequestParam String password, @RequestParam String re_password, ModelMap modelMap){
		boolean checkMail = validate(username);
		if(checkMail) {
			if(password.equals(re_password)) {
				UserEntity UserEntity = new UserEntity();
				UserEntity.setUserName(username);
				UserEntity.setFullName(username);
				UserEntity.setPassword(password);
				UserEntity.setStatus(1);
				boolean ktthem = UserService.save(UserEntity);
				if(ktthem)
					modelMap.addAttribute("kiemtra","Đăng ký thành công");
				else
					modelMap.addAttribute("kiemtra", "Đăng ký thất bại");
			}else {
				modelMap.addAttribute("kiemtra","Mật khẩu không trùng khớp");
			}
		}else {
			modelMap.addAttribute("kiemtra", "Vui lòng nhập đúng định dạng email");
		}
		return "dangnhap";
	}
	@RequestMapping(value = "/thoat", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		return new ModelAndView("redirect:/dangnhap?accessDenied");
	}
	
	public static final Pattern VAL_ID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	public static boolean validate(String emailStr) {
		Matcher matcher = VAL_ID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}
}
