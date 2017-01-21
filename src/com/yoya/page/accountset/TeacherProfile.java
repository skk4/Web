package com.yoya.page.accountset;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.yoya.util.Wait;

public class TeacherProfile {
	
	private WebDriver driver;
	
	public TeacherProfile(WebDriver driver){
		this.driver=driver;
	}

	
	/**
	 * 进入完善
	 */
	public void enterInformation(){
		driver.findElement(By.id("go")).click();
		Wait.waitMilliSeconds(5000);
	}
	
	public void uploadHeadImage(String head_img_url){
		Actions action=new Actions(driver);
		  action.moveToElement(driver.findElement(By.id("headUpload"))).perform();
		  Wait.waitMilliSeconds(3000);
		  
		  driver.findElement(By.xpath("//div[@id='headUpload']/div/span")).click();
		  Wait.waitMilliSeconds(5000);
		  
		  driver.findElement(By.className("webuploader-element-invisible")).sendKeys(head_img_url);
		  Wait.waitMilliSeconds(5000);
		  driver.findElement(By.cssSelector("a.btn.btn-cropSave")).click();
		  Wait.waitMilliSeconds(5000);
	}
	
	public void inputRealName(String real_name){
		WebElement realname=driver.findElement(By.id("u_user_name"));
		  realname.clear();
		  realname.sendKeys(real_name);
	}
	
	public void inputUserID(String user_id){
		WebElement id=driver.findElement(By.id("u_idcard"));
		  id.clear();
		  id.sendKeys(user_id);
	}
	
	public void validateTel(String tel_no){
		 WebElement tel=driver.findElement(By.id("u_mobile"));
		  if(tel.isDisplayed()){ //无法使用，说明已经绑定电话号码
			  System.out.println("已经绑定手机号...");
		  }else{
			  tel.clear();
			  tel.sendKeys(tel_no);
			  driver.findElement(By.id("validate_mobile")).click();
			  Wait.waitMilliSeconds(5000);
		  }
	}
	
	
	public void inputDescription(String tea_desc){
		WebElement desc=driver.findElement(By.id("u_user_desc"));
		  desc.clear();
		  desc.sendKeys(tea_desc);
	}
	
	public void inputUnit(String tea_company){
		 WebElement unit=driver.findElement(By.id("u_work_unit"));
		  unit.clear();
		  unit.sendKeys(tea_company);
	}
	
	
	public void inputCourseType(String course_type){
		WebElement type=driver.findElement(By.id("u_teach_type"));
		  type.clear();
		  type.sendKeys(course_type);
	}
	
	public void inputPosition(String tea_position){
		WebElement pos=driver.findElement(By.id("u_post"));
		  pos.clear();
		  pos.sendKeys(tea_position);
	}
	
	public void uploadPosCert(String pos_cert_url){
		 WebElement poscert=driver.findElement(By.id("u_post_certify"));
		  poscert.sendKeys(pos_cert_url);
	}
	
	public void uploadTeaCert(String capa_url){
		 WebElement tc=driver.findElement(By.id("u_teacher_certify"));
		  tc.sendKeys(capa_url);
	}
	
	public void clicksubmitModifyBtn(){
		 WebElement checkPro=driver.findElement(By.id("check_protocol"));
		  if(checkPro.isSelected()){
			  //提交修改
			  driver.findElement(By.id("user_submit")).click();
			  Wait.waitMilliSeconds(5000);
		  }
	}
	
	
	
}
