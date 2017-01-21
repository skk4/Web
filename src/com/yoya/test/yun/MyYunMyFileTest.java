package com.yoya.test.yun;
import static com.yoya.util.ElementExistOrNot.elementExist;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yoya.page.yun.MyYunMyFile;
import com.yoya.util.MyYunAssist;
import com.yoya.util.Wait;

/**
 * 我的云盘-我的文件
 * @author Administrator
 *
 */
public class MyYunMyFileTest {
	public WebDriver driver;
	private MyYunMyFile myfile;
	
	@BeforeTest
	@Parameters({"web_driver","host_url","user_name","pass_word"})
	public void beforeTest(String web_driver,String host_url,String user_name,String pass_word){
		driver=MyYunAssist.enterMyYun(web_driver, host_url, user_name, pass_word);
		this.myfile=new MyYunMyFile(driver); 
	}
	
	@AfterTest
	public void afterTest(){
		Wait.waitMilliSeconds(5000);
		driver.quit();
	}

	/**
	 * 检查logo图片和logo文字
	 */
	  @Test(alwaysRun=true)
	  public void logoVerify() {
		  	Assert.assertTrue(elementExist(driver,By.className("logo")));
		  	Assert.assertEquals("我的云盘",this.myfile.getLogoText().getText());
	  }
	  

	  /**
	   * 新建文件夹
	   */
	  @Test(alwaysRun=true)
	  @Parameters(value={"filedir_name"})
	  public void createFileDir(String filedir_name){
		  Wait.waitMilliSeconds(5000);
		  
		  //新建文件夹按钮
		  this.myfile.clickNewDirectoryButton();
		  
		  this.myfile.inputDirectoryName(filedir_name);
		  
		  this.myfile.confirm();
		  
		  Assert.assertEquals(this.myfile.findFile(filedir_name).getText(), filedir_name);
		  Wait.waitMilliSeconds(5000);
		  driver.switchTo().defaultContent();
	  }
	  
	  /**
	   * 文件夹和文件删除（顶部图标）
	   */
	  @Test(alwaysRun=true)
	  @Parameters({"filedir_name_delete"})
	  public void deleteFiledir(String filedir_name_delete){
		  
		  this.myfile.clickDeleteDirectoryTopButton(filedir_name_delete);
		  
		  Assert.assertEquals(this.myfile.getTip().getText(), "您确定要删除？");
		  
		  Wait.waitMilliSeconds(5000);
		 
		  this.myfile.confirm();
		  
		  Wait.waitMilliSeconds(5000);
		  driver.switchTo().defaultContent();
	  }
	  
	  /**
	   * 文件夹和文件删除（右键图标）
	   */
/*	  @Test(alwaysRun=true)
	  @Parameters({"filedir_name_delete"})
	  public void deleteFiledirFromRightButton(String filedir_name_delete){
		  
		  Wait.waitMilliSeconds(5000);
		  WebElement iframe=locateIframe("main");
		  
		  
		  Actions action=new Actions(driver);
		  action.contextClick(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+filedir_name_delete+"']"))).perform();
		  Wait.waitMilliSeconds(5000);
		  driver.findElement(By.xpath("//div[@id='folderLi']/ul/li[@class='delete']")).click();
		  Wait.waitMilliSeconds(5000);
		  Assert.assertEquals(driver.findElement(By.className("layui-layer-content")).getText(), "您确定要删除？");
		  
		  Wait.waitMilliSeconds(5000);
		  driver.findElement(By.className("layui-layer-btn0")).click();
		  Wait.waitMilliSeconds(5000);
		  driver.switchTo().defaultContent();
	  }*/
	  
