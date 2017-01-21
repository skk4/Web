package com.yoya.page.receipt;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.yoya.util.Wait;

public class Receipt {
	
	private WebDriver driver;
	public Receipt(WebDriver driver){
		this.driver=driver;
	}
	
	
	/**
	 * 选择普通发票的单位选择
	 */
	public void chooseSimpleReceipt(){
		driver.findElement(By.xpath("//div[@id='simple_invoice']//div[@class='form-input']/div/label[2]/i")).click();
		Wait.waitMilliSeconds(2000);
		
	}
	/**
	 * 发票抬头
	 * @param company_name
	 */
	public void inputSimpleReceiptCom(String company_name){
		driver.findElement(By.id("invoice_title")).sendKeys(company_name);
	}
	
	/**
	 * 选择发票类型
	 * @param invoice_type
	 */
	public void chooseReceipt(String invoice_type){
		WebElement type=driver.findElement(By.xpath("//div[@class='from-invoice-infos']/div[contains(@class,'body')]//div[@class='radio-box']/label/span[text()='"+invoice_type+"']"));
		type.findElement(By.xpath("./../i")).click();
	}
	
	/**
	 * 专用发票-单位名称
	 */
	public void inputCompanyName(String company_name){
		WebElement name=driver.findElement(By.id("company_name"));
		name.clear();
		name.sendKeys(company_name);
		
	}
	
	
	/**
	 * 纳税人识别码
	 */
	public void inputTaxNumber(String ident_num){
		WebElement input=driver.findElement(By.id("taxplayer_ident_num"));
		input.clear();
		input.sendKeys(ident_num);
		
	}
	
	/**
	 *注册地址
	 */
	public void inputRegAddr(String registered_address){
		WebElement input=driver.findElement(By.id("registered_address"));
		input.clear();
		input.sendKeys(registered_address);
		
	}
	
	/**
	 *注册电话
	 */
	public void inputRegPhone(String registered_phone){
		WebElement input=driver.findElement(By.id("registered_phone"));
		input.clear();
		input.sendKeys(registered_phone);
		
	}
	
	

	/**
	 *开户银行
	 */
	public void inputbank(String bank){
		WebElement input=driver.findElement(By.id("bank"));
		input.clear();
		input.sendKeys(bank);
		
	}
	/**
	 *银行账户
	 */
	public void inputbankAccount(String bank_account){
		WebElement input=driver.findElement(By.id("bank_account"));
		input.clear();
		input.sendKeys(bank_account);
		
	}
	/**
	 *资质文件
	 */
	public void inputSvatFile(String svat_file_url){
		WebElement input=driver.findElement(By.id("svat_file_repeat"));
		input.sendKeys(svat_file_url);	
	}
	
	
	
}
