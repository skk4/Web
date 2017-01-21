package com.yoya.test.designer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yoya.page.MyYoya;
import com.yoya.page.designer.MyOrder;
import com.yoya.test.login.LoginTest;
import com.yoya.util.Wait;

public class MyOrderTest {

	private WebDriver driver;
	private MyOrder myorder;
  @BeforeTest
  @Parameters({"web_driver","host_url","user_name","pass_word"})
  public void beforeTest(String web_driver,String host_url,String user_name,String pass_word) {
	  LoginTest lg=new LoginTest();
	  driver=lg.login(web_driver, host_url, user_name, pass_word);
	  
	  MyYoya myyoya=new MyYoya(driver);
	  driver=myyoya.enterMyDesiger();
	  
	  this.myorder=new MyOrder(driver);
	  
  }

  @AfterTest
  public void afterTest() {
	  //driver.quit();
  }
  
  
  @Test(alwaysRun=true)
  @Parameters({"order_type","order_id","product_name","pay_money","order_status"})
  public void orderDetails(String order_type,String order_id,String product_name,
		  String pay_money,String order_status) {
	  
	  this.myorder.enterOrderType(order_type);
	 
	  WebElement order=this.myorder.findOrder(order_id);
	  
	  this.myorder.clickViewBtn(order);
	  
	  Wait.waitMilliSeconds(5000);
	  
	  Assert.assertEquals(this.myorder.getProductName(),product_name );
	  Assert.assertEquals(this.myorder.getPayMoney(), pay_money);
	  Assert.assertEquals(this.myorder.getStatus(), order_status);
		
  }
  
  

}
