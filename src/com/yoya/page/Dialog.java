package com.yoya.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.yoya.util.Wait;

public class Dialog {
	
	private WebDriver driver;
	
	public Dialog(WebDriver driver){
		this.driver=driver;
	}
	/**
	 * 获取标题
	 * @return
	 */
	public String getTitle(){
		return driver.findElement(By.xpath("//div[starts-with(@id,'layui-layer') and @type='dialog']/div[@class='layui-layer-title']")).getText();
	}
	
	/**
	 * 获取内容
	 * @return
	 */
	public String getContent(){
		return driver.findElement(By.xpath("//div[starts-with(@id,'layui-layer') and @type='dialog']/div[contains(@class,'layui-layer-content')]")).getText();
	}
	/**
	 * 点击确认按钮
	 */
	public void clickconfirmBtn(){
		driver.findElement(By.className("layui-layer-btn0")).click();
		Wait.waitMilliSeconds(5000);
	}
	
	/**
	 * 点击取消按钮
	 */
	public void clickcancelBtn(){
		driver.findElement(By.className("layui-layer-btn1")).click();
	}
	
	/**
	 * 获取成功提示框
	 * @return
	 */
	public WebElement getSuccessTip(){
		return Wait.explicitlyWait(driver, 10, By.xpath("//div[starts-with(@id,'layui-layer') and @type='dialog']/div"));
	}
	
}
