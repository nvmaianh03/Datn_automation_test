package tc_website.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

/**
 * Smart Locator Helper sử dụng AI-powered element detection
 * Tự động tìm elements với nhiều strategy khác nhau
 */
public class SmartLocatorHelper {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    public SmartLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
    
    /**
     * Tìm email input field bằng AI-powered locators
     * Strategy: type -> placeholder -> label -> relative -> fallback
     */
    public WebElement findEmailField() {
        System.out.println("🔍 Smart Locator: Tìm email field...");
        
        try {
            // Strategy 1: Tìm bằng semantic attributes (type=email)
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input[type='email']")
            ));
            System.out.println("✅ Tìm thấy email field bằng type='email'");
            return element;
            
        } catch (Exception e1) {
            try {
                // Strategy 2: Tìm bằng name/id chứa 'email'
                WebElement element = driver.findElement(
                    By.cssSelector("input[name*='email'], input[id*='email']")
                );
                System.out.println("✅ Tìm thấy email field bằng name/id contains 'email'");
                return element;
                
            } catch (Exception e2) {
                try {
                    // Strategy 3: Tìm bằng placeholder text
                    WebElement element = driver.findElement(
                        By.cssSelector("input[placeholder*='email'], input[placeholder*='Email'], input[placeholder*='E-mail']")
                    );
                    System.out.println("✅ Tìm thấy email field bằng placeholder");
                    return element;
                    
                } catch (Exception e3) {
                    try {
                        // Strategy 4: Relative locator với label (AI-powered)
                        WebElement emailLabel = driver.findElement(
                            By.xpath("//label[contains(text(),'email') or contains(text(),'Email') or contains(text(),'E-mail')]")
                        );
                        WebElement element = driver.findElement(
                            RelativeLocator.with(By.tagName("input")).below(emailLabel)
                        );
                        System.out.println("✅ Tìm thấy email field bằng RelativeLocator với label");
                        return element;
                        
                    } catch (Exception e4) {
                        try {
                            // Strategy 5: Tìm input đầu tiên trong form login
                            List<WebElement> inputs = driver.findElements(By.tagName("input"));
                            WebElement firstInput = inputs.stream()
                                .filter(input -> input.isDisplayed() && input.isEnabled())
                                .findFirst()
                                .orElse(null);
                            
                            if (firstInput != null) {
                                System.out.println("✅ Tìm thấy email field bằng first visible input");
                                return firstInput;
                            }
                            
                        } catch (Exception e5) {
                            // Strategy 6: Fallback về ID động
                            System.out.println("⚠️ Fallback về dynamic ID");
                            return driver.findElement(By.id(":r0:"));
                        }
                    }
                }
            }
        }
        
        throw new RuntimeException("❌ Smart Locator: Không thể tìm thấy email field");
    }
    
    /**
     * Tìm password input field bằng AI-powered locators
     */
    public WebElement findPasswordField() {
        System.out.println("🔍 Smart Locator: Tìm password field...");
        
        try {
            // Strategy 1: Tìm bằng type='password'
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input[type='password']")
            ));
            System.out.println("✅ Tìm thấy password field bằng type='password'");
            return element;
            
        } catch (Exception e1) {
            try {
                // Strategy 2: Tìm bằng name/id chứa 'password'
                WebElement element = driver.findElement(
                    By.cssSelector("input[name*='password'], input[id*='password']")
                );
                System.out.println("✅ Tìm thấy password field bằng name/id");
                return element;
                
            } catch (Exception e2) {
                try {
                    // Strategy 3: RelativeLocator - field sau email field
                    WebElement emailField = findEmailField();
                    WebElement element = driver.findElement(
                        RelativeLocator.with(By.tagName("input")).below(emailField)
                    );
                    System.out.println("✅ Tìm thấy password field bằng RelativeLocator");
                    return element;
                    
                } catch (Exception e3) {
                    try {
                        // Strategy 4: Tìm input thứ 2 trong form
                        List<WebElement> inputs = driver.findElements(By.tagName("input"));
                        if (inputs.size() >= 2) {
                            WebElement secondInput = inputs.get(1);
                            System.out.println("✅ Tìm thấy password field bằng second input");
                            return secondInput;
                        }
                        
                    } catch (Exception e4) {
                        // Fallback về ID động
                        System.out.println("⚠️ Fallback về dynamic ID");
                        return driver.findElement(By.id(":r1:"));
                    }
                }
            }
        }
        
        throw new RuntimeException("❌ Smart Locator: Không thể tìm thấy password field");
    }
    
    /**
     * Tìm login button bằng AI-powered locators
     */
    public WebElement findLoginButton() {
        System.out.println("🔍 Smart Locator: Tìm login button...");
        
        try {
            // Strategy 1: Tìm bằng text content (multilingual)
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Đăng nhập') or contains(text(),'Login') or contains(text(),'ĐĂNG NHẬP') or contains(text(),'Sign in')]")
            ));
            System.out.println("✅ Tìm thấy login button bằng text content");
            return element;
            
        } catch (Exception e1) {
            try {
                // Strategy 2: Tìm bằng type='submit'
                WebElement element = driver.findElement(By.cssSelector("button[type='submit']"));
                System.out.println("✅ Tìm thấy login button bằng type='submit'");
                return element;
                
            } catch (Exception e2) {
                try {
                    // Strategy 3: RelativeLocator - button sau password field
                    WebElement passwordField = findPasswordField();
                    WebElement element = driver.findElement(
                        RelativeLocator.with(By.tagName("button")).below(passwordField)
                    );
                    System.out.println("✅ Tìm thấy login button bằng RelativeLocator");
                    return element;
                    
                } catch (Exception e3) {
                    try {
                        // Strategy 4: Tìm button trong form
                        List<WebElement> buttons = driver.findElements(By.tagName("button"));
                        WebElement submitButton = buttons.stream()
                            .filter(btn -> btn.isDisplayed() && btn.isEnabled())
                            .findFirst()
                            .orElse(null);
                        
                        if (submitButton != null) {
                            System.out.println("✅ Tìm thấy login button bằng first visible button");
                            return submitButton;
                        }
                        
                    } catch (Exception e4) {
                        // Fallback CSS selector
                        System.out.println("⚠️ Fallback về CSS selector");
                        return driver.findElement(By.cssSelector("button[type='submit'] span[class='text-inherit']"));
                    }
                }
            }
        }
        
        throw new RuntimeException("❌ Smart Locator: Không thể tìm thấy login button");
    }
    
    /**
     * Tìm login navigation link
     */
    public WebElement findLoginNavLink() {
        System.out.println("🔍 Smart Locator: Tìm login nav link...");
        
        try {
            // Strategy 1: Tìm bằng href='/auth/login'
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("a[href='/auth/login'], a[href*='login']")
            ));
            System.out.println("✅ Tìm thấy login nav bằng href");
            return element;
            
        } catch (Exception e1) {
            try {
                // Strategy 2: Tìm bằng text content
                WebElement element = driver.findElement(
                    By.xpath("//a[contains(text(),'Đăng nhập') or contains(text(),'Login') or contains(text(),'Sign in')]")
                );
                System.out.println("✅ Tìm thấy login nav bằng text");
                return element;
                
            } catch (Exception e2) {
                // Fallback
                System.out.println("⚠️ Fallback về xpath");
                return driver.findElement(By.xpath("//a[@href='/auth/login']"));
            }
        }
    }
    
    /**
     * Kiểm tra xem có đang ở trang login không
     */
    public boolean isLoginPage() {
        try {
            return driver.getCurrentUrl().contains("login") || 
                   driver.getPageSource().contains("Đăng nhập") ||
                   driver.findElements(By.cssSelector("input[type='password']")).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Tìm error message sau khi login fail
     */
    public WebElement findErrorMessage() {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector(".error, .alert-danger, .text-red-500, [class*='error'], [class*='danger']")
            ));
        } catch (Exception e) {
            return null;
        }
    }
}