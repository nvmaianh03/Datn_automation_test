@echo off
echo Chạy TestNG để tạo report với giao diện truyền thống...
cd /d "%~dp0"
mvn exec:java -Dexec.mainClass="org.testng.TestNG" -Dexec.args="testng-simple.xml -d test-output" -Dexec.classpathScope="test"
echo Report TestNG đã được tạo trong thư mục test-output/
pause

