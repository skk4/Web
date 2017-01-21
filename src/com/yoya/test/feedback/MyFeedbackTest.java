package com.yoya.test.feedback;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yoya.page.Dialog;
import com.yoya.page.MyYoya;
import com.yoya.page.feedback.MyFeedback;
import com.yoya.test.login.LoginTest;
import com.yoya.util.Wait;

/**
 * 我的反馈
 * @author Administrator
 *
 */
public class MyFeedbackTest {
	private WebDriver driver;
	
	private MyFeedback myfb;
	
	
	@BeforeTest
	@Parameters({"web_driver","host_url","user_name","pass_word"})
	public void beforeTest(String web_driver,String host_url,String user_name,String pass_word){
		  LoginTest lg=new LoginTest();
		  driver=lg.login(web_driver, host_url, user_name, pass_word);
		  
		  MyYoya myyoya=new MyYoya(driver);
		  driver=myyoya.enterMyFeedback();
		  
		  //进入意见反馈页面
		  this.myfb=new MyFeedback(driver);	  
	}
	
	@AfterTest
	public void afterTest(){
		driver.quit();
	}
	
	@Test(alwaysRun=true)
	@Parameters({"fb_desc","fb_status"})
	public void assertStatus(String fb_desc,String fb_status){
		
		WebElement feedback=this.myfb.findFeedback(fb_desc);
		Assert.assertEquals(this.myfb.getStatus(feedback), fb_status);
		
	}
	
	@Test(alwaysRun=true)
	@Parameters({"fb_desc"})
	public void deleteFeedback(String fb_desc){
		
		WebElement feedback=this.myfb.findFeedback(fb_desc);
		this.myfb.clickDeleteBtn(feedback);
		
		Wait.waitMilliSeconds(3000);
		
		Dialog d=new Dialog(driver);
		Assert.assertEquals(d.getContent(), "确定要删除该条反馈？");
		d.clickconfirmBtn();
		
		Assert.assertEquals(d.getContent(), "删除成功！");
		d.clickconfirmBtn();
	}
	
	
	
}
