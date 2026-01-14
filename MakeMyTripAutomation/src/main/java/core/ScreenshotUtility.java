package core;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenshotUtility {

public static void captureScreenshot(WebDriver driver, String scenarioName) throws IOException {
	
	LocalDateTime currentDateTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss"); 
    String formattedDateTime = currentDateTime.format(formatter);
    
    String safeScenarioName = scenarioName.replaceAll("[^a-zA-Z0-9\\s]", "").replaceAll("\\s+", "_");
 
	TakesScreenshot t = (TakesScreenshot) driver;
	File source = t.getScreenshotAs(OutputType.FILE);
    File dest = new File("test-output/Screenshot/" + safeScenarioName + "_"+ formattedDateTime +".jpeg") ;
    FileHandler.copy(source,dest);
    System.out.println("Screenshot saved at: " +dest);
  }
}
