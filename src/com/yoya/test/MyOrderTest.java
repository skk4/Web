package com.yoya.test;
import static com.yoya.util.ElementExistOrNot.elementExist;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yoya.page.MyOrder;
import com.yoya.page.MyYoya;
import com.yoya.test.login.LoginTest;
import com.yoya.util.Wait;
/**
 * 我的订单
 * @author Administrator
 */
public class MyOrderTest {

	public WebDriver driver;
	private MyOrder myorder;
	  @BeforeTest
	  @Parameters({"web_driver","host_url","user_name","pass_word"})
	  public void beforeTest(String web_driver,String host_url,String user_name,String pass_word) {
		  LoginTest lg=new LoginTest();
		  driver=lg.login(web_driver, host_url, user_name, pass_word);
		  MyYoya yoya=new MyYoya(driver);
		  driver=yoya.enterMyOrder();
		  myorder=new MyOrder(driver);
		  
	  }

  @AfterTest
  public void afterTest() {
	  Wait.waitMilliSeconds(5000);
	  driver.quit();
  }


  /**
   * 查看订单详情
   * @param order_id
   */
  @Test(alwaysRun=true)
  @Parameters({"order_id"})
  public void orderDetails(String order_id) {
	  
	  //查看详情
	  this.myorder.clickorderDetails(order_id);
	  Assert.assertTrue(elementExist(driver,By.id("order_detail")));
	  Wait.implicitlyWait(driver, 10);
  }
  
  /**
   * 取消订单
   * @param order_id
   */
  @Test(alwaysRun=true)
  @Parameters({"order_id"})
  public void cancelOrder(String order_id){
	  //点击取消订单
	  this.myorder.clickcancelOrder(order_id);
	  
	  //验证确认框
	  
	  Assert.assertEquals(this.myorder.getTip().getText(), "确定要取消该订单吗？");
	  
	  //确认
	  this.myorder.confirm();
	  
	  //Wait.waitMilliSeconds(5000);
	  
	  Assert.assertEquals(this.myorder.getTip().getText(), "订单取消成功");
	  this.myorder.confirm();
	  
	  //Wait.waitMilliSeconds(5000);
	
	  //验证订单是否已关闭
	  Assert.assertEquals(this.myorder.findOrderStatus(order_id).getText(), "已关闭");
	    
  }
  

  
  
  /**
   * 立即付款
   */
  @Test(alwaysRun=true)
  @Parameters({"order_id"})
  public void payOrder(String order_id){
	  //支付按钮
	  
	 this.myorder.clickpayOrder(order_id);
	
	 //切换到支付页面
	  driver=this.myorder.switchToPayPage(driver);
	  //验证
	  Assert.assertEquals(this.myorder.getSuccessTip().getText(), "订单提交成功，请尽快付款");
	  Assert.assertEquals(this.myorder.findOrderNo().getText(), order_id);
  }
  
  
  /**
   * 删除订单
   */
  @Test(alwaysRun=true)
  @Parameters({"order_id"})
  public void deleteOrder(String order_id){
	 
	  //删除订单
	  this.myorder.clickdeleteOrder(order_id);
	
	  //是否删除
	  Assert.assertEquals(this.myorder.getTip().getText(), "确定要删除该订单吗？");
	  this.myorder.confirm();
	  
	  //删除订单成功
	  Assert.assertEquals(this.myorder.getTip().getText(), "订单删除成功");
	  this.myorder.confirm();
	  
  }
}
