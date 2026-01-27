package pom;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Live_StatusPage {
	
@FindBy (xpath = "//img[@alt='minimize']")	
private	WebElement aiPopup;	

private WebDriver driver;
private WebDriverWait wait;
private JavascriptExecutor js;

//@FindBy (xpath = "//h2[text()='Disclaimer']")
//WebElement Disclaimer;

public Live_StatusPage(WebDriver driver) {
	this.driver = driver;
	this.js = (JavascriptExecutor)this.driver;
	this.wait = new WebDriverWait(this.driver,Duration.ofSeconds(10));
	PageFactory.initElements(driver, this);
}
	
public void verifyTrainsPageTitle(String title) {
 try {	
	System.out.println("Title : "+this.driver.getTitle());
Assert.assertEquals(this.driver.getTitle(),title,"Trains home page title does not match expected value");
}
 catch(Exception e) {
	 System.err.println("Failed to verify page title");
	 e.printStackTrace();
  }
 }
public void verifyTrainPageUrl(String url) {
try {	
	System.out.println("URL : "+this.driver.getCurrentUrl());
	Assert.assertEquals(this.driver.getCurrentUrl(), url,"Page URL does not match expected value");
 } 
catch(Exception e) {
	System.err.println("Failed to verify page URL");
	e.printStackTrace();
 }
} 
public void clickAiPopUp() {
 try {
		//this.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@alt='minimize']")));
		this.wait.until(ExpectedConditions.elementToBeClickable(this.aiPopup));
	    this.aiPopup.click();
     }
 catch(Exception e) {
	 System.out.println("fail to click on ai popup");
	 e.printStackTrace();
 }
	
}
 
}