	  /**
	   * 文件夹和文件删除（隐藏图标）
	   */
	  @Test(alwaysRun=true)
	  @Parameters({"filedir_name_delete"})
	  public void deleteFiledirFromHiddenButton(String filedir_name_delete){
		  this.myfile.clickDeleteDiretHiddenButton(filedir_name_delete);
		  Wait.waitMilliSeconds(5000);
		  Assert.assertEquals(this.myfile.getTip().getText(), "您确定要删除？");
		  
		  Wait.waitMilliSeconds(5000);
		  this.myfile.confirm();
		  
		  Wait.waitMilliSeconds(5000);
		  driver.switchTo().defaultContent();
	  }
	  
	  
	  
	  
	  
	  

	  
	  /**
	   * 编辑文件夹(重命名)名称（隐藏图标）
	   */
	  @Test(alwaysRun=true)
	  @Parameters(value={"old_filedir_name","new_filedir_name"})
	  public void editFiledirFromHiddenButton(String old_filedir_name,String new_filedir_name){
		  
		  this.myfile.clickRenameDiretHiddenBtn(old_filedir_name, new_filedir_name);
		  
		  Wait.waitMilliSeconds(5000);

		  this.myfile.inputRenameName(old_filedir_name, new_filedir_name);
		  
		  Wait.waitMilliSeconds(5000);
		  
		  this.myfile.renameConifrm(old_filedir_name);
		  
		  Wait.waitMilliSeconds(5000);
		  
		  Assert.assertEquals(this.myfile.findFile(new_filedir_name).getText().trim(), new_filedir_name);
		  
		  driver.switchTo().defaultContent();

	  }
	  
	  
	  
	  /**
	   * 编辑文件夹(重命名)名称（右键按钮）
	   */
/*	  @Test(alwaysRun=true)
	  @Parameters(value={"old_filedir_name","new_filedir_name"})
	  public void editFiledirFromRightButton(String old_filedir_name,String new_filedir_name){
		  Wait.waitMilliSeconds(5000);
		  WebElement iframe=locateIframe("main");
		 
		  Actions action=new Actions(driver);		  
		  action.contextClick(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+old_filedir_name+"']"))).perform();
		 
		  Wait.waitMilliSeconds(5000);
		  
		  driver.findElement(By.xpath("//div[@id='folderLi']/ul/li[@class='rename']")).click();
		  
		  Wait.waitMilliSeconds(5000);
		  
		  WebElement we=driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+old_filedir_name+"']"+"/td[@class='t-filename']/a/input"));
		  Wait.waitMilliSeconds(5000);
		  we.clear();
		  we.sendKeys(new_filedir_name);
		  Wait.waitMilliSeconds(5000);
		  driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+old_filedir_name+"']"+"//button[@class='sure']")).click();
		  Wait.waitMilliSeconds(5000);
		  
		  Assert.assertEquals(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+new_filedir_name+"']"+"/td[@class='t-filename']"+"/a/span[@class='name']")).getText().trim(), new_filedir_name);
		  
		  driver.switchTo().defaultContent();
		  
	  }*/
	  
	  
	  
	  /**
	   * 编辑文件夹(重命名)名称（顶部图标）
	   */
/*	  @Test(alwaysRun=true)
	  @Parameters(value={"old_filedir_name","new_filedir_name"})
	  public void editFiledir(String old_filedir_name,String new_filedir_name){
		  
		  Wait.waitMilliSeconds(5000);
		  WebElement iframe=locateIframe("main");		  
		  Actions action=new Actions(driver);
		  driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+old_filedir_name+"']"+"//input[@type='checkbox']")).click();
		  Wait.waitMilliSeconds(5000);
		  action.moveToElement(driver.findElement(By.xpath("//div[@class='btn-more-wrapper']/a")))
		  .moveToElement(driver.findElement(By.xpath("//div[@class='btn-more-wrapper']/div/a[@class='rename']"))).perform();
		  Wait.waitMilliSeconds(5000);
		  
		  driver.findElement(By.xpath("//div[@class='btn-more-wrapper']/a")).click();
		  
		  driver.findElement(By.xpath("//div[@class='btn-more-wrapper']/div/a[@class='rename']")).click();
		  Wait.waitMilliSeconds(5000);
		  
		  WebElement we=driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+old_filedir_name+"']"+"/td[@class='t-filename']/a/input"));
		  Wait.waitMilliSeconds(5000);
		  we.clear();
		  we.sendKeys(new_filedir_name);
		  Wait.waitMilliSeconds(5000);
		  driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+old_filedir_name+"']"+"//button[@class='sure']")).click();
		  Wait.waitMilliSeconds(10000);
		  Assert.assertEquals(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+new_filedir_name+"']"+"/td[@class='t-filename']"+"/a/span[@class='name']")).getText().trim(), new_filedir_name);
			
		  driver.switchTo().defaultContent();
	  }*/
	  
