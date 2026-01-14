package pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Book_TrainSelectPage {

@FindBy(xpath = "//label[@for='AC']")	
private	WebElement acChkbx;

@FindBy (xpath = "//p[@data-testid='class-info']")
private	List<WebElement> trainOptions;

@FindBy (xpath = "//p[@data-testid='class-info']")
private	List<WebElement> classOptions;

private WebDriver driver;	

public Book_TrainSelectPage(WebDriver driver) {
	
	this.driver = driver;
	PageFactory.initElements(driver, this);
 }
public void selectAcCheckBox() {
	if(acChkbx.isSelected()) {
		   System.out.println("AC is selected already,no need to check checkbox");
	   }else {
		   System.out.println(this.acChkbx.getText());
		   this.acChkbx.click();
	   }
	Assert.assertEquals(this.acChkbx.isSelected(), true,"AC checkbox is not selected");
}

public void verifyTrainsPageTitle(String title) {
	System.out.println("Title : "+this.driver.getTitle());
	Assert.assertEquals(this.driver.getTitle(),title,"Trains home page title does not match expected value");
}
public void verifyTrainPageUrl(String url) {
	System.out.println("URL : "+this.driver.getCurrentUrl());
	Assert.assertEquals(this.driver.getCurrentUrl(), url,"Page URL does not match expected value");
 } 

public void selectTrain(String tName,String classType) {
	for(int i = 0; i<this.trainOptions.size(); i++)
    {
 	  String selectedTrain = this.trainOptions.get(i).getText().trim();
 	  Assert.assertFalse(selectedTrain.isBlank(),tName +"for this train train option text is blank");
 	  
 	  if(selectedTrain.contains(tName)) {
 		Assert.assertTrue(selectedTrain.contains(tName), "Train name "+ tName + " is not present in options");
 		  System.out.println("Train name to book ticket is = "+selectedTrain);
    	     }
   }
	
	 for(int i=0; i<this.classOptions.size(); i++)
	    {
	    	String selectedClass = classOptions.get(i).getText();
	    	Assert.assertFalse(selectedClass.isBlank(), classType +"for this code text is blank");
	    	
	    	if(selectedClass.equals(classType)) {
	    	Assert.assertTrue(selectedClass.equals(classType),"Selected class and type class failed to matched");
	    		this.classOptions.get(i).click();
	    		break;
	    	}
	   }
   }
}