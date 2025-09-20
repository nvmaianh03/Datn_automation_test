package tc_website.utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class for reading test data from CSV files
 */
public class CsvDataProvider {
    
    private List<String[]> csvData;
    private List<String> headers;
    
    /**
     * Constructor to initialize CSV file
     * @param filePath Path to CSV file
     */
    public CsvDataProvider(String filePath) {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            csvData = reader.readAll();
            
            if (csvData.isEmpty()) {
                throw new IllegalArgumentException("CSV file is empty: " + filePath);
            }
            
            // First row as headers
            headers = List.of(csvData.get(0));
            
        } catch (IOException | CsvException e) {
            throw new RuntimeException("Error reading CSV file: " + filePath, e);
        }
    }
    
    /**
     * Get all test data as a list of maps
     * @return List of test data rows
     */
    public List<Map<String, String>> getAllData() {
        List<Map<String, String>> data = new ArrayList<>();
        
        // Skip header row (index 0)
        for (int i = 1; i < csvData.size(); i++) {
            String[] row = csvData.get(i);
            Map<String, String> rowData = new HashMap<>();
            
            for (int j = 0; j < Math.min(headers.size(), row.length); j++) {
                rowData.put(headers.get(j), row[j]);
            }
            
            data.add(rowData);
        }
        
        return data;
    }
    
    /**
     * Get test data for a specific test case
     * @param testCaseId Test case identifier
     * @return Test data map or empty map if not found
     */
    public Map<String, String> getDataByTestCase(String testCaseId) {
        List<Map<String, String>> allData = getAllData();
        
        for (Map<String, String> row : allData) {
            // Check common identifier columns
            if (testCaseId.equals(row.get("TestCase")) || 
                testCaseId.equals(row.get("testcase")) ||
                testCaseId.equals(row.get("AccountID")) ||
                testCaseId.equals(row.get("accountid")) ||
                testCaseId.equals(row.get("ID")) ||
                testCaseId.equals(row.get("id"))) {
                return row;
            }
            
            // If no specific identifier column found, check first column
            if (!headers.isEmpty() && testCaseId.equals(row.get(headers.get(0)))) {
                return row;
            }
        }
        
        return new HashMap<>(); // Return empty map instead of null
    }
    
    /**
     * Get headers from CSV file
     * @return List of column headers
     */
    public List<String> getHeaders() {
        return new ArrayList<>(headers);
    }
}