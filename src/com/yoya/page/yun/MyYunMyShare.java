package com.yoya.page.yun;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.yoya.util.MyYunAssist;
import com.yoya.util.Wait;

public class MyYunMyShare {
	private WebDriver driver;
	
	public MyYunMyShare(WebDriver driver){
		this.driver=driver;
	}
	
	  /**
	   * 进入我的分享页面
	   */

	  public void enterMyShare(){
		  driver.findElement(By.linkText("我的分享")).click();
		  Wait.waitMilliSeconds(5000);
		  Wait.waitMilliSeconds(5000);
		  MyYunAssist.locateIframe(driver, "main");
	  }
	  
	  
	  public void chooseShare(String movie_name){
		  Actions action=new Actions(driver);
		  action.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']"+"//tr[@data-name='"+movie_name+"']"+
		  "/td[@class='t-checkbox']/div/input[@type='checkbox']"))).perform();
		  driver.findElement(By.xpath("//table[@id='file_list']"+"//tr[@data-name='"+movie_name+"']"+
		  "/td[@class='t-checkbox']/div/input[@type='checkbox']")).click();
		  Wait.waitMilliSeconds(5000);
	  }
	  
	  public WebElement findShare(String new_movie_name){
		  return driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='" + new_movie_name
					+ "']" + "/td[@class='t-filename']" + "/a/span[@class='name']"));
	  }
	  
	  public void movetoShare(String old_movie_name){
		  Actions action = new Actions(driver);
			action.moveToElement(
					driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='" + old_movie_name + "']")))
					.perform();

			Actions action1 = new Actions(driver);
			action1.moveToElement(driver.findElement(By.xpath(
					"//table[@id='file_list']//tr[@data-name='" + old_movie_name + "']" + "/td//span[@class='btn-val']")))
					.perform();

			Wait.waitMilliSeconds(5000);
	  }
	  
	  public void moveToRename(String old_movie_name){
		  Actions action2 = new Actions(driver);
			action2.moveToElement(driver.findElement(By.xpath(
					"//table[@id='file_list']//tr[@data-name='" + old_movie_name + "']" + "/td//a[@class='rename']")))
					.perform();
	  }
	  
	  public void moveToMark(String movie_name){
		  Actions action2=new Actions(driver);
		  action2.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+movie_name+"']"+"/td//a[@class='mark']"))).perform();
		  Wait.waitMilliSeconds(5000);
	  }
	  
	  
	  
	  
	  
	  public void clickRenameBtn(String old_movie_name){
		  driver.findElement(By
					.xpath("//table[@id='file_list']//tr[@data-name='" + old_movie_name + "']" + "/td//a[@class='rename']"))
					.click();
	  }
	  
	  public void clickMarkBtn(String movie_name){
		  driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+movie_name+"']"+"/td//a[@class='mark']")).click();
		  MyYunAssist.locateIframe(driver, "layui-layer-iframe");
	  }
	  
	  public void inputMarkName(String mark_name){
		  WebElement markinput=driver.findElement(By.id("tags_2_tag"));
		  markinput.clear();
		  Wait.waitMilliSeconds(5000);
		  markinput.sendKeys(mark_name);
	  }
	  
	  public void inputName(String old_movie_name,String new_movie_name){
		  WebElement we = driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='" + old_movie_name + "']"
					+ "/td[@class='t-filename']/a/input"));
			Wait.waitMilliSeconds(5000);
			we.clear();
			we.sendKeys(new_movie_name);
			Wait.waitMilliSeconds(5000);
	  }
	  
	  public void renameConfirm(String old_movie_name){
		  driver.findElement(By
					.xpath("//table[@id='file_list']//tr[@data-name='" + old_movie_name + "']" + "//button[@class='sure']"))
					.click();
			Wait.waitMilliSeconds(5000);
	  }
	  
	  public void clickCopyButton(){
		  driver.findElement(By.id("ZeroClipboardMovie_1")).click();
	  }
	  
	  public void clickDeleteShareBtn(){
		  driver.findElement(By.id("btn-delete-share")).click();
		  Wait.waitMilliSeconds(5000);
	  }
	  
	  public WebElement getTip(){
		  return driver.findElement(By.xpath("//div[starts-with(@id,'layui-layer')]/div"));
	  }
	  public WebElement getContentTip(){
		  return driver.findElement(By.xpath("//div[starts-with(@id,'layui-layer')]/div[2]"));
	  }
	  
	  public WebElement getTitleTip(){
		  return driver.findElement(By.xpath("//div[starts-with(@id,'layui-layer')]/div[@class='layui-layer-title']"));
	  }
	  
	  
	  public WebElement getMarkTip(){
		  return driver.findElement(By.xpath("//*[@id=\"xubox_layer1\"]/div[1]/div/span[2]"));
	  }
	  
	  public void confirm(){
		  driver.findElement(By.className("layui-layer-btn0")).click();
	  }
	  
	  public void saveButton(){
		  driver.findElement(By.id("saveLabel")).click();
	  }
	  
	  public void movetoShareCode(String movie_name){
		  
		  Actions action2=new Actions(driver);
		  action2.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+movie_name+"']"+"/td//a[@class='showcode']"))).perform();
		  Wait.waitMilliSeconds(5000);
	  }
	  public void clickShareCodeBtn(String movie_name){
		  driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+movie_name+"']"+"/td//a[@class='showcode']")).click();
			
	  }
	
}
