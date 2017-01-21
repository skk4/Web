package com.yoya.test.classes;

import java.util.List;

import org.openqa.selenium.By;
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
import com.yoya.page.classes.ClassInteraction;
import com.yoya.test.login.LoginTest;
import com.yoya.util.DriverExchangeAssist;
import com.yoya.util.LocateElement;
import com.yoya.util.LocateIframe;
import com.yoya.util.Wait;

public class ClassInteractionTest {

	 public WebDriver driver;
	 private ClassInteraction interaction;
	  @Parameters({"web_driver","host_url","user_name","pass_word"})
	  @BeforeTest
	  public void beforeTest(String web_driver,String host_url,String login_username,String old_p) {
		  	LoginTest lg=new LoginTest();
		  	driver=lg.login(web_driver, host_url, login_username, old_p);
		  	
		  	MyYoya yoya=new MyYoya(driver);
		  	driver=yoya.enterKCB();
		  	KCB kcb=new KCB(driver);
		  	driver=kcb.clickCreateClass();
		  	this.interaction=new ClassInteraction(driver);
	  }

	  @AfterTest
	  public void afterTest() {
		  Wait.waitMilliSeconds(5000);
		  driver.quit();
	  }

  
	  /**
	   * 加入课程班-课堂互动
	   */
/*	  @Test(alwaysRun=true)
	  @Parameters({"class_name"})
	  public void joinedClassInteraction(String class_name){
		  
		  //进入课程班
		  this.enterKCB();
		  
		  WebElement target=LocateElement.locateElementUseText(driver, "//ul[@id='class_list']/li/div/a", class_name);
		  Wait.waitMilliSeconds(5000);
		  target.findElement(By.xpath("./../../div[@class='content']/ul/li[2]/a/div")).click();
		  Wait.waitMilliSeconds(5000);
		  
		  Assert.assertEquals(driver.findElement(By.xpath("//div[@id='value-content']//small")).getText(), "请去移动端查看课堂内容喔~");
		  Wait.waitMilliSeconds(5000);
		  
		  Assert.assertTrue(ElementExistOrNot.elementExist(driver, By.className("download-iphone")));
		  Assert.assertTrue(ElementExistOrNot.elementExist(driver, By.className("download-android")));
		  Assert.assertTrue(ElementExistOrNot.elementExist(driver, By.className("weixin-code")));
	  
	  }*/
	  
	  
	  /**
	   * 加入课程班-作业
	   */
/*	  @Test(alwaysRun=true)
	  @Parameters({"class_name","homework_status","homework_name"})
	  public void joinedClassHomework(String class_name,String homework_status,String homework_name){
		//进入课程班
		  this.enterKCB();
		  
		  WebElement target=LocateElement.locateElementUseText(driver, "//ul[@id='class_list']/li/div/a", class_name);
		  Wait.waitMilliSeconds(5000);
		  //作业按钮
		  target.findElement(By.xpath("./../../div[@class='content']/ul/li[3]/a/div")).click();
		  Wait.waitMilliSeconds(5000);
		  
		  //作业状态
		  WebElement status=LocateElement.locateElementUseText(driver, "//div[@class='page-tabs']/a", homework_status);
		  Wait.waitMilliSeconds(5000);
		  status.click();
		  Wait.waitMilliSeconds(5000);
		  
		  Assert.assertEquals(driver.findElement(By.id("class_name")).getText(), class_name);
		  
		  WebElement title=LocateElement.locateElementUsePartialText(driver, "//a[@class='title']", homework_name);
		  Assert.assertNotNull(title);
		  
		 
		  
	  }*/
	  
	  
	  
	  
	  /**
	   * 创建课程班-课堂互动
	   */
	  @Test(alwaysRun=true)
	  @Parameters({"class_name"})
	  public void createdClassInteraction(String class_name){
		  
		  //进入课堂互动
		  this.interaction.clickInterctionClassBtn(class_name);
		  
		  //新建课堂
		  driver=this.interaction.createClass();
		 		 
		  driver=this.interaction.save();
		  Wait.waitMilliSeconds(5000);
		  Assert.assertEquals(this.interaction.findClass().getText(), "您已成功添加《"+class_name+"》。");
		  
		  /*driver.findElement(By.xpath("/a[@id='_99layer_btn_1_0' and @title='进入课堂']")).click();
		  Wait.waitMilliSeconds(5000);*/
		  
	  }
	  
