package stepDef;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import driverSetup.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pom.Book_HomePage;
import pom.Book_TrainSelectPage;
import pom.Book_TravellerDetailsPage;
import pom.PNR_HomePage;
import pom.Train_PopupPage;
import pom.PNR_DetailsPage;
import pom.Live_HomePage;
import pom.Live_StatusPage;
import pom.LoginPage;

public class TrainStepDef extends DriverFactory {

	//private LoginPage loginPage;
	private Train_PopupPage train_PopupPage;
	private Book_HomePage book_HomePage;
	private Book_TrainSelectPage book_TrainSelectPage;
	private Book_TravellerDetailsPage book_TravellerDetailsPage;
	private PNR_HomePage pnr_HomePage; 
	private PNR_DetailsPage pnr_DetailsPage;
	private Live_HomePage live_HomePage;
	private Live_StatusPage live_StatusPage;
	
	@Given ("User click on popup")
	 public void close_popup() throws InterruptedException  {
		this.train_PopupPage = new Train_PopupPage(driver);
		this.train_PopupPage.clickAiPopUp();
		this.train_PopupPage.verifyTrainsPageTitle(driver.getTitle());
		this.train_PopupPage.verifyTrainPageUrl(driver.getCurrentUrl());
		this.train_PopupPage.clickLoginPopup();;
		}
	
	/*@Given ("User login to application using below credentials")
	public void login() {
    loginPage = new LoginPage(driver); 
	loginPage.verifyLoginPage();
	
	
	this.loginPage.clickEmailIcon();
	String username = DriverFactory.envConfig.getUsername();
	this.loginPage.sendUsername(username);
	
	this.loginPage.clickContinueButton();
	
	String password = DriverFactory.envConfig.getPassword();
	this.loginPage.sendPassword(password);
	}*/
	
	@Given ("User select on Trains option")
	public void select_Trains_Option() throws InterruptedException {
		this.train_PopupPage = new Train_PopupPage(driver);
		this.train_PopupPage.clickTrainsTab();
	}

	@Given ("User click on {string} option")
	public void click_Book_Train_Tickets_Option(String type) {
		this.book_HomePage = new Book_HomePage(driver);
		this.book_HomePage.verifyTrainButtonTypes(type);

		this.book_HomePage.verifyTrainsPageTitle(driver.getTitle());
		this.book_HomePage.verifyTrainPageUrl(driver.getCurrentUrl());
	}
	
	@When ("User click and enter {string} as a source city and select matched city {string}")
	public void click_and_Enter_and_Select_Source_City(String enterCity,String expCity) throws InterruptedException {
		this.book_HomePage = new Book_HomePage(driver);
		this.book_HomePage.verifySourceCity(enterCity, expCity);
	}
	
	@When ("User enter {string} as a destination city and select matched city {string}")
	public void click_and_Enter_and_Select_Destination_City(String enterCity,String expCity) throws InterruptedException {
		this.book_HomePage = new Book_HomePage(driver);
		this.book_HomePage.verifyToCity(enterCity, expCity);
	}
	
	@When ("User select the travel date {int} days from today")
	public void select_Travel_Date(int date) throws InterruptedException {
		this.book_HomePage = new Book_HomePage(driver);
		this.book_HomePage.selectAndVerifyJourneyDateFromCalender(date);
	}
	
	@When ("User select the travel class as {string}")
	public void select_Travel_Class(String className) {
		this.book_HomePage = new Book_HomePage(driver);
		this.book_HomePage.selectClass(className);
	}
	
	@When ("User click on search button")
	public void click_Search_Button() {
		this.book_HomePage = new Book_HomePage(driver);
		this.book_HomePage.clickOnSearch();
	}
	
