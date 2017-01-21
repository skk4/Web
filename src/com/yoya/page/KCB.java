package com.yoya.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.yoya.util.Wait;

public class KCB {
	private WebDriver driver;
	
	public KCB(WebDriver driver){
		this.driver=driver;
	}
	
	  
	  /**
	   * 返回创建的课程表
	   */
	  public WebDriver clickCreateClass(){
		  driver.findElement(By.id("my_create_class")).click();
		  Wait.waitMilliSeconds(5000);
		  return driver;
	  }
	  
	  /**
	   * 返回加入的课程班
	   */
	  public WebDriver clickJoinClass(){
		  driver.findElement(By.id("my_join_class")).click();
		  Wait.waitMilliSeconds(5000);
		  return driver;
	  }
	  
	  public void clickNewButton(){
		  driver.findElement(By.id("class_add_btn")).click();
		  Wait.waitMilliSeconds(5000);
	  }
	  
	  public void clickJoinButton(){

		  driver.findElement(By.id("join_class")).click();
		  Wait.waitMilliSeconds(5000);
	  }
	  
	  public void clickSearch(){
		  driver.findElement(By.id("search_btn")).click();
		  Wait.waitMilliSeconds(5000);
	  }
	  
	  public void clickJoinClassButton(){
		  driver.findElement(By.cssSelector("a.btn.btn-8")).click();
	  }
	  
	  public void clickRemoveRemember(WebElement target,String login_name){
		  Actions action=new Actions(driver);
		  action.moveToElement(target).perform();
		
		  target.findElement(By.xpath("./../../div[@class='content']/ul/li[1]//i")).click();
		  
		  Wait.waitMilliSeconds(5000);
		  
		  WebElement student=this.findStudent(driver, login_name);
		  student.findElement(By.xpath("./..//a[@title='移出班级']")).click();
		
		  Wait.waitMilliSeconds(5000);
	  }
	  
	  public void clickDeleteClass(String class_name){
		  Actions action=new Actions(driver);
		  action.moveToElement(this.findClass(driver, class_name)).perform();
		  Wait.waitMilliSeconds(5000);
		  
		  WebElement target=this.findClass(driver, class_name);
		  
		  target.findElement(By.xpath("./../..//li[@class='opts']/div/a[2]/i")).click();
		  
		  Wait.waitMilliSeconds(5000);
	  }
	  
	  public void clickeditClass(String class_name){
		  Actions action=new Actions(driver);
		  
		  WebElement target=this.findClass(driver, class_name);
		  
		  action.moveToElement(target).perform();
		  Wait.waitMilliSeconds(5000);
		  
		  target.findElement(By.xpath("./../..//li[@class='opts']/div/a[1]/i")).click();
		  
		  Wait.waitMilliSeconds(5000);
	  }
	  
	  
	  public WebElement findStudent(WebDriver driver,String student_name){
		  WebElement student=null;
		  List<WebElement> students=driver.findElements(By.xpath("//div[@id='student_table']//tr/td[1]"));
		  
		  for(WebElement s:students){
			  if(s.getText().equalsIgnoreCase(student_name)){
				  student=s;
				  break;
			  }
		  }
		  return student;
	  }
	  
	  
	  public void inputClassname(String class_name){
		  WebElement name=driver.findElement(By.id("class_name"));
		  name.clear();
		  name.sendKeys(class_name);
		  Wait.waitMilliSeconds(5000);
	  }
	  
	  
	  
	  public void inputinviteCode(String classcode){
		  WebElement joinCode=driver.findElement(By.id("invite_code"));
		  joinCode.clear();
		  joinCode.sendKeys(classcode);
	  }
	  
	  
	  public void confirm(){
		  driver.findElement(By.className("layui-layer-btn0")).click();
	  }
	  /**
	   * 通过班级名字定位某个班级
	   */
	  public WebElement findClass(WebDriver driver,String class_name){
		  List<WebElement> wes=driver.findElements(By.xpath("//ul[@id='class_list']/li//a[@class='title']"));
		  WebElement target=null;
		  for(WebElement ws:wes){
			  if(ws.getText().trim().equalsIgnoreCase(class_name)){
				  target=ws;
				  break;
			  }
		  }
		  return target;
	  }
	  
	  /**
	   * 查询班级的邀请码
	   * @param driver
	   * @param class_name
	   * @return
	   */
	  public String  queryClassCode(WebDriver driver,String class_name){
		  String classcode="";
		  driver.findElement(By.id("my_create_class")).click();
		  Wait.waitMilliSeconds(5000);
		  WebElement target=this.findClass(driver, class_name);
		  classcode=target.findElement(By.xpath("./../span/span[@class='code']")).getText();
		  return classcode.trim();
	  }
	  
	  public WebElement getTip(){
		  WebElement we=Wait.explicitlyWait(driver, 10, By.xpath("//div[starts-with(@id,'layui-layer')]/div"));
		  return we;
		  
	  }
	 
	  
}
