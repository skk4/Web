package com.yoya.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.yoya.util.DriverExchangeAssist;
import com.yoya.util.LocateElement;
import com.yoya.util.LocateIframe;
import com.yoya.util.Wait;

public class InteractiveMovie {
	private WebDriver driver;
	
	public InteractiveMovie(WebDriver driver){
		this.driver=driver;
	}
	
	public void enterGroupTab(String group_name){
		WebElement tab=LocateElement.locateElementUseText(driver, "//div[@id='allSort']/a", group_name);
		tab.click();
		Wait.waitMilliSeconds(5000);
	}
	
	
	public void clickGroupManage(){
		 driver.findElement(By.className("sort-link")).click(); 
	}
	
	
	public void clickNewGroup(){
		Wait.waitMilliSeconds(5000);
		driver.findElement(By.cssSelector("i.icon.icon-newly-built")).click();
	}
	
	
	
	public void inputGroupName(String group_name){
			driver.findElement(By.id("dir_name")).clear();
		  driver.findElement(By.id("dir_name")).sendKeys(group_name);
	}
	
	public void clickSave(){
		driver.findElement(By.id("savaBtn")).click();
	}
	
	public void clickConfirm(){
		driver.findElement(By.className("layui-layer-btn0")).click();
	}
	
	
	public WebElement getSuccussTip(){
		return Wait.explicitlyWait(driver, 10, By.xpath("//div[starts-with(@id,'layui-layer') and @type='dialog']/div"));	
	}
	public WebElement getMessageTip(){
		Wait.implicitlyWait(driver, 10);
		return driver.findElement(By.xpath("//div[starts-with(@id,'layui-layer') and @type='dialog']/div[2]"));
	}
	
	public WebElement findGroup(String group_name){
		WebElement target=LocateElement.locateElementUseText(driver, "//tbody[@id='movie_classify_list']//td", group_name);
		return target;
	}
	
	public void editGroup(WebElement target,String new_group_name){
		target.findElement(By.xpath("./..//div[@class='opts']//i[@title='编辑']")).click();
		  driver.findElement(By.id("dir_name")).clear();
		  driver.findElement(By.id("dir_name")).sendKeys(new_group_name);

	}
	
	public void deleteGroup(WebElement target){
		target.findElement(By.xpath("./..//div[@class='opts']//i[@title='删除']")).click();
	}
	
	/**
	 *进入互动电影页
	 */
	public void enterInteractiveMovie(){
		driver.findElement(By.xpath("//a[@data-type='movie']")).click();
		Wait.waitMilliSeconds(5000);
	}
	
	
	/**
	 * 进入购买的模板
	 */
	
	public void enterTemplateMarket(){
		driver.findElement(By.xpath("//a[@data-type='template']")).click();
		Wait.waitMilliSeconds(5000);
	}
	
	
	/**
	 * 进入模板市场
	 */
	public WebDriver clickTemplateMarketBtn(){
		driver.findElement(By.xpath("//div[@id='template_list_src']/div[@class='form-btns']/a")).click();
		Wait.waitMilliSeconds(5000);

		return DriverExchangeAssist.exchangeDriverUseTitle(driver, "互动电影模版");
	}
	
	/**
	 * 查找电影
	 */
	public WebElement findMovie(String movie_name){
		 WebElement movie=driver.findElement(By.xpath("//div[@id='movie_list_src']/ul/li[@data-title='"+movie_name+"']"));
		 return movie;
	}
	
	public WebDriver clickExportBtn(WebElement movie){
		 Actions action=new Actions(driver);
		  action.moveToElement(movie).perform();
		  Wait.waitMilliSeconds(5000);
		  
		  WebElement export=movie.findElement(By.xpath("./div[@class='page-opts']/div[@class='wrap']/a[contains(@class,'exportMovie')]"));
		  
		  action.moveToElement(export).perform();
		  Wait.waitMilliSeconds(5000);
		  export.click();
		  
		  driver=LocateIframe.locateIframe(driver, "layui-layer-iframe");
		  return driver;
	}
	
	
	public WebDriver clickEditMovieBtn(WebElement movie){
		 Actions action=new Actions(driver);
		  action.moveToElement(movie).perform();
		  Wait.waitMilliSeconds(5000);
		  
		  WebElement editbtn=movie.findElement(By.xpath("./div[@class='box']/div[@class='opts-mask']/div[@class='wrap']/a[1]"));
		  editbtn.click();
		  Wait.waitMilliSeconds(5000);
		  
		  driver=DriverExchangeAssist.exchangeDriverUseTitle(driver, "互动电影");
		  driver.manage().window().maximize();
		  return driver;
	}
	
	public WebDriver clickPreviewMovieBtn(WebElement movie,String movie_title){
		Actions action=new Actions(driver);
		  action.moveToElement(movie).perform();
		  Wait.waitMilliSeconds(5000);
		  
		  WebElement editbtn=movie.findElement(By.xpath("./div[@class='box']/div[@class='opts-mask']/div[@class='wrap']/a[2]"));
		  editbtn.click();
		  Wait.waitMilliSeconds(5000);
		  
		  driver=DriverExchangeAssist.exchangeDriverUseTitle(driver, movie_title);
		  driver.manage().window().maximize();
		  return driver;
	}
	