	@When ("User select AC checkbox from quick filter options")
	public void select_Journey_class_Checkbox_from_Quick_filters() {
		this.book_TrainSelectPage = new Book_TrainSelectPage(driver);
		this.book_TrainSelectPage.selectACjourneyFilter();
		
		this.book_TrainSelectPage.verifyTrainsPageTitle(driver.getTitle());
		this.book_TrainSelectPage.verifyTrainPageUrl(driver.getCurrentUrl());
	}
	@When ("User select first train from available options having class type {string}")
	public void select_Train(String type) {
		this.book_TrainSelectPage = new Book_TrainSelectPage(driver);
		this.book_TrainSelectPage.selectAnyTrainWithClass(type);
	}
	@When ("User click on Add Traveller button")
	public void click_Add_Traveller_Button() {
		this.book_TravellerDetailsPage = new Book_TravellerDetailsPage(driver);
	    this.book_TravellerDetailsPage.clickToAddTravellerBtn();
    }
	@When ("User enter name as {string} in fullName")
	public void enter_Traveller_Name(String fullName) {
		this.book_TravellerDetailsPage = new Book_TravellerDetailsPage(driver);
		this.book_TravellerDetailsPage.enterTravellerName(fullName);
	  }
	@When ("User enter age as {string} completeAge")
	public void enter_Traveller_Age(String completeAge) {
		this.book_TravellerDetailsPage = new Book_TravellerDetailsPage(driver);
	    this.book_TravellerDetailsPage.enterTravellerAge(completeAge);
	}

	@When ("User select gender as {string} from dropdown")
	public void select_Gender_from_Dropdown(String gen) {
		this.book_TravellerDetailsPage = new Book_TravellerDetailsPage(driver);
		this.book_TravellerDetailsPage.selectGender(gen);
	}
	
	@When ("User select birth preference as {string} from dropdown")
	public void select_Birth_Preference_from_Dropdown(String seatPrefered) {
		this.book_TravellerDetailsPage = new Book_TravellerDetailsPage(driver);
		this.book_TravellerDetailsPage.selectBirthPreference(seatPrefered);
	}
	
	@When ("User click on ADD button")
	public void click_ADD_Button() {
	  this.book_TravellerDetailsPage = new Book_TravellerDetailsPage(driver);
	  this.book_TravellerDetailsPage.clickAddButton();
	}
	
	@When ("User enter EmailId as {string} and Mobile Number as {string} in contact information")
	public void enter_Contact_Information(String Id,String MobNumber) {
	this.book_TravellerDetailsPage = new Book_TravellerDetailsPage(driver);
	this.book_TravellerDetailsPage.verifyTrainsPageTitle(driver.getTitle());
	this.book_TravellerDetailsPage.verifyTrainPageUrl(driver.getCurrentUrl());
	this.book_TravellerDetailsPage.enterEmailAndMobile(Id, MobNumber);
	}
	
	@Then ("User select checkbox confirm and save billing details to your profile")
	public void select_Checkbox_Confirm_and_Save_billing() {
		this.book_TravellerDetailsPage = new Book_TravellerDetailsPage(driver);	
	    this.book_TravellerDetailsPage.saveChkBox();
	}
	
	@Given ("User click on close")
	 public void close() throws InterruptedException  {
		this.train_PopupPage = new Train_PopupPage(driver);
		this.train_PopupPage.clickAiPopUp();
		
		this.train_PopupPage.verifyTrainsPageTitle(driver.getTitle());
		this.train_PopupPage.verifyTrainPageUrl(driver.getCurrentUrl());
		this.train_PopupPage.clickLoginPopup();
		}
	
	/*@Given ("User login to application using below credentials")
    public void login_Application(List<Map<String,String>> credentials ) {
		this.loginPage = new LoginPage(driver);
		this.loginPage.verifyLoginPage();
	
		this.loginPage.clickEmailIcon();
		this.loginPage.sendUsername(credentials.get(1).get("Username"));
		this.loginPage.clickContinueButton();
		this.loginPage.sendPassword(credentials.get(2).get("Password"));
   }*/
	

	@Given ("User click on Train")
	public void clicks_to_Trains() throws InterruptedException {
			this.train_PopupPage = new Train_PopupPage(driver);
			this.train_PopupPage.clickTrainsTab();
			}
	
	@Given("User click on Check PNR Status button")
	public void click_Check_PNR_Status_Button() {
		this.pnr_HomePage = new PNR_HomePage(driver);
		this.pnr_HomePage.clickNVerifyerifyChkPNRStatusBtn();
		
		this.pnr_HomePage.verifyTrainsPageTitle(driver.getTitle());
		this.pnr_HomePage.verifyTrainPageUrl(driver.getCurrentUrl());
		
	}
	
	@When ("User enter valid PNR number in PNR number input field as {string}")
	public void enter_PNR_Number(String pnrNum) {
		this.pnr_HomePage = new PNR_HomePage(driver);
		this.pnr_HomePage.enterPnrNumber(pnrNum);
	}
	
