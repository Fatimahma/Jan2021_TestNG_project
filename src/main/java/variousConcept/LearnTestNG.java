package variousConcept;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LearnTestNG {

	WebDriver driver;

	@BeforeMethod
	public void init() {
		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.techfios.com/billing");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test(priority = 1)
	public void loginTest() {
		Assert.assertEquals(driver.getTitle(), "Login - iBilling", "Wrong page!!");

		WebElement USER_NAME_FIELD_ELEMENT = driver.findElement(By.xpath("//input[@id='username']"));
		WebElement PASSWORD_FIELD_ELEMENT = driver.findElement(By.xpath("//input[@id='password']"));
		WebElement SIGNIN_BUTTON_ELEMENT = driver.findElement(By.xpath("//button[@name='login']"));

		String loginId = "demo@techfios.com";
		String password = "abc123";

		USER_NAME_FIELD_ELEMENT.sendKeys(loginId);
		PASSWORD_FIELD_ELEMENT.sendKeys(password);
		SIGNIN_BUTTON_ELEMENT.click();

		WebElement DASHBOARD_TITLE_ELEMENT = driver.findElement(By.xpath("//h2[contains(text(), ' Dashboard ')]"));
		Assert.assertEquals(DASHBOARD_TITLE_ELEMENT.getText(), "Dashboard", "wrong page again");
	}

	@Test(priority = 2)
	public void addCustomer() {
		Assert.assertEquals(driver.getTitle(), "Login - iBilling", "Wrong page!!");

		WebElement USER_NAME_FIELD_ELEMENT = driver.findElement(By.xpath("//input[@id='username']"));
		WebElement PASSWORD_FIELD_ELEMENT = driver.findElement(By.xpath("//input[@id='password']"));
		WebElement SIGNIN_BUTTON_ELEMENT = driver.findElement(By.xpath("//button[@name='login']"));

		String loginId = "demo@techfios.com";
		String password = "abc123";
		String fullName = "Test Janurary";
		String companyName = "google";
		String email = "techfios@gmail.com";
		String phone = "21433780";

		USER_NAME_FIELD_ELEMENT.sendKeys(loginId);
		PASSWORD_FIELD_ELEMENT.sendKeys(password);
		SIGNIN_BUTTON_ELEMENT.click();

		WebElement DASHBOARD_TITLE_ELEMENT = driver.findElement(By.xpath("//h2[contains(text(), ' Dashboard ')]"));
		Assert.assertEquals(DASHBOARD_TITLE_ELEMENT.getText(), "Dashboard", "wrong page again");

		WebElement CUSTOMER_TITLE_ELEMENT = driver.findElement(By.xpath("//span[contains(text(),'Customers')]"));
		WebElement ADD_CUSTOMER_ELEMENT = driver.findElement(By.xpath("//a[contains(text(),'Add Customer')]"));
		CUSTOMER_TITLE_ELEMENT.click();
		ADD_CUSTOMER_ELEMENT.click();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@id='account']"))));

		// WebElement NAME_INPUT_ELEMENT =
		// driver.findElement(By.xpath("//input[@id='account']"));
		// WebElement COMPANY_DROP_DOWN =
		// driver.findElement(By.xpath("//select[@id=\"cid\"]"));
		WebElement EMAIL_INPUT_ELEMENT = driver.findElement(By.xpath("//input[@id='email']"));
		WebElement PHONE_INPUT_ELEMENT = driver.findElement(By.xpath("//input[@id='phone']"));

		driver.findElement(By.xpath("//input[@id='account']")).sendKeys("customer123");

		WebElement COMPANY_DROPDOWN_LOCATOR = driver.findElement(By.xpath("//select[@id=\"cid\"]"));

		wait.until(ExpectedConditions.visibilityOfAllElements(COMPANY_DROPDOWN_LOCATOR));
		Select sel = new Select(COMPANY_DROPDOWN_LOCATOR);
		sel.selectByVisibleText("Google");

		

		EMAIL_INPUT_ELEMENT.sendKeys(randomEmail(email));
		PHONE_INPUT_ELEMENT.sendKeys(phone);

	}

	public String randomEmail(String email) {
		Random rmd = new Random();
		int num = rmd.nextInt(999);
		return num + email;
	}

}
