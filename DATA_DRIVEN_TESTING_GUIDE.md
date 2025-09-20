# Data-Driven Testing Implementation Guide

## Overview
Dự án đã được refactor để hỗ trợ **Data-Driven Testing** với khả năng đọc test data từ:
- ✅ **CSV files** (đã implement)
- ✅ **Excel files** (.xls, .xlsx) (đã implement)
- ✅ **Cấu hình linh hoạt** qua properties file

## Cấu trúc Test Data

### 1. **CSV Format (Recommended)**
```
src/test/resources/testdata/
├── login.csv           # Login test data
├── registration.csv    # Registration test data  
├── changepassword.csv  # Change password test data
└── search.csv          # Search test data
```

### 2. **Excel Format**
```
src/test/resources/testdata/
├── login.xlsx
├── registration.xlsx
└── search.xlsx
```

## Cách sử dụng

### 1. **Cấu hình Data Source**
File: `src/test/resources/testdata-config.properties`
```properties
# CSV mode
data.format=csv
data.path=src/test/resources/testdata/

# Excel mode  
data.format=xlsx
data.path=src/test/resources/testdata/
excel.default.sheet=TestData
```

### 2. **Sử dụng trong Test Class**
```java
public class LoginTestDataDriven extends BaseTest {
    private TestDataManager testDataManager;

    @BeforeMethod
    public void setUp() throws InterruptedException {
        super.setUp();
        testDataManager = TestDataManager.getInstance();
    }

    @Test
    public void testLogin() throws InterruptedException {
        // Đọc data từ CSV/Excel
        Map<String, String> testData = testDataManager.getLoginData("dn1_loginSuccessfully");
        
        // Sử dụng data
        performLogin(testData.get("Email"), testData.get("Password"));
        
        // Verify kết quả
        String expectedResult = testData.get("ExpectedResult");
        // Add assertions...
    }
}
```

### 3. **CSV File Format**
File: `login.csv`
```csv
TestCase,Email,Password,ExpectedResult,Description
dn1_loginSuccessfully,user@gmail.com,Pass123,success,Login with valid credentials
dn2_loginWithWrongEmail,wrong@gmail.com,Pass123,fail,Login with wrong email
```

### 4. **Excel File Format**
Sheet name: "TestData"
| TestCase | Email | Password | ExpectedResult | Description |
|----------|-------|----------|----------------|-------------|
| dn1_loginSuccessfully | user@gmail.com | Pass123 | success | Login with valid credentials |

## Classes đã tạo

### 1. **Utility Classes**
- `ExcelDataProvider.java` - Đọc data từ Excel files
- `CsvDataProvider.java` - Đọc data từ CSV files  
- `TestDataManager.java` - Quản lý tất cả data sources

### 2. **Refactored Test Classes**
- `LoginTestDataDriven.java` - Login tests với data-driven
- `RegistrationTestDataDriven.java` - Registration tests với data-driven

### 3. **Data Files**
- `login.csv` - Login test data (8 test cases)
- `registration.csv` - Registration test data (9 test cases)
- `changepassword.csv` - Change password test data
- `search.csv` - Search test data

## Ưu điểm của Data-Driven Testing

### ✅ **Maintenance**
- Thay đổi test data không cần modify code
- Non-technical user có thể update test data
- Dễ dàng add thêm test cases

### ✅ **Scalability**
- Chạy cùng 1 test với nhiều bộ data khác nhau
- Hỗ trợ test với large dataset
- Parallel execution với different data

### ✅ **Flexibility**
- Switch giữa CSV và Excel dễ dàng
- Configure data source qua properties
- Support multiple data formats

### ✅ **Reusability**
- Same test logic, different data
- Share test data across different test suites
- Centralized data management

## Cách chạy Tests

```bash
# Chạy data-driven tests
mvn test -Dtest=LoginTestDataDriven
mvn test -Dtest=RegistrationTestDataDriven

# Chạy với specific data format
mvn test -Ddata.format=csv
mvn test -Ddata.format=excel

# Chạy tất cả data-driven tests
mvn test -Dtest="*DataDriven"
```

## Migration từ Hardcoded

### Before (Hardcoded):
```java
@Test
public void testLogin() {
    login("user@gmail.com", "password123");
}
```

### After (Data-Driven):
```java
@Test  
public void testLogin() {
    Map<String, String> data = testDataManager.getLoginData("testLogin");
    login(data.get("Email"), data.get("Password"));
}
```

## Best Practices

1. **Naming Convention**
   - TestCase IDs should be unique and descriptive
   - Use consistent column names across files

2. **Data Organization**
   - Group related test data in same file
   - Use separate sheets/files for different test modules

3. **Error Handling**
   - Always check if data exists before using
   - Use getOrDefault() for optional fields

4. **Performance**
   - TestDataManager uses singleton pattern
   - Data is cached after first load

## Future Enhancements

- Database integration
- JSON data support
- Dynamic data generation
- Test data versioning
- Data validation

---
**Result**: Dự án đã được successfully refactor để hỗ trợ Data-Driven Testing với CSV và Excel!





mvn clean test "-Dsurefire.suiteXmlFiles=testng-simple.xml"





