package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;

	public static String getValueFromPropertyFile(String fileName, String key) {
		String value = "";
		try {
			String path = System.getProperty("user.dir") + "\\src\\test\\resources\\Properties\\" + fileName
					+ ".properties";
			FileInputStream fin = new FileInputStream(path);
			Properties properties = new Properties();
			properties.load(fin);
			value = properties.getProperty(key);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return value;
	}

	public static void setValueFromPropertyFile(String fileName, String key, String value) {
		try {
			String path = System.getProperty("user.dir") + "\\src\\test\\resources\\Properties\\" + fileName
					+ ".properties";
			OutputStream fin = new FileOutputStream(path);
			Properties properties = new Properties();
			properties.setProperty(key, value);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public static void browserLaunchAndNavigateToWebsite(String browserName) {
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
			driver.manage().window().maximize();
			driver.get("https://reqres.in/");
			WebElement reqres = driver.findElement(By.xpath("//div[@class='request']"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", reqres);

		} else if (browserName == "firefox") {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
			driver.manage().window().maximize();
			driver.get("https://reqres.in/");
			WebElement reqres = driver.findElement(By.xpath("//div[@class='request']"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", reqres);

		} else if (browserName == "edge") {
			EdgeOptions ed = new EdgeOptions();
			ed.addArguments("--remote-allow-origins=*");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver(ed);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
			driver.manage().window().maximize();
			driver.get("https://reqres.in/");
			WebElement reqres = driver.findElement(By.xpath("//div[@class='request']"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", reqres);
		}
	}

	public static void browserClosed() {
		// driver.close();
		driver.quit();
	}
}
