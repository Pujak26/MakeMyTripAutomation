package page;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class BusTicketBooking_BusSelectPage {

	
@FindBy (xpath = "//p[text()='AC']")	
private WebElement ac;	

@FindBy (xpath = "//p[text()='Sleeper']")	
private WebElement sleeper;

@FindBy (xpath = "//div[@role='region']//button[text()='Select Seats']")
private List<WebElement> suggestionList;

WebDriver driver;


public  BusTicketBooking_BusSelectPage() {
	
 PageFactory.initElements(driver, this);
}

public void clickOnFilters() {
	
	this.ac.click();
	if(this.ac.isSelected()) {
		this.ac.getText();
	}else {
		this.ac.click();
	}
	Assert.assertTrue(this.ac.isSelected(), "AC filter is not selected");
}









}