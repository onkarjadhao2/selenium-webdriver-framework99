package stepdefination_UI;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.google.common.io.Files;

import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class GoogleSearchSteps {
	WebDriver driver = null;

	@Given("browser is open")
	public void browser_is_open() {
		System.out.println("Step1: Browser is open");
		WebDriverManager.chromedriver().setup();
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(co);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

	}

	@And("user is on google search page")
	public void user_is_on_google_search_page() throws IOException {
		System.out.println("Step2: User is on google search page");
		driver.manage().window().maximize();
		driver.navigate().to("https://www.google.com/");
		File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		Files.copy(f, new File("C:\\Users\\Onkar Jadhao\\Documents\\Screenshots\\search.jpg"));
	}

	@When("user enters a text in search box")
	public void user_enters_a_text_in_search_box() {
		System.out.println("Step3: User enter text in search box");
		driver.findElement(By.name("q")).sendKeys("Selenium BDD");
	}

	@And("hits enter")
	public void hits_enter() throws Exception {
		System.out.println("Step4: Hits enter");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		Thread.sleep(200);
	}

	@Then("user is navigated to search results")
	public void user_is_navigated_to_search_results() {
		System.out.println("Inside step: User is navigated to search results");
		driver.getPageSource().contains("Selenium with Cucumber (BDD Framework) - Guru99");
		driver.close();
		driver.quit();
	}

}
