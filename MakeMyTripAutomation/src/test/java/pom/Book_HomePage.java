package pom;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

public class Book_HomePage {

@FindBy (xpath = "//span[@data-cy='bookTrainTickets']")
private	WebElement bookTrainTickets;

@FindBy (xpath = "//span[@class='appendRight10']")
private WebElement checkPNRStatus;

@FindBy (xpath = "//span[text()='Live Train Status']")
private WebElement liveTrainStatus;

@FindBy (css = "#fromCity")
private	WebElement fromCity;

@FindBy (xpath = "//input[@placeholder='From']")
private	WebElement searchFromCity;

@FindBy (xpath = "//ul[@class='react-autosuggest__suggestions-list']//li//div/span")
List<WebElement> suggestionList;

@FindBy (xpath = "//input[@placeholder='To']")
private	WebElement searchToCity;

@FindBy (xpath = "//span[@aria-label='Next Month']")
private	WebElement NextArrow;

@FindBy (xpath = "(//label[@for='travelDate']//p//span)[1]")
private	WebElement travelDate;

@FindBy (xpath = "(//label[@for='travelDate']//p//span)[2]")
private	WebElement travelMonth;

@FindBy (xpath = "//label[@for='travelDate']//span[@class='shortYear']")
private	WebElement year;

@FindBy (xpath = "//p[@data-cy='departureDay']")
private	WebElement travelDay;

@FindBy (xpath = "//input[@id='travelClass']")
private	WebElement travelClass;

@FindBy (xpath = "//ul[@class='travelForPopup']//li")
List<WebElement> classOptions;

@FindBy (xpath = "//input[@id='travelClass']")
private	WebElement classCode;

@FindBy (xpath = "//a[text()='Search']")
private	WebElement search;

private WebDriver driver;
private WebDriverWait wait;
private JavascriptExecutor js;

public Book_HomePage(WebDriver driver) {
	 this.driver = driver;
	 PageFactory.initElements(driver, this);
	 this.js = (JavascriptExecutor) driver;
     this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
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
public void verifyTrainButtonTypes(String type) {
try {
	this.js.executeScript("document.body.style.zoom='60%'");
	System.out.println("Selecting the radio button");
	boolean status = false;
	if(type.equalsIgnoreCase("Book Train Tickets")) {
		status = this.bookTrainTickets.isEnabled();
		System.out.println("Book Train Tickets button is selected");
	}
	 Assert.assertTrue(status, "Button should be selected");
	}
catch(Exception e) {
	System.err.println("Failed to select Book Train Tickets button");
		e.printStackTrace();
   }
}
public void verifySourceCity(String enterCity,String expCity) throws InterruptedException {
 try {	
			this.js.executeScript("document.body.style.zoom='50%'");
			System.out.println("Clicking search source city field");
			this.fromCity.click();
			
			this.wait.until(ExpectedConditions.visibilityOf(this.searchFromCity));
			this.searchFromCity.sendKeys(enterCity);
			
		    Assert.assertEquals(this.searchFromCity.getAttribute("Value"),enterCity,"Fail to verify enter city search as expected city");
		    Thread.sleep(5000);
			this.wait.until(ExpectedConditions.visibilityOfAllElements(this.suggestionList));
	
		    for(int i = 0; i < this.suggestionList.size(); i++) {
			
			String list = suggestionList.get(i).getText().trim();
			Assert.assertFalse(list.isEmpty(),enterCity +"is not found in suggestions list");     
			
		  	if(list.toUpperCase().contains(expCity.toUpperCase().trim())) {
		  	System.out.println("Source city has been selected from the suggestion list");
			this.suggestionList.get(i).click();
		  	      
		    Assert.assertTrue(list.toUpperCase().contains(expCity.toUpperCase().trim()),"Expected city"+expCity+ "this city is not found in suggestion list");     
			        break;
	     }
	}
}
 catch(Exception e) {
	System.err.println("Failed to enter and select source city");
	e.printStackTrace();
  }
}
	
public void verifyToCity(String enterCity,String expCity) throws InterruptedException {
 try {	
	this.wait.until(ExpectedConditions.visibilityOf(this.searchToCity));
			this.searchToCity.sendKeys(enterCity);
		    Assert.assertEquals(this.searchToCity.getAttribute("value"),enterCity,"Fail to verify enter city match with expected");
			Thread.sleep(5000);
			
			this.wait.until(ExpectedConditions.visibilityOfAllElements(this.suggestionList));
			for(int i=0; i<this.suggestionList.size(); i++) {
				
			String list = this.suggestionList.get(i).getText().trim();
		    Assert.assertFalse(list.isEmpty(),enterCity+"is not found in suggestions list");
				
	  	   if(list.toUpperCase().contains(expCity.toUpperCase().trim())) {
	  		
	  		 this.suggestionList.get(i).click();
	  		System.out.println("Destination city has been selected from the suggestion list");
		        
		    Assert.assertTrue(list.toUpperCase().contains(expCity.toUpperCase()),"expected city"+expCity+ "this city is not found in suggestion list");   
	        break;
  	   }
	}
}
 catch(Exception e) {
	 System.err.println("Failed to enter and select destination city");
	 e.printStackTrace();
 }
}
public void selectAndVerifyJourneyDateFromCalender(int days) throws InterruptedException {
  try {
		this.wait.until(ExpectedConditions.attributeContains(this.NextArrow, "class", "next"));
		Assert.assertTrue(this.NextArrow.getAttribute("class").contains("next"), "Fail to open journey date select from calender");
		
	    LocalDate  CurrentDate = LocalDate.now();
	    LocalDate journeyStartDate = CurrentDate.plusDays(days);
	 
	    String formatDate = journeyStartDate.format(DateTimeFormatter.ofPattern("EE MMM dd yyyy"));
	    System.out.println("Journey stars from date : "+formatDate);
	      
	    WebElement dateSelected = driver.findElement(By.xpath("//div[@aria-label='"+formatDate+"']"));
	    Thread.sleep(3000);
	    dateSelected.click();
	    System.out.println("Date has selected from the calender");
   
        System.out.println("Date showing in the format day month date year");
		String expectedDateSelect = journeyStartDate.format(DateTimeFormatter.ofPattern("EE MMM d yy"));
		String dateText = (this.travelDay.getText().trim().substring(0, 3)).concat(" ").concat
			               (this.travelMonth.getText()).concat(" ").concat
			               (this.travelDate.getText().trim()).concat(" ").concat
			               (this.year.getText().trim());

Assert.assertEquals(dateText, expectedDateSelect,"fail to match date format");
}

 catch(Exception e) {
	System.err.println("Date is not match with selected format");
	e.printStackTrace();
 }
}

public void selectClass(String className) {
 try {	
       for (int i=0; i<classOptions.size(); i++) {
    	   
	   	     String code = this.classCode.getAttribute("value");
	   	     String classname = classOptions.get(i).getText().trim();
	   	     String selectedClass = code + classname;
		  	     
	         if (classname.equalsIgnoreCase(className)) {
	    	 this.wait.until(ExpectedConditions.elementToBeClickable(this.classOptions.get(i)));
   	         this.classOptions.get(i).click();
   	         this.wait.until(ExpectedConditions.textToBePresentInElementValue(this.classCode, code));
             break;
	        }
           Assert.assertEquals(selectedClass,className,"Failed to select expectted class");
      }
 }
 catch(Exception e) {
	 System.err.println("Fail to select class");
	 e.printStackTrace();
 }
} 
 
public void clickOnSearch() {
 try {	
      this.wait.until(ExpectedConditions.elementToBeClickable(this.search));
	  this.search.click();
	  System.out.println("Clicked to Search button");
  }
 catch(Exception e) {
	 System.err.println("Failed to click on search button");
	 e.printStackTrace();
  }
 }
}
















	
