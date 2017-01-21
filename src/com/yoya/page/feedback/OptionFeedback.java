package com.yoya.page.feedback;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.yoya.util.Wait;



public class OptionFeedback {
	private WebDriver driver;
	
	public OptionFeedback(WebDriver driver){
		this.driver=driver;
	}
	
	
	public void enterReport(){
		driver.findElement(By.className("report")).click();
		Wait.waitMilliSeconds(5000);
	}
	
	public void chooseProjectType(String product_type){
		WebElement projects=driver.findElement(By.id("project_list"));
		projects.findElement(By.xpath("./option[text()='"+product_type+"']")).click();
	}
	
	
	
	public void inputsuggestDesc(String fb_desc){
		WebElement content=driver.findElement(By.id("suggest_content"));
		content.clear();
		content.sendKeys(fb_desc);
	}
	
	
	public void inputRpDesc(String rp_desc){
		WebElement content=driver.findElement(By.id("jubao_desc"));
		content.clear();
		content.sendKeys(rp_desc);
	}
	
	public void inputsuggestUrl(String rel_website){
		WebElement url=driver.findElement(By.id("suggest_url"));
		url.clear();
		url.sendKeys(rel_website);
	}
	
	public void inputRpUrl(String rp_website){
		WebElement url=driver.findElement(By.id("jubao_url"));
		url.clear();
		url.sendKeys(rp_website);
	}
	
	
	public void inputContact(String contact){
		WebElement way=driver.findElement(By.id("suggest_contact_way"));
		way.clear();
		way.sendKeys(contact);
	}
	
	
	public void inputRpContact(String contact){
		WebElement way=driver.findElement(By.id("jubao_contact_way"));
		way.clear();
		way.sendKeys(contact);
	}
	
	
	public void submit(){
		WebElement submit=driver.findElement(By.id("user_submit"));
		submit.click();
		Wait.waitMilliSeconds(5000);
	}
	
}
