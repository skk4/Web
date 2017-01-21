package com.yoya.test.message;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yoya.page.MyYoya;
import com.yoya.page.message.Notice;
import com.yoya.test.login.LoginTest;
import com.yoya.util.ElementExistOrNot;
import com.yoya.util.Wait;

public class NoticeTest {

	public WebDriver driver;
	private Notice notice;
	@BeforeTest
	  @Parameters({"web_driver","host_url","user_name","pass_word"})
	  public void beforeTest(String web_driver,String host_url,String user_name,String pass_word)  { 
		  
		  LoginTest lg=new LoginTest();
		  driver=lg.login(web_driver, host_url, user_name, pass_word);
		  MyYoya myyoya=new MyYoya(driver);
		  myyoya.enterMessage();
		  
		  this.notice=new Notice(driver);
		  this.notice.eneterNotice();
	  }

	  @AfterTest
	  public void afterTest() {
		  Wait.waitMilliSeconds(5000);
		  driver.quit();
	  }
	  
	  @Test(alwaysRun=true)
	  public void readNotice(){
		  WebElement notice=this.notice.findUnreadMessage();
		  
		  String message_id=notice.getAttribute("bill_m_notice_id");
		  
		  this.notice.clickReadBtn(notice);
		  
		  notice=this.notice.findMessageFromID(message_id);
		  Assert.assertEquals(notice.getAttribute("class"), "");
		  Assert.assertFalse(ElementExistOrNot.elementExist(notice, By.xpath(".//div[@class='opts']/a[contains(@class,'btn_read')]")));
		  
	  }
	  
	  
}
