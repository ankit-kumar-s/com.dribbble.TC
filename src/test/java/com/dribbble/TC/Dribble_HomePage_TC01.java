package com.dribbble.TC;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Dribble_HomePage_TC01 {

	public static WebDriver driver;
	public static List<WebElement> alllinklist;
	public static Set<String> windID;

	@BeforeClass
	public void setup() {

		WebDriverManager.chromedriver().setup();
		;
		driver = new ChromeDriver();
		driver.get("https://dribbble.com/signup/new");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@Test
	public void validateAllTheLinkonHomePage() {

		String currenttitle = driver.getTitle();
		String parentWindowID = driver.getWindowHandle();
		alllinklist = driver.findElements(By.xpath("//p[starts-with(@class,'font-small text-align-center')]/a"));
		for (WebElement singlelink : alllinklist) {
              singlelink.click();
			windID = driver.getWindowHandles();
		}
        for(String singlWindow : windID) {
			  String titleOfPage =driver.switchTo().window(singlWindow).getTitle(); 
			  if(!titleOfPage.equalsIgnoreCase(currenttitle)) {
				  driver.close();
			  }
			  driver.switchTo().window(parentWindowID);
		  }
		 

	}
	
	@Test(priority=1)
	public void fillingRegistrationForm() {
		
		WebElement continuewithEmail = driver.findElement(By.xpath(" //button[starts-with(@class,'btn2 btn2--large btn2')]"));
		continuewithEmail.click();
		
		WebElement Name_txtfield = driver.findElement(By.id("user_name"));
		Name_txtfield.sendKeys("Ashok");
		
		WebElement username_txtfield= driver.findElement(By.id("user_login"));
		username_txtfield.sendKeys("ASK_091998");
		
		WebElement email_txtfield = driver.findElement(By.id("user_email"));
		email_txtfield.sendKeys("ashokk@gmail.com");
		
		WebElement password_txtfield = driver.findElement(By.id("user_password"));
		password_txtfield.sendKeys("123asdfg56");
		
		WebElement checkbox= driver.findElement(By.id("user_agree_to_terms"));
		checkbox.click();
		
		WebElement creatAccountBtn= driver.findElement(By.xpath("//input[@type='submit']"));
		creatAccountBtn.click();
		
		
	}
	
	
	

	@AfterClass public void teardown() 
	{ driver.quit(); }
	 
	 

}
