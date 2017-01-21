package com.yoya.test;
import static com.yoya.util.ElementExistOrNot.elementExist;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yoya.page.MyYoya;
import com.yoya.test.login.LoginTest;
import com.yoya.util.Wait;

/**
 * 主要用于验证用户登录后，我的优芽页面元素存在性的验证
 * @author Administrator
 *
 */
public class MyYoyaTest {
	
	public WebDriver driver;	
	private MyYoya myyoya;
	
  @BeforeTest
  @Parameters({"web_driver","host_url","user_name","pass_word"})
  public void beforeTest(String web_driver,String host_url,String user_name,String pass_word)  { 
	  
	  LoginTest lg=new LoginTest();
	  driver=lg.login(web_driver, host_url, user_name, pass_word);
	  myyoya=new MyYoya(driver);
  }

  @AfterTest
  public void afterTest() {
	  Wait.waitMilliSeconds(5000);
	  driver.quit();
  }

  /**
   * 主站底部关于优芽
   */
  
  @Test(alwaysRun=true)

  public void aboutYoya(){
	  //关于优芽
	  AssertJUnit.assertEquals("关于优芽", this.myyoya.getAboutYoya().getText().trim());
	  //媒体报道  
	  AssertJUnit.assertTrue(elementExist(driver,By.linkText("媒体报道")));
	  //关于我们
	  AssertJUnit.assertTrue(elementExist(driver,By.linkText("关于我们")));
	  	  
	  //加入我们
	  AssertJUnit.assertTrue(elementExist(driver,By.linkText("加入我们")));
  }
  
  /**
   * 主站底部合作加盟

   */
  @Test(alwaysRun=true)
  public void coroperation(){
	  //合作加盟
	  AssertJUnit.assertEquals("合作加盟",this.myyoya.getCoroperation().getText().trim());
	  
	  //渠道合作
	  AssertJUnit.assertTrue(elementExist(driver,By.linkText("渠道合作")));
	  
	  
	  //机构入驻
	  AssertJUnit.assertTrue(elementExist(driver,By.linkText("机构入驻")));
	 

	  //开放接口
	  AssertJUnit.assertTrue(elementExist(driver,By.linkText("开放接口")));
	  
  }
  
  /**
   * 主站底部帮助中心

   */
  @Test(alwaysRun=true)
 
  public void helpCenter(){

	  //帮助中心
	  AssertJUnit.assertEquals("帮助中心", this.myyoya.gethelpCenter().getText().trim());
	  
	  
	//常见问题
	  AssertJUnit.assertTrue(elementExist(driver,By.linkText("常见问题")));
	  
	  
	//意见反馈
	  AssertJUnit.assertTrue(elementExist(driver,By.linkText("意见反馈")));
	  
	//服务条款
	  AssertJUnit.assertTrue(elementExist(driver,By.linkText("服务条款")));
  }
  
  /**
   * 主站底部客服服务

   */
  @Test(alwaysRun=true)
  
  public void customService(){
	  //客服服务
	  AssertJUnit.assertEquals("客服服务", this.myyoya.getcustomService().getText().trim());
	    
	  //在线客服
	  AssertJUnit.assertTrue(elementExist(driver,By.cssSelector("i.icon.icon-QQ-online")));
	  AssertJUnit.assertEquals("在线客服", this.myyoya.getonline_qq().getText());
	  
	  //热线电话4008-116-309
	  AssertJUnit.assertTrue(elementExist(driver,By.cssSelector("i.icon.icon-phone")));
	  AssertJUnit.assertEquals("4008-116-309", this.myyoya.gethot_line().getText());
	
	  //邮箱
	  AssertJUnit.assertTrue(elementExist(driver,By.cssSelector("i.icon.icon-email")));
	  AssertJUnit.assertTrue(elementExist(driver,By.linkText("service@yoya.com")));
  }
  
  /**
   * 主站底部添加微信号
   
   */
  @Test(alwaysRun=true)
 
