package com.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.yoya.test.login.LoginTest;
import com.yoya.util.DriverExchangeAssist;
import com.yoya.util.LocateIframe;
import com.yoya.util.Wait;

public class TestClient {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		LoginTest lg = new LoginTest();
		String web_driver = "chrome";
		String host_url = "http://www.99cj.com/";
		String user_name = "xiesj";
		String pass_word = "123456";
		WebDriver driver = lg.login(web_driver, host_url, user_name, pass_word);
		
		driver.findElement(By.cssSelector("i.icon.icon-newly-built")).click();
		//刚进入制作页面有点慢，等待时间久一点
		Wait.waitMilliSeconds(10000);
		driver = DriverExchangeAssist.exchangeDriverUseTitle(driver, "制作互动电影");
		
		
		
		
		driver=LocateIframe.locateIframeTwo(driver, "layui-layer-iframe","doView");
		
		
		
		
		
		Wait.waitMilliSeconds(5000);
		driver.findElement(By.id("movieName")).sendKeys("aaaa");
		
		Actions actions=new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath("//button[@id='newMovie']/span[@class='file-front']"))).perform();
		Wait.waitMilliSeconds(2000);
		driver.findElement(By.xpath("//button[@id='newMovie']/span[@class='file-back']")).click();
		Wait.waitMilliSeconds(5000);
		
		
		driver.findElement(By.id("sc_role")).click();
		Wait.waitMilliSeconds(5000);
		
		driver.findElement(By.id("sc_act")).click();
		Wait.waitMilliSeconds(5000);
		
		driver.findElement(By.id("sc_insert")).click();
		Wait.waitMilliSeconds(5000);
		
		driver.findElement(By.id("sc_prop")).click();
		Wait.waitMilliSeconds(5000);
		
		driver.findElement(By.id("sc_questions")).click();
		Wait.waitMilliSeconds(5000);
		
	}	

}
