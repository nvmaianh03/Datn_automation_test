package tc_website;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

public class tc_search extends BaseTest {

	@Override
	@BeforeMethod
	public void setUp() throws InterruptedException {
		super.setUp();
		
		// Navigate to URL
		driver.navigate().to("https://nguyetviet.io.vn");

		// Wait web load
		Thread.sleep(2000);
	}
	
	public void btnHuy() throws InterruptedException {
		WebElement btn_HuyElement = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/main[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[5]/button[2]/span[1]"));
		btn_HuyElement.click();
		Thread.sleep(2000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 0);");
		Thread.sleep(2000);
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
		txtSearchByName.sendKeys(keySearch);
		
		WebElement btnSearch = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/main[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/button[1]"));
		btnSearch.click();
		Thread.sleep(2000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,450)");
		
		WebElement message = driver.findElement(By.xpath("//div[contains(text(),'Không tìm thấy sản phẩm phù hợp')]"));
		Assert.assertEquals(message, "Không tìm thấy sản phẩm phù hợp");
		Thread.sleep(3000);
	}
	
	//Search by price
	@Test(priority = 2,enabled = true)
	public void tk3_searchByPrice() throws InterruptedException {
		
		btnHuy();
		WebElement price1Element = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/main[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[3]/div[1]/div[1]/div[1]/div[1]/input[1]"));
		price1Element.sendKeys("400000");
		
		WebElement price2Element = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/main[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[3]/div[3]/div[1]/div[1]/div[1]/input[1]"));
		price2Element.sendKeys("600000");
		
		WebElement btnSearch = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/main[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[3]/button[1]"));
		btnSearch.click();
		Thread.sleep(2000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,450)");
		
		 List<WebElement> elements = driver.findElements(By.cssSelector(".text-xl.font-bold.text-dark"));
		 
		 for (WebElement element : elements) {
	           long priceElement = convertPrice(element.getText());
	           System.out.print(priceElement);
	           Assert.assertTrue(priceElement > 400000 && priceElement < 600000);
	     }
		 Thread.sleep(3000);
	}
	
	//Search by brand
	@Test(priority = 3,enabled = true)
	public void tk4_searchByBrand() throws InterruptedException {
		
		btnHuy();
		
		WebElement checkboxBrand = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/main[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[4]/div[3]/div[1]/div[1]/div[3]/label[1]/span[1]"));
		String brandNameExpectString = checkboxBrand.getText();
		checkboxBrand.click();
		Thread.sleep(1000);
		
		WebElement btnSearch = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/main[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[3]/button[1]"));
		btnSearch.click();
		Thread.sleep(2000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,450)");
		
		 List<WebElement> elements = driver.findElements(By.cssSelector(".text-base.font-semibold.text-emerald.mt-2"));
		 
		 for (WebElement element : elements) {
	           String brandNameString = element.getText();
	           System.out.print(brandNameString);
	           Assert.assertEquals(brandNameString, brandNameExpectString);
	     }
		 Thread.sleep(3000);
	}
	
	@AfterMethod
	public void afterTest() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
	}
}
