package implementation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class Login_Method {
	WebDriver driver;

	public Login_Method(WebDriver driver) {
		this.driver = driver;
	}

	public void enterUsername(String username) {
		driver.findElement(By.id("input-email")).sendKeys(username);
	}

	public void enterPassword(String password) {
		driver.findElement(By.id("input-password")).sendKeys(password);
	}

	public void clickLogin() {
		driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();
	}

}
