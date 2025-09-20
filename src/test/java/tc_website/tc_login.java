//package tc_website;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.Assert;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//import tc_website.utils.TestDataManager;
//
//import java.util.Map;
//
//public class tc_login extends BaseTest {
//
//	private TestDataManager testDataManager;
//
//	public void login(String mail, String pw) {
//		// Find and fill name
//		WebElement inputEmail = driver.findElement(By.id(":r0:"));
//		inputEmail.sendKeys(mail);
//
//		// Find and fill email
//		WebElement inputPassword = driver.findElement(By.id(":r1:"));
//		inputPassword.sendKeys(pw);
//	}
//
//	public void btn_Login_click() throws InterruptedException {
//		WebElement btnLoginElement = driver
//				.findElement(By.cssSelector("button[type='submit'] span[class='text-inherit']"));
//		btnLoginElement.click();
//		Thread.sleep(2000);
//	}
//
//	@BeforeMethod
//	@Override
//	public void setUp() throws InterruptedException {
//		super.setUp();
//
//		// Initialize test data manager
//		testDataManager = TestDataManager.getInstance();
//
//		// Navigate to login page after base setup
//		WebElement btnHomeLogin = driver.findElement(By.xpath("//a[@href='/auth/login']"));
//		btnHomeLogin.click();
//		Thread.sleep(2000);
//	}
//
//	// Login successfully
//	@Test(priority = 0, enabled = true)
//	public void dn1_loginSuccessfully() throws InterruptedException {
//		// Get test data from CSV file
//		Map<String, String> testData = testDataManager.getLoginData("dn1_loginSuccessfully");
//
//		login(testData.get("Email"), testData.get("Password"));
//		Thread.sleep(2000);
//		btn_Login_click();
//		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Đăng nhập thành công')]")).isDisplayed());
//	}
//
//	// Login with wrong Email
//	@Test(priority = 1, enabled = true)
//	public void dn2_loginWithWrongEmail() throws InterruptedException {
//		Map<String, String> testData = testDataManager.getLoginData("dn2_loginWithWrongEmail");
//		login(testData.get("Email"), testData.get("Password"));
//		Thread.sleep(2000);
//		btn_Login_click();
//		Assert.assertTrue(driver
//				.findElement(By
//						.xpath("//div[contains(text(),'Tài khoản hoặc mật khẩu không chính xác. Vui lòng thử lại.')]"))
//				.isDisplayed());
//	}
//
//	// Login with wrong Password
//	@Test(priority = 2, enabled = true)
//	public void dn3_loginWithWrongPassword() throws InterruptedException {
//		Map<String, String> testData = testDataManager.getLoginData("dn3_loginWithWrongPassword");
//		login(testData.get("Email"), testData.get("Password"));
//		Thread.sleep(2000);
//		btn_Login_click();
//		Assert.assertTrue(driver
//				.findElement(By
//						.xpath("//div[contains(text(),'Tài khoản hoặc mật khẩu không chính xác. Vui lòng thử lại.')]"))
//				.isDisplayed());
//	}
//
//	// Login with Empty Email
//	@Test(priority = 3, enabled = true)
//	public void dn4_loginWithEmptyEmail() throws InterruptedException {
//		Map<String, String> testData = testDataManager.getLoginData("dn4_loginWithEmptyEmail");
//
//		WebElement inputPassword = driver.findElement(By.id(":r1:"));
//		inputPassword.sendKeys(testData.get("Password"));
//		Thread.sleep(1000);
//		btn_Login_click();
//		Assert.assertTrue(driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[2]/form[1]/div[1]/div[2]"))
//				.isDisplayed());
//	}
//
//	// Login with Empty Password
//	@Test(priority = 4, enabled = true)
//	public void dn5_loginWithEmptyPassword() throws InterruptedException {
//		Map<String, String> testData = testDataManager.getLoginData("dn5_loginWithEmptyPassword");
//
//		WebElement inputPassword = driver.findElement(By.id(":r0:"));
//		inputPassword.sendKeys(testData.get("Email"));
//		Thread.sleep(1000);
//		btn_Login_click();
//		Assert.assertTrue(driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[2]/form[1]/div[2]/div[2]"))
//				.isDisplayed());
//	}
//
//	// Login with Invalid Email
//	@Test(priority = 5, enabled = true)
//	public void dn6_loginWithInvalidEmail() throws InterruptedException {
//		Map<String, String> testData = testDataManager.getLoginData("dn6_loginWithInvalidEmail");
//		login(testData.get("Email"), testData.get("Password"));
//		Thread.sleep(1000);
//		btn_Login_click();
//		Assert.assertTrue(driver
//				.findElement(By
//						.xpath("//div[contains(text(),'Email không hợp lệ.')]"))
//				.isDisplayed());
//	}
//
//	// Login with Password Has Only Numbers
//	@Test(priority = 6, enabled = true)
//	public void dn7_loginWithPasswordHasOnlyNumbers() throws InterruptedException {
//		Map<String, String> testData = testDataManager.getLoginData("dn7_loginWithPasswordHasOnlyNumbers");
//		login(testData.get("Email"), testData.get("Password"));
//		Thread.sleep(1000);
//		btn_Login_click();
//		Assert.assertTrue(driver
//				.findElement(By
//						.xpath("//div[contains(text(),'Mật khẩu phải chứa 1 ký tự và 1 số')]"))
//				.isDisplayed());
//	}
//
//	// Login with Password Has Only Alphabet
//	@Test(priority = 7, enabled = true)
//	public void dn8_loginWithPasswordHasOnlyAlphabet() throws InterruptedException {
//		Map<String, String> testData = testDataManager.getLoginData("dn8_loginWithPasswordHasOnlyAlphabet");
//		login(testData.get("Email"), testData.get("Password"));
//		Thread.sleep(1000);
//		btn_Login_click();
//		Assert.assertTrue(driver
//				.findElement(By
//						.xpath("//div[contains(text(),'Mật khẩu phải chứa 1 ký tự và 1 số')]"))
//				.isDisplayed());
//	}
//
//}
