package testrunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = { "src\\test\\java\\cucumberbdd_UI" }, 
               glue = {"stepdefination_UI" },
              // tags = "@demo",
               //tags = "@search",
               tags = "@raw",
               monochrome = true, 
               plugin = { "pretty", "html:target/HtmlReports.html",
				"json:target/JsonReport.json", "junit:target/JunitReport.xml",
				"html:target/cucumber-reports","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }
		)
public class Test_Runner_UI extends AbstractTestNGCucumberTests {
	
}
