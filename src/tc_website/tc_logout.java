package tc_website;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tc_website.utils.TestDataManager;

import java.util.Map;

public class tc_logout extends BaseTest {

	private TestDataManager testDataManager;

	@BeforeMethod
	@Override
	public void setUp() throws InterruptedException {
		super.setUp();
		testDataManager = TestDataManager.getInstance();
		
		// Login với test data
		Map<String, String> loginData = testDataManager.getLogoutData("tk1_logoutSuccess");
		login(loginData.get("Username"), loginData.get("Password"));
		btn_Login_click();
		Thread.sleep(1000);
		
		WebElement btn_closeMessage = driver.findElement(By.xpath("//button[@aria-label='close']//*[name()='svg']"));
		btn_closeMessage.click();
		Thread.sleep(1000);
		
		driver.navigate().to("https://nguyetviet.io.vn/profile/me");
		
		Thread.sleep(1000);
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

	@Test(priority = 0, enabled = true)
	public void dx1_logoutSuccessfully() throws InterruptedException {
		
		WebElement btn_logout = driver.findElement(By.xpath("//span[contains(text(),'Đăng xuất')]"));
		btn_logout.click();
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Đăng xuất thành công')]")).isDisplayed());
		
	}

}
