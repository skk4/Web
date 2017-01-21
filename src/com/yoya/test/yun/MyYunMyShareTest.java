package com.yoya.test.yun;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yoya.page.yun.MyYunMyShare;
import com.yoya.util.MyYunAssist;
import com.yoya.util.Wait;

/**
 * 我的云盘-我的分享
 * @author Administrator
 *
 */
public class MyYunMyShareTest {

	public WebDriver driver;
	private MyYunMyShare myshare;
	
  @BeforeTest
  @Parameters({"web_driver","host_url","user_name","pass_word"})
  public void beforeTest(String web_driver,String host_url,String user_name,String pass_word) {
	  driver=MyYunAssist.enterMyYun(web_driver, host_url, user_name, pass_word);
	  
	  this.myshare=new MyYunMyShare(driver);
  }

  @AfterTest
  public void afterTest() {
	  Wait.waitMilliSeconds(5000);
	  driver.quit();
  }
  

  
  
  /**
   * 复制分享
   * @param movie_name
   */
  @Test(alwaysRun=true)
  @Parameters({"movie_name"})
  public void copyMovieRef(String movie_name){
	  //进入我的分享
	 this.myshare.enterMyShare();
	  
	 this.myshare.chooseShare(movie_name);
	  
	  //复制链接按钮
	 this.myshare.clickCopyButton();

	  Assert.assertEquals(this.myshare.getTip().getText(), "复制成功！");
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  
	  driver.switchTo().defaultContent();
	  
  }
  
  /**
   * 取消分享
   */
  @Test(alwaysRun=true)
  @Parameters({"movie_name"})
  public void cancelMovie(String movie_name){
	  //进入我的分享
	  this.myshare.enterMyShare();

	  
	  /*Actions action=new Actions(driver);
	  action.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']"+"//tr[@data-name='"+movie_name+"']"+
	  "/td[@class='t-checkbox']/div/input[@type='checkbox']"))).perform();
	  driver.findElement(By.xpath("//table[@id='file_list']"+"//tr[@data-name='"+movie_name+"']"+
	  "/td[@class='t-checkbox']/div/input[@type='checkbox']")).click();
	  Wait.waitMilliSeconds(5000);*/
	  
	  this.myshare.chooseShare(movie_name);
  
	  this.myshare.clickDeleteShareBtn();
	  
	  Assert.assertEquals(this.myshare.getContentTip().getText(), "确定取消分享电影"+movie_name);
	  Wait.waitMilliSeconds(5000);
	  
	  this.myshare.confirm();
	  
	  Assert.assertEquals(this.myshare.getTip().getText(), "加载中，请稍候..");
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  
	  
	  driver.switchTo().defaultContent();  
  }
  
  
  
  
  /**
   * 重命名
   */
  @Test(alwaysRun=true)
  @Parameters({"old_movie_name","new_movie_name"})
  public void renameMovie(String old_movie_name,String new_movie_name){
	  	this.myshare.enterMyShare();

		this.myshare.movetoShare(old_movie_name);
	  	this.myshare.moveToRename(old_movie_name);
	  	
		this.myshare.clickRenameBtn(old_movie_name);

		
		this.myshare.inputName(old_movie_name, new_movie_name);
		
		this.myshare.clickRenameBtn(old_movie_name);
		Assert.assertEquals(this.myshare.findShare(new_movie_name).getText().trim(), new_movie_name);

		driver.switchTo().defaultContent();
  }
  
  
  /**
   * 分享电影标签
   * @param movie_name
   * @param mark_name
   */
  @Test(alwaysRun=true)
	@Parameters({"movie_name","mark_name"})
	public void markMovie(String movie_name,String mark_name){
	  this.myshare.enterMyShare();
		 

	  	this.myshare.movetoShare(movie_name);
	  
		  this.myshare.moveToMark(movie_name);
		  

		  this.myshare.clickMarkBtn(movie_name);
		  
		  //input标签输入框
		  this.myshare.inputMarkName(mark_name);
		  
		  this.myshare.saveButton();
		  
		  WebDriverWait wait=new WebDriverWait(driver, 10);
		  Assert.assertTrue(wait.until(ExpectedConditions.textToBePresentInElement
				  (this.myshare.getMarkTip(), "操作成功")));
		  Wait.waitMilliSeconds(5000);
		  driver.switchTo().defaultContent();
	}
  
  
  
  /**
   * 二维码
   */
  @Test(alwaysRun=true)
  @Parameters({"movie_name"})
  public void showcodeMovie(String movie_name){
	  this.myshare.enterMyShare();
	  
	  
	  Wait.waitMilliSeconds(5000);

	  this.myshare.movetoShare(movie_name);
	  
	  this.myshare.movetoShareCode(movie_name);
	  
	  this.myshare.clickShareCodeBtn(movie_name);
	  Assert.assertEquals(this.myshare.getTitleTip().getText(), "二维码");
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.switchTo().defaultContent();
  }
  
  
  
  

}
