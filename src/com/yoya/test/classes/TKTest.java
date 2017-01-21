package com.yoya.test.classes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yoya.entities.AnswerQuestion;
import com.yoya.entities.JudgeQuestion;
import com.yoya.entities.MultiChoiceQuestion;
import com.yoya.entities.SimpleChoiceQuestion;
import com.yoya.entities.TKQuestion;
import com.yoya.entities.UnfixedChoiceQuestion;
import com.yoya.inf.QuestionInf;
import com.yoya.page.AssertAlertTip;
import com.yoya.page.MyYoya;
import com.yoya.page.STK;
import com.yoya.page.classes.TK;
import com.yoya.test.login.LoginTest;
import com.yoya.util.Wait;


/**
 * 主要的功能是为题库新增章节，新增题目，导入章节，导入题目等
 * @author Administrator
 *
 */

public class TKTest {
	public WebDriver driver;
	private TK tk;
  @BeforeTest
  @Parameters({"web_driver","host_url","user_name","pass_word"})
  public void beforeTest(String web_driver,String host_url,String user_name,String pass_word) {	  
	  	LoginTest lg=new LoginTest();
	  	driver=lg.login(web_driver, host_url, user_name, pass_word);
	  	
	  	MyYoya yoya=new MyYoya(driver);
	  	driver=yoya.enterSTK();
	  	
	  	
  }

  @AfterTest
  public void afterTest() {
	  Wait.waitMilliSeconds(5000);
	  driver.quit();
  }
  
  
  /**
   * 增加第一章
   * @param chapter_name
   */
  @Test(alwaysRun=true)
  @Parameters(value={"tk_name","chapter_name"})
  public void addFirstChapter(String tk_name,String chapter_name){
	 
	  
	  STK stk=new STK(driver);
	  driver=stk.enterTK(tk_name);
	  this.tk=new TK(driver);
	//按增加章节按钮
	  driver=this.tk.clickAddFirstChapterBtn();
	  
	  
	  //输入章名称
	  this.tk.inputChapterName(chapter_name);
	  
	  this.tk.confirm();
	 
	  
	  AssertAlertTip aat=new AssertAlertTip(driver);
	  driver=aat.assertAlertTip("新增成功，再新增一节就可以增加题目了哦~");
 
  }
  
  
  
  /**
   * 在某一章下增加一个新的章
   * @param old_chapter_name
   * @param one_chapter_name
   */
  @Test(alwaysRun=true)
  @Parameters(value={"tk_name","old_chapter_name","one_chapter_name"})
  public void addOneChapter(String tk_name,String old_chapter_name, String one_chapter_name){

	  STK stk=new STK(driver);
	  driver=stk.enterTK(tk_name);
	  this.tk=new TK(driver);
	  
	  driver=this.tk.addChapter(old_chapter_name);
	  
	  this.tk.inputChapterName(one_chapter_name);
	 
	  this.tk.confirm();
	  
	  AssertAlertTip aat=new AssertAlertTip(driver);
	  driver=aat.assertAlertTip("新增成功，再新增一节就可以增加题目了哦~");	    
  }
  
  /**
   * 在某一章的某一节下增加一新节
   * @param old_chapter_name
   * @param old_section_name
   * @param next_section_name
   */
  @Test(alwaysRun=true)
  @Parameters({"tk_name","old_chapter_name","old_section_name","next_section_name"})
  public void addNextSection(String tk_name,String old_chapter_name,String old_section_name,String next_section_name){
	//按进入题库按钮
	  
	  STK stk=new STK(driver);
	  driver=stk.enterTK(tk_name);
	  this.tk=new TK(driver);
	  
	  
	 this.tk.addSectionUnderSect(old_chapter_name, old_section_name);
	  
	 this.tk.inputSectionName(next_section_name);
	 
	 
	 this.tk.confirm();
	 
	 AssertAlertTip aat=new AssertAlertTip(driver);
	  driver=aat.assertAlertTip("新增成功"); 
	  
	  
	  
  }
  
  
  /**
   * 在某一章下增加一节
   * @param chapter_name
   * @param section_name
   */
  @Test(alwaysRun=true)
  @Parameters(value={"tk_name","chapter_name","section_name"})
  public void addOneSection(String tk_name,String chapter_name,String section_name){
	//按进入题库按钮
	  STK stk=new STK(driver);
	  driver=stk.enterTK(tk_name);
	  this.tk=new TK(driver);
	  
	 
	  driver=this.tk.addSectionUnderChap(chapter_name);
	  
	 this.tk.inputSectionName(section_name); 
	 
	 this.tk.confirm();
	 
	 AssertAlertTip aat=new AssertAlertTip(driver);
	  driver=aat.assertAlertTip("新增成功");
	  
  }
  
