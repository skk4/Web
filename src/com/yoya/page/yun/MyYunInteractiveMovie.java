package com.yoya.page.yun;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.yoya.util.MyYunAssist;
import com.yoya.util.Wait;

public class MyYunInteractiveMovie {
	private WebDriver driver;
	
	public MyYunInteractiveMovie(WebDriver driver){
		this.driver=driver;
	}
	
	
	/**
	 * 进入互动电影
	 */
	public void enterInteractiveMovie(){
		driver.findElement(By.linkText("互动电影")).click();
		MyYunAssist.locateIframe(driver, "main");
		Wait.waitMilliSeconds(5000);
	}
	
	
	public void moveToMovie(String movie_name){
		Actions action=new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']"+"//tr[@data-name='"+movie_name+"']"))).perform();
	}
	
	public void clickDeleteMovieBtn(String movie_name){
		Actions action1=new Actions(driver);
		action1.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']"+"//tr[@data-name='"+movie_name+"']"+"//a[@class='delete']"))).perform();
		
		Wait.waitMilliSeconds(5000);
		driver.findElement(By.xpath("//table[@id='file_list']"+"//tr[@data-name='"+movie_name+"']"+"//a[@class='delete']")).click();
		
	}
	
	public void clickRenameMovieBtn(String old_movie_name){
		  Actions action1=new Actions(driver);
		  action1.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+old_movie_name+"']"+"/td//span[@class='btn-val']"))).perform();
		  
		  Actions action2=new Actions(driver);
		  action2.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+old_movie_name+"']"+"/td//a[@class='rename']"))).perform();

		  Wait.waitMilliSeconds(5000);
		  driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+old_movie_name+"']"+"/td//a[@class='rename']")).click();

	}
	
	public WebElement getCotentTip(){
		return driver.findElement(By.className("layui-layer-content"));
	}
	
	public WebElement getTitleTip(){
		return driver.findElement(By.className("layui-layer-title"));
	}
	
	public void confirm(){
		driver.findElement(By.className("layui-layer-btn0")).click();
		
	}
	public void sure(){
		driver.findElement(By.id("sure")).click();
	}
	
	public void confirmMove(){
		driver.findElement(By.id("move_file")).click();
	}
	
	public WebDriver clickShareMovieBtn(String movie_name){
		Actions action1=new Actions(driver);
		action1.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']"+"//tr[@data-name='"+movie_name+"']"+"//a[@class='share']"))).perform();
		Wait.waitMilliSeconds(5000);
		driver.findElement(By.xpath("//table[@id='file_list']"+"//tr[@data-name='"+movie_name+"']"+"//a[@class='share']")).click();
		Wait.waitMilliSeconds(5000);
		MyYunAssist.locateIframe(driver, "layui-layer-iframe");
		return driver;
	}
	
	public void clickEditMovieBtn(String movie_name){
		  Actions action1=new Actions(driver);
		  action1.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+movie_name+"']"+"/td//span[@class='btn-val']"))).perform();
		  
		  Actions action2=new Actions(driver);
		  action2.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+movie_name+"']"+"/td//a[@class='edit']"))).perform();
		  
		  Wait.waitMilliSeconds(5000);
		  
		  driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+movie_name+"']"+"/td//a[@class='edit']")).click();
		 
	}
	
	public WebDriver clickMarkMovieBtn(String movie_name){
		Actions action1=new Actions(driver);
		  action1.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+movie_name+"']"+"/td//span[@class='btn-val']"))).perform();		  
		  Actions action2=new Actions(driver);
		  action2.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+movie_name+"']"+"/td//a[@class='mark']"))).perform();
		  Wait.waitMilliSeconds(5000);
		  
		  driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+movie_name+"']"+"/td//a[@class='mark']")).click();
		  
		  MyYunAssist.locateIframe(driver, "layui-layer-iframe");
		  return driver;
	}
	
	public WebDriver clickCopyToOrgBtn(String movie_name){
		Actions action1 = new Actions(driver);
		action1.moveToElement(driver.findElement(By.xpath(
				"//table[@id='file_list']//tr[@data-name='" + movie_name + "']" + "/td//span[@class='btn-val']")))
				.perform();
		Actions action2 = new Actions(driver);
		action2.moveToElement(driver.findElement(
				By.xpath("//table[@id='file_list']//tr[@data-name='" + movie_name + "']" + "/td//a[@class='copyToOrg']")))
				.perform();
		Wait.waitMilliSeconds(5000);
		driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+movie_name+"']"+"/td//a[@class='copyToOrg']")).click();
		
		Wait.waitMilliSeconds(5000);
		MyYunAssist.locateIframe(driver, "layui-layer-iframe");
		
		return driver;
	}
	
	public void clickCopyMovieBtn(String movie_name){
		  Actions action1=new Actions(driver);
		  action1.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+movie_name+"']"+"/td//span[@class='btn-val']"))).perform();
		  
		  Actions action2=new Actions(driver);
		  action2.moveToElement(driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+movie_name+"']"+"/td//a[@class='copy']"))).perform();
	
		  Wait.waitMilliSeconds(5000);
		  driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+movie_name+"']"+"/td//a[@class='copy']")).click();

	}
	
	public WebDriver clickExportMovieBtn(String movie_name){
		Actions action1 = new Actions(driver);
		action1.moveToElement(driver.findElement(By.xpath(
				"//table[@id='file_list']//tr[@data-name='" + movie_name + "']" + "/td//span[@class='btn-val']")))
				.perform();
		Actions action2 = new Actions(driver);
		action2.moveToElement(driver.findElement(
				By.xpath("//table[@id='file_list']//tr[@data-name='" + movie_name + "']" + "/td//a[@class='export']")))
				.perform();
		Wait.waitMilliSeconds(5000);
		driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+movie_name+"']"+"/td//a[@class='export']")).click();
		MyYunAssist.locateIframe(driver, "layui-layer-iframe");
		return driver;
	}
	
	public void chooseMovieType(String movie_name){
		Wait.waitMilliSeconds(5000);
		Assert.assertEquals(driver.findElement(By.id("share_name")).getAttribute("value"), movie_name);
		driver.findElement(By.id("share_classify")).click();
		Wait.waitMilliSeconds(5000);
		driver.findElement(By.xpath("//ul[@id='classify_tree_src']//a[@data-value='教育']")).click();
		Wait.waitMilliSeconds(5000);
		
		driver.findElement(By.xpath("//div[@class='options-list-2']/ul//div[@class='jspPane']/li/a[@data-value='学前教育']")).click();
		Wait.waitMilliSeconds(5000);
	}
	
	public void inputMovieName(String old_movie_name,String new_movie_name){
		  WebElement we=driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+old_movie_name+"']"+"/td[@class='t-filename']/a/input"));
		  Wait.waitMilliSeconds(5000);
		  we.clear();
		  we.sendKeys(new_movie_name);
		  Wait.waitMilliSeconds(5000);
		  driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+old_movie_name+"']"+"//button[@class='sure']")).click();

	}
	
	public WebElement findMovie(String new_movie_name){
		return driver.findElement(By.xpath("//table[@id='file_list']//tr[@data-name='"+new_movie_name+"']"+"/td[@class='t-filename']"+"/a/span[@class='name']"));
	}
	
	public void chooseOrg(String org_name){
		driver.findElement(By.xpath("//div[@id='tree_container']//a[@title='"+org_name+"']"+"/following-sibling::ul/li/a/span[2]")).click();
		Wait.waitMilliSeconds(5000);
	}
	
	public WebElement getCopyTip(){
		return driver.findElement(By.xpath("//div[starts-with(@id,'layui-layer')]/div[2]"));
	}
	public WebElement getSucessTip(){
		return driver.findElement(By.xpath("//*[@id=\"xubox_layer1\"]/div[1]/div/span[2]"));
	}
	
	
	
	public void inputMark(String mark_name){
		 WebElement markinput=driver.findElement(By.id("tags_2_tag"));
		  markinput.clear();
		  Wait.waitMilliSeconds(5000);
		  markinput.sendKeys(mark_name);
	}
	
	
	public void save(){
		driver.findElement(By.id("saveLabel")).click();
	}
	
	/**
	 * export_way："导出播放文件","导出播放代码","导出图片漫画","导出文本文档"
	 * 分别对应到 export_code: 1,2,3,4
	 * @param export_way
	 * @param export_code
	 */
	public void exportMovie(String export_way,String export_code){
		List<WebElement> ways=driver.findElements(By.xpath("//div[@class='tab-items']/ul//div[@class='title']"));
		
		for(WebElement we:ways){
			String way=we.getText();
			if(way.equalsIgnoreCase(export_way)){
				we.click();
				Wait.waitMilliSeconds(5000);
				break;
			}
		}
		//导出
		driver.findElement(By.id("exp")).click();
		Wait.waitMilliSeconds(5000);
		switch(export_code){
		case "1":
			export_movie_way(driver);
			break;
		case "2":
			export_code_way(driver);
			break;
		case "3":
			export_cartoon_way(driver);
			break;
		case "4":
			export_word_way(driver);
			break;
			default:
				new IllegalArgumentException("导出方式出错！");
		}
	}
	
	/**
	 * 导出播放文件
	 * @param driver
	 * @return
	 */
	public WebDriver export_movie_way(WebDriver driver){
		Wait.waitMilliSeconds(5000);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='export-sure']/p")).getText(),"导演,您的导出需要消耗2个优豆。");
		driver.findElement(By.id("sure")).click();
		Wait.waitMilliSeconds(5000);
		driver.findElement(By.xpath("//div[starts-with(@id,'layui-layer')]/span/a")).click();
		return driver;
	}
	
	/**
	 * 导出播放代码
	 * @param driver
	 * @return
	 */
	public WebDriver export_code_way(WebDriver driver){
		Wait.waitMilliSeconds(5000);
		driver.findElement(By.id("btn-url-copy")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[starts-with(@id,'layui-layer') and @type='dialog']/div")).getText(), "复制成功！");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.id("btn-code-copy")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[starts-with(@id,'layui-layer') and @type='dialog']/div")).getText(), "复制成功！");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[starts-with(@id,'layui-layer')]/span/a")).click();
		return driver;
	}
	
	/**
	 * 导出图片漫画
	 * @param driver
	 * @return
	 */
	public WebDriver export_cartoon_way(WebDriver driver){
		
		Wait.waitMilliSeconds(5000);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='export-sure']/p")).getText(),"导演,您的导出需要消耗1个优豆。");
		driver.findElement(By.id("sure")).click();
		Wait.waitMilliSeconds(5000);
		driver.findElement(By.xpath("//div[starts-with(@id,'layui-layer')]/span/a")).click();
		return driver;
		
	}
	/**
	 * 导出文本文档
	 * @param driver
	 * @return
	 */
	public WebDriver export_word_way(WebDriver driver){
		
		driver.findElement(By.xpath("//div[starts-with(@id,'layui-layer')]/span/a")).click();
		return driver;
	}
	
	
}
