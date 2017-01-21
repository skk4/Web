package com.yoya.entities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.yoya.inf.QuestionInf;
import com.yoya.util.Wait;

public class TKQuestion implements QuestionInf {

	public String q_title;
	public String[] q_answers;
	public String q_analysis;
	public String q_score;
	public TKQuestion(String question_url){
		try {
			File questionFile=new File(question_url);
			FileReader fr=new FileReader(questionFile);
			BufferedReader br=new BufferedReader(fr);
			
			q_title=br.readLine().substring(7);
			q_answers=br.readLine().substring(6).split(";");
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
		//填标题
		WebElement title = driver.findElement(By.id("title"));
		title.clear();
		Wait.waitMilliSeconds(5000);
		title.sendKeys(this.q_title);
		Wait.waitMilliSeconds(5000);

		int blankets = this.q_answers.length;

		for (int i = 1; i < blankets; i++) {
			driver.findElement(By.xpath("//div[@name='edit_a']/a")).click();
			Wait.waitMilliSeconds(3000);
		}

		List<WebElement> answers = driver.findElements(By.xpath("//div[@id='answer_div']/div[@name='asdiv']"));
		for (int i = 0; i < blankets; i++) {
			WebElement input = answers.get(i).findElement(By.xpath("./input[@name='answer_item']"));
			input.clear();
			input.sendKeys(this.q_answers[i]);
			Wait.waitMilliSeconds(5000);
		}

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
		WebElement title = driver.findElement(By.id("title"));
		title.clear();
		Wait.waitMilliSeconds(5000);
		title.sendKeys(this.q_title);
		Wait.waitMilliSeconds(5000);
		//现在的空格要求
		int blankets = this.q_answers.length;
		//当前空格
		List<WebElement> answers = driver.findElements(By.xpath("//div[@id='answer_div']/div[@name='asdiv']"));
		int answers_count=answers.size();
		if(blankets>answers_count){
			int temp=blankets-answers_count;
			for (int i = 1; i <= temp; i++) {
				driver.findElement(By.xpath("//div[@name='edit_a']/a[1]")).click();
				Wait.waitMilliSeconds(3000);
			}
		}else if(blankets<answers_count){
			int temp=answers_count-blankets;
			for (int i = 1; i <= temp; i++) {
				driver.findElement(By.xpath("//div[@name='edit_a']/a[2]")).click();
				Wait.waitMilliSeconds(3000);
			}
		}
		answers=driver.findElements(By.xpath("//div[@id='answer_div']/div[@name='asdiv']"));
		for (int i = 0; i < blankets; i++) {
			WebElement input = answers.get(i).findElement(By.xpath("./input[@name='answer_item']"));
			input.clear();
			input.sendKeys(this.q_answers[i]);
			Wait.waitMilliSeconds(5000);
		}

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