  public void joinWechat(){
	  //验证二维码
	  AssertJUnit.assertTrue(elementExist(driver, By.xpath("//div[@class='wgt-footer']/div[1]/dl[5]/dd/span[@class='qrcode']")));
	  
	  //关注微信
	  AssertJUnit.assertEquals("关注微信", this.myyoya.getjoin_wechat().getText());

  }
  
  /**
   * 主站底部公司商标和通讯地址

   */
  @Test(alwaysRun=true)
 
  public void websiteFooter(){
	  
	  //slogan
	  AssertJUnit.assertTrue(elementExist(driver, By.xpath("//div[@class='wgt-footer']/div[2]/p[@class='slogan']")));

	  //icp
	  AssertJUnit.assertTrue(elementExist(driver, By.partialLinkText("闽ICP备")));
	  
	  //copyright
	  String copyright=this.myyoya.getcopyright().getText().trim();
	  AssertJUnit.assertTrue(copyright.contains("COPYRIGHT ©"));
	  AssertJUnit.assertTrue(copyright.contains("版权所有"));
	  //年份
	  AssertJUnit.assertEquals("2015", this.myyoya.getrelease_year().getText());

	  
	  //yoya官网地址
	  AssertJUnit.assertTrue(elementExist(driver, By.linkText("优芽网YOYA.COM")));
  
	  //yoya地址
	  AssertJUnit.assertEquals("厦门市思明区莲前西路595-597号益马国际大厦二楼", this.myyoya.getaddress().getText());

  }
  
  
  /**
   * 顶部logo
   */
  @Test(alwaysRun=true)
  public void logoVerify(){
	  Wait.waitMilliSeconds(5000);
	  //logo
	  AssertJUnit.assertTrue(elementExist(driver,By.className("logo-img")));
	  AssertJUnit.assertTrue(elementExist(driver,By.className("logo-text")));
  }
  
  /**
   * 顶部菜单
   * @param home_page
   * @param interact_movie
   * @param yoya_competion
   * @param yoya_org
   */
  @Test(alwaysRun=true)
  public void menuSiteVerify(){
	  Wait.waitMilliSeconds(5000);
	  //菜单选项
	  //首页
	  AssertJUnit.assertTrue(elementExist(driver,By.xpath("//div[@class=\"menu-site\"]/ul/li/a[@menuid=\"index\"]")));
	  WebElement index=driver.findElement(By.xpath("//div[@class=\"menu-site\"]/ul/li/a[@menuid=\"index\"]"));
	  AssertJUnit.assertEquals("首页", index.getText());
	  
	//互动电影
	  AssertJUnit.assertTrue(elementExist(driver,By.xpath("//div[@class=\"menu-site\"]/ul/li/a[@menuid='hddy']")));
	  WebElement hddy=driver.findElement(By.xpath("//div[@class=\"menu-site\"]/ul/li/a[@menuid='hddy']"));
	  AssertJUnit.assertEquals("互动电影", hddy.getText());
	  
	//优芽赛事
	  AssertJUnit.assertTrue(elementExist(driver,By.xpath("//div[@class=\"menu-site\"]/ul/li/a[@menuid='bs']")));
	  WebElement yyss=driver.findElement(By.xpath("//div[@class=\"menu-site\"]/ul/li/a[@menuid='bs']"));
	  AssertJUnit.assertEquals("优芽赛事", yyss.getText());
	  
	  //热门活动
	  AssertJUnit.assertTrue(elementExist(driver,By.xpath("//div[@class=\"menu-site\"]/ul/li/a[@menuid='active']")));
	  WebElement rmhd=driver.findElement(By.xpath("//div[@class=\"menu-site\"]/ul/li/a[@menuid='active']"));
	  AssertJUnit.assertEquals("热门活动", rmhd.getText());
	  
	  //优芽机构
	  AssertJUnit.assertTrue(elementExist(driver,By.xpath("//div[@class=\"menu-site\"]/ul/li/a[@menuid='org']")));
	  WebElement yyjg=driver.findElement(By.xpath("//div[@class=\"menu-site\"]/ul/li/a[@menuid='org']"));
	  AssertJUnit.assertEquals("优芽机构", yyjg.getText());
  }
  
