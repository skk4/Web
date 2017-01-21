package com.yoya.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yoya.page.Dialog;
import com.yoya.page.InteractiveMovie;
import com.yoya.page.Page;
import com.yoya.page.yun.MyYunInteractiveMovie;
import com.yoya.test.login.LoginTest;
import com.yoya.util.DriverExchangeAssist;
import com.yoya.util.ElementExistOrNot;
import com.yoya.util.Wait;

/**
 * 主站互动电影
 * @author Administrator
 *
 */
public class InteractiveMovieTest {
 
	public WebDriver driver;
	private InteractiveMovie im;
	  @BeforeTest
	  @Parameters({"web_driver","host_url","user_name","pass_word"})
	  public void beforeTest(String web_driver,String host_url,String user_name,String pass_word) {
		  LoginTest lg=new LoginTest();
		  driver=lg.login(web_driver, host_url, user_name, pass_word);
		  im=new InteractiveMovie(driver);
	  }
	  
	  
	  @AfterTest
	  public void afterTest() {
		  Wait.waitMilliSeconds(5000);
		  driver.quit();
	  }
	  
	  /**
	   * 由于无法保存创建的电影，所以该方法暂时不传很多复杂的参数
	   */
	  @Test(alwaysRun=false)
	  public void createMovie(){
		  driver.findElement(By.cssSelector("i.icon.icon-newly-built")).click();
		  Wait.waitMilliSeconds(5000);
		  driver=DriverExchangeAssist.exchangeDriverUseTitle(driver, "互动电影");
		  Wait.waitMilliSeconds(5000);
		  Assert.assertTrue(ElementExistOrNot.elementExist(driver, By.xpath("//iframe[starts-with(@id,'layui-layer-iframe')]")));
		  
		  driver.findElement(By.id("newMovie")).click();
		  Wait.waitMilliSeconds(5000);
		  
		  
	  
	  }
	  
	  
	  /**
	   * 由分组管理返回到互动电影
	   */
	  public void returnInteractiveMovie(){
		  Actions action=new Actions(driver);
		  action.moveToElement(driver.findElement(By.cssSelector("i.icon.icon-back"))).perform();
		  Wait.waitMilliSeconds(5000);
		  
		  driver.findElement(By.cssSelector("i.icon.icon-back")).click();
		  Wait.waitMilliSeconds(5000);
		  Assert.assertEquals(driver.findElement(By.className("title")), "互动电影");
		  
	  }
	  
	  /**
	   * 新建分组
	   */
	  @Test(alwaysRun=true)
	  @Parameters({"group_name"})
	  public void createGroup(String group_name){
		  //分组管理
		  this.im.clickGroupManage();
		  //新建分组
		  this.im.clickNewGroup();
		  
		  //输入分组名
		  this.im.inputGroupName(group_name);
		  
		  //点击保存
		  this.im.clickSave();
		  
		  //检查操作成功
		  WebElement result=this.im.getSuccussTip();
		  Assert.assertEquals(result.getText(), "操作成功");		  
	  }
	  
	  /**
	   * 分组修改
	   */
	  @Test(alwaysRun=true)
	  @Parameters({"group_name","new_group_name"})
	  public void editGroup(String group_name,String new_group_name){
		  
		//分组管理
		  this.im.clickGroupManage();
		  
		  WebElement target=this.im.findGroup(group_name);
		  //编辑
		  this.im.editGroup(target, new_group_name);
		  
		  //保存
		  this.im.clickSave();
		  
		  //提示成功
		  Assert.assertEquals(this.im.getSuccussTip().getText(), "操作成功");		  
		  
	  }
	  
	  
	  /**
	   * 分组删除
	   */
	  
	  @Test(alwaysRun=true)
	  @Parameters({"group_name"})
	  public void deleteGroup(String group_name){
		  
		// 分组管理
		this.im.clickGroupManage();

		// 查找分组

		WebElement target = this.im.findGroup(group_name);

		// 删除分组
		this.im.deleteGroup(target);

		// 断言是否删除
		Assert.assertEquals(this.im.getMessageTip().getText(), "是否确认删除该分组!");

		// 确认
		this.im.clickConfirm();
		// Wait.waitMilliSeconds(5000);
		  
	  }
	  
	  
	  /**
	   * 编辑电影
	   */
	  @Test(alwaysRun=true)
	  @Parameters({"movie_name"})
	  public void editMovie(String movie_name){
		  
		  //找电影
		  WebElement movie=this.im.findMovie(movie_name);
				 
		  //点编辑按钮
		 driver=this.im.clickEditMovieBtn(movie);
		  
		  //断言页面标题
		  Assert.assertEquals(driver.getTitle(), "互动电影");
		  
		  
	  }
	  
