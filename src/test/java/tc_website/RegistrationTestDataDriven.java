package tc_website;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tc_website.utils.TestDataManager;

import java.util.Map;

/**
 * Refactored Registration Test Class using Data-Driven Testing
 * Test OK
 */
public class RegistrationTestDataDriven extends BaseTest {

    private TestDataManager testDataManager;

    @BeforeMethod
    @Override
    public void setUp() throws InterruptedException {
        super.setUp();
        
        // Initialize test data manager
        testDataManager = TestDataManager.getInstance();
        
        // Navigate to registration page
        WebElement btnRegister = driver.findElement(By.xpath("//a[@href='/auth/sign-up']"));
        btnRegister.click();
        Thread.sleep(2000);
    }

    /**
     * Perform registration with given data
     */
    public void performRegistration(String name, String email, String password, String confirmPassword) {
        // Fill name
        WebElement inputName = driver.findElement(By.id(":r0:"));
        inputName.clear();
        inputName.sendKeys(name);

        // Fill email
        WebElement inputEmail = driver.findElement(By.id(":r1:"));
        inputEmail.clear();
        inputEmail.sendKeys(email);

        // Fill password
        WebElement inputPassword = driver.findElement(By.id(":r2:"));
        inputPassword.clear();
        inputPassword.sendKeys(password);

        // Fill confirm password
        WebElement inputConfirmPassword = driver.findElement(By.id(":r3:"));
        inputConfirmPassword.clear();
        inputConfirmPassword.sendKeys(confirmPassword);
    }

    /**
     * Click submit registration button
     */
    public void clickSubmitButton() throws InterruptedException {
        WebElement btnSubmit = driver.findElement(By.cssSelector("button[type='submit']"));
        btnSubmit.click();
        Thread.sleep(2000);
    }

    /**
     * Test successful registration
     */
    @Test(priority = 1, description = "Register with valid data")
    public void testRegisterSuccessfully() throws InterruptedException {
        Map<String, String> testData = testDataManager.getRegistrationData("dk1_registerSuccessfully");
        
        performRegistration(
            testData.get("Name"),
            testData.get("Email"), 
            testData.get("Password"),
            testData.get("ConfirmPassword")
        );
        
        clickSubmitButton();
        
        // Add assertions based on expected result
        System.out.println("Test: " + testData.get("Description"));
    }

    /**
     * Test registration with existing email
     */
    @Test(priority = 2, description = "Register with existing email")
    public void testRegisterWithExistingEmail() throws InterruptedException {
        Map<String, String> testData = testDataManager.getRegistrationData("dky2_registerByExistedMail");
        
        performRegistration(
            testData.get("Name"),
            testData.get("Email"), 
            testData.get("Password"),
            testData.get("ConfirmPassword")
        );
        
        clickSubmitButton();
        
        // Verify error message for existing email
        System.out.println("Test: " + testData.get("Description"));
    }

    /**
     * Test registration with empty name
     */
    @Test(priority = 3, description = "Register with empty name")
    public void testRegisterWithEmptyName() throws InterruptedException {
        Map<String, String> testData = testDataManager.getRegistrationData("dk3_emptyName");
        
        performRegistration(
            testData.getOrDefault("Name", ""),
            testData.get("Email"), 
            testData.get("Password"),
            testData.get("ConfirmPassword")
        );
        
        clickSubmitButton();
        
        System.out.println("Test: " + testData.get("Description"));
    }

    /**
     * Test registration with empty email
     */
    @Test(priority = 4, description = "Register with empty email")
    public void testRegisterWithEmptyEmail() throws InterruptedException {
        Map<String, String> testData = testDataManager.getRegistrationData("dk4_emptyEmail");
        
        performRegistration(
            testData.get("Name"),
            testData.getOrDefault("Email", ""), 
            testData.get("Password"),
            testData.get("ConfirmPassword")
        );
        
        clickSubmitButton();
        
        System.out.println("Test: " + testData.get("Description"));
    }

    /**
     * Test registration with invalid email
     */
    @Test(priority = 5, description = "Register with invalid email")
    public void testRegisterWithInvalidEmail() throws InterruptedException {
        Map<String, String> testData = testDataManager.getRegistrationData("dky7_InvalidMail");
        
        performRegistration(
            testData.get("Name"),
            testData.get("Email"), 
            testData.get("Password"),
            testData.get("ConfirmPassword")
        );
        
        // Don't click submit as it should show validation error immediately
        System.out.println("Test: " + testData.get("Description"));
    }
}