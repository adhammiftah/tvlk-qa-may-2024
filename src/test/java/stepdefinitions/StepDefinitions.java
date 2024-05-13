package stepdefinitions;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.After;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import pageobjects.*;
import utils.DatePickerUtils;
import utils.FileUtils;
import utils.Waits;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;

public class StepDefinitions {

    private WebDriver driver;
    private Waits waits;
    private HomePage homepage;
    private LoginPage loginPage;
    private CarRentalPage carRentalPage;
    private FinalizeBookingPage finalizeBookingPage;

    public void startDriver(String url) {
        System.setProperty("webdriver.chrome.driver", new File("chromedriver.exe").getPath());
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();

        waits = new Waits(driver);
        homepage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        carRentalPage = new CarRentalPage(driver);
        finalizeBookingPage = new FinalizeBookingPage(driver);
    }

    @After
    public void tearDown(Scenario scenario) throws IOException {

        if (scenario.isFailed()) {
            FileUtils fileUtils = new FileUtils();
            fileUtils.addScreenshot(scenario, driver);
        }

        driver.quit();

    }

    @Given("Open Traveloka website")
    public void openTravelokaWebsite() {
        String url = "https://www.traveloka.com/";

        startDriver(url);

        waits.waitForElement(homepage.taglineHomepage);
        assertThat("Tagline not found", homepage.taglineHomepage.isDisplayed());
    }

    @When("User click on login button")
    public void userClickOnLoginButton() {
        waits.waitForElement(homepage.loginButton);
        homepage.loginButton.click();
    }

    @And("User input email {string}")
    public void userInputEmail(String email) {
        waits.waitForElement(loginPage.emailField);
        loginPage.emailField.sendKeys(email);
    }

    @And("User input password {string}")
    public void userInputPassword(String password) {
        waits.waitForElement(loginPage.passwordField);
        loginPage.passwordField.sendKeys(password);
    }

    @And("User click on submit login button")
    public void userClickOnSubmitLoginButton() {
        waits.waitForElement(loginPage.submitLoginButton);
        loginPage.submitLoginButton.click();
    }

    @Then("User should be logged in to Traveloka")
    public void userShouldBeLoggedInToTraveloka() {
        waits.waitForElement("//h1[text()='Log In Successful!']", 30);
        assertThat("No login successful prompt",
                driver.findElement(By.xpath("//h1[text()='Log In Successful!']")).isDisplayed());
    }

    @Given("User click on {string} service button")
    public void userClickOnServiceButton(String serviceName) throws InterruptedException {
        waits.waitForElement("//div[text()='" + serviceName + "']/ancestor::div[2]", 30);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//div[text()='" + serviceName + "']/ancestor::div[2]")).click();
    }

    @When("User select {string} service option")
    public void userSelectOption(String option) {
        waits.waitForElement("//h4[text()='" + option + "']");
        driver.findElement(By.xpath("//h4[text()='" + option + "']")).click();
    }

    @And("User input {string} as pickup location")
    public void userInputAsPickupLocation(String location) throws InterruptedException {
        waits.waitForElement(homepage.pickupLocationField);
        homepage.pickupLocationField.sendKeys(location);
        Thread.sleep(2000);

        waits.waitForElements(homepage.pickupLocationItems);

        // select the first location from the list
        homepage.pickupLocationItems.get(0).click();
    }

    @And("User input today plus {int} days as rental start date")
    public void userInputTodayPlusDaysAsRentalStartDate(int plusDays) {
        waits.waitForElement(homepage.rentalStartDateField);
        homepage.rentalStartDateField.click();

        String dataTestId = "date-cell-" + DatePickerUtils.addDaysToTodayDate(plusDays);
        driver.findElement(By.xpath("//div[@data-testid='" + dataTestId + "']"));
    }

    @And("User input hour {int} and minute {int}")
    public void userInputHourAndMinuteAsStartTime(int hour, int minute) {
        waits.waitForElement("//div[text()='Hour']/following-sibling::div//div[text()='" + hour + "']");
        driver.findElement(By.xpath("//div[text()='Hour']/following-sibling::div//div[text()='" + hour + "']"))
                .click();

        waits.waitForElement("//div[text()='Minute']/following-sibling::div//div[text()='" + minute + "']");
        driver.findElement(By.xpath("//div[text()='Minute']/following-sibling::div//div[text()='" + minute + "']"))
                .click();

        homepage.doneButton.click();
    }

