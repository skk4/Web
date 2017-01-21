package com.yoya.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yoya.page.MyOrganization;
import com.yoya.page.MyYoya;
import com.yoya.test.login.LoginTest;
import com.yoya.util.Wait;

/**
 * 我的机构
 * @author Administrator
 *
 */
public class MyOrganizationTest {
	public WebDriver driver;
	private MyOrganization myorg;
	
  @BeforeTest
  @Parameters({"web_driver","host_url","user_name","pass_word"})
  public void beforeTest(String web_driver,String host_url,String user_name,String pass_word) {
	  LoginTest lg=new LoginTest();
	  driver=lg.login(web_driver, host_url, user_name, pass_word);
	  MyYoya yoya=new MyYoya(driver);
	  driver=yoya.enterMyOrg();
	  myorg=new MyOrganization(driver);
  }

  @AfterTest
  public void afterTest() {
	  Wait.waitMilliSeconds(5000);
	  driver.quit();
  }
  /**
   * 进入我的机构
   */
  @Test(alwaysRun=false)
  public void enterMyOrg() {
	  driver.findElement(By.linkText("我的机构")).click();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }
  
  @Test(alwaysRun=true)
  @Parameters({"org_name","org_id"})
  public void enterMyJoinedOrg(String org_name,String org_id){
	  
	  //找到传入参数值的机构
	  Assert.assertEquals(this.myorg.findOrganization().getText(), org_name);
	  //进入结构
	  this.myorg.enterMyJoinedOrg(org_id);
	  Assert.assertEquals(driver.getTitle(), org_name);  
  
  }
  
  @Test(alwaysRun=true)
  @Parameters({"invite_code","org_name","your_name","mobile_number","verify_code"})
  public void joinOrganization(String invite_code,String org_name,String your_name,String mobile_number,String verify_code){
	  
	  //点击加入
	  this.myorg.enterJoinedOrg();
	  
	  //输入邀请码
	 this.myorg.inputInvitecode(invite_code);
	  
	  //输入机构名称
	  this.myorg.inputOrgName(org_name);
	  
	  //输入用户名
	 this.myorg.inputUserName(your_name);
	  
	  //输入手机号
	  
	  this.myorg.inputMobile(mobile_number);
	  
	  //获取验证码
	  this.myorg.clickGetcodeBtn();
	  
	  //输入验证码
	  this.myorg.inputCode(mobile_number);
	  //提交申请
	  this.myorg.clickApplyBtn();
  }
		  
  
  

}
