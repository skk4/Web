package com.yoya.util;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * 定位页面元素
 * @author lin
 *
 */
public class LocateElement {
	
	/**
	 * 通过文本定位元素
	 */
	public static WebElement locateElementUseText(WebDriver driver,String xpath,String text){
		List<WebElement> wes=driver.findElements(By.xpath(xpath));
		  WebElement target=null;
		  for(WebElement ws:wes){
			  if(ws.getText().trim().equalsIgnoreCase(text)){
				  target=ws;
				  break;
			  }
		  }
		  return target;
	}
	
	/**
	 * 通过模糊文本定位元素
	 */
	public static WebElement locateElementUsePartialText(WebDriver driver,String xpath,String partialtext){
		List<WebElement> wes=driver.findElements(By.xpath(xpath));
		  WebElement target=null;
		  for(WebElement ws:wes){
			  if(ws.getText().trim().contains(partialtext)){
				  target=ws;
				  break;
			  }
		  }
		  return target;
	}
	
	public static WebElement locateElementUseTextUnderElement(WebElement e,String xpath,String text){
		List<WebElement> wes=e.findElements(By.xpath(xpath));
		  WebElement target=null;
		  for(WebElement ws:wes){
			  if(ws.getText().trim().equalsIgnoreCase(text)){
				  target=ws;
				  break;
			  }
		  }
		  return target;
	}
	

}