  /**
   * 修改某一章下的某一节
   * @param chapter_name
   * @param old_section_name
   * @param new_section_name
   */
  @Test(alwaysRun=true)
  @Parameters({"tk_name","chapter_name","old_section_name","new_section_name"})
  public void editSectionName(String tk_name,String chapter_name,String old_section_name,String new_section_name){
	
	  STK stk=new STK(driver);
	  driver=stk.enterTK(tk_name);
	  this.tk=new TK(driver);
	  
	 driver=this.tk.editSection(chapter_name,old_section_name);
	  
	 this.tk.inputSectionName(new_section_name);

	 this.tk.confirm();
	 
	 AssertAlertTip aat=new AssertAlertTip(driver);
	  driver=aat.assertAlertTip("修改成功!");

  }
  	/**
  	 * 删除某一章下的某一节
  	 * @param chapter_name
  	 * @param section_name
  	 */
  @Test(alwaysRun=true)
  @Parameters({"tk_name","chapter_name","section_name"})
  public void deleteSection(String tk_name,String chapter_name,String section_name){

	  STK stk=new STK(driver);
	  driver=stk.enterTK(tk_name);
	  this.tk=new TK(driver);
	  
	  driver=this.tk.deleteSection(chapter_name, section_name);
	  
	  AssertAlertTip aat=new AssertAlertTip(driver);
	  driver=aat.assertTwoAlertTip("确定要删除《"+section_name+"》吗？", "删除成功！");
			    
  }
  
  
  
  /**
   * 删除某一章
   */
  @Test(alwaysRun=true)
  @Parameters({"tk_name","chapter_name"})
  public void deleteChapter(String tk_name,String chapter_name){

	  STK stk=new STK(driver);
	  driver=stk.enterTK(tk_name);
	  this.tk=new TK(driver);

	  driver=this.tk.deleteChapter(chapter_name);
	 
	  AssertAlertTip aat=new AssertAlertTip(driver);
	  driver=aat.assertTwoAlertTip("确定要删除《"+chapter_name+"》吗？", "删除成功！"); 
  }
  
  /**
   * 编辑某一章
   * @param new_chapter_name
   */
  
  @Test(alwaysRun=true)
  @Parameters(value={"tk_name","old_chapter_name","new_chapter_name"})
  public void editChapter(String tk_name,String old_chapter_name,String new_chapter_name){

		STK stk = new STK(driver);
		driver = stk.enterTK(tk_name);
		this.tk = new TK(driver);

		this.tk.editChapter(old_chapter_name);

		this.tk.inputChapterName(new_chapter_name);

		this.tk.confirm();

		AssertAlertTip aat = new AssertAlertTip(driver);
		driver = aat.assertAlertTip("修改成功!");
 
  
  }
  

  
  /**
   * 从外部文件导入章节
   * @param file_url
   */
  @Test(alwaysRun=true)
  @Parameters({"tk_name","file_url"})
  public void importChaptersLib(String tk_name,String file_url){
	  
	  STK stk = new STK(driver);
		driver = stk.enterTK(tk_name);
		this.tk = new TK(driver);
	  
	this.tk.clickImportChapButton();
		

	  this.tk.uploadfile(file_url);
	 
	  this.tk.confirmUpload();
	  
	  Assert.assertEquals(this.tk.getTip().getText().trim(),"恭喜您，导入章节成功！".trim() );
	  
  }
  
