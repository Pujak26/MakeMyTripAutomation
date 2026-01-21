package pom;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Live_HomePage {
	
@FindBy (xpath = "//span[@data-cy='liveTrainStatus']")
private	WebElement liveTrainStatusBtn;

@FindBy (xpath = "//label[@for='trainNum']")
WebElement searchInput;

@FindBy (xpath = "//input[contains(@class, 'react-autosuggest__input')]")
private	WebElement searchTrain;

@FindBy (xpath ="//ul[@role='listbox']//li")
List<WebElement> suggestionList;

@FindBy (xpath = "//ul[@class='travelForPopup']//li")
List<WebElement> stops;

@FindBy (xpath = "//a[text()='CHECK STATUS']")
private	WebElement checkStatus;

private WebDriver driver;
private	WebDriverWait wait;

public Live_HomePage(WebDriver driver) {
	
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

public void clickNVerifyLiveTrainStatusBtn() {
try {	
	this.wait.until(ExpectedConditions.elementToBeClickable(this.liveTrainStatusBtn));
	this.liveTrainStatusBtn.click();
	this.wait.until(ExpectedConditions.attributeContains(this.liveTrainStatusBtn, "class", "active"));
	Assert.assertTrue(this.liveTrainStatusBtn.getAttribute("class").contains("active"),"Failed to cliked on Live train status button");
}
catch(Exception e) {
	System.out.println("Live train status button is not clickable");
	e.printStackTrace();
 }
}

public void clickSerachInputField() {
try {
	System.out.println("clicking on search train num or name field");
	this.wait.until(ExpectedConditions.elementToBeClickable(this.searchInput));
	this.searchInput.click();
}
catch(Exception e) {
	System.out.println("Failed to cllick on search input field");
	e.printStackTrace();
 }
}

public void enterAndVerifyTrainNum(String trainNum) throws InterruptedException {
try {
	this.wait.until(ExpectedConditions.visibilityOf(this.searchTrain));
    this.searchTrain.sendKeys(trainNum);
    Assert.assertEquals(searchTrain.getAttribute("value"),trainNum,"Failed to match entered train number to check live train status");
    Thread.sleep(5000);
    this.wait.until(ExpectedConditions.visibilityOfAllElements(this.suggestionList));
    
       for(int i=0; i<this.suggestionList.size(); i++) {
		String trainOptions = this.suggestionList.get(i).getText().trim();
		System.out.println("Available trains : "+trainOptions );
		Assert.assertFalse(trainOptions.isEmpty(),"Failed to find this train in train options");
		
	if(trainOptions.toUpperCase().contains(trainNum.toUpperCase().trim())) {
	   this.wait.until(ExpectedConditions.elementToBeClickable(this.suggestionList.get(i)));
	   this.suggestionList.get(i).click();
	   break;
	   	   }
         }   
}catch(Exception e) {
	System.out.println("Failed to select train");
	e.printStackTrace();
}
}

public void verifYourStopField(String stopName) {
 try {	
		this.wait.until(ExpectedConditions.visibilityOfAllElements(this.stops));	
	    for(WebElement e : stops) {
	   	 String sList = e.getText().trim();
	   	 System.out.println("stop list :" +sList);
	   	 if(sList.toUpperCase().contains(stopName.toUpperCase())) {
	   	 this.wait.until(ExpectedConditions.elementToBeClickable(e));	 
         e.click();
		 Assert.assertTrue(sList.toUpperCase().contains(stopName.toUpperCase()),"name you enter is not found in this list");    
		  break;
	  	    }
	    }
    }
 catch(Exception e) {
	 System.out.println("Failed to match name");
	 e.printStackTrace();
  }
 }

public void selectDate(String startDate) {
try {
 List<WebElement> datePath = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy
    		(By.xpath("//ul[@class='travelForPopup dateSel']//li/span[text()='"+startDate+"']")));  
 datePath.get(0).click();
}
catch(Exception e) {
	System.out.println("Failed to select date");
	e.printStackTrace();
 }
}
public void clickCheckStatusButton() {
 try {	
	this.wait.until(ExpectedConditions.elementToBeClickable(checkStatus));
    this.checkStatus.click();
  }
 catch(Exception e) {
	 System.out.println("Failed to click on check status button");
	 e.printStackTrace();
 }
}
}





