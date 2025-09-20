package tc_website;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tc_website.base.BaseTest;
import tc_website.utils.TestDataManager;

import java.util.Map;

public class tc_dky extends BaseTest {
	
	private TestDataManager testDataManager = TestDataManager.getInstance();

	public void registerAccount(String name, String email, String pw, String cfPw) {
		
		//Find and fill name
		WebElement inputName = driver.findElement(By.id(":r0:"));
		inputName.sendKeys(name);

		// Find and fill email
		WebElement inputMail = driver.findElement(By.id(":r1:"));
		inputMail.sendKeys(email);

		// Find and fill password
		WebElement inputPassword = driver.findElement(By.id(":r2:"));
		inputPassword.sendKeys(pw);

		// Find and fill confirm password
		WebElement inputConfirmPassword = driver.findElement(By.id(":r3:"));
		inputConfirmPassword.sendKeys(cfPw);
	}

	public void btnSubmitRegister() throws InterruptedException {

		// Click submit
		WebElement btn_submit_dky = driver.findElement(By.cssSelector("button[type='submit']"));
		btn_submit_dky.click();
		Thread.sleep(2000);
	}
	
	@BeforeMethod
	public void setupRegisterTest() throws InterruptedException {
		// 1 - Navigate to homepage
		driver.navigate().to("https://nguyetviet.io.vn");
		Thread.sleep(2000);

		// 2 - Navigate to register page
		WebElement btn_dky1 = driver.findElement(By.xpath("//a[@href='/auth/sign-up']"));
		btn_dky1.click();
		Thread.sleep(2000);
	}

	// Register successfully
	@Test(priority = 0, enabled = true)
	public void dk1_registerSuccessfully() throws InterruptedException {
		Map<String, String> testData = testDataManager.getRegisterData("tk1_registerSuccess");

		// Fill all input
		registerAccount(testData.get("Name"), testData.get("Email"), testData.get("Password"), testData.get("ConfirmPassword"));

		Thread.sleep(2000);

		btnSubmitRegister();

		// Check message register successfully
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[contains(text(),'Đăng ký tài khoản thành công!')]")).isDisplayed());
		

		Thread.sleep(4000);

		// Check navigate to page login
		String expectedUrl = "https://nguyetviet.io.vn/auth/login";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);

	}

	// Register fail when submit email already exist
	@Test(priority = 1, enabled = true)
	public void dky2_registerByExistedMail() throws InterruptedException {

		// Fill all input
		registerAccount("Nguyen Thi Hai Yen", "nguyenyen2003+11@gmail.com", "Yen12345", "Yen12345");

		Thread.sleep(2000);

		// Click submit
		btnSubmitRegister();

		// Check message register successfully
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Email đã tồn tại')]")).isDisplayed());

		System.out.println("Hiển thị message Email đã tồn tại");

	}

	// Register with empty name
	@Test(priority = 2, enabled = true)
	public void dk3_emptyName() throws InterruptedException {

		// Find input mail
		WebElement inputMail = driver.findElement(By.id(":r1:"));
		inputMail.sendKeys("nguyenyen2003+11@gmail.com");

		// Find input password
		WebElement inputPassword = driver.findElement(By.id(":r2:"));
		inputPassword.sendKeys("Yen12345");

		// Find input confirm password
		WebElement inputConfirmPassword = driver.findElement(By.id(":r3:"));
		inputConfirmPassword.sendKeys("Yen12345");

		// Click submit
		btnSubmitRegister();

		Assert.assertTrue(
				driver.findElement(By.xpath("//div[contains(text(),'Tên đăng nhập là bắt buộc')]")).isDisplayed());

	}

	// Register with empty Email
	@Test(priority = 3, enabled = true)
	public void dk4_emptyEmail() throws InterruptedException {

		// Find input name
		WebElement inputName = driver.findElement(By.id(":r0:"));
		inputName.sendKeys("Nguyen Thi Hai Yen");

		// Find input password
		WebElement inputPassword = driver.findElement(By.id(":r2:"));
		inputPassword.sendKeys("Yen12345");

		// Find input confirm password
		WebElement inputConfirmPassword = driver.findElement(By.id(":r3:"));
		inputConfirmPassword.sendKeys("Yen12345");

		// Click submit
		btnSubmitRegister();

		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Email là bắt buộc')]")).isDisplayed());

	}

	// Register with empty Password
	@Test(priority = 4, enabled = true)
	public void dk5_emptyPassword() throws InterruptedException {

		// Find input name
		WebElement inputName = driver.findElement(By.id(":r0:"));
		inputName.sendKeys("Nguyen Thi Hai Yen");

		// Find input mail
		WebElement inputMail = driver.findElement(By.id(":r1:"));
		inputMail.sendKeys("nguyenyen2003+11@gmail.com");

		// Find input confirm password
		WebElement inputConfirmPassword = driver.findElement(By.id(":r3:"));
		inputConfirmPassword.sendKeys("Yen12345");

		// Click submit
		btnSubmitRegister();

		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Mật khẩu là bắt buộc')]")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[contains(text(),'Mật khẩu xác nhận không khớp')]")).isDisplayed());

	}

	// Register with empty Confirm Password
	@Test(priority = 5, enabled = true)
	public void dk6_emptyPassword() throws InterruptedException {

		// Find input name
		WebElement inputName = driver.findElement(By.id(":r0:"));
		inputName.sendKeys("Nguyen Thi Hai Yen");

		// Find input mail
		WebElement inputMail = driver.findElement(By.id(":r1:"));
		inputMail.sendKeys("nguyenyen2003+11@gmail.com");

		// Find input password
		WebElement inputPassword = driver.findElement(By.id(":r2:"));
		inputPassword.sendKeys("Yen12345");

		// Click submit
		btnSubmitRegister();

		Assert.assertTrue(
				driver.findElement(By.xpath("//div[contains(text(),'Xác nhận mật khẩu là bắt buộc')]")).isDisplayed());

	}

	// Register with invalid mail
	@Test(priority = 6, enabled = true)
	public void dky7_InvalidMail() throws InterruptedException {

		// Fill all input
		registerAccount("Nguyen Thi Hai Yen", ".nguyenyen2003gmail.com", "Yen12345", "Yen12345");

		Thread.sleep(2000);
		btnSubmitRegister();
		Thread.sleep(1000);
		// Click submit
		// btnSubmitRegister();

		// Check message register successfully
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Email không hợp lệ.')]")).isDisplayed());

	}

	// Register with password has only numbers
	@Test(priority = 7, enabled = true)
	public void dky8_passwordHasOnlyNumbers() throws InterruptedException {
		registerAccount("Yen Nguyen", "nguyenyen2003+12@gmail.com", "12345678", "12345678");
		Thread.sleep(1000);
		btnSubmitRegister();
		Thread.sleep(1000);
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Mật khẩu phải chứa 1 ký tự và 1 số')]"))
				.isDisplayed());
	}

	// Register with password has only numbers
	@Test(priority = 8, enabled = true)
	public void dky9_passwordHasOnlyAlphabet() throws InterruptedException {
		registerAccount("Yen Nguyen", "nguyenyen2003+12@gmail.com", "nguyenyen", "nguyenyen");
		Thread.sleep(1000);
		btnSubmitRegister();
		Thread.sleep(1000);
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Mật khẩu phải chứa 1 ký tự và 1 số')]"))
				.isDisplayed());
	}
}
