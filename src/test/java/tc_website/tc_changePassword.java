package tc_website;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class tc_changePassword extends BaseTest {
	

	public void login(String mail, String pw) {
		// Find and fill name
		WebElement inputEmail = driver.findElement(By.id(":r0:"));
		inputEmail.sendKeys(mail);

		// Find and fill email
		WebElement inputPassword = driver.findElement(By.id(":r1:"));
		inputPassword.sendKeys(pw);
	}

	public void btn_Login_click() throws InterruptedException {
		WebElement btnLoginElement = driver
				.findElement(By.cssSelector("button[type='submit'] span[class='text-inherit']"));
		btnLoginElement.click();
		Thread.sleep(2000);
	}

	public void changePassword(String currentPw, String newPw, String cfNewPw) {
		// Find and fill current Password
		WebElement inputCurrentPw = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/section[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/input[1]"));
		inputCurrentPw.sendKeys(currentPw);

		// Find and fill new Password
		WebElement inputNewPassword = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/section[1]/div[1]/form[1]/div[1]/div[2]/div[1]/div[1]/input[1]"));
		inputNewPassword.sendKeys(newPw);
		
		// Find and fill confirm new Password
		WebElement inputCfNewPassword = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/section[1]/div[1]/form[1]/div[1]/div[3]/div[1]/div[1]/input[1]"));
		inputCfNewPassword.sendKeys(cfNewPw);
	}

	@Override
	@BeforeMethod
	public void setUp() throws InterruptedException {
		super.setUp();
		
		// Navigate to url
		driver.navigate().to("https://nguyetviet.io.vn/auth/login");

		// Wait web load
		Thread.sleep(2000);

		login("nguyenyen2003+15@gmail.com", "Yen12345");
		btn_Login_click();
		Thread.sleep(1000);
		WebElement btn_closeMessage = driver.findElement(By.xpath("//button[@aria-label='close']//*[name()='svg']"));
		btn_closeMessage.click();
		Thread.sleep(1000);

		// Find elements
		WebElement btn_home_profile = driver.findElement(By.xpath("//a[@href='/profile/me']"));

		btn_home_profile.click();

		Thread.sleep(1000);

		WebElement btn_navigatePageChangePasswordElement = driver
				.findElement(By.xpath("//span[contains(text(),'Đổi mật khẩu')]"));
		btn_navigatePageChangePasswordElement.click();
		Thread.sleep(2000);

	}

	@Test(priority = 1, enabled = true)
	public void mk2_changePasswordSuccessfull() throws InterruptedException {
		
		changePassword("Yen12345", "Yen54321", "Yen54321");
		WebElement btn_submitChangePw = driver.findElement(By.xpath("(//span[@class='text-inherit'][contains(text(),'Lưu thay đổi')])[2]"));
		btn_submitChangePw.click();
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Mật khẩu đã được thay đổi thành công.')]")).isDisplayed());
	}
	
	@Test(priority = 0, enabled = true)
	public void mk1_wrongCurrentPassword() throws InterruptedException {
		
		changePassword("Yen54321", "Yen12345", "Yen12345");
		WebElement btn_submitChangePw = driver.findElement(By.xpath("(//span[@class='text-inherit'][contains(text(),'Lưu thay đổi')])[2]"));
		btn_submitChangePw.click();
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.cssSelector("div[role='alert'] div:nth-child(1)")).isDisplayed());
		WebElement btnClosElement = driver.findElement(By.xpath("//button[@aria-label='close']"));
		btnClosElement.click();
		Thread.sleep(1000);
	}

	@AfterMethod
	public void afterTest() throws InterruptedException {

		Thread.sleep(2000);
		driver.quit();

	}

}
