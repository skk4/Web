package com.yoya.page.designer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.yoya.util.Wait;

public class CashierRecord {
	private WebDriver driver;
	
	public CashierRecord(WebDriver driver){
		this.driver=driver;
	}
	
	/**
	 * 进入提现记录
	 */
	public void enterGetCashier(){
		driver.findElement(By.cssSelector("i.icon.icon-card")).click();
		  Wait.waitMilliSeconds(5000);
	}
	
	public void clickApplyBtn(){
		WebElement applybtn=driver.findElement(By.cssSelector("a.btn.btn-11.cash_apply"));
		  if(applybtn.isEnabled()){
			  applybtn.click();
		  }else{
			  WebElement cashbtn=driver.findElement(By.cssSelector("i.icon.icon-withdraw"));
			  Actions action=new Actions(driver);
			  action.moveToElement(cashbtn).perform();
			  Wait.waitMilliSeconds(3000);
			  
			  cashbtn.findElement(By.xpath("./../span")).click();
		  }
		  Wait.waitMilliSeconds(5000);
	}
	
	public void clickCancelBtn(){
		driver.findElement(By.id("cancel_btn")).click();
	}
	public void clickConfirmBtn(){
		driver.findElement(By.id("ok_btn")).click();
	}
	
	public String getAvaibleCash(){
		return driver.findElement(By.id("cash_span")).getText();
	}
	
	public void inputCashAmount(String cash_amount){
		 driver.findElement(By.id("cash_amount")).sendKeys(cash_amount);
	}
	public void inputName(String name){
		driver.findElement(By.id("user_name")).sendKeys(name);
	}
	
	public void chooseAccountType(String account_type){
		WebElement acctype=driver.findElement(By.id("incoming_acct_type"));
		  acctype.findElement(By.xpath("./option[text()='"+account_type+"']")).click();
	}
	
	public void inputIncomingAccount(String incoming_account){
		driver.findElement(By.id("incoming_acct")).sendKeys(incoming_account);
	}
	
	public void inputLoginPwd(String login_pwd){
		driver.findElement(By.id("login_pwd")).sendKeys(login_pwd);
	}
	
}
