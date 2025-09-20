package tc_website;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tc_website.utils.TestDataManager;


public class tc_updateProfile extends BaseTest {

	private TestDataManager testDataManager;
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

	@Override
	@BeforeMethod
	public void setUp() throws InterruptedException {
		super.setUp();
		testDataManager = TestDataManager.getInstance();
		// 2 - Navigate to url
		driver.navigate().to("https://nguyetviet.io.vn/auth/login");

		// Wait web load
		Thread.sleep(2000);

		Map<String, String> accountData = testDataManager.getAccountData("acc3_profile");
		login(accountData.get("Email"), accountData.get("Password"));
		btn_Login_click();
		Thread.sleep(1000);
		WebElement btn_closeMessage = driver.findElement(By.xpath("//button[@aria-label='close']//*[name()='svg']"));
		btn_closeMessage.click();
		Thread.sleep(1000);

		// Find elements
		WebElement btn_home_profile = driver.findElement(By.xpath("//a[@href='/profile/me']"));

		btn_home_profile.click();
		
		Thread.sleep(1000);

	}

	@Test(priority = 0, enabled = true)
	public void pf1_updateProfile() throws InterruptedException {
		//find and fill Phone number
		WebElement txtSDT = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/section[1]/div[1]/form[1]/div[1]/div[3]/div[1]/div[1]/input[1]"));
		txtSDT.sendKeys("0322488825");
		
		//find and fill Province
		WebElement txtTinh = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/section[1]/div[1]/form[1]/div[1]/div[4]/div[1]/div[1]/div[1]/input[1]"));
		txtTinh.click();
		Thread.sleep(1000);
		
		WebElement listTinh = driver.findElement(By.xpath("//span[contains(text(),'Hải Dương')]"));
		listTinh.click();
		Thread.sleep(1000);

		//find and fill District
		WebElement txtHuyen = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/section[1]/div[1]/form[1]/div[1]/div[5]/div[1]/div[1]/div[1]/input[1]"));
		txtHuyen.click();
		Thread.sleep(1000);
		
		WebElement listHuyen = driver.findElement(By.xpath("//span[contains(text(),'Huyện Ninh Giang')]"));
		listHuyen.click();
		Thread.sleep(1000);
		
		//find and fill Ward
		WebElement txtXa = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/section[1]/div[1]/form[1]/div[1]/div[6]/div[1]/div[1]/div[1]/input[1]"));
		txtXa.click();
		Thread.sleep(1000);
		
		WebElement listXa = driver.findElement(By.xpath("//span[contains(text(),'Xã Kiến Phúc')]"));
		listXa.click();
		Thread.sleep(1000);
		
		//find and fill Street
		WebElement txtDuong = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/section[1]/div[1]/form[1]/div[1]/div[7]/div[1]/div[1]/input[1]"));
		txtDuong.sendKeys("Đường 5");
		Thread.sleep(1000);
		
		//click Save
		WebElement btnSave = driver.findElement(By.xpath("(//span[@class='text-inherit'][contains(text(),'Lưu thay đổi')])[2]"));
		btnSave.click();
		Thread.sleep(1000);
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Cập nhật thông tin cá nhân thành công.')]")).isDisplayed());
		
	}

	@AfterMethod
	public void afterMethod() throws InterruptedException {

		Thread.sleep(2000);
		driver.quit();

	}

}
