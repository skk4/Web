package com.yoya.test.designer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yoya.page.Dialog;
import com.yoya.page.MyYoya;
import com.yoya.page.designer.SalesTemplate;
import com.yoya.test.login.LoginTest;
import com.yoya.util.Wait;

public class SalesTemplateTest {
	
	private WebDriver driver;
	private SalesTemplate salesTemp;
	
	@BeforeTest
	@Parameters({"web_driver","host_url","user_name","pass_word"})
	public void beforeTest(String web_driver,String host_url,String user_name,String pass_word){
		  LoginTest lg=new LoginTest();
		  driver=lg.login(web_driver, host_url, user_name, pass_word);
		  
		  MyYoya myyoya=new MyYoya(driver);
		  driver=myyoya.enterMyDesiger();
		  
		  this.salesTemp=new SalesTemplate(driver);
		  this.salesTemp.enterSalesTemplate();
	}
	
	@AfterTest
	public void afterTest(){
		driver.quit();
	}
	
	/**
	 * 下架
	 * @param template_name
	 */
	@Test(alwaysRun=true)
	@Parameters({"template_name"})
	public void offlineProduct(String template_name){
		
		WebElement template=this.salesTemp.findTemplate(template_name);
		
		driver=this.salesTemp.clickOfflineBtn(template);
		
		
		Dialog dialog=new Dialog(driver);
		
		Assert.assertEquals(dialog.getContent(), "确定要下架？");
		
		dialog.clickconfirmBtn();
		
		Wait.waitMilliSeconds(5000);
		
		driver.navigate().refresh();
		
		Wait.waitMilliSeconds(5000);
		
		Assert.assertEquals(this.salesTemp.getStatus(template_name), "下架");
		
		
	}
	
	/**
	 * 上架
	 * @param template_name
	 */
	@Test(alwaysRun=true)
	@Parameters({"template_name"})
	public void onlineProduct(String template_name){
		WebElement template=this.salesTemp.findTemplate(template_name);
		
		driver=this.salesTemp.clickOnlineBtn(template);
		
		Dialog dialog=new Dialog(driver);
		
		Assert.assertEquals(dialog.getContent(), "确定要上架？");
		
		dialog.clickconfirmBtn();
		
		Wait.waitMilliSeconds(5000);
		
		driver.navigate().refresh();
		
		Wait.waitMilliSeconds(5000);
		
		Assert.assertEquals(this.salesTemp.getStatus(template_name), "上架");
		
	}
	
	/**
	 * 查看
	 * @param template_name
	 * @param t_status
	 * @param t_type
	 * @param t_price
	 */
	@Test(alwaysRun=true)
	@Parameters({"template_name","t_status","t_type","t_price"})
	public void viewTemplate(String template_name,String t_status,String t_type,String t_price){
		WebElement template=this.salesTemp.findTemplate(template_name);
		this.salesTemp.clickViewBtn(template);
				
		Assert.assertEquals(this.salesTemp.getTempStatus(), t_status);
		Assert.assertEquals(this.salesTemp.getTempName(), template_name);
		Assert.assertEquals(this.salesTemp.getTempType(), t_type);
		Assert.assertEquals(this.salesTemp.getTempPrice(), t_price);
		
	}
	
	/**
	 * 编辑模板
	 * @param template_name
	 * @param t_price
	 */
	@Test(alwaysRun=true)
	@Parameters({"template_name","t_price"})
	public void editTemplate(String template_name,String t_price){
		WebElement template=this.salesTemp.findTemplate(template_name);
		
		this.salesTemp.clickEditBtn(template);
		
		this.salesTemp.inputPrice(t_price);
		
		this.salesTemp.clickSaveBtn();
		Wait.waitMilliSeconds(5000);
		driver.navigate().refresh();
		Wait.waitMilliSeconds(5000);
		Assert.assertEquals(this.salesTemp.getPrice(), t_price);
	}
	
	
	
}
