package com.yoya.test.yun;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yoya.page.yun.MyYunMyWork;
import com.yoya.util.MyYunAssist;
import com.yoya.util.Wait;

/**
 * 我的云盘-赛事作品
 * @author Administrator
 *
 */
public class MyYunMyWorkTest {
	
	public WebDriver driver;
	private MyYunMyWork mywork;
	
  @BeforeTest
  @Parameters({"web_driver","host_url","user_name","pass_word"})
  public void beforeTest(String web_driver,String host_url,String user_name,String pass_word) {
	  driver=MyYunAssist.enterMyYun(web_driver, host_url, user_name, pass_word);
	  this.mywork=new MyYunMyWork(driver);
	  
  }
  @AfterTest
  public void afterTest() {
	  Wait.waitMilliSeconds(5000);
	  driver.quit();
  }
  

  
  
  /**
   * 编辑赛事作品
   */
  @Test(alwaysRun=true)
  @Parameters({"work_name"})
  public void editWork(String work_name) {
	  //进入赛事作品
	  this.mywork.enterWork();
	  
	  
	  this.mywork.movetoWorkAndEdit(work_name);
	  Wait.waitMilliSeconds(5000);
	  MyYunAssist.locateDriver(driver, "互动电影", "http://movie.test.yoya.com/edit");
	  
	  //返回原始driver
	  driver.switchTo().defaultContent();
	  
  }
  
  
  /**
   * 赛事作品标签
   */
  @Test(alwaysRun=true)
  @Parameters({"work_name","mark_name"})
  public void markWork(String work_name,String mark_name) {
	//进入赛事作品
	  this.mywork.enterWork();

	  
	  this.mywork.movetoWorkAndMark(work_name);
	  
	  //input标签输入框
	  this.mywork.inputMark(mark_name);
	  
	  this.mywork.saveBtn();
	  
	  
	  WebDriverWait wait=new WebDriverWait(driver, 10);
	  Assert.assertTrue(wait.until(ExpectedConditions.textToBePresentInElement
			  (this.mywork.getTip(), "操作成功")));
	  Wait.waitMilliSeconds(5000);
	  driver.switchTo().defaultContent();
  }
  
  /**
   * 复制赛事作品到我的互动电影
   */
  
/*  @Test(alwaysRun=true)
  @Parameters({"work_name"})
  public void copyWorkToRoot(String work_name) {
	//进入赛事作品
	  enterWork();
	  Wait.waitMilliSeconds(5000);
	  WebElement iframe=MyYunAssist.locateIframe(driver, "main");
	  Wait.waitMilliSeconds(5000);
	  Actions action=new Actions(driver);
	  action.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+work_name+"']"))).perform();

	  Actions action2=new Actions(driver);
	  action2.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+work_name+"']"+"/td//a[@class='copyTo']"))).perform();
	  Wait.waitMilliSeconds(5000);
	  
	  driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+work_name+"']"+"/td//a[@class='copyTo']")).click();
	  
	  WebElement iframe1=driver.findElement(By.xpath("//iframe[starts-with(@id,'layui-layer-iframe')]"));
	  driver.switchTo().frame(iframe1);	
	  
	  driver.findElement(By.id("tree_1_switch")).click();
	  Wait.waitMilliSeconds(5000);
	  
	  driver.findElement(By.xpath("//div[@id='tree_container']//ul[@id='tree_1_ul']/li/a[@title='我的互动电影']")).click();
	  Wait.waitMilliSeconds(2000);
	  driver.findElement(By.id("move_file")).click();
	  WebDriverWait wait=new WebDriverWait(driver, 10);
	  Assert.assertTrue(wait.until(ExpectedConditions.textToBePresentInElement
			  (driver.findElement(By.xpath("//*[@id=\"xubox_layer1\"]/div[1]/div/span[2]")), "操作成功")));
	  Wait.waitMilliSeconds(5000);
	  
	  driver.switchTo().defaultContent();
  }*/
  
  
  /**
   * 复制赛事作品到分类
   */
 /* @Test(alwaysRun=true)
  @Parameters({"work_name","classify"})
  public void copyWorkToClassify(String work_name,String classify) {
	  //进入赛事作品
	  enterWork();
	  

	  Wait.waitMilliSeconds(5000);
	  WebElement iframe=MyYunAssist.locateIframe(driver, "main");
	  Wait.waitMilliSeconds(5000);
	  Actions action=new Actions(driver);
	  action.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+work_name+"']"))).perform();

	  Actions action2=new Actions(driver);
	  action2.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+work_name+"']"+"/td//a[@class='copyTo']"))).perform();
	  Wait.waitMilliSeconds(5000);
	  
	  driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+work_name+"']"+"/td//a[@class='copyTo']")).click();
	  
	  WebElement iframe1=driver.findElement(By.xpath("//iframe[starts-with(@id,'layui-layer-iframe')]"));
	  driver.switchTo().frame(iframe1);	
	  
	  driver.findElement(By.id("tree_1_switch")).click();
	  Wait.waitMilliSeconds(5000);
	  
	  //我的电影根目录
	  driver.findElement(By.xpath("//div[@id='tree_container']//ul[@id='tree_1_ul']/li/a[@title='我的互动电影']")).click();
	  Wait.waitMilliSeconds(2000);
	  
	  //分类
	  
	  driver.findElement(By.xpath("//div[@id='tree_container']//ul[@id='tree_2_ul']/li/a[@title='"+classify+"']")).click();
	  Wait.waitMilliSeconds(2000);
	  
	  
	  //确定移动文件
	  driver.findElement(By.id("move_file")).click();
	  WebDriverWait wait=new WebDriverWait(driver, 10);
	  Assert.assertTrue(wait.until(ExpectedConditions.textToBePresentInElement
			  (driver.findElement(By.xpath("//*[@id=\"xubox_layer1\"]/div[1]/div/span[2]")), "操作成功")));
	  Wait.waitMilliSeconds(5000);
	  
	  driver.switchTo().defaultContent();
  }*/
  
  /**
   * 双击赛事作品播放
   */
  @Test(alwaysRun=true)
  @Parameters({"work_name"})
  public void playWork(String work_name){
	  //进入赛事作品
	  this.mywork.enterWork();
	  
	  this.mywork.movetoWorkAndPlay(work_name);
	 
	  Wait.waitMilliSeconds(5000);
	  MyYunAssist.locateDriver(driver, work_name, "http://movie.test.yoya.com/play");
	  driver.switchTo().defaultContent();
	  
  }
  
  
  

}
