package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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
	
public PNR_HomePage(WebDriver driver) {	
	this.driver = driver;
 PageFactory.initElements(driver, this);
}

public void clickClosePopup() {
	this.close.click();
	Assert.assertTrue(this.close.isDisplayed(),"Close icon is not visible on popup");
}
public void verifyTrainsPageTitle(String title) {
	Assert.assertEquals(this.driver.getTitle(),title,"Trains home page title does not match expected value");
}
public void verifyTrainPageUrl(String url) {
	Assert.assertEquals(this.driver.getCurrentUrl(), url,"Page URL does not match expected value");
 } 
public void clickTrainsTab() {
	this.trains.click();
	Assert.assertEquals(this.trains.getText(),"Trains","Trains tab text should be as 'Trains'");
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

/*public void verifyPNRStatusButton() {
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
}
public void verifyBookTrainTicketsButton() {
	if(this.bookTrainTickets.isEnabled()){
	     System.out.println(this.bookTrainTickets.getText());
	   }else{
		this.bookTrainTickets.click();
	   }
	Assert.assertEquals(this.bookTrainTickets.isEnabled(),true, "Book Train ticket option should be selected after clicking");
}*/

public void enterPnrNumber(String pnrNum){
   this.pnrNumber.sendKeys(pnrNum);
   
  Assert.assertEquals(pnrNumber.getAttribute("value"), pnrNum,"Fail to verify match with expectes PNR number");
}

public void clickAndVerifyCheckStatusButton() {
	this.checkStatus.click();
	Assert.assertEquals(this.checkStatus.getText(),"CHECK STATUS","Tab text should be as CHECK STATUS" );
}
}