  /**
   * 从外部文件导入题目,指定章，节，知识点.
   * @param questions_url
   */
  @Test(alwaysRun=true)
  @Parameters({"tk_name","chapter_name","section_name","knowledge","questions_url"})
  public void importQuestionsLibWithSpecifics(String tk_name,String chapter_name,String section_name,String knowledge,String questions_url){

		STK stk = new STK(driver);
		driver = stk.enterTK(tk_name);
		this.tk = new TK(driver);

		driver = this.tk.clickImportQuestionButton();

		// 导入到指定章节
		this.tk.clickCheckedBox();

		// 指定章
		this.tk.specifyChapter(chapter_name);

		// 指定节
		this.tk.specifySection(section_name);

		// 指定知识点
		this.tk.specifyKnow(knowledge);

		// 传word题目
		this.tk.uploadQuestionfile(questions_url);
		Wait.waitMilliSeconds(5000);

		this.tk.confirmUpload();
		Wait.waitMilliSeconds(5000);
		Assert.assertTrue(this.tk.getTip().getText().trim().contains("恭喜您，导入成功"));
	  
  }
  
  
  /**
   * 从外部文件导入题目,不指定章，节，知识点.
   * @param questions_url
   */
  @Test(alwaysRun=true)
  @Parameters({"tk_name","questions_url"})
  public void importQuestionsLibWithoutSpecifics(String tk_name,String questions_url){
	  STK stk = new STK(driver);
			driver = stk.enterTK(tk_name);
			this.tk = new TK(driver);
			
	this.tk.clickImportQuestionButton();		
			
	  
	  	  
	  //传word题目
	  this.tk.uploadQuestionfile(questions_url);
		 
	  this.tk.confirmUpload();
	  
	  
	  Assert.assertTrue(this.tk.getTip().getText().trim().contains("恭喜您，导入成功"));

  
  }

  /**
   * 返回试题库
   */
  public void returnSTK(){

	  driver.findElement(By.className("tk-fh")).click();
	  Wait.waitMilliSeconds(5000);  
	  Assert.assertEquals(driver.getTitle(), "我的题库");
	  Wait.waitMilliSeconds(5000);
  }
  
  /**
   * 新增知识点
   */
  @Test(alwaysRun=true)
  @Parameters({"tk_name","chapter_name","section_name","knowledge"})
  public void addKnowledge(String tk_name,String chapter_name,String section_name,String knowledge){
	  
	  STK stk = new STK(driver);
		driver = stk.enterTK(tk_name);
		this.tk = new TK(driver);
	  
		
		//定位到某章的某小节
	  this.tk.findSection(chapter_name, section_name);
	  
	  //知识点管理
	  this.tk.clickknowledgesManageButton();
	  
	  this.tk.addknowledges();
	  
	  //提示框，输入知识点名称
	  
	  this.tk.inputKnowName(knowledge);
	 
	 this.tk.confirm();
	  
	 
	 AssertAlertTip aat=new AssertAlertTip(driver);
	 aat.assertAlertTip("新增成功");

	  
  }
  
  
  /**
   * 编辑知识点
   */
  @Test(alwaysRun=true)
  @Parameters({"tk_name","chapter_name","section_name","knowledge","new_knowledge"})
  public void editKnowledge(String tk_name,String chapter_name,String section_name,String knowledge,String new_knowledge){
	  
	  STK stk = new STK(driver);
		driver = stk.enterTK(tk_name);
		this.tk = new TK(driver);
	  
	  
	  //定位到某章的某小节
	  this.tk.findSection(chapter_name, section_name);
	  
	  //知识点管理
	  this.tk.knowledgeManager();
	 
	  WebElement target=this.tk.findKnowledge(knowledge);
	  
	  //编辑
	  this.tk.editKnowledge(target);
	  
	  this.tk.inputKnowName(new_knowledge);
	  this.tk.confirm();
	  
	  AssertAlertTip aat=new AssertAlertTip(driver);
		 aat.assertAlertTip("修改成功!");	  
  }
  
  
  /**
   * 删除知识点
   */
  @Test(alwaysRun=true)
  @Parameters({"tk_name","chapter_name","section_name","knowledge"})
  public void deleteKnowledge(String tk_name,String chapter_name,String section_name,String knowledge){
	  STK stk = new STK(driver);
		driver = stk.enterTK(tk_name);
		this.tk = new TK(driver);
		
		
	  //定位到某章的某小节
		
		this.tk.findSection(chapter_name, section_name);
	  Wait.waitMilliSeconds(5000);
	  
	  //知识点管理
	  this.tk.knowledgeManager();
	  Wait.waitMilliSeconds(5000);
	  WebElement target=this.tk.findKnowledge(knowledge);
	  
	  
	  this.tk.deleteKnowledge(target);
	  
	//提示确认删除,确认成功
	  AssertAlertTip aat=new AssertAlertTip(driver);
	  aat.assertTwoAlertTip("确定要删除《"+knowledge+"》吗？", "删除成功！");

  }
  

