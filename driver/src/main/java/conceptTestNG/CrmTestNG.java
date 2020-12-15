package conceptTestNG;

import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CrmTestNG {
	WebDriver driver;

	@BeforeMethod
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.get("https://www.techfios.com/billing/?ng=admin/");         //going to the website
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

}
	@Test(priority = 2)    //giving priority to the test case.
	public void loginTest1() throws InterruptedException {
		// test case 1
		driver.findElement(By.id("username")).sendKeys("demo@techfios.com");
		driver.findElement(By.id("password")).sendKeys("abc123");
		
		//WebDriverWait wait = new WebDriverWait(driver, 5);      //always is in seconds(explicit wait)
		
		//wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("login"))));
		
		driver.findElement(By.name("login")).click();
		
		WebElement DASHBORD_FIELD=driver.findElement(By.xpath("//h2[contains(text(),' Dashboard ')]"));
		
		System.out.println(DASHBORD_FIELD.getText());
		
		Assert.assertEquals( DASHBORD_FIELD.getText(), "Dashboard", "Dashboard title doesnot match");//validation
}
	@Test(priority = 1)
	public void loginTest3() throws InterruptedException { // test case 2
		driver.findElement(By.id("username")).sendKeys("demo@techfios.com");
		driver.findElement(By.id("password")).sendKeys("abc123");
		driver.findElement(By.name("login")).click();
		Thread.sleep(2000);
		
	}
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
}