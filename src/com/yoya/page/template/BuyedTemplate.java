package com.yoya.page.template;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.yoya.util.Wait;

public class BuyedTemplate {
	private WebDriver driver;
	
	public BuyedTemplate(WebDriver driver){
		this.driver=driver;
	}
	
	public WebElement findTemplate(String template_name){
		WebElement template=driver.findElement(By.xpath("//div[@id='template_list_src']/ul/li[@data-goods_name='"+template_name+"']"));
		  Actions action=new Actions(driver);
		  action.moveToElement(template).perform();
		  Wait.waitMilliSeconds(5000);
		  return template;
	}
	
	public WebDriver clickPreviewBtn(WebElement template){
		  template.findElement(By.xpath(".//div[@class='text' and text()='预览']")).click();
		  Wait.waitMilliSeconds(5000);
		  return driver;
	}
	
	public WebDriver clickCreateBtn(WebElement template){
		  template.findElement(By.xpath(".//div[@class='text' and text()='创建']")).click();
		  Wait.waitMilliSeconds(5000);
		  return driver;
	}
	
}
