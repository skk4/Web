package com.yoya.test.favorite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yoya.page.Dialog;
import com.yoya.page.MyYoya;
import com.yoya.page.favorite.Favorite;
import com.yoya.page.movieplay.MoviePlay;
import com.yoya.test.login.LoginTest;
import com.yoya.util.DriverExchangeAssist;
import com.yoya.util.ElementExistOrNot;
import com.yoya.util.Wait;

public class FavoriteTest {
  
  private WebDriver driver;	
  private Favorite favorite;
  
  	@BeforeTest
	@Parameters({"web_driver","host_url","user_name","pass_word"})
	public void beforeTest(String web_driver,String host_url,String user_name,String pass_word){
		  LoginTest lg=new LoginTest();
		  driver=lg.login(web_driver, host_url, user_name, pass_word);
		  
		  MyYoya myyoya=new MyYoya(driver);
		  driver=myyoya.enterMyfavorite();
		  
		  //进入收藏
		  this.favorite=new Favorite(driver);	  
	}
	
	@AfterTest
	public void afterTest(){
		driver.quit();
	}
  
  
  @Test(alwaysRun=true)
  @Parameters({"movie_name"})
  public void play(String movie_name) {
	  
	  //查找电影
	  WebElement movie=this.favorite.findMovie(movie_name);
	  //点击预览按钮
	  this.favorite.clickPlayBtn(movie);
	  
	  driver=DriverExchangeAssist.exchangeDriverUsePartialUrl(driver,movie_name );
	  
	  MoviePlay mp=new MoviePlay(driver);
	  
	  Assert.assertEquals(mp.getFavoriteStatus(), "取消收藏");
	  
  }
  
  
  @Test(alwaysRun=true)
  @Parameters({"movie_name"})
  public void cancelFavorite(String movie_name){
	  //查找电影
	  WebElement movie=this.favorite.findMovie(movie_name);
	  
	  //取消收藏按钮
	  this.favorite.clickCancelBtn(movie);
	  
	  Dialog d=new Dialog(driver);
	  Assert.assertEquals(d.getContent(), "确定要删除？");
	  d.clickconfirmBtn();
	  
	  driver.navigate().refresh();
	  Wait.waitMilliSeconds(5000);
	  Assert.assertFalse(ElementExistOrNot.elementExist(driver, By.xpath("//div[@id='list_model']/ul/li/h3[text()='"+movie_name+"']")));
	 
  }
  
}
