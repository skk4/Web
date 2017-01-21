package com.yoya.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.yoya.util.Wait;

public class MyCourse {
	public WebDriver driver;
	
	public MyCourse(WebDriver driver){
		this.driver=driver;
	}

	public WebElement findCourse(String course_title){
		List<WebElement> wes=driver.findElements(By.xpath("//ul[@id='course_list']/li/div[@class='desc']/h2/a"));
		  WebElement target=null;
		  for(WebElement ws:wes){
			  if(ws.getText().trim().equalsIgnoreCase(course_title)){
				  target=ws;
				  break;
			  }
		  }
		  return target;
	}
	
	public WebElement findhistoryCourse(String course_title){
		 List<WebElement> wes=driver.findElements(By.xpath("//ul[@id='his_course_list']/li/div[@class='desc']/h2/a"));
		  WebElement target=null;
		  for(WebElement ws:wes){
			  if(ws.getText().trim().equalsIgnoreCase(course_title)){
				  target=ws;
				  break;
			  }
		  }
		  return target;
	}
	
	
	public void clickStartlearning(WebElement target){
		target.findElement(By.xpath("./../../div[@class='status']/a")).click();
	}
	
	public void clickRecoverCourse(WebElement target){
		target.findElement(By.xpath("./../../div[@class='status']/a")).click();
	}
	
	
	public WebElement getTitle(WebDriver driver){
		return driver.findElement(By.id("play_title"));
	}
	
	public void clickCleanCourse(WebElement target){
		  Actions action=new Actions(driver);
		  action.moveToElement(target.findElement(By.xpath("./../.."))).perform();
		  Wait.waitMilliSeconds(5000);
		  
		  target.findElement(By.xpath("./../../a")).click();
		  
	}
	
	public WebElement getTip(){
		return driver.findElement(By.xpath("//div[starts-with(@id,'layui-layer')]/div[2]"));
	}
	
	
	public void confirm(){
		 driver.findElement(By.className("layui-layer-btn0")).click();
	}
	public WebElement getSuccessTip(){
		return Wait.explicitlyWait(driver, 10, By.xpath("//div[starts-with(@id,'layui-layer')]/div"));
	}
	
	public void clickHistoryCourse(){
		  driver.findElement(By.xpath(".//*[@id='course_history']/span")).click();
		  Wait.waitMilliSeconds(5000);
	}
	
	
	
	
}
