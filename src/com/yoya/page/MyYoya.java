package com.yoya.page;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.yoya.util.DriverExchangeAssist;
import com.yoya.util.Wait;

public class MyYoya {
	private WebDriver driver;
	
	public MyYoya(WebDriver driver){
		this.driver=driver;
	}
	
	public String getNickname(){
		return driver.findElement(By.id("lsmenu_n_name")).getText();
	}
	public String getYoudou(){
		return driver.findElement(By.id("lsmenu_youdou_num")).getText().substring(3);
	}
	
	
	public String getMovieNum(){
		return driver.findElement(By.id("ucpanel_hddyCount")).getText();
	}
	
	public String getOrgNum(){
		return driver.findElement(By.id("ucpanel_orgCount")).getText();
	}
	
	public String getClassNum(){
		return driver.findElement(By.id("ucpanel_classCount")).getText();
	}
	
	public String getFavNum(){
		return driver.findElement(By.id("ucpanel_scCount")).getText();
	}
	
	
	public WebElement getAboutYoya(){
		return driver.findElement(By.xpath("//div[@class='wgt-footer']/div[1]/dl[1]/dt"));
	}
	
	
	
	public WebElement getCoroperation(){
		return driver.findElement(By.xpath("//div[@class='wgt-footer']/div[1]/dl[2]/dt"));
	}
	
	
	public WebElement gethelpCenter(){
		return driver.findElement(By.xpath("//div[@class='wgt-footer']/div[1]/dl[3]/dt"));
	}
	
	public WebElement getcustomService(){
		return driver.findElement(By.xpath("//div[@class='wgt-footer']/div[1]/dl[4]/dt"));
	}
	public WebElement getonline_qq(){
		return driver.findElement(By.id("_yoya_bottom_qqkf"));
	}
	
	public WebElement gethot_line(){
		return driver.findElement(By.xpath("//div[@class='wgt-footer']/div[1]/dl[4]/dd/span"));
	}
	
	public WebElement getjoin_wechat(){
		return driver.findElement(By.xpath("//div[@class='wgt-footer']/div[1]/dl[5]/dd/span[@class='text']"));
	}
	
	public WebElement getcopyright(){
		return driver.findElement(By.xpath("//div[@class='wgt-footer']/div[2]/p[@class='copyright']"));
	}
	
	public WebElement getrelease_year(){
		return driver.findElement(By.xpath("//div[@class='wgt-footer']/div[2]/p[@class='copyright']/span"));
	}
	
	public WebElement getaddress(){
		return driver.findElement(By.xpath("//div[@class='wgt-footer']/div[2]/p[@class='addr']"));
	}
	
	/**
	 * 进入个人基本资料页
	 * @return
	 */
	public WebDriver enterPersonalDetailsPage(){
		
		Actions action=new Actions(driver);
		Wait.implicitlyWait(driver, 10);
		//Wait.waitMilliSeconds(5000);
		  WebElement head_img=driver.findElement(By.id("lsmenu_head_img"));
		  action.moveToElement(head_img).perform();
		  WebElement edit=driver.findElement(By.cssSelector("a.icon.icon-edit-big"));	  
		  edit.click();
		  driver=DriverExchangeAssist.exchangeDriverUsePartialUrl(driver, "http://test.yoya.com/doView?action=v_main&start=st_info");
		  Wait.waitMilliSeconds(5000);
		  return driver;
	}
	
	/**
	 * 进入我的VIP
	 */
	public WebDriver enterMyVIP(){
		driver.findElement(By.linkText("我的VIP")).click();
		Wait.waitMilliSeconds(5000);
		return driver;
	}
	
	
	/**
	 * 进入我的机构
	 */
	public WebDriver enterMyOrg(){
		driver.findElement(By.linkText("我的机构")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	
	/**
	 * 我的订单
	 */
	public WebDriver enterMyOrder(){
		 driver.findElement(By.linkText("我的订单")).click();
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  return driver;
	}
	
	  /**
	   * 进入我的课程
	   */
	 public WebDriver enterMyCourse(){
		 driver.findElement(By.linkText("我的课程")).click();
		 Wait.waitMilliSeconds(5000);
		 return driver;
	 }
	 
	  /**
	   * 进入课程班
	   */
	  public WebDriver enterKCB(){
		  WebElement kcb=driver.findElement(By.id("my_student"));
			kcb.click();
			Wait.waitMilliSeconds(5000);
			return driver;
	  }
	  
	  /**
	   * 进入试题库页面
	   */
	  public WebDriver enterSTK(){
		 
			WebElement stk=driver.findElement(By.cssSelector("i.iconfont.icon-lib"));
			stk.click();
			Wait.waitMilliSeconds(5000);
			return driver;
	  }
	  
	  
	  /**
	   * 进入我的云盘页面
	   */
	  public WebDriver enterMyYun(){
		//主窗口
			String home_window=driver.getWindowHandle();
			driver.findElement(By.linkText("我的云盘")).click();
			Wait.waitMilliSeconds(5000);
			//所有窗口
			Set<String> ws=driver.getWindowHandles();
			
			//找到云盘窗口
			for(String current:ws){
				if(!current.equalsIgnoreCase(home_window)){
					driver.switchTo().window(current);
					break;
				}
				
			}
			return driver;
	  }
	  
	  
	  /**
	   * 进入我是设计页面
	   */
	  public WebDriver enterMyDesiger(){
		  Actions action=new Actions(driver);
		  action.moveToElement(driver.findElement(By.id("top_head_img"))).perform();
		  Wait.waitMilliSeconds(2000);
		  
		  driver.findElement(By.cssSelector("i.icon.icon-bulb")).click();
		  
		  Wait.waitMilliSeconds(5000);
		  return driver;
	  }
	  
	  
	  /**
	   * 进入意见反馈
	   */
	  public WebDriver enterOptionsfeedback(){
		  Actions action=new Actions(driver);
		  action.moveToElement(driver.findElement(By.cssSelector("i.icon.icon-help"))).perform();
		  Wait.waitMilliSeconds(2000);
		  
		  driver.findElement(By.cssSelector("i.icon.icon-dropdown-feedback")).click();
		  Wait.waitMilliSeconds(5000);
		  
		  return driver;
	  }
	 
	  /**
	   * 进入我的反馈页面
	   */
	  public WebDriver enterMyFeedback(){
		  Actions action=new Actions(driver);
		  action.moveToElement(driver.findElement(By.id("top_head_img"))).perform();
		  Wait.waitMilliSeconds(2000);
		  
		  driver.findElement(By.id("top_suggest")).click();
		  
		  Wait.waitMilliSeconds(5000);
		  return driver;
	  }
	
	  /**
	   * 进入购买的模板
	   */
	  public WebDriver enterBuyedTemplate(){
		  WebElement temp=driver.findElement(By.xpath("//div[contains(@class,'title')]/a[@data-type='template']"));
		  temp.click();
		  Wait.waitMilliSeconds(5000);
		  return driver;
	  }
	  /**
	   * 进入消息
	   */
	  public WebDriver enterMessage(){
		  driver.findElement(By.id("top_messageId")).click();
		  Wait.waitMilliSeconds(5000);
		  return driver;
	  }
	  
	  
	  /**
	   * 进入我的收藏
	   */
	  public WebDriver enterMyfavorite(){
		  Actions action=new Actions(driver);
		  action.moveToElement(driver.findElement(By.id("top_head_img"))).perform();
		  
		  driver.findElement(By.id("top_favorite")).click();
		  Wait.waitMilliSeconds(5000);
		  return driver;
	  }
	  
	
}
