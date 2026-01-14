package driverSetup;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import configuration.ConfigReader;
import configuration.EnvironmentConfig;

public class DriverFactory {
	
 public static WebDriver driver; 
 public static EnvironmentConfig envConfig;
     
 
	 public void setup() {                          
		 
	envConfig = ConfigReader.getActiveEnvironment();
	
		if( envConfig.getBrowserName().equals("chrome")) {
			driver = new ChromeDriver();
		}
		if( envConfig.getBrowserName().equals("firefox")) {
			driver = new FirefoxDriver();
		}
		if( envConfig.getBrowserName().equals("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		
		driver.get(envConfig.getBaseUrl());
		
}
	 
	 	 public void tearDown() {
		 
		 if(driver != null)
		 {
			 driver.quit();
		 }
	 }
}
