package com.yoya.page.designer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.yoya.util.LocateElement;
import com.yoya.util.Wait;

public class SalesTemplate {
	private WebDriver driver;
	
	public SalesTemplate( WebDriver driver){
		this.driver=driver;
	}
	
	
	public void enterSalesTemplate(){
		driver.findElement(By.cssSelector("i.icon.icon-copi")).click();
		  Wait.waitMilliSeconds(5000);
	}
	
	
	public WebElement findTemplate(String template_name){
		WebElement tName=LocateElement.locateElementUseText(driver, "//div[@id='rederEl']/table[@class='order']/tbody/tr/td[@class='detail']//a[@class='viewm']", template_name);
		Actions action=new Actions(driver);
		action.moveToElement(tName);
		return tName;
	}
	
	public WebDriver clickOfflineBtn(WebElement t){
		WebElement detail=t.findElement(By.xpath("./ancestor::td[@class='detail']"));

		WebElement offline=detail.findElement(By.xpath("./../td[@class='opts']//a[@class='up_status']"));
		offline.click();
		
		Wait.waitMilliSeconds(5000);
		return driver;
	}
	
	public WebDriver clickOnlineBtn(WebElement t){
		return this.clickOfflineBtn(t);
	}
	
	public void clickViewBtn(WebElement t){
		WebElement detail=t.findElement(By.xpath("./ancestor::td[@class='detail']"));
		WebElement view=detail.findElement(By.xpath("./../td[@class='opts']//a[@class='view']"));
		view.click();
		Wait.waitMilliSeconds(5000);
	}
	
	public void clickEditBtn(WebElement t){
		WebElement detail=t.findElement(By.xpath("./ancestor::td[@class='detail']"));

		WebElement edit=detail.findElement(By.xpath("./../td[@class='opts']//a[@class='edit']"));
		edit.click();	
		Wait.waitMilliSeconds(5000);
	}
	
	
	public String getStatus(String template_name){
		WebElement tName=LocateElement.locateElementUseText(driver, "//div[@id='rederEl']/table[@class='order']/tbody/tr/td[@class='detail']//a[@class='viewm']", template_name);
		WebElement detail=tName.findElement(By.xpath("./ancestor::td[@class='detail']"));
		WebElement status=detail.findElement(By.xpath("./../td[@class='status']/div"));
		return status.getText().trim();
	}
	
	public String getTempStatus(){
		String status=driver.findElement(By.xpath("//div[@id='goods_detail']//div[@class='v-head']//div[@class='status-1']")).getText();
		return status;
	}
	
	public String getTempName(){
		WebElement head=driver.findElement(By.xpath("//div[@id='goods_detail']//div[@class='v-head']"));
		return head.findElement(By.xpath("./../div[2]/div[@class='form-text']")).getText();
		
	}
	
	public String getTempType(){
		WebElement head=driver.findElement(By.xpath("//div[@id='goods_detail']//div[@class='v-head']"));
		return head.findElement(By.xpath("./../div[4]/div[contains(@class,'input')]")).getText();
	}
	
	public String getTempPrice(){
		WebElement head=driver.findElement(By.xpath("//div[@id='goods_detail']//div[@class='v-head']"));
		return head.findElement(By.xpath("./../div[5]//div[@class='status-1']")).getText();
	}
	
	public void inputPrice(String t_price){
		WebElement price=driver.findElement(By.id("goods_discount_price"));
		price.clear();
		price.sendKeys(t_price);
	}
	
	public void clickSaveBtn(){
		driver.findElement(By.id("save_btn")).click();
		Wait.waitMilliSeconds(5000);
	}
	
	public String getPrice(){
		WebElement price=driver.findElement(By.id("goods_discount_price"));
		return price.getAttribute("value");
	}
	
}
