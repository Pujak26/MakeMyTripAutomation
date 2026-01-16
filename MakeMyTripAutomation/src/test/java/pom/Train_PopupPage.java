package pom;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Train_PopupPage {

@FindBy (xpath = "//img[@alt='minimize']")	
private	WebElement close;	

@FindBy (xpath = "//span[@data-cy='closeModal']")
private	WebElement aiPopup;

@FindBy (xpath = "//a[contains(@href,'/railways/')]")
private	WebElement trains;


private JavascriptExecutor js;
private WebDriver driver;
private WebDriverWait wait;

public Train_PopupPage(WebDriver driver) {
	
	this.driver = driver;
	PageFactory.initElements(driver, this);
	this.js = (JavascriptExecutor) driver;
	this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
}

public void clickAiPopUp() {
 try {	
	this.wait.until(ExpectedConditions.visibilityOf(this.aiPopup));
	aiPopup.click();
 }
 catch(Exception e) {
	 System.err.println("Failed to close Ai poopup");
	 e.printStackTrace();
 }
}	

public void clickClosePopup() {
 try {	
	System.out.println("Zooming out current page");
	this.js.executeScript("document.body.style.zoom='60%'");
	this.wait.until(ExpectedConditions.visibilityOf(this.close));
	this.close.click();
 }
 catch(Exception e) {
	 System.err.println("Failed to close login popup");
	 e.printStackTrace();
 }
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
public void clickTrainsTab() throws InterruptedException {
	try {
		this.wait.until(ExpectedConditions.elementToBeClickable(this.trains));
		Thread.sleep(3000);
		this.trains.click();
		this.wait.until(ExpectedConditions.attributeContains(this.trains, "class", "active"));
		System.out.println("Trains tab has selected");
		Assert.assertTrue(this.trains.getAttribute("class").contains("active"),"Trains tab is not selected");
		}
	catch(Exception e) {
		System.err.println("Failed to cliked on Trains tab");
		e.printStackTrace();		
	  }
  }
}
