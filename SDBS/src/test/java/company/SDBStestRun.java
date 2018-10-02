package company;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

//import static company.LoginPage.correctUser;
import static java.lang.System.setProperty;

public class SDBStestRun {

    private static final String URL          = "http://47.254.147.178";
    /*private static final String username     = "sa";
    private static final String password     = "sa";*/

    private static final String LOGIN_MATCH  = "Substrate DB";

    private LoginPage           loginPage;



    private static WebDriverWait wait;
    private static WebDriver driver;

    @BeforeClass
    private void init ( ) {
        setProperty ( "webdriver.chrome.driver" , "C:\\Program Files\\ChromeDriver2\\chromedriver.exe" );
        driver = new ChromeDriver ();
        driver.manage ().window ().maximize ();
        this.driver.get ( URL );
        driver.manage ().timeouts ().implicitlyWait ( 25 , TimeUnit.SECONDS );
        loginPage = new LoginPage ( driver );
    }




    @Test(testName = "Page Title")
    public void pageTitleMethod ( ) {
        Assert.assertEquals ( LOGIN_MATCH , loginPage.getPageTitle () );
        System.out.println (driver.getTitle ());
        System.out.println (loginPage.getPageTitle ());

    }

    @Test(/*dependsOnMethods = "pageTitleMethod"*/)
    public void loginAdmin ( ) throws InterruptedException {
String username     = "sa";
     String password     = "sa";
        loginPage.login ( username , password );
        //System.out.println (LoginPage.deleteButton.getText());
       // Assert.assertEquals ("DELETE", LoginPage.deleteButton.getText());


        /*WebElement compareAdmin = driver.findElement ( By.xpath ( "//mat-toolbar/button[2]" ) );
        String     compareA     = compareAdmin.getText ();
        Assert.assertEquals ( compareA, "Andrey Kamandzenka / ADMIN account_circle" );
        System.out.println(compareAdmin.getText ());*/

        
   }
    @Test(/*dependsOnMethods = "pageTitleMethod"*/)
    public void loginQT ( ) throws InterruptedException {
        driver.get(URL);
        String username = "qual";
        String password = "pa$$word";
        loginPage.login ( username , password );

    }

    @Test(/*dependsOnMethods = "pageTitleMethod"*/)
    public void loginService ( ) throws InterruptedException {
        driver.get(URL);
        String username = "service";
        String password = "pa$$word";
        loginPage.login ( username , password );

    }




    @AfterTest
    public void tearDown ( ) {
        driver.close ();
    }

}
