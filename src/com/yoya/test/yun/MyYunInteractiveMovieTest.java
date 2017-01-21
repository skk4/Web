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

import com.yoya.page.yun.MyYunInteractiveMovie;
import com.yoya.util.MyYunAssist;
import com.yoya.util.Wait;
/**
 * 我的云盘-互动电影
 * @author Administrator
 *
 */
public class MyYunInteractiveMovieTest {
 
	
	public WebDriver driver;
	private MyYunInteractiveMovie movie;
	@BeforeTest
	@Parameters({"web_driver","host_url","user_name","pass_word"})
	public void beforeTest(String web_driver,String host_url,String user_name,String pass_word){
		//进入我的云盘
		driver=MyYunAssist.enterMyYun(web_driver, host_url, user_name, pass_word);
		this.movie=new MyYunInteractiveMovie(driver);
	}

	@AfterTest
	public void afterTest(){
		Wait.waitMilliSeconds(5000);
		driver.quit();
	}
	

	
	/**
	 * 用于定位不同的新建窗口
	 * @param driver
	 * @param page_title
	 * @param partial_url
	 * @return
	 */
/*	@Test(alwaysRun=true)
	public WebDriver locateDriver(WebDriver driver,String page_title,String partial_url){
		
		Set<String> ws=driver.getWindowHandles();
		//找到新建电影的窗口
		for(String c:ws){
			driver.switchTo().window(c);
			String title=driver.getTitle();
			if(title.equalsIgnoreCase(page_title)){
					break;
			}
		}		
		Assert.assertTrue(driver.getCurrentUrl().contains(partial_url));
		return driver;
	}*/
	
	
	/**
	 * 无法在制作电影的页面进行保存，该页面可能比较特殊，后续研究
	 * @param movie_name
	 */
/*	
	@Test(alwaysRun=true)
	@Parameters({"movie_name"})
	public void addEmptyMovie(String movie_name){

		Wait.waitMilliSeconds(5000);
		WebElement iframe=driver.findElement(By.xpath("//iframe[starts-with(@id,'layui-layer-iframe')]/"));
		driver.switchTo().frame(iframe);
		
		WebElement movie=driver.findElement(By.id("movieName"));
		movie.clear();
		Wait.waitMilliSeconds(5000);
		movie.sendKeys(movie_name);
		Wait.waitMilliSeconds(5000);
		
		driver.findElement(By.id("newMovie")).click();
	
	}*/
	
	
	/**
	 * 删除电影-隐藏图标
	 * @param movie_name
	 */
	@Test(alwaysRun=true)
	@Parameters({"movie_name"})
	public void deleteMovie(String movie_name){
		//进入互动电影
		this.movie.enterInteractiveMovie();
		
		this.movie.moveToMovie(movie_name);
		
		this.movie.clickDeleteMovieBtn(movie_name);

		Assert.assertEquals(this.movie.getCotentTip().getText(), "您确定要删除？");
		  
		Wait.waitMilliSeconds(5000);
		this.movie.confirm();
		Wait.waitMilliSeconds(5000);
		driver.switchTo().defaultContent();
			
	}
	
	/**
	 * 分享电影-隐藏图标
	 * @param movie_name
	 */
	@Test(alwaysRun=true)
	@Parameters({"movie_name"})
	public void shareMovie(String movie_name){
		this.movie.enterInteractiveMovie();
		
		this.movie.moveToMovie(movie_name);
		
		driver=this.movie.clickShareMovieBtn(movie_name);
		
		
		this.movie.chooseMovieType(movie_name);
		
		this.movie.sure();
		Wait.waitMilliSeconds(5000);
		
		//断言分享成功
		Assert.assertEquals(this.movie.getTitleTip().getText(), "发布与分享");
		//driver.findElement(By.xpath("//div[starts-with(@id,'layui-layer')]/span/a")).click();
		
		
	}
	
