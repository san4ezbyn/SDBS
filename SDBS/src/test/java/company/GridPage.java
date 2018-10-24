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


  @FindBy (xpath = "//div/block-ui/app-header/mat-toolbar/button[2]")
    WebElement correctUser;

    @FindBy (xpath = "//div/block-ui/app-header/mat-toolbar/button")
    WebElement correctUserService;

    @FindBy (xpath = "//button//span[3]/span")
    WebElement roleMenu;

    @FindBy(xpath = "//div[@class='cdk-overlay-container']//button")
    WebElement logOutButton;


    WebDriverWait wait;

    public GridPage ( WebDriver driver ) {
        this.driver = driver;
        wait = new WebDriverWait ( driver , 20 , 100 );
        PageFactory.initElements ( this.driver , this );
    }


       public GridPage grid ( ) throws InterruptedException {
//Thread.sleep ( 2500 );
          wait.until ( ExpectedConditions.elementToBeClickable ( roleMenu ) );
           new Actions ( driver ).doubleClick ( roleMenu ).build ().perform ();
           wait.until ( ExpectedConditions.elementToBeClickable ( logOutButton ) );
           new Actions ( driver ).doubleClick ( logOutButton ).build ().perform ();
        logOutButton.click ();

        return new GridPage ( this.driver );
    }

}
