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

@FindBy (xpath = "//span[@data-cy='checkPnrStatus']")
private WebElement checkPNRStatusBtn;

@FindBy (xpath = "//input[@id='pnr']")
private	WebElement pnrNumber;

@FindBy (xpath = "(//div[@class='tp-dt-header-icon'])[2]")
private WebElement aiPopup ;

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
public void clickNVerifyerifyChkPNRStatusBtn() {
 try {	
	 this.wait.until(ExpectedConditions.elementToBeClickable(this.checkPNRStatusBtn));
	 this.checkPNRStatusBtn.click();
	 this.wait.until(ExpectedConditions.attributeContains(this.checkPNRStatusBtn,"class","active"));
	 Assert.assertTrue(this.checkPNRStatusBtn.getAttribute("class").contains("active"), "Failed to cliked on Check PNR Status button");
	  }
 catch(Exception e) {
	 System.out.println("Failed to clicked on Check PNR Status button");
	 e.printStackTrace();
 }
}

public void enterPnrNumber(String pnrNum){
 try {
	 this.wait.until(ExpectedConditions.elementToBeClickable(this.pnrNumber));
	 this.pnrNumber.click();
	 System.out.println("Sending PNR number to input field");
     this.pnrNumber.sendKeys(pnrNum);
     Assert.assertEquals(this.pnrNumber.getAttribute("value"),pnrNum,"Entered PNR number is mismatched");
   }
 catch(Exception e) {
	System.out.println("Entered PNR number is mismatched");
	e.printStackTrace();
 }
}

public void clickAndVerifyCheckStatusButton() {
 try {
	 this.wait.until(ExpectedConditions.elementToBeClickable(this.checkStatus));
	 this.checkStatus.click();
	}
 catch(Exception e) {
	 System.out.println("Failed to click CHECK STATUS button");
	 e.printStackTrace();
  }
 }
}
