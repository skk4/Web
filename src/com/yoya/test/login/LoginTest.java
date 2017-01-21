package com.yoya.test.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yoya.page.MyYoya;
import com.yoya.page.login.LoginYoya;
import com.yoya.util.Wait;
import com.yoya.util.WebDriverGenerator;
/**
 * 登陆测试
 * @author Administrator
 */
public class LoginTest {
	
	public WebDriver driver;
	private LoginYoya loginyoya;
	
	@BeforeTest
	@Parameters({"web_driver","host_url"})
	public void BeforeTest(String web_driver,String host_url){
		driver=WebDriverGenerator.generateWebDriver(web_driver);
		loginyoya=new LoginYoya(driver);
		loginyoya.launchSite(host_url);
	}
	
	@AfterTest
	public void AfterTest(){
		Wait.waitMilliSeconds(5000);
		driver.quit();
	}
	

	
	/**
	 * 用于其他模块的登陆主站使用
	 * @param web_driver
	 * @param host_url
	 * @param user_name
	 * @param pass_word
	 * @return
	 */
	public  WebDriver login(String web_driver,String host_url,String user_name,String pass_word ){
		driver=WebDriverGenerator.generateWebDriver(web_driver);
		this.loginyoya=new LoginYoya(driver);
		this.loginyoya.launchSite(host_url);
		this.loginyoya.clickLoginPrompt();
		this.loginyoya.inputUsername(user_name);
		this.loginyoya.inputPassword(pass_word);
		this.loginyoya.clickLoginButton();
		
		return driver;
	}
	
	
	/**
	 * 用户不存在
	 */
	@Test(alwaysRun=true)
	@Parameters({"user_name","pass_word","assert_string"})
	public void loginYoyaUserNotExist(String wrong_user,String wrong_password,String assert_string){
		
		this.loginyoya.clickLoginPrompt();
		this.loginyoya.inputUsername(wrong_user);
		this.loginyoya.inputPassword(wrong_password);
		this.loginyoya.clickLoginButton();	
		WebElement error_tips=this.loginyoya.getWrongUsernameTip();
		String tips=error_tips.getText().substring(0, 21);
		AssertJUnit.assertEquals(tips, assert_string);
		
		
	}
	
	/**
	 * 空用户名
	 */
	@Test(alwaysRun=true)
	@Parameters({"null_user_name","pass_word","assert_string"})
	public void loginYoyaViaNonUsername(String null_user_name,String pass_word,String assert_string){

		this.loginyoya.clickLoginPrompt();
		this.loginyoya.inputUsername(null_user_name);
		this.loginyoya.inputPassword(pass_word);
		this.loginyoya.clickLoginButton();	
		
		WebElement tip=this.loginyoya.getNuLLusernameTip();
		AssertJUnit.assertEquals(tip.getText(), assert_string);
		
	}
	
	
	/**
	 * 空密码
	 */
	@Test(alwaysRun=true)
	@Parameters({"user_name","pass_word","assert_string"})
	public void loginYoyaViaNonPassword(String user_name,String pass_word,String assert_string){

		this.loginyoya.clickLoginPrompt();
		this.loginyoya.inputUsername(user_name);
		this.loginyoya.inputPassword(pass_word);
		this.loginyoya.clickLoginButton();	
		
		WebElement tip=this.loginyoya.getNullpasswordTip();
		AssertJUnit.assertEquals(tip.getText(), assert_string);
		
	}
		
	/**
	 * 错误密码
	 * 
	 */
	@Test(alwaysRun=true)
	@Parameters({"user_name","pass_word"})
	public void loginYoyaViaWrPassword(String user_name,String pass_word){
		
		this.loginyoya.clickLoginPrompt();
		this.loginyoya.inputUsername(user_name);
		this.loginyoya.inputPassword(pass_word);
		
		this.loginyoya.clickLoginButton();
		WebElement password_tips=this.loginyoya.getWrongPasswordTip();
		String tips=password_tips.getText();
		
		AssertJUnit.assertTrue(tips.contains("密码错误，出错"));
		AssertJUnit.assertTrue(tips.contains("次后账号将锁定"));
	}
	

	/**
	 *用户名和密码为空
	 * 
	 */
	@Test(alwaysRun=true)
	@Parameters({"assert_user","assert_password"})
	public void loginYoyaViaAllNon(String assert_user,String assert_password){
		this.loginyoya.clickLoginPrompt();
		this.loginyoya.getInput_username().clear();
		this.loginyoya.getInput_password().clear();
		this.loginyoya.clickLoginButton();
		WebElement e1=this.loginyoya.getNuLLusernameTip();	
		WebElement e2=this.loginyoya.getNullpasswordTip();
		AssertJUnit.assertEquals(e1.getText(), assert_user);
		AssertJUnit.assertEquals(e2.getText(), assert_password);
		
	}
	
	/**
	 * 用户登出
	 * @param user_name
	 * @param pass_word
	 */
	@Test(alwaysRun=true)
	@Parameters(value={"user_name","pass_word"})
	public void logoutYoya(String user_name,String pass_word){
		loginyoya.clickLoginPrompt();
		this.loginyoya.inputUsername(user_name);
		this.loginyoya.inputPassword(pass_word);
		this.loginyoya.clickLoginButton();
		String title=driver.getTitle();
		AssertJUnit.assertEquals(title, "优芽互动电影");
		this.loginyoya.logout();
		Assert.assertEquals(driver.getTitle(), "优芽网 帮您更优秀- www.yoya.com");
	}
	
	/**
	 * 用户名和密码单次登陆
	 * @param user_name
	 * @param pass_word
	 */
	@Test(alwaysRun=true)
	@Parameters(value={"user_name","pass_word","nick_name"})
	public void loginYoya(String user_name,String pass_word,String nick_name) {
		
		loginyoya.clickLoginPrompt();
		
		this.loginyoya.inputUsername(user_name);
		
		this.loginyoya.inputPassword(pass_word);
		
		this.loginyoya.clickLoginButton();
		
		
		String title=driver.getTitle();
		AssertJUnit.assertEquals(title, "优芽互动电影");
		
		MyYoya myyoya=new MyYoya(driver);
		Assert.assertEquals(myyoya.getNickname(), nick_name);
		
	}
	
	
	
	
}






