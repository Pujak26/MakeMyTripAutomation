package core;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Allure;

public class ScreenshotUtility {

public static void captureScreenshot(WebDriver driver, String scenarioName) throws IOException {
	
	LocalDateTime currentDateTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss"); 
    String formattedDateTime = currentDateTime.format(formatter);
    
    String safeScenarioName = scenarioName.replaceAll("[^a-zA-Z0-9\\s]", "").replaceAll("\\s+", "_");
 try {
	 TakesScreenshot t = (TakesScreenshot) driver;
	 byte[] screenshot = t.getScreenshotAs(OutputType.BYTES);
	 Allure.addAttachment(safeScenarioName + "_" + formattedDateTime,
                          "image/png",
                           new ByteArrayInputStream(screenshot),".png");
     System.out.println("Screenshot attached to Allure for scenario: " +safeScenarioName);
  }
 catch(Exception e) {
	 System.err.println("Screenshot failed: " +e.getMessage());
 }
	 
 }
}
