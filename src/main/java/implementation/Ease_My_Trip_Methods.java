package implementation;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.BaseClass;

public class Ease_My_Trip_Methods extends BaseClass {

	public WebDriver driver;

	public Ease_My_Trip_Methods(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void browser_Open(String browserName) {
		if (browserName == "chrome") {
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			ChromeOptions co = new ChromeOptions();
			co.setExperimentalOption("prefs", prefs);
			WebDriverManager.chromedriver().setup();
			co.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(co);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));

		} else if (browserName == "firefox") {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));

		} else if (browserName == "edge") {
			EdgeOptions ed = new EdgeOptions();
			ed.addArguments("--remote-allow-origins=*");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver(ed);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
		}

	}

	public void navigate_To_EaseMyTrip() throws InterruptedException {
		driver.manage().window().maximize();
		driver.get(BaseClass.getValueFromPropertyFile("testData", "url"));
		List<WebElement> x = driver.findElements(By.className("eb_widget_header_close"));

		if (x.size() > 0) {
			x.get(0).click();
		}
	}

	public void one_Way() {
		driver.findElement(By.id("oway")).click();
	}

	public void travel_Details(String Origin, String Destination) throws Exception {
		driver.findElement(By.id("frmcity")).click();
		driver.findElement(By.id("a_FromSector_show")).sendKeys(Origin);
		Thread.sleep(300);
		driver.findElement(By.xpath("(//span[text()='Mumbai(BOM)'])[1]")).click();
		driver.findElement(By.id("ptt")).click();
		driver.findElement(By.id("a_Editbox13_show")).sendKeys(Destination);
		Thread.sleep(300);
		driver.findElement(By.xpath("//ul[@class='ausuggest']//span[text()='New Delhi(DEL)']")).click();
		driver.findElement(By.id("ddate")).click();
		driver.findElement(By.xpath("(//li[@class='active-date']//following::li[@style=\"visibility:show\"])[1]"))
				.click();
		Thread.sleep(200);
	}

	public void search() {
		driver.findElement(By.className("srchBtnSe")).click();
	}

	public void search_Result_Page() throws Exception {
		driver.findElement(By.xpath("//span[text()='Cheapest ']")).isDisplayed();
	}

	public void book_Button() throws InterruptedException {
		driver.findElement(By.xpath("(//div[@id='spnPrice1']/following::button)[1]")).click();
	}

	public void flight_Details() {
		driver.findElement(By.xpath("//span[text()='Flight Detail']")).isDisplayed();
	}

	public void user_Details() throws Exception {
		driver.findElement(By.xpath("(//label[contains(text(),'No, I do not want to insure my trip')])[1]//span"))
				.click();
		driver.findElement(By.id("txtEmailId")).clear();
		driver.findElement(By.id("txtEmailId")).sendKeys(BaseClass.getValueFromPropertyFile("testData", "email"));

		driver.findElement(By.xpath("//span[@id='spnVerifyEmail']")).click();
		driver.findElement(By.id("txtCPhone")).sendKeys(BaseClass.getValueFromPropertyFile("testData", "mobileNo"));
		// Thread.sleep(200);
		Select title = new Select(driver.findElement(By.name("TitleAdult")));
		title.selectByVisibleText(BaseClass.getValueFromPropertyFile("testData", "title"));
		driver.findElement(By.xpath("//input[@placeholder='Enter First Name']"))
				.sendKeys(BaseClass.getValueFromPropertyFile("testData", "firstName"));
		driver.findElement(By.xpath("//input[@placeholder='Enter Last Name']"))
				.sendKeys(BaseClass.getValueFromPropertyFile("testData", "lastName"));
		WebElement paste = driver.findElement(By.xpath("//div[@class='con1']//span[@class='co1 addonstp']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", paste);

		Thread.sleep(1000);
		WebDriverWait t = new WebDriverWait(driver, Duration.ofSeconds(30));
		t.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(
				"//div[@class='con1']//following::span[@class='co1 addonstp'] | ( //span[text()='Continue Booking'])[3] | (//span[@class='co'])[4]"))));
		// this is needed as of now.
		File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String Path = System.getProperty("user.dir");
		Files.copy(f, new File(Path + "\\Screenshots\\Continue_OneWay.png"));
		driver.findElement(By.xpath(
				"//div[@class='con1']//following::span[@class='co1 addonstp'] | ( //span[text()='Continue Booking'])[3] | (//span[@class='co'])[4]"))
				.click();

