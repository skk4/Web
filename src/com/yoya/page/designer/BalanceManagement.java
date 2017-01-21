package com.yoya.page.designer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.yoya.util.Wait;

public class BalanceManagement {
	private WebDriver driver;
	
	public BalanceManagement(WebDriver driver){
		this.driver=driver;
	}
	
	
	/**
	 * 进入结算管理
	 */
	public void enterBalanceManagement(){
		driver.findElement(By.cssSelector("i.icon.icon-settlement")).click();
		  Wait.waitMilliSeconds(5000);
	}
	
	public WebElement findBill(String bill_id){
		  WebElement id=driver.findElement(By.xpath("//table[@class='table']//tbody/tr/td[text()='"+bill_id+"']"));
		  WebElement bill=id.findElement(By.xpath("./ancestor::tbody"));
		  return bill;
	}
	
	public void clickViewBtn(WebElement bill){
		bill.findElement(By.xpath("./tr/td[@class='opt-1']/a[@name='viewLink']")).click();
		  Wait.waitMilliSeconds(5000);
	}
	
	public void clickExportBtn(WebElement bill){
		bill.findElement(By.xpath("./tr/td[@class='opt-1']/a[@name='exportLink']")).click();
		  Wait.waitMilliSeconds(5000);
	}
	
	
	public String  getStatus(){
		return driver.findElement(By.id("bill_status")).getText();
	}
	
	public String  getUsercode(){
		return driver.findElement(By.id("acct_name")).getText();
	}
	
	public String  getTotalaccount(){
		return driver.findElement(By.id("total_amount")).getText();
	}
	
	public String getSplitamount(){
		return driver.findElement(By.id("split_amount")).getText();
	}
	
	public String getTaxAccount(){
		return driver.findElement(By.id("tax_amount")).getText();
	}
	
	public String getIncomingaccount(){
		return driver.findElement(By.id("incoming_amount")).getText();
	}
	
	
}
