package com.yoya.page;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.AssertJUnit;

import com.yoya.util.Wait;

public class AssertAlertTip {
	
	private WebDriver driver;
	
	public AssertAlertTip(WebDriver driver){
		this.driver=driver;
	}
	
	/**
	 * 一个alert框
	 * @param expected
	 * @return
	 */
	public WebDriver assertAlertTip(String expected){
		Alert alert=driver.switchTo().alert();
	  Wait.waitMilliSeconds(5000);
	  AssertJUnit.assertEquals(expected, alert.getText());
	  alert.accept();
	  Wait.waitMilliSeconds(3000);
	
	  driver.switchTo().defaultContent();
	  return driver;
	}
	
	/**
	 * 页面连续出现2个alert提示框的，可以使用该方法
	 * @param expected1
	 * @param expected2
	 * @return
	 */
	public WebDriver assertTwoAlertTip(String expected1,String expected2){
		 Alert alert=driver.switchTo().alert();
		  Assert.assertEquals(alert.getText(),expected1 );
		  alert.accept();
		  Wait.waitMilliSeconds(3000);
		  Assert.assertEquals(alert.getText(), expected2);
		  alert.accept();
		  driver.switchTo().defaultContent();
	  return driver;
	}
	
	

}