  /**
   * 新增题目
   */
  @Test(alwaysRun=true)
  @Parameters({"tk_name","chapter_name","section_name","knowledge","question_type","question_url"})
  public void addQuestion(String tk_name,String chapter_name,String section_name,
		  String knowledge,String question_type,String question_url){
	  STK stk = new STK(driver);
		driver = stk.enterTK(tk_name);
		this.tk = new TK(driver);
	  //定位到某章的某小节
		this.tk.findSection(chapter_name, section_name);
		
	  Wait.waitMilliSeconds(5000);
	  this.tk.questionsList();
	  
	  Wait.waitMilliSeconds(5000);
	  //选择知识点
	  this.tk.chooseKnowledge(knowledge);
	  //选择题型
	  this.tk.chooseQuesType(question_type);
	  

	  //新增题目
	  driver=this.tk.clickNewQuestionButton();
	  
	 
	  
	  
	  QuestionInf qi=null;
	  
	  switch(question_type){
	  case "单选题":
		  qi=new SimpleChoiceQuestion(question_url);
		  break;
	  case "多选题":
		  qi=new MultiChoiceQuestion(question_url);
		  break;
	  case "填空题":
		  qi=new TKQuestion(question_url);
		  break;
	  case "判断题":
		  qi=new JudgeQuestion(question_url);
		  break;
	  case "问答题":
		  qi=new AnswerQuestion(question_url);
		  break;
	  case "不定项选择题":
		  qi=new UnfixedChoiceQuestion(question_url);
		  break;
	  }
	  driver=qi.addQuestion(driver);
	  Wait.waitMilliSeconds(5000);
	  driver.switchTo().defaultContent();
	  
  }
  
  
  /**
   * 编辑题目
   */
  @Test(alwaysRun=true)
  @Parameters({"tk_name","chapter_name","section_name","knowledge","question_type","question_title","eidt_question_url"})
  public void editQuestion(String tk_name,String chapter_name,String section_name,
		  String knowledge,String question_type,String question_title,String eidt_question_url){
	  STK stk = new STK(driver);
		driver = stk.enterTK(tk_name);
		this.tk = new TK(driver);
		
	  //定位到某章的某小节
	  this.tk.findSection(chapter_name, section_name);
		
	  this.tk.questionsList();
	  
	  Wait.waitMilliSeconds(5000);
	  //选择知识点
	  this.tk.chooseKnowledge(knowledge);
	  //选择题型
	  this.tk.chooseQuesType(question_type);
	  
	  WebElement target=this.tk.findQuestion(question_title);
	  
	  driver=this.tk.editQuestion(target);
	  
	  
	  QuestionInf qi=null;
	  switch(question_type){
	  case "单选题":
		  qi=new SimpleChoiceQuestion(eidt_question_url);
		  break;
	  case "多选题":
		  qi=new MultiChoiceQuestion(eidt_question_url);
		  break;
	  case "填空题":
		  qi=new TKQuestion(eidt_question_url);
		  break;
	  case "判断题":
		  qi=new JudgeQuestion(eidt_question_url);
		  break;
	  case "问答题":
		  qi=new AnswerQuestion(eidt_question_url);
		  break;
	  case "不定项选择题":
		  qi=new UnfixedChoiceQuestion(eidt_question_url);
		  break;
	  }
	  
	  driver=qi.editQuestion(driver);
	  Wait.waitMilliSeconds(5000);
	  driver.switchTo().defaultContent();
  }
  
  
  /**
   * 删除题目
   */
  @Test(alwaysRun=true)
  @Parameters({"tk_name","chapter_name","section_name","knowledge","question_type","question_title"})
  public void deleteQuestion(String tk_name,String chapter_name,String section_name,
		  String knowledge,String question_type,String question_title){
	  STK stk = new STK(driver);
		driver = stk.enterTK(tk_name);
		this.tk = new TK(driver);
	  //定位到某章的某小节
	  this.tk.findSection(chapter_name, section_name);
		
		
	  this.tk.questionsList();
	  
	  Wait.waitMilliSeconds(5000);
	  //选择知识点
	 this.tk.chooseKnowledge(knowledge);
	  //选择题型
	  this.tk.chooseQuesType(question_type);
	  
	  
	  this.tk.deleteQuestion(question_title);
	  
	  AssertAlertTip aat=new AssertAlertTip(driver);
		 aat.assertTwoAlertTip("确定要删除该题目吗？删除后可在\"我的题库 》回收站\"中查看！", "操作成功!");
	  
  }
  
}
