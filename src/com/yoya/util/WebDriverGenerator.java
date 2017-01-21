package com.yoya.util;

import java.util.Enumeration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

/**
 * 根据不同字符串生成不同的 WebDriver实例
 * @author lin
 *
 */

public class WebDriverGenerator {
	
	private static Properties properties=null;
	
	/**
	 * 通过xml配置文件生成WebDriver实例
	 * @param web_driver
	 * @return
	 * 
	 */
	public static WebDriver generateWebDriver(String web_driver){
		
		properties=PropertyConfigure.configure("./properties/system.properties");
		
		if(!web_driver.equalsIgnoreCase(BrowserType.FIREFOX)){
			System.setProperty(properties.getProperty("browser.driver.type"), properties.getProperty("browser.driver.url"));
		}
		System.setProperty(properties.getProperty("browser.bin.type"), properties.getProperty("browser.bin.url"));
		
		
		WebDriver driver=null;
		
		if(web_driver.equalsIgnoreCase(BrowserType.FIREFOX)){
			
			Properties p=PropertyConfigure.configure("./properties/firefox.properties");
			Enumeration<Object> keys=p.keys();
			FirefoxProfile profile=new FirefoxProfile();
			
			while(keys.hasMoreElements()){
				String key=(String)keys.nextElement();
				String value=p.getProperty(key);
				profile.setPreference(key, value);
			}
			driver=new FirefoxDriver(profile);
		}else if(web_driver.equalsIgnoreCase(BrowserType.CHROME)){
			ChromeOptions co=new ChromeOptions();
			co.addArguments("user-data-dir=C:\\Users\\Administrator\\AppData\\Local\\Google\\Chrome\\User Data\\Default");
			co.addArguments("start-maximized");
			driver=new ChromeDriver(co);
		}else if(web_driver.equalsIgnoreCase(BrowserType.IE)){  //internet explorer
			driver=new  InternetExplorerDriver();
		}
		
		
		/*if(web_driver.equalsIgnoreCase(BrowserType.FIREFOX)){
			DesiredCapabilities capability = DesiredCapabilities.firefox();
			try {
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(web_driver.equalsIgnoreCase(BrowserType.CHROME)){
			
		}else if(web_driver.equalsIgnoreCase(BrowserType.IE)){
			
		}*/
		
		
		return driver;
	}
	
}
