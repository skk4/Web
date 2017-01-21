package com.yoya.test.login;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yoya.page.login.RegisterPage;
import com.yoya.util.Wait;
import com.yoya.util.WebDriverGenerator;

public class RegisterTest {
 
	private WebDriver driver;
	private RegisterPage register;

	@BeforeTest
	@Parameters({"web_driver","host_url"})
	public void beforeTest(String web_driver,String host_url){
		this.driver=WebDriverGenerator.generateWebDriver(web_driver);
		this.driver.get(host_url);
		Wait.waitMilliSeconds(5000);
		
		this.register=new RegisterPage(driver);
	}
	@AfterTest
	public void afterTest(){
		//Wait.waitMilliSeconds(5000);
		driver.quit();
	}
	
	/**
	 * 通过邮箱注册
	 */
  @Test(alwaysRun=true)
  @Parameters({"user_code","pass_word","contact"})
  public void registerYoyaViaEmail(String user_code,String pass_word,String contact) {
	//弹出注册对话框
	 this.register.clickRegesterIcon();
	  
	  //输入用户名
	  this.register.inputUsercode(user_code);
	  
	  Wait.waitMilliSeconds(5000);
	  //输入密码
	  this.register.inputPassword(pass_word);
	  Wait.waitMilliSeconds(5000);
	  //输入绑定内容
	  this.register.inputEmailOrMobile(contact);
	  Wait.waitMilliSeconds(5000);
	  //输入图片验证码
	  this.register.inputValidcode();
	  Wait.waitMilliSeconds(5000);
	  //马上注册
	  this.register.clickRegisterBtn();
	  
	  //断言注册成功
	  Assert.assertEquals(this.register.getRegisterSuccTip1(), "验证邮件已发送！");
	  Assert.assertEquals(this.register.getRegisterSuccTip2(), "请在48小时内登录您的邮箱验证");
	  Assert.assertEquals(this.register.getRegisterSuccTip3(), contact);
  }
  
  	/**
  	 * 通过手机注册
  	 */
  @Test(alwaysRun=true)
  @Parameters({"user_code","pass_word","contact"})
  public void registerYoyaViaMobile(String user_code,String pass_word,String contact){
		// 弹出注册对话框
		this.register.clickRegesterIcon();

		// 输入用户名
		this.register.inputUsercode(user_code);

		Wait.waitMilliSeconds(5000);
		// 输入密码
		this.register.inputPassword(pass_word);
		Wait.waitMilliSeconds(5000);
		// 输入绑定内容
		this.register.inputEmailOrMobile(contact);
		Wait.waitMilliSeconds(5000);
		// 输入图片验证码
		this.register.inputValidcode();
		Wait.waitMilliSeconds(5000);

		// 输入短信验证码
		String sendcode = this.register.clickSendcodeBtn(contact);
		this.register.inputMobilecode(sendcode);
		Wait.waitMilliSeconds(5000);
		// 马上注册
		this.register.clickRegisterBtn();

		// 断言
		Assert.assertEquals(this.register.getRegisterSuccTip1(), "恭喜，账号注册成功！");
	  
  }
  /**
   * 输入为空，直接提交注册
   */
  @Test(alwaysRun=true)
  public void registerWithNull(){
		// 弹出注册对话框
		this.register.clickRegesterIcon();
		Wait.waitMilliSeconds(5000);
		// 马上注册
		this.register.clickRegisterBtn();
		Wait.waitMilliSeconds(5000);

		Assert.assertEquals(this.register.getNullusernameTip(), "用户名不能为空");
		Assert.assertEquals(this.register.getNullpwdTip(), "密码不能为空");
		Assert.assertEquals(this.register.getNullcontactTip(), "邮箱/手机不能为空");
		Assert.assertEquals(this.register.getNullvalidcodeTip(), "验证码不能为空");
  }
  
