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
	
@FindBy (xpath ="//a[@class='makeFlex spaceBetween hrtlCenter']")
private	WebElement addTraveller;

@FindBy (xpath ="//input[@id='name']")
private	WebElement name ;

@FindBy (xpath ="//input[@placeholder='Enter Age']")
private	WebElement age;

@FindBy (xpath ="//span[text()='Select']")
private	WebElement gender;

@FindBy (xpath ="//span[@class='arrow arrow-down-wide'])[4]")
private	WebElement birth;

@FindBy (xpath ="//button[text()='Add']")
private	WebElement add;

@FindBy (xpath ="//input[@id='contactEmail']")
private	WebElement emailId;

@FindBy (xpath ="//input[@placeholder='Enter Mobile Number']")
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
	this.wait = new WebDriverWait(driver ,Duration.ofSeconds(20));
}

public void addTravellerButton() throws InterruptedException {
 try {	
	Thread.sleep(5000);
	this.js.executeScript("document.body.style.zoom='50%'");
	this.wait.until(ExpectedConditions.elementToBeClickable(this.addTraveller));
	System.out.println();
	this.addTraveller.click();
}
 catch(Exception e) {
	 System.out.println("Failed to click on add traveller button");
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
public void enterTravellerName(String fullName) {
	this.name.sendKeys(fullName);
	Assert.assertEquals(this.name.getAttribute("value"),fullName,"Traveller name is not enter correctly");
}

public void enterTravellerAge(String completeAge) {
	this.age.sendKeys(completeAge);
	Assert.assertEquals(this.age.getAttribute("value"), completeAge,"Entered age is not enter correctly");
}

public void selectGender(String gen) {
	this.gender.click();
	WebElement genOptions = driver.findElement(By.xpath("//span[text()='"+gen+"']"));
	 genOptions.click();
	 Assert.assertEquals(genOptions.getText(), gen,"Gender fail to select");
}	
public void selectBirthPreference(String seatPrefered) {	    
	    this.birth.click();
    WebElement birtOptions = driver.findElement(By.xpath("//span[text()='"+seatPrefered+"']"));
    birtOptions.click();  
    Assert.assertEquals(birtOptions.getText(), seatPrefered ,"Birth fail to select");
}
public void clickAddButton() {
    this.add.click();
    Assert.assertTrue(add.isDisplayed(), "Add button should be visible");
}

public void enterEmailAndMobile(String Id,String MobNumber) {

	    this.emailId.clear();
	    this.emailId.sendKeys(Id);
	    Assert.assertEquals(this.emailId.getAttribute("value"), Id, "Email not entered correctly");

	    this.mobileNo.clear();
	    this.mobileNo.sendKeys(MobNumber);
	    Assert.assertEquals(this.mobileNo.getAttribute("value"), MobNumber, "Mobile number not entered correctly");
     }
public void saveChkBox() {
	    this.savechkbx.click();
	    Assert.assertTrue(this.savechkbx.isSelected(), "Save traveller checkbox should be selected after clicking");
	}
}