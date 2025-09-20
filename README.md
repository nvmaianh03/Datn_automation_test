# DATN Website Testing Project

## Chuyển đổi từ Eclipse sang Maven Project

Dự án này đã được chuyển đổi từ Eclipse project sang Maven project để dễ dàng quản lý dependencies và build.

## Cấu trúc dự án

```
├── pom.xml                           # Maven configuration
├── src/
│   ├── main/
│   │   ├── java/                     # Main source code (trống)
│   │   └── resources/                # Main resources
│   └── test/
│       ├── java/tc_website/          # Test source code
│       │   ├── tc_login.java
│       │   ├── tc_dky.java
│       │   ├── tc_cartManagement.java
│       │   ├── tc_changePassword.java
│       │   ├── tc_logout.java
│       │   ├── tc_payment.java
│       │   ├── tc_search.java
│       │   ├── tc_updateProfile.java
│       │   ├── tc_viewProductDetail.java
│       │   └── tc_viewProfile.java
│       └── resources/
│           └── config.properties     # Configuration file
└── target/                           # Maven build output
```

## Yêu cầu hệ thống

- **Java 17** hoặc cao hơn
- **Maven 3.6+**
- **ChromeDriver** (tương thích với phiên bản Chrome hiện tại)

## Cấu hình ChromeDriver

### Cách 1: Sử dụng đường dẫn mặc định
Đặt ChromeDriver tại: `D:\Chromedriver\chromedriver.exe`

### Cách 2: Sử dụng System Property
```bash
mvn test -Dchrome.driver.path="C:/path/to/your/chromedriver.exe"
```

### Cách 3: Cập nhật file config.properties
Chỉnh sửa file `src/test/resources/config.properties`:
```properties
chrome.driver.path=C:/your/path/to/chromedriver.exe
```

## Dependencies chính

- **Selenium WebDriver 3.141.59** - Automation framework
- **TestNG 7.4.0** - Testing framework
- **WebDriverManager 4.4.3** - Automatic driver management (optional)

## Cách chạy tests

### Chạy tất cả tests
```bash
mvn test
```

### Chạy test cụ thể
```bash
mvn test -Dtest=tc_login
```

### Chạy với Chrome driver path tùy chỉnh
```bash
mvn test -Dchrome.driver.path="D:/your/chromedriver.exe"
```

## Báo cáo test

Sau khi chạy tests, báo cáo sẽ được tạo tại:
- `target/surefire-reports/` - Maven Surefire reports
- `test-output/` - TestNG HTML reports

## Lưu ý

1. **Phiên bản Selenium**: Dự án sử dụng Selenium 3.141.59 để duy trì compatibility với code hiện tại
2. **ChromeDriver**: Cần đảm bảo phiên bản ChromeDriver tương thích với Chrome browser
3. **Website testing**: Các test được thiết kế để test website `https://nguyetviet.io.vn`

## Nâng cấp trong tương lai

Để nâng cấp lên Selenium 4.x:
1. Cập nhật version trong `pom.xml`
2. Cập nhật các WebDriver setup method
3. Test lại tất cả test cases

---

**Sinh viên thực hiện**: Nguyễn Vũ Mai Anh - 2021608240  
**Đồ án tốt nghiệp**: Kiểm thử tự động website thương mại điện tử