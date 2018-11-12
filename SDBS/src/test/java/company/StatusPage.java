
package company;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class StatusPage {

    private WebDriver driver;
    WebDriverWait wait;

    String evalCommentText = "Veni vidi vici!";

    public StatusPage ( WebDriver driver ) {
        this.driver = driver;
        wait = new WebDriverWait ( driver , 70 , 100 );
        PageFactory.initElements ( this.driver , this );
    }

    @FindBy(xpath = "//span[contains(text(), 'Set status')]")
    private WebElement        setStatusButton;
    @FindBy(xpath = "//mat-table[@class='mat-table']")
    private WebElement        substratesList;
    @FindBy(xpath = "//span[contains(text(),'General Info')]")
    private WebElement        generalInfo;
    @FindBy(xpath = "//mat-icon[@ng-reflect-message='Customer Raw Data']")
    private WebElement        listOfCustomerRawData;
    @FindBy(xpath = "//app-status//div[@class=\"mat-select-arrow\"]")
    private WebElement        selectStatusField;
    @FindBy(xpath = "//span[contains(text(), 'Customer Raw Data')]")
    private WebElement        customerRawDataStatus;
    //    @FindBy(xpath = "//span[contains(text(), 'Qualified')]")
    @FindBy(xpath = "//div[@class='status-dialog']//div[@class='mat-select-value']")
    private WebElement        statusSelectButton;
    @FindBy(xpath = "//span[@class='mat-option-text']")
    private List <WebElement> options;
    @FindBy(xpath = "//span[contains(text(), 'Verified')]")
    private WebElement        verifiedStatus;
    @FindBy(xpath = "//span[contains(text(), 'Public')]")
    private WebElement        publicStatus;
    @FindBy(xpath = "//mat-select[@ng-reflect-placeholder='Substatus']")
    private WebElement        substatusField;
    @FindBy(xpath = "//span[contains(text(), 'Accepted')]")
    private WebElement        substatusAccepted;
    @FindBy(xpath = "//span[contains(text(), 'Off-limits')]")
    private WebElement        substatusOffLimits;
    @FindBy(xpath = "//textarea[@ng-reflect-message='Evaluation comment']")
    private WebElement        evalCommentTextArea;
    @FindBy(xpath = "//span[contains(text(), 'Ok')]")
    private WebElement        okButton;

    @FindBy(xpath = "//mat-icon[@ng-reflect-message='Qualified']")
    private WebElement checkStatusQualified;

    // @FindBy(xpath = "//mat-select[@aria-label='Qualification status']")
    @FindBy(xpath = "//mat-select[@ng-reflect-placeholder='Qualification status'] ")
    private WebElement statusName;

    @FindBy(xpath = "//app-status//div[1][@class='mat-select-arrow']")
    private WebElement arrowStatus;


    public HomePage setStatus ( String stringStatusName , String subStatusName ) throws InterruptedException {

        wait.until ( ExpectedConditions.visibilityOf ( generalInfo ) );
        generalInfo.click ();
        wait.until ( ExpectedConditions.elementToBeClickable ( setStatusButton ) ).click ();
        statusSelectButton.click ();
        options.stream ().filter ( option -> stringStatusName.equals ( option.getText () ) ).findFirst ().ifPresent ( WebElement::click );
        System.out.println ( statusName.getText () );
//        TODO: check if substatus
        if (subStatusName != null) {
            substatusField.click ();
            options.stream ().filter ( option -> subStatusName.equals ( option.getText () ) ).findFirst ().ifPresent ( WebElement::click );
        }
        wait.until ( ExpectedConditions.visibilityOf ( evalCommentTextArea ) );
        evalCommentTextArea.sendKeys ( evalCommentText );
        evalCommentTextArea.click ();
        wait.until ( ExpectedConditions.elementToBeClickable ( okButton ) ).click ();

        return new HomePage ( this.driver );
    }

    public HomePage setStatusVerifiedAccepted ( ) throws InterruptedException {
        //  Thread.sleep ( 3000 );

        wait.until ( ExpectedConditions.elementToBeClickable ( setStatusButton ) ).click ();
//        setStatusButton.click ();
        wait.until ( ExpectedConditions.textToBePresentInElement ( statusName , "Qualified" ) );
        statusName.click ();
        wait.until ( ExpectedConditions.visibilityOf ( verifiedStatus ) );
        verifiedStatus.click ();
        substatusField.click ();
        substatusAccepted.click ();
        Assert.assertEquals ( statusName.getText () , "Verified" );
        System.out.println ( statusName.getText () );
        evalCommentTextArea.sendKeys ( evalCommentText );
        evalCommentTextArea.click ();
        wait.until ( ExpectedConditions.elementToBeClickable ( okButton ) ).click ();


        return new HomePage ( this.driver );
    }

    public HomePage setStatusPublicOFFlimits ( ) throws InterruptedException {


        wait.until ( ExpectedConditions.visibilityOf ( generalInfo ) );
        wait.until ( ExpectedConditions.textToBePresentInElement ( setStatusButton , "Set status" ) );

        wait.until ( ExpectedConditions.elementToBeClickable ( setStatusButton ) ).click ();
        wait.until ( ExpectedConditions.visibilityOf ( statusName ) );
        statusName.click ();
        publicStatus.click ();
        substatusField.click ();
        substatusOffLimits.click ();
        Assert.assertEquals ( statusName.getText () , "Public" );

        System.out.println ( statusName.getText () );
        evalCommentTextArea.sendKeys ( evalCommentText );
        evalCommentTextArea.click ();
        wait.until ( ExpectedConditions.elementToBeClickable ( okButton ) ).click ();

        return new HomePage ( this.driver );
    }

    public HomePage setStatusVerifiedOFFlimit ( ) throws InterruptedException {

        wait.until ( ExpectedConditions.visibilityOf ( generalInfo ) );
        wait.until ( ExpectedConditions.textToBePresentInElement ( setStatusButton , "Set status" ) );
        Thread.sleep ( 100 );
        wait.until ( ExpectedConditions.elementToBeClickable ( setStatusButton ) ).click ();
//wait.until ( ExpectedConditions.visibilityOf ( statusName ) );
//        wait.until ( ExpectedConditions.elementToBeClickable ( statusName ) );
        Thread.sleep ( 1000 );
        statusName.click ();
        Thread.sleep ( 500 );
        verifiedStatus.click ();
        substatusField.click ();
        substatusAccepted.click ();
        substatusField.click ();
        substatusOffLimits.click ();
        Assert.assertEquals ( statusName.getText () , "Verified" );

        System.out.println ( statusName.getText () );
        evalCommentTextArea.sendKeys ( evalCommentText );
        wait.until ( ExpectedConditions.textToBePresentInElementValue ( evalCommentTextArea , evalCommentText ) );
        //  evalCommentTextArea.click ();
        wait.until ( ExpectedConditions.elementToBeClickable ( okButton ) ).click ();
        return new HomePage ( this.driver );
    }

    public HomePage setStatusPublicAccepted ( ) throws InterruptedException {

        wait.until ( ExpectedConditions.visibilityOf ( generalInfo ) );
        wait.until ( ExpectedConditions.textToBePresentInElement ( setStatusButton , "Set status" ) );
//        Thread.sleep ( 500 );
        wait.until ( ExpectedConditions.elementToBeClickable ( setStatusButton ) ).click ();

        wait.until ( ExpectedConditions.elementToBeClickable ( statusName ) );
//        Thread.sleep ( 3100 );
        statusName.click ();

        publicStatus.click ();


        new Actions ( driver ).doubleClick ( substatusField ).build ().perform ();
        substatusAccepted.click ();
        Assert.assertEquals ( statusName.getText () , "Public" );

        System.out.println ( statusName.getText () );
        evalCommentTextArea.sendKeys ( evalCommentText );
        evalCommentTextArea.click ();
        wait.until ( ExpectedConditions.elementToBeClickable ( okButton ) ).click ();

        return new HomePage ( this.driver );
    }

    public HomePage setStatusRawData ( ) throws InterruptedException {

        wait.until ( ExpectedConditions.visibilityOf ( generalInfo ) );
        wait.until ( ExpectedConditions.textToBePresentInElement ( setStatusButton , "Set status" ) );
        Thread.sleep ( 100 );
        wait.until ( ExpectedConditions.elementToBeClickable ( setStatusButton ) ).click ();
        statusName.click ();

        customerRawDataStatus.click ();
        Assert.assertEquals ( statusName.getText () , "Customer Raw Data" );

        System.out.println ( statusName.getText () );
        evalCommentTextArea.sendKeys ( evalCommentText );
        evalCommentTextArea.click ();
        wait.until ( ExpectedConditions.elementToBeClickable ( okButton ) ).click ();
        return new HomePage ( this.driver );
    }

    public HomePage checkStatus ( ) throws InterruptedException {

        return new HomePage ( this.driver );
    }

    public boolean isOpened ( ) {
        return generalInfo.isDisplayed ();
    }

}