	  /**
	   * 预览电影
	   */
	  @Test(alwaysRun=true)
	  @Parameters({"movie_name"})
	  public void previewMovie(String movie_name){
		  //找电影
		  WebElement movie=this.im.findMovie(movie_name);
		  
		  //预览按钮
		  driver=this.im.clickPreviewMovieBtn(movie, movie_name);
		  
		  Assert.assertEquals(driver.getTitle(), movie_name);
	  }
	  
	  /**
	   * 电影发布  movie_type="教育,小学,英语"
	   */
	  @Test(alwaysRun=true)
	  @Parameters({"movie_name","movie_type"})
	  public void publishMovie(String movie_name,String movie_type){
		  //找电影
		  WebElement movie=this.im.findMovie(movie_name);
		  
		  //分享按钮
		  driver=this.im.clickPublishMovieBtn(movie);
		  
		  Assert.assertEquals(this.im.getMovieName(), movie_name);
		  
		  this.im.chooseMovieType(movie_type);
		  
		  this.im.clickSureBtn();
		  Wait.waitMilliSeconds(5000);
		  //验证发布成功
		  Assert.assertEquals(this.im.getPublishAndShareTitle(), "发布与分享");
	  }
	  
	  
	  
	  
	  /**
	   * 申请成为模板
	   */
	  @Test(alwaysRun=true)
	  @Parameters({"movie_name","template_type","fee_type"})
	  public void applyAsTemplate(String movie_name,String template_type,String fee_type){
		  //找电影
		  WebElement movie=this.im.findMovie(movie_name);
		  
		  driver=this.im.clickApplyBtn(movie);
		  
		  Assert.assertEquals(this.im.getTemplateName(), movie_name);
		  
		  this.im.chooseMovieType(template_type);
		  
 		  //选择是否免费
		  this.im.chooseFreeOrNot(fee_type);
		  Wait.waitMilliSeconds(5000);
		  //申请按钮
		  driver=this.im.clickApplyConfirmBtn();
		  
		  Dialog dialog=new Dialog(driver);
		  WebElement tip=dialog.getSuccessTip();
		  Assert.assertEquals(tip.getText(), "操作成功");
		  
		  
		  
	  }
	  
	  /**
	   * 导出
	   */
	  @Test(alwaysRun=true)
	  @Parameters({"movie_name","export_way","export_code"})
	  public void exportMovie(String movie_name,String export_way,String export_code){
		//找电影
		  WebElement movie=this.im.findMovie(movie_name);
		  
		  //按导出电影按钮
		  driver=this.im.clickExportBtn(movie);
		  
		  MyYunInteractiveMovie yunIM=new MyYunInteractiveMovie(driver);
		  //导出电影
		  yunIM.exportMovie(export_way,export_code);
		  Wait.waitMilliSeconds(5000);
		  driver.switchTo().defaultContent();  
	  }
	  
	  /**
	   * 复制电影
	   */
	  
	  @Test(alwaysRun=true)
	  @Parameters({"movie_name"})
	  public void copyMovie(String movie_name){
		  WebElement movie=this.im.findMovie(movie_name);
		  driver=this.im.clickCopyBtn(movie);
		  
		  Dialog d=new Dialog(driver);
		  Assert.assertEquals(d.getContent(), "确定要复制一个副本吗？名称为："+movie_name+"_副本");
		  
		  d.clickconfirmBtn();
		  driver.navigate().refresh();
		  Wait.waitMilliSeconds(5000);
		  Assert.assertNotNull(this.im.findMovie(movie_name+"_副本"));
	  }
	  
	  /**
	   * 电影分组
	   */
	  @Test(alwaysRun=true)
	  @Parameters({"movie_name","group_name"})
	  public void groupMovie(String movie_name,String group_name){
		  WebElement movie=this.im.findMovie(movie_name);
		  driver=this.im.clickGroupBtn(movie);
		  
		  Page page=new Page(driver);
		  
		  page.chooseGroup(group_name);
		  
		  page.clickSaveBtn();
		  
		  driver.switchTo().defaultContent();
		  //进入新分组页面
		  this.im.enterGroupTab(group_name);
		  //断言在该分组下找到电影
		  Assert.assertNotNull(this.im.findMovie(movie_name));
		  
	  }
	  
	  /**
	   * 删除电影
	   */
	  @Test(alwaysRun=true)
	  @Parameters({"movie_name"})
	  public void deleteMovie(String movie_name){
		  //找电影
		  WebElement movie=this.im.findMovie(movie_name);
		  Wait.waitMilliSeconds(5000);
		  driver=this.im.clickDeleteBtn(movie);
		  Wait.waitMilliSeconds(5000);
		  Dialog dialog=new Dialog(driver);
		  dialog.clickconfirmBtn();
		  WebElement tip=dialog.getSuccessTip();
		  //断言删除成功
		  Assert.assertEquals(tip.getText(), "操作成功");
	  }
	  
	  
	  

}
