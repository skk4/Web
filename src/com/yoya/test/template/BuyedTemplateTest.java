package com.yoya.test.template;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yoya.page.MyYoya;
import com.yoya.page.template.BuyedTemplate;
import com.yoya.test.login.LoginTest;

public class BuyedTemplateTest {
	private WebDriver driver;
	private BuyedTemplate bt;
	@BeforeTest
	@Parameters({"web_driver","host_url","user_name","pass_word"})
	public void beforeTest(String web_driver,String host_url,String user_name,String pass_word){
		  LoginTest lg=new LoginTest();
		  driver=lg.login(web_driver, host_url, user_name, pass_word);
		  
		  MyYoya myyoya=new MyYoya(driver);
		  driver=myyoya.enterBuyedTemplate();
		  
		  this.bt=new BuyedTemplate(driver);
	}
	
	@AfterTest
	public void afterTest(){
		//driver.quit();
	}

  @Test(alwaysRun=true)
  @Parameters({"template_name"})
  public void previewTemp(String template_name) {
	  
	  WebElement template=this.bt.findTemplate(template_name);
	  
	  this.bt.clickPreviewBtn(template);

  }
  
  
  @Test(alwaysRun=true)
  @Parameters({"template_name"})
  public void createTemp(String template_name) {
	  
	  WebElement template=this.bt.findTemplate(template_name);
	  

  }
  
  
  
}
