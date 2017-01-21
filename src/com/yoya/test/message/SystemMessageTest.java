package com.yoya.test.message;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yoya.page.Dialog;
import com.yoya.page.MyYoya;
import com.yoya.page.message.SystemMessage;
import com.yoya.test.login.LoginTest;
import com.yoya.util.ElementExistOrNot;
import com.yoya.util.Wait;

public class SystemMessageTest {
	private WebDriver driver;
	private SystemMessage systemmessage;
	@BeforeTest
	  @Parameters({"web_driver","host_url","user_name","pass_word"})
	  public void beforeTest(String web_driver,String host_url,String user_name,String pass_word)  { 
		  
		  LoginTest lg=new LoginTest();
		  driver=lg.login(web_driver, host_url, user_name, pass_word);
		  MyYoya myyoya=new MyYoya(driver);
		  
		  myyoya.enterMessage();
		  
		  this.systemmessage=new SystemMessage(driver);
	  }

	  @AfterTest
	  public void afterTest() {
		  Wait.waitMilliSeconds(5000);
		  //driver.quit();
	  }
	
	  /**
	   * 设置已读
	   */
	  @Test(alwaysRun=true)
	  public void setReadedStatus() {
		  
		  WebElement message=this.systemmessage.findUnreadMessage();
		  String message_id=message.getAttribute("bill_m_sys_id");
		  //System.out.println("message_id="+message_id);
		  this.systemmessage.clickReadBtn(message);
		  
		  message=this.systemmessage.findMessageFromID(message_id);
		  Assert.assertEquals(message.getAttribute("class"), "");
		  Assert.assertFalse(ElementExistOrNot.elementExist(message, By.xpath(".//div[@class='opts']/a[contains(@class,'btn_read')]")));
		  
		  
	  }
	  
	  /**
	   * 删除
	   */
	  @Test(alwaysRun=true)
	  public void deleteMessage(){
		  WebElement message=this.systemmessage.findfirstMessage();
		  String message_id=message.getAttribute("bill_m_sys_id");
		  
		  this.systemmessage.clickDeleteBtn(message);
		  
		  Dialog d=new Dialog(driver);
		  Assert.assertEquals(d.getContent(), "确定要删除该条消息？");
		  d.clickconfirmBtn();
		  
		  Assert.assertFalse(ElementExistOrNot.elementExist(driver,By.id(message_id)));
			
	  }
	  
}