	  /**
	   * 创建课程班-互动课堂-删除
	   */
	  @Test(alwaysRun=true)
	  @Parameters({"class_date","class_name"})
	  public void deleteCreatedInteractionClass(String class_date,String class_name){
		  
/*		  //寻找班级
		  WebElement target=LocateElement.locateElementUseText(driver, "//ul[@id='class_list']/li/div/a", class_name);
		  Wait.waitMilliSeconds(5000);
		  //点击互动课堂
		  target.findElement(By.xpath("./../../div[@class='content']/ul/li[2]/a/div")).click();
		  Wait.waitMilliSeconds(5000);*/
		  
		  this.interaction.clickInterctionClassBtn(class_name);
		  
		  
		  WebElement aa=this.interaction.moveToClass(class_date);
		  
		  //删除按钮
		 this.interaction.clickDeleteBtn(aa);
		  
		 AssertAlertTip tip=new AssertAlertTip(driver);
		 tip.assertAlertTip( "确认删除该课堂记录，相关数据也将一并清除？");		  
	  }
	  
	  
	  /**
	   * 创建课程班-互动课堂-编辑
	   */
	  @Test(alwaysRun=true)
	  @Parameters({"class_date","class_name","new_class_date"})
	  public void editCreatedInteractionClass(String class_date,String class_name,String new_class_date){

		  //寻找班级
		  //点击互动课堂
		  this.interaction.clickInterctionClassBtn(class_name);
		  
		  WebElement aa=this.interaction.moveToClass(class_date);
		 
		  //编辑按钮
		 this.interaction.clickEditBtn(aa);
		  
		 //设置日期控件为可写，改上课日期
		this.interaction.inputClassDate(new_class_date);
		
		driver=this.interaction.save();
		
		Assert.assertEquals(this.interaction.findClass().getText(), "您已成功修改《"+class_name+"》。");
		Wait.waitMilliSeconds(5000);
	  }
	  
	  
	  /**
	   * 签到
	   */
	  @Test(alwaysRun=true)
	  @Parameters({"class_name","class_date"})
	  public void signIn(String class_name,String class_date){

		  
		 this.interaction.clickInterctionClassBtn(class_name);
		  //找到日期
		 driver=this.interaction.enterClass(class_date);

		  //签到
		  driver=this.interaction.clickSignBtn();
		  
		  
		  this.interaction.save();

		  //刷新
		  driver.navigate().refresh();
		  Wait.waitMilliSeconds(5000);
		  
		  //开始互动
		  this.interaction.startInteraction();
		  
		 
		  Assert.assertEquals(this.interaction.getTip().getText(), "确定“开始”该互动");
		  
		  this.interaction.confirm();
		  
		  Assert.assertEquals(this.interaction.getStatus().getText(), "进行中");
		  
		  
	  }
	  
