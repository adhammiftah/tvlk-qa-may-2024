package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FinalizeBookingPage {
    public FinalizeBookingPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h1")
    public WebElement finalizeBookingHeader;
    @FindBy(xpath = "//h2[text()='Contact Details']//following-sibling::div//input[@aria-labelledby='name.full']")
    public WebElement contactDetailsNameField;
    @FindBy(xpath = "//h2[text()='Contact Details']//following-sibling::div//input[@aria-label='Phone Number']")
    public WebElement contactDetailsPhoneNumberField;
    @FindBy(xpath = "//h2[text()='Contact Details']//following-sibling::div//input[@aria-labelledby='emailAddress']")
    public WebElement contactDetailsEmailField;
    @FindBy(xpath = "//h2[text()='Driver Details']/following-sibling::div//div[@aria-labelledby='title']/select")
    public WebElement driverDetailsTitleDropdown;
    @FindBy(xpath = "//h2[text()='Driver Details']/following-sibling::div//input[@aria-labelledby='name.full']")
    public WebElement driverDetailsNameField;
    @FindBy(xpath = "//h2[text()='Driver Details']/following-sibling::div//input[@aria-label='Phone Number']")
    public WebElement driverDetailsPhoneNumberField;
    @FindBy(xpath = "//h2[text()='Rental Requirements']")
    public WebElement rentalRequirementsHeader;
    @FindBy(xpath = "(//div[text()='Tap to check the requirements.'])[1]")
    public WebElement rentalRequirementsButton;
    @FindBy(xpath = "//div[text()='Check All']/preceding-sibling::div/ancestor::div[2]")
    public WebElement rentalRequirementsCheckAllCheckbox;
    @FindBy(xpath = "(//div[text()='Save'])[2]")
    public WebElement rentalRequirementsSaveButton;
    @FindBy(xpath = "(//div[text()='Continue to Payment'])[2]")
    public WebElement continueToPaymentButton;
    @FindBy(xpath = "//h1[text()='Payment']")
    public WebElement paymentHeader;

}