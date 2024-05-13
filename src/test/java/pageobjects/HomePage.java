package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage {

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[text()='From Southeast Asia to the World, All Yours.']")
    public WebElement taglineHomepage;
    @FindBy(xpath = "//div[text()='Log In']")
    public WebElement loginButton;
    @FindBy(xpath = "//input[@data-testid='rental-search-form-location-input']")
    public WebElement pickupLocationField;
    @FindBy(xpath = "//div[@data-testid='rental-search-form-location-item']")
    public List<WebElement> pickupLocationItems;
    @FindBy(xpath = "//input[@data-testid='rental-search-form-date-input-start']")
    public WebElement rentalStartDateField;
    @FindBy(xpath = "//input[@data-testid='rental-search-form-date-input-end']")
    public WebElement rentalEndDateField;
    @FindBy(xpath = "//input[@data-testid='rental-search-form-time-input-start']")
    public WebElement rentalStartTimeField;
    @FindBy(xpath = "//input[@data-testid='rental-search-form-time-input-end']")
    public WebElement rentalEndTimeField;
    @FindBy(xpath = "//div[text()='Done']")
    public WebElement doneButton;
    @FindBy(xpath = "//div[@data-testid='rental-search-form-cta']")
    public WebElement searchButton;
}
