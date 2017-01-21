package com.yoya.test.designer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yoya.page.MyYoya;
import com.yoya.page.designer.BalanceManagement;
import com.yoya.test.login.LoginTest;
import com.yoya.util.Wait;

public class BalanceManagementTest {
	private WebDriver driver;
	private BalanceManagement bm;

  @BeforeTest
  @Parameters({"web_driver","host_url","user_name","pass_word"})
  public void beforeTest(String web_driver,String host_url,String user_name,String pass_word) {
	  LoginTest lg=new LoginTest();
	  driver=lg.login(web_driver, host_url, user_name, pass_word);
	  
	  MyYoya myyoya=new MyYoya(driver);
	  driver=myyoya.enterMyDesiger();
	  
	  this.bm=new BalanceManagement(driver);
	  this.bm.enterBalanceManagement();
  }

  @AfterTest
  public void afterTest() {
	 // driver.quit();
  }
  
  
  
  @Test(alwaysRun=true)
  @Parameters({"bill_id","bill_status","user_code",
	  "bill_money","deduct","tax","settle_money"})
  public void viewBalance(String bill_id,String bill_status,String user_code,String bill_money,
		  String deduct,String tax,String settle_money){
	  
	  WebElement bill=this.bm.findBill(bill_id);
	  
	  this.bm.clickViewBtn(bill);	  
	  
	  Assert.assertEquals(this.bm.getStatus(), bill_status);
	  	  
	  Assert.assertEquals(this.bm.getUsercode(), user_code);
	  
	  Assert.assertEquals(this.bm.getTotalaccount(), bill_money);

	  Assert.assertEquals(this.bm.getSplitamount(), deduct);

	  Assert.assertEquals(this.bm.getTaxAccount(), tax);

	  Assert.assertEquals(this.bm.getIncomingaccount(), settle_money);  
  }
  
  @Test(alwaysRun=true)
  @Parameters({"bill_id"})
  public void exportBill(String bill_id){
	  WebElement bill=this.bm.findBill(bill_id);
	  this.bm.clickExportBtn(bill);
	  //等待下载
	  Wait.waitMilliSeconds(5000);
	  
  }
  
  
  

}
