package com.yoya.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.yoya.database.ResultSetHelper;
import com.yoya.util.Wait;

public class MyOrganization {
	
	private WebDriver driver;
	
	public MyOrganization(WebDriver driver){
		this.driver=driver;
		
	}
	
	
	public void enterMyJoinedOrg(String org_id){
		driver.findElement(By.xpath("//div[@id='"+org_id+"']/a")).click();
	}
	
	public WebElement findOrganization(){
		return driver.findElement(By.xpath("//div[@id='my_org']/ul/li[1]//div[@class='name']"));
	}
	
	public void enterJoinedOrg(){
		driver.findElement(By.id("join_org")).click();
		  Wait.waitMilliSeconds(5000);
	}
	
	public void inputInvitecode(String invite_code){
		 driver.findElement(By.id("org_Invitation_code")).sendKeys(invite_code);
	}
	public void inputOrgName(String org_name){
		driver.findElement(By.id("org_name")).sendKeys(org_name);
	}
	public void inputUserName(String your_name){
		 WebElement name=driver.findElement(By.id("user_name"));
		  name.clear();
		  name.sendKeys(your_name);
	}
	
	public void inputMobile(String mobile_number){
		WebElement mobile=driver.findElement(By.id("mobile"));
		  mobile.clear();
		  mobile.sendKeys(mobile_number);
	}
	public void clickGetcodeBtn(){
		driver.findElement(By.id("getIdentifyCode")).click();
		  //等待验证码
		  Wait.waitMilliSeconds(30000);
		  
	}
	
	public void inputCode(String mobile_number){
		String code=ResultSetHelper.smsMessage(mobile_number);
		  driver.findElement(By.id("identifyCode")).sendKeys(code.substring(4, 10));
	}
	
	public void clickApplyBtn(){
		driver.findElement(By.id("apply")).click();
	}
	
}
