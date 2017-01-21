package com.yoya.test.yun;
import static com.yoya.util.ElementExistOrNot.elementExist;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yoya.page.Dialog;
import com.yoya.page.yun.MyYunMyMaterial;
import com.yoya.util.ElementExistOrNot;
import com.yoya.util.MyYunAssist;
import com.yoya.util.Wait;


/**
 * 我的素材
 * @author Administrator
 *
 */
public class MyYunMyMaterialTest {
  
	public WebDriver driver;
	private MyYunMyMaterial mymaterial;
	@BeforeTest
	@Parameters({"web_driver","host_url","user_name","pass_word"})
	public void beforeTest(String web_driver,String host_url,String user_name,String pass_word){
		driver=MyYunAssist.enterMyYun(web_driver, host_url, user_name, pass_word);
		
		this.mymaterial=new MyYunMyMaterial(driver);
	}
	
	
	
	@AfterTest
	public void afterTest(){
		//Wait.waitMilliSeconds(5000);
		//driver.quit();
	}

  /**
   * 验证已给的素材文件夹是否存在
   * @param file_name
   */
  @Test(alwaysRun=true)
  @Parameters({"file_names"})
  public void assertFileDir(String file_names){
	  
	  String[] dir_names=file_names.split(",");
	  int size=dir_names.length;
	  Wait.waitMilliSeconds(5000);
	  //点击进入我的素材
	  this.mymaterial.enterMyMaterial();
	  
	  //断言所给文件是否存在
	  for(int i=0;i<size;i++){
		  Assert.assertTrue(elementExist(driver,this.mymaterial.findBy(dir_names[i])));
	  }
	  
	  driver.switchTo().defaultContent();
  
  }
  /**
   * 在某个素材文件夹中上传素材
   * @param file_name
   * @param material_url
   */
  @Test(alwaysRun=true)
  @Parameters({"file_name","material_url"})
  public void uploadMaterial(String file_name,String material_url){
	  Wait.waitMilliSeconds(5000);
	  //点击进入我的素材
	  this.mymaterial.enterMyMaterial();
	  //等待加载
	  //Wait.waitMilliSeconds(10000);
	  
	  //选择文件
	  this.mymaterial.findDirectory(file_name);

	  this.mymaterial.clickUploadBtn();
	  
	 this.mymaterial.uploadFile(material_url);
	  
	 this.mymaterial.confirmUpload();
	  //该行不能休眠
	  //文件上传中
	  Assert.assertEquals(this.mymaterial.getTip().getText().trim(),"文件上传中");
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  
	  //上传成功1个
	  Assert.assertEquals(this.mymaterial.getTip().getText().trim(),"上传成功1个");
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.switchTo().defaultContent();
	  
  }
  
  @Test(alwaysRun=true)
  @Parameters({"file_directory","file_name"})
  public void downloadSC(String file_directory,String file_name){
	  
	  //点击进入我的素材
	  this.mymaterial.enterMyMaterial();

	  //选择文件夹
	  this.mymaterial.findDirectory(file_directory);
	  
	  //寻找素材
	  WebElement sc=this.mymaterial.findSC(file_name);
	  
	  //下载
	  this.mymaterial.clickDownloadBtn(sc);
  }
  
  
  @Test(alwaysRun=true)
  @Parameters({"file_directory","file_name"})
  public void deleteSC(String file_directory,String file_name){
	  
	  //点击进入我的素材
	  this.mymaterial.enterMyMaterial();

	  //选择文件夹
	  this.mymaterial.findDirectory(file_directory);
	  
	  //寻找素材
	  WebElement sc=this.mymaterial.findSC(file_name);
	  
	  //下载
	  this.mymaterial.clickDeleteBtn(sc);
	  
	  //删除确认框
	  Dialog d=new Dialog(driver);
	  Assert.assertEquals(d.getContent(),"您确定要删除？");
	  d.clickconfirmBtn();
	  Wait.waitMilliSeconds(5000);
	  
	  //断言已经不存在该素材
	  Assert.assertFalse(ElementExistOrNot.elementExist(driver, this.mymaterial.findBy(file_name)));
  }
  
  @Test(alwaysRun=true)
  @Parameters({"file_directory","file_name"})
  public void previewSC(String file_directory,String file_name){
	  //点击进入我的素材
	  this.mymaterial.enterMyMaterial();

	  //选择文件夹
	  this.mymaterial.findDirectory(file_directory);
	  
	  //寻找素材
	  WebElement sc=this.mymaterial.findSC(file_name);
	  
	  //预览
	  this.mymaterial.clickPreviewBtn(sc);
  }
  
  @Test(alwaysRun=true)
  @Parameters({"file_directory","file_name","new_scname"})
  public void renameSC(String file_directory,String file_name,String new_scname){
	  //点击进入我的素材
	  this.mymaterial.enterMyMaterial();

	  //选择文件夹
	  this.mymaterial.findDirectory(file_directory);
	  
	  //寻找素材
	  WebElement sc=this.mymaterial.findSC(file_name);
	  
	  //重命名
	  WebElement input=this.mymaterial.clickRenameBtn(sc);
	  
	  
	  //输入新的名字
	  this.mymaterial.inputNewSCname(input, new_scname);
	  
	  //确认修改
	  this.mymaterial.clickConfirmBtn(file_name);
	  Wait.waitMilliSeconds(5000);
	  Assert.assertTrue(ElementExistOrNot.elementExist(driver, this.mymaterial.findBy(new_scname)));
  }
  
  @Test(alwaysRun=true)
  @Parameters({"file_directory","file_name"})
  public void remarkSC(String file_directory,String file_name){
	  this.mymaterial.enterMyMaterial();
	  //选择文件夹
	  this.mymaterial.findDirectory(file_directory);	  
	  //寻找素材
	  WebElement sc=this.mymaterial.findSC(file_name);

	  //点击标签按钮
	  this.mymaterial.clickRemarkBtn(sc);
  }
  
  
}
