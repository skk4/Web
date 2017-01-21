package com.yoya.page.receipt;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.yoya.util.LocateElement;
import com.yoya.util.Wait;

public class ReceiptApply {
	private WebDriver driver;
	
	public ReceiptApply(WebDriver driver){
		this.driver=driver;
	}
	
	/**
	 * 默认选择类型为一年
	 */
	public void chooseTimeType(){
		WebElement temp=driver.findElement(By.id("laydate-wrapper"));
		temp.findElement(By.xpath(".//div[@class='trigger']")).click();
		Wait.waitMilliSeconds(5000);
		driver.findElement(By.xpath("//thead[@id='laydate-wrapper']//ul[contains(@class,'overflowing')]/li[@data-raw-value='12']")).click();
		Wait.waitMilliSeconds(5000);
	}
	
	
	
	public WebElement findReceipt(String order_id){
		WebElement order=LocateElement.locateElementUseText(driver, "//tbody[@id='orderListRender']/tr/td[2]", order_id);
		return order;
	}
	
	public void clickApplyBtn(WebElement order){
		order.findElement(By.xpath("./../td[@class='opts']/div/a")).click();
		Wait.waitMilliSeconds(5000);
	}
	
	public void newAddress(EmailAddress address){
	
		Actions action=new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).perform();
		Wait.waitMilliSeconds(2000);
		
		driver.findElement(By.id("add_addr")).click();
		Wait.waitMilliSeconds(5000);
		driver.findElement(By.id("mailing_name")).sendKeys(address.getReal_name());
		driver.findElement(By.id("mailing_mobile")).sendKeys(address.getMobile_number());
		
		WebElement province=driver.findElement(By.id("dk_container_mailing_province"));
		province.click();
		Wait.waitMilliSeconds(3000);
		province.findElement(By.xpath("//div[@id='dk_container_mailing_province']/div/ul/li/a[@data-dk-dropdown-value='"+address.getProvince()+"']")).click();
		
		Wait.waitMilliSeconds(5000);
		
		WebElement city=driver.findElement(By.id("dk_container_mailing_city"));
		city.click();
		Wait.waitMilliSeconds(3000);
		city.findElement(By.xpath("//div[@id='dk_container_mailing_city']/div/ul/li/a[@data-dk-dropdown-value='"+address.getCity()+"']")).click();
		
		driver.findElement(By.id("mailing_addr")).sendKeys(address.getAddress());
		
		driver.findElement(By.id("mailing_zipcode")).sendKeys(address.getEmail_code());
		
		
		
		driver.findElement(By.id("mailing_remark")).sendKeys(address.getPostscript());
		
		driver.findElement(By.id("save_addr")).click();
		Wait.waitMilliSeconds(5000);
	}
	
	
	public void clickchooseAddressBtn(WebElement add){
		add.findElement(By.xpath("./ancestor::div[@class='row']/div[@class='radio-box']")).click();
	}
	
	public WebElement findAddress(String province,String city,String address){
		WebElement add=driver.findElement(By.xpath("//div[@id='addr_list']//div[@class='address']/span[text()='"+province+city+address+"']"));
		return add;
	}
	
	public void setDefaultAddress(WebElement add){
		add.findElement(By.xpath("./ancestor::div[@class='row']/div[@class='opts-items']/div/a[@name='default']")).click();
	}
	
	public void clickSubmitBtn(){
		driver.findElement(By.id("invoice_sub")).click();
	}
	
	/**
	 * 找第一条记录的状态
	 * @return
	 */
	public String findApplyRecordStatus(){
		return driver.findElement(By.xpath("//tbody[@id='rederEl']/tr[1]/td[@class='state']/label")).getText();
	}
	
	public String findApplyRecordStatus(WebElement apply){
		return apply.findElement(By.xpath("./../td[@class='state']/label")).getText();
	}
	
	public WebElement findApplyRecord(String apply_id){
		return driver.findElement(By.xpath("//tbody[@id='rederEl']/tr/td[text()='"+apply_id+"']"));
	}
	
	public void clickViewBtn(WebElement apply){
		apply.findElement(By.xpath("./../td[@class='opts']/div/a[@name='viewApplyLink']")).click();
		Wait.waitMilliSeconds(5000);
	}
	
	public void clickCancelApplyBtn(WebElement apply){
		apply.findElement(By.xpath("./../td[@class='opts']/div/a[@name='cancelApplyLink']")).click();
		Wait.waitMilliSeconds(5000);
	}
	
	
	public void clickOrderCheck(WebElement order){
		order.findElement(By.xpath("./../td[contains(@class,'checkbox')]/label/i")).click();
		Wait.waitMilliSeconds(2000);
	}
	
	public void clickMultiApplyBtn(){
		
		driver.findElement(By.id("mergeInvoiceApply")).click();
		Wait.waitMilliSeconds(5000);
	}
	
	public String getAuditStatus(){
		return driver.findElement(By.xpath("//div[@id='audit_status']/span")).getText();
	}
	public String getInvoiceStatus(){
		return driver.findElement(By.xpath("//div[@id='invoice_status']/span")).getText();
	}
	
	public void clickApplyRecordTab(){
		driver.findElement(By.linkText("发票申请记录")).click();
	}
	
	
}
