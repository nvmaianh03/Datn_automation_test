package tc_website;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import tc_website.utils.TestDataManager;

import java.util.Map;

public class tc_payment extends BaseTest {
	private TestDataManager testDataManager;
	public void login(String mail, String pw) {
		// Find and fill name
		WebElement inputEmail = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[2]/form[1]/div[1]/div[1]/div[1]/input[1]"));
		inputEmail.sendKeys(mail);

		// Find and fill email
		WebElement inputPassword = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[2]/form[1]/div[2]/div[1]/div[1]/input[1]"));
		inputPassword.sendKeys(pw);
	}

	public void btn_Login_click() throws InterruptedException {
		WebElement btnLoginElement = driver
				.findElement(By.cssSelector("button[type='submit'] span[class='text-inherit']"));
		btnLoginElement.click();
		Thread.sleep(2000);
	}
	
	//Fill information to pay
	public void thongTinThanhToan(String sdt1, String name, String sdt2, int a) throws InterruptedException {
		
		WebElement sdt1Element = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/main[1]/main[1]/form[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/input[1]"));
		sdt1Element.sendKeys(sdt1);
		
		WebElement nameElement = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/main[1]/main[1]/form[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/input[1]"));
		nameElement.sendKeys(name);
		
		WebElement sdt2Element = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/main[1]/main[1]/form[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/input[1]"));
		sdt2Element.sendKeys(sdt2);
		
		
		// find and fill Province
		WebElement txtTinh = driver.findElement(By.xpath(
				"/html[1]/body[1]/div[1]/div[2]/main[1]/main[1]/form[1]/div[1]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/input[1]"));
		txtTinh.click();
		Thread.sleep(1000);

		WebElement listTinh = driver.findElement(By.xpath("//span[contains(text(),'Hải Dương')]"));
		listTinh.click();
		Thread.sleep(1000);

		// find and fill District
		WebElement txtHuyen = driver.findElement(By.xpath(
				"/html[1]/body[1]/div[1]/div[2]/main[1]/main[1]/form[1]/div[1]/div[2]/div[1]/div[1]/div[3]/div[1]/div[2]/div[1]/div[1]/input[1]"));
		txtHuyen.click();
		Thread.sleep(1000);

		WebElement listHuyen = driver.findElement(By.xpath("//span[contains(text(),'Huyện Ninh Giang')]"));
		listHuyen.click();
		Thread.sleep(1000);

		// find and fill Ward
		WebElement txtXa = driver.findElement(By.xpath(
				"/html[1]/body[1]/div[1]/div[2]/main[1]/main[1]/form[1]/div[1]/div[2]/div[1]/div[1]/div[3]/div[1]/div[3]/div[1]/div[1]/input[1]"));
		txtXa.click();
		Thread.sleep(1000);

		WebElement listXa = driver.findElement(By.xpath("//span[contains(text(),'Xã Kiến Phúc')]"));
		listXa.click();
		Thread.sleep(1000);

		// find and fill Street
		WebElement txtDuong = driver.findElement(By.xpath(
				"/html[1]/body[1]/div[1]/div[2]/main[1]/main[1]/form[1]/div[1]/div[2]/div[1]/div[1]/div[3]/div[3]/div[1]/div[1]/textarea[1]"));
		txtDuong.sendKeys("Đường 5");
		Thread.sleep(1000);
		
		//choose payment method 1:payInCash, 2:payWithMomo, 3:payWithZalopay
		if(a == 1) {
			WebElement btnPayCashElement = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/main[1]/main[1]/form[1]/div[1]/div[2]/div[1]/div[2]/div[1]/input[1]"));
			btnPayCashElement.click();
			Thread.sleep(1000);
		}else if(a==2) {
			WebElement btnMomo = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/main[1]/main[1]/form[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/input[1]"));
			btnMomo.click();
			Thread.sleep(1000);
		}else if(a==3) {
			WebElement btnZalopay = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/main[1]/main[1]/form[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[2]/div[2]/input[1]"));
			btnZalopay.click();
			Thread.sleep(1000);
		}
	}
	
	//click pay
	public void btnThanhToan() throws InterruptedException {
		WebElement btn_thanhToanElement = driver.findElement(By.xpath("//button[contains(@type,'submit')]"));
		btn_thanhToanElement.click();
		Thread.sleep(2000);
	}
	
	@Override
	@BeforeMethod
	public void setUp() throws InterruptedException {
		super.setUp();
		testDataManager = TestDataManager.getInstance();
		// Navigate to URL
		driver.navigate().to("https://nguyetviet.io.vn");

		// Wait web load
		Thread.sleep(2000);
	}
	
	
	
	//Pay in cash
	@Test(priority = 0, enabled = true)
	public void tt1_payInCash() throws InterruptedException {
		Map<String, String> testData = testDataManager.getPaymentData("tk3_paymentCOD");
		Map<String, String> accountData = testDataManager.getAccountData("acc1_primary");
		
		WebElement btn_home_login = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/header[1]/div[1]/div[3]/a[2]/span[1]/*[name()='svg'][1]"));
		btn_home_login.click();
		Thread.sleep(2000);
		
		login(accountData.get("Email"), accountData.get("Password"));
		btn_Login_click();
		Thread.sleep(1000);
		WebElement btn_closeMessage = driver.findElement(By.xpath("//button[@aria-label='close']//*[name()='svg']"));
		btn_closeMessage.click();
		Thread.sleep(1000);

		// Find elements
		WebElement btn_home_cart = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/header[1]/div[1]/div[3]/div[1]/button[1]/span[1]"));
		btn_home_cart.click();
		Thread.sleep(2000);
		
		WebElement btn_pay = driver.findElement(By.xpath("//span[normalize-space()='Thanh toán']"));
		btn_pay.click();
		Thread.sleep(2000);
		
		thongTinThanhToan(testData.get("Phone"), testData.get("FullName"), testData.get("Phone"), 1);
		Thread.sleep(2000);
		
		btnThanhToan();
		
		WebElement messagElement = driver.findElement(By.xpath("//div[contains(text(),'Tạo đơn hàng mới thành công')]"));
		Assert.assertEquals(messagElement.getText(), "Tạo đơn hàng mới thành công");
		Thread.sleep(1000);
		
		WebElement btnClosElement = driver.findElement(By.xpath("//button[@aria-label='close']"));
		btnClosElement.click();
		Thread.sleep(500);
		
		btn_home_cart.click();
		Thread.sleep(2000); 
		
		WebElement checkEmptyCart = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/header[1]/div[3]/div[2]/div[1]/div[1]/p[1]"));
		Assert.assertEquals(checkEmptyCart.getText(), "Giỏ hàng của bạn đang trống");
		Thread.sleep(1000);
		
		WebElement btnCloseCartElement = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/header[1]/div[3]/div[1]/div[1]/button[1]"));
		btnCloseCartElement.click();
		Thread.sleep(3000);
		
	}
	
	// Pay with Momo
	@Test(priority = 1, enabled = true)
	public void tt2_payWithMomo() throws InterruptedException {
		//đăng nhập
		WebElement btn_home_login = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/header[1]/div[1]/div[3]/a[2]/span[1]/*[name()='svg'][1]"));
		btn_home_login.click();
		Thread.sleep(2000);

		Map<String, String> accountData = testDataManager.getAccountData("acc1_primary");
		login(accountData.get("Email"), accountData.get("Password"));
		btn_Login_click();
		Thread.sleep(1000);
		WebElement btn_closeMessageC = driver.findElement(By.xpath("//button[@aria-label='close']//*[name()='svg']"));
		btn_closeMessageC.click();
		Thread.sleep(1000);

		Map<String, String> testData = testDataManager.getPaymentData("tk2_paymentInvalidCard");
		
		WebElement btn_home_product = driver.findElement(By.xpath("//a[@href='/products']"));
		btn_home_product.click();
		Thread.sleep(2000);
		
		// Hover (move to element)
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement product = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/main[1]/div[1]/form[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/img[1]"));
		js.executeScript("arguments[0].scrollIntoView(true);", product);
		Thread.sleep(1000);

		Actions actions = new Actions(driver);
		actions.moveToElement(product).perform();
		Thread.sleep(1000);

		// click button add Cart
		WebElement btnViewDetailProduct = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/main[1]/div[1]/form[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/button[1]"));
		btnViewDetailProduct.click();
		Thread.sleep(2000);

		WebElement btn_closeMessage = driver.findElement(By.xpath("//button[@aria-label='close']//*[name()='svg']"));
		btn_closeMessage.click();
		Thread.sleep(1000);

		// Find elements
		WebElement btn_home_cart = driver.findElement(
				By.xpath("/html[1]/body[1]/div[1]/div[2]/header[1]/div[1]/div[3]/div[1]/button[1]/span[1]"));
		btn_home_cart.click();
		Thread.sleep(2000);

		WebElement btn_pay = driver.findElement(By.xpath("//span[normalize-space()='Thanh toán']"));
		btn_pay.click();
		Thread.sleep(2000);

		thongTinThanhToan(testData.get("Phone"), testData.get("FullName"), testData.get("Phone"), 2);
		Thread.sleep(2000);

		String firstTab = driver.getWindowHandle();
		
		btnThanhToan();
		Thread.sleep(3000);
		
		driver.switchTo().window(firstTab);
		Thread.sleep(1000);
		
		WebElement messagElement = driver.findElement(By.xpath("//div[contains(text(),'Tạo đơn hàng mới thành công')]"));
		Assert.assertEquals(messagElement.getText(), "Tạo đơn hàng mới thành công");
		Thread.sleep(1000);
		
		WebElement btnClosElement = driver.findElement(By.xpath("//button[@aria-label='close']"));
		btnClosElement.click();
		Thread.sleep(500);
		
		btn_home_cart.click();
		Thread.sleep(2000); 
		
		WebElement checkEmptyCart = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/header[1]/div[3]/div[2]/div[1]/div[1]/p[1]"));
		Assert.assertEquals(checkEmptyCart.getText(), "Giỏ hàng của bạn đang trống");
		Thread.sleep(1000);
		
		WebElement btnCloseCartElement = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/header[1]/div[3]/div[1]/div[1]/button[1]"));
		btnCloseCartElement.click();
		Thread.sleep(3000);
	}
	
	// Pay with Zalopay
	@Test(priority = 2, enabled = true)
	public void tt3_payWithZalopay() throws InterruptedException {
		WebElement btn_home_login = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/header[1]/div[1]/div[3]/a[2]/span[1]/*[name()='svg'][1]"));
		btn_home_login.click();
		Thread.sleep(2000);

		Map<String, String> accountData = testDataManager.getAccountData("acc1_primary");
		login(accountData.get("Email"), accountData.get("Password"));
		btn_Login_click();
		Thread.sleep(1000);
		WebElement btn_closeMessageC = driver.findElement(By.xpath("//button[@aria-label='close']//*[name()='svg']"));
		btn_closeMessageC.click();
		Thread.sleep(1000);

		Map<String, String> testData = testDataManager.getPaymentData("tk1_paymentSuccess");

		WebElement btn_home_product = driver.findElement(By.xpath("//a[@href='/products']"));
		btn_home_product.click();
		Thread.sleep(2000);

		// Hover (move to element)
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement product = driver.findElement(By.xpath(
				"/html[1]/body[1]/div[1]/div[2]/main[1]/div[1]/form[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/img[1]"));
		js.executeScript("arguments[0].scrollIntoView(true);", product);
		Thread.sleep(1000);

		Actions actions = new Actions(driver);
		actions.moveToElement(product).perform();
		Thread.sleep(1000);

		// click button add Cart
		WebElement btnViewDetailProduct = driver.findElement(By.xpath(
				"/html[1]/body[1]/div[1]/div[2]/main[1]/div[1]/form[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/button[1]"));
		btnViewDetailProduct.click();
		Thread.sleep(2000);

		WebElement btn_closeMessage = driver.findElement(By.xpath("//button[@aria-label='close']//*[name()='svg']"));
		btn_closeMessage.click();
		Thread.sleep(1000);

		// Find elements
		WebElement btn_home_cart = driver.findElement(
				By.xpath("/html[1]/body[1]/div[1]/div[2]/header[1]/div[1]/div[3]/div[1]/button[1]/span[1]"));
		btn_home_cart.click();
		Thread.sleep(2000);

		WebElement btn_pay = driver.findElement(By.xpath("//span[normalize-space()='Thanh toán']"));
		btn_pay.click();
		Thread.sleep(2000);

		thongTinThanhToan(testData.get("Phone"), testData.get("FullName"), testData.get("Phone"), 3);
		Thread.sleep(2000);

		String firstTab = driver.getWindowHandle();

		btnThanhToan();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(driver -> ((JavascriptExecutor) driver)
			    .executeScript("return document.readyState").equals("complete"));
		Thread.sleep(2000);

		driver.switchTo().window(firstTab);
		Thread.sleep(1000);

		WebElement messagElement = driver
				.findElement(By.xpath("//div[contains(text(),'Tạo đơn hàng mới thành công')]"));
		Assert.assertEquals(messagElement.getText(), "Tạo đơn hàng mới thành công");
		Thread.sleep(1000);

		WebElement btnClosElement = driver.findElement(By.xpath("//button[@aria-label='close']"));
		btnClosElement.click();
		Thread.sleep(500);

		btn_home_cart.click();
		Thread.sleep(2000);

		WebElement checkEmptyCart = driver
				.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/header[1]/div[3]/div[2]/div[1]/div[1]/p[1]"));
		Assert.assertEquals(checkEmptyCart.getText(), "Giỏ hàng của bạn đang trống");
		Thread.sleep(1000);

		WebElement btnCloseCartElement = driver
				.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/header[1]/div[3]/div[1]/div[1]/button[1]"));
		btnCloseCartElement.click();
		Thread.sleep(3000);
	}
	
//	@AfterMethod
//	public void afterTest() throws InterruptedException {
//
//		// View your orders
//		WebElement btn_home_profile = driver.findElement(By.xpath("//a[@href='/profile/me']"));
//		btn_home_profile.click();
//		Thread.sleep(1000);
//
//		WebElement viewOrders = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/aside[1]/nav[1]/div[2]/ul[1]/li[1]/a[1]/span[2]"));
//		viewOrders.click();
//		Thread.sleep(6000);
//		driver.quit();
//	}

}
