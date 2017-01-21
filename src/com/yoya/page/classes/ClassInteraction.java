package com.yoya.page.classes;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.yoya.util.DriverExchangeAssist;
import com.yoya.util.LocateElement;
import com.yoya.util.LocateIframe;
import com.yoya.util.Wait;

public class ClassInteraction {
	private WebDriver driver;
	
	public ClassInteraction(WebDriver driver){
		this.driver=driver;
	}
	
	
	public void clickHistoryClass(){
		driver.findElement(By.id("lesson_list_history")).click();
		  Wait.waitMilliSeconds(5000);
	}
	
	public void clickInterctionClassBtn(String class_name){
		WebElement target=LocateElement.locateElementUseText(driver, "//ul[@id='class_list']/li/div/a", class_name);
		  Wait.waitMilliSeconds(5000);
		  target.findElement(By.xpath("./../../div[@class='content']/ul/li[2]/a/div")).click();
		  Wait.waitMilliSeconds(5000);
	}
	
	public WebDriver createClass(){
		driver.findElement(By.id("add_lesson")).click();
		  Wait.waitMilliSeconds(5000);
		  driver=LocateIframe.locateIframe(driver, "layui-layer-iframe");
		  return driver;
	}
	public WebDriver  save(){
		 driver.findElement(By.id("addbtn")).click();
		  driver.switchTo().defaultContent();
		  return driver;
	}
	public void confirm(){
		driver.findElement(By.className("layui-layer-btn0")).click();
		  Wait.waitMilliSeconds(5000);
	}
	
	public WebElement findClass(){
		return driver.findElement(By.id("sel_entry_lesson"));
	}
	
	public WebElement moveToClass(String class_date){
		WebElement date=LocateElement.locateElementUseText(driver, "//tbody[@id='lesson_list_view']//div[@class='date']", class_date);
		  WebElement aa=date.findElement(By.xpath("./../.."));
		  Actions action=new Actions(driver);
		  action.moveToElement(aa).perform();
		  aa.click();
		  Wait.waitMilliSeconds(5000);
		  return aa;
	}
	
	public void clickDeleteBtn(WebElement aa){
		 aa.findElement(By.xpath("./td[@class='action']/div[2]/div[@class='sub-action']/a[2]")).click();
		  Wait.waitMilliSeconds(5000);
	}
	public void clickEditBtn(WebElement aa){
		 aa.findElement(By.xpath("./td[@class='action']/div[2]/div[@class='sub-action']/a[1]")).click();
		  Wait.waitMilliSeconds(5000);
	  
		  driver=LocateIframe.locateIframe(driver, "layui-layer-iframe");
	}
	public void inputClassDate(String new_class_date){
		JavascriptExecutor removeAttribute = (JavascriptExecutor)driver;
		removeAttribute.executeScript("var setDate=document.getElementById(\"open_dates\");setDate.removeAttribute('readonly');") ;
		  
		driver.findElement(By.id("open_dates")).clear();
		driver.findElement(By.id("open_dates")).sendKeys(new_class_date);
		Wait.waitMilliSeconds(5000);
	}
	public WebDriver enterClass(String class_date){
		  WebElement date=LocateElement.locateElementUseText(driver, "//tbody[@id='lesson_list_view']//div[@class='date']", class_date);
		  
		  date.findElement(By.xpath("./../../td[@class='action']/div[2]/a[text()='进入课堂']")).click();
		  
		  Wait.waitMilliSeconds(5000);
		  //切换到新的页面
		  driver=DriverExchangeAssist.exchangeDriverUsePartialUrl(driver, "http://ke-nin.test.yoya.com/doView?action=classroom/v_t_classroom&start=to_classroom");
		  Wait.waitMilliSeconds(5000);
		  return driver;
	}
	
	public WebDriver clickSignBtn(){
		driver.findElement(By.xpath("//ul[@class='top-nav-boxes']/li[@data-task_type='qd']")).click();
		  Wait.waitMilliSeconds(5000);
		  driver=LocateIframe.locateIframe(driver, "layui-layer-iframe");
		  return driver;
	}
	
	public WebDriver clickQuestionBtn(){
		driver.findElement(By.xpath("//ul[@class='top-nav-boxes']/li[@data-task_type='tw']")).click();
		  Wait.waitMilliSeconds(5000);
		  return driver;
	}
	public void clickSignoutBtn(){
		driver.findElement(By.cssSelector("i.icon.icon-alarm")).click();
		  Wait.waitMilliSeconds(5000);
	}
	
	public void startInteraction(){
		driver.findElement(By.xpath("//ul[@id='task_type_ul']/li/a[@data-task_type='01']")).click();
		  Wait.waitMilliSeconds(5000);
		  driver.findElement(By.xpath(".//*[@id='tbody']/tr/td[5]/input")).click();
		  Wait.waitMilliSeconds(5000);
	}
	
	public WebElement getTip(){
		return  driver.findElement(By.xpath("//div[starts-with(@id,'layui-layer') and @type='dialog']/div[2]"));
	}
	
	public WebElement getStatus(){
		return  driver.findElement(By.xpath("//tbody[@id='tbody']/tr[1]/td[contains(@class,'hd-state')]/span"));
	}
	
	
	public WebDriver getQuestionFromTK(String tk_name,String chapter_name,String section_name,String question_title){

		  return driver;
	}
	
	
}