    @And("User input today plus {int} days as rental end date")
    public void userInputTodayPlusDaysAsRentalEndDate(int plusDays) {
        waits.waitForElement(homepage.rentalEndDateField);
        homepage.rentalEndDateField.click();

        String dataTestId = "date-cell-" + DatePickerUtils.addDaysToTodayDate(plusDays);
        driver.findElement(By.xpath("//div[@data-testid='" + dataTestId + "']"));
    }

    @And("User click on start time field")
    public void userClickOnStartTimeField() {
        waits.waitForElement(homepage.rentalStartTimeField);
        homepage.rentalStartTimeField.click();
    }

    @And("User click on end time field")
    public void userClickOnEndTimeField() {
        waits.waitForElement(homepage.rentalEndTimeField);
        homepage.rentalEndTimeField.click();
    }

    @And("User click on search button")
    public void userClickOnSearchButton() {
        waits.waitForElement(homepage.searchButton);
        homepage.searchButton.click();
    }

    @Then("User should see list of available cars")
    public void userShouldSeeListOfAvailableCars() throws InterruptedException {
        while (carRentalPage.carRentalItems.isEmpty()) {
            Thread.sleep(1000);
        }
        waits.waitForElement(carRentalPage.carRentalItems.get(0));
        assertThat("No car items", !carRentalPage.carRentalItems.isEmpty());
    }

    @Given("User select the first car")
    public void userSelectTheFirstCar() {
        waits.waitForElement(carRentalPage.continueButtons.get(0));
        carRentalPage.continueButtons.get(0).click();
    }

    @When("User select the first provider")
    public void userSelectTheFirstProvider() throws InterruptedException {
        Thread.sleep(300);
        waits.waitForElement(carRentalPage.continueButtons.get(0));
        carRentalPage.continueButtons.get(0).click();
    }


    @And("User select the first rental office as pickup location")
    public void userSelectTheFirstAsPickupLocation() throws InterruptedException {
        Thread.sleep(1000);
        waits.waitForElement(carRentalPage.pickupRentalOfficeRadioButton);
        carRentalPage.pickupRentalOfficeRadioButton.click();

        waits.waitForElement(carRentalPage.pickupRentalOfficeDropdown);
        carRentalPage.pickupRentalOfficeDropdown.click();

        waits.waitForElements(carRentalPage.pickupRentalOfficeItems);
        carRentalPage.pickupRentalOfficeItems.get(0).click();
    }

    @And("User select the first other location as drop-off location")
    public void userSelectTheFirstAsDropOffLocation() throws InterruptedException {
        Thread.sleep(1000);
        waits.waitForElement(carRentalPage.dropOffOtherLocationsRadioButton);
        carRentalPage.dropOffOtherLocationsRadioButton.click();

        waits.waitForElement(carRentalPage.dropOffOtherLocationsField);
        carRentalPage.dropOffOtherLocationsField.click();

        waits.waitForElements(carRentalPage.dropOffOtherLocationsPopularLocationItems);
        carRentalPage.dropOffOtherLocationsPopularLocationItems.get(0).click();
    }

    @And("User insert {string} as additional notes")
    public void userInsertAsAdditionalNotes(String notes) {
        waits.waitForElement(carRentalPage.additionalNotesField);
        carRentalPage.additionalNotesField.sendKeys(notes);
    }

    @And("User click on the bottom continue button")
    public void userClickOnTheBottomContinueButton() throws InterruptedException {
        Thread.sleep(1000);
        waits.waitForElement("(//div[text()='Continue'])[2]");
        driver.findElement(By.xpath("(//div[text()='Continue'])[2]")).click();
    }

    @Then("User should see the booking details form page")
    public void userShouldSeeTheBookingDetailsFormPage() {
        waits.waitForElement(finalizeBookingPage.finalizeBookingHeader);
        assertThat("No booking review title", finalizeBookingPage.finalizeBookingHeader.isDisplayed());
    }

