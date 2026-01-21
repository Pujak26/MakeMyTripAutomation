Feature: To verify trains tab.
Scenario: Verify user can book train tickets.

Given User click on popup
#And User login to application using below credentials
And User select on Trains option
And User click on "Book Train Tickets" option
When User click and enter "Pune" as a source city and select matched city "PUNE"
And User enter "Nagpur" as a destination city and select matched city "NGP" 
And User select the travel date 9 days from today
And User select the travel class as "All Class" 
And User click on search button
And User select AC checkbox from quick filter options
And User select first train from available options having class type "2A" 
And User click on Add Traveller button
And User enter name as "Prisha" in fullName
And User enter age as "10" completeAge
And User select gender as "Female" from dropdown
And User select birth preference as "Lower" from dropdown
And User click on ADD button
And User enter EmailId as "test@gmail.com" and Mobile Number as "8686868686" in contact information 
Then User select checkbox confirm and save billing details to your profile

Scenario: Verify user can check PNR status.

Given User click on close 
#And User login to application using below credentials
And User click on Train
And User click on Check PNR Status button
When User enter valid PNR number in PNR number input field as "8742655436"
And User click on CHECK STATUS button
And User handle the Ai Popup
And User click on See description of all symbols link text
And User click on See Coach Position link text
Then User click on Close Coach Position Popup

Scenario: Verify user can live train status.

Given User select on close icon on popup 
#And User login to application using below credentials
And User click the Trains icon
And User click on live train status button
When User click on search train input field
And User enter valid Train Number as "12136"
And User select Your Stop as "Nagpur" from dropdown
And User select Train Start Date as "Tomorrow" from dropdown
And User click on Check Status Button
Then User closed ai Popup 