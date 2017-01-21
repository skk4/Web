package com.yoya.page.movieplay;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MoviePlay {
	private WebDriver driver;
	
	public MoviePlay(WebDriver driver){
		this.driver=driver;
	}
	
	public String getFavoriteStatus(){
		return driver.findElement(By.id("sc")).getText();
	}
}
