package company;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SettingsPage {

    private WebDriver driver;
    WebDriverWait wait;


    public SettingsPage ( WebDriver driver ) {
        this.driver = driver;
        wait = new WebDriverWait ( driver , 20 , 100 );
        PageFactory.initElements ( this.driver , this );
    }

    @FindBy(xpath = "//button//mat-icon[contains(text(),'settings')]")
    WebElement settings;

    @FindBy (xpath = "//button/span [contains(text(), \"Add User\")]")
    WebElement addUserButton;

    @FindBy (xpath = "//div/input [@placeholder= \"Login\"]")
    WebElement addUserLoginField;

    @FindBy (xpath = "//div/input [@placeholder= \"First Name\"]")
    WebElement addUserFNameField;

    @FindBy (xpath = "//div/input [@placeholder= \"Last Name\"]")
    WebElement addUserLNameField;

    @FindBy (xpath = "//div/span[contains(text(), \"Role\")]")
    WebElement addUserRoleField;

    @FindBy (xpath = "//div/mat-option/span[contains(text(), \"Admin\")]")
    WebElement addUserRoleAdmin;

    @FindBy (xpath = "//div/mat-option/span[contains(text(), \"Qualification Team\")]")
    WebElement addUserRoleQT;

    @FindBy (xpath = "//div/mat-option/span[contains(text(), \"Service\")]")
    WebElement addUserRoleService;

    @FindBy (xpath = "//div/mat-form-field//input [@placeholder= \"Customer\"]")
    WebElement addUserCustomerField;

    @FindBy (xpath = "//div//span [contains(text(), \"CMYK\")]")
    WebElement addUserCustomerCMYK;

    @FindBy (xpath = "//div/input [@placeholder= \"Email\"]")
    WebElement addUserEmailField;

    @FindBy (xpath = "//button/span [contains(text(), \"Create User\")]")
    WebElement addUserCreateButton;




    public SettingsPage popup (){

settings.click ();
wait.until( ExpectedConditions.elementToBeClickable ( addUserButton )).click ();
addUserLoginField.sendKeys ( "UserLogin" );
addUserFNameField.sendKeys ( "FirstName" );
addUserLNameField.sendKeys ( "LastName" );
addUserRoleField.click ();
addUserRoleAdmin.click ();
addUserCustomerField.click ();
addUserCustomerCMYK.click ();
addUserEmailField.sendKeys ( "user@test.io" );
addUserCreateButton.click ();

        return new SettingsPage ( this.driver );
    }


}
