package com.yoya.test.accountset;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yoya.page.MyYoya;
import com.yoya.page.accountset.PersonalDetails;
import com.yoya.page.accountset.SecurityCenter;
import com.yoya.test.login.LoginTest;
import com.yoya.util.Wait;

public class SecurityCenterTest {
	
	private WebDriver driver;
	private SecurityCenter securitycenter;
	
  @BeforeTest
  @Parameters({"web_driver","host_url","user_name","pass_word"})
  public void beforeTest(String web_driver,String host_url,String user_name,String pass_word) {
	  LoginTest lg=new LoginTest();
	  driver=lg.login(web_driver, host_url, user_name, pass_word);
	  MyYoya myyoya=new MyYoya(driver);
	  driver=myyoya.enterPersonalDetailsPage();
	  
	  PersonalDetails pd=new PersonalDetails(driver);
	  driver=pd.enterSecurityCenter();
	  
  }

  @AfterTest
  public void afterTest() {
	  Wait.waitMilliSeconds(5000);
	  driver.quit();
  }
  
  
  @Test(alwaysRun=true)
  @Parameters({"email_url"})
  public void nullEmailUrlBinding(String email_url) {
	  this.securitycenter=new SecurityCenter(driver);
	  //进入邮箱绑定
	  this.securitycenter.enterEmailBinding();
	  //输入空邮箱号
	  this.securitycenter.inputEmailAddr(email_url);
	  
	  //确认
	  this.securitycenter.clickConfirmBtn();
	  //验证提示
	  Assert.assertEquals(this.securitycenter.getBadEmailNote(), "邮件地址不能为空");
	  
	  
  }
  
  
  @Test(alwaysRun=true)
  @Parameters({"email_url"})
  public void wrongEmailUrlBinding(String email_url) {
	  this.securitycenter=new SecurityCenter(driver);
	  //进入邮箱绑定
	  this.securitycenter.enterEmailBinding();
	  //输入空邮箱号
	  this.securitycenter.inputEmailAddr(email_url);
	  //确认
	  this.securitycenter.clickConfirmBtn();
	  //验证提示
	  Assert.assertEquals(this.securitycenter.getBadEmailNote(), "邮箱地址格式错误");
	  
	  
  }
  
  @Test(alwaysRun=true)
  @Parameters({"email_url"})
  public void emailUrlDuplicateBinding(String email_url){
	  this.securitycenter=new SecurityCenter(driver);
	  //进入邮箱绑定
	  this.securitycenter.enterEmailBinding();
	  //输入空邮箱号
	  this.securitycenter.inputEmailAddr(email_url);
	  //确认
	  this.securitycenter.clickConfirmBtn();
	  //验证提示
	  Assert.assertEquals(this.securitycenter.getDuplicateEmail(), "您的邮箱已经存在，注意一个邮箱只能绑定一个账号!");
  }
  
  @Test(alwaysRun=true)
  @Parameters({"email_url"})
  public void emailUrlBinding(String email_url){
	  this.securitycenter=new SecurityCenter(driver);
	  //进入邮箱绑定
	  this.securitycenter.enterEmailBinding();
	  //输入空邮箱号
	  this.securitycenter.inputEmailAddr(email_url);
	  //确认
	  this.securitycenter.clickConfirmBtn();
	  //验证提示
	  Assert.assertEquals(this.securitycenter.getSuccessTip(),  "邮箱绑定信息已经发送到您的邮箱，请尽快登录邮箱确认！");
  }
  
  
  
  /**
   * 安全中心-密码修改
   */
  @Parameters({"old_p","new_p"})
  @Test(alwaysRun=true)
  public void modifyPasswordFromSecurity(String oldp,String newp){
	  
	  
	  this.securitycenter=new SecurityCenter(driver);
	  
	  //密码修改
	  this.securitycenter.changePassword();
	  

	  this.securitycenter.inputOldpassword(oldp);
	  
	  this.securitycenter.inputNewpassword(newp);
	  
	  
	  this.securitycenter.confirmNewpassword(newp);
	  
	  this.securitycenter.submitPasswordModify();
	  
	  driver=this.securitycenter.modifySuccessedConfirm("密码修改成功！");
  }
  
  
  /*
   * 手机绑定
   */
  @Test(alwaysRun=true)
  @Parameters({"mobile_number"})
  public void mobileBinding(String mobile_number){
	  this.securitycenter=new SecurityCenter(driver);
	  this.securitycenter.enterMobileBinding();
	  
	  this.securitycenter.inputMobileNumber(mobile_number);
	  
	  this.securitycenter.clickSendCode();
	  
	  
	  String mobile_code=this.securitycenter.getMobileCode(mobile_number);
	  this.securitycenter.inputCode(mobile_code);
	  
	  this.securitycenter.clickConfirmBtn();
	  
	  //验证绑定成功
	  Assert.assertEquals(this.securitycenter.getSuccessTip(), "恭喜，手机绑定成功！");
	  
  }
  
  
  /**
   * 实名认证
   */
  @Test(alwaysRun=true)
  @Parameters({"user_realname","card_type","card_number","frontpic_url","backpic_url"})
  public void realNameAuthentication(String user_realname,String card_type,String card_number,String frontpic_url,String backpic_url){
	  this.securitycenter=new SecurityCenter(driver);
	  
	  this.securitycenter.enterAuthentication();
	  
	  this.securitycenter.inputUsername(user_realname);
	  this.securitycenter.chooseCardType(card_type);
	  this.securitycenter.inputCardNumber(card_number);
	  
	  this.securitycenter.uploadFrontPic(frontpic_url);
	  this.securitycenter.uploadBackpic(backpic_url);
	  
	  this.securitycenter.clickVerifyBtn();
	  
	  //断言
	  Assert.assertEquals(this.securitycenter.getName(), "李*");
	  
	  Assert.assertEquals(this.securitycenter.getCardType(),"身份证");
	  
	  Assert.assertEquals(this.securitycenter.getCardNumber(), "350322**********51");
	  
	  Assert.assertEquals(this.securitycenter.getStatus1(),"认证审核中" );
	  Assert.assertEquals(this.securitycenter.getStatus2(), "（认证审核需要2-3个工作日，请您耐心等待！）");
	  
	  
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  String currentTime=formatter.format(new Date());
	  //只验证到分钟数，不验证秒数
	  Assert.assertEquals(this.securitycenter.getAuthTime().substring(0, 16), currentTime.substring(0, 16));
	 
	  
	  
  }
  
  
  
  
  

}