	  /**
	   * 提问
	   */
	  @Test(alwaysRun=true)
	  @Parameters({"class_name","class_date","tk_name","chapter_name","section_name","question_title"})
	  public void question(String class_name,String class_date,String tk_name,String chapter_name,String section_name,String question_title){

		
		  //点击互动课堂
		  this.interaction.clickInterctionClassBtn(class_name);
		  
		  //找到日期
		 this.interaction.enterClass(class_date);
		  
		  
		  //提问
		  driver=this.interaction.clickQuestionBtn();
		  
		  
		  //发起提问框，从题库中选
		  WebElement fqtwIframe=driver.findElement(By.xpath("//iframe[starts-with(@id,'layui-layer-iframe')]"));
		  driver.switchTo().frame(fqtwIframe);
		  int hashcode1=fqtwIframe.hashCode();
		  Wait.waitMilliSeconds(5000);
		  
		  
		  driver.findElement(By.id("to_select_tm")).click();
		  Wait.waitMilliSeconds(5000);
		  
		  //回到top窗口
		  driver.switchTo().defaultContent();
		  
		  //题库框
		  WebElement tkIframe=null;
		  List<WebElement> iframes=driver.findElements(By.xpath("//iframe[starts-with(@id,'layui-layer-iframe')]"));
		  for(WebElement current:iframes){
			  int hashcode=current.hashCode();
			  if(hashcode!=hashcode1){
				  tkIframe=current;
				  break;
			  }
		  }
		  
		  driver.switchTo().frame(tkIframe);
		  Wait.waitMilliSeconds(5000);
		  
		  driver.findElement(By.id("tk_list")).click();
		  Wait.waitMilliSeconds(5000);
		  
		  //选择题库
		  WebElement tk=LocateElement.locateElementUseText(driver, "//select[@id='tk_list']/option", tk_name);
		  tk.click();
		  Wait.waitMilliSeconds(5000);
		  
		  //选章,节
		  LocateElement.locateElementUseText(driver, "//ul[@id='chapterlist']/li[@data-chapter_name='"+chapter_name+"']/div/span[@name='node_name']",section_name ).click();
		  Wait.waitMilliSeconds(5000);
		  
		  //选择题目
		  WebElement tm=LocateElement.locateElementUsePartialText(driver, "//div[@id='question_list']/div/div[@class='tk-content']/div[1]", question_title);
		  Wait.waitMilliSeconds(2000);
		  tm.click();
		  Wait.waitMilliSeconds(5000);
		  
		  //确定
		  driver.findElement(By.xpath("//div[@class='xz_time']/a[2]")).click();
		  Wait.waitMilliSeconds(5000);
		  
		  //切换到第一个框
		  driver.switchTo().defaultContent();
		  driver.switchTo().frame(fqtwIframe);
		  
		  //保存
		  driver.findElement(By.id("addbtn")).click();
		  
		  
		  //定位提问页面
		  driver.switchTo().defaultContent();
		  driver.navigate().refresh();
		  driver.findElement(By.xpath("//ul[@id='task_type_ul']/li/a[@data-task_type='02']")).click();
		  Wait.waitMilliSeconds(5000);
		  
		  
		  
		  
		  //寻找提问内容
		  WebElement twcontent=LocateElement.locateElementUsePartialText(driver, ".//*[@id='tbody']/tr/td[@class='info-message']/a", question_title);
		  twcontent.findElement(By.xpath("./../../td[5]/input")).click();
		  Wait.waitMilliSeconds(5000);
		  
		  //开始互动
		  
		  Assert.assertEquals(this.interaction.getTip().getText(), "确定“开始”该互动");
		  
		  this.interaction.confirm();
		  Wait.waitMilliSeconds(5000);
		  
		  
		  //验证是否进行中
		  Assert.assertEquals(this.interaction.getStatus().getText(), "进行中");

	  }
	  
