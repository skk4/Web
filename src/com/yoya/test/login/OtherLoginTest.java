package com.yoya.test.login;


import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.AssertJUnit;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.yoya.page.login.LoginYoya;
import com.yoya.util.Wait;
import com.yoya.util.WebDriverGenerator;

/**
 * 第三方登录
 * @author Administrator
 */
public class OtherLoginTest {
	public WebDriver driver;
	private LoginYoya loginyoya;
	
	@BeforeTest
	@Parameters({"web_driver","host_url"})
	public void beforeTest(String web_driver,String host_url) throws Exception {
		driver=WebDriverGenerator.generateWebDriver(web_driver);
		loginyoya=new LoginYoya(driver);
		this.loginyoya.launchSite(host_url);
		
	}

	@AfterTest
	public void afterTest() throws Exception {
		driver.quit();
	}

	
	/**
	 * 第三方QQ登录
	 */
	@Test(alwaysRun=true)
	public void other_login_QQ() {
		
		this.loginyoya.clickLoginPrompt();
		this.loginyoya.otherLoginQQ();
		Wait.waitMilliSeconds(5000);
		String title=driver.getTitle();
		AssertJUnit.assertEquals(title, "QQ帐号安全登录");
	
	}
	
	/**
	 * 微信登陆
	 */
	@Test(alwaysRun=true)
	public void other_login_Wechat(){
		this.loginyoya.clickLoginPrompt();
		this.loginyoya.otherLoginWechat();
		Wait.waitMilliSeconds(5000);
		AssertJUnit.assertEquals("微信登录", driver.getTitle());
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	
	/**
	 * 微博登陆
	 */
	@Test(alwaysRun=true)
	public void other_login_Sina(){
		this.loginyoya.clickLoginPrompt();
		this.loginyoya.otherLoginSina();
		Wait.waitMilliSeconds(5000);	
		AssertJUnit.assertEquals("网站连接 - 优芽", driver.getTitle());
	
	}

}
