package stepdefination_UI;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Login_steps {
	WebDriver driver = null;
	implementation.Login_Method login;

	@Given("browser is open for login")
	public void browser_is_open_for_login() {
		System.out.println("Step1: Browser is open for login");
		WebDriverManager.chromedriver().setup();
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(co);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
	}

	@Given("User is on login page")
	public void user_is_on_login_page() {
		System.out.println("Step2: User is on the login page");
		driver.manage().window().maximize();
		driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
	}

	@When("^User enters (.*) and (.*)$")
	public void user_enters_username_and_password(String username, String password) throws Exception {
		System.out.println("Step3: User enter username and password");
		login = new implementation.Login_Method(driver);
		login.enterUsername(username);
		login.enterPassword(password);
	}

	@When("user clicks in login button")
	public void user_clicks_in_login_button() throws Exception {
		System.out.println("Step4: User click the login button");
		login.clickLogin();
	}

	@Then("user is navigated to home page")
	public void user_is_navigated_to_home_page() {
		System.out.println("Step5: User is navigated to home page");
		WebElement Postive = driver.findElement(By.xpath("//a[text() = 'View your order history']"));
		Postive.isDisplayed();
		driver.close();
		driver.quit();
	}

	@Given("browser is open for invalid user")
	public void browser_is_open_for_unvalid_user() {
		System.out.println("Step1: Browser is open for login");
		WebDriverManager.chromedriver().setup();
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(co);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

	}

	@Given("User is on login page for invalid user")
	public void user_is_on_login_page_for_unvalid_user() {
		System.out.println("Step2: User is on the login page");
		driver.manage().window().maximize();
		driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
	}

	@When("^User filled (.*) and (.*) for invalid user$")
	public void user_enters_username_and_password_for_unvalid_user(String username, String password) throws Exception {
		System.out.println("Step3: User enter username and password");
		login = new implementation.Login_Method(driver);
		login.enterUsername(username);
		login.enterPassword(password);
	}

	@When("user clicks in login button for invalid user")
	public void user_clicks_in_login_button_for_unvalid_user() throws Exception {
		System.out.println("Step4: User click the login button");
		login.clickLogin();
	}

	@Then("user get error")
	public void user_get_error() {
		System.out.println("Step5: User get error");
		WebElement Negative = driver.findElement(By.xpath("//div[@id='account-login']/ul/following::div/i"));
		Negative.isDisplayed();
		driver.close();
		driver.quit();
	}

	Scenario scenario;

	@After
	public void failedScreenshot(Scenario scenario) {
		try {
			if (scenario.isFailed()) {
				// Take screenshot
				byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

				// Embed screenshot in HTML report
				scenario.attach(screenshot, "image/png", "failedScreenshot");
				driver.close();

			}
		} catch (

		WebDriverException e) {
			e.printStackTrace();
		}
	}

}
