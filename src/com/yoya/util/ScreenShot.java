package com.yoya.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
/**
 * 屏幕截屏
 * @author Administrator
 *
 */
public class ScreenShot {
	
	static{
		PropertyConfigurator.configure("./properties/log4j.properties");
	}
	
	private static Logger log4j=Logger.getLogger(ScreenShot.class);
	
	public static void getScreenShot(WebDriver driver,String currentPath,String fileName){
		File scrFile=null;
		if(driver instanceof FirefoxDriver){
			scrFile=((FirefoxDriver)driver).getScreenshotAs(OutputType.FILE);
		}else if(driver instanceof ChromeDriver){
			scrFile=((ChromeDriver)driver).getScreenshotAs(OutputType.FILE);
		}else if(driver instanceof InternetExplorerDriver){
			scrFile=((InternetExplorerDriver)driver).getScreenshotAs(OutputType.FILE);
		}else{
			log4j.error("bad driver is exist!!! Checked it please!");
		}
		try {
			FileUtils.copyFile(scrFile, new File(currentPath+"\\"+fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log4j.error("copy file <"+fileName+"> to disk failed!!!");
		}
		
		
	}

}
