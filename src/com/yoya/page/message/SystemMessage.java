package com.yoya.page.message;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.yoya.util.Wait;

public class SystemMessage {
	
	private WebDriver driver;
	public SystemMessage(WebDriver driver){
		this.driver=driver;
	}
	
	public WebElement findUnreadMessage(){
		  WebElement message=driver.findElement(By.xpath("//div[@id='list_data']/table/tbody[@class='unread']"));
		return message;
	}
	
	public WebElement findfirstMessage(){
		  WebElement message=driver.findElement(By.xpath("//div[@id='list_data']/table/tbody[1]"));
		return message;
	}
	
	
	public WebElement findMessageFromID(String message_id){
		return driver.findElement(By.xpath("//tbody[@bill_m_sys_id='"+message_id+"']"));
	}
	
	
	public void clickReadBtn(WebElement message){
		WebElement readbtn=message.findElement(By.xpath(".//div[@class='opts']/a[contains(@class,'btn_read')]"));
		  if(readbtn.isEnabled()){
			  readbtn.click();
			  Wait.waitMilliSeconds(3000);
		  }
	}
	
	public void clickDeleteBtn(WebElement message){
		WebElement btn=message.findElement(By.xpath(".//div[@class='opts']/a[contains(@class,'btn_del')]"));
		  if(btn.isEnabled()){
			  btn.click();
			  Wait.waitMilliSeconds(5000);
		  }
	}
	

}
