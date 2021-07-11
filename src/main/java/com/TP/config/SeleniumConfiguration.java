package com.TP.config;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class SeleniumConfiguration {
    @PostConstruct
    void  postConstruct()
    {
        String driverPath =  "D:\\chromedriver_win32\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",driverPath);
    }

    @Bean
    public  void driver()
    {
        final ChromeOptions chromeOptions = new ChromeOptions();
        // Set the allowable frame
        chromeOptions.addArguments("disable-infobars","disable-web-security");
        // Set no gui development or do not add, you can see the browser effect
        chromeOptions.addArguments("--headless");
//        return new ChromeDriver();
    }

}
