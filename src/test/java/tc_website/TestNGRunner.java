package tc_website;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.List;

/**
 * TestNG Runner to generate reports in test-output folder
 */
public class TestNGRunner {
    
    public static void main(String[] args) {
        // Create TestNG instance
        TestNG testNG = new TestNG();
        
        // Set output directory for TestNG reports
        testNG.setOutputDirectory("test-output");
        
        // Create XML Suite programmatically
        XmlSuite suite = new XmlSuite();
        suite.setName("Login Test Suite");
        suite.setParallel(XmlSuite.ParallelMode.NONE);
        
        // Create XML Test
        XmlTest test = new XmlTest(suite);
        test.setName("Login Tests");
        
        // Add test class
        List<XmlClass> classes = new ArrayList<>();
        classes.add(new XmlClass("tc_website.LoginTestDataDriven"));
        test.setXmlClasses(classes);
        
        // Add suite to TestNG
        List<XmlSuite> suites = new ArrayList<>();
        suites.add(suite);
        testNG.setXmlSuites(suites);
        
        // Run tests
        System.out.println("Running TestNG tests with reports in test-output folder...");
        testNG.run();
        System.out.println("Tests completed! Check test-output folder for reports.");
    }
}