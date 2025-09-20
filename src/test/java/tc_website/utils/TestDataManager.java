package tc_website.utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Central manager for test data from various sources (Excel, CSV, Properties)
 */
public class TestDataManager {
    
    private static TestDataManager instance;
    private Map<String, Object> dataProviders = new HashMap<>();
    private Properties config;
    
    private TestDataManager() {
        loadConfiguration();
    }
    
    public static TestDataManager getInstance() {
        if (instance == null) {
            instance = new TestDataManager();
        }
        return instance;
    }
    
    /**
     * Load configuration from properties file
     */
    private void loadConfiguration() {
        config = new Properties();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("testdata-config.properties")) {
            if (is != null) {
                config.load(is);
            }
        } catch (Exception e) {
            // Use default values if config file not found
            config.setProperty("data.format", "csv");
            config.setProperty("data.path", "src/test/resources/testdata/");
        }
    }
    
    /**
     * Get login test data
     * @param testCase Test case identifier
     * @return Map containing login data (email, password, etc.)
     */
    public Map<String, String> getLoginData(String testCase) {
        return getTestData("login", testCase);
    }
    
    /**
     * Get registration test data
     * @param testCase Test case identifier
     * @return Map containing registration data (name, email, password, etc.)
     */
    public Map<String, String> getRegistrationData(String testCase) {
        return getTestData("registration", testCase);
    }
    
    /**
     * Get change password test data
     * @param testCase Test case identifier
     * @return Map containing password change data
     */
    public Map<String, String> getChangePasswordData(String testCase) {
        return getTestData("changepassword", testCase);
    }
    
    /**
     * Get user profile test data
     * @param testCase Test case identifier
     * @return Map containing profile data
     */
    public Map<String, String> getProfileData(String testCase) {
        return getTestData("profile", testCase);
    }
    
    /**
     * Get search test data
     * @param testCase Test case identifier
     * @return Map containing search data
     */
    public Map<String, String> getSearchData(String testCase) {
        return getTestData("search", testCase);
    }
    
    /**
     * Get logout test data
     * @param testCase Test case identifier
     * @return Map containing logout data
     */
    public Map<String, String> getLogoutData(String testCase) {
        return getTestData("logout", testCase);
    }
    
    /**
     * Get account test data
     * @param accountID Account identifier
     * @return Map containing account data
     */
    public Map<String, String> getAccountData(String accountID) {
        return getTestData("accounts", accountID);
    }
    
    /**
     * Get cart test data
     * @param testCase Test case identifier
     * @return Map containing cart data
     */
    public Map<String, String> getCartData(String testCase) {
        return getTestData("cart", testCase);
    }
    
    /**
     * Get payment test data
     * @param testCase Test case identifier
     * @return Map containing payment data
     */
    public Map<String, String> getPaymentData(String testCase) {
        return getTestData("payment", testCase);
    }
    
    /**
     * Get product test data
     * @param testCase Test case identifier
     * @return Map containing product data
     */
    public Map<String, String> getProductData(String testCase) {
        return getTestData("product", testCase);
    }
    
    /**
     * Generic method to get test data from any data source
     * @param dataType Type of data (login, registration, etc.)
     * @param testCase Test case identifier
     * @return Test data map
     */
    private Map<String, String> getTestData(String dataType, String testCase) {
        String key = dataType.toLowerCase();
        
        if (!dataProviders.containsKey(key)) {
            loadDataProvider(key);
        }
        
        Object provider = dataProviders.get(key);
        
        if (provider instanceof ExcelDataProvider) {
            return ((ExcelDataProvider) provider).getDataByTestCase(testCase);
        } else if (provider instanceof CsvDataProvider) {
            return ((CsvDataProvider) provider).getDataByTestCase(testCase);
        }
        
        return new HashMap<>();
    }
    
    /**
     * Load data provider based on configuration
     * @param dataType Type of data to load
     */
    private void loadDataProvider(String dataType) {
        String format = config.getProperty("data.format", "csv");
        String basePath = config.getProperty("data.path", "src/test/resources/testdata/");
        
        try {
            String filePath = basePath + dataType + "." + format;
            
            if ("excel".equalsIgnoreCase(format) || "xlsx".equalsIgnoreCase(format)) {
                dataProviders.put(dataType, new ExcelDataProvider(filePath, "TestData"));
            } else if ("csv".equalsIgnoreCase(format)) {
                dataProviders.put(dataType, new CsvDataProvider(filePath));
            }
        } catch (Exception e) {
            System.err.println("Failed to load " + dataType + " data: " + e.getMessage());
            // Return empty provider or use fallback data
        }
    }
    
    /**
     * Get all test data for a specific type
     * @param dataType Type of data
     * @return List of all test data rows
     */
    public List<Map<String, String>> getAllTestData(String dataType) {
        String key = dataType.toLowerCase();
        
        if (!dataProviders.containsKey(key)) {
            loadDataProvider(key);
        }
        
        Object provider = dataProviders.get(key);
        
        if (provider instanceof ExcelDataProvider) {
            return ((ExcelDataProvider) provider).getAllData();
        } else if (provider instanceof CsvDataProvider) {
            return ((CsvDataProvider) provider).getAllData();
        }
        
        return List.of();
    }
    
    /**
     * Get payment test data
     * @param testCase Test case identifier
     * @return Map containing payment data
     */
//    public Map<String, String> getPaymentData(String testCase) {
//        return getTestData("payment", testCase);
//    }
//
    
    /**
     * Get login test data
     * @param testCase Test case identifier  
     * @return Map containing login data
     */
//    public Map<String, String> getLoginData(String testCase) {
//        return getTestData("login", testCase);
//    }
//
    /**
     * Get register test data
     * @param testCase Test case identifier  
     * @return Map containing register data
     */
    public Map<String, String> getRegisterData(String testCase) {
        return getTestData("register", testCase);
    }
    
    /**
     * Clean up resources
     */
    public void cleanup() {
        dataProviders.values().forEach(provider -> {
            if (provider instanceof ExcelDataProvider) {
                ((ExcelDataProvider) provider).close();
            }
        });
        dataProviders.clear();
    }
}