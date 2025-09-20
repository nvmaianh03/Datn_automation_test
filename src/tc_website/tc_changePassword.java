package tc_website;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tc_website.utils.TestDataManager;

import java.util.Map;

public class tc_changePassword extends BaseTest {
	
	private TestDataManager testDataManager;

	@BeforeMethod
	@Override
	public void setUp() throws InterruptedException {
		super.setUp();
		testDataManager = TestDataManager.getInstance();
		
		// Get default login credentials for accessing change password page
		Map<String, String> loginData = testDataManager.getChangePasswordData("tk1_changePasswordSuccess");
		
		// Navigate to login page and login
		driver.navigate().to("https://nguyetviet.io.vn/auth/login");
		Thread.sleep(2000);
		
		login(loginData.get("LoginEmail"), loginData.get("LoginPassword"));
		btn_Login_click();
		Thread.sleep(1000);
		WebElement btn_closeMessage = driver.findElement(By.xpath("//button[@aria-label='close']//*[name()='svg']"));
		btn_closeMessage.click();
		Thread.sleep(1000);
		
		WebElement btn_home_profile = driver.findElement(By.xpath("//a[@href='/profile/me']"));
		btn_home_profile.click();
		Thread.sleep(2000);

		WebElement btn_navigatePageChangePasswordElement = driver
				.findElement(By.xpath("//span[contains(text(),'Đổi mật khẩu')]"));
		btn_navigatePageChangePasswordElement.click();
		Thread.sleep(2000);
	}
	

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

	@Test(priority = 1, enabled = true)
	public void mk2_changePasswordSuccessfull() throws InterruptedException {
		Map<String, String> testData = testDataManager.getChangePasswordData("tk1_changePasswordSuccess");
		
		changePassword(testData.get("OldPassword"), testData.get("NewPassword"), testData.get("ConfirmPassword"));
		WebElement btn_submitChangePw = driver.findElement(By.xpath("(//span[@class='text-inherit'][contains(text(),'Lưu thay đổi')])[2]"));
		btn_submitChangePw.click();
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Mật khẩu đã được thay đổi thành công.')]")).isDisplayed());
	}
	
	@Test(priority = 0, enabled = true)
	public void mk1_wrongCurrentPassword() throws InterruptedException {
		Map<String, String> testData = testDataManager.getChangePasswordData("tk2_changePasswordWrongCurrent");
		
		changePassword(testData.get("OldPassword"), testData.get("NewPassword"), testData.get("ConfirmPassword"));
		WebElement btn_submitChangePw = driver.findElement(By.xpath("(//span[@class='text-inherit'][contains(text(),'Lưu thay đổi')])[2]"));
		btn_submitChangePw.click();
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.cssSelector("div[role='alert'] div:nth-child(1)")).isDisplayed());
		WebElement btnClosElement = driver.findElement(By.xpath("//button[@aria-label='close']"));
		btnClosElement.click();
		Thread.sleep(1000);
	}

}
