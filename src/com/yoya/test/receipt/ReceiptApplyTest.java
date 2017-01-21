package com.yoya.test.receipt;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yoya.page.Dialog;
import com.yoya.page.MyOrder;
import com.yoya.page.MyYoya;
import com.yoya.page.receipt.EmailAddress;
import com.yoya.page.receipt.ReceiptApply;
import com.yoya.test.login.LoginTest;
import com.yoya.util.Wait;

public class ReceiptApplyTest {
  
	public WebDriver driver;
	private ReceiptApply receiptapply;
	  @BeforeTest
	  @Parameters({"web_driver","host_url","user_name","pass_word"})
	  public void beforeTest(String web_driver,String host_url,String user_name,String pass_word) {
		  LoginTest lg=new LoginTest();
		  driver=lg.login(web_driver, host_url, user_name, pass_word);
		  MyYoya yoya=new MyYoya(driver);
		  driver=yoya.enterMyOrder();
		  MyOrder myorder=new MyOrder(driver);
		  driver=myorder.enterWantReceipt();
		  
		  this.receiptapply=new ReceiptApply(driver);
		  this.receiptapply.chooseTimeType();
		  
	  }

  @AfterTest
  public void afterTest() {
	  //driver.quit();
  }	
	
	
  @Test(alwaysRun=true)
  @Parameters({"order_id"})
  public void applyReceipt(String order_id) {
		
		WebElement order=this.receiptapply.findReceipt(order_id);
		
		this.receiptapply.clickApplyBtn(order);
		
		//默认采用普通发票，发票信息采用默认
		
		//新建地址
		EmailAddress address=new EmailAddress("abcd","18255555555","福建省","厦门市","123456789","360001","0000");
		
		this.receiptapply.newAddress(address);
		
		//选择新增的地址
		WebElement add=this.receiptapply.findAddress(address.getProvince(), address.getCity(), address.getAddress());
		this.receiptapply.clickchooseAddressBtn(add);
		
		//提交申请
		this.receiptapply.clickSubmitBtn();
		
		Dialog d=new Dialog(driver);
		Wait.implicitlyWait(driver, 10);
		Assert.assertEquals(d.getContent(), "成功提交开票申请!");
				
		//发票申请记录中查找是否存在
		Assert.assertEquals(this.receiptapply.findApplyRecordStatus(), "审核中");	
  }

  
  @Test(alwaysRun=true)
  @Parameters({"order_id1","order_id2"})
	public void applyMultiReceipts(String order_id1, String order_id2) {
		WebElement order1 = this.receiptapply.findReceipt(order_id1);
		WebElement order2 = this.receiptapply.findReceipt(order_id2);

		// 勾选要合并开票的订单
		this.receiptapply.clickOrderCheck(order1);
		this.receiptapply.clickOrderCheck(order2);
		// 合并开票
		this.receiptapply.clickMultiApplyBtn();

		// 默认发票方式

		// 新建地址
		EmailAddress address = new EmailAddress("abcd", "18255555555", "福建省", "厦门市", "123456789", "360001", "0000");

		this.receiptapply.newAddress(address);

		// 选择新增的地址
		WebElement add=this.receiptapply.findAddress(address.getProvince(), address.getCity(), address.getAddress());
		this.receiptapply.clickchooseAddressBtn(add);

		// 提交申请
		this.receiptapply.clickSubmitBtn();

		Dialog d = new Dialog(driver);
		Wait.implicitlyWait(driver, 10);
		Assert.assertEquals(d.getContent(), "成功提交开票申请!");

		// 发票申请记录中查找是否存在
		Assert.assertEquals(this.receiptapply.findApplyRecordStatus(), "审核中");
	}
  
  /**
   * 发票申请记录-查看
   * @param apply_id
   */
  @Test(alwaysRun=true)
  @Parameters({"apply_id"})
  public void viewApply(String apply_id){
	  
	  this.receiptapply.clickApplyRecordTab();
	  
	  WebElement apply=this.receiptapply.findApplyRecord(apply_id);
	  
	  this.receiptapply.clickViewBtn(apply);
	  
	  Assert.assertEquals(this.receiptapply.getInvoiceStatus(), "待开票");
  }
  
  /**
   * 发票申请记录-取消申请
   * @param apply_id
   */
  @Test(alwaysRun=true)
  @Parameters({"apply_id"})
  public void cancelApply(String apply_id){
	  
	  this.receiptapply.clickApplyRecordTab();
	  
	  WebElement apply=this.receiptapply.findApplyRecord(apply_id);
	  
	  this.receiptapply.clickCancelApplyBtn(apply);
	  
	  Dialog d=new Dialog(driver);
	  Assert.assertEquals(d.getContent(), "确定要取消该发票申请吗？");
	  d.clickconfirmBtn();
	  
	  
	  WebElement canelapply=this.receiptapply.findApplyRecord(apply_id);
	  Assert.assertEquals(this.receiptapply.findApplyRecordStatus(canelapply), "取消申请");
	  
  }
  
  

}
