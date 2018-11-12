package company;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ImportPage {

    private WebDriver driver;
    WebDriverWait wait;

    int count;
    int count2;

    public ImportPage ( WebDriver driver ) {
        this.driver = driver;
        wait = new WebDriverWait ( driver , 20 , 60 );
        PageFactory.initElements ( this.driver , this );
    }

    String autoCustomer = "cus";

    @FindBy(xpath = "//button[@ng-reflect-message='Import']")
    private WebElement importButton;
    @FindBy(xpath = "//*[@id='import-input']")
    private WebElement chooseFileForImport;
    @FindBy(xpath = "//app-import//input[@placeholder='Customer']")
    private WebElement chooseCustomerForImport;
    @FindBy(xpath = "//div[@class='import-button']")
    private WebElement importFileButton;
    @FindBy(xpath = "//input[@class='import-input']")
    private WebElement fileInput;
    @FindBy(xpath = "//span[contains(text(),'cus')]")
    private WebElement selectLsnFromList;
    @FindBy(xpath = "//span[contains(text(), 'History')]")
    private WebElement uiHistory;
    @FindBy(xpath = "//div[@class='mat-expansion-panel-content ng-trigger ng-trigger-bodyExpansion mat-expanded']")
    private WebElement historyList;
    @FindBy(xpath = "//input[@placeholder='Customer']")
    private WebElement filterCustomer;
    @FindBy(xpath = "//button[@class='search-button mat-icon-button']")
    private WebElement filterSearch;

    public StatusPage importFile ( ) throws InterruptedException {

        wait.until ( ExpectedConditions.visibilityOf ( importButton ) );
        wait.until ( ExpectedConditions.elementToBeClickable ( importButton ) );

        // Thread.sleep ( 1000 );
        importButton.click ();
        chooseFileForImport.click ();
        wait.until ( ExpectedConditions.visibilityOfAllElements ( fileInput ) );
        fileInput.sendKeys ( "C:/1/all_params(origin).zip" );
//        chooseCustomerForImport.click ();
        chooseCustomerForImport.sendKeys ( autoCustomer );
        selectLsnFromList.click ();
        wait.until ( ExpectedConditions.visibilityOfAllElements ( importFileButton ) );
        importFileButton.click ();
        Thread.sleep ( 2000 );

        return new StatusPage ( this.driver );
    }

    public StatusPage customerSearch ( ) throws InterruptedException {
        wait.until ( ExpectedConditions.visibilityOf ( filterCustomer ) ).sendKeys ( autoCustomer );

//        filterCustomer.sendKeys ( autoCustomer );

       Thread.sleep ( 3000 );

        wait.until ( ExpectedConditions.elementToBeClickable ( filterSearch ) ).click ();

//        Thread.sleep ( 5000 );
         wait.until (ExpectedConditions.elementToBeClickable ( uiHistory ));
        uiHistory.click ();
        count = getSizeHistoryEntries ();
        System.out.println ( count );
        StatusPage statusPage = new StatusPage ( this.driver );
//        Thread.sleep ( 500 );
        wait.until ( waitForStatusPageIsOpened ( statusPage ) );
        return statusPage;
    }

    public StatusPage countHistoryEntries ( ) throws InterruptedException {


        // wait.until (ExpectedConditions.not(ExpectedConditions.visibilityOf ( chooseFileForImport )));
        wait.until ( ExpectedConditions.elementToBeClickable ( uiHistory ) );
        wait.until ( ExpectedConditions.visibilityOf ( uiHistory ) );
        Thread.sleep ( 7000 );
        uiHistory.click ();
        count2 = getSizeHistoryEntries ();
        System.out.println ( count2 );
        return new StatusPage ( this.driver );

    }

    public boolean compareImportEntries ( ) {
        boolean importHistoryEntries;
        if (count2 > count) {
            return true;
        }
        else return false;
    }

    public int getSizeHistoryEntries ( ) {
        int num = driver.findElements ( By.xpath ( "//span[contains(text(), 'import by sa')]" ) ).size ();
        return num;
    }

    public StatusPage goToStatusPageMethod ( ) {

        return new StatusPage ( this.driver );
    }

    private ExpectedCondition <Boolean> waitForStatusPageIsOpened ( StatusPage statusPage ) {
        return new ExpectedCondition <Boolean> () {
            public Boolean apply ( WebDriver driver ) {
                return statusPage.isOpened ();
            }

            public String toString ( ) {
                return "status page is opened: " + statusPage.isOpened ();
            }
        };
    }
}















