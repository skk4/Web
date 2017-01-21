package com.yoya.page.yun;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.yoya.util.MyYunAssist;
import com.yoya.util.Wait;

public class MyYunRecycle {
	
	private WebDriver driver;
	
	public MyYunRecycle(WebDriver driver){
		this.driver=driver;
	}
	

	  /**
	   * 进入回收站页面
	   */
	  public WebDriver enterRecycle(){
		  driver.findElement(By.linkText("回收站")).click();
		  Wait.waitMilliSeconds(5000);
		  MyYunAssist.locateIframe(driver, "main");
		  return driver;
	  }
	  
	  
	  public void clickCleanAllBtn(){
		  driver.findElement(By.id("btn-clearall")).click();
		  Wait.waitMilliSeconds(5000);
	  }
	  
	  public WebElement getTitleTip(){
		  return driver.findElement(By.xpath("//div[starts-with(@id,layui-layer)]/div[@class='layui-layer-title']"));
	  }
	  
	  public WebElement getContentTip(){
		  return driver.findElement(By.xpath("//div[starts-with(@id,layui-layer)]/div[@class='layui-layer-content']"));
	  }
	  
	  
	  public void confirm(){
		  driver.findElement(By.className("layui-layer-btn0")).click();
	  }
	  
	  
	  public void chooseFile(String file_name){
		  driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+file_name+"']"+"/td[@class='t-checkbox']/div/input")).click();
		  Wait.waitMilliSeconds(5000);
	  }
	  public void clickDeleteButton(){
		  driver.findElement(By.className("delete")).click();
		  Wait.waitMilliSeconds(5000);
	  }
	  
	  public void clickRecoverBtn(){
		  driver.findElement(By.id("btn-restore-file")).click();
	  }
	  public WebElement getTip(){
		  return driver.findElement(By.xpath("//div[starts-with(@id,'layui-layer')]/div"));
	  }
	  
}
