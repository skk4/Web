package com.yoya.page.classes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.yoya.util.LocateElement;
import com.yoya.util.Wait;

public class Homework {
	private WebDriver driver;
	
	
	public Homework(WebDriver driver){
		this.driver=driver;
	}
	
	public void clickHomeworkBtn(String class_name){
		WebElement target=LocateElement.locateElementUseText(driver, "//ul[@id='class_list']/li/div/a", class_name);
		  Wait.waitMilliSeconds(5000);
		  //作业按钮
		  target.findElement(By.xpath("./../../div[@class='content']/ul/li[3]/a/div")).click();
		  Wait.waitMilliSeconds(5000);
	}
	
	public void randomImportQuestions(){
		driver.findElement(By.xpath("//div[@class='do_sth']/div[@key='select-tk']")).click();
		  Wait.waitMilliSeconds(5000);
	}
	public void pictureHomework(){
		driver.findElement(By.xpath("//div[@class='do_sth']/div[@key='upload-img']")).click();
		  Wait.waitMilliSeconds(5000);
	}
	
	
	public void inputHomeworkTitle(String homework_title){
		driver.findElement(By.id("task_title")).sendKeys(homework_title);
		  Wait.waitMilliSeconds(5000);
	}
	
	public void clickRandomBtn(){
		driver.findElement(By.xpath("//a[@class='to_select_tk']")).click();
		  Wait.waitMilliSeconds(5000);
	}
	
	public WebElement chooseTK(String tk_name){
		WebElement tk=LocateElement.locateElementUsePartialText(driver, "//tbody[@id='model_view_4_data']/tr/td//span[@class='content-title']", tk_name);
		  tk.click();
		  Wait.waitMilliSeconds(5000);
		  return tk;
	}
	
	public void chooseChapSectKnow(WebElement tk,String chapter_name,String section_name,String knowledge){
		WebElement chapter=tk.findElement(By.xpath("./../../div/span/select[@data-name='tk-chapter']"));
		  chapter.click();
		  Wait.waitMilliSeconds(5000);
		  chapter.findElement(By.xpath("./option[@data-name='"+chapter_name+"']")).click();
		  Wait.waitMilliSeconds(5000);
		  
		  
		//选择节
		  WebElement section=tk.findElement(By.xpath("./../../div/span/select[@data-name='tk-chapter-node']"));
		  //WebElement section=chapter.findElement(By.xpath("./../div/span/select[@data-name='tk-chapter-node']"));
		  section.click();
		  Wait.waitMilliSeconds(5000);
		  section.findElement(By.xpath("./option[@data-name='"+section_name+"']")).click();
		  Wait.waitMilliSeconds(5000);

		  //知识点的选择
		  WebElement knows=tk.findElement(By.xpath("./../../div/span/select[@data-name='tk-chapter-node-know']"));
		  //WebElement knows=section.findElement(By.xpath("./../div/span/select[@data-name='tk-chapter-node-know']"));
		  knows.click();
		  Actions action=new Actions(driver);
		  WebElement b=knows.findElement(By.xpath("./option[@data-name='"+knowledge+"']"));
		  action.moveToElement(b).perform();
		  Wait.waitMilliSeconds(5000);
		  b.click();
		  Wait.waitMilliSeconds(5000);
	}
	
	public void inputQuestionsNumber(WebElement tk,String number){
		 WebElement ts=tk.findElement(By.xpath("./../../div/span/input"));
		  ts.clear();
		  ts.sendKeys(number);
		  Wait.waitMilliSeconds(5000); 
	}
	
	public void confirm(){
		driver.findElement(By.xpath(".//*[@id='apDiv4']/div[@class='xz_qd']/a")).click();
		  
		  Wait.waitMilliSeconds(5000);
	}
	
	public WebElement getTKview(){
		return driver.findElement(By.xpath("//div[@id='show_tk_view']/span"));
	}
	public WebElement getWordView(){
		return driver.findElement(By.id("show_word_view"));
	}
	
	public void releaseHomework(){
		driver.findElement(By.id("btn_release")).click();
		  Wait.waitMilliSeconds(5000);
	}
	
	
	public void uploadpicture(String picture_url){
		driver.findElement(By.xpath("//ul[@id='img_view']/../li[@class='add_tp']/input[@type='file']")).sendKeys(picture_url);
		  Wait.waitMilliSeconds(5000);
	}
	public void uploadword(String word_url){
		 driver.findElement(By.name("question_file")).sendKeys(word_url);
		  Wait.waitMilliSeconds(5000);
	}
	
	public void createQuestion(){
		driver.findElement(By.xpath("//span[@class='answer_view']/input[1]")).click();
		  Wait.waitMilliSeconds(5000);
	}
	
	public void importWord(){
		driver.findElement(By.xpath("//div[@class='do_sth']/div[@key='upload-word']")).click();
		  Wait.waitMilliSeconds(5000);
	}
	
}
