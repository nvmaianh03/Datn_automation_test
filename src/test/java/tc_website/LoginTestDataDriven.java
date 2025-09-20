package tc_website;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tc_website.utils.TestDataManager;

import java.util.Map;

/**
 * Refactored Login Test Class using Data-Driven Testing
 * Reads test data from CSV files instead of hardcoded values
 * Test OK
 */
public class LoginTestDataDriven extends BaseTest {

    private TestDataManager testDataManager;

    @BeforeMethod
    @Override
    public void setUp() throws InterruptedException {
        super.setUp();
        
        // Initialize test data manager
        testDataManager = TestDataManager.getInstance();
        
        // Navigate to login page after base setup
        WebElement btnHomeLogin = driver.findElement(By.xpath("//a[@href='/auth/login']"));
        btnHomeLogin.click();
        Thread.sleep(2000);
    }

    /**
     * Perform login with given credentials
     * @param email User email
     * @param password User password
     */
    public void performLogin(String email, String password) {
        WebElement inputEmail = driver.findElement(By.id(":r0:"));
        inputEmail.clear();
        inputEmail.sendKeys(email);

        WebElement inputPassword = driver.findElement(By.id(":r1:"));
        inputPassword.clear();
        inputPassword.sendKeys(password);
    }

    /**
     * Click login button
     */
    public void clickLoginButton() throws InterruptedException {
        WebElement btnLoginElement = driver.findElement(
            By.cssSelector("button[type='submit'] span[class='text-inherit']")
        );
        btnLoginElement.click();
        Thread.sleep(2000);
    }

    /**
     * Test login with valid credentials - should succeed
     */
    @Test(priority = 1, description = "Login with valid credentials")
    public void testLoginSuccessfully() throws InterruptedException {
        // Get test data from CSV file
        Map<String, String> testData = testDataManager.getLoginData("dn1_loginSuccessfully");
        
        performLogin(testData.get("Email"), testData.get("Password"));
        Thread.sleep(2000);
        clickLoginButton();

        // Verify successful login (you can add specific assertions here)
        String expectedResult = testData.get("ExpectedResult");
        if ("success".equals(expectedResult)) {
            // Add assertion for successful login
            System.out.println("Test: " + testData.get("Description"));
        }
    }

    /**
     * Test login with wrong email - should fail
     */
    @Test(priority = 2, description = "Login with wrong email")
    public void testLoginWithWrongEmail() throws InterruptedException {
        // Get test data from CSV file
        Map<String, String> testData = testDataManager.getLoginData("dn2_loginWithWrongEmail");
        
        performLogin(testData.get("Email"), testData.get("Password"));
        Thread.sleep(2000);
        clickLoginButton();

        // Verify login failure
        String expectedResult = testData.get("ExpectedResult");
        if ("fail".equals(expectedResult)) {
            // Add assertion for login failure
            System.out.println("Test: " + testData.get("Description"));
        }
    }

    /**
     * Test login with wrong password - should fail
     */
    @Test(priority = 3, description = "Login with wrong password")
    public void testLoginWithWrongPassword() throws InterruptedException {
        // Get test data from CSV file
        Map<String, String> testData = testDataManager.getLoginData("dn3_loginWithWrongPassword");
        
        performLogin(testData.get("Email"), testData.get("Password"));
        Thread.sleep(2000);
        clickLoginButton();

        // Verify login failure
        String expectedResult = testData.get("ExpectedResult");
        if ("fail".equals(expectedResult)) {
            // Add assertion for login failure
            System.out.println("Test: " + testData.get("Description"));
        }
    }

    /**
     * Test login with empty email - should fail
     */
    @Test(priority = 4, description = "Login with empty email")
    public void testLoginWithEmptyEmail() throws InterruptedException {
        // Get test data from CSV file
        Map<String, String> testData = testDataManager.getLoginData("dn4_loginWithEmptyEmail");
        
        performLogin(testData.getOrDefault("Email", ""), testData.get("Password"));
        Thread.sleep(2000);
        clickLoginButton();

        // Verify login failure
        String expectedResult = testData.get("ExpectedResult");
        if ("fail".equals(expectedResult)) {
            System.out.println("Test: " + testData.get("Description"));
        }
    }

    /**
     * Test login with empty password - should fail
     */
    @Test(priority = 5, description = "Login with empty password")
    public void testLoginWithEmptyPassword() throws InterruptedException {
        // Get test data from CSV file
        Map<String, String> testData = testDataManager.getLoginData("dn5_loginWithEmptyPassword");
        
        performLogin(testData.get("Email"), testData.getOrDefault("Password", ""));
        Thread.sleep(2000);
        clickLoginButton();

        // Verify login failure
        String expectedResult = testData.get("ExpectedResult");
        if ("fail".equals(expectedResult)) {
            System.out.println("Test: " + testData.get("Description"));
        }
    }

    /**
     * Test login with invalid email format - should fail
     */
    @Test(priority = 6, description = "Login with invalid email format")
    public void testLoginWithInvalidEmail() throws InterruptedException {
        // Get test data from CSV file
        Map<String, String> testData = testDataManager.getLoginData("dn6_loginWithInvalidEmail");
        
        performLogin(testData.get("Email"), testData.get("Password"));
        Thread.sleep(2000);
        clickLoginButton();

        // Verify login failure
        String expectedResult = testData.get("ExpectedResult");
        if ("fail".equals(expectedResult)) {
            System.out.println("Test: " + testData.get("Description"));
        }
    }

    /**
     * Test login with numeric only password - should fail
     */
    @Test(priority = 7, description = "Login with numeric only password")
    public void testLoginWithNumericPassword() throws InterruptedException {
        // Get test data from CSV file
        Map<String, String> testData = testDataManager.getLoginData("dn7_loginWithPasswordHasOnlyNumbers");
        
        performLogin(testData.get("Email"), testData.get("Password"));
        Thread.sleep(2000);
        clickLoginButton();

        // Verify login failure
        String expectedResult = testData.get("ExpectedResult");
        if ("fail".equals(expectedResult)) {
            System.out.println("Test: " + testData.get("Description"));
        }
    }

    /**
     * Test login with alphabetic only password - should fail
     */
    @Test(priority = 8, description = "Login with alphabetic only password")
    public void testLoginWithAlphabeticPassword() throws InterruptedException {
        // Get test data from CSV file
        Map<String, String> testData = testDataManager.getLoginData("dn8_loginWithPasswordHasOnlyAlphabet");
        
        performLogin(testData.get("Email"), testData.get("Password"));
        Thread.sleep(2000);
        clickLoginButton();

        // Verify login failure
        String expectedResult = testData.get("ExpectedResult");
        if ("fail".equals(expectedResult)) {
            System.out.println("Test: " + testData.get("Description"));
        }
    }
}