  /**
   * 优芽APP下载
   */
  @Test(alwaysRun=false)
  public void menuUserAppVerify(){
	  //下载图标
	  AssertJUnit.assertTrue(elementExist(driver,By.cssSelector("i.icon.icon-download")));	  
	  //验证app图标
	  AssertJUnit.assertEquals("优芽APP",driver.findElement(By.xpath("//div[@class=\"menu-user\"]/a[@class='app']/span")).getText());
  }
  
  
  
  /**
   * 顶部帮助
   * @param help
   * @param qq_service
   * @param service_center
   * @param proposal_feedback
   */
  @Test(alwaysRun=true)
  public void menuUserHelpVerify(){
	  Wait.waitMilliSeconds(5000);
	  //帮助图标
	  AssertJUnit.assertTrue(elementExist(driver,By.cssSelector("i.icon.icon-help")));
	  
	//鼠标移动到图标上触发动作
	  Actions action=new Actions(driver);
	                                                                                                       
	  AssertJUnit.assertTrue(elementExist(driver,By.xpath("//div[@class=\"menu-user\"]/div[@class='help']/span")));
	                                                       
	  AssertJUnit.assertEquals("帮助",driver.findElement(By.xpath("//div[@class=\"menu-user\"]/div[@class='help']/span")).getText());
	 
	  //客服服务
	  
	  WebElement help1=driver.findElement(By.xpath("//div[@class=\"menu-user\"]/div[@class='help']/span"));
	  action.moveToElement(help1).perform();	  
	  Wait.waitMilliSeconds(2000);
	  AssertJUnit.assertTrue(elementExist(driver,By.cssSelector("i.icon.icon-dropdown-QQ")));
	  AssertJUnit.assertTrue(elementExist(driver,By.id("_yoya_top_qqkf")));
	  WebElement qqkf=driver.findElement(By.id("_yoya_top_qqkf"));
	  AssertJUnit.assertEquals("在线客服", qqkf.getText()); 
	  
	//帮助中心
	  AssertJUnit.assertTrue(elementExist(driver,By.cssSelector("i.icon.icon-dropdown-help")));
	  WebElement bzzx=driver.findElement(By.cssSelector("i.icon.icon-dropdown-help")).findElement(By.xpath("./.."));
	  AssertJUnit.assertEquals("帮助中心", bzzx.getText());
	 
	//意见反馈
	  AssertJUnit.assertTrue(elementExist(driver,By.cssSelector("i.icon.icon-dropdown-feedback")));
	  WebElement yjfk=driver.findElement(By.cssSelector("i.icon.icon-dropdown-feedback")).findElement(By.xpath("./.."));
	  AssertJUnit.assertEquals("意见反馈", yjfk.getText());
	  
  }
  
