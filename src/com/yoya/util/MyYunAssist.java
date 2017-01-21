package com.yoya.util;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.yoya.test.login.LoginTest;
/**
 * 我的云盘功能的辅助类
 * @author Administrator
 *
 */
public class MyYunAssist {

	/**
	 * 进入我的云盘，并把浏览器切换到我的云盘页面
	 * @param web_driver
	 * @param host_url
	 * @param user_name
	 * @param pass_word
	 * @return
	 */
	public static WebDriver enterMyYun(String web_driver,String host_url,String user_name,String pass_word){
		
		LoginTest lg=new LoginTest();
		WebDriver driver=lg.login(web_driver, host_url, user_name, pass_word);
		Wait.waitMilliSeconds(5000);
		//主窗口
		String home_window=driver.getWindowHandle();
		driver.findElement(By.linkText("我的云盘")).click();
		Wait.waitMilliSeconds(5000);
		//所有窗口
		Set<String> ws=driver.getWindowHandles();
		
		//找到云盘窗口
		for(String current:ws){
			if(!current.equalsIgnoreCase(home_window)){
				driver.switchTo().window(current);
				break;
			}
			
		}
		return driver;
	}
	
	/**
	 * 定位页面中的iframe框架
	 * @param driver
	 * @param locate
	 * @return
	 */
	public static WebElement locateIframe(WebDriver driver,String locate){
		  WebElement we=driver.findElement(By.xpath("//iframe[starts-with(@id,'"+locate+"')]"));
		  driver.switchTo().frame(we);
		  return we;
	}
	
	/**
	 * 根据页面的标题和地址，把WebDriver切换到想要的页面上
	 * @param driver
	 * @param page_title
	 * @param partial_url
	 * @return
	 */
	public static WebDriver locateDriver(WebDriver driver,String page_title,String partial_url){
		
		Set<String> ws=driver.getWindowHandles();
		//找到所需标题的窗口
		for(String c:ws){
			driver.switchTo().window(c);
			String title=driver.getTitle();
			if(title.equalsIgnoreCase(page_title)){
					break;
			}
		}		
		Assert.assertTrue(driver.getCurrentUrl().contains(partial_url));
		return driver;
	}
	
	
}
