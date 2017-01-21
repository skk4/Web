package com.yoya.page.favorite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.yoya.util.Wait;

public class Favorite {
	private WebDriver driver;
	
	public Favorite(WebDriver driver){
		this.driver=driver;
	}
	
	public WebElement findMovie(String movie_name){
		return driver.findElement(By.xpath("//div[@id='list_model']/ul/li/h3[text()='"+movie_name+"']"));
	}
	
	public void clickPlayBtn(WebElement movie){
		Actions action=new Actions(driver);
		  action.moveToElement(movie.findElement(By.xpath("./../div[@class='movies']"))).click().perform();
		  
		  Wait.waitMilliSeconds(5000);
	}
	
	public void clickCancelBtn(WebElement movie){
		movie.findElement(By.xpath("./../a[@name='del_favorite']")).click();
		Wait.waitMilliSeconds(5000);
	}
	
}