  /**
   * 顶部消息
   * @param message
   */
  @Test(alwaysRun=true)
  @Parameters()
  public void menuUserMessageVerify(){
	//消息
	  Wait.waitMilliSeconds(2000);
	  AssertJUnit.assertTrue(elementExist(driver,By.cssSelector("i.icon.icon-message")));
	  AssertJUnit.assertTrue(elementExist(driver,By.id("top_messageId")));
	  WebElement top_message=driver.findElement(By.id("top_messageId"));
	  AssertJUnit.assertEquals("消息", top_message.getText());
	  
	   
  }
  
  
  
  
  /**
   * 顶部账户信息

   */
  @Test(alwaysRun=true)
  public void menuUserVerify(){

	  Actions action=new Actions(driver);
	//头像
	  Wait.waitMilliSeconds(2000);
	  AssertJUnit.assertTrue(elementExist(driver,By.id("top_head_img")));
	  
	  
	//触发头像的下拉菜单,我的优芽
	  action.moveToElement(driver.findElement(By.id("top_head_img"))).perform();
	  Wait.waitMilliSeconds(2000);
	  AssertJUnit.assertTrue(elementExist(driver,By.cssSelector("i.icon.icon-dropdown-yoya")));
	  WebElement myyoya=driver.findElement(By.cssSelector("i.icon.icon-dropdown-yoya")).findElement(By.xpath("./.."));
			  AssertJUnit.assertEquals("我的优芽", myyoya.getText());
	  
	//我是设计
	  Assert.assertTrue(elementExist(driver,By.cssSelector("i.icon.icon-bulb")));
	  WebElement designer=driver.findElement(By.cssSelector("i.icon.icon-bulb")).findElement(By.xpath("./.."));
			  AssertJUnit.assertEquals("我是设计", designer.getText());
	  
	//我要开课
	  AssertJUnit.assertTrue(elementExist(driver,By.cssSelector("i.icon.icon-dropdown-class")));
	  WebElement wykk=driver.findElement(By.cssSelector("i.icon.icon-dropdown-class")).findElement(By.xpath("./.."));
	  AssertJUnit.assertEquals("我要开课", wykk.getText());
	  
	//账户设置
	  AssertJUnit.assertTrue(elementExist(driver,By.cssSelector("i.icon.icon-dropdown-account")));
	  AssertJUnit.assertTrue(elementExist(driver,By.id("top_safe")));
	  WebElement zhsz=driver.findElement(By.id("top_safe"));
	  AssertJUnit.assertEquals("账户设置", zhsz.getText());
	  
	//我的收藏
	  AssertJUnit.assertTrue(elementExist(driver,By.cssSelector("i.icon.icon-dropdown-collection")));
	  AssertJUnit.assertTrue(elementExist(driver,By.id("top_favorite")));
	  WebElement sc=driver.findElement(By.id("top_favorite"));
	  AssertJUnit.assertEquals("我的收藏", sc.getText()); 
	  
	//我的反馈
	  AssertJUnit.assertTrue(elementExist(driver,By.cssSelector("i.icon.icon-dropdown-myFeedback")));
	  AssertJUnit.assertTrue(elementExist(driver,By.id("top_suggest")));
	  WebElement fk=driver.findElement(By.id("top_suggest"));
	  AssertJUnit.assertEquals("我的反馈", fk.getText());
	  	  
	  //退出登录
	  AssertJUnit.assertTrue(elementExist(driver,By.cssSelector("i.icon.icon-dropdown-loginOut")));
	  AssertJUnit.assertTrue(elementExist(driver,By.id("top_ssoLogoutId")));
	  WebElement tcdl=driver.findElement(By.id("top_ssoLogoutId"));
	  AssertJUnit.assertEquals("退出登录", tcdl.getText());
	  	  
  }
  
  /**
   * 用户头像，昵称，优豆，VIP图标验证
   */
  @Test(alwaysRun=true)
  public void userInfoVerify(){
   
	  Wait.waitMilliSeconds(5000);
	  //头像
	  AssertJUnit.assertTrue(elementExist(driver,By.id("lsmenu_head_img")));
	  
	  //其他信息
	  AssertJUnit.assertTrue(elementExist(driver,By.id("lsmenu_n_name")));
	  AssertJUnit.assertTrue(elementExist(driver,By.xpath("//div[@class='user-name']/span[@class='text']")));
	  AssertJUnit.assertTrue(elementExist(driver,By.xpath("//div[@class='u-pay']/img")));
	  AssertJUnit.assertTrue(elementExist(driver,By.id("lsmenu_youdou_num")));
	  AssertJUnit.assertTrue(elementExist(driver,By.id("lsmenu_vip_img")));
	  

  }
  
  /**
   * 互动电影，我的机构，课程班，我的收藏

   */
  @Test(alwaysRun=true)
 
