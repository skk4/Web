package com.yoya.page.designer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.yoya.util.Wait;

public class MyOrder {
	private WebDriver driver;
	
	
	public MyOrder(WebDriver driver){
		this.driver=driver;
	}
	/**
	 * 已付款
	 */
	public void enterpayedTab(){
		WebElement tab=driver.findElement(By.xpath("//div[@class='page-tabs']/a[@bill_status='1']"));
		tab.click();
		Wait.waitMilliSeconds(5000);
	}
	/**
	 * 待付款
	 */
	public void enterpayingTab(){
		WebElement tab=driver.findElement(By.xpath("//div[@class='page-tabs']/a[@bill_status='0']"));
		tab.click();
		Wait.waitMilliSeconds(5000);
	}
	
	/**
	 * 已关闭
	 */
	public void enterClosedTab(){
		WebElement tab=driver.findElement(By.xpath("//div[@class='page-tabs']/a[@bill_status='2']"));
		tab.click();
		Wait.waitMilliSeconds(5000);
	}
	
	/**
	 * 全部
	 */
	public void enterAllTab(){
		WebElement tab=driver.findElement(By.xpath("//div[@class='page-tabs']/a[1]"));
		tab.click();
		Wait.waitMilliSeconds(5000);
	}
	
	/**
	 * 查找订单
	 */
	public WebElement findOrder(String order_id){
		WebElement orderid=driver.findElement(By.xpath("//div[@id='list_data']//th[@class='num' and contains(text(),'"+order_id+"')]"));
		  
		  WebElement order=orderid.findElement(By.xpath("./ancestor::table[@class='order']"));
		  return order;
	}
	
	public void clickViewBtn(WebElement order){
		order.findElement(By.xpath("./tbody//td[@class='opts']//a[@class='view']")).click();
	}
	
	
	public String getProductName(){
		return driver.findElement(By.xpath("//div[@id='order_detail']//table[@class='order']//td[@class='detail']//div[@class='desc']//a")).getText().trim();
	}
	
	public String getPayMoney(){
		return driver.findElement(By.xpath("//div[@id='order_detail']//table[@class='order']//td[@class='price']//div")).getText().trim();
	}
	
	public String getStatus(){
		return driver.findElement(By.xpath("//div[@id='order_detail']//table[@class='order']//td[@class='status']//div")).getText().trim();
	}
	
	public void enterOrderType(String order_type){
		if(order_type.equalsIgnoreCase("已关闭")){
			  this.enterClosedTab();
		  }else if(order_type.equalsIgnoreCase("待付款")){
			  this.enterpayingTab();
		  }else if(order_type.equalsIgnoreCase("已付款")){
			this.enterpayedTab();
		  }else{ //默认全部订单
			  this.enterAllTab();
		  }
	}
	
	
}
