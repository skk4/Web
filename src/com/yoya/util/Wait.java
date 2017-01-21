package com.yoya.util;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wait {
	
	/**
	 * 线程休眠等待 time是毫秒
	 * @param time
	 */
	public static void waitMilliSeconds(long time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 *隐式等待
	 */
	
	public static void implicitlyWait(WebDriver driver,long time){
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS); 
	}
	
	
	/**
	 * 显示等待
	 */
	
	public static WebElement explicitlyWait(WebDriver driver,long timeout,By by){
		WebDriverWait wait = new WebDriverWait(driver,timeout);  
        WebElement element=wait.until(new ExpectedCondition<WebElement>(){  
        	@Override  
            public WebElement apply(WebDriver d) {  
            	return d.findElement(by);  
            }});
		return element;
        
	}
	
}
