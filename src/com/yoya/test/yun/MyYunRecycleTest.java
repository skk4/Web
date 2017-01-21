package com.yoya.test.yun;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yoya.page.yun.MyYunRecycle;
import com.yoya.util.MyYunAssist;
import com.yoya.util.Wait;
/**
 * 我的云盘-回收站
 * @author Administrator
 *
 */
public class MyYunRecycleTest {

	public WebDriver driver;
	
	private MyYunRecycle recycle;
	
  @BeforeTest
  @Parameters({"web_driver","host_url","user_name","pass_word"})
  public void beforeTest(String web_driver,String host_url,String user_name,String pass_word) {
	  driver=MyYunAssist.enterMyYun(web_driver, host_url, user_name, pass_word);
	  recycle=new MyYunRecycle(driver);
	  
  }

  @AfterTest
  public void afterTest() {
	  Wait.waitMilliSeconds(5000);
	  driver.quit();
  }

  
  
  /**
   * 清空回收站
   */
  @Test(alwaysRun=true)
  public void clearAll() {

	  //进入回收站//定位main框架
	  driver=this.recycle.enterRecycle();
	
	  this.recycle.clickCleanAllBtn();
	  
	  
	  Assert.assertEquals(this.recycle.getTitleTip().getText(), "信息");
	  Assert.assertEquals(this.recycle.getContentTip().getText(), "清空回收站后，所有已删除的文件无法再恢复，请慎重考虑！确认要清空回收站？");
	  
	 this.recycle.confirm();
	  
	  Wait.waitMilliSeconds(5000);
	  driver.switchTo().defaultContent();
	  Wait.waitMilliSeconds(5000);
	  
  }
  
  /**
   * 删除文件
   * @param file_name
   */
  @Test(alwaysRun=true)
  @Parameters({"file_name"})
  public void deleteFile(String file_name){
	  //进入回收站 //定位main框架
	  driver=this.recycle.enterRecycle();
	  
	 this.recycle.chooseFile(file_name);
	  
	  this.recycle.clickDeleteButton();
	  
	 
	  Assert.assertEquals( this.recycle.getTitleTip().getText(), "信息");
	  Assert.assertEquals( this.recycle.getContentTip().getText(), "在回收站中被删除的文件将无法再恢复，请慎重考虑！确认要删除这些文件吗？");
	  
	  this.recycle.confirm();
	  Wait.waitMilliSeconds(5000);
	  driver.switchTo().defaultContent();
  }
  
  /**
   * 还原文件
   * @param file_name
   */
  @Test(alwaysRun=true)
  @Parameters({"file_name"})
  public void restoreFile(String file_name){
	  
	  //进入回收站
	  driver=this.recycle.enterRecycle();
	  
	  
	  //选择要恢复的数据
	  this.recycle.chooseFile(file_name);
	  
	  
	  this.recycle.clickRecoverBtn();
	  
	  Assert.assertEquals(this.recycle.getTip().getText(), "操作成功");
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.switchTo().defaultContent();
	  
	  
  }

  
  
}
