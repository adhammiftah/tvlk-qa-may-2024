# Written by Adham Miftah for Traveloka Take Home Test as QA Engineer
# May 2024
@CarRentalFeature
Feature: Car Rental feature

  Background:
    Given Open Traveloka website
    When User click on login button
    # email must already been registered
    And User input email "example@mail.com"
    And User input password "password1234"
    And User click on submit login button
    Then User should be logged in to Traveloka

  Scenario: User can rent a car without driver
    Given User click on "Car Rental" service button
    When User select "Without Driver" service option
    And User input "Jakarta" as pickup location
    And User input today plus 2 days as rental start date
    And User click on start time field
    And User input hour 9 and minute 0
    And User input today plus 5 days as rental end date
    And User click on end time field
    And User input hour 11 and minute 0
    And User click on search button
    Then User should see list of available cars
    Given User select the first car
    When User select the first provider
    And User select the first rental office as pickup location
    And User select the first other location as drop-off location
    And User insert "Test message" as additional notes
    And User click on the bottom continue button
    Then User should see the booking details form page
    When User input "Test Name" as contact details name
    And User input "081234567890" as contact details phone number
    And User input "test@email.com" as contact details email
    And User select "MR" as driver title
    And User input "Test Name" as driver name
    And User input "081234567890" as driver phone number
    And User click on the bottom continue button
    Then User should see the booking review page
    When User input "Test request" as special request
    And User open the rental requirements section
    And User checks the check all checkbox
    And User click save button
    And User click continue to payment button
    And User click on the bottom continue button
    Then User should see the payment page
    When User choose "ATM/Other Banks" as payment method
    And User click on pay with "ATM/Other Banks" button
    Then User should see the payment confirmation page