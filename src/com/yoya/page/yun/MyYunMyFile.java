package com.yoya.page.yun;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.yoya.util.MyYunAssist;
import com.yoya.util.Wait;

public class MyYunMyFile {
	
	private WebDriver driver;
	
	public MyYunMyFile(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public WebElement getLogoText(){
		return driver.findElement(By.className("logo-text"));
	}
	
	  /**
	   * 帮助定位iframe框架
	   * @param locate
	   * @return
	   */
	  public WebElement locateIframe(String locate){
		  return MyYunAssist.locateIframe(driver, locate);
	  }
	  
	  public void clickNewDirectoryButton(){
		  locateIframe("main");
		  driver.findElement(By.id("new_folder")).click();
	  }
	  
	  public void inputDirectoryName(String filedir_name){
		  WebElement file_name=driver.findElement(By.xpath("//input[@class='layui-layer-input']"));
		  file_name.clear();
		  Wait.waitMilliSeconds(5000);
		  file_name.sendKeys(filedir_name);
		  Wait.waitMilliSeconds(5000);
	  }
	  
	  public void inputRenameName(String old_filedir_name,String new_filedir_name){
		  
		  WebElement we=driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+old_filedir_name+"']"+"/td[@class='t-filename']/a/input"));
		  Wait.waitMilliSeconds(5000);
		  we.clear();
		  we.sendKeys(new_filedir_name);
	  }
	  
	  public void inputMarkName(String mark_name){
		  WebElement markinput=driver.findElement(By.id("tags_2_tag"));
		  markinput.clear();
		  Wait.waitMilliSeconds(5000);
		  markinput.sendKeys(mark_name);
	  }
	  
	  public void saveMark(){
		  driver.findElement(By.id("saveLabel")).click();
	  }
	  
	  public void renameConifrm(String old_filedir_name){
		  driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+old_filedir_name+"']"+"//button[@class='sure']")).click();
			
	  }
	  public void confirm(){
		  driver.findElement(By.className("layui-layer-btn0")).click();
		  Wait.waitMilliSeconds(5000);
	  }
	
	  public WebDriver clickCopytoHiddenBtn(String file_name){
		  Wait.waitMilliSeconds(5000);
		  WebElement iframe=locateIframe("main");		 
		  Actions action=new Actions(driver);
		  action.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+file_name+"']"))).perform();
		  Actions action1=new Actions(driver);
		  action1.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+file_name+"']"+"/td//span[@class='btn-val']"))).perform();		  
		  Actions action2=new Actions(driver);
		  action2.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+file_name+"']"+"/td//a[@class='copyTo']"))).perform();
		  Wait.waitMilliSeconds(5000);
		  
		  driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+file_name+"']"+"/td//a[@class='copyTo']")).click();
		  
		  WebElement iframe1=driver.findElement(By.xpath("//iframe[starts-with(@id,'layui-layer-iframe')]"));
		  driver.switchTo().frame(iframe1);	
		  return driver;
	  }
	  
	  public void moveConfirm(){
		  driver.findElement(By.id("move_file")).click();
	  }
	  
	  public WebElement findFile(String filedir_name){
		  return driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+filedir_name+"']"+"/td[@class='t-filename']/a/span[@class='name']"));  				
	  }
	  
	  
	  public void findDirectory(String filedir){
		  driver.findElement(By.id("tree_1_switch")).click();
		  Wait.waitMilliSeconds(5000);
		  
		  driver.findElement(By.xpath("//div[@id='tree_container']//ul[@id='tree_1_ul']/li/a[@title='"+filedir+"']")).click();
		  Wait.waitMilliSeconds(2000);
		  
		  
	  }
	  
	  public void clickDeleteDirectoryTopButton(String filedir_name_delete){
		  Wait.waitMilliSeconds(5000);
		  WebElement iframe=locateIframe("main");
		  
		  driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+filedir_name_delete+"']"+"//input[@type='checkbox']")).click();
		  driver.findElement(By.cssSelector("i.icon.icon-a-del")).click();
		  Wait.waitMilliSeconds(5000);
	  }
	  
	  public void clickDeleteDiretHiddenButton(String filedir_name_delete){
		  Wait.waitMilliSeconds(5000);
		  WebElement iframe=locateIframe("main");
		  Actions action=new Actions(driver);
		  action.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+filedir_name_delete+"']"))).perform();
		  
		  Wait.waitMilliSeconds(5000);
		  
		  driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+filedir_name_delete+"']"+"//a[@class='delete']")).click();
	  }
	  
	  public WebDriver clickEditHiddenBtn(String file_name){
		  Wait.waitMilliSeconds(5000);
		  WebElement iframe=locateIframe("main");		 
		  Actions action=new Actions(driver);
		  action.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+file_name+"']"))).perform();
		  Actions action1=new Actions(driver);
		  action1.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+file_name+"']"+"/td//span[@class='btn-val']"))).perform();		  
		  Actions action2=new Actions(driver);
		  action2.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+file_name+"']"+"/td//a[@class='edit']"))).perform();
		  Wait.waitMilliSeconds(5000);
		  
		  driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+file_name+"']"+"/td//a[@class='edit']")).click();
		
		  WebElement iframe1=driver.findElement(By.xpath("//iframe[starts-with(@id,'layui-layer-iframe')]"));
		  driver.switchTo().frame(iframe1);	
		  
		  return driver;
	  }
	  
	  public void clickRenameDiretHiddenBtn(String old_filedir_name,String new_filedir_name){
		  Wait.waitMilliSeconds(5000);
		  WebElement iframe=locateIframe("main");
		 
		  Actions action=new Actions(driver);
		  action.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+old_filedir_name+"']"))).perform();

		  Actions action1=new Actions(driver);
		  action1.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+old_filedir_name+"']"+"/td//span[@class='btn-val']"))).perform();
		  
		  Actions action2=new Actions(driver);
		  action2.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+old_filedir_name+"']"+"/td//a[@class='rename']"))).perform();
		
		  
		  Wait.waitMilliSeconds(5000);
		  
		  driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+old_filedir_name+"']"+"/td//a[@class='rename']")).click();
	  }
	  
	  public void clickUploadButton(){
		  
		  locateIframe("main");
		  Wait.waitMilliSeconds(5000);
		  driver.findElement(By.id("upload_file")).click();
		  Wait.waitMilliSeconds(5000);
		  locateIframe("layui-layer-iframe");
		  Wait.waitMilliSeconds(5000);
		  
	  }
	  public void confirmFileEdit(){
		  String js="document.getElementById('agree-checkbox').style.display='block';";
		  JavascriptExecutor jse = (JavascriptExecutor)driver; 
		  jse.executeScript(js);
		  driver.findElement(By.id("agree-checkbox")).click();
		  Wait.waitMilliSeconds(5000);
		  driver.findElement(By.id("upload_file")).click();
	  }
	  
	  public void confirmReplace(){
		  driver.findElement(By.cssSelector("a.xubox_yes.xubox_botton2")).click();
	  }
	  
	  public void clickDownloadHiddenBtn(String file_name){
		  Wait.waitMilliSeconds(5000);
		  WebElement iframe=locateIframe("main");
		  
		  Actions action=new Actions(driver);
		  action.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+file_name+"']"+"/td//a[@class='download']"))).perform();
		  Wait.waitMilliSeconds(5000);
		  driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+file_name+"']"+"/td//a[@class='download']")).click();

	  }
	  
	  public void clickPreviewFileHiddenBtn(String file_name){
		  Wait.waitMilliSeconds(5000);
		  WebElement iframe=locateIframe("main");
		 
		  Actions action=new Actions(driver);
		  action.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+file_name+"']"))).perform();

		  Actions action1=new Actions(driver);
		  action1.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+file_name+"']"+"/td//span[@class='btn-val']"))).perform();
		  
		  Actions action2=new Actions(driver);
		  action2.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+file_name+"']"+"/td//a[@class='view']"))).perform();
		  
		  
		  Wait.waitMilliSeconds(5000);
		  
		  driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+file_name+"']"+"/td//a[@class='view']")).click();
		  
	  }
	  
	  
	  public void clickDeleteFileHiddenBtn(String file_name_delete){
		  Wait.waitMilliSeconds(5000);
		  WebElement iframe=locateIframe("main");
		  Actions action=new Actions(driver);
		  action.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+file_name_delete+"']"))).perform();
		  
		  Wait.waitMilliSeconds(5000);
		  
		  driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+file_name_delete+"']"+"//a[@class='delete']")).click();
	  }
	  
	  public void clickRenameFileHiddenBtn(String old_file_name){
		  Wait.waitMilliSeconds(5000);
		  WebElement iframe=locateIframe("main");
		 
		  Actions action=new Actions(driver);
		  action.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+old_file_name+"']"))).perform();

		  Actions action1=new Actions(driver);
		  action1.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+old_file_name+"']"+"/td//span[@class='btn-val']"))).perform();
		  
		  Actions action2=new Actions(driver);
		  action2.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+old_file_name+"']"+"/td//a[@class='rename']"))).perform();
		  
		  
		  Wait.waitMilliSeconds(5000);
		  
		  driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+old_file_name+"']"+"/td//a[@class='rename']")).click();
		  
		  
		  
	  }
	  
	  
	  public WebDriver clickMovetoHiddenBtn(String file_name){
		  Wait.waitMilliSeconds(5000);
		  WebElement iframe=locateIframe("main");		 
		  Actions action=new Actions(driver);
		  action.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+file_name+"']"))).perform();
		  Actions action1=new Actions(driver);
		  action1.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+file_name+"']"+"/td//span[@class='btn-val']"))).perform();		  
		  Actions action2=new Actions(driver);
		  action2.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+file_name+"']"+"/td//a[@class='moveTo']"))).perform();
		  Wait.waitMilliSeconds(5000);
		  
		  driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+file_name+"']"+"/td//a[@class='moveTo']")).click();
		  
		  WebElement iframe1=driver.findElement(By.xpath("//iframe[starts-with(@id,'layui-layer-iframe')]"));
		  driver.switchTo().frame(iframe1);	
		  return driver;
	  }
	  
	  public WebDriver clickMarkHiddenBtn(String file_name){
		  Wait.waitMilliSeconds(5000);
		  WebElement iframe=locateIframe("main");
		  Actions action=new Actions(driver);
		  action.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+file_name+"']"))).perform();
		  Actions action1=new Actions(driver);
		  action1.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+file_name+"']"+"/td//span[@class='btn-val']"))).perform();		  
		  Actions action2=new Actions(driver);
		  action2.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+file_name+"']"+"/td//a[@class='mark']"))).perform();
		  Wait.waitMilliSeconds(5000);
		  
		  driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+file_name+"']"+"/td//a[@class='mark']")).click();
		  
		  WebElement iframe1=driver.findElement(By.xpath("//iframe[starts-with(@id,'layui-layer-iframe')]"));
		  driver.switchTo().frame(iframe1);	
		  return driver;
	  }
	  
	  
	  public void uploadResource(String resource_url){
		  driver.findElement(By.xpath("//input[@name='file']")).sendKeys(resource_url);
		  Wait.waitMilliSeconds(5000);
	  }
	  public void uploadConfirm(){
		  driver.findElement(By.id("upload_file")).click();
		  Wait.waitMilliSeconds(10000);
	  }
	  
	  public void close(){
		   driver.findElement(By.xpath("//div[starts-with(@id,layui-layer)]"+"/span/a")).click();
	  }
	  
	  public WebElement getTip(){
		  return driver.findElement(By.className("layui-layer-content"));
	  }
	  
	  public WebElement getSuccessTip(){
		  return driver.findElement(By.xpath("//*[@id=\"xubox_layer1\"]/div[1]/div/span[2]"));
		 
	  }
	  
	  /**
	   * 列表形式查看
	   */
	  @Test(alwaysRun=true)
	  public void listForm(){
		  locateIframe("main");
		  Wait.waitMilliSeconds(5000);
		  driver.findElement(By.cssSelector("i.icon.icon-listlist")).click();
		  driver.switchTo().defaultContent();
		  
	  }
	  /**
	   * 平铺形式
	   */
	  @Test(alwaysRun=true)
	  public void viewForm(){
		  locateIframe("main");
		  Wait.waitMilliSeconds(5000);
		  driver.findElement(By.cssSelector("i.icon.icon-viewlist")).click();
		  driver.switchTo().defaultContent();
	  }
	  
	  
	  

}
