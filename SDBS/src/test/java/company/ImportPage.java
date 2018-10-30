package company;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ImportPage {


    private WebDriver driver;
    WebDriverWait wait;


    public ImportPage ( WebDriver driver ) {
        this.driver = driver;
        wait = new WebDriverWait ( driver , 20 , 60 );
        PageFactory.initElements ( this.driver , this );

    }

    String autoCustomer = "cus";

    @FindBy(xpath = "//button[@ng-reflect-message='Import']")
    private WebElement importButton;
    @FindBy (xpath = "//*[@id='import-input']")
    private WebElement chooseFileForImport;
    @FindBy(xpath = "//app-import//input[@placeholder='Customer']")
    private WebElement chooseCustomerForImport;
@FindBy (xpath = "//div[@class='import-button']")
private WebElement importFileButton;
@FindBy (xpath = "//input[@class='import-input']")
private WebElement fileInput;
@FindBy (xpath = "//span[contains(text(),'cus')]")
private WebElement selectLsnFromList;


    public HomePage importFile () throws InterruptedException {
wait.until(ExpectedConditions.visibilityOfAllElements ( importButton ));
wait.until ( ExpectedConditions.elementToBeClickable ( importButton ) );

       Thread.sleep ( 1000 );
       importButton.click ();
       chooseFileForImport.click ();
        wait.until ( ExpectedConditions.visibilityOfAllElements ( fileInput ) );
       fileInput.sendKeys ( "C:/1/all_params(origin).zip" );
       chooseCustomerForImport.click ();
       chooseCustomerForImport.sendKeys ( autoCustomer);
       selectLsnFromList.click ();
       wait.until ( ExpectedConditions.visibilityOfAllElements ( importFileButton));
       importFileButton.click ();

        Thread.sleep ( 3000 );

        return new HomePage ( this.driver );
    }



}













