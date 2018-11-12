package company;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SettingsAddCustomerPage {


        private WebDriver driver;
        WebDriverWait wait;


        public SettingsAddCustomerPage ( WebDriver driver ) {
            this.driver = driver;
            wait = new WebDriverWait ( driver , 20 , 60 );
            PageFactory.initElements ( this.driver , this );
        }

        @FindBy(xpath = "//button//mat-icon[contains(text(),'settings')]")
        private WebElement settings;
        @FindBy (xpath = "//div[contains(text(), 'Master Data: Customers')]")
        private WebElement masterDataCustomersTab;
        @FindBy (xpath = "//span[contains(text(), 'Add Customer')]")
        private WebElement addCustomerButton;
        @FindBy (xpath = "//div/input[@placeholder='Site ID']")
        private WebElement addCustomerSiteIdField;
        @FindBy (xpath = "//div/input[@placeholder='Name']")
        private WebElement addCustomerNameField;
        @FindBy (xpath = "//div/input[@placeholder='LSN']")
        private WebElement addCustomerLSNField;
        @FindBy (xpath = "//div/span[contains(text(),'Country')]")
        private WebElement addCustomerCountryField;
        @FindBy (xpath = "//mat-option/span[contains(text(),'Australia')]")
        private WebElement addCustomerSelectCountry;
        @FindBy (xpath = "//button/span[contains(text(),'Create customer')]")
        private WebElement addCustomerCreateButton;
        @FindBy (xpath = "//mat-cell[contains(text(), 'cusLSN')]")
        private WebElement customerHasBeenCreated;
        @FindBy (xpath = "//div[@class='table-container']//mat-table[@role='grid']")
        private WebElement listOfCustomers;


        String customerLSN = "cusLSN";


        public ImportPage popupCus () throws InterruptedException {

            Thread.sleep ( 3000 );

            settings.click ();
            wait.until( ExpectedConditions.elementToBeClickable ( masterDataCustomersTab )).click ();
            Thread.sleep ( 3000 );
           wait.until (ExpectedConditions.elementToBeClickable ( addCustomerButton)).click ();
            addCustomerSiteIdField.sendKeys ( "Test_Site_ID" );
            addCustomerNameField.sendKeys ( "Test_Name" );
            addCustomerLSNField.sendKeys ( customerLSN );
            addCustomerCountryField.click ();
            addCustomerSelectCountry.click ();
            addCustomerCreateButton.click ();

            return new ImportPage ( this.driver );
        }

        public boolean createCustomer() throws InterruptedException {
           Thread.sleep ( 300 );

            List<WebElement> customerNames;
            customerNames = driver.findElements( By.xpath("//div[@class='table-container']//mat-table[@role='grid']"));
             Boolean          customerNameIsPresent = false;
            for (WebElement customer : customerNames) {
                if (customer.getText ().contains ( customerLSN )) {
                    customerNameIsPresent = true;
                    break;
                                   }
            }
            return customerNameIsPresent;
        }

        public ImportPage custMeth (){
            return new ImportPage ( this.driver );
        }
    }






