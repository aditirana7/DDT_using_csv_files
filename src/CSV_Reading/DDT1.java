package CSV_Reading;

import java.io.FileReader;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import au.com.bytecode.opencsv.CSVReader;

public class DDT1 {
	WebDriver driver;
	String CSV_path = "./CSV_FILE/CSV_FILES_FOR_PROJECT.csv";

	@BeforeTest
	public void Setup() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./Chrome_driver_jars/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.hollandandbarrett.com/auth/signup");
		driver.manage().window().maximize();
	}

	@Test
	public void locatorsTestingBlog() throws Exception {
		CSVReader reader = new CSVReader(new FileReader(CSV_path));
		String[] csvCell;
		while ((csvCell = reader.readNext()) != null) {
			String FName = csvCell[0];
			String LName = csvCell[1];
			String Email = csvCell[2];
			String Mnumb = csvCell[3];
			String CName = csvCell[4];

			driver.findElement(By.name("FirstName")).sendKeys(FName);
			driver.findElement(By.name("LastName")).sendKeys(LName);
			driver.findElement(By.name("EmailID")).sendKeys(Email);
			driver.findElement(By.name("MobNo")).sendKeys(Mnumb);
			driver.findElement(By.name("Company")).sendKeys(CName);

			Thread.sleep(3000);
			driver.findElement(By.xpath("//input[@value='Submit']")).click();
			Thread.sleep(3000);
			driver.switchTo().alert().accept();
		}
		reader.close();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