//		Actions act = new Actions(driver);
//		act.moveToElement(driver.findElement(By.xpath("//div[@class=\"con1\"]//following::span[@class=\"co1 addonstp\"]"))).click().build().perform();

		driver.findElement(
				By.xpath("(//label[@ng-class='NewSeatMapCss_Return(c,(row>0?rs.LstRow[row]:rs.LstRow[row]))'])[1]"))
				.click();
		driver.findElement(
				By.xpath("(//label[@ng-class='NewSeatMapCss_Return(c,(row>0?rs.LstRow[row]:rs.LstRow[row]))'])[1]"))
				.isSelected();
		// assertion to the seat selected
		driver.findElement(By.id("skipPop")).click();

	}

	public void payment_Details() throws Exception {
		driver.findElement(By.xpath("//span[text()='Payment Mode']")).isDisplayed();
		driver.findElement(By.id("CC")).click();
		driver.findElement(By.id("CC")).sendKeys(BaseClass.getValueFromPropertyFile("testData", "accountNo"));
		driver.findElement(By.id("CCN")).sendKeys(BaseClass.getValueFromPropertyFile("testData", "accountHolder"));
		Select month = new Select(driver.findElement(By.name("CCMM")));
		month.selectByVisibleText(BaseClass.getValueFromPropertyFile("testData", "expireMonth"));
		Select year = new Select(driver.findElement(By.name("PAYMENT_expiryYear")));
		year.selectByVisibleText(BaseClass.getValueFromPropertyFile("testData", "expireYear"));
		driver.findElement(By.id("CCCVV")).sendKeys(BaseClass.getValueFromPropertyFile("testData", "cvv"));
		driver.findElement(By.className("mk-pym")).click();
		driver.findElement(By.xpath("//input[@value=\"INR\"]")).click();
		driver.findElement(By.className("pay-btn")).click();
//		driver.findElement(By.xpath("//div[text() ='Booking Status: Payment Failed']")).isDisplayed();
//		driver.findElement(By.xpath("//a[text()='Retry Payment']")).isEnabled();
//		driver.findElement(By.xpath("//a[text()='Retry Payment']")).isDisplayed();
		File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String Path = System.getProperty("user.dir");
		Files.copy(f, new File(Path + "\\Screenshots\\Payment.png"));
		driver.close();
		driver.quit();
	}

	public void round_trip() {
		driver.findElement(By.xpath("//li[@id='rtrip']")).click();
	}

	public void travel_details_round_trip() {
		driver.findElement(By.id("frmcity")).click();
		driver.findElement(By.id("a_FromSector_show")).sendKeys("Mumbai");
		driver.findElement(By.xpath(" //*[contains(text(), 'Mumbai(BOM)')]"));
		driver.findElement(By.id("ptt")).click();
		driver.findElement(By.id("a_Editbox13_show")).sendKeys("Delhi");
		driver.findElement(By.xpath(" //*[contains(text(), 'New Delhi(DEL)')]"));
		driver.findElement(By.id("ddate")).click();
		driver.findElement(By.xpath("(//li[@class='active-date']//following::li[@style='visibility:show'])[1]"))
				.click();
		driver.findElement(By.id("rdate")).click();
		driver.findElement(By.xpath("(//li[@class='active-date']//following::li[@style='visibility:show'])[5]"))
				.click();
	}

	public void traveller_and_class() throws InterruptedException {
		driver.findElement(By.id("trvlr_colm")).click();
		for (int i = 1; i <= 2; i++) {
			driver.findElement(By.xpath("(//div[@class='flex-adltcol']//following::button[@id='add'])[1]")).click();
		}
		for (int i = 1; i <= 2; i++) {
			driver.findElement(By.xpath("(//p[text()='Children']//following::button[@id='add'])[1]")).click();
		}
		driver.findElement(By.id("traveLer")).click();
	}

	public void search_Result_Page_roundtrip() throws Exception {

		driver.findElement(By.xpath("//a[@class=\"stk_btm_chpFlight active\"]")).isDisplayed();
	}

	public void book_button_round_trip() {
		driver.findElement(By.xpath("(//div[@class=\"txt-r6 ng-binding\"]/following::label)[1]")).click();
		driver.findElement(By.xpath(
				"(//div[@class=\"travel-det pdt5\"]//following::div[@class=\"col-md-1 col-sm-4 col-xs-2 new-rad text-center mag\"])[2]"))
				.click();
		driver.findElement(By.id("BtnBookNow")).click();
		driver.findElement(By.xpath("(//a[@class=\"countnbtn\"])[1]")).click();
	}

	public void user_Details_roundtrip() throws Exception {
		driver.findElement(By.xpath("(//label[contains(text(),'No, I do not want to insure my trip')])[1]//span"))
				.click();
		driver.findElement(By.id("txtEmailId")).clear();
		driver.findElement(By.id("txtEmailId")).sendKeys(BaseClass.getValueFromPropertyFile("testData", "email"));
		driver.findElement(By.xpath("//span[@id='spnVerifyEmail']")).click();
		driver.findElement(By.id("txtCPhone")).sendKeys(BaseClass.getValueFromPropertyFile("testData", "mobileNo"));
		Select title = new Select(driver.findElement(By.name("TitleAdult")));
		title.selectByVisibleText(BaseClass.getValueFromPropertyFile("testData", "title"));
		driver.findElement(By.xpath("//input[@placeholder='Enter First Name']"))
				.sendKeys(BaseClass.getValueFromPropertyFile("testData", "firstName"));
		driver.findElement(By.xpath("//input[@placeholder='Enter Last Name']"))
				.sendKeys(BaseClass.getValueFromPropertyFile("testData", "lastName"));
		WebElement paste = driver.findElement(By.xpath("//div[@class='con1']//span[@class='co1 addonstp']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", paste);

		Thread.sleep(1000);

		WebDriverWait t = new WebDriverWait(driver, Duration.ofSeconds(30));
		t.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(
				"//div[@class='con1']//following::span[@class='co1 addonstp'] | ( //span[text()='Continue Booking'])[3] | (//span[@class='co'])[4]"))));
		// this is needed as of now.
		File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		String Path = System.getProperty("user.dir");
		Files.copy(f, new File(Path + "\\Screenshots\\Continue_RoundTrip.png"));
		driver.findElement(By.xpath(
				"//div[@class='con1']//following::span[@class='co1 addonstp'] | ( //span[text()='Continue Booking'])[3] | (//span[@class='co'])[4]"))
				.click();
		driver.findElement(
				By.xpath("(//label[@ng-class='NewSeatMapCss_Return(c,(row>0?rs.LstRow[row]:rs.LstRow[row]))'])[1]"))
				.click();
		driver.findElement(
				By.xpath("(//label[@ng-class='NewSeatMapCss_Return(c,(row>0?rs.LstRow[row]:rs.LstRow[row]))'])[1]"))
				.isSelected();
		// assertion to the seat selected
		driver.findElement(By.id("skipPop")).click();

	}
	
	public void navigate_To_Ecommerce() throws InterruptedException {
		driver.manage().window().maximize();
		driver.get(BaseClass.getValueFromPropertyFile("testData", "ecommerce_url"));
	}
	public void login_Ecommerce() {
		driver.findElement(By.xpath("//span[text()='Login']")).click();
		driver.findElement(By.id("wpl_user_login")).sendKeys(BaseClass.getValueFromPropertyFile("testData", "userName"));
		driver.findElement(By.id("wpl_user_pass")).sendKeys(BaseClass.getValueFromPropertyFile("testData", "passWord"));
		driver.findElement(By.id("login_button")).click();
		driver.findElement(By.className("wpl-close-button")).click();
	}
	
	public void search_the_product() {
		driver.findElement(By.xpath("//span[@class=\"icon-magnifier\"]")).click();
		driver.findElement(By.xpath("(//input[@type=\"search\"])[1]")).sendKeys(BaseClass.getValueFromPropertyFile("testData", "productName"));
		driver.findElement(By.xpath("(//input[@type=\"search\"])[1]")).sendKeys(Keys.ENTER);
	}
	
	public void add_to_cart() {
		driver.findElement(By.xpath("//a[text()='Oakley Frogskins 9013 Sunglasses']")).click();
		driver.findElement(By.xpath("//h2[text()='Oakley Frogskins 9013 Sunglasses']")).isDisplayed();
		driver.findElement(By.xpath("//button[text()='Add to cart']")).click();
		driver.findElement(By.xpath("//h3[text()='Item added to your cart']")).isDisplayed();
		driver.findElement(By.xpath("//a[text()='Go To The Cart']")).click();
		driver.findElement(By.xpath("//a[@class='checkout-button button alt wc-forward wp-element-button']")).click();
	}
	public void user_details_ecommerce() {
		driver.findElement(By.xpath("//h3[contains(text(),'Billing details')]")).isDisplayed();
		driver.findElement(By.id("billing_first_name")).sendKeys(BaseClass.getValueFromPropertyFile("testData", "firstName"));
		driver.findElement(By.id("billing_last_name")).sendKeys(BaseClass.getValueFromPropertyFile("testData", "lastName"));
		driver.findElement(By.id("billing_company")).sendKeys(BaseClass.getValueFromPropertyFile("testData", "company"));
		driver.findElement(By.id("select2-billing_country-container")).click();
		driver.findElement(By.xpath("//input[@class=\"select2-search__field\"]")).sendKeys(BaseClass.getValueFromPropertyFile("testData", "country"));
		driver.findElement(By.xpath("//li[text()='India']")).click();
		driver.findElement(By.id("billing_address_1")).sendKeys(BaseClass.getValueFromPropertyFile("testData", "address1"));
		driver.findElement(By.id("billing_address_2")).sendKeys(BaseClass.getValueFromPropertyFile("testData", "address2"));
		driver.findElement(By.id("billing_city")).sendKeys(BaseClass.getValueFromPropertyFile("testData", "city"));
		driver.findElement(By.id("select2-billing_state-container")).click();
		driver.findElement(By.xpath("//input[@class='select2-search__field']")).sendKeys(BaseClass.getValueFromPropertyFile("testData", "state"));
		driver.findElement(By.xpath("//li[text()='Maharashtra']")).click();
		driver.findElement(By.id("billing_postcode")).sendKeys(BaseClass.getValueFromPropertyFile("testData", "zipcode"));
		driver.findElement(By.id("billing_phone")).sendKeys(BaseClass.getValueFromPropertyFile("testData", "phone"));
		driver.findElement(By.id("billing_email")).sendKeys(BaseClass.getValueFromPropertyFile("testData", "email"));
	}
	
	public void place_order() {
		driver.findElement(By.xpath("//button[text()='Place order']")).isDisplayed();
		driver.close();
		driver.quit();
	}


}
