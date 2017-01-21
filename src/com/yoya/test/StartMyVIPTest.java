package com.yoya.test;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yoya.page.MyYoya;
import com.yoya.page.StartMyVIP;
import com.yoya.test.login.LoginTest;
import com.yoya.util.Wait;
/**
 * 我的优芽-开通VIP
 * @author Administrator
 *
 */
public class StartMyVIPTest {
  public WebDriver driver;
  
  private StartMyVIP myvip;

  @BeforeTest
  @Parameters({"web_driver","host_url","user_name","pass_word"})
  public void beforeTest(String web_driver,String host_url,String user_name,String pass_word) {
	  LoginTest lg=new LoginTest();
	  driver=lg.login(web_driver, host_url, user_name, pass_word);
	  MyYoya myyoya=new MyYoya(driver);
	  driver=myyoya.enterMyVIP();
	  this.myvip=new StartMyVIP(driver);
  }
  
  
  @AfterTest
  public void afterTest() {
	  Wait.waitMilliSeconds(5000);
	  driver.quit();
  }


  
  /**
   * 顶部开通VIP进行升级，参数包含vip类型，时间，支付方式
   * @param vip_url
   * @param vip_type
   * @param vip_time
   * @param pay_type
   */
  @Test(alwaysRun=true)
  @Parameters({"vip_type","vip_time","pay_type"})
  public void startVIP(String vip_type,String vip_time,String pay_type) {
	  
	  this.myvip.clickStartVip();
	  this.myvip.purchaseProcess(vip_type,vip_time,pay_type);	  
	  
  }
  /**
   * 从我的VIP进行升级，参数包含vip类型，时间，支付方式
   * @param vip_type
   * @param vip_time
   * @param pay_type
   */
  @Test(alwaysRun=true)
  @Parameters({"vip_type","vip_time","pay_type"})
  public void startMyVIP(String vip_type,String vip_time,String pay_type){
	  //马上升级
	  this.myvip.clickNowVip();
	  //购买定制过程
	  this.myvip.purchaseProcess(vip_type,vip_time,pay_type);
	  
  }
  

  
  
  
  
  
}
