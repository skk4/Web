package com.yoya.page;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.yoya.util.LocateElement;
import com.yoya.util.Wait;

public class MyOrder {
	private WebDriver driver;
	
	public MyOrder(WebDriver driver){
		this.driver=driver;
	}
	
	
	public void clickorderDetails(String order_id){
		  driver.findElement(By.xpath("//div[@id='list_data']/table//a[@bill_type='view' and @bill_order_no='"+order_id+"']")).click();		
	}
	
	public void clickcancelOrder(String order_id){
		 driver.findElement(By.xpath("//div[@id='list_data']/table//a[@bill_type='close' and @bill_order_no='"+order_id+"']")).click();
		 // Wait.waitMilliSeconds(5000);
	}
	public void clickpayOrder(String order_id){
		  driver.findElement(By.xpath("//div[@id='list_data']/table//a[@bill_type='pay' and @bill_order_no='"+order_id+"']")).click();
	}
	
	public void clickdeleteOrder(String order_id){
		driver.findElement(By.xpath("//div[@id='list_data']/table//a[@bill_type='del' and @bill_order_no='"+order_id+"']")).click();
	}
	
	
	public WebElement getTip(){
		return driver.findElement(By.xpath("//div[starts-with(@id,'layui-layer')]/div[2]"));
	}
	
	
	public void confirm(){
		driver.findElement(By.className("layui-layer-btn0")).click();
	}
	
	public WebElement findOrderNo(){
		return driver.findElement(By.id("order_no"));
	}
	
	public WebElement findOrderStatus(String order_id){
		WebElement order=LocateElement.locateElementUsePartialText(driver, "//div[@id='list_data']/table//th[@class='num']", order_id);
		return order.findElement(By.xpath("./../../../tbody//td[@class='status']/div"));
	}
	
	public WebDriver switchToPayPage(WebDriver driver){
		String current=driver.getWindowHandle();
		  
		  Set<String> ws=driver.getWindowHandles();
		  
		  for(String s :ws){
			  if(!s.equalsIgnoreCase(current)){
				  driver.switchTo().window(s);
				  break;
			  }
		  }
		  return driver;
	}
	
	public WebElement getSuccessTip(){
		WebElement tip=driver.findElement(By.className("tip-1"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return tip;
	}
	
	/**
	 * 进入我要开票
	 */
	public WebDriver enterWantReceipt(){
		WebElement btn=driver.findElement(By.xpath("//div[@id='invoiceApplyBtn']/a"));
		Actions action=new Actions(driver);
		action.moveToElement(btn).click().perform();
		Wait.waitMilliSeconds(5000);
		return driver;
	}
	
	
	

}
