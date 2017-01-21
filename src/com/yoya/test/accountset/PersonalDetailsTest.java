package com.yoya.test.accountset;

import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yoya.page.MyYoya;
import com.yoya.page.accountset.PersonalDetails;
import com.yoya.page.accountset.SecurityCenter;
import com.yoya.test.login.LoginTest;
import com.yoya.util.Wait;
/**
 * 个人资料修改
 * @author Administrator
 *
 */
public class PersonalDetailsTest {
	public WebDriver driver;
	private PersonalDetails pd;
  @Parameters({"web_driver","host_url","user_name","pass_word"})
  @BeforeTest
  public void beforeTest(String web_driver,String host_url,String user_name,String pass_word) {	  
	  LoginTest lg=new LoginTest();
	  driver=lg.login(web_driver, host_url, user_name, pass_word);
  }

  @AfterTest
  public void afterTest() {
	  Wait.waitMilliSeconds(5000);
	  driver.quit();
  }
  
  /**
   * 更新个人基本资料
   * @param imgUrl
   * @param nickName
   * @param userName
   * @param specific_skills
   * @param user_desc
   */
  @Parameters({"img_url","nick_name","real_name","specific_skills","user_desc"})
  @Test(alwaysRun=true)
  public void updateBasicInformation(String imgUrl,String nickName,String userName,String specific_skills,String user_desc){

	  MyYoya myyoya=new MyYoya(driver);
	  driver=myyoya.enterPersonalDetailsPage();
	  this.pd=new PersonalDetails(driver);
	  this.pd.uploadHeadImg(imgUrl);
	  //昵称
	  this.pd.inputNickName(nickName);
	  //名字
	  this.pd.inputRealName(userName);
	  
	  //年
	  this.pd.inputYear();
	  
	  //月
	  this.pd.inputMonth();
	  
	  //日
	  this.pd.inputDay();
	  Wait.waitMilliSeconds(5000);
	  AssertJUnit.assertEquals("处女座", this.pd.getConstellation().getText());
	  
	  //选择性别
	  this.pd.inputSex();

	  //省份
	  this.pd.inputProvince();

	  //城市
	  this.pd.inputCity();

	  //技能
	  this.pd.inputSkills(specific_skills);
	  
	  
	  //描述
	  this.pd.inputDescription(user_desc);
	  
	  //提交修改
	  this.pd.submit();
	  //等待提示成功
	  Wait.waitMilliSeconds(5000);
	  //成功确认
	  this.pd.modifySuccessedConfirm("信息修改成功");
  }
  

  /**
   * 基本资料页面的修改密码
   * @param oldp
   * @param newp
   */
  @Parameters({"old_p","new_p"})
  @Test(alwaysRun=true)
  public void modifyPassword(String oldp,String newp){
	  //进入系列修改页
	  MyYoya myyoya=new MyYoya(driver);
	  driver=myyoya.enterPersonalDetailsPage();
	  this.pd=new PersonalDetails(driver);
	  //修改密码
	  driver=this.pd.entermodifyPassword();

	  SecurityCenter sc=new SecurityCenter(driver);
	  
	  //输入原密码
	  sc.inputOldpassword(oldp);
	  //输入新密码
	  sc.inputNewpassword(newp);
	  
	  //确认新密码
	  sc.confirmNewpassword(newp);
	  
	  //提交修改
	  sc.submitPasswordModify();
	  
	  driver=sc.modifySuccessedConfirm("密码修改成功！");
  }
  
  
 
  
  
}
