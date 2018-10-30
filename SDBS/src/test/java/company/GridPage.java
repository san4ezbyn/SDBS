package company;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.By.xpath;

public class GridPage {

    private WebDriver driver;


    @FindBy(xpath = "//div/block-ui/app-header/mat-toolbar/button[2]")
    private WebElement correctUser;

    @FindBy(xpath = "//div/block-ui/app-header/mat-toolbar/button")
    WebElement correctUserService;

    @FindBy(xpath = "//button//span[3]/span")
    private WebElement roleMenu;

    @FindBy(xpath = "//div[@class='cdk-overlay-container']//button")
    private WebElement logOutButton;


    WebDriverWait wait;

    public GridPage ( WebDriver driver ) {
        this.driver = driver;
        wait = new WebDriverWait ( driver , 20 , 100 );
        PageFactory.initElements ( this.driver , this );
    }


         public GridPage gridLogOut ( ) throws InterruptedException {

           wait.until ( ExpectedConditions.elementToBeClickable ( roleMenu ) );
           new Actions ( driver ).doubleClick ( roleMenu ).build ().perform ();
           wait.until ( ExpectedConditions.elementToBeClickable ( logOutButton ) );
           new Actions ( driver ).doubleClick ( logOutButton ).build ().perform ();

         return new GridPage ( this.driver );
    }

    public SettingsPage grid ( ) {

                return new SettingsPage ( this.driver );
    }

    public String getCorrectUser ( ) {
        return correctUser.getText ();
    }

}
