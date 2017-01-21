package com.yoya.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yoya.page.MyCourse;
import com.yoya.page.MyYoya;
import com.yoya.test.login.LoginTest;
import com.yoya.util.DriverExchangeAssist;
import com.yoya.util.Wait;

public class MyCourseTest {

	public WebDriver driver;
	private MyCourse mycourse;
	  @BeforeTest
	  @Parameters({"web_driver","host_url","user_name","pass_word"})
	  public void beforeTest(String web_driver,String host_url,String user_name,String pass_word) {
		  LoginTest lg=new LoginTest();
		  driver=lg.login(web_driver, host_url, user_name, pass_word);
		  MyYoya yoya=new MyYoya(driver);
		  driver=yoya.enterMyCourse();
		  
		  this.mycourse=new MyCourse(driver);
		  
	  }

	  @AfterTest
	  public void afterTest() {
		  Wait.waitMilliSeconds(5000);
		  driver.quit();
	  }
  
  


	 
	  
  /**
   * 开始学习
   */
  @Test(alwaysRun=true)
  @Parameters({"course_title"})
  public void startLearning(String course_title) {
	  
	  //定位课程标题
	  WebElement target=this.mycourse.findCourse(course_title);

	  //定位开始学习按钮
	  this.mycourse.clickStartlearning(target);
	  
	  //等待新的页面
	  Wait.waitMilliSeconds(5000);
	  //切到到学习页面
	  driver=DriverExchangeAssist.exchangeDriverUsePartialUrl(driver, "http://course-nin.test.yoya.com/doView?action=gnl");
	  //查找标题
	  Assert.assertEquals(this.mycourse.getTitle(driver).getText(),course_title);
	  
  }
  
  /**
   *  
   *  清理课程并恢复课程
   *  
   */
  
  @Test(alwaysRun=true)
  @Parameters({"course_title"})
  public void cleanAndRecoverCourse(String course_title){
	  
	//定位课程标题
	  WebElement target=this.mycourse.findCourse(course_title);
	  
	//定位清理课程按钮
	  this.mycourse.clickCleanCourse(target);
	  
	  //删除确认框
	  Assert.assertEquals(this.mycourse.getTip().getText(), "是否确定清理该课程？");
	 
	  //确认
	 this.mycourse.confirm();
	  
	 //获取成功提示框
	  WebElement we=this.mycourse.getSuccessTip();
	  Assert.assertEquals(we.getText(), "课程清理成功");
	  
	  Wait.waitMilliSeconds(5000);
	  
	  //历史课程
	  this.mycourse.clickHistoryCourse();
	  
	  //查找历史课程
	  WebElement history_course=this.mycourse.findhistoryCourse(course_title);
		//点击恢复按钮	 
	  this.mycourse.clickRecoverCourse(history_course);
	  //Wait.waitMilliSeconds(5000);
	  //断言是否删除
	  Assert.assertEquals(this.mycourse.getTip().getText(), "是否确定恢复该课程学习？");
	  //确认
	  this.mycourse.confirm();
	  
	  WebElement we1=this.mycourse.getSuccessTip();
	  Assert.assertEquals(we1.getText(), "恢复课程成功");
	  
	  
  }
  
  

}
