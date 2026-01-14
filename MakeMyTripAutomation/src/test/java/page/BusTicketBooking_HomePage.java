package page;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BusTicketBooking_HomePage {
	
@FindBy (xpath = "(//span[text()='Buses'])[1]")	
private	WebElement buses;

@FindBy (xpath = "//input[@data-cy='fromCityVal']")	
private	WebElement searchInput;	
	
@FindBy (xpath = "//input[@placeholder='From']")	
private	WebElement searchFromCity;	
	
@FindBy (xpath = "//ul[@role='listbox']//li")	
private	List<WebElement> suggestionList;

@FindBy (xpath = "/input[@placeholder='To']")	
private	WebElement searchToCity;

@FindBy (xpath = "//ul[@role='listbox']//li")	
private	List<WebElement> suggestionList1;

@FindBy (xpath = "//button[text()='Search']")	
private	WebElement search;

WebDriverWait wait;	
WebDriver driver;	
	
public BusTicketBooking_HomePage(WebDriver driver) {
    this.driver = driver;
  PageFactory.initElements(driver, this);
	this.wait =  new WebDriverWait(driver,Duration.ofSeconds(50));
}

public void clickBusesTab() {
	this.buses.click();
	Assert.assertEquals(this.buses.getText(), "Buses","Buses tab ttext should be as 'Buses'");
}
public void verifyTrainsHomePageTitle() {
	String actualTitle = driver.getTitle();
	String expectedTitle = "";
	Assert.assertEquals(actualTitle,expectedTitle,"Buses home page title doen not matched expected value");
}
public void verifyTrainsHomePageUrl() {
	String actualUrl = driver.getCurrentUrl();
	String expectedUrl = "";
	Assert.assertEquals(actualUrl, expectedUrl,"Page URL does not matched expected value");
}

public void verifySourceCity(String enterCity,String expCity) {
	this.searchInput.click();
	this.wait.until(ExpectedConditions.visibilityOfAllElements(this.suggestionList));
	this.searchFromCity.sendKeys(enterCity);
	
	for(int i=0; i<this.suggestionList.size(); i++) {
		String buses =  this.suggestionList.get(i).getText().trim();
		Assert.assertFalse(buses.isBlank(),"for enter bus "+enterCity+" suggestionList text is blank");
		
		 if(buses.contains(expCity)) {
			 this.suggestionList.get(i).click();
			 Assert.assertTrue(suggestionList.contains(expCity),"Failed to match enter and expected city");
			 break;
		 }
	}
}
	
public void verifyToCity(String enterCity,String expCity) {
	this.searchToCity.click();
	this.wait.until(ExpectedConditions.visibilityOfAllElements(this.suggestionList1));
	this.searchToCity.sendKeys(enterCity);
	
	for(int i=0; i<this.suggestionList1.size(); i++) {
	   String busList = this.suggestionList1.get(i).getText().trim();
	  Assert.assertTrue(busList.isBlank(), "For enter bus "+enterCity+" SuggestionList1 text is blank");
		if(busList.contains(expCity)) {
			this.suggestionList1.get(i).click();
			Assert.assertTrue(busList.contains(expCity),"Failed to match enter and expected city");
			break;
	}
  }	
}

public void selectDate() {
	LocalDate  CurrentDate = LocalDate.now();
    LocalDate journeyStartDate = CurrentDate.plusDays(10);
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EE MMM dd yyyy"); 
    String formattedJourneyDate = journeyStartDate.format(formatter);
    System.out.println("Journey stars from date : "+formattedJourneyDate);
      
   WebElement dateSelect = driver.findElement(By.xpath("//div[@aria-label='"+formattedJourneyDate+"']"));
   dateSelect.click();	
   
   Assert.assertEquals(dateSelect.getAttribute("class").contains("selected"), "Failed to select date in calender u were expected");
}

public void clickSearchButton() {
	
	this.search.click();
	Assert.assertEquals(this.search.getText(), "Search", "Seartch button text is should be as 'Search'");
}
}
