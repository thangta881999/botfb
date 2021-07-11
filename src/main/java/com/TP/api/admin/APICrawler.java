 package com.TP.api.admin;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.TP.service.CrawlerService;

 @RestController(value = "APICrawlerOfAdmin")
 @RequestMapping("admin/api/")
 public class APICrawler {

     @Autowired
     CrawlerService crawler;

     @GetMapping(path = "crawler", produces = "text/plain; charset=utf-8")
     @ResponseBody
     public String Crawler() throws IOException {
         return crawler.CrawlerData();
     }




 }