    @When("User input {string} as contact details name")
    public void userInputAsContactDetailsName(String name) {
        waits.waitForElement(finalizeBookingPage.contactDetailsNameField);
        finalizeBookingPage.contactDetailsNameField.sendKeys(name);
    }

    @And("User input {string} as contact details phone number")
    public void userInputAsContactDetailsPhoneNumber(String number) {
        waits.waitForElement(finalizeBookingPage.contactDetailsPhoneNumberField);
        finalizeBookingPage.contactDetailsPhoneNumberField.sendKeys(number);
    }

    @And("User input {string} as contact details email")
    public void userInputAsContactDetailsEmail(String email) {
        waits.waitForElement(finalizeBookingPage.contactDetailsEmailField);
        finalizeBookingPage.contactDetailsEmailField.sendKeys(email);
    }

    @And("User select {string} as driver title")
    public void userSelectAsDriverTitle(String title) throws InterruptedException {
        Thread.sleep(300);
        finalizeBookingPage.driverDetailsTitleDropdown.click();
        Select select = new Select(finalizeBookingPage.driverDetailsTitleDropdown);
        select.selectByValue(title);
    }

    @And("User input {string} as driver name")
    public void userInputAsDriverName(String name) {
        waits.waitForElement(finalizeBookingPage.driverDetailsNameField);
        finalizeBookingPage.driverDetailsNameField.sendKeys(name);
    }

    @And("User input {string} as driver phone number")
    public void userInputAsDriverPhoneNumber(String number) {
        waits.waitForElement(finalizeBookingPage.driverDetailsPhoneNumberField);
        finalizeBookingPage.driverDetailsPhoneNumberField.sendKeys(number);
    }

    @Then("User should see the booking review page")
    public void userShouldSeeTheBookingReviewPage() {
        waits.waitForElement(finalizeBookingPage.rentalRequirementsHeader);
        assertThat("No rental requirements header", finalizeBookingPage.rentalRequirementsHeader.isDisplayed());
    }

    @When("User input {string} as special request")
    public void userInputAsSpecialRequest(String request) {
        waits.waitForElement("//textarea");
        driver.findElement(By.xpath("//textarea")).sendKeys(request);
    }

    @And("User open the rental requirements section")
    public void userOpenTheRentalRequirementsSection() {
        waits.waitForElement(finalizeBookingPage.rentalRequirementsButton);
        finalizeBookingPage.rentalRequirementsButton.click();
    }

    @And("User checks the check all checkbox")
    public void userChecksTheCheckAllCheckbox() throws InterruptedException {
        Thread.sleep(300);
        waits.waitForElement(finalizeBookingPage.rentalRequirementsCheckAllCheckbox);
        finalizeBookingPage.rentalRequirementsCheckAllCheckbox.click();
    }

    @And("User click save button")
    public void userClickSaveButton() throws InterruptedException {
        Thread.sleep(300);
        waits.waitForElement(finalizeBookingPage.rentalRequirementsSaveButton);
        finalizeBookingPage.rentalRequirementsSaveButton.click();
    }

    @And("User click continue to payment button")
    public void userClickContinueToPaymentButton() {
        waits.waitForElement(finalizeBookingPage.continueToPaymentButton);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", finalizeBookingPage.continueToPaymentButton);
    }

    @Then("User should see the payment page")
    public void userShouldSeeThePaymentPage() {
        waits.waitForElement(finalizeBookingPage.paymentHeader);
        assertThat("No payment header", finalizeBookingPage.paymentHeader.isDisplayed());
    }

    @When("User choose {string} as payment method")
    public void userChooseAsPaymentMethod(String method) {
        waits.waitForElement("//div[text()='" + method + "']");
        driver.findElement(By.xpath("//div[text()='" + method + "']")).click();
    }

    @And("User click on pay with {string} button")
    public void userClickOnPayWithButton(String method) {
        waits.waitForElement("//div[text()='Pay with " + method + "']");
        driver.findElement(By.xpath("//div[text()='Pay with " + method + "']")).click();
    }

    @Then("User should see the payment confirmation page")
    public void userShouldSeeThePaymentConfirmationPage() {
        waits.waitForElement("//h2[text()='Make a Payment Before']");
        assertThat("No payment confirmation header",
                driver.findElement(By.xpath("//h2[text()='Make a Payment Before']")).isDisplayed());
    }
}
