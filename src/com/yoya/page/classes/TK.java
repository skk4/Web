package com.yoya.page.classes;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.AssertJUnit;

import com.yoya.util.LocateElement;
import com.yoya.util.LocateIframe;
import com.yoya.util.Wait;

public class TK {
	private WebDriver driver;
	
	public TK(WebDriver driver){
		this.driver=driver;
	}
	
	public WebDriver addChapter(String old_chapter_name){
		  Actions action=new Actions(driver);
		  action.moveToElement(driver.findElement(By.xpath("//ul[@id='chapterlist']/li/div[@title='"+old_chapter_name+"']"))).perform();
		  Wait.waitMilliSeconds(5000);
		  driver.findElement(By.xpath("//ul[@id='chapterlist']/li/div[@title='"+old_chapter_name+"']"+"/.."+"//span[contains(@onclick,'chapter')]")).click();
		  Wait.waitMilliSeconds(5000);
		  WebElement iframe=driver.findElement(By.xpath("//iframe[starts-with(@id,'xubox_iframe')]"));
		  driver.switchTo().frame(iframe);
		  return driver;
	}
	
	
	//
	public WebDriver addSectionUnderChap(String chapter_name){
/*		 Actions action=new Actions(driver);
		  action.moveToElement(driver.findElement(By.xpath("//ul[@id='chapterlist']/li/div/span[text()='"+chapter_name+"']"))).perform();
*/		  System.out.println("显示章节编辑菜单");
		  Wait.waitMilliSeconds(5000);
		  
		  JavascriptExecutor  js = (JavascriptExecutor)driver;

		  WebElement element = driver.findElement(By.xpath("//ul[@id='chapterlist']/li/div/span[text()='"+chapter_name+"']"+"/../.."+"//div[contains(@class,'sets')]"));

		  js.executeScript("arguments[0].style=arguments[1]",element,"display: block;");
		  
		  Wait.waitMilliSeconds(5000);
		  
		  
		  driver.findElement(By.xpath("//ul[@id='chapterlist']/li/div/span[text()='"+chapter_name+"']"+"/../.."+"//span[contains(@onclick,'node')]")).click();
		  
		  
		  
		  System.out.println("这行显示出来");
		  Wait.waitMilliSeconds(10000);
		  
		  WebElement iframe=driver.findElement(By.xpath("//div[starts-with(@id,'xubox_layer') and @type='iframe']/div[@class='xubox_main']/iframe[starts-with(@id,'xubox_iframe')]"));
		  driver=driver.switchTo().frame(iframe);
		  return driver;
	}
	
	public WebDriver addSectionUnderSect(String old_chapter_name,String old_section_name){
		 Actions action=new Actions(driver);
		  action.moveToElement(driver.findElement(By.xpath("//ul[@id='chapterlist']/li[@data-chapter_name='"+old_chapter_name+"']"+"/div[@title='"+old_section_name+"']"))).perform();
		  Wait.waitMilliSeconds(5000);
		  
		  driver.findElement(By.xpath("//ul[@id='chapterlist']/li[@data-chapter_name='"+old_chapter_name+"']"+"/div[@title='"+old_section_name+"']"+"/.."+"//i[@class='icon-plus']")).click();
		  Wait.waitMilliSeconds(5000);
		  
		  WebElement iframe=driver.findElement(By.xpath("//iframe[starts-with(@id,'xubox_iframe')]"));
		  driver.switchTo().frame(iframe);
		  return driver;
	}
	
	public WebDriver editSection(String chapter_name,String old_section_name){
		 Actions action=new Actions(driver);
		  WebElement e=driver.findElement(By.xpath("//ul[@id='chapterlist']/li[@data-chapter_name='"+chapter_name+"']"+"/div[@title='"+old_section_name+"']"));
		  action.moveToElement(e).perform();
		  Wait.waitMilliSeconds(5000);
		  driver.findElement(By.xpath("//ul[@id='chapterlist']/li[@data-chapter_name='"+chapter_name+"']"+"/div[@title='"+old_section_name+"']"+"/.."+"//i[@class='icon-edit']")).click();
		  Wait.waitMilliSeconds(5000);
		  
		  WebElement iframe=driver.findElement(By.xpath("//iframe[starts-with(@id,'xubox_iframe')]"));
		  driver.switchTo().frame(iframe);
		  return driver;
	}
	
	public WebDriver deleteSection(String chapter_name,String section_name){
		  Actions action=new Actions(driver);
		  WebElement e=driver.findElement(By.xpath("//ul[@id='chapterlist']/li[@data-chapter_name='"+chapter_name+"']"+"/div[@title='"+section_name+"']"));
		  action.moveToElement(e).perform();
		  Wait.waitMilliSeconds(3000);
		  driver.findElement(By.xpath("//ul[@id='chapterlist']/li[@data-chapter_name='"+chapter_name+"']"+"/div[@title='"+section_name+"']"+"/.."+"//i[@class='icon-trash']")).click();
		  Wait.waitMilliSeconds(5000);
		  return driver;
	}
	
