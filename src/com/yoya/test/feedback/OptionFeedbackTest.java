package com.yoya.test.feedback;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yoya.page.Dialog;
import com.yoya.page.MyYoya;
import com.yoya.page.feedback.OptionFeedback;
import com.yoya.test.login.LoginTest;

/**
 * 帮助下的意见反馈
 * @author Administrator
 *
 */
public class OptionFeedbackTest {
	private WebDriver driver;
	private OptionFeedback feedback;
	
	@BeforeTest
	@Parameters({"web_driver","host_url","user_name","pass_word"})
	public void beforeTest(String web_driver,String host_url,String user_name,String pass_word){
		  LoginTest lg=new LoginTest();
		  driver=lg.login(web_driver, host_url, user_name, pass_word);
		  
		  MyYoya myyoya=new MyYoya(driver);
		  driver=myyoya.enterOptionsfeedback();
		  
		  //进入意见反馈页面
		  feedback=new OptionFeedback(driver);
		  
		  
	}
	
	@AfterTest
	public void afterTest(){
		driver.quit();
	}
	
	@Test(alwaysRun=true)
	@Parameters({"product_type","fb_type","fb_desc","rel_website","contact"})
	public void postAdvice(String product_type,String fb_type,
			String fb_desc,String rel_website,String contact){
		
		//产品类型
		this.feedback.chooseProjectType(product_type);
		
		//反馈分类先不写 
		
		//反馈描述
		this.feedback.inputsuggestDesc(fb_desc);
		
		//相关页面
		
		this.feedback.inputsuggestUrl(rel_website);
		
		//联系方式
		
		this.feedback.inputContact(contact);
		
		//提交
		
		this.feedback.submit();
		
		//成功确认
		Dialog d=new Dialog(driver);
		Assert.assertEquals(d.getContent(), "感谢您对优芽网提出的宝贵建议！"+"\n"+"如经采纳，即可获得5个优豆喔~"+"\n"+"优芽网将不断改进完善，与您共同进步！");
		d.clickconfirmBtn();
	}
	
	
	@Test(alwaysRun=true)
	@Parameters({"product_type","report_reason","rp_desc","rp_website","contact"})
	public void postReport(String product_type,String report_reason,
			String rp_desc,String rp_website,String contact){
		
			this.feedback.enterReport();
				//产品类型
				this.feedback.chooseProjectType(product_type);
				
				//举报原因先不写，默认 
				
				//举报内容
				this.feedback.inputRpDesc(rp_desc);
				
				//相关页面
				
				this.feedback.inputRpUrl(rp_website);
				
				//联系方式
				
				this.feedback.inputRpContact(contact);
				
				//提交
				
				this.feedback.submit();
				
				Alert alert=driver.switchTo().alert();
				Assert.assertEquals(alert.getText(), "感谢您的举报.");
				alert.accept();
				
		
	}
	
	
	
}
