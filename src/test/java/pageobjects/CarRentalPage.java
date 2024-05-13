package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CarRentalPage {
    public CarRentalPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[text()='Continue']/ancestor::div[7]")
    public List<WebElement> carRentalItems;
    @FindBy(xpath = "//div[text()='Continue' and @aria-hidden='true']")
    public List<WebElement> continueButtons;
    @FindBy(xpath = "//div[@id='RENTAL_PICKUP_LOCATION']//div[text()='Rental Office']")
    public WebElement pickupRentalOfficeRadioButton;
    @FindBy(xpath = "//h4[text()='Pick-up Location']/following-sibling::div[1]")
    public WebElement pickupRentalOfficeDropdown;
    @FindBy(xpath = "//h4[text()='Pick-up Location']/following-sibling::div/div[2]/div/div")
    public List<WebElement> pickupRentalOfficeItems;
    @FindBy(xpath = "//div[@id='RENTAL_DROPOFF_LOCATION']//div[text()='Other Locations']")
    public WebElement dropOffOtherLocationsRadioButton;
    @FindBy(xpath = "//div[@id='RENTAL_DROPOFF_LOCATION']//input")
    public WebElement dropOffOtherLocationsField;
    @FindBy(xpath = "//div[@id='RENTAL_DROPOFF_LOCATION']//div[@aria-label='Popular Locations']/following-sibling::div")
    public List<WebElement> dropOffOtherLocationsPopularLocationItems;
    @FindBy(xpath = "//textarea[@placeholder='Additional notes (optional)']")
    public WebElement additionalNotesField;

}
