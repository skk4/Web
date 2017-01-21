package com.yoya.util;

import java.util.Set;

import org.openqa.selenium.WebDriver;

/**
 * 切换窗口
 * @author linan
 *
 */
public class DriverExchangeAssist {
	
	/**
	 * 通过窗口的标题查找目的窗口
	 * @param driver
	 * @param title
	 * @return
	 */
	public static WebDriver exchangeDriverUseTitle(WebDriver driver,String title){
		WebDriver target=driver;
		Set<String> windows=driver.getWindowHandles();
		
		for(String s:windows){
			target=driver.switchTo().window(s);
			if(target.getTitle().equalsIgnoreCase(title)){
				break;
			}
		}
		return target;
		
	}
	
	/**
	 * 通过部分url地址来寻找目的窗口，一般采用地址的主机地址部分以及接口
	 * @param driver
	 * @param partial_url
	 * @return
	 */
	public static WebDriver exchangeDriverUsePartialUrl(WebDriver driver,String partial_url){
		WebDriver target=driver;
		Set<String> windows=driver.getWindowHandles();
		
		for(String s:windows){
			target=driver.switchTo().window(s);
			if(target.getCurrentUrl().contains(partial_url)){
				break;
			}
		}
		
		return target;
		
	}
	

}
