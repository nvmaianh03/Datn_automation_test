package tc_website.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class for reading test data from Excel files
 * Supports both .xls and .xlsx formats
 */
public class ExcelDataProvider {
    
    private Workbook workbook;
    private Sheet sheet;
    
    /**
     * Constructor to initialize Excel file and sheet
     * @param filePath Path to Excel file
     * @param sheetName Name of the sheet to read
     */
    public ExcelDataProvider(String filePath, String sheetName) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            // Determine file type and create appropriate workbook
            if (filePath.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(fis);
            } else if (filePath.endsWith(".xls")) {
                workbook = new HSSFWorkbook(fis);
            } else {
                throw new IllegalArgumentException("Unsupported file format. Use .xls or .xlsx");
            }
            
            sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet '" + sheetName + "' not found in " + filePath);
            }
            
        } catch (IOException e) {
            throw new RuntimeException("Error reading Excel file: " + filePath, e);
        }
    }
    
    /**
     * Get all test data as a list of maps (each row as a map with column headers as keys)
     * @return List of test data rows
     */
    public List<Map<String, String>> getAllData() {
        List<Map<String, String>> data = new ArrayList<>();
        
        if (sheet.getPhysicalNumberOfRows() <= 1) {
            return data; // No data rows
        }
        
        // Get header row
        Row headerRow = sheet.getRow(0);
        List<String> headers = new ArrayList<>();
        
        for (Cell cell : headerRow) {
            headers.add(getCellValueAsString(cell));
        }
        
        // Read data rows
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row dataRow = sheet.getRow(i);
            if (dataRow == null) continue;
            
            Map<String, String> rowData = new HashMap<>();
            
            for (int j = 0; j < headers.size(); j++) {
                Cell cell = dataRow.getCell(j);
                String value = getCellValueAsString(cell);
                rowData.put(headers.get(j), value);
            }
            
            data.add(rowData);
        }
        
        return data;
    }
    
    /**
     * Get test data for a specific test case
     * @param testCaseId Test case identifier
     * @return Test data map or null if not found
     */
    public Map<String, String> getDataByTestCase(String testCaseId) {
        List<Map<String, String>> allData = getAllData();
        
        for (Map<String, String> row : allData) {
            if (testCaseId.equals(row.get("TestCase")) || testCaseId.equals(row.get("testcase"))) {
                return row;
            }
        }
        
        return null;
    }
    
    /**
     * Convert Excel cell value to string
     * @param cell Excel cell
     * @return String representation of cell value
     */
    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    // Format numeric value to avoid scientific notation
                    double numValue = cell.getNumericCellValue();
                    if (numValue == (long) numValue) {
                        return String.valueOf((long) numValue);
                    } else {
                        return String.valueOf(numValue);
                    }
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
    
    /**
     * Close the workbook to free resources
     */
    public void close() {
        if (workbook != null) {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}