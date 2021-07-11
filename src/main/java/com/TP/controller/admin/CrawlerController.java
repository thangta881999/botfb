package com.TP.controller.admin;

import com.TP.Respone.CrawlerResponse;
import com.TP.helper.Helper;
import com.TP.service.CrawlerService;
import com.TP.service.DanhMucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.lang.Thread.State;

@Controller
@RequestMapping("admin/crawler")
public class CrawlerController {
	CrawlerService crawler;
	@Autowired
	DanhMucService danhMucService;
	@SuppressWarnings("deprecation")
	@GetMapping
	public String Default(ModelMap modelMap) throws IOException {
		Helper.crawlerBy=1;
		if (crawler==null)
		{
			
			ApplicationContext context = new ClassPathXmlApplicationContext("IoC.xml");
			crawler=(CrawlerService) context.getBean("crawlerService");
		}
		
		if (crawler.getState()== State.RUNNABLE || crawler.getState()== State.BLOCKED || crawler.getState()== State.TIMED_WAITING || 
				crawler.getState()== State.WAITING)
		{
			crawler.resume();
		}
		
		
		else
		{
		if (crawler.getState()== State.TERMINATED )
		{
			ApplicationContext context = new ClassPathXmlApplicationContext("IoC.xml");
			crawler=(CrawlerService) context.getBean("crawlerService");
			crawler.start();
		}
		else
		{
			try {
				crawler.start();
				modelMap.addAttribute("state", "Đang tiến hành");
				System.out.println("State4:" + crawler.getState());
			} catch (Exception e) {
				System.out.println("State6:" + crawler.getState());
			}
		}
		}
	
		return "admin/crawler";
	}
	
	

	@SuppressWarnings("deprecation")
	@GetMapping(path = "/changestate")
	@ResponseBody
	public CrawlerResponse.State ChangeState(@RequestParam String action) throws IOException, InterruptedException {
		System.out.println("State change:" + crawler.getState() );
		if ((crawler.getState()== State.TERMINATED ) && action.equals("play"))
				{
			ApplicationContext context = new ClassPathXmlApplicationContext("IoC.xml");
			crawler=(CrawlerService) context.getBean("crawlerService");
			crawler.start();
			return CrawlerResponse.State.PLAY;
				}
		if (action.equals("stop"))
		{
			crawler.stop();
				return CrawlerResponse.State.STOP;
		}
		if (action.equals("refresh"))
			{
			crawler.stop();
			ApplicationContext context = new ClassPathXmlApplicationContext("IoC.xml");
			crawler=(CrawlerService) context.getBean("crawlerService");
			crawler.start();
					return CrawlerResponse.State.REFRESH;
			}
		if (action.equals("pause")) {
			System.out.println("State:" + crawler.getState());
			System.out.println("Waiting for Thread to ...");
				crawler.suspend();
			return CrawlerResponse.State.PAUSE;
			
		} 
		else 
		{
			System.out.println("State:" + crawler.getState());
			System.out.println("Resume ...");
				crawler.resume();
				return CrawlerResponse.State.PLAY;
		}
		
		
		

	}

}