	/**
	 * 我的电影-编辑
	 */
	@Test(alwaysRun=true)
	@Parameters({"movie_name"})
	public void editMovie(String movie_name){
		this.movie.enterInteractiveMovie();
//		Wait.waitMilliSeconds(5000);
//		WebElement iframe = MyYunAssist.locateIframe(driver, "main");

		this.movie.moveToMovie(movie_name);

		this.movie.clickEditMovieBtn(movie_name);
		Wait.waitMilliSeconds(5000);
		  
		  //locateDriver(driver,"互动电影","http://movie.test.yoya.com/edit");
		  
		  driver.switchTo().defaultContent();
	}
	
	
	/**
	 * 我的电影-重命名
	 */
	@Test(alwaysRun=true)
	@Parameters({"old_movie_name","new_movie_name"})
	public void renameMovie(String old_movie_name,String new_movie_name){
		this.movie.enterInteractiveMovie();
		
		this.movie.moveToMovie(old_movie_name);
		
		this.movie.clickRenameMovieBtn(old_movie_name);

		this.movie.inputMovieName(old_movie_name, new_movie_name);		
		Wait.waitMilliSeconds(5000);
		Assert.assertEquals(this.movie.findMovie(new_movie_name).getText().trim(), new_movie_name);
		  
		driver.switchTo().defaultContent();
	
	}
	
	
	/**
	 * 复制电影
	 * @param movie_name
	 */
	@Test(alwaysRun=true)
	@Parameters({"movie_name"})
	public void copyMovie(String movie_name){
		this.movie.enterInteractiveMovie();
		
		//三个Actions定位隐藏在更多下的操作
		this.movie.moveToMovie(movie_name);

		this.movie.clickCopyMovieBtn(movie_name);
		
		  Wait.waitMilliSeconds(5000);
		  String copy_message=this.movie.getCopyTip().getText();
		  //String copy_movie_name=copy_message.substring(15);
		  Assert.assertTrue(copy_message.contains("确定要复制一个副本吗？"));	  
		  Wait.waitMilliSeconds(5000);
		  
		  this.movie.confirm();
		  
/*		  Wait.waitMilliSeconds(5000);
		  Assert.assertTrue(elementExist(driver,By.xpath("//table[@id='file_list']//tr[@data-name='"+copy_movie_name+"']")));
		  */
		  driver.switchTo().defaultContent();
		  
	}
	
	
	
	/**
	 * 电影标签
	 */
	@Test(alwaysRun=true)
	@Parameters({"movie_name","mark_name"})
	public void markInteractiveMovie(String movie_name,String mark_name){
		this.movie.enterInteractiveMovie();


		this.movie.moveToMovie(movie_name);
		  
		  driver=this.movie.clickMarkMovieBtn(movie_name);
		  //input标签输入框
		 this.movie.inputMark(mark_name);
		 
		 this.movie.save(); 
		 
		  WebDriverWait wait=new WebDriverWait(driver, 10);
		  Assert.assertTrue(wait.until(ExpectedConditions.textToBePresentInElement
				  (this.movie.getSucessTip(), "操作成功")));
		  Wait.waitMilliSeconds(5000);
		  driver.switchTo().defaultContent();
	}
	
	/**
	 * 电影导出
	 */
	@Test(alwaysRun=true)
	@Parameters({"movie_name","export_way","export_code"})
	public void exportMovie(String movie_name,String export_way,String export_code){

		this.movie.enterInteractiveMovie();

		this.movie.moveToMovie(movie_name);
		
		driver=this.movie.clickExportMovieBtn(movie_name);
		
		this.movie.exportMovie(export_way,export_code);

		Wait.waitMilliSeconds(5000);
		driver.switchTo().defaultContent();
		
	}
	
	
	/**
	 * 电影复制到机构
	 * 
	 */
	@Test(alwaysRun=true)
	@Parameters({"movie_name","org_name"})
	public void copyMovieToOrg(String movie_name,String org_name){
		this.movie.enterInteractiveMovie();
		Wait.waitMilliSeconds(5000);
		
		this.movie.moveToMovie(movie_name);
		
		this.movie.clickCopyToOrgBtn(movie_name);
		
		this.movie.chooseOrg(org_name);
		
		this.movie.confirmMove();
		
		Assert.assertEquals(this.movie.getSucessTip().getText(), "操作成功");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.switchTo().defaultContent();
	}
	
	
	
	
	
}