	public WebDriver clickPublishMovieBtn(WebElement movie){
		Actions action=new Actions(driver);
		  action.moveToElement(movie).perform();
		  Wait.waitMilliSeconds(5000);
		  
		  WebElement publishbtn=movie.findElement(By.xpath("./div[@class='box']/div[@class='opts-mask']/div[@class='wrap']/a[3]"));
		  publishbtn.click();
		  
		  driver=LocateIframe.locateIframe(driver, "layui-layer-iframe");
		  
		  return driver;
	}
	
	public WebDriver clickApplyBtn(WebElement movie){
		Actions action=new Actions(driver);
		  action.moveToElement(movie).perform();
		  Wait.waitMilliSeconds(5000);
		  WebElement apply=movie.findElement(By.xpath("./div[@class='page-opts']/div[@class='wrap']/a[contains(@class,'applyTemplate')]"));
		  
		  action.moveToElement(apply).perform();
		  Wait.waitMilliSeconds(5000);
		  apply.click();
		  
		  driver=LocateIframe.locateIframe(driver, "layui-layer-iframe");
		  return driver;
	}
	
	public WebDriver clickCopyBtn(WebElement movie){
		Actions action=new Actions(driver);
		  action.moveToElement(movie).perform();
		  Wait.waitMilliSeconds(5000);
		  WebElement copy=movie.findElement(By.xpath("./div[@class='page-opts']/div[@class='wrap']/a[contains(@class,'copyMovie')]"));
		  
		  action.moveToElement(copy).perform();
		  Wait.waitMilliSeconds(5000);
		  copy.click();
		  
		  return driver;
	}
	
	public WebDriver clickGroupBtn(WebElement movie){
		Actions action=new Actions(driver);
		  action.moveToElement(movie).perform();
		  Wait.waitMilliSeconds(5000);
		  WebElement group=movie.findElement(By.xpath("./div[@class='page-opts']/div[@class='wrap']/a[contains(@class,'assort')]"));
		  
		  action.moveToElement(group).perform();
		  Wait.waitMilliSeconds(5000);
		  group.click();
		  
		  return driver;
	}
	
	
	
	public WebDriver clickApplyConfirmBtn(){
		driver.findElement(By.cssSelector("button.btn.btn-8")).click();
		  driver.switchTo().defaultContent();
		  return driver;
	}
	
	
	public void chooseFreeOrNot(String fee_type){
		if(fee_type.contains("免费")){
			  driver.findElement(By.cssSelector("div.radio-box-2.is_free_0")).click();
		  }else{
			  String fee=fee_type.substring(3);
			  driver.findElement(By.cssSelector("div.radio-box-2.is_free_1")).click();
			  
			  driver.findElement(By.id("price")).sendKeys(fee);
		  }
	}
	
	public WebDriver clickDeleteBtn(WebElement movie){
		Actions action=new Actions(driver);
		  action.moveToElement(movie).perform();
		  Wait.waitMilliSeconds(5000);
		  
		  WebElement delete=movie.findElement(By.xpath("./div[@class='page-opts']/div[@class='wrap']/a/i[contains(@class,'icon-del-1')]"));
		  action.moveToElement(delete).perform();
		  Wait.waitMilliSeconds(5000);
		  delete.click();
		  Wait.waitMilliSeconds(5000);
		  return driver;
	}
	
	
	public String getTemplateName(){
		return driver.findElement(By.id("template_name")).getAttribute("value");
	}
	
	
	public void chooseMovieType(String movie_type){
		String[] types=movie_type.split(",");
		driver.findElement(By.id("share_classify")).click();
		 Wait.waitMilliSeconds(5000);
		 int count=0;
		 for(String type:types){
			 if(count==0){
				 WebElement e=driver.findElement(By.xpath("//div[@class='options-list']/ul[@id='classify_tree_src']//li/a[@data-value='"+type+"']"));
				 Wait.waitMilliSeconds(5000); 
				 e.click();
				 
			 }
			 if(count==1){
				 WebElement e1=driver.findElement(By.xpath("//div[@class='options-list']/div[@class='options-list-2']//li/a[@data-value='"+type+"']"));
				 Actions action=new Actions(driver);
				 action.moveToElement(e1).click().perform();
				 Wait.waitMilliSeconds(5000); 
				
			 }
			 if(count==2){
				 WebElement e2=driver.findElement(By.xpath("//div[@class='options-list']/div[@class='options-list-3']//li/a[@data-value='"+type+"']"));
				 Actions action=new Actions(driver);
				 action.moveToElement(e2).perform();
				 Wait.waitMilliSeconds(5000); 
				 e2.click();
			 }
			 count++;
			 Wait.waitMilliSeconds(5000); 

		 }
		 
	}
	
	public void clickSureBtn(){
		driver.findElement(By.id("sure")).click();
		Wait.waitMilliSeconds(5000);
	}
	
	public String getPublishAndShareTitle(){
		return driver.findElement(By.xpath("//div[starts-with(@id,'layui-layer') and @type='iframe']/div[@class='layui-layer-title']")).getText();
	}
	
	
	
	public String getMovieName(){
		return driver.findElement(By.id("share_name")).getAttribute("value");
	}
	
	

}
