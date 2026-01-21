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

public class PNR_DetailsPage {
	
@FindBy (xpath = "//img[@alt='minimize']")	
private	WebElement aiPopup;

@FindBy(xpath = "//span[text()='See description of all symbols']")
private WebElement descriptionLinkText ;

@FindBy(xpath = "//a[@data-cy='coachPos']")
private WebElement coachPositions;

@FindBy(xpath = "//span[@class='close']")
private WebElement closeCoach;

private WebDriver driver;
private WebDriverWait wait;
private JavascriptExecutor js;
	
	public PNR_DetailsPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.js = (JavascriptExecutor)this.driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
public void clickAiPopUp() {
 try {	
		this.wait.until(ExpectedConditions.visibilityOf(this.aiPopup));
		aiPopup.click();
		this.js.executeScript("arguments[0].scrollIntoView({block: 'center'});", this.descriptionLinkText);
     }
 catch(Exception e) {
		 System.err.println("Failed to close Ai poopup");
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

public void clickOnDescription() {
 try {	
	 this.js.executeScript("document.body.style.zoom='50%'");
	  this.wait.until(ExpectedConditions.elementToBeClickable(this.descriptionLinkText));
	  this.descriptionLinkText.click();
	  System.out.println("Clicked to See description of all symbols linkn text");
	  }
 catch(Exception e) {
	 System.out.println("failed to click on description");
	 e.printStackTrace();
  }
 }
  public void clickOnCoach() {
	  
	  this.js.executeScript("arguments[0].scrollIntoView(true);", this.coachPositions);
	  this.wait.until(ExpectedConditions.elementToBeClickable(this.coachPositions));
	  this.coachPositions.click();
	  System.out.println("Clicked to See Coach Positions link text");
  }
  public void clickClose() {
	  this.wait.until(ExpectedConditions.elementToBeClickable(this.coachPositions));
	  this.closeCoach.click();
	  System.out.println("Clicked to close coach position popup");
  }
}
