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

public class Book_TravellerDetailsPage {
	
@FindBy (xpath ="//span[text()='Add Traveller']")
private	WebElement addTraveller;

@FindBy (xpath ="//span[text()='Add Traveller Information']")
private	WebElement addInfoPopup;

@FindBy (xpath ="//input[@id='name']")
private	WebElement name ;

@FindBy (xpath ="//input[@id='age']")
private	WebElement age;

@FindBy (xpath ="//span[text()='Select']")
private	WebElement gender;

@FindBy (xpath ="//span[text()='No Berth Preference']")
private	WebElement birth;

@FindBy (xpath ="//button[text()='Add']")
private	WebElement add;

@FindBy (xpath ="//input[@id='contactEmail']")
private	WebElement emailId;

@FindBy (xpath ="//input[@id='mobileNumber']")
private	WebElement mobileNo;

@FindBy (xpath ="//p[@class='checkboxWithLblWpr__label']")
private	WebElement savechkbx;


private WebDriver driver;
private WebDriverWait wait;
JavascriptExecutor js;	
	
public Book_TravellerDetailsPage(WebDriver driver)
{
	this.driver = driver;
	PageFactory.initElements(driver, this);
	this.js = (JavascriptExecutor)driver;
	this.wait = new WebDriverWait(driver,Duration.ofSeconds(30));
}

public void clickToAddTravellerBtn(){
 try {	
	 this.wait.until(ExpectedConditions.visibilityOf(this.addTraveller));
	 this.js.executeScript("arguments[0].scrollIntoView();", this.addTraveller);
	 this.addTraveller.click();
	 this.js.executeScript("document.body.style.zoom='70%'");
	}
 catch(Exception e) {
	 System.err.println("Failed to click on add traveller button");
	 this.js.executeScript("arguments[0].click();", this.addTraveller);
 }
}

public void enterTravellerName(String travellerName) {
 try {	
	this.wait.until(ExpectedConditions.elementToBeClickable(this.name));
	this.name.clear();
	this.name.sendKeys(travellerName);
	Assert.assertEquals(this.name.getAttribute("value"),travellerName,"Traveller name was not enter correctly");
}
 catch(Exception e) {
   System.err.println("Failed to click on name field");	 
   e.printStackTrace();
 }
}
public void enterTravellerAge(String completeAge) {
 try {
	
	this.wait.until(ExpectedConditions.elementToBeClickable(this.age));
	this.age.clear();
	this.age.sendKeys(completeAge);
	Assert.assertEquals(this.age.getAttribute("value"), completeAge,"Entered age is not enter correctly");
}
 catch(Exception e) {
	 System.err.println("Failed to click on age field");
	 e.printStackTrace();
 }
}
public void selectGender(String gen) {
 try {	
	 this.wait.until(ExpectedConditions.elementToBeClickable(this.gender));
	this.gender.click();
	 WebElement genOptions = driver.findElement(By.xpath("//span[text()='"+gen+"']"));
	 genOptions.click();
	 Assert.assertEquals(genOptions.getText(), gen,"Gender fail to select");
 }
 catch(Exception e) {
	 System.err.println("Failed to select gender");
	 e.printStackTrace();
 }
}

public void selectBirthPreference(String seatPrefered) {
 try {	
	  this.wait.until(ExpectedConditions.elementToBeClickable(this.birth));
	  this.birth.click();
      WebElement birtOptions = driver.findElement(By.xpath("//span[text()='"+seatPrefered+"']"));
      birtOptions.click();  
      Assert.assertEquals(birtOptions.getText(), seatPrefered ,"Birth fail to select");
    }
 catch(Exception e) {
	 System.err.println("Failed to select birth");
	 e.printStackTrace();
 }
}
public void clickAddButton() {
 try {
 	  this.wait.until(ExpectedConditions.elementToBeClickable(this.add));
      this.add.click();
 }
 catch(Exception e) {
	System.out.println("Falied to click on add button");
	e.printStackTrace();
 }
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
public void enterEmailAndMobile(String Id,String MobNumber) { 
 try {	
	    this.js.executeScript("arguments[0].scrollIntoView({block: 'center'});", this.emailId);
	    System.out.println("Email id entering into input field");
	    this.wait.until(ExpectedConditions.elementToBeClickable(this.emailId));
	    this.emailId.clear();
	    this.emailId.sendKeys(Id);
	    System.out.println("Email id entered into input field");
	    Assert.assertEquals(this.emailId.getAttribute("value"), Id, "Email value mismatch in input field");

	    System.out.println("Mobile number entering into input field");
	    this.wait.until(ExpectedConditions.elementToBeClickable(this.mobileNo));
	    this.mobileNo.clear();
	    this.mobileNo.sendKeys(MobNumber);
	    System.out.println("Mobile number entered into input field");
	    Assert.assertEquals(this.mobileNo.getAttribute("value"), MobNumber, "Mobile number mismatch in input field");
 }
 catch(Exception e) {
	 System.out.println("Failed to enter email and mobile number into input field");
 }
}
public void saveChkBox() {
 try {
	  System.out.println("Selecting Confirm and save billing details to your profile checkbox");
	  this.wait.until(ExpectedConditions.elementToBeClickable(this.savechkbx));
	  this.savechkbx.click();
	  System.out.println("Selected Confirm and save billing details to your profile checkbox");
   }
 catch(Exception e) {
	 System.out.println("Failed to select save checkbox");
	 e.printStackTrace();
  }
 }
}