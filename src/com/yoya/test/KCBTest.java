package com.yoya.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yoya.page.KCB;
import com.yoya.page.MyYoya;
import com.yoya.test.login.LoginTest;
import com.yoya.util.Wait;

public class KCBTest {
  public WebDriver driver;
  private KCB kcb;
 
  
  @BeforeTest
  @Parameters({"web_driver","host_url","user_name","pass_word"})
  public void beforeTest(String web_driver,String host_url,String user_name,String pass_word) {
	  	LoginTest lg=new LoginTest();
	  	driver=lg.login(web_driver, host_url, user_name, pass_word);
	  	MyYoya yoya=new MyYoya(driver);
	  	driver=yoya.enterKCB();
	  	
	  	this.kcb=new KCB(driver);
  }

  @AfterTest
  public void afterTest() {
	  Wait.waitMilliSeconds(5000);
	  driver.quit();
  }

  

  
  
  /**
   * 新建课程班
   * @param class_name
   */
  
  @Test(alwaysRun=true)
  @Parameters({"class_name"})
  public void createClassRoom(String class_name) {

		// 点击创建的课程班
		this.kcb.clickCreateClass();

		// 新建
		this.kcb.clickNewButton();

		// 输入班级名称
		this.kcb.inputClassname(class_name);

		// 确认
		this.kcb.confirm();
		//这个休眠等待不能删
		Wait.waitMilliSeconds(5000);
		// 验证是否新建成功
		String className = this.kcb.findClass(driver, class_name).getText();
		AssertJUnit.assertEquals(class_name, className);
  
  }
  
  /**
   * 加入课程班,自己加入自己创建的班级
   *
   * @param login_username
   */
  @Parameters({"class_name"})
  @Test(alwaysRun=true)
  public void joinClassRoom(String class_name){
	 
	  String classcode=this.kcb.queryClassCode(driver, class_name);
	  
	  this.kcb.clickJoinClass();
	  
	  this.kcb.clickJoinButton();
	  
	 this.kcb.inputinviteCode(classcode);
	  
	  this.kcb.clickSearch();
	  
	  //加入班级按钮
	  this.kcb.clickJoinClassButton();
	  
	  WebElement we=this.kcb.getTip();
	  String result=we.getText();
	  Assert.assertEquals(result, "提交中，请稍候..");
	  
	  WebElement we1=this.kcb.getTip(); 
	  String result1=we1.getText();
	  Assert.assertEquals(result1, "加入课程班成功");
	 
  }
  
  
  
  /**
   * 加入别人创建的班级
   */
  @Test(alwaysRun=true)
  @Parameters({"class_code"})
  public void joinClassRoomFromClassCode(String class_code){

	  this.kcb.clickJoinClass();
	  
	  this.kcb.clickJoinButton();
	  
	  
	 this.kcb.clickSearch();
	 
	 this.kcb.inputinviteCode(class_code);
	  
	  
	 this.kcb.clickSearch();
	  //加入班级按钮
	  this.kcb.clickJoinClassButton();

	  WebElement we1=this.kcb.getTip(); 
	  String result1=we1.getText();
	  Assert.assertEquals(result1, "加入课程班成功");

  }
  
  
  

  
  /**
   * 从班级中移除人员
   */
  @Test(alwaysRun=true)
  @Parameters({"class_name","login_name"})
  public void removeRememberFromClass(String class_name,String login_name){
	  //进入我创建的班级tab页
	  this.kcb.clickCreateClass();
	  
	  //查找班级
	  WebElement target=this.kcb.findClass(driver, class_name);
	  
	  //移除学生
	  this.kcb.clickRemoveRemember(target, login_name);
	  
	  this.kcb.confirm();
	  //获取成功提示框之前不要加等待
	  WebElement we=this.kcb.getTip();
	  String result=we.getText();
	  Assert.assertEquals(result, "移出成功");
  }
  

  
  
  /**
   * 删除课程班
   */
  @Test(alwaysRun=true)
  @Parameters("class_name")
  public void removeClassRoom(String class_name){
	  
	  this.kcb.clickCreateClass();	  
	  this.kcb.clickDeleteClass(class_name);
	  this.kcb.confirm();  
  }
  
  
  /**
   * 编辑课程名称
   * @param new_class_name
   */
  @Test(alwaysRun=true)
  @Parameters(value={"class_name","new_class_name"})
  public void editClass(String class_name,String new_class_name){
		this.kcb.clickCreateClass();
		this.kcb.clickeditClass(class_name);
		this.kcb.inputClassname(new_class_name);
		this.kcb.confirm();
	   
  }
  
}
