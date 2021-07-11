package com.TP.crawler;

import com.TP.DTO.SanPhamDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class HotProducts {
	
	public List<SanPhamDTO> findHotProduct() throws IOException
	{
		  Document document= Jsoup.connect("https://yame.vn/").get();
          Elements elements = document.select(".pitem row mb-3");
          for(Element element : elements)
          {
        	  
          }
		return null;
	}

}
