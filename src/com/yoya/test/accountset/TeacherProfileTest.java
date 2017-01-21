package com.yoya.test.accountset;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yoya.page.Dialog;
import com.yoya.page.MyYoya;
import com.yoya.page.accountset.PersonalDetails;
import com.yoya.page.accountset.TeacherProfile;
import com.yoya.test.login.LoginTest;
import com.yoya.util.Wait;

public class TeacherProfileTest {

	
	private WebDriver driver;
	private TeacherProfile teacher;
  @BeforeTest
  @Parameters({"web_driver","host_url","user_name","pass_word"})
  public void beforeTest(String web_driver,String host_url,String user_name,String pass_word) {
	  LoginTest lg=new LoginTest();
	  driver=lg.login(web_driver, host_url, user_name, pass_word);
	  MyYoya myyoya=new MyYoya(driver);
	  driver=myyoya.enterPersonalDetailsPage();
	  
	  PersonalDetails pd=new PersonalDetails(driver);
	  driver=pd.enterTeacherProfile();
	 
  }

  @AfterTest
  public void afterTest() {
	  Wait.waitMilliSeconds(5000);
	  driver.quit();
  }

  
  
  @Test(alwaysRun=true)
  @Parameters({"head_img_url","real_name","user_id","tel_no","tea_desc","tea_company","province","city","course_type","tea_position","pos_cert_url","capa_url"})
  public void improveTeacherInfo(String head_img_url,String real_name,String user_id,String tel_no,String tea_desc,String
		  tea_company,String province,String city,String course_type,String tea_position,String pos_cert_url,
		  String capa_url){
	  this.teacher=new TeacherProfile(driver);
	  
	  this.teacher.enterInformation();
	  
	  //上传头像
	  this.teacher.uploadHeadImage(head_img_url);
	  
	  //上传成功对话框
	  Dialog d=new Dialog(driver);
	  Assert.assertEquals(d.getContent(), "上传成功！");
	  d.clickconfirmBtn();
	  
	  
	  
	  //输入真实姓名
	  this.teacher.inputRealName(real_name);
	  
	  
	  //输入身份证号
	  this.teacher.inputUserID(user_id);
	  
	  //判断手机号是否已绑定
	  this.teacher.validateTel(tel_no);
	  
	  
	  //简介
	  this.teacher.inputDescription(tea_desc);
	  
	  
	  //单位
	  this.teacher.inputUnit(tea_company);
	  
	 
	  
	  //省份和市暂时不写
	  
	  //授课类别
	  this.teacher.inputCourseType(course_type);
	  
	  
	  //职位
	  this.teacher.inputPosition(tea_position);
	  
	  
	  
	  //职位证明
	 this.teacher.uploadPosCert(pos_cert_url);
	  
	  //资质证明
	 this.teacher.uploadTeaCert(capa_url);
	  
	 //提交修改
	 this.teacher.clicksubmitModifyBtn();
	  
	  Alert alert=driver.switchTo().alert();
	  Assert.assertEquals(alert.getText(), "恭喜您已成为优芽网教师！");
	  alert.accept();
	  
  }
  
  
}