	public WebDriver deleteChapter(String chapter_name){
		 Actions action=new Actions(driver);
		  
		  action.moveToElement(driver.findElement(By.xpath("//ul[@id='chapterlist']/li/div[@title='"+chapter_name+"']"))).perform();
		  Wait.waitMilliSeconds(5000);  
		  driver.findElement(By.xpath("//ul[@id='chapterlist']/li/div[@title='"+chapter_name+"']"+"/.."+"//i[@class='icon-trash']")).click();
		  Wait.waitMilliSeconds(5000);  
		  return driver;
	}
	
	public WebDriver editChapter(String old_chapter_name){
		Actions action=new Actions(driver);
		   
		  action.moveToElement(driver.findElement(By.xpath("//ul[@id='chapterlist']/li/div[@title='"+old_chapter_name+"']"))).perform();
		  Wait.waitMilliSeconds(5000);  
		  driver.findElement(By.xpath("//ul[@id='chapterlist']/li/div[@title='"+old_chapter_name+"']"+"/.."+"//i[@class='icon-edit']")).click();
		  Wait.waitMilliSeconds(5000); 
		  
		  WebElement iframe=driver.findElement(By.xpath("//iframe[starts-with(@id,'xubox_iframe')]"));
		  driver.switchTo().frame(iframe);
		  return driver;
	}
	
	
	
	public WebDriver clickAddFirstChapterBtn(){
		driver.findElement(By.className("btn-a")).click();
		  Wait.waitMilliSeconds(5000);
		  return this.switchToIframe();
	}
	
	public WebDriver switchToIframe(){
		WebElement iframe=driver.findElement(By.xpath("//iframe[starts-with(@id,'xubox_iframe')]"));
		  driver.switchTo().frame(iframe);
		  return driver;
	}
	
	public void inputChapterName(String chapter_name){
		WebElement we1=driver.findElement(By.id("content_name"));
		  we1.clear();
		  Wait.waitMilliSeconds(5000);
		  we1.sendKeys(chapter_name);
		  Wait.waitMilliSeconds(5000);
	}
	
