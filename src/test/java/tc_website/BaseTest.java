package tc_website;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Base test class with flexible WebDriver setup
 * No hardcoded paths - uses WebDriverManager for automatic driver management
 */
public class BaseTest {
    
    protected WebDriver driver;
    
    @BeforeMethod
    public void setUp() throws InterruptedException {
        
        // Setup browser based on system property (default: chrome)
        String browserName = System.getProperty("browser", "chrome");
        
        switch (browserName.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "chrome":
            default:
                // Auto-download and setup ChromeDriver - force latest version
                WebDriverManager.chromedriver().setup();
                
                // Optional: Chrome options for better automation
                ChromeOptions options = new ChromeOptions();
                
                // Add options to make Chrome more stable for testing
                options.addArguments("--disable-blink-features=AutomationControlled");
                options.addArguments("--disable-extensions");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                
                driver = new ChromeDriver(options);
                break;
        }
        
        // Common browser setup
        driver.manage().window().maximize();
        driver.navigate().to("https://nguyetviet.io.vn");
        
        // Wait for page load
        Thread.sleep(2000);
    }
    
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}