  public void itemListVerify(){
	  //互动电影
	  AssertJUnit.assertTrue(elementExist(driver,By.cssSelector("i.icon.icon-interactive-movie")));	  
	  AssertJUnit.assertTrue(elementExist(driver,By.id("ucpanel_hddyCount")));
	  AssertJUnit.assertTrue(elementExist(driver,By.xpath("//span[@id='ucpanel_hddyCount']/preceding-sibling::label")));
	  WebElement interactive=driver.findElement(By.xpath("//span[@id='ucpanel_hddyCount']/preceding-sibling::label"));
	  AssertJUnit.assertEquals("互动电影：", interactive.getText());
	  
	  //我的机构
	  AssertJUnit.assertTrue(elementExist(driver,By.cssSelector("i.icon.icon-my-institution")));
	  AssertJUnit.assertTrue(elementExist(driver,By.id("ucpanel_orgCount")));
	  AssertJUnit.assertTrue(elementExist(driver,By.xpath("//span[@id='ucpanel_orgCount']/preceding-sibling::label")));
	  WebElement institution=driver.findElement(By.xpath("//span[@id='ucpanel_orgCount']/preceding-sibling::label"));
	  AssertJUnit.assertEquals("我的机构：", institution.getText());
	  
	  
	  //课程班
	  AssertJUnit.assertTrue(elementExist(driver,By.cssSelector("i.icon.icon-course-class")));
	  AssertJUnit.assertTrue(elementExist(driver,By.id("ucpanel_classCount")));
	  AssertJUnit.assertTrue(elementExist(driver,By.xpath("//span[@id='ucpanel_classCount']/preceding-sibling::label")));
	  WebElement course=driver.findElement(By.xpath("//span[@id='ucpanel_classCount']/preceding-sibling::label"));
	  AssertJUnit.assertEquals("课程班：", course.getText());
	  
	  
	  //我的收藏
	  AssertJUnit.assertTrue(elementExist(driver,By.cssSelector("i.icon.icon-my-collection")));
	  AssertJUnit.assertTrue(elementExist(driver,By.id("ucpanel_scCount")));
	  AssertJUnit.assertTrue(elementExist(driver,By.xpath("//span[@id='ucpanel_scCount']/preceding-sibling::label")));
	  WebElement collection=driver.findElement(By.xpath("//span[@id='ucpanel_scCount']/preceding-sibling::label"));
	  AssertJUnit.assertEquals("我的收藏：", collection.getText());
  }
  
  /**
   * 互动电影页面元素验证
   * @param hd_movie
   * @param newly_built
   * @param all
   * @param unfl
   * @param sort_link
   */
  @Test(alwaysRun=true)
  public void  hddyElementsExist(){
	  //互动电影
	  AssertJUnit.assertTrue(elementExist(driver,By.cssSelector("i.iconfont.icon-hdmovie")));
	  
	  AssertJUnit.assertEquals("互动电影", driver.findElement(By.xpath("//div[@class='wgt-uc-menu']/ul/li[1]/a/span")).getText());
	  	  
	  AssertJUnit.assertEquals("互动电影",driver.findElement(By.xpath("//div[@class='wgt-uc-company']/div[@class='page-header']/div[contains(@class,'title')]/a[@data-type='movie']")).getText());
	  AssertJUnit.assertEquals("购买的模板",driver.findElement(By.xpath("//div[@class='wgt-uc-company']/div[@class='page-header']/div[contains(@class,'title')]/a[@data-type='template']")).getText());
		 
	  
	  
	  AssertJUnit.assertTrue(elementExist(driver,By.cssSelector("i.icon.icon-newly-built")));
	  AssertJUnit.assertEquals("新建", driver.findElement(By.xpath("//div[@class='wgt-uc-company']/div[@class='page-header']/div[@class='page-opts']/a/span")).getText());
	  

	  AssertJUnit.assertEquals("全部", driver.findElement(By.id("all")).getText());
	  AssertJUnit.assertEquals("未分组", driver.findElement(By.id("unfl")).getText());
	  AssertJUnit.assertEquals("[分组管理]", driver.findElement(By.className("sort-link")).getText());

  }
  
  /**
   * 我的课程页面元素验证
  
   */
  @Test(alwaysRun=true)
  public void my_course(){
	  //我的课程
	  
	  AssertJUnit.assertTrue(elementExist(driver,By.cssSelector("i.iconfont.icon-course")));
	  AssertJUnit.assertTrue(elementExist(driver,By.xpath("//div[@class='wgt-uc-menu']/ul/li[2]/a/span")));
	  WebElement mycource=driver.findElement(By.xpath("//div[@class='wgt-uc-menu']/ul/li[2]/a/span"));
	  AssertJUnit.assertEquals("我的课程", mycource.getText());
  }
  