  /**
   * 密码位数不够
   */
  @Test(alwaysRun=true)
  @Parameters({"user_code","pass_word"})
  public void registerWithSimplePwd(String user_code,String pass_word){
	// 弹出注册对话框
			this.register.clickRegesterIcon();

			// 输入用户名
			this.register.inputUsercode(user_code);

			Wait.waitMilliSeconds(5000);
			// 输入密码
			this.register.inputPassword(pass_word);
			Wait.waitMilliSeconds(5000);
			// 马上注册
			this.register.clickRegisterBtn();
			Wait.waitMilliSeconds(5000);
			
			Assert.assertEquals(this.register.getNullpwdTip(), "\"密码\"长度不能少于6个字");
			
			
  }
  
  
  /**
   * 绑定方式已用过
   */
  @Test(alwaysRun=true)
  @Parameters({"user_code","pass_word","contact"})
  public void registerWithUnAvaiableContact(String user_code,String pass_word,String contact){
		// 弹出注册对话框
		this.register.clickRegesterIcon();

		// 输入用户名
		this.register.inputUsercode(user_code);

		Wait.waitMilliSeconds(5000);
		// 输入密码
		this.register.inputPassword(pass_word);
		Wait.waitMilliSeconds(5000);
		// 输入绑定内容
		this.register.inputEmailOrMobile(contact);
		Wait.waitMilliSeconds(5000);

		// 马上注册
		this.register.clickRegisterBtn();
		Wait.waitMilliSeconds(5000);
		String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		String checktel = "^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$";

		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(contact);

		Pattern regextel = Pattern.compile(checktel);
		Matcher matchertel = regextel.matcher(contact);

		if (matcher.matches()) {// 是正确的邮箱，但是已被占用
			Assert.assertEquals(this.register.getNullcontactTip(), "该邮箱已被使用!");
		} else if (matchertel.matches()) {// 是正确的手机号，但是已被占用
			Assert.assertEquals(this.register.getNullcontactTip(), "该手机号已被使用!");
		} else {
			Assert.assertEquals(this.register.getNullcontactTip(), "请输入正确的邮箱地址/手机号码");

		}		
  }
  
  /**
   * 图片验证码出错
   * @param user_code
   * @param pass_word
   * @param contact
   */
  @Test(alwaysRun=true)
  @Parameters({"user_code","pass_word","contact"})
  public void registerWithUnAvaiableCheckcode(String user_code,String pass_word,String contact){
		// 弹出注册对话框
		this.register.clickRegesterIcon();

		// 输入用户名
		this.register.inputUsercode(user_code);

		Wait.waitMilliSeconds(5000);
		// 输入密码
		this.register.inputPassword(pass_word);
		Wait.waitMilliSeconds(5000);
		// 输入绑定内容
		this.register.inputEmailOrMobile(contact);
		Wait.waitMilliSeconds(5000);

		// 输入图片验证码
		this.register.inputValidcode("-1");
		Wait.waitMilliSeconds(5000);

		// 马上注册
		this.register.clickRegisterBtn();
		Wait.waitMilliSeconds(5000);

		Assert.assertEquals(this.register.getNullvalidcodeTip(), "验证码错误");
			
  }
  
  
  /**
   * 短信验证码出错
   */
  @Test(alwaysRun=true)
  @Parameters({"user_code","pass_word","contact"})
  public void registerWithUnAvaiableMoblieCode(String user_code,String pass_word,String contact){
		// 弹出注册对话框
		this.register.clickRegesterIcon();

		// 输入用户名
		this.register.inputUsercode(user_code);

		Wait.waitMilliSeconds(5000);
		// 输入密码
		this.register.inputPassword(pass_word);
		Wait.waitMilliSeconds(5000);
		// 输入绑定内容
		this.register.inputEmailOrMobile(contact);
		Wait.waitMilliSeconds(5000);

		//输入图片验证码
		this.register.inputValidcode();
		Wait.waitMilliSeconds(5000);
		
		// 输入错误验证码
		this.register.inputMobilecode("1234");
		Wait.waitMilliSeconds(5000);

		// 马上注册
		this.register.clickRegisterBtn();
		Wait.waitMilliSeconds(5000);

		Assert.assertEquals(this.register.getNullMobilecodeTip(), "手机验证码错误!");
  }
  
  

  
}
