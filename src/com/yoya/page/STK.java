package com.yoya.page;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.AssertJUnit;

import com.yoya.util.LocateElement;
import com.yoya.util.Wait;

public class STK {
	private WebDriver driver;
	
	public STK(WebDriver driver){
		this.driver=driver;
	}
	
	
	
	public void clickclassifyManage(){
		 driver.findElement(By.id("go_sort_list_btn")).click();
		  Wait.waitMilliSeconds(5000);
	}
	
	public void clickNewClassifyButton(){
		String js="javascript:addClassify();";
		  ((JavascriptExecutor)driver).executeScript(js);
	}
	
	public void inputClassifyName(String fl1){
		 Wait.waitMilliSeconds(5000);
		  WebElement fl_name1=driver.findElement(By.id("classify_name"));
		  Wait.waitMilliSeconds(5000);
		  fl_name1.clear();
		  Wait.waitMilliSeconds(5000);
		  fl_name1.sendKeys(fl1);
		  Wait.waitMilliSeconds(5000);
	}
	
	public void inputTKname(String TK_name){
		 WebElement e0=driver.findElement(By.id("topic_warehouse_name"));
		  e0.clear();
		  e0.sendKeys(TK_name);
		  Wait.waitMilliSeconds(5000);
	}
	
	public void chooseTKtype(String TK_type){
		 driver.findElement(By.id("tk_type"));
		  Wait.waitMilliSeconds(5000);
		  
		  WebElement tktype=this.findClassify(driver, TK_type);
		  tktype.click();
	}
	
	
	  /**
	   * 创建题库的时候用于定位分类
	   * @param driver
	   * @param classify_name
	   * @return
	   */
	  public WebElement findClassify(WebDriver driver,String classify_name){
		  List<WebElement> wes=driver.findElements(By.xpath("//select[@id='tk_type']/option"));
		  WebElement target=null;
		  for(WebElement ws:wes){
			  if(ws.getText().trim().equalsIgnoreCase(classify_name)){
				  target=ws;
				  break;
			  }
		  }
		  return target;
	  }
	
	public void saveTkButton(){

		  WebElement pub_class=driver.findElement(By.id("pub_class"));
		  pub_class.click();
		  Wait.waitMilliSeconds(5000);
	}
	  
	  
	public void clickSave(){
		driver.findElement(By.id("savebtn")).click();
		  Wait.waitMilliSeconds(5000);
	}
	
	public WebElement getTip(){
		return driver.findElement(By.cssSelector("div.layui-layer-content.layui-layer-padding"));
	}
	
	public void confirm(){
		driver.findElement(By.className("layui-layer-btn0")).click();
	}
	
	public void clickeditClassifyButton(String classify){
		 driver.findElement(By.xpath("//div[@id='classify_list']//tbody/tr//a[@data-name='edit_classify' and @data-classify-name='"+classify+"']")).click();
		  Wait.waitMilliSeconds(5000);
	}
	
	public void clickdeleteClassifyButton(String classify_name){
		  driver.findElement(By.xpath("//div[@id='classify_list']//tbody/tr//a[@data-name='edit_classify' and @data-classify-name='"+classify_name+"']"+"/../a[@data-name='del_classify']/i[@title='删除']")).click();

	}
	
	public void clickNewTKButton(){
		driver.findElement(By.cssSelector("a.btn.btn-icon-text.active")).click();
		  Wait.waitMilliSeconds(5000);
		  WebElement iframe=driver.findElement(By.xpath("//iframe[starts-with(@id,'layui-layer-iframe')]"));
		  driver.switchTo().frame(iframe);
	}
	
	public WebDriver assertAlertTip(String expected){
			Alert alert=driver.switchTo().alert();
		  Wait.waitMilliSeconds(5000);
		  AssertJUnit.assertEquals(expected, alert.getText());
		  alert.accept();
		  Wait.waitMilliSeconds(5000);
		
		  driver.switchTo().defaultContent();
		  return driver;
	}
	
	public WebElement findTk(String tk_name){
		 WebElement target=LocateElement.locateElementUseText(driver, "//div[@id='test_list']//tbody/tr/td", tk_name);
		  Wait.waitMilliSeconds(5000);
		  return target;
	}
	
	public void clickTkPreviewButton(WebElement target){
		Actions action=new Actions(driver);
		  action.moveToElement(target.findElement(By.xpath("./../td//a[@data-name='preview_test']"))).perform();
		  Wait.waitMilliSeconds(5000);
		  target.findElement(By.xpath("./../td//a[@data-name='preview_test']")).click();
		  Wait.waitMilliSeconds(5000);
	}
	
	public WebDriver clickTkEditButton(WebElement target){
		 Actions action=new Actions(driver);
		  action.moveToElement(target.findElement(By.xpath("./../td//a[@data-name='edit_test']"))).perform();
		  target.findElement(By.xpath("./../td//a[@data-name='edit_test']")).click();
		  Wait.waitMilliSeconds(5000);
		  WebElement iframe=driver.findElement(By.xpath("//iframe[starts-with(@id,'layui-layer-iframe')]"));
		  driver.switchTo().frame(iframe);
		  return driver;
	}
	public void clickTkEnterButton(WebElement target){
		Actions action=new Actions(driver);
		  action.moveToElement(target.findElement(By.xpath("./../td//a[@data-name='enter_test']"))).perform();
		  target.findElement(By.xpath("./../td//a[@data-name='enter_test']")).click();
		  Wait.waitMilliSeconds(5000);
	}
	
	
	public void clickTkDeleteButton(WebElement target){
		Actions action=new Actions(driver);
		  action.moveToElement(target.findElement(By.xpath("./../td//a[@data-name='del_test']"))).perform();
		  target.findElement(By.xpath("./../td//a[@data-name='del_test']")).click();
		  Wait.waitMilliSeconds(5000);
	}
	
	
	public String findTkName(){
		return driver.findElement(By.id("tk_name")).getText();
	}
	
	
	public WebDriver enterTK(String tk_name){
		WebElement target=this.findTk(tk_name);
		this.clickTkEnterButton(target);
		return driver;
	}
	
	
}
