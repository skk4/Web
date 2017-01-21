package com.yoya.page.accountset;



import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.AssertJUnit;

import com.yoya.util.Wait;

public class PersonalDetails {
	private WebDriver driver;
	private WebElement nickname;
	private WebElement name;
	private WebElement year;
	private WebElement month;
	private WebElement day;
	private WebElement constellation;
	private WebElement sex;
	private WebElement province;
	private WebElement city;
	private WebElement skills;
	private WebElement description;
	private WebElement submit;

	public PersonalDetails(WebDriver driver){
		this.driver=driver;
		this.nickname=driver.findElement(By.id("u_nick_name"));
		this.name=driver.findElement(By.id("u_user_name"));
		this.year=driver.findElement(By.id("dk_container_u_year"));
		this.month=driver.findElement(By.id("dk_container_u_month"));
		this.day=driver.findElement(By.id("dk_container_u_date"));
		this.constellation=driver.findElement(By.id("dk_container_u_constellation")).findElement(By.tagName("a"));
		this.sex=driver.findElement(By.id("b_user_sex"));
		this.province=driver.findElement(By.id("dk_container_u_province"));
		this.city=driver.findElement(By.id("dk_container_u_city"));
		this.skills=driver.findElement(By.id("u_specific_skills"));
		this.description=driver.findElement(By.id("u_user_desc"));
		this.submit=driver.findElement(By.id("user_submit"));
	}
	
	public WebDriver getDriver() {
		return driver;
	}



	public WebElement getNickname() {
		return nickname;
	}



	public WebElement getName() {
		return name;
	}



	public WebElement getYear() {
		return year;
	}



	public WebElement getMonth() {
		return month;
	}



	public WebElement getDay() {
		return day;
	}



	public WebElement getConstellation() {
		return constellation;
	}



	public WebElement getSex() {
		return sex;
	}



	public WebElement getProvince() {
		return province;
	}



	public WebElement getCity() {
		return city;
	}



	public WebElement getSkills() {
		return skills;
	}



	public WebElement getDescription() {
		return description;
	}



	public WebElement getSubmit() {
		return submit;
	}




	public void uploadHeadImg(String imgUrl){
		Wait.implicitlyWait(driver, 10);
		  Actions action=new Actions(driver);
		  action.moveToElement(driver.findElement(By.id("img_head_url"))).perform();
		  driver.findElement(By.className("upload")).click();
		  driver.findElement(By.name("file")).sendKeys(imgUrl);
		  Wait.waitMilliSeconds(5000);
		  driver.findElement(By.cssSelector("a.btn.btn-cropSave")).click();
		  AssertJUnit.assertEquals("上传成功！", driver.findElement(By.cssSelector("div.layui-layer-content.layui-layer-padding")).getText());
		  driver.findElement(By.className("layui-layer-btn0")).click();
	}

	
	
	public void inputNickName(String nickName){
		WebElement nick_name=this.getNickname();
		  nick_name.clear();
		  nick_name.sendKeys(nickName);
	}
	
	public void inputRealName(String userName){
		WebElement user_name=this.getName();
		  user_name.clear();
		  user_name.sendKeys(userName);
	}
	
	public void inputYear(){
		  WebElement year=this.getYear();
		  year.click();
		  Wait.waitMilliSeconds(5000);
		  driver.findElement(By.xpath("//div[@id='dk_container_u_year']/div[@class='dk_options']/ul/li/a[@data-dk-dropdown-value='1990']")).click();
		  
	}
	
	public void inputMonth(){
		  WebElement month=this.getMonth();
		  month.click();
		  Wait.waitMilliSeconds(5000);
		  driver.findElement(By.xpath("//div[@id='dk_container_u_month']/div[@class='dk_options']/ul/li/a[@data-dk-dropdown-value='09']")).click();
		  
	}
	public void inputDay(){
		  WebElement day=this.getDay();
		  day.click();
		  Wait.waitMilliSeconds(5000);
		  driver.findElement(By.xpath("//div[@id='dk_container_u_date']/div[@class='dk_options']/ul/li/a[@data-dk-dropdown-value='10']")).click();
		
	}
	
	public void inputSex(){
		  String b_user_sex_js = "document.getElementById(\"b_user_sex\").style.display='block';";
		  String g_user_sex_js="document.getElementById(\"g_user_sex\").style.display='block';";
	      ((JavascriptExecutor)driver).executeScript(b_user_sex_js);
	      ((JavascriptExecutor)driver).executeScript(g_user_sex_js);
		  this.getSex().click();
			
	}
	
	public void inputProvince(){
		  
		  WebElement province=this.getProvince();
		  province.click();
		  Wait.waitMilliSeconds(5000);
		  driver.findElement(By.xpath("//div[@id='dk_container_u_province']/div[@class='dk_options']/ul/li/a[@data-dk-dropdown-value='350000']")).click();
			
	}
	
	 public void inputCity(){
		  WebElement city=this.getCity();
		  city.click();
		  Wait.waitMilliSeconds(5000);
		  driver.findElement(By.xpath("//div[@id='dk_container_u_city']/div[@class='dk_options']/ul/li/a[@data-dk-dropdown-value='350200']")).click();

	 }
	 
	 public void inputSkills(String specific_skills){
		 WebElement skills=this.getSkills();
		  skills.clear();
		  skills.sendKeys(specific_skills);
	 }
	public void inputDescription(String user_desc){
		WebElement desc=this.description;
		  desc.clear();
		  desc.sendKeys(user_desc);
	}
	
	public void submit(){
		this.getSubmit().click();
	}
	

	

	
	public WebDriver entermodifyPassword(){
		driver.findElement(By.linkText("[修改密码]")).click();
		Wait.waitMilliSeconds(5000);
		return driver;
	}
	
	
	
	public WebDriver enterSecurityCenter(){
		driver.findElement(By.linkText("安全中心")).click();
		Wait.waitMilliSeconds(5000);
		return driver;
	}
	
	/**
	 * 进入教师资料
	 * @param 
	 * @return
	 */
	public WebDriver enterTeacherProfile(){
		driver.findElement(By.linkText("教师资料")).click();
		Wait.waitMilliSeconds(5000);
		return driver;
	}
	
	
	public WebDriver modifySuccessedConfirm(String expected){
		Alert alert=driver.switchTo().alert();
		  String message=alert.getText();
		  AssertJUnit.assertEquals(expected, message.trim());
		  alert.accept();
		  driver.switchTo().defaultContent();
		  return driver;
	}
	
	

}
