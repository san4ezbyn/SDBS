package company;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SettingsPage  {

    private WebDriver driver;
    WebDriverWait wait;


    public SettingsPage ( WebDriver driver ) {
        this.driver = driver;
        wait = new WebDriverWait ( driver , 25 , 60 );
        PageFactory.initElements ( this.driver , this );
    }

    @FindBy(xpath = "//button//mat-icon[contains(text(),'settings')]")
    private WebElement settings;
    @FindBy (xpath = "//button/span [contains(text(), \"Add User\")]")
    private WebElement addUserButton;
    @FindBy (xpath = "//div/input [@placeholder= \"Login\"]")
    private WebElement addUserLoginField;
    @FindBy (xpath = "//div/input [@placeholder= \"First Name\"]")
    private WebElement addUserFNameField;
    @FindBy (xpath = "//div/input [@placeholder= \"Last Name\"]")
    private WebElement addUserLNameField;
    @FindBy (xpath = "//div/span[contains(text(), \"Role\")]")
    private WebElement addUserRoleField;
    @FindBy (xpath = "//div/mat-option/span[contains(text(), \"Admin\")]")
    private WebElement addUserRoleAdmin;
    @FindBy (xpath = "//div/mat-option/span[contains(text(), \"Qualification Team\")]")
    private WebElement addUserRoleQT;
    @FindBy (xpath = "//div/mat-option/span[contains(text(), \"Service\")]")
    private WebElement addUserRoleService;
    @FindBy (xpath = "//div/mat-form-field//input [@placeholder= \"Customer\"]")
    private WebElement addUserCustomerField;
    @FindBy (xpath = "//div//span [contains(text(), \"CMYK\")]")
    private WebElement addUserCustomerCMYK;
    @FindBy (xpath = "//div/input [@placeholder= \"Email\"]")
    private WebElement addUserEmailField;
    @FindBy (xpath = "//button/span [contains(text(), \"Create User\")]")
    private WebElement addUserCreateButton;
    @FindBy (xpath = "//mat-cell[contains(text(), 'UserLogin')]")
    private WebElement userHasBeenCreated;

    @FindBy (xpath = "//div[@class='table-container']//mat-table[@role='grid']")
    private WebElement userList;

    String userLogin = "UserLogin";


    public SettingsAddCustomerPage popupUser () throws InterruptedException {

        Thread.sleep ( 3000 );

        wait.until ( ExpectedConditions.elementToBeClickable ( settings ) ).click ();


        addUserButton.click ();
        addUserLoginField.sendKeys ( userLogin );
        addUserFNameField.sendKeys ( "FirstName" );
        addUserLNameField.sendKeys ( "LastName" );
        addUserRoleField.click ();
        addUserRoleAdmin.click ();
        addUserCustomerField.click ();
        addUserCustomerCMYK.click ();
        addUserEmailField.sendKeys ( "user@test.io" );
        addUserCreateButton.click ();


        return new SettingsAddCustomerPage ( this.driver );

    }
        public SettingsAddCustomerPage popupTab () throws InterruptedException {

            return new SettingsAddCustomerPage ( this.driver );
    }

    public boolean createUser () throws InterruptedException {
        Thread.sleep ( 1000 );
        List<WebElement> userNames;
        userNames = driver.findElements( By.xpath("//div[@class='table-container']//mat-table[@role='grid']"));
        Boolean          userNameIsPresent = false;
        for (WebElement user : userNames) {
            if (user.getText ().contains ( userLogin )) {

                userNameIsPresent = true;
                break;
            }
        }
        return userNameIsPresent;
    }
}
