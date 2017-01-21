package com.yoya.test.designer;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yoya.page.MyYoya;
import com.yoya.page.designer.CashierRecord;
import com.yoya.test.login.LoginTest;
import com.yoya.util.Wait;

public class CashierRecordTest {

	private WebDriver driver;
	private CashierRecord cashier;
	  @BeforeTest
	  @Parameters({"web_driver","host_url","user_name","pass_word"})
	  public void beforeTest(String web_driver,String host_url,String user_name,String pass_word) {
		  LoginTest lg=new LoginTest();
		  driver=lg.login(web_driver, host_url, user_name, pass_word);
		  
		  MyYoya myyoya=new MyYoya(driver);
		  driver=myyoya.enterMyDesiger();
		  
		  this.cashier=new CashierRecord(driver);
		  this.cashier.enterGetCashier();
	  }

  @AfterTest
  public void afterTest() {
	  //driver.quit();
  }

  /**
   * 申请提现
   */
  @Test(alwaysRun=true)
  @Parameters({"avaiable_cash","cash_amount","name"
	  ,"account_type","incoming_account","login_pwd"})
  public void applyForMoney(String avaiable_cash,String cash_amount,String name
		  ,String account_type,String incoming_account,String login_pwd) {
	  
	  this.cashier.clickApplyBtn();
	  
	  Assert.assertEquals(this.cashier.getAvaibleCash(), avaiable_cash);
	  
	  //本次提现
	  if(this.cashier.getAvaibleCash().startsWith("0.0")){
		  this.cashier.clickCancelBtn();
	  }else{
		  this.cashier.inputCashAmount(cash_amount);
		//姓名
		  this.cashier.inputName(name);
		  
		  //选择账号类型
		  this.cashier.chooseAccountType(account_type);
		  
		  //支付账号
		  this.cashier.inputIncomingAccount(incoming_account);
		  
		  //登陆密码
		  this.cashier.inputLoginPwd(login_pwd);
		  
		  this.cashier.clickConfirmBtn();
	  }

	  Wait.waitMilliSeconds(10000);
  }
  
  
}
