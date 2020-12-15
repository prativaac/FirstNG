package conceptTestNG;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddContact {

	WebDriver driver;

	@BeforeMethod
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.techfios.com/billing/?ng=admin/");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test
	public void addcustomerTest() {

		// Generate Random Number
		Random rnd = new Random();
		int random = rnd.nextInt(999);

		// Element library
		By USERNAME = By.id("username");
		By Password_FIELD = By.id("password");
		By LOGIN = By.name("login");
		By DASHBOARD = By.xpath("//span[contains(text(),'Dashboard')]");
		By CUSTOMER = By.xpath("//span[contains(text(),'Customers')]");
		By ADD_CUSTOMER = By.xpath("//a[contains(text(),'Add Customer')]");
		By ADD_CONTACT = By.xpath("//h5[contains(text(),'Add Contact')]");
		By FULL_NAME = By.xpath("//input[@id='account']");

		By Email_FIELD = By.xpath("//input[@id='email']");
		By PHONE_NUMBER = By.xpath("//input[@id='phone']");
		By ADDRESS_FIELD = By.xpath("//input[@id='address']");
		By CITY_FIELD = By.xpath("//input[@id='city']");
		By STATE_FIELD = By.xpath("//input[@id='state']");
		By ZIPCODE_FIELD = By.xpath("//input[@id='zip']");
		By SAVE_BUTTON = By.xpath("//button[@id='submit']");

		// Login Data in a bucket
		String loginId = "demo@techfios.com";
		String password = "abc123";

		// Test Data we want to pass, saved in a bucket variable
		String fullname = " ABC";
		String companyname = "Techfios";
		String emailaddress = "test@gmail.com";
		String phonenumber = "1234563456";

		// Perform Login In
		driver.findElement(USERNAME).sendKeys(loginId);
		driver.findElement(Password_FIELD).sendKeys(password);
		driver.findElement(LOGIN).click();

		// Validate Dashboard Page
		waitforElement(driver, 3, DASHBOARD);
		String dashboardTitle = driver.findElement(DASHBOARD).getText();
		System.out.println("Page found = " + dashboardTitle);
		Assert.assertEquals(dashboardTitle, "Dashboard", "Wrong Page");
		
		// Fill out Add Customer Form
		driver.findElement(CUSTOMER).click();
		driver.findElement(ADD_CUSTOMER).click();
		
		//Validate Add Contact
		waitforElement(driver, 5, ADD_CONTACT);
		String addcontact = driver.findElement(ADD_CONTACT).getText();
		System.out.println("Page Did Match =" +addcontact );
		Assert.assertEquals(addcontact, "Add Contact","Page Did Not Match");
		
		
		driver.findElement(FULL_NAME).sendKeys(fullname + random);
		waitforElement(driver, 5, FULL_NAME);

		// DRopDOWN
		By COMPANY_FIELD = By.xpath("//select[@id='cid']");
		driver.findElement(COMPANY_FIELD);
		Select sel = new Select(driver.findElement(COMPANY_FIELD));
		sel.selectByVisibleText(companyname);

		driver.findElement(Email_FIELD).sendKeys(random + emailaddress);
		waitforElement(driver, 5, Email_FIELD);

		driver.findElement(PHONE_NUMBER).sendKeys(phonenumber+random);
		waitforElement(driver, 5, PHONE_NUMBER);

		driver.findElement(ADDRESS_FIELD).sendKeys("2741 E Belt Line Rd #105,"+random);
		waitforElement(driver, 5, ADDRESS_FIELD);

		driver.findElement(CITY_FIELD).sendKeys("Carrollton"+random);
		waitforElement(driver, 5, CITY_FIELD);

		driver.findElement(STATE_FIELD).sendKeys("TX "+random);
		waitforElement(driver, 5, STATE_FIELD);

		driver.findElement(ZIPCODE_FIELD).sendKeys("75006"+random);
		waitforElement(driver, 5, ZIPCODE_FIELD);

		// DROPDROWN
		WebElement COUNTRY = driver.findElement(By.id("country"));
		Select sel1 = new Select(COUNTRY);
		sel1.selectByVisibleText("Nepal");

		driver.findElement(SAVE_BUTTON).click();

	}

	public void waitforElement(WebDriver driver, int timeINSeconds, By locator) {

		WebDriverWait wait = new WebDriverWait(driver, 5);

		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}
}