  /**
   * 课程班
   */
  @Test(alwaysRun=true)

  public void kcbElementsExist(){
	  AssertJUnit.assertTrue(elementExist(driver,By.cssSelector("i.iconfont.icon-classroom")));
	  AssertJUnit.assertEquals("课程班",driver.findElement(By.xpath("//a[@id='my_student']/span")).getText());
	  WebElement kcb=driver.findElement(By.id("my_student"));

  }
  
  /**
   * 试题库
   */
  @Test(alwaysRun=true)
  public void stkElementsExist(){
	  AssertJUnit.assertTrue(elementExist(driver,By.cssSelector("i.iconfont.icon-lib")));
	  AssertJUnit.assertTrue(elementExist(driver,By.linkText("试题库")));
	 
  }
  
  /**
   * 我的云盘
   */
  @Test(alwaysRun=true)
  
  public void wdypElementsExist(){
	  AssertJUnit.assertTrue(elementExist(driver,By.cssSelector("i.iconfont.icon-cloud")));
	  AssertJUnit.assertTrue(elementExist(driver,By.linkText("我的云盘")));
	  
  }
  
  /**
   * 我的机构
   */
  @Test(alwaysRun=true)

  public void wdjgElementsExist(){
	  AssertJUnit.assertTrue(elementExist(driver,By.cssSelector("i.iconfont.icon-org")));
	  WebElement jg=driver.findElement(By.linkText("我的机构")); 
  }
  
  /**
   * 我的订单

   */
  @Test(alwaysRun=true)

  public void wdddElementsExist(){
	  AssertJUnit.assertTrue(elementExist(driver,By.cssSelector("i.iconfont.icon-order"))); 
	  WebElement dd=driver.findElement(By.linkText("我的订单"));
  }
  
  
  /**
   * 我的VIP
   */
  @Test(alwaysRun=true)
  public void myVIP(){
	  Assert.assertTrue(elementExist(driver,By.cssSelector("i.iconfont.icon-vip")));
	  Assert.assertTrue(elementExist(driver,By.linkText("我的VIP")));
	  WebElement vip=driver.findElement(By.linkText("我的VIP"));
/*	  vip.click();
	  Wait.waitMilliSeconds(5000);
	  
	  AssertJUnit.assertEquals("我的VIP",driver.findElement(By.xpath("//div[@class='wgt-uc-company']/div[@class='page-header']/div[@class='title']")).getText());
	  
	  Assert.assertTrue(elementExist(driver,By.id("userVipImg")));
	  Assert.assertEquals("普通vip",driver.findElement(By.id("userVipName")).getText());
	  Assert.assertEquals("尊享VIP特权，解锁更多功能",driver.findElement(By.id("userVipInfo")).getText().trim());
	  
	  
	  Assert.assertTrue(elementExist(driver,By.cssSelector("i.icon.icon-privilege-function")));
	  Assert.assertEquals("功能特权",driver.findElement(By.xpath("//div[@id='img_tq']//span[@class='pink']")).getText());
	  
	  
	  Assert.assertTrue(elementExist(driver,By.cssSelector("i.icon.icon-privilege-resources")));
	  Assert.assertEquals("资源特权",driver.findElement(By.xpath("//div[@id='img_tq']//span[@class='orange']")).getText());
	  
	  
	  Assert.assertTrue(elementExist(driver,By.cssSelector("i.icon.icon-privilege-storage")));
	  Assert.assertEquals("存储特权",driver.findElement(By.xpath("//div[@id='img_tq']//span[@class='green']")).getText());
	  
	  Assert.assertTrue(elementExist(driver,By.cssSelector("i.icon.icon-privilege-brand")));
	  Assert.assertEquals("品牌特权",driver.findElement(By.xpath("//div[@id='img_tq']//span[@class='blue']")).getText());
	  
	  Assert.assertEquals("马上开通",driver.findElement(By.xpath("//div[@id='canOpBtn']/button")).getText());
*/	  
  }

}
