package pom;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Book_TrainSelectPage {

@FindBy(xpath = "//ul[@class='makeFlex column Checkbox_checkboxList__Ge1fM']//li")	
private	List<WebElement> filters;

@FindBy (xpath = "//label[@for='2nd Class AC - 2A']")
private WebElement journeyFilter;

@FindBy (xpath = "//section[@aria-label='Train Listings']//div//div")
private	List<WebElement> trainOptions;

@FindBy (xpath = "//span[text()='Confirmed Options']")
private WebElement confirmOption;

@FindBy (xpath = "//span[text()='Book Now']")
private WebElement bookNowBtn;

private WebDriver driver;
private WebDriverWait wait;
private JavascriptExecutor js;

public Book_TrainSelectPage(WebDriver driver) {
	
	this.driver = driver;
	PageFactory.initElements(driver, this);
	this.js = (JavascriptExecutor)this.driver;
	this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
 }
public void select2AjourneyFilter() throws InterruptedException {
 try {	
	 Thread.sleep(5000);
	 this.js.executeScript("document.body.style.zoom='50%'");
	 for(int i=0; i<this.filters.size(); i++) {
		 String filter = this.filters.get(i).getText().trim();
		 if(this.filters.get(i).equals(this.journeyFilter));
		 this.wait.until(ExpectedConditions.elementToBeClickable(this.journeyFilter));
		 this.journeyFilter.click();
		 break;
	 }
}
 catch(Exception e) {
	 e.printStackTrace();
	 System.out.println("failed to select  2A Checkbox");
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

public void selectTrain() {
 try {	
	for(int i = 0; i<this.trainOptions.size(); i++)
    {
 	  String selectedTrain = this.trainOptions.get(i).getText().trim();
 	  System.out.println(selectedTrain);
 	  
 	  if(this.trainOptions.get(i).equals(this.confirmOption)) {
 		  this.wait.until(ExpectedConditions.elementToBeClickable(this.trainOptions.get(i)));
 		  this.trainOptions.get(i).click();
 		  this.wait.until(ExpectedConditions.elementToBeClickable(this.bookNowBtn));
 		  this.bookNowBtn.click();
 	     break;
   }
  }	
 }
 catch(Exception e) {
	 System.out.println("Fail to select train");
	 e.printStackTrace();
  }
}	
}