package com.yoya.page.yun;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.yoya.util.MyYunAssist;
import com.yoya.util.Wait;

public class MyYunMyWork {
	private WebDriver driver;
	
	public MyYunMyWork(WebDriver driver){
		this.driver=driver;
	}
	
	  /**
	   * 进入赛事作品
	   */
	  
	  public void enterWork(){
		  driver.findElement(By.linkText("赛事作品")).click();
		  Wait.waitMilliSeconds(5000);
		  WebElement iframe=MyYunAssist.locateIframe(driver, "main");
	  }
	  
	  
	  public void movetoWorkAndPlay(String work_name){
		  Wait.waitMilliSeconds(5000);
		  Actions action=new Actions(driver);
		  action.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+work_name+"']"))).perform();

		  Actions doubleclick=new Actions(driver);
		  doubleclick.doubleClick(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+work_name+"']"))).perform();
	  }
	  
	  public void movetoWorkAndMark(String work_name){
		  Actions action=new Actions(driver);
		  action.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+work_name+"']"))).perform();

		  Actions action2=new Actions(driver);
		  action2.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+work_name+"']"+"/td//a[@class='mark']"))).perform();
		  Wait.waitMilliSeconds(5000);
		  
		  driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+work_name+"']"+"/td//a[@class='mark']")).click();
		  
		  WebElement iframe1=driver.findElement(By.xpath("//iframe[starts-with(@id,'layui-layer-iframe')]"));
		  driver.switchTo().frame(iframe1);	
	  }
	  
	  public void movetoWorkAndEdit(String work_name){
		  Actions action=new Actions(driver);
		  action.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+work_name+"']"))).perform();
		  
		  Actions action1=new Actions(driver);
		  action1.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+work_name+"']"+"/td//a[@class='edit']"))).perform();
		  
		  Wait.waitMilliSeconds(5000);
		  driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+work_name+"']"+"/td//a[@class='edit']")).click();
		
	  }
	  
	  public void inputMark(String mark_name){
		  WebElement markinput=driver.findElement(By.id("tags_2_tag"));
		  markinput.clear();
		  Wait.waitMilliSeconds(5000);
		  markinput.sendKeys(mark_name);
	  }
	  public void saveBtn(){
		  driver.findElement(By.id("saveLabel")).click();
	  }
	  
	
	  public WebElement getTip(){
		  return driver.findElement(By.xpath("//*[@id=\"xubox_layer1\"]/div[1]/div/span[2]"));
	  }
}
