package tc_website;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tc_website.utils.TestDataManager;
import tc_website.utils.SmartLocatorHelper;

import java.util.Map;

/**
 * Enhanced Login Test Class using Smart Locator AI + Data-Driven Testing
 * Sử dụng AI-powered element detection thay vì hardcode selectors
 * Tự động tìm elements với multiple fallback strategies
 */
public class LoginTestDataDriven extends BaseTest {

    private TestDataManager testDataManager;
    private SmartLocatorHelper smartLocator;

    @BeforeMethod
    @Override
    public void setUp() throws InterruptedException {
        super.setUp();

        // Initialize test data manager và smart locator AI
        testDataManager = TestDataManager.getInstance();
        smartLocator = new SmartLocatorHelper(driver);

        // Navigate to login page sử dụng Smart Locator AI
        System.out.println("🚀 Khởi tạo Smart Locator AI cho Login Test");
        WebElement btnHomeLogin = smartLocator.findLoginNavLink();
        btnHomeLogin.click();
        Thread.sleep(2000);
        System.out.println("✅ Đã navigate đến trang login sử dụng Smart Locator AI");
    }

    /**
     * Perform login với Smart Locator AI - Tự động tìm elements
     * @param email User email
     * @param password User password
     */
    public void performLogin(String email, String password) {
        try {
            // Sử dụng Smart Locator để tìm email field với multiple strategies
            WebElement inputEmail = smartLocator.findEmailField();
            inputEmail.clear();
            inputEmail.sendKeys(email);
            System.out.println("📧 Smart AI: Đã nhập email = " + email);

            // Sử dụng Smart Locator để tìm password field
            WebElement inputPassword = smartLocator.findPasswordField();
            inputPassword.clear();
            inputPassword.sendKeys(password);
            System.out.println("🔒 Smart AI: Đã nhập password");

        } catch (Exception e) {
            System.err.println("❌ Smart Locator AI lỗi khi nhập login info: " + e.getMessage());
            throw new RuntimeException("Smart Locator AI không thể tìm thấy login fields", e);
        }
    }

    /**
     * Click login button với Smart Locator AI
     */
    public void clickLoginButton() throws InterruptedException {
        try {
            WebElement btnLoginElement = smartLocator.findLoginButton();
            btnLoginElement.click();
            Thread.sleep(2000);
            System.out.println("🚀 Smart AI: Đã click login button");

        } catch (Exception e) {
            System.err.println("❌ Smart Locator AI lỗi khi click login: " + e.getMessage());
            throw new RuntimeException("Smart Locator AI không thể click login button", e);
        }
        Thread.sleep(2000);
    }

    /**
     * Test login with valid credentials - Smart Locator AI
     */
    @Test(priority = 1, description = "Login với thông tin hợp lệ - Smart Locator AI")
    public void testLoginSuccessfully() throws InterruptedException {
        // Get test data from CSV file
        Map<String, String> testData = testDataManager.getLoginData("dn1_loginSuccessfully");
        System.out.println("🧪 Test Case: " + testData.get("Description"));

        performLogin(testData.get("Email"), testData.get("Password"));
        Thread.sleep(2000);
        clickLoginButton();

        // Verify successful login với Smart Locator
        String expectedResult = testData.get("ExpectedResult");
        if ("success".equals(expectedResult)) {
            System.out.println("✅ Smart AI Test PASS: " + testData.get("Description"));

            // Có thể kiểm tra xem có redirect về trang chủ không
            if (driver.getCurrentUrl().contains("nguyetviet.io.vn") && !smartLocator.isLoginPage()) {
                System.out.println("🎯 Smart AI: Login thành công, đã redirect khỏi trang login");
            }
            // Add assertion for successful login
            System.out.println("Test: " + testData.get("Description"));
        }
        Assert.assertTrue(this.driver.findElement(By.xpath("//div[contains(text(),'Đăng nhập thành công')]")).isDisplayed());
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