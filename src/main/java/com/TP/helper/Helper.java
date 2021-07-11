package com.TP.helper;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;

public class Helper {
	
	@Autowired
	public static ServletContext context;
	public static int crawlerBy=1;
	
	public static String getPathSaveImg()
	{
		return "C:\\Users\\Admin\\Desktop\\GitProjects\\TPShop\\src\\main\\webapp\\resources\\images\\sanpham\\";
	}

}
