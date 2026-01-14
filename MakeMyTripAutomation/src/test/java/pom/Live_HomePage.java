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
	
@FindBy (xpath = "//span[@class='commonModal__close']")	
private	WebElement close;

@FindBy (xpath = "(//span[text()='Trains'])[1]\")")	
private	WebElement trains;	

@FindBy (xpath = "//span[@data-cy='bookTrainTickets']")
private	WebElement bookTrainTickets;

@FindBy (xpath = "//span[@class='appendRight10']")
private WebElement checkPNRStatus;

@FindBy (xpath = "//span[text()='Live Train Status']")
private	WebElement liveTrainStatus;

@FindBy (xpath = "(//div[@class='tp-dt-header-icon'])[2]")
private	WebElement aiPopup;

@FindBy (xpath = "//label[@class='makeFlex column latoRegular']")
WebElement searchInput;

@FindBy (xpath = "//input[contains(@class, 'react-autosuggest__input')]")
private	WebElement searchTrain;

@FindBy (xpath ="//ul[@class='react-autosuggest__suggestions-list']//li//div")
List<WebElement> suggestionList;

@FindBy (xpath = "//ul[@class='travelForPopup']//li//span")
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
public void clickClosePopup() {
	this.close.click();
	Assert.assertTrue(this.close.isDisplayed(),"Close icon is not visible on popup");
}
public void clickTrainsTab() {
	this.trains.click();
	Assert.assertEquals(this.trains.getText(),"Trains","Trains tab text should be as 'Trains'");
}
public void clickAiPopUp() {
	this.aiPopup.click();
}
public void verifyTrainsPageTitle(String title) {
	Assert.assertEquals(this.driver.getTitle(),title,"Trains home page title does not match expected value");
}
public void verifyTrainPageUrl(String url) {
	Assert.assertEquals(this.driver.getCurrentUrl(), url,"Page URL does not match expected value");
 } 
public void verifyTrainButtonTypes(String type) {
	boolean status = false;
	
	if(type.equalsIgnoreCase("Book Train Tickets")) {
		status = this.bookTrainTickets.isEnabled();
	}
	if(type.equalsIgnoreCase("Check PNR Status")) {
		status = this.checkPNRStatus.isSelected();
	}
	if(type.equalsIgnoreCase("Live Train Status")) {
		status = this.liveTrainStatus.isSelected();
	
Assert.assertEquals(status, true,"Fail to verify "+type+" radio button is selected");
		//OR
//Assert.assertTrue(status, "Button should be selected");
	}
}
  //OR Separate method

/*public void verifyBookTrainTicketsButton() {
	if(this.bookTrainTickets.isEnabled()){
  	     System.out.println(this.bookTrainTickets.getText());
  	   }else{
  		this.bookTrainTickets.click();
  	   }
	Assert.assertEquals(this.bookTrainTickets.isEnabled(),true, "Book Train ticket option should be selected after clicking");
}
  public void verifyPNRStatusButton() {
	if(this.checkPNRStatus.isSelected()) {
		System.out.println(this.checkPNRStatus.getText());
	}else {
			this.checkPNRStatus.click();
	}
	Assert.assertEquals(this.checkPNRStatus.isSelected(), true, "Check PNR status button should be selected after clicking");
}

public void verifyLiveTrainStatusButton() {
	if(this.liveTrainStatus.isSelected()) {
		System.out.println(this.liveTrainStatus.getText());
	}else {
		this.liveTrainStatus.click();
	}
	Assert.assertEquals(this.liveTrainStatus.isSelected(), true, "Live train status button should be selected after clicking");
}*/

public void clickSerachInputField() {
	this.searchInput.click();
	
	 Assert.assertTrue(searchInput.isDisplayed() && searchInput.isEnabled(),"Search input field should be clickable");
}
public void verifySearchTrainByNameField(String name,String tName) {
	this.wait.until(ExpectedConditions.visibilityOf(this.searchTrain));
    this.searchTrain.sendKeys(name);;
  
Assert.assertEquals(searchTrain.getAttribute("value"), name,"Search train entered value fail to match with expected value");  

this.wait.until(ExpectedConditions.visibilityOfAllElements(this.suggestionList));
       for(WebElement e : this.suggestionList) {
		String tList = e.getText().trim();
		Assert.assertFalse(tList.isBlank(),name+"is not found in suggestions list");
		
		if(tList.toUpperCase().contains(tName)) {
			  e.click();
		Assert.assertTrue(tList.toUpperCase().contains(tName.toUpperCase()),"train "+tName+"is not found in the list");	
		     break;
	   	    }
        }   
    }

public void verifYourStopField(String stopName) {
	this.wait.until(ExpectedConditions.visibilityOfAllElements(this.stops));	
    for(WebElement e : stops) {
   	 String sList = e.getText().trim();
   	 if(sList.equalsIgnoreCase(stopName)) {
	        e.click();
	    Assert.assertTrue(sList.equalsIgnoreCase(stopName),"name you enter is not found in this list");    
	        break;
  	    }
    }
}

public void selectDate(String startDate) {

 List<WebElement> datePath = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy
    		(By.xpath("//ul[@class='travelForPopup dateSel']//li/span[text()='"+startDate+"']")));  

 datePath.get(0).click();
}

public void clickCheckStatusButton() {
    this.checkStatus.click();
	Assert.assertEquals(this.checkStatus.getText(), "CHECK STATUS","Fail to verify buttom text");
  }
}






