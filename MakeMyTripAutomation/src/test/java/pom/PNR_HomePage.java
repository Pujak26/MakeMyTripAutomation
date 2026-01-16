package pom;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class PNR_HomePage {

@FindBy (xpath = "//span[@class='commonModal__close']")	
private	WebElement close;

@FindBy (xpath = "(//span[text()='Trains'])[1]")	
private	WebElement trains;

@FindBy (xpath = "//span[@data-cy='bookTrainTickets']")
private	WebElement bookTrainTickets;

@FindBy (xpath = "//span[text()='Live Train Status']")
private WebElement liveTrainStatus;

@FindBy (xpath = "//span[@class='appendRight10']")
private WebElement checkPNRStatus;

@FindBy (xpath = "(//div[@class='tp-dt-header-icon'])[2]")
private WebElement aiPopup ;

@FindBy (xpath = "//input[@id='pnr']")
private	WebElement pnrNumber;

@FindBy (xpath = "//a[text()='CHECK STATUS']")
private	WebElement checkStatus;
	
private WebDriver driver;
private WebDriverWait wait;
	
public PNR_HomePage(WebDriver driver) {	
	this.driver = driver;
 PageFactory.initElements(driver, this);
 this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
}

public void verifyTrainsPageTitle(String title) {
 try {
	System.out.println("Title : "+this.driver.getTitle());
	Assert.assertEquals(this.driver.getTitle(),title,"Trains home page title does not match expected value");
}
 catch(Exception e) {
		System.err.println("Fail to verify trains page title");
		e.printStackTrace();
	}
  }
public void verifyTrainPageUrl(String url) {
 try {
	System.out.println("URL : "+this.driver.getCurrentUrl());
	Assert.assertEquals(this.driver.getCurrentUrl(), url,"Page URL does not match expected value");
 } 
 catch(Exception e) {
	System.err.println("Fail to verify trains page title");
	e.printStackTrace();
 }
}
public void verifyTrainButtonTypes() {
 try {	
	this.wait.until(ExpectedConditions.elementToBeClickable(this.checkPNRStatus));
	this.checkPNRStatus.click();
	this.wait.until(ExpectedConditions.attributeContains(this.checkPNRStatus,"class","active"));
	 //Assert.assertTrue(this.checkPNRStatus.isSelected(), "Failed to select Check PNR Status button");
	Assert.assertTrue(this.checkPNRStatus.getAttribute("class").contains("active"), "Failed to select Check PNR Status button");
		}
 catch(Exception e) {
	 System.out.println("Failed to select Check PNR Status button");
	 e.printStackTrace();
 }
}

public void enterPnrNumber(String pnrNum){
 try {
	 this.wait.until(ExpectedConditions.elementToBeClickable(this.pnrNumber));
	 this.pnrNumber.click();
	 System.out.println("Sending PNR number to input field");
     this.pnrNumber.sendKeys(pnrNum);
    Assert.assertTrue(this.pnrNumber.getAttribute("value").contains(pnrNum),"Fail to verify PNR number input field");
 }
 catch(Exception e) {
	System.out.println("Failed to enter PNR number");
	e.printStackTrace();
 }
}

public void clickAndVerifyCheckStatusButton() {
 try {	
	this.checkStatus.click();
	Assert.assertTrue(this.checkStatus.isSelected(),"Failed to click Check Status button");
}
 catch(Exception e) {
	 System.out.println("Failed to click Check Status button");
	 e.printStackTrace();
  }
 }
}
