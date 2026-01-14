package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Live_StatusPage {
	
@FindBy (xpath = "(//div[@class='tp-dt-header-icon'])[2]")
WebElement aiPopup;	

WebDriver driver;

//@FindBy (xpath = "//h2[text()='Disclaimer']")
//WebElement Disclaimer;

public Live_StatusPage(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver, this);
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














}
