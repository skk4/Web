package com.yoya.test.template;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yoya.page.template.TemplateMarket;
import com.yoya.test.login.LoginTest;
import com.yoya.util.Wait;

public class TemplateMarketTest {

	private WebDriver driver;
	private TemplateMarket market;
  @BeforeTest
  @Parameters({"web_driver","host_url","user_name","pass_word"})
  public void beforeTest(String web_driver,String host_url,String user_name,String pass_word) {
	  LoginTest lg=new LoginTest();
	  driver=lg.login(web_driver, host_url, user_name, pass_word);
	  this.market=new TemplateMarket(driver);
  }

  @AfterTest
  public void afterTest() {
	  
  }
  
  
  @Test(alwaysRun=true)
  @Parameters({"user_realname","mobile_number","work1_url","work2_url"})
  public void designerApply(String user_realname,String mobile_number,String work1_url,String work2_url) {
	  //点击按钮
	  this.market.clickApplyDesignerBtn();
	  
	  this.market.inputUsername(user_realname);
	  
	  this.market.inputMobileNumber(mobile_number);
	  
	  String mobile_code=this.market.clickSendCodeBtn(mobile_number);
	  
	  this.market.inputCode(mobile_code);
	  
	  this.market.inputWork1(work1_url);
	  this.market.inputWork2(work2_url);
	  
	  this.market.submitApplyBtn();
	  //断言
	  
	  Assert.assertEquals(this.market.getApplySuccessTip(), "亲，您已提交了申请，我们的客服会尽快与您联系，请耐心等候！");
	  
	  
  }
  

  /*
   * 预览模板   "宣传","免费模板","刘小小合作"
   */
  @Test(alwaysRun=true)
  @Parameters({"template_type","free_type","movie_title"})
  public void previewTemplate(String template_type,String free_type,String movie_title){
	  
	  this.market.chooseTempType(template_type);
	  
	  this.market.chooseFeeType(free_type);
	  
	  Wait.waitMilliSeconds(5000);
	  
	  WebElement movie=this.market.findTemplateMovie(movie_title);
	  
	 driver=this.market.clickPreviewBtn(movie, movie_title);

	  Assert.assertEquals(this.market.getMovieTitle(), movie_title);
	  
  }
  
  /**
   * 购买免费模板
   * @param template_type
   * @param free_type
   * @param movie_title
   */
  @Test(alwaysRun=true)
  @Parameters({"template_type","free_type","movie_title"})
  public void buyTemplateMovie(String template_type,String free_type,String movie_title){
	  
	  this.market.chooseTempType(template_type);
	  
	  this.market.chooseFeeType(free_type);
	  
	  Wait.waitMilliSeconds(5000);

	  WebElement movie=this.market.findTemplateMovie(movie_title);
	  
	 this.market.clickBuyBtn(movie, movie_title);
	 
	 Assert.assertEquals(this.market.getMovieTitle2(), movie_title);
	 
	 this.market.clickPayBtn();
	 
	 
	 Assert.assertEquals(this.market.getBuySuccTip(), "购买成功");
	 
  }
  
  /**
   * 购买付费模板
   * @param template_type
   * @param free_type
   * @param movie_title
   */
  @Test(alwaysRun=true)
  @Parameters({"template_type","free_type","movie_title","pay_type","pay_username","pay_password"})
  public void buyTemplateMovieNoFree(String template_type,String free_type,String movie_title,String pay_type,String pay_username,String pay_password){
	  this.market.chooseTempType(template_type);
	  
	  this.market.chooseFeeType(free_type);
	  
	  Wait.waitMilliSeconds(5000);

	  WebElement movie=this.market.findTemplateMovie(movie_title);
	  
	  this.market.clickBuyBtn(movie, movie_title);
	  
	  Assert.assertEquals(this.market.getMovieTitle2(), movie_title);
	  this.market.clickPayBtn();
	  Wait.waitMilliSeconds(5000);
	  Assert.assertEquals(this.market.getOrderSuccTip(), "订单提交成功，请尽快付款");
	  
/*	  OrderPay orderpay=new OrderPay(driver);
	  orderpay.choosePayType(pay_type);
	  orderpay.clickPayBtn();
	  orderpay.clickLoginAccount();
	  orderpay.inputUsername(pay_username);
	  orderpay.inputPassword(pay_password);
	  orderpay.clickNextStep();*/
	  
		 
  }
  
  
  @Test(alwaysRun=true)
  @Parameters({"search_key","results_num"})
  public void searchTemplate(String search_key,String results_num){
	 
		this.market.inputSearchkey(search_key);

		this.market.clickSearchBtn();

		Assert.assertEquals(this.market.getResultsCount(), results_num);

		this.market.assertResults(search_key);
  
  }
  
  @Test(alwaysRun=true)
  @Parameters({"template_type","free_type","movie_title"})
  public void buyFreeAfterPreview(String template_type,String free_type,String movie_title){
	  this.market.chooseTempType(template_type);
	  
	  this.market.chooseFeeType(free_type);
	  
	  Wait.waitMilliSeconds(5000);
	  
	  WebElement movie=this.market.findTemplateMovie(movie_title);
	  
	 driver=this.market.clickPreviewBtn(movie, movie_title);
	 
	 //立即支付
	 
	 this.market.clickPayImmeBtn();
	 Assert.assertEquals(this.market.getMovieTitle2(), movie_title);
	 this.market.clickPayBtn();
	 Assert.assertEquals(this.market.getBuySuccTip(), "购买成功");
  }
  
  
  @Test(alwaysRun=true)
  @Parameters({"template_type","free_type","movie_title"})
  public void buyNofreeAfterPreview(String template_type,String free_type,String movie_title){
	  this.market.chooseTempType(template_type);
	  
	  this.market.chooseFeeType(free_type);
	  
	  Wait.waitMilliSeconds(5000);
	  
	  WebElement movie=this.market.findTemplateMovie(movie_title);
	  
	 driver=this.market.clickPreviewBtn(movie, movie_title);
	 
	 //立即支付
	 
	 this.market.clickPayImmeBtn();
	 
	 Assert.assertEquals(this.market.getMovieTitle2(), movie_title);
	 
	 this.market.clickPayBtn();
	 
	 	Wait.waitMilliSeconds(5000);
	  Assert.assertEquals(this.market.getOrderSuccTip(), "订单提交成功，请尽快付款");
  }
  
  
  
  
  
  
  
  
  
}
