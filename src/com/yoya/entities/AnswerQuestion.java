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
import com.yoya.util.Wait;

public class AnswerQuestion implements QuestionInf {

	public String q_title;
	public String q_answer;
	public String q_analysis;
	public String q_score;
	
	public AnswerQuestion(String question_url){
		try {
			File questionFile=new File(question_url);
			FileReader fr=new FileReader(questionFile);
			BufferedReader br=new BufferedReader(fr);
			
			q_title=br.readLine().substring(7);
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
	@Override
	public WebDriver addQuestion(WebDriver driver) {
		// TODO Auto-generated method stub
		//输入标题
		WebElement title = driver.findElement(By.id("title"));
		title.clear();
		Wait.waitMilliSeconds(5000);
		title.sendKeys(this.q_title);
		Wait.waitMilliSeconds(5000);

		WebElement answer = driver.findElement(By.xpath("//div[@id='answer_div']/div[@name='asdiv']/textarea"));
		answer.clear();
		answer.sendKeys(this.q_answer);
		Wait.waitMilliSeconds(5000);

		// 高级设置
		driver.findElement(By.xpath("//button[@id='submitBtn']/../a")).click();
		Wait.waitMilliSeconds(5000);
		WebElement score = driver.findElement(By.xpath("//input[@name='score']"));
		score.clear();
		score.sendKeys(this.q_score);
		Wait.waitMilliSeconds(5000);

		driver.findElement(By.xpath("//textarea[@name='answer_analysis']")).sendKeys(this.q_analysis);
		Wait.waitMilliSeconds(5000);

		driver.findElement(By.id("submitBtn")).click();
		Wait.waitMilliSeconds(5000);
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "新增成功");
		alert.accept();

		return driver;
	}

	@Override
	public WebDriver editQuestion(WebDriver driver) {
		// TODO Auto-generated method stub
		//输入标题
		WebElement title = driver.findElement(By.id("title"));
		title.clear();
		Wait.waitMilliSeconds(5000);
		title.sendKeys(this.q_title);
		Wait.waitMilliSeconds(5000);

		WebElement answer = driver.findElement(By.xpath("//div[@id='answer_div']/div[@name='asdiv']/textarea"));
		answer.clear();
		answer.sendKeys(this.q_answer);
		Wait.waitMilliSeconds(5000);

		// 高级设置
		driver.findElement(By.xpath("//button[@id='submitBtn']/../a")).click();
		Wait.waitMilliSeconds(5000);
		WebElement score = driver.findElement(By.xpath("//input[@name='score']"));
		score.clear();
		score.sendKeys(this.q_score);
		Wait.waitMilliSeconds(5000);

		driver.findElement(By.xpath("//textarea[@name='answer_analysis']")).sendKeys(this.q_analysis);
		Wait.waitMilliSeconds(5000);

		driver.findElement(By.id("submitBtn")).click();
		Wait.waitMilliSeconds(5000);
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "修改成功");
		alert.accept();

		return driver;
	}

	



}
