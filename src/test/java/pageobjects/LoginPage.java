package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@data-testid='auth-username']")
    public WebElement emailField;

    @FindBy(xpath = "//input[@data-testid='auth-password']")
    public WebElement passwordField;

    @FindBy(xpath = "(//div[@data-testid='auth-form']//div[text()='Log In'])[2]")
    public WebElement submitLoginButton;
}
