package com.yoya.entities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.yoya.inf.QuestionInf;
import com.yoya.util.LocateElement;
import com.yoya.util.Wait;

public class SimpleChoiceQuestion implements QuestionInf {

	public String q_title;
	public String[] q_choices;
	public String q_answer;
	public String q_analysis;
	public String q_score;
	
	
	public SimpleChoiceQuestion(){
		//blank
	}
	
	public SimpleChoiceQuestion(String question_url){
		
		try {
			File questionFile=new File(question_url);
			FileReader fr=new FileReader(questionFile);
			BufferedReader br=new BufferedReader(fr);
			
			q_title=br.readLine().substring(7);
			q_choices=br.readLine().substring(6).split(";");
			q_answer=br.readLine().substring(6);
			q_analysis=br.readLine().substring(6);
			q_score=br.readLine().substring(4);
		
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
/*	public static void main(String[] args){
		SimpleChoiceQuestion scq=new SimpleChoiceQuestion("F:\\WebAutomation\\addSimpleChoice.txt");
		System.out.println(scq.toString());
	}*/
	
	
	@Override
	public WebDriver addQuestion(WebDriver driver) {
		// TODO Auto-generated method stub
		
		WebElement title=driver.findElement(By.id("title"));
		title.clear();
		Wait.waitMilliSeconds(5000);
		title.sendKeys(this.q_title);
		Wait.waitMilliSeconds(5000);
		
		WebElement choice=LocateElement.locateElementUsePartialText(driver, "//tr[@id='itemArea']//td[@name='itemTitle']", this.q_answer);
		choice.findElement(By.xpath("./..//input[@type='checkbox']")).click();
		Wait.waitMilliSeconds(5000);
		
		
		WebElement item1=driver.findElement(By.xpath("//tr[@id='itemArea']//tbody//tr[1]/td[3]/textarea"));
		item1.clear();
		item1.sendKeys(this.q_choices[0]);
		Wait.waitMilliSeconds(5000);
		
		WebElement item2=driver.findElement(By.xpath("//tr[@id='itemArea']//tbody//tr[2]/td[3]/textarea"));
		item2.clear();
		item2.sendKeys(this.q_choices[1]);
		Wait.waitMilliSeconds(5000);
		
		
		WebElement item3=driver.findElement(By.xpath("//tr[@id='itemArea']//tbody//tr[3]/td[3]/textarea"));
		item3.clear();
		item3.sendKeys(this.q_choices[2]);
		Wait.waitMilliSeconds(5000);
		
		
		WebElement item4=driver.findElement(By.xpath("//tr[@id='itemArea']//tbody//tr[4]/td[3]/textarea"));
		item4.clear();
		item4.sendKeys(this.q_choices[3]);
		Wait.waitMilliSeconds(5000);
		
		//高级设置
		driver.findElement(By.xpath("//button[@id='submitBtn']/../a")).click();
		Wait.waitMilliSeconds(5000);
		WebElement score=driver.findElement(By.xpath("//input[@name='score']"));
		score.clear();
		score.sendKeys(this.q_score);
		Wait.waitMilliSeconds(5000);
		
		driver.findElement(By.xpath("//textarea[@name='answer_analysis']")).sendKeys(this.q_analysis);
		Wait.waitMilliSeconds(5000);
		
		driver.findElement(By.id("submitBtn")).click();
		Wait.waitMilliSeconds(5000);
		Alert alert=driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "新增成功");
		alert.accept();
		
		return driver;
	}
	@Override
	public WebDriver editQuestion(WebDriver driver) {
		// TODO Auto-generated method stub
		WebElement title=driver.findElement(By.id("title"));
		title.clear();
		Wait.waitMilliSeconds(5000);
		title.sendKeys(this.q_title);
		Wait.waitMilliSeconds(5000);
		
		//先去除旧答案
		driver.findElement(By.xpath("//tr[@id='itemArea']//tr[@name='itemTr']//input[@checked='checked']")).click();
		Wait.waitMilliSeconds(5000);
		
		
		//选择正确的答案
		WebElement choice=LocateElement.locateElementUsePartialText(driver, "//tr[@id='itemArea']//td[@name='itemTitle']", this.q_answer);
		choice.findElement(By.xpath("./..//input[@type='checkbox']")).click();
		Wait.waitMilliSeconds(5000);
		
		
		WebElement item1=driver.findElement(By.xpath("//tr[@id='itemArea']//tbody//tr[1]/td[3]/textarea"));
		item1.clear();
		item1.sendKeys(this.q_choices[0]);
		Wait.waitMilliSeconds(5000);
		
		WebElement item2=driver.findElement(By.xpath("//tr[@id='itemArea']//tbody//tr[2]/td[3]/textarea"));
		item2.clear();
		item2.sendKeys(this.q_choices[1]);
		Wait.waitMilliSeconds(5000);
		
		
		WebElement item3=driver.findElement(By.xpath("//tr[@id='itemArea']//tbody//tr[3]/td[3]/textarea"));
		item3.clear();
		item3.sendKeys(this.q_choices[2]);
		Wait.waitMilliSeconds(5000);
		
		
		WebElement item4=driver.findElement(By.xpath("//tr[@id='itemArea']//tbody//tr[4]/td[3]/textarea"));
		item4.clear();
		item4.sendKeys(this.q_choices[3]);
		Wait.waitMilliSeconds(5000);
		
		//高级设置
		driver.findElement(By.xpath("//button[@id='submitBtn']/../a")).click();
		Wait.waitMilliSeconds(5000);
		WebElement score=driver.findElement(By.xpath("//input[@name='score']"));
		score.clear();
		score.sendKeys(this.q_score);
		Wait.waitMilliSeconds(5000);
		
		WebElement analysis=driver.findElement(By.xpath("//textarea[@name='answer_analysis']"));
		analysis.clear();
		analysis.sendKeys(this.q_analysis);
		Wait.waitMilliSeconds(5000);
		
		driver.findElement(By.id("submitBtn")).click();
		Wait.waitMilliSeconds(5000);
		Alert alert=driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "修改成功");
		alert.accept();
		
		return driver;
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String result="";
		result+=this.q_title+" "+this.q_answer+" "+this.q_analysis+" "+this.q_score;
		String temp="";
		for(String s:this.q_choices){
			temp+=s;
		}
		return result+"\n"+temp;
	}


	
	
	

}
