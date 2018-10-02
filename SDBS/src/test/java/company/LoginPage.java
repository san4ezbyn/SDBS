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
    @FindBy(xpath = "//span[@class='mat-button-wrapper']//span[@class='ng-star-inserted']")
    static WebElement correctUser;
    @FindBy(xpath = "//*[@class='mat-menu-item']")
    private static WebElement logOutButton;
    @FindBy (xpath = "//button[@class='delete-btn mat-raised-button mat-warn ng-star-inserted']")
    protected static WebElement deleteButton;


    WebDriverWait wait;

    public LoginPage ( WebDriver driver ) {
        this.driver = driver;
        wait = new WebDriverWait ( driver , 20 , 1000 );
        PageFactory.initElements ( this.driver , this );
    }

    public String getPageTitle ( ) {
        return this.driver.getTitle ();
    }

    public LoginPage login ( String username, String password ) throws InterruptedException {
loginField.clear ();
        loginField.sendKeys ( username );
        passwordField.clear ();
        passwordField.sendKeys ( password );/*  correctUser.click();
        correctUser.getText ();
        logOutButton.click();*/
        loginButton.click ();
        Thread.sleep(1000);




        return new LoginPage ( this.driver );
    }

}