	  /**
	   * 上传文件
	   */
	  @Test(alwaysRun=true)
	  @Parameters({"resource_url"})
	  public void uploadResource(String resource_url){
		  
		  this.myfile.clickUploadButton();
		  
		  this.myfile.uploadResource(resource_url);
		  
		  this.myfile.uploadConfirm();
		  driver.switchTo().defaultContent();
	  }
	  

	  
	  
	  
	  /**
	   * 文件下载（顶部图标）
	   */
/*	  @Test
	  @Parameters({"file_name"})
	  public void downloadFile(String file_name){
		  Wait.waitMilliSeconds(5000);
		  WebElement iframe=locateIframe("main");
		  
		  driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+file_name+"']"+"//input[@type='checkbox']")).click();
		  driver.findElement(By.cssSelector("i.icon.icon-a-download")).click();
		  
		  Wait.waitMilliSeconds(10000);
  
	  }*/
	  
	  
	  
	  /**
	   * 文件的隐藏图标功能-下载
	   */
	  
	  @Test(alwaysRun=true)
	  @Parameters({"file_name"})
	  public void downloadFileFromHidden(String file_name){
		  this.downloadFileFromHidden(file_name);
		  //下载等待时间
		  Wait.waitMilliSeconds(10000);
	  }
	  
	  /**
	   * 文件的隐藏图标功能-删除
	   */
	  @Test(alwaysRun=true)
	  @Parameters({"file_name_delete"})
	  public void deleteFileFromHidden(String file_name_delete){
		  this.myfile.clickDeleteFileHiddenBtn(file_name_delete);
		  
		  Wait.waitMilliSeconds(5000);
		  Assert.assertEquals(this.myfile.getTip().getText(), "您确定要删除？");
		  
		  Wait.waitMilliSeconds(5000);
		  
		  this.myfile.confirm();
		  
		  Wait.waitMilliSeconds(5000);
		  driver.switchTo().defaultContent();
	  }
	  
	  
	  /**
	   * 文件的隐藏图标功能-预览
	   */
	  @Test(alwaysRun=true)
	  @Parameters(value={"file_name","file_format"})
	  public void previewFileFromHidden(String file_name,String file_format){
		  
		  this.myfile.clickPreviewFileHiddenBtn(file_name);
		  Wait.waitMilliSeconds(5000);
		  
		  //Assert.assertEquals(driver.findElement(By.xpath("//div[starts-with(@id,layui-layer)]/div[@class='layui-layer-title']")).getText().trim()+file_format, file_name);
		  
		  this.myfile.close();
		  driver.switchTo().defaultContent();

	  }
	  