	  /**
	   * 测验
	   */
	  @Test(alwaysRun=true)
	  @Parameters({"class_name","class_date","cy_title","tk_name","chapter_name","section_name"})
	  public void quiz(String class_name,String class_date,String cy_title,String tk_name,String chapter_name,String section_name){
		  
		 
		  //寻找班级
		  WebElement target=LocateElement.locateElementUseText(driver, "//ul[@id='class_list']/li/div/a", class_name);
		  Wait.waitMilliSeconds(5000);
		  //点击互动课堂
		  target.findElement(By.xpath("./../../div[@class='content']/ul/li[2]/a/div")).click();
		  Wait.waitMilliSeconds(5000);
		  
		  //找到日期
		  WebElement date=LocateElement.locateElementUseText(driver, "//tbody[@id='lesson_list_view']//div[@class='date']", class_date);
		  
		  date.findElement(By.xpath("./../../td[@class='action']/div[2]/a[text()='进入课堂']")).click();
		  
		  Wait.waitMilliSeconds(5000);
		  
		  //切换到新的页面
		  driver=DriverExchangeAssist.exchangeDriverUsePartialUrl(driver, "http://ke-nin.test.yoya.com/doView?action=classroom/v_t_classroom&start=to_classroom");
		  Wait.waitMilliSeconds(5000);
		  
		  
		  //测验
		  driver.findElement(By.xpath("//ul[@class='top-nav-boxes']/li[@data-task_type='cy']")).click();
		  Wait.waitMilliSeconds(5000);
		  
		  //定位iframe		  
		  WebElement iframe1=driver.findElement(By.xpath("//iframe[starts-with(@id,'layui-layer-iframe')]"));
		  driver.switchTo().frame(iframe1);
		  
		  WebElement task=driver.findElement(By.id("task_content"));
		  task.clear();
		  task.sendKeys(cy_title);
		  
		  Wait.waitMilliSeconds(5000);
		  driver.findElement(By.id("to_select_tms")).click();
		  Wait.waitMilliSeconds(5000);
		  
		  driver.switchTo().defaultContent();
		  //获取两个iframe，定位到从题库中选
		  List<WebElement> iframes=driver.findElements(By.xpath("//iframe[starts-with(@id,'layui-layer-iframe')]"));
		  for(WebElement current:iframes){
			  driver.switchTo().defaultContent();
			  driver.switchTo().frame(current);
			  String temp_title=driver.findElement(By.xpath("//div[@id='testModal']/div[@class='testModal-header']")).getText().trim();
			  if(temp_title.equalsIgnoreCase("从题库中选")){
				  break;
			  }
			  
		  }

		  //选择题库
		  driver.findElement(By.xpath("//div[@id='testModal']//div[@class='library-select-wrapper']/div/div[@class='sod_label']")).click();
		  Wait.waitMilliSeconds(5000);

		  WebElement tk=LocateElement.locateElementUseText(driver, "//div[@id='testModal']//div[@class='library-select-wrapper']/div/div[@class='sod_list']/ul/li",tk_name );
		  tk.click();
		  Wait.waitMilliSeconds(5000);
		  
		  //选择章
		  WebElement chapter=LocateElement.locateElementUseText(driver, "//div[@id='left_menu_view']/div", chapter_name);
		  chapter.click();
		  Wait.waitMilliSeconds(5000);
		  //选择节
		  
		  WebElement section=driver.findElement(By.xpath("//div[@id='left_menu_view']/div/div[@data-chapter_name='"+chapter_name+"'"+" and "+"@data-node_name='"+section_name+"'"+"]"+"/a"));
		  section.click();
		  Wait.waitMilliSeconds(5000);
		  
		  //全选
		  driver.findElement(By.id("select-all")).click();
		  Wait.waitMilliSeconds(5000);
		  
		  //确定
		  driver.findElement(By.className("testModal-confirm")).click();
		 
		  
		  //定位发起测试框
		  driver.switchTo().defaultContent();
		  driver.switchTo().frame(iframe1);
		  Wait.waitMilliSeconds(5000);
		  //保存
		  driver.findElement(By.id("saveBtn")).click();
		  Wait.waitMilliSeconds(5000);
		  
		  //跳出iframe框
		  driver.switchTo().defaultContent();
		  //重新刷新请求新的页面
		  driver.navigate().refresh();
		  
		  
		  //定位到测验的互动页 
		  driver.findElement(By.xpath("//ul[@id='task_type_ul']/li/a[@data-task_type='04']")).click();
		  Wait.waitMilliSeconds(5000);
		  
		  //寻找测验内容
		  WebElement cycontent=LocateElement.locateElementUseText(driver, ".//*[@id='tbody']/tr/td[@class='info-message']/a", cy_title);
		  cycontent.findElement(By.xpath("./../../td[5]/input")).click();
		  Wait.waitMilliSeconds(5000);
		  
		  //开始互动
		  WebElement tip_dialog=driver.findElement(By.xpath("//div[starts-with(@id,'layui-layer') and @type='dialog']"));
		  Assert.assertEquals(tip_dialog.findElement(By.xpath("./div[2]")).getText(), "确定“开始”该互动");
		  
		  driver.findElement(By.className("layui-layer-btn0")).click();
		  Wait.waitMilliSeconds(5000);
		  
		  
		  //验证是否进行中
		  Assert.assertEquals(driver.findElement(By.xpath(".//*[@id='tbody']/tr[1]/td[4]/span[1]")).getText(), "进行中");
		  
	  }

