package com.yoya.page.login;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.yoya.database.ResultSetHelper;
import com.yoya.util.PropertyConfigure;
import com.yoya.util.SpyMemcacheClient;
import com.yoya.util.Wait;

public class RegisterPage {
	private WebDriver driver;
	
	public RegisterPage(WebDriver driver){
		this.driver=driver;
	}
	
	public void clickRegesterIcon(){
		driver.findElement(By.id("_yoya_reg")).click();
		Wait.waitMilliSeconds(5000);
	}
	
	public String getCookieValue(String cookie_name){
		Cookie cookie=driver.manage().getCookieNamed(cookie_name);
		return cookie.getValue();
	}
	
	
	
	public void inputUsercode(String user_code){
		WebElement usercode=driver.findElement(By.xpath("//form[@id='_pop_register_form']//input[@id='_pop_user_code']"));
		usercode.clear();
		usercode.sendKeys(user_code);
	}
	
	public void inputPassword(String pass_word){
		WebElement password=driver.findElement(By.xpath("//form[@id='_pop_register_form']//input[@id='_pop_password']"));
		password.clear();
		password.sendKeys(pass_word);
	}
	
	/**
	 * 输入绑定的邮箱或者手机号
	 * @param contact
	 */
	public void inputEmailOrMobile(String contact){
		WebElement e=driver.findElement(By.xpath("//form[@id='_pop_register_form']//input[@id='_pop_user_name']"));
		e.clear();
		e.sendKeys(contact);
		driver.findElement(By.cssSelector("label.icon.icon-form-contact")).click();
		Wait.waitMilliSeconds(5000);
	}
	
	/**
	 * 输入图片验证码
	 * @param valid_code
	 */
	public void inputValidcode(){
		Properties p=PropertyConfigure.configure("./properties/memcache.properties");
		String valid_code="";
		String cookie_name=this.getCookieValue("_SESSIONID_COOKIE_NAME");
		String mem_key=p.getProperty("AK")+"_"+cookie_name+"_"+"CHECKCODE";
		//SpyMemcacheClient memcache=new SpyMemcacheClient("192.168.20.61:11211","64d2fb4a5b4a11e4","netinnetCom2015");
		
		SpyMemcacheClient memcache=new SpyMemcacheClient(p.getProperty("host"),p.getProperty("username"),p.getProperty("password"));
		System.out.println("mem_key:"+mem_key);
		valid_code=(String)memcache.get(mem_key);
		System.out.println("valid_code:"+valid_code);
		driver.findElement(By.xpath("//form[@id='_pop_register_form']//input[@id='_pop_valid_code']")).sendKeys(valid_code);
	}
	
	/**
	 * 输入错误图片验证码
	 * @param mobile_number
	 * @return
	 */
	public void inputValidcode(String wr_valid_code){
		driver.findElement(By.xpath("//form[@id='_pop_register_form']//input[@id='_pop_valid_code']")).sendKeys(wr_valid_code);
	}
	
	
	public String clickSendcodeBtn(String mobile_number){
		driver.findElement(By.id("_pop_check_mobile")).click();
		Wait.waitMilliSeconds(30000);
		//获取验证码
		String validcode=ResultSetHelper.smsMessage(mobile_number);
		return validcode.substring(4, 10);
	}

	public void inputMobilecode(String mobile_code){
		WebElement mobile=driver.findElement(By.xpath("//form[@id='_pop_register_form']//input[@id='_pop_mobile_code']"));
		mobile.clear();
		mobile.sendKeys(mobile_code);
	}
	
	
	
	public void clickRegisterBtn(){
		driver.findElement(By.id("_pop_register_btn")).click();
		Wait.waitMilliSeconds(5000);
	}
	
	public String getRegisterSuccTip1(){
		return driver.findElement(By.xpath("//div[@id='msg_body_reg']/p[1]")).getText();
	}
	
	public String getRegisterSuccTip2(){
		return driver.findElement(By.xpath("//div[@id='msg_body_reg']/p[2]")).getText();

	}
	
	public String getRegisterSuccTip3(){
		return driver.findElement(By.xpath("//div[@id='msg_body_reg']/p[@class='email']/a")).getText();
	}
	
	
	public String getNullusernameTip(){
		return driver.findElement(By.xpath("//input[@id='_pop_user_code']/../div[@class='help-tip']/p")).getText();
	}
	
	public String getNullpwdTip(){
		return driver.findElement(By.xpath("//input[@id='_pop_password']/../div[@class='help-tip']/p")).getText();
	}
	
	public String getNullcontactTip(){
		return driver.findElement(By.xpath("//input[@id='_pop_user_name']/../div[@class='help-tip']/p")).getText();
	}
	
	public String getNullvalidcodeTip(){
		return driver.findElement(By.xpath("//input[@id='_pop_valid_code']/../div[@class='help-tip']/p")).getText();
	}
	
	public String getNullMobilecodeTip(){
		return driver.findElement(By.xpath("//form[@id='_pop_register_form']/div[@id='_pop_error_msg']")).getText();
	}
	
}
