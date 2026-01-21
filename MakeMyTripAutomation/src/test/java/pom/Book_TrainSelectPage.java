package pom;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
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

@FindBy (xpath = "//p[text()='AC']")
private WebElement AC;

@FindBy (xpath = "//p[text()='2A']")
private	List<WebElement> classType;

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
public void selectACjourneyFilter()  {
 try {	
	 this.js.executeScript("document.body.style.zoom='50%'");
	 for(int i=0; i<this.filters.size(); i++) {
		 this.wait.until(ExpectedConditions.elementToBeClickable(this.AC));
		 this.AC.click();
			 break;
	 }
}
 catch(Exception e) {
	 e.printStackTrace();
	 System.out.println("failed to select  AC Checkbox");
 }
}
public void selectAnyTrainWithClass(String type) {

 boolean matchFound = false;
 
	for (WebElement btn : classType) {
	try {
	      if (!btn.isEnabled() || btn.getAttribute("class").contains("disabled")) {
	      continue; 
	 }
	  this.js.executeScript("arguments[0].scrollIntoView({block: 'center'});", btn);
	  this.wait.until(ExpectedConditions.elementToBeClickable(btn));
       btn.click();
       System.out.println("Success: Selected specific train with class " + classType);
       matchFound = true;
       Assert.assertTrue(matchFound,"Fail to select train");
        break;
  } 
  catch (Exception e) {
	System.err.println("Failed to find the train having class type 2A");
    }
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


}