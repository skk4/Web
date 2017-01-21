package com.yoya.test.classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yoya.page.AssertAlertTip;
import com.yoya.page.KCB;
import com.yoya.page.MyYoya;
import com.yoya.page.classes.Homework;
import com.yoya.test.login.LoginTest;
import com.yoya.util.Wait;


/**
 * 课程班作业
 * @author Administrator
 *
 */
public class HomeworkTest {
	
	 public WebDriver driver;
	 private Homework homework;
	 @Parameters({"web_driver","host_url","user_name","pass_word"})
	 @BeforeTest
	 public void beforeTest(String web_driver,String host_url,String login_username,String old_p) {
		  	LoginTest lg=new LoginTest();
		  	driver=lg.login(web_driver, host_url, login_username, old_p);
		  	
		  	MyYoya yoya=new MyYoya(driver);
		  	driver=yoya.enterKCB();
		  	
		  	//进入已创建的课程班
		  	KCB kcb=new KCB(driver);
		  	driver=kcb.clickCreateClass();
		  	
		  	this.homework=new Homework(driver);
		  	
		  	
	  }

	 @AfterTest
	 public void afterTest() {
		  Wait.waitMilliSeconds(5000);
		  driver.quit();
	  }
  
  
  
  /**
   * 创建课程班-作业-随机抽题
   */
  @Test(alwaysRun=true)
  @Parameters({"class_name","homework_title","tk_name","chapter_name","section_name","knowledge","number"})
  public void createdClasshomeworkSJCT(String class_name,String homework_title,String tk_name,String chapter_name,String section_name,String knowledge,String number){
	  

	  //进入作业
	  this.homework.clickHomeworkBtn(class_name);
	  
	  
	  //随机抽题
	  this.homework.randomImportQuestions();
	  
	//输入作业标题
	  this.homework.inputHomeworkTitle(homework_title);
	  
	  //随机抽题按钮
	  this.homework.clickRandomBtn();
	  
	  //选择题库
	  WebElement tk=this.homework.chooseTK(tk_name);
	  
	  //选择章
	  this.homework.chooseChapSectKnow(tk, chapter_name, section_name, knowledge);
	  
	  
	//抽题数
	 this.homework.inputQuestionsNumber(tk, number);
	  
	  //确定
	 this.homework.confirm(); 
	 
	  //WebElement a=Wait.explicitlyWait(driver, 10, By.xpath("//div[@id='show_tk_view']/span"));
	  
	  Assert.assertEquals(this.homework.getTKview().getText(), "您选择了题库【"+tk_name+"】，并在题库目录中的 \""+chapter_name+ "/ "+section_name+"/ "+ knowledge+"\"中抽了"+knowledge+"题。");
	  
	  
	  //发布作业
	  AssertAlertTip tip=new AssertAlertTip(driver);
	  tip.assertAlertTip("是不是确定立即发布作业《"+homework_title+"》？提交之后不可修改！");	  
	  //Wait.explicitlyWait(driver, 15, By.xpath(".//*[@id='show_task_div']/div[@class='task-list-one']/div[@class='title_h']"));  
  }
  

  /**
   * 创建课程班-作业-图片作业
   */
  @Test(alwaysRun=true)
  @Parameters({"class_name","homework_title","picture_url"})
  public void createdClasshomeworkTPZY(String class_name,String homework_title,String picture_url){
	  
	  //进入课程班
	  
	  //进入作业
	  this.homework.clickHomeworkBtn(class_name);
	  
	//图片作业
	  this.homework.pictureHomework();
	  
	//输入作业标题
	  this.homework.inputHomeworkTitle(homework_title);

	  //添加图片
	  this.homework.uploadpicture(picture_url);
	  
	  //默认为单选题，设置答案
	  this.homework.createQuestion();
	  
	  //发布作业
	  this.homework.releaseHomework();
	  
	  AssertAlertTip tip=new AssertAlertTip(driver);
	  tip.assertAlertTip("是不是确定立即发布作业《"+homework_title+"》？提交之后不可修改！");	  
  }
  
  
  /**
   * 创建课程班-作业-导入word
   */
  @Test(alwaysRun=true)
  @Parameters({"class_name","homework_title","word_url","word_name"})
  public void createdClasshomeworkDL(String class_name,String homework_title,String word_url,String word_name){

	  //进入课程班
	  
	  //进入作业
	 this.homework.clickHomeworkBtn(class_name);
	  
	  //导入word
	  this.homework.importWord();
	  
	  
	  //输入作业标题
	  this.homework.inputHomeworkTitle(homework_title);
	  
	  //导入文件
	 this.homework.uploadword(word_url);
	  
	  Assert.assertEquals(this.homework.getWordView().getText(), "【"+word_name+"】");
	  
	  //发布作业
	 this.homework.releaseHomework();
	 
	 AssertAlertTip tip=new AssertAlertTip(driver);
	 tip.assertAlertTip("是不是确定立即发布作业《"+homework_title+"》？提交之后不可修改！");	  
/*	  WebElement title=Wait.explicitlyWait(driver, 15, By.xpath(".//*[@id='show_task_div']/div[@class='task-list-one']/div[@class='task-list-contents']"));
	  Assert.assertEquals(title.getText().trim(), "【"+word_name+"】");*/
	  
	  
  }
  
  /**
   * 自定义作业
   */
  @Test(alwaysRun=false)
  @Parameters({"class_name","homework_type","homework_title"})
  public void createdClasshomeworkZDY(){
	  
  }
  
  
  
  
  
  

}
