package tc_website;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;

public class tc_search extends BaseTest {

	@Override
	@BeforeMethod
	public void setUp() throws InterruptedException {
		super.setUp();
		
		// Navigate to URL and wait for load
		driver.navigate().to("https://nguyetviet.io.vn");
		Thread.sleep(2000);
		
		// Navigate to products page for all tests
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement btn_home_product = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/products']")));
		btn_home_product.click();
		Thread.sleep(2000);
		
		// Wait for page to fully load
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("/html[1]/body[1]/div[1]/div[2]/main[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")));
	}
	
	public void btnHuy() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement btn_HuyElement = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("/html[1]/body[1]/div[1]/div[2]/main[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[5]/button[2]/span[1]")));
		btn_HuyElement.click();
		Thread.sleep(2000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 0);");
		Thread.sleep(2000);
	}
	
	private WebElement waitForElement(By locator, int timeoutSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public long convertPrice(String price) {
        String cleaned = price.replace(".", "").replace("đ", "").trim();
        long value = Long.parseLong(cleaned);
        return value;
	}
	
	//Search by keyword -> has products 
	@Test(priority = 0,enabled = true)
	public void tk1_searchByCorrectName() throws InterruptedException {
		
		WebElement btn_home_product = driver.findElement(By.xpath("//a[@href='/products']"));
		btn_home_product.click();
		Thread.sleep(2000);
		
		WebElement txtSearchByName = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/main[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]"));
		String keySearch = "bánh dẻo";
		txtSearchByName.sendKeys(keySearch);
		
		WebElement btnSearch = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/main[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/button[1]"));
		btnSearch.click();
		Thread.sleep(2000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,450)");
		
		 List<WebElement> elements = driver.findElements(By.cssSelector(".text-xl.font-semibold.text-dark.mt-2.line-clamp-1"));
		 
		 for (WebElement element : elements) {
	           String nameElement = element.getText();
	           System.out.print(nameElement);
	           Assert.assertTrue(nameElement.toLowerCase().contains(keySearch));
	     }
		 Thread.sleep(3000);
	}
	
	//Search by keyword -> no product
	@Test(priority = 1,enabled = true)
	public void tk2_searchByIncorrectName() throws InterruptedException {
		
		btnHuy();
		
		WebElement txtSearchByName = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/main[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]"));
		String keySearch = "bánh bò";
		txtSearchByName.clear();
		txtSearchByName.sendKeys(keySearch);
		
		WebElement btnSearch = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/main[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/button[1]"));
		btnSearch.click();
		Thread.sleep(3000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,450)");
		Thread.sleep(2000);
		
		try {
			WebElement message = driver.findElement(By.xpath("//div[contains(text(),'Không tìm thấy sản phẩm phù hợp')]"));
			String actualMessage = message.getText().trim();
			System.out.println("Found message: '" + actualMessage + "'");
			Assert.assertTrue(actualMessage.contains("Không tìm thấy sản phẩm phù hợp"), 
					"Expected message containing 'Không tìm thấy sản phẩm phù hợp' but found: '" + actualMessage + "'");
		} catch (Exception e) {
			System.out.println("Could not find 'no product' message. Checking if products were found instead...");
			List<WebElement> products = driver.findElements(By.cssSelector(".text-xl.font-semibold.text-dark.mt-2.line-clamp-1"));
			System.out.println("Found " + products.size() + " products");
			Assert.assertTrue(products.isEmpty(), "Expected no products but found " + products.size() + " products");
		}
		Thread.sleep(3000);
	}
	
	//Search by price
	@Test(priority = 2,enabled = true)
	public void tk3_searchByPrice() throws InterruptedException {
		
		btnHuy();
		
		WebElement price1Element = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/main[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[3]/div[1]/div[1]/div[1]/div[1]/input[1]"));
		price1Element.clear();
		price1Element.sendKeys("400000");
		
		WebElement price2Element = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/main[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[3]/div[3]/div[1]/div[1]/div[1]/input[1]"));
		price2Element.clear();
		price2Element.sendKeys("600000");
		
		WebElement btnSearch = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/main[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[3]/button[1]"));
		btnSearch.click();
		Thread.sleep(3000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,450)");
		Thread.sleep(2000);
		
		 List<WebElement> elements = driver.findElements(By.cssSelector(".text-xl.font-bold.text-dark"));
		 System.out.println("Found " + elements.size() + " products with prices");
		 
		 for (WebElement element : elements) {
			 try {
				 long priceElement = convertPrice(element.getText());
				 System.out.println("Price found: " + priceElement);
				 Assert.assertTrue(priceElement >= 400000 && priceElement <= 600000, 
						 "Price " + priceElement + " is not in range 400000-600000");
			 } catch (NumberFormatException e) {
				 System.out.println("Could not parse price: " + element.getText());
			 }
	     }
		 Thread.sleep(3000);
	}
	
	//Search by brand
	@Test(priority = 3,enabled = true)
	public void tk4_searchByBrand() throws InterruptedException {
		
		btnHuy();
		
		WebElement checkboxBrand = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/main[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[4]/div[3]/div[1]/div[1]/div[3]/label[1]/span[1]"));
		String brandNameExpectString = checkboxBrand.getText().trim();
		System.out.println("Expected brand: '" + brandNameExpectString + "'");
		checkboxBrand.click();
		Thread.sleep(1000);
		
		WebElement btnSearch = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/main[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[3]/button[1]"));
		btnSearch.click();
		Thread.sleep(3000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,450)");
		Thread.sleep(2000);
		
		 List<WebElement> elements = driver.findElements(By.cssSelector(".text-base.font-semibold.text-emerald.mt-2"));
		 System.out.println("Found " + elements.size() + " products with brand");
		 
		 for (WebElement element : elements) {
	           String brandNameString = element.getText().trim();
	           System.out.println("Found brand: '" + brandNameString + "'");
	           Assert.assertEquals(brandNameString, brandNameExpectString, 
	        		   "Brand name mismatch. Expected: '" + brandNameExpectString + "', Found: '" + brandNameString + "'");
	     }
		 Thread.sleep(3000);
	}
	
	@AfterMethod
	public void afterTest() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
	}
}
