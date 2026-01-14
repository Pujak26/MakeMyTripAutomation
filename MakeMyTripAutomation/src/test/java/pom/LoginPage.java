package pom;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

public class LoginPage {

@FindBy (xpath = "//img[@data-cy='signInByMailButton']")
private	WebElement emailIcon;

@FindBy (xpath = "//input[@data-cy='userName']")
private	WebElement email;

@FindBy (xpath = "//button[@class='capText font16']")
private	WebElement continueBtn;

@FindBy (xpath = "//input[@data-cy='password']")
private	WebElement pass;

WebDriver driver;
JavascriptExecutor js;	
SoftAssert soft = new SoftAssert();
	
public LoginPage(WebDriver driver) {
	
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

public void verifyLoginPage() {
	this.soft.assertTrue(this.emailIcon.isDisplayed() && this.emailIcon.isEnabled(),"Email icon should be visible and enable");
	this.soft.assertTrue(this.email.isDisplayed()&& this.emailIcon.isEnabled(),"Email field should be visible and enable");
	this.soft.assertTrue(continueBtn.isDisplayed()&& this.emailIcon.isEnabled(),"Continue button should be clickable");
	this.soft.assertTrue(this.pass.isDisplayed() && this.pass.isEnabled(),"Password field should be visible and enable");
	soft.assertAll();
}
public void clickEmailIcon() {
	soft.assertTrue(emailIcon.isDisplayed(), "Email icon should be clickable");
	this.emailIcon.click();
}

public void sendUsername(String username) {
	 this.email.sendKeys(username);	
}

public void clickContinueButton() {
	this.continueBtn.click();
}

public void sendPassword(String password) {
	this.pass.sendKeys(password);
		}
}


//common method 
/*public void verifyLoginPage(String username,String pass ) {
	SoftAssert soft = new SoftAssert();

	soft.assertTrue(emailIcon.isDisplayed(), "Email icon should be clickable");
	this.emailIcon.click();
	
	this.email.sendKeys(username);
    soft.assertEquals(email.getAttribute("value"), username,"Email value not correct");
    
	this.continueBtn.click();
	soft.assertTrue(continueBtn.isDisplayed(), "Continue button should be clickable");
	 
	this.password.sendKeys(pass);
	soft.assertEquals(password.getAttribute("value"), pass,"Password value not correct");
	 
	soft.assertAll();
}*/