	@When ("User click on CHECK STATUS button")
	public void click_Check_Status_Button() {
		this.pnr_HomePage = new PNR_HomePage(driver);
		this.pnr_HomePage.clickAndVerifyCheckStatusButton();
	}
	
	@When ("User handle the Ai Popup")
	public void dismiss_Ai_popup() {
		this.pnr_DetailsPage = new  PNR_DetailsPage(driver);
		this.pnr_DetailsPage.clickAiPopUp();
		
		this.pnr_DetailsPage.verifyTrainsPageTitle(driver.getTitle());
		this.pnr_DetailsPage.verifyTrainPageUrl(driver.getCurrentUrl());
	}
	
	@When ("User click on See description of all symbols link text")
	public void click_See_Description() {
		this.pnr_DetailsPage = new  PNR_DetailsPage(driver);
		this.pnr_DetailsPage.clickOnDescription();
	}
	
	@When ("User click on See Coach Position link text")
	public void click_Coach_Position() {
		this.pnr_DetailsPage = new PNR_DetailsPage(driver); 
	    this.pnr_DetailsPage.clickOnCoach();
	}
	@Then ("User click on Close Coach Position Popup")
	public void click_Close() {
		this.pnr_DetailsPage = new PNR_DetailsPage(driver);
	    this.pnr_DetailsPage.clickClose();
 }
	
	@Given ("User select on close icon on popup")
	public void select_closed() {
		this.train_PopupPage = new Train_PopupPage(driver);
		this.train_PopupPage.clickAiPopUp();
		this.train_PopupPage.verifyTrainsPageTitle(driver.getTitle());
		this.train_PopupPage.verifyTrainPageUrl(driver.getCurrentUrl());
		this.train_PopupPage.clickLoginPopup();
		}

	/*@Given ("User login to application using below credentials")
	public void login_App(List<Map<String,String>> credentials) {
		this.loginPage = new LoginPage(driver);
		this.loginPage.verifyLoginPage();
		
		this.loginPage.clickEmailIcon();
		this.loginPage.sendUsername(credentials.get(1).get("Username"));
		this.loginPage.clickContinueButton();
		this.loginPage.sendPassword(credentials.get(2).get("Password"));
	}*/
	
	@Given ("User click the Trains icon")
	public void clicks_Trains_icon() throws InterruptedException {
		this.train_PopupPage = new Train_PopupPage(driver);
		this.train_PopupPage.clickTrainsTab();
		
		}
	
	@Given ("User click on live train status button")
	public void live_Train_Status() {
	  this.live_HomePage = new Live_HomePage(driver);
	  this.live_HomePage.clickNVerifyLiveTrainStatusBtn();
	  
	  this.live_HomePage.verifyTrainsPageTitle(driver.getTitle());
	  this.live_HomePage.verifyTrainPageUrl(driver.getCurrentUrl());
	}
	
	@When ("User click on search train input field")
	public void click_Search_Train() {
		this.live_HomePage = new Live_HomePage(driver);	
	    this.live_HomePage.clickSerachInputField();
	}
	
	@When ("User enter valid Train Number as {string}")
	public void enter_and_select_Train (String tNumber) throws InterruptedException  {
		this.live_HomePage = new Live_HomePage(driver);
		this.live_HomePage.enterAndVerifyTrainNum(tNumber);
	}
	
	@When ("User select Your Stop as {string} from dropdown")
	public void select_Your_Stop(String stopName) {
		this.live_HomePage = new Live_HomePage(driver);	
		this.live_HomePage.verifYourStopField(stopName);
	}
	
	@When ("User select Train Start Date as {string} from dropdown")
	public void select_Train_Start_Date(String startDate) {
		this.live_HomePage = new Live_HomePage(driver);
		this.live_HomePage.selectDate(startDate);
	}
	
	@When ("User click on Check Status Button")
	public void click_Check_Status() {
		this.live_HomePage = new Live_HomePage(driver);
		this.live_HomePage.clickCheckStatusButton();
	}
	
	@Then ("User closed ai Popup")
	public void click_ai_Popup() throws InterruptedException {
		 this.live_StatusPage.verifyTrainsPageTitle(driver.getTitle());
		 this.live_StatusPage.verifyTrainPageUrl(driver.getCurrentUrl());
		  
		this.live_StatusPage = new Live_StatusPage(driver);
	    this.live_StatusPage.clickAiPopUp();

    }
}