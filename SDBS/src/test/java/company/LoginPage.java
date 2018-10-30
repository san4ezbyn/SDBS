package company;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"mat-input-0\"]")
    private static WebElement loginField;
    @FindBy(xpath = "//*[@id=\"mat-input-1\"]")
    private static WebElement passwordField;
    @FindBy(xpath = "//body/app-root//block-ui//app-login//form/div[3]/div[2]/button")
    private static WebElement loginButton;
    @FindBy(xpath = "//button//mat-icon[contains(text(),'settings')]")
    WebElement settings;


    WebDriverWait wait;

    public LoginPage ( WebDriver driver ) {
        this.driver = driver;
        wait = new WebDriverWait ( driver , 60 , 1000 );
        PageFactory.initElements ( this.driver , this );
    }

    public String getPageTitle ( ) {
        return this.driver.getTitle ();
    }

    public GridPage login ( String username , String password, String roleAssert ) {
        loginField.clear ();
        loginField.sendKeys ( username );
        passwordField.clear ();
        passwordField.sendKeys ( password );
        loginButton.click ();

        return new GridPage ( this.driver );
    }

    public boolean settingsPresent ( ) {
        try {
            return settings.isDisplayed ();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
        }
        return false;
    }
}