	  /**
	   * 文件的隐藏图标功能-重命名
	   */
	  @Test(alwaysRun=true)
	  @Parameters(value={"old_file_name","new_file_name"})
	  public void renameFileFromHidden(String old_file_name,String new_file_name){
		  
		  this.myfile.clickRenameFileHiddenBtn(old_file_name);
		  
		 this.myfile.inputRenameName(old_file_name, new_file_name);
		  
		  Wait.waitMilliSeconds(5000);
		  
		  this.myfile.renameConifrm(old_file_name);
		  
		  Assert.assertEquals(this.myfile.findFile(new_file_name).getText().trim(), new_file_name);
		  
		  driver.switchTo().defaultContent();
		  
	  }
	  
	  
	  /**
	   * 文件的隐藏图标功能-复制到
	   */
	  @Test(alwaysRun=true)
	  @Parameters({"file_name","filedir"})
	  public void copyToFileFromHidden(String file_name,String filedir){
		  
		  this.myfile.clickCopytoHiddenBtn(file_name);
		  
		 this.myfile.findDirectory(filedir);
		  
		 this.myfile.moveConfirm();
		  
		  WebDriverWait wait=new WebDriverWait(driver, 10);
		  Assert.assertTrue(wait.until(ExpectedConditions.textToBePresentInElement
				  (this.myfile.getSuccessTip(), "操作成功")));
		  Wait.waitMilliSeconds(5000);
		  
		  driver.switchTo().defaultContent();
		  
		  
		  
	  }
	  
	  /**
	   * 文件的隐藏图标功能-移动到
	   */
	  @Test(alwaysRun=true)
	  @Parameters({"file_name","filedir"})
	  public void moveToFileFromHidden(String file_name,String filedir){
		 
		  this.myfile.clickMovetoHiddenBtn(file_name);
		  
		  this.myfile.findDirectory(filedir);
		  
		  this.myfile.moveConfirm();
		  
		  WebDriverWait wait=new WebDriverWait(driver, 10);
		  Assert.assertTrue(wait.until(ExpectedConditions.textToBePresentInElement
				  (this.myfile.getSuccessTip(), "操作成功")));
		  Wait.waitMilliSeconds(5000);
		  driver.switchTo().defaultContent();
	  }
	  
	  /**
	   * 文件的隐藏图标功能-标签
	   */
	  @Test(alwaysRun=true)
	  @Parameters({"file_name","mark_name"})
	  public void markFileFromHidden(String file_name,String mark_name){
		 
		  this.myfile.clickMarkHiddenBtn(file_name);
		  
		  
		  //input标签输入框
		  this.myfile.inputMarkName(mark_name);
		  
		  this.myfile.saveMark();
		  
		  WebDriverWait wait=new WebDriverWait(driver, 10);
		  Assert.assertTrue(wait.until(ExpectedConditions.textToBePresentInElement
				  (this.myfile.getSuccessTip(), "操作成功")));
		  Wait.waitMilliSeconds(5000);
		  driver.switchTo().defaultContent();
		  
		  
	  }
	  
	  
	  /**
	   * 文件的隐藏图标功能-编辑  
	   */
	  @Test(alwaysRun=true)
	  @Parameters({"file_name","file_url"})
	  public void editFileFromHidden(String file_name,String file_url){
		 
		 driver=this.myfile.clickEditHiddenBtn(file_name); 
		  
		  /*Wait.waitMilliSeconds(5000);
		  driver.findElement(By.name("file")).sendKeys(file_url);*/
		  
		 this.myfile.uploadResource(file_url);
 
		  Wait.waitMilliSeconds(5000);
		  
		  this.myfile.confirmFileEdit();
		  
		  
		  Wait.waitMilliSeconds(5000);
		  Assert.assertEquals(this.myfile.getSuccessTip().getText(), "是否确认替换？");
		  Wait.waitMilliSeconds(5000);
		  
		  this.myfile.confirmReplace();
		  
		  
		  /**
		   * 该行断言有问题
		   */
		  /*WebDriverWait wait=new WebDriverWait(driver, 10);
		 
		  Assert.assertTrue(wait.until(ExpectedConditions.textToBePresentInElement
				  (driver.findElement(By.xpath("//div[@id='xubox_layer3']/div/div[1]/span[2]")), "上传成功1个")));
		  Wait.waitMilliSeconds(50000);*/
		  driver.switchTo().defaultContent();
		  
	  }
	  
	  
	  /**
	   * 文件的操作-顶部图标的存在性
	   */
/*	  @Test(alwaysRun=true)
	  @Parameters({"file_name"})
	  public void assertTopFileOperExist(String file_name){
		  
		  Wait.waitMilliSeconds(5000);
		  WebElement iframe=locateIframe("main");
		  driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+file_name+"']"+"//input[@type='checkbox']")).click();
		  
		  *//**
		   * 先验证顶部图标
		   *//*
		  Assert.assertTrue(elementExist(driver,By.xpath("//a[@class='download']/i")));
		  Assert.assertEquals(driver.findElement(By.xpath("//a[@class='download']/span")).getText(), "下载");
		  
		  
		  Assert.assertTrue(elementExist(driver,By.xpath("//a[@class='delete']/i")));
		  Assert.assertEquals(driver.findElement(By.xpath("//a[@class='delete']/span")).getText(), "删除");
		  
		  Assert.assertTrue(elementExist(driver,By.xpath("//a[@class='more-btn']/span[@class='ico-arrow']")));
		  Assert.assertEquals(driver.findElement(By.xpath("//a[@class='more-btn']/span[@class='btn-val']")).getText(), "更多");
		  
		  Actions action=new Actions(driver);
		  action.moveToElement(driver.findElement(By.xpath("//a[@class='more-btn']"))).perform();
		  Wait.waitMilliSeconds(5000);
		  
		  Assert.assertEquals(driver.findElement(By.xpath("//div[@class='menu']/a[@class='edit']")).getText(), "编辑");
		  Assert.assertEquals(driver.findElement(By.xpath("//div[@class='menu']/a[@class='rename']")).getText(), "重命名");
		  
		  Assert.assertEquals(driver.findElement(By.xpath("//div[@class='menu']/a[@class='copyTo']")).getText(), "复制到");
		  Assert.assertEquals(driver.findElement(By.xpath("//div[@class='menu']/a[@class='moveTo']")).getText(), "移动到");
		  Assert.assertEquals(driver.findElement(By.xpath("//div[@class='menu']/a[@class='mark']")).getText(), "标签");
		 
	  }*/
	  
