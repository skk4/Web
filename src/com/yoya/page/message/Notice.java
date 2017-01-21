package com.yoya.page.message;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.yoya.util.Wait;

public class Notice {
	private WebDriver driver;
	public Notice(WebDriver driver){
		this.driver=driver;
	}
	
	
	public void eneterNotice(){
		driver.findElement(By.partialLinkText("通知公告")).click();
		Wait.waitMilliSeconds(5000);
	}
	
	public WebElement findUnreadMessage(){
		  WebElement message=driver.findElement(By.xpath("//div[@id='list_data']/table/tbody[@class='unread']"));
		return message;
	}
	
	public void clickReadBtn(WebElement message){
		WebElement readbtn=message.findElement(By.xpath(".//div[@class='opts']/a[contains(@class,'btn_read')]"));
		  if(readbtn.isEnabled()){
			  readbtn.click();
			  Wait.waitMilliSeconds(3000);
		  }
	}
	public WebElement findMessageFromID(String message_id){
		return driver.findElement(By.xpath("//tbody[@bill_m_notice_id='"+message_id+"']"));
	}
	
	
}
