package com.yoya.page.feedback;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyFeedback {
	private WebDriver driver;
	
	public MyFeedback(WebDriver driver){
		this.driver=driver;
	}
	/**
	 * 查找我的反馈下的反馈内容
	 * @param fb_desc
	 * @return
	 */
	public WebElement findFeedback(String fb_desc){
		WebElement feedback=driver.findElement(By.xpath("//div[@id='list_table']//div[@class='title' and contains(text(),'"+fb_desc+"')]"));
		return feedback;
	}
	
	
	public String getStatus(WebElement feedback){
		return feedback.findElement(By.xpath("./../../td[@class='td-state']/span")).getText();
	}
	
	public void clickDeleteBtn(WebElement feedback){
		feedback.findElement(By.xpath("./../../td[@class='td-ope']/a")).click();
	}
	
}
