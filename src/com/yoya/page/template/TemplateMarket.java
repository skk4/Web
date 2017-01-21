package com.yoya.page.template;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.yoya.database.ResultSetHelper;
import com.yoya.util.DriverExchangeAssist;
import com.yoya.util.LocateElement;
import com.yoya.util.Wait;

public class TemplateMarket {
	private WebDriver driver;
	
	
	public TemplateMarket(WebDriver driver){
		this.driver=driver;
	}
	
	/**
	 * 点击申请为设计师
	 */
	public void clickApplyDesignerBtn(){
		driver.findElement(By.linkText("申请为设计师")).click();
		Wait.waitMilliSeconds(5000);
	}
	
	public void inputUsername(String user_realname){
		WebElement username=driver.findElement(By.id("user_name"));
		username.clear();
		username.sendKeys(user_realname);
	}
	
	public void inputMobileNumber(String mobile_number){
		WebElement number=driver.findElement(By.id("mobile"));
		number.clear();
		number.sendKeys(mobile_number);
	}
	public String clickSendCodeBtn(String mobile_number){

		WebElement e=driver.findElement(By.id("mobile_code"));
		e.clear();
		Wait.waitMilliSeconds(5000);
		driver.findElement(By.id("sendBtn")).click();
		Wait.waitMilliSeconds(30000);
		
		//查询数据库获取手机验证码
		
		String code=ResultSetHelper.smsMessage(mobile_number);
		return code.substring(4, 10);
	}
	
	public void inputCode(String mobile_code){
		WebElement code=driver.findElement(By.id("mobile_code"));
		code.clear();
		code.sendKeys(mobile_code);
	}
	
	public void inputWork1(String work1_url){
		WebElement work=driver.findElement(By.id("work_url_1"));
		work.clear();
		work.sendKeys(work1_url);
	}
	
	
	public void inputWork2(String work2_url){
		WebElement work=driver.findElement(By.id("work_url_2"));
		work.clear();
		work.sendKeys(work2_url);
	}
	
	public void submitApplyBtn(){
		this.driver.findElement(By.id("sub_btn")).click();
		Wait.waitMilliSeconds(5000);
	}
	
	/*
	 * "亲，您已提交了申请，我们的客服会尽快与您联系，请耐心等候！"
	 */
	public String getApplySuccessTip(){
		return driver.findElement(By.xpath("//div[@id='audit_div']/div[@class='con']/p")).getText();
	}
	
	
	
	public void chooseTempType(String template_type){
		WebElement t_t=LocateElement.locateElementUseText(driver, "//div[@id='template_type']/dl[@data-level='1']//a", template_type);
		  t_t.click();
	}
	
	public void chooseFeeType(String free_type){
		WebElement f_t=LocateElement.locateElementUseText(driver, "//dl[@id='fee_type']//a", free_type);
		  f_t.click();
	}
	
	public WebElement findTemplateMovie(String movie_title){
		WebElement movie=LocateElement.locateElementUseText(driver, "//div[@id='template_list']/div[@class='mov-list']//h3[@class='mov-title']/a", movie_title);
		  
		  Actions action=new Actions(driver);
		  action.moveToElement(movie).perform();
		  Wait.waitMilliSeconds(3000);
		  return movie;
	}
	
	public WebDriver clickPreviewBtn(WebElement movie,String movie_title){
		 //预览按钮
		  movie.findElement(By.xpath("./../../div[@class='wrap-img']/div[@class='opts-mask']/div[@class='wrap-opt']/a[1]")).click();
		  Wait.waitMilliSeconds(10000);
		  //切换到电影打开页面
		  driver=DriverExchangeAssist.exchangeDriverUsePartialUrl(driver, movie_title);
		  return driver;
	}
	
	public void clickBuyBtn(WebElement movie,String movie_title){
		 //购买按钮
		  movie.findElement(By.xpath("./../../div[@class='wrap-img']/div[@class='opts-mask']/div[@class='wrap-opt']/a[2]")).click();
		  Wait.waitMilliSeconds(5000);
	  
	}
	
	public String getMovieTitle(){
		return driver.findElement(By.id("goods_name")).getText();
	}
	
	public String getMovieTitle2(){
		return driver.findElement(By.xpath("//div[starts-with(@id,'layui-layer') and @type='page']//span[@id='goods_name']")).getText();
	}
	
	public void clickPayBtn(){
		driver.findElement(By.xpath("//div[starts-with(@id,'layui-layer') and @type='page']//button[@id='buy_pay']")).click();
	}
	
	public String getBuySuccTip(){
		Wait.implicitlyWait(driver, 10);
		WebElement tip=driver.findElement(By.xpath("//div[starts-with(@id,'layui-layer') and @type='dialog']/div"));
		//WebElement tip=Wait.explicitlyWait(driver, 10, By.xpath("//div[starts-with(@id,'layui-layer') and @type='dialog']/div"));
		return tip.getText();
	}

	
	public String getOrderSuccTip(){
		return driver.findElement(By.xpath("//div[@class='tip-1']")).getText();
	}
	
	
	public void inputSearchkey(String search_key){
		 WebElement search=driver.findElement(By.id("search_name"));
		  search.clear();
		  search.sendKeys(search_key);
	}
	
	
	public void clickSearchBtn(){
		driver.findElement(By.id("searchBtn")).click();
		  
		  Wait.waitMilliSeconds(10000);
	}
	
	public String getResultsCount(){
		return driver.findElement(By.id("record_count")).getText();
	}
	
	
	public void assertResults(String search_key){
		List<WebElement> templates=driver.findElements(By.xpath("//div[@id='template_list']/div/ul/li/h3[@class='mov-title']/a"));
		  
		  for(WebElement we:templates){
			  Assert.assertTrue(we.getText().contains(search_key));
		  }
	}
	
	public void clickPayImmeBtn(){
		driver.findElement(By.id("btn_pay")).click();
	}
	
	
	
}
