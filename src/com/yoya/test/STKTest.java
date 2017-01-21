package com.yoya.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yoya.page.MyYoya;
import com.yoya.page.STK;
import com.yoya.test.login.LoginTest;
import com.yoya.util.Wait;
/**
 * 试题库
 * @author Administrator
 *
 */
public class STKTest {
 
	public WebDriver driver;
	private STK stk;
	@BeforeTest
  @Parameters({"web_driver","host_url","user_name","pass_word"})
  public void beforeClass(String web_driver,String host_url,String user_name,String pass_word) {	
		LoginTest lg=new LoginTest();
		driver=lg.login(web_driver, host_url, user_name, pass_word);
		
		MyYoya yoya=new MyYoya(driver);
		driver=yoya.enterSTK();
		
		stk=new STK(driver);
  }

  @AfterTest
  public void afterClass() {
	  Wait.waitMilliSeconds(5000);
	  driver.quit();
  }
  

  

  
  
  
  
  
  /**
   * 创建分类
   * @param classification
   */
  
  @Test(alwaysRun=true)
  @Parameters({"classification"})
  public void createFenLei(String classification) {

	 this.stk.clickclassifyManage();
	  
	 
	  this.stk.clickNewClassifyButton();
	  
	 this.stk.inputClassifyName(classification);
	 this.stk.clickSave();
	 
	  AssertJUnit.assertEquals("新增成功!",this.stk.getTip().getText());
	  this.stk.confirm();
	  
	    
  }
  /**
   * 编辑第一个分类的名称为新的分类名
   * @param new_classify
   */
  @Test(alwaysRun=true)
  @Parameters({"classify","new_classify"})
  public void editFenLei(String classify,String new_classify){
	  
	  this.stk.clickclassifyManage();
	  //定位旧的分类编辑按钮
	 this.stk.clickeditClassifyButton(classify);
	  
	 
	 this.stk.inputClassifyName(new_classify);

	 this.stk.clickSave();
	 
	  Wait.waitMilliSeconds(5000);
	  AssertJUnit.assertEquals("修改成功!", this.stk.getTip().getText());
	  this.stk.confirm();

  }
  
  /**
   * 删除一个分类
   */
  
  @Test(alwaysRun=true)
  @Parameters("classify_name")
  public void deleteFenLei(String classify_name){
	  this.stk.clickclassifyManage();
	  Wait.waitMilliSeconds(5000);

	  this.stk.clickdeleteClassifyButton(classify_name);
	  Wait.waitMilliSeconds(5000);
	  
	  
	  AssertJUnit.assertEquals("确定要删除该分类吗？",this.stk.getTip().getText());
	  Wait.waitMilliSeconds(5000);
	  
	  this.stk.confirm();
	  
	  Wait.waitMilliSeconds(5000);
	  
	  AssertJUnit.assertEquals("删除成功!",this.stk.getTip().getText());
	  Wait.waitMilliSeconds(5000);
	  
	  this.stk.confirm();
	  Wait.waitMilliSeconds(5000);
	  
  }
  
  /**
   * 新建题库
   * @param TK_name
   */

  @Parameters({"TK_name","TK_type"})
  @Test(alwaysRun=true)
  public void createTK(String TK_name,String TK_type){
	  
	  //新建题库按钮
	  this.stk.clickNewTKButton();

	  Wait.waitMilliSeconds(5000);
	  
	 this.stk.inputTKname(TK_name);
  
	  
	 this.stk.chooseTKtype(TK_type);
	  
	 this.stk.saveTkButton();
	 
	 driver=this.stk.assertAlertTip("新增成功!");
	 
	  
  }
  
  
  /**
   * 在分类管理页面返回题库
   */
/*  @Test(alwaysRun=true)
  public void returnTK(){
	  Actions action=new Actions(driver);
	  action.moveToElement( driver.findElement(By.cssSelector("i.icon.icon-back"))).perform();
	  Wait.waitMilliSeconds(5000);	  
	  driver.findElement(By.id("go_test_list_btn"));
	  Wait.waitMilliSeconds(5000);	 
	  
  }*/
  
  
  /**
   * 预览题库
   */
  @Test(alwaysRun=true)
  @Parameters({"tk_name"})
  public void previewTK(String tk_name){
	  
	  WebElement target=this.stk.findTk(tk_name); 
	 
	  this.stk.clickTkPreviewButton(target);
	  AssertJUnit.assertEquals(tk_name, this.stk.findTkName().substring(1, 5).trim());
  }
  
  /**
   * 编辑题库名
   * @param new_TK_name
   */
  @Test(alwaysRun=true)
  @Parameters({"tk_name","new_tk_name"})
  public void editTK(String tk_name,String new_TK_name){
	  
	  WebElement target=this.stk.findTk(tk_name);
	  
	 driver=this.stk.clickTkEditButton(target);
	  
	 this.stk.inputTKname(new_TK_name);
	 
	 this.stk.saveTkButton();
	 
	  Wait.waitMilliSeconds(10000);
	 
	  driver=this.stk.assertAlertTip("修改成功!");
	  
  }
  /**
   * 删除题库
   */
  @Test(alwaysRun=true)
  @Parameters({"tk_name"})
  public void deleteTK(String tk_name){
	  
	  WebElement target=this.stk.findTk(tk_name);	  
	  this.stk.clickTkDeleteButton(target);
	  AssertJUnit.assertEquals("确定要删除该题库吗？", this.stk.getTip().getText());
	  Wait.waitMilliSeconds(5000);
	  this.stk.confirm();
	  Wait.waitMilliSeconds(5000);
	  AssertJUnit.assertEquals("删除成功！", this.stk.getTip().getText());
	  Wait.waitMilliSeconds(5000);
	  this.stk.confirm();
	  Wait.waitMilliSeconds(5000);
	  

  }
  /**
   * 进入题库
   */
  @Test(alwaysRun=true)
  @Parameters({"tk_name"})
  public void enterTK(String tk_name){
	  
	  WebElement target=this.stk.findTk(tk_name);
	  this.stk.clickTkEnterButton(target);
	  AssertJUnit.assertEquals(tk_name,this.stk.findTkName());
  
  }
  
  
  
  

}
