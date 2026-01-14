package pom;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class PNR_DetailsPage {
@FindBy(xpath = "(//div[@class='tp-dt-header-icon'])[2]")
WebElement aiPopup;

@FindBy(xpath = "//span[text()='See description of all symbols']")
private WebElement description ;

@FindBy(xpath = "//a[text()='See Coach Positions']")
private WebElement coachPositions;

@FindBy(xpath = "//span[@class='close']")
private WebElement closeCoach;

private WebDriver driver;
JavascriptExecutor js;
	
	public PNR_DetailsPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
public void clickToAiPopup() {
	this.aiPopup.click();
}
public void zoomOutCurrentPage() {
     this.js = (JavascriptExecutor)this.driver;
		this.js.executeScript("document.body.style.zoom='50%'");
}
public void verifyTrainsPageTitle(String title) {
	Assert.assertEquals(this.driver.getTitle(),title,"Trains home page title does not match expected value");
}
public void verifyTrainPageUrl(String url) {
	Assert.assertEquals(this.driver.getCurrentUrl(), url,"Page URL does not match expected value");
 } 

public void clickOnDescription() {
	   this.description.click();
	   }
  public void clickOnCoach() {
		this.js = (JavascriptExecutor)this.driver;
		this.js.executeScript("arguments[0].scrollIntoView(true);",this.coachPositions);
	
		this.coachPositions.click();
	Assert.assertEquals(this.coachPositions.getText(),"See Coach Positions","coachPositions tab text should be as 'See Coach Positions'");
  }
  public void clickClose() {
	  this.closeCoach.click();
	    }
}
