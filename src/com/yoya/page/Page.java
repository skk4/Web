package com.yoya.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.yoya.util.LocateElement;
import com.yoya.util.Wait;

public class Page {
	
	private WebDriver driver;
	private WebElement page;
	
	public Page(WebDriver driver){
		this.driver=driver;
		this.page=driver.findElement(By.xpath("//div[starts-with(@id,'layui-layer') and @type='page']"));
	}
	
	public WebElement getPageElement(){
		return this.page;
	}
	
	
	public void chooseGroup(String group_name){
		
		page.findElement(By.xpath(".//select[@id='dir_id']")).click();
		Wait.waitMilliSeconds(5000);
		WebElement group=LocateElement.locateElementUseTextUnderElement(page, "//select[@id='dir_id']/option", group_name);
		Actions action=new Actions(driver);
		action.moveToElement(group).click().perform();
	}
	
	public void clickSaveBtn(){
		Wait.waitMilliSeconds(5000);
		page.findElement(By.id("savebtn")).click();
		
	}
	
}
