package company;

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
    /*@FindBy(xpath = "//span[@class='mat-button-wrapper']//span[@class='ng-star-inserted']")
    static WebElement correctUser;*/



    WebDriverWait wait;

    public LoginPage ( WebDriver driver ) {
        this.driver = driver;
        wait = new WebDriverWait ( driver , 20 , 500 );
        PageFactory.initElements ( this.driver , this );
    }

    public String getPageTitle ( ) {
        return this.driver.getTitle ();
    }

    public LoginPage login ( String username, String password ) throws InterruptedException {

        loginField.sendKeys ( username );
        passwordField.sendKeys ( password );
        loginButton.click ();
        //correctUser.getText ();

        return new LoginPage ( this.driver );
    }

}
