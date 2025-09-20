package tc_website;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class tc_viewProfile {
	
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

	WebDriver driver = null;

	@BeforeMethod
	public void beforeMethod() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "D:\\Chromedriver\\chromedriver.exe");

		driver = new ChromeDriver();

		// 1 - Maximize browser
		driver.manage().window().maximize();

		// 2 - Navigate to url
		driver.navigate().to("https://nguyetviet.io.vn/auth/login");

		// Wait web load
		Thread.sleep(2000);

		login("nguyenyen2003+7@gmail.com", "Yen12345");
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
	public void cn1_viewProfileDetail() throws InterruptedException {
		
		WebElement nameElement = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/section[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/input[1]"));
		String actualName  = nameElement.getAttribute("value");
		
		WebElement mailElement = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/section[1]/div[1]/form[1]/div[1]/div[2]/div[1]/div[1]/input[1]"));
		String actualMail = mailElement.getAttribute("value");
		
		String expectedNameString = "Nguyen Thi Hai Yen";
		Assert.assertEquals(actualName,expectedNameString,"Giá trị của thuộc tính 'value' không khớp!");
		
		String expectedMail = "nguyenyen2003+5@gmail.com";
		Assert.assertEquals(actualMail, expectedMail, "Giá trị của thuộc tính 'value' không khớp!");
		
	}

	@AfterMethod
	public void afterMethod() throws InterruptedException {

		Thread.sleep(2000);
		driver.quit();

	}

}