	public void inputSectionName(String section_name){
		 WebElement we1=driver.findElement(By.id("content_name"));
		  we1.clear();
		  Wait.waitMilliSeconds(5000);
		  we1.sendKeys(section_name);
		  Wait.waitMilliSeconds(5000);
	}
	public void inputKnowName(String knowledge){
		 WebElement knows=driver.findElement(By.id("content_name"));
		  knows.clear();
		  Wait.waitMilliSeconds(5000);
		  knows.sendKeys(knowledge);
		  Wait.waitMilliSeconds(5000);
	}
	
	
	public void confirm(){
		 driver.findElement(By.id("pub_class")).click();
		  Wait.waitMilliSeconds(5000);
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
	
	
	public WebDriver clickImportChapButton(){
		
		
		  driver.findElement(By.linkText("导入章节")).click();
		  Wait.waitMilliSeconds(5000);
		  
		  WebElement iframe=driver.findElement(By.xpath("//iframe[starts-with(@id,'xubox_iframe')]"));
		  driver.switchTo().frame(iframe);
		  return driver;
	}
	
	public WebDriver clickImportQuestionButton(){
		driver.findElement(By.linkText("导入题目")).click();
		  Wait.waitMilliSeconds(5000);
		  
		  WebElement iframe=driver.findElement(By.xpath("//iframe[starts-with(@id,'xubox_iframe')]"));
		  driver.switchTo().frame(iframe);
		  return driver;
	}
	
	public void uploadfile(String file_url){
		WebElement file=driver.findElement(By.id("fileWord"));
		  file.sendKeys(file_url);
		  
		  Wait.waitMilliSeconds(5000);
	}
	
	public void uploadQuestionfile(String questions_url){
		driver.findElement(By.id("file")).sendKeys(questions_url);
		  Wait.waitMilliSeconds(5000);
	}
	
	
	public void confirmUpload(){
		driver.findElement(By.className("mb-jx")).click();
		  Wait.waitMilliSeconds(10000);
	}
	
	public WebElement getTip(){
		Wait.waitMilliSeconds(5000);
		return driver.findElement(By.xpath("//div[@id='chapterlist']/div"));
	}
	
	public WebElement findSection(String chapter_name,String section_name){
		WebElement target=driver.findElement(By.xpath("//ul[@id='chapterlist']/li[@data-chapter_name='"+chapter_name+"']"+"/div[@title='"+section_name+"']"));
		target.click();
		  Wait.waitMilliSeconds(5000);
		  return target;
	}
	
	public void clickknowledgesManageButton(){
		driver.findElement(By.xpath("//ul[@id='know_list']/li/a")).click();
		  Wait.waitMilliSeconds(5000);
	
	}

	public WebDriver addknowledges(){
		driver.findElement(By.xpath("//div[@id='knowList']//img[contains(@src,'add_white.png')]")).click();
		  Wait.waitMilliSeconds(5000);
		  Wait.waitMilliSeconds(5000);
		  
		  driver=LocateIframe.locateIframe(driver, "xubox_iframe");
		  return driver;
	}
	
	  /**
	   * 题目列表
	   */
	  public void questionsList(){
		  driver.findElement(By.cssSelector("div.img.img2")).click();
		  Wait.waitMilliSeconds(5000);
	  }

	  /**
	   * 知识点管理
	   */
	  public void knowledgeManager(){
		  driver.findElement(By.cssSelector("div.img.img1")).click();
		  Wait.waitMilliSeconds(5000);
	  }
	  
	  public WebElement findKnowledge(String knowledge){
		  Wait.waitMilliSeconds(5000);
		  WebElement target=LocateElement.locateElementUseText(driver, "//div[@id='knowList']/ul/li[3]/div", knowledge);
		  return target;
	  }
	  
	  public void chooseKnowledge(String knowledge){
		  WebElement knows=LocateElement.locateElementUseText(driver, "//ul[@id='know_list']/li", knowledge);
		  knows.click();
		  Wait.waitMilliSeconds(5000);
	  }
	  public void chooseQuesType(String question_type){
		  WebElement types=LocateElement.locateElementUseText(driver, "//ul[@id='type_list']/li", question_type);
		  types.click();
		  Wait.waitMilliSeconds(5000);
	  }
	  
	  
	  
	  public WebDriver editKnowledge(WebElement target){
		  target.findElement(By.xpath("./../small/a/i[@class='icon-edit']")).click();
		  Wait.waitMilliSeconds(5000);
		  
		  driver=LocateIframe.locateIframe(driver, "xubox_iframe");
		  return driver;
	  }
	  
	  public WebElement findQuestion(String question_title){
		  WebElement title=LocateElement.locateElementUsePartialText(driver, 
				  "//div[@id='question_list']/div[@class='tm_tk']/div[@class='tk-content']/div[1]", question_title);
		  return title;
	  }
	  
	  public WebDriver editQuestion(WebElement target){
		  target.findElement(By.xpath("./../../div[@class='title']//i[@class='icon-edit']")).click();
		  Wait.waitMilliSeconds(5000);
		  //定位修改题目的框
		  driver=LocateIframe.locateIframe(driver, "xubox_iframe");
		  return driver;
	  }
	  
	  public void clickCheckedBox(){
		  driver.findElement(By.id("boxbtn")).click();
		  Wait.waitMilliSeconds(5000);
		  
	  }
	  
	  public void specifyChapter(String chapter_name){
		  WebElement chapter=driver.findElement(By.id("chapter_id"));
		  chapter.findElement(By.xpath("./option[text()='"+chapter_name+"']")).click();
		  Wait.waitMilliSeconds(5000);
	  }
	  
	  public void specifySection(String section_name){
		  
		  WebElement node=driver.findElement(By.id("node_id"));
		  node.findElement(By.xpath("./option[text()='"+section_name+"']")).click();
		  Wait.waitMilliSeconds(5000);
		  
	  }
	  
	  public void specifyKnow(String knowledge){
		 
		  WebElement know=driver.findElement(By.id("knowledge_id"));
		  know.findElement(By.xpath("./option[text()='"+knowledge+"']")).click();
		  Wait.waitMilliSeconds(5000);
		  
	  }
	  
	  public void deleteQuestion(String question_title){
		  WebElement title=LocateElement.locateElementUsePartialText(driver, "//div[@id='question_list']/div[@class='tm_tk']/div[@class='tk-content']/div[1]", question_title);
		  
		  title.findElement(By.xpath("./../../div[@class='title']//i[@class='icon-trash']")).click();
		  Wait.waitMilliSeconds(5000);
	  }

	  public void deleteKnowledge(WebElement target){
		  target.findElement(By.xpath("./../small/a/i[@class='icon-trash']")).click();
		  Wait.waitMilliSeconds(5000);
	  }
	  
	  public WebDriver clickNewQuestionButton(){
		  driver.findElement(By.xpath("//div[@id='qcontent']//a")).click();
		  Wait.waitMilliSeconds(5000);
		  //定位创建题目的框
		  driver=LocateIframe.locateIframe(driver, "xubox_iframe");
		  return driver;
	  }
	 
	
	

}
