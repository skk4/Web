package com.yoya.page.yun;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.yoya.util.MyYunAssist;
import com.yoya.util.Wait;

public class MyYunMyMaterial {
	public WebDriver driver;
	
	public MyYunMyMaterial(WebDriver driver){
		this.driver=driver;
	}
	
	  /**
	   * 进入我的素材页面
	   */
	  public void enterMyMaterial() {
		  
		  driver.findElement(By.linkText("我的素材")).click();
		  WebElement iframe=locateIframe("main");
		//等待加载
		  Wait.waitMilliSeconds(10000);
	  }
	  
	  /**
	   * 定位iframe
	   * @param locate
	   * @return
	   */
	  public WebElement locateIframe(String locate){
		  return MyYunAssist.locateIframe(driver, locate);
	  }
	  
	  public By findBy(String file_name){
		  return By.xpath("//table[@id='file_list']//tr[@data-name='"+file_name+"']");
	  }
	  
	  
	  
	  public void findDirectory(String file_name){
		  driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+file_name+"']"+"/td[@class='t-filename']/a[@class='ico']")).click();
		  Wait.waitMilliSeconds(10000);
	  }
	  
	  public WebElement findSC(String file_name){
		  return driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+file_name+"']"));
	  }
	  
	  
	  public void clickUploadBtn(){
		  driver.findElement(By.id("upload_file")).click();
		  Wait.waitMilliSeconds(5000);
		  locateIframe("layui-layer-iframe");
		  Wait.waitMilliSeconds(5000);
	  }
	  
	  public void uploadFile(String material_url){
		  driver.findElement(By.name("file")).sendKeys(material_url);
		  Wait.waitMilliSeconds(5000);
	  }
	  
	  public void confirmUpload(){
		  driver.findElement(By.id("upload_file")).click();
	  }
	
	  public WebElement getTip(){
		  return driver.findElement(By.xpath("//*[starts-with(@id,'xubox_layer')]/div[1]/div/span[2]"));
	  }
	  
	
	  public void moveToSC(WebElement sc){
		  Actions action=new Actions(driver);
		  action.moveToElement(sc).perform();
		  Wait.waitMilliSeconds(2000);
	  }
	  
	  /**
	   * 下载按钮
	   */
	  public void clickDownloadBtn(WebElement sc){
		  
		  this.moveToSC(sc);
		  sc.findElement(By.xpath("./td[contains(@class,'t-opts')]/div/a[@class='download']")).click();
		  Wait.waitMilliSeconds(5000);
	  }
	  
	  /**
	   * 删除按钮
	   */
	  public void clickDeleteBtn(WebElement sc){
		  
		  this.moveToSC(sc);
		  sc.findElement(By.xpath("./td[contains(@class,'t-opts')]/div/a[@class='delete']")).click();
		  Wait.waitMilliSeconds(5000);
	  }
	  
	  /**
	   * 预览
	   */
	  public void clickPreviewBtn(WebElement sc){
		  
		  this.moveToSC(sc);
		  WebElement more=this.findMoreBtn(sc);
		  this.moveToSC(more);
		  Wait.waitMilliSeconds(2000);
		  
		  more.findElement(By.xpath("./../div[@class='menu']/a[@class='view']")).click();
		  Wait.waitMilliSeconds(5000);
	  }
	  
	  public WebElement findMoreBtn(WebElement sc){
		  return sc.findElement(By.xpath("./td[contains(@class,'t-opts')]/div//a[@class='more-btn']"));
	  }
	  /**
	   * 重命名

	   */
	  public WebElement clickRenameBtn(WebElement sc){
		  String file_name=sc.getAttribute("data-name");
		  this.moveToSC(sc);
		  WebElement more=this.findMoreBtn(sc);
		  this.moveToSC(more);
		  Wait.waitMilliSeconds(2000);
		  
		  more.findElement(By.xpath("./../div[@class='menu']/a[@class='rename']")).click();
		  
		  WebElement temp=this.findSC(file_name);
		  return temp.findElement(By.xpath("./td[@class='t-filename']/a/input"));
	  }
	  
	  /**
	   * 输入新的素材名
	   */
	  public void inputNewSCname(WebElement input,String new_scname){
		  input.clear();
		  input.sendKeys(new_scname);
		  
	  }
	
	  
	  /**
	   * 确认修改
	   */
	  public void clickConfirmBtn(String file_name){
		  WebElement temp=this.findSC(file_name);
		  temp.findElement(By.xpath("./td[@class='t-filename']/a/button[@class='sure']")).click();
	  }
	  
	  
	  /**
	   * 标签

	   */
	  public void clickRemarkBtn(WebElement sc){
		  
		  this.moveToSC(sc);
		  WebElement more=this.findMoreBtn(sc);
		  this.moveToSC(more);
		  Wait.waitMilliSeconds(2000);
		  
		  more.findElement(By.xpath("./../div[@class='menu']/a[@class='mark']")).click();
		  
	  }
	  
	  
	  
}