	  /**
	   *  文件的操作-右键图标的存在性
	   * @param file_name
	   */
/*	  @Test(alwaysRun=true)
	  @Parameters({"file_name"})
	  public void assertRightButtonFileOperExist(String file_name){
		  Wait.waitMilliSeconds(5000);
		  WebElement iframe=locateIframe("main");
		  
		
		  Actions action=new Actions(driver);
		  action.contextClick(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+file_name+"']"))).perform();
		  Wait.waitMilliSeconds(5000);
		  
		  Assert.assertEquals(driver.findElement(By.xpath("//div[@id='myFileLi']/ul/li[@class='view']")).getText(), "预览");
		  Assert.assertEquals(driver.findElement(By.xpath("//div[@id='myFileLi']/ul/li[@class='edit']")).getText(), "编辑");
		  Assert.assertEquals(driver.findElement(By.xpath("//div[@id='myFileLi']/ul/li[@class='rename']")).getText(), "重命名");
		  Assert.assertEquals(driver.findElement(By.xpath("//div[@id='myFileLi']/ul/li[@class='download']")).getText(), "下载");
		  Assert.assertEquals(driver.findElement(By.xpath("//div[@id='myFileLi']/ul/li[@class='delete']")).getText(), "删除");
		  Assert.assertEquals(driver.findElement(By.xpath("//div[@id='myFileLi']/ul/li[@class='copyTo']")).getText(), "复制到");
		  Assert.assertEquals(driver.findElement(By.xpath("//div[@id='myFileLi']/ul/li[@class='moveTo']")).getText(), "移动到");
		  Assert.assertEquals(driver.findElement(By.xpath("//div[@id='myFileLi']/ul/li[@class='mark']")).getText(), "标签");
	  }*/
	  

}
