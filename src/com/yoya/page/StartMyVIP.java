package com.yoya.page;

import static com.yoya.util.ElementExistOrNot.elementExist;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.yoya.util.Wait;

public class StartMyVIP {
	
	private WebDriver driver;
	private WebElement startvip;
	private WebElement nowvip;
	
	
	public StartMyVIP(WebDriver driver){
		this.driver=driver;
		this.startvip=driver.findElement(By.linkText("开通VIP"));
		this.nowvip=driver.findElement(By.xpath("//div[@id='canOpBtn']/button"));
		
	}
	
	public void clickStartVip(){
		this.startvip.click();
	}
	
	public void clickNowVip(){
		this.nowvip.click();
	}
	
	

	  /**
	   * 购买VIP套餐的过程
	   * @param vip_type
	   * @param vip_time
	   * @param pay_type
	   */
	  public String purchaseProcess(String vip_type,String vip_time,String pay_type){

		  String order_id="";
		  Wait.waitMilliSeconds(5000);
		  WebElement vip=driver.findElement(By.xpath("//input[@id='"+vip_type+"']"));
		  
		  vip.findElement(By.xpath("./../span//span[@class='name']")).click();

		  if(vip_time.equalsIgnoreCase("3")){
			  
			  driver.findElement(By.xpath("//div[@id='pay_type_id']/label/input[@bill_time_num='3' and @bill_unit='m']")).click();
			  Wait.waitMilliSeconds(5000);
		  }else if(vip_time.equalsIgnoreCase("12")){
			  driver.findElement(By.xpath("//div[@id='pay_type_id']/label/input[@bill_time_num='1' and @bill_unit='y']")).click();
			  Wait.waitMilliSeconds(5000);
		  }else {//默认情况是1个月
			  driver.findElement(By.xpath("//div[@id='pay_type_id']/label/input[@bill_time_num='1' and @bill_unit='m']")).click();
			  Wait.waitMilliSeconds(5000);
		  }
		  
		  
		  driver.findElement(By.id("go_pay")).click();
		  Wait.waitMilliSeconds(5000);
		  Assert.assertEquals(driver.findElement(By.className("tip-1")).getText(), "订单提交成功，请尽快付款");

		  order_id=driver.findElement(By.id("order_no")).getText();
		  
		  driver.findElement(By.xpath("//input[@value='"+pay_type+"']/parent::label")).click();
		  Wait.waitMilliSeconds(5000);
		  if(pay_type.equalsIgnoreCase("alipay")){
			  driver.findElement(By.id("pay_type_alipay")).click();
		  }else{
			  Assert.assertTrue(elementExist(driver,By.id("wx_qr_code")));
		  }
		  Wait.waitMilliSeconds(5000);
		  return order_id;
	  }
}
