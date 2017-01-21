package com.yoya.page.accountset;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;

import com.yoya.database.ResultSetHelper;
import com.yoya.util.Wait;

public class SecurityCenter {
	
	private WebDriver driver;
	
	public SecurityCenter(WebDriver driver){
		this.driver=driver;
	}
	
	
	/**
	 * 实名认证-进入认证页面
	 */
	
	public void enterAuthentication(){
		this.driver.findElement(By.xpath("//div[@id='verifica_div']/div[@class='form-opts']/a")).click();
		Wait.waitMilliSeconds(5000);
	}
	
	public void inputUsername(String user_name){
		WebElement username=this.driver.findElement(By.id("user_name"));
		username.clear();
		username.sendKeys(user_name);
	}
	
	/**
	 * 选择证件类型  
	 * 身份证："idcard" 
	 * 学生证："stcard" 
	 * 军官证："sdcard"
	 * @param card_type
	 */
	public void chooseCardType(String card_type){
		this.driver.findElement(By.xpath("//div[@id='dk_container_idcard_type']/a")).click();
		Wait.waitMilliSeconds(5000);
		
		this.driver.findElement(By.xpath("//div[@id='dk_container_idcard_type']/div[@class='dk_options']/ul/li/a[@data-dk-dropdown-value='"+card_type+"']")).click();
		
		
	}
	/**
	 * 输入证件号码
	 * @return
	 * 
	 */
	public void inputCardNumber(String card_number){
		WebElement number=this.driver.findElement(By.id("idcard"));
		number.clear();
		number.sendKeys(card_number);
	}
	
	/**
	 * 上传正面照片
	 * @return
	 */
	public void uploadFrontPic(String frontpic_url){
		this.driver.findElement(By.id("t_idcard_front_pic")).sendKeys(frontpic_url);
		Wait.waitMilliSeconds(5000);
	}
	
	/**
	 * 上传背面照
	 * @return
	 */
	public void uploadBackpic(String backpic_url){
		this.driver.findElement(By.id("t_idcard_back_pic")).sendKeys(backpic_url);
		Wait.waitMilliSeconds(5000);
	}
	
	
	/**
	 * 实名认证提交
	 * @return
	 */
	public void clickVerifyBtn(){
		this.driver.findElement(By.id("verifica_submit")).click();
	}
	
	
	public String getName(){
		return this.driver.findElement(By.xpath("//div[@id='mode_div']/div[1]/div[@class='form-text']/span")).getText();
	}
	
	public String getCardType(){
		return this.driver.findElement(By.xpath("//div[@id='mode_div']/div[2]/div[@class='form-text']/span")).getText();
	}
	
	public String getCardNumber(){
		return this.driver.findElement(By.xpath("//div[@id='mode_div']/div[3]/div[@class='form-text']/span")).getText();

	}
	
	public String getStatus1(){
		return this.driver.findElement(By.xpath("//div[@id='mode_div']/div[4]/div[@class='form-text']/span[1]")).getText();
	}
	
	public String getStatus2(){
		return this.driver.findElement(By.xpath("//div[@id='mode_div']/div[4]/div[@class='form-text']/span[2]")).getText();

	}
	
	public String getAuthTime(){

		return this.driver.findElement(By.xpath("//div[@id='mode_div']/div[5]/div[@class='form-text']/span")).getText();

	}
	
	
	
	public String getEmailBindStatus(){
		return this.driver.findElement(By.xpath("//div[@id='eamil_div']/div[@class='form-opts']/a")).getText().trim();
	}
	
	public void enterEmailBinding(){
		//进入邮箱绑定
		this.driver.findElement(By.xpath("//div[@id='eamil_div']/div[@class='form-opts']/a")).click();
		Wait.waitMilliSeconds(5000);
	}
	
	public void inputEmailAddr(String email_url){
		WebElement email=this.driver.findElement(By.id("u_email"));
		email.clear();
		email.sendKeys(email_url);
	}
	
	/**
	 * 确认按钮
	 */
	public void clickConfirmBtn(){
		this.driver.findElement(By.id("user_submit")).click();
		Wait.waitMilliSeconds(5000);
	}
	/**
	 * 获取空邮箱或错误格式提示  "邮箱地址格式错误" "邮件地址不能为空"
	 * @return
	 */
	public String getBadEmailNote(){
		return this.driver.findElement(By.xpath("//div[@class='help-tip']/p")).getText();
	}
	/**
	 * 重复绑定邮箱提示  "您的邮箱已经存在，注意一个邮箱只能绑定一个账号!"
	 */
	public String getDuplicateEmail(){
		return this.driver.findElement(By.id("error_msg")).getText();
	}
	
	
	/**
	 * 成功绑定邮箱提示  "邮箱绑定信息已经发送到您的邮箱，请尽快登录邮箱确认！"  "恭喜，手机绑定成功！"
	 */
	public String getSuccessTip(){
		return this.driver.findElement(By.xpath("//div[@id='msg_body']/p")).getText();
	}
	
	/**
	 * 修改密码按钮
	 */
	public void changePassword(){
		 driver.findElement(By.id("changePassword")).click();
	}
	public void inputOldpassword(String oldp){
		driver.findElement(By.id("u_old_password")).clear();
		driver.findElement(By.id("u_old_password")).sendKeys(oldp);
		  
	}
	public void inputNewpassword(String newp){
		driver.findElement(By.id("u_password")).clear();
		  driver.findElement(By.id("u_password")).sendKeys(newp);
	}
	
	public void confirmNewpassword(String newp){
		driver.findElement(By.id("u_re_password")).clear();
		  driver.findElement(By.id("u_re_password")).sendKeys(newp);
	}
	public void submitPasswordModify(){
		driver.findElement(By.id("user_change")).click();
	}
	public WebDriver modifySuccessedConfirm(String expected){
		Alert alert=driver.switchTo().alert();
		  String message=alert.getText();
		  AssertJUnit.assertEquals(expected, message.trim());
		  alert.accept();
		  driver.switchTo().defaultContent();
		  return driver;
	}
	

	
	
	
	/**
	 * 手机绑定
	 */
	/**
	 * 进入手机绑定页面
	 */
	public void enterMobileBinding(){
		this.driver.findElement(By.xpath("//div[@id='mobile_div']/div[@class='form-opts']/a")).click();
		Wait.waitMilliSeconds(5000);
	}
	
	
	/**
	 * 输入手机号
	 */
	public void inputMobileNumber(String m_number){
		WebElement mobile=driver.findElement(By.id("u_mobile"));
		mobile.clear();
		mobile.sendKeys(m_number);
	}
	
	/**
	 * 点击按钮获取验证码
	 */
	public void clickSendCode(){
		this.driver.findElement(By.id("send_code")).click();
		Wait.waitMilliSeconds(60000);
	}
	
	/**
	 * 获取验证码
	 * @param mobile_number
	 * @return
	 */
	public String getMobileCode(String mobile_number){
		return ResultSetHelper.smsMessage(mobile_number).substring(4, 10);
	}
	
	/**
	 * 输入验证码
	 */
	public void inputCode(String mobile_code){
		WebElement code=driver.findElement(By.id("u_mobile_code"));
		code.clear();
		code.sendKeys(mobile_code);
		Wait.waitMilliSeconds(5000);
	}
	
	
	


	
	
	
}