	  /**
	   * 投票
	   */
	  @Test(alwaysRun=true)
	  @Parameters({"class_name","class_date","tp_title","tp_choices"})
	  public void vote(String class_name,String class_date,String tp_title,String tp_choices){
		 
		  //寻找班级
		  WebElement target=LocateElement.locateElementUseText(driver, "//ul[@id='class_list']/li/div/a", class_name);
		  Wait.waitMilliSeconds(5000);
		  //点击互动课堂
		  target.findElement(By.xpath("./../../div[@class='content']/ul/li[2]/a/div")).click();
		  Wait.waitMilliSeconds(5000);
		  
		  //找到日期
		  WebElement date=LocateElement.locateElementUseText(driver, "//tbody[@id='lesson_list_view']//div[@class='date']", class_date);
		  
		  date.findElement(By.xpath("./../../td[@class='action']/div[2]/a[text()='进入课堂']")).click();
		  
		  Wait.waitMilliSeconds(5000);
		  
		  //切换到新的页面
		  driver=DriverExchangeAssist.exchangeDriverUsePartialUrl(driver, "http://ke-nin.test.yoya.com/doView?action=classroom/v_t_classroom&start=to_classroom");
		  Wait.waitMilliSeconds(5000);
		  
		  //投票
		  driver.findElement(By.xpath("//ul[@class='top-nav-boxes']/li[@data-task_type='tp']")).click();
		  Wait.waitMilliSeconds(5000);
		  driver=LocateIframe.locateIframe(driver, "layui-layer-iframe");
		  
		  driver.findElement(By.id("vote_title")).sendKeys(tp_title);
		  
		  String[] choices=tp_choices.split(";");
		  
		  WebElement items=driver.findElement(By.id("vote_item"));
		  
		  //四个选项
		  items.findElement(By.xpath("./div[1]/div[@class='pdtx_na']/input[@name='vote_item_title']")).sendKeys(choices[0]);
		  items.findElement(By.xpath("./div[2]/div[@class='pdtx_na']/input[@name='vote_item_title']")).sendKeys(choices[1]);
		  items.findElement(By.xpath("./div[3]/div[@class='pdtx_na']/input[@name='vote_item_title']")).sendKeys(choices[2]);
		  items.findElement(By.xpath("./div[4]/div[@class='pdtx_na']/input[@name='vote_item_title']")).sendKeys(choices[3]);
		  
		  //定位到投票的页面
		  driver.findElement(By.id("addbtn")).click();
		  Wait.waitMilliSeconds(5000);
		  
		  driver.switchTo().defaultContent();
		  driver.navigate().refresh();
		  Wait.waitMilliSeconds(5000);
		  
		  driver.findElement(By.xpath("//ul[@id='task_type_ul']/li/a[@data-task_type='03']")).click();
		  Wait.waitMilliSeconds(5000);
		  
		  //开始按钮
		  driver.findElement(By.xpath(".//*[@id='tbody']/tr[1]/td[5]/input")).click();
		  Wait.waitMilliSeconds(5000);
		  
		  WebElement tip_dialog=driver.findElement(By.xpath("//div[starts-with(@id,'layui-layer') and @type='dialog']"));
		  Assert.assertEquals(tip_dialog.findElement(By.xpath("./div[2]")).getText(), "确定“开始”该互动");
		  
		  driver.findElement(By.className("layui-layer-btn0")).click();
		  Wait.waitMilliSeconds(5000);
		  
		  Assert.assertEquals(driver.findElement(By.xpath("//tbody[@id='tbody']/tr[1]/td[contains(@class,'hd-state')]/span")).getText(), "进行中");
		 
	  }
	  /**
	   * 下课
	   */
	  @Test(alwaysRun=true)
	  @Parameters({"class_name","class_date"})
	  public void signOut(String class_name,String class_date){
		
		 
		  this.interaction.clickInterctionClassBtn(class_name);
		  
		  //找到日期
		  driver=this.interaction.enterClass(class_date);
		  

		  //下课
		  this.interaction.clickSignoutBtn();
		  
		  
		 
		  Assert.assertEquals(this.interaction.getTip().getText(), "确定要下课吗？下课后所有的学生将不能再操作。");
		  
		  this.interaction.confirm();
		  Wait.waitMilliSeconds(5000);
		  //Assert.assertTrue(Wait.explicitlyWaitTextByLocator(driver, 10, driver.findElement(By.xpath("//div[starts-with(@id,'layui-layer') and @type='dialog']/div")), "课堂关闭成功"));
		  
		  driver.switchTo().defaultContent(); 
		  
	  }
	  
	  
	  /**
	   * 历史课堂
	   */
	  @Test(alwaysRun=true)
	  @Parameters({"class_name"})
	  public void historyClass(String class_name){
		  
			  
		  this.interaction.clickInterctionClassBtn(class_name);
		  //历史课堂
		  this.interaction.clickHistoryClass();
		  
		  
	  }
	  

	  
	  
	  
  
}
