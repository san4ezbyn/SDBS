package company;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;
import static java.lang.System.setOut;
import static java.lang.System.setProperty;

public class Tests {

    private static final String URL  = "http://47.254.147.178";
    private static final String NOT_LOGED_IN_URL = "http://47.254.147.178/login";
    private static String username;
    private static String password;
    private static final String LOGIN_TEXT_ADMIN_ASSERT = "Superadmin SA / ADMIN account_circle";
    private static final String LOGIN_TEXT_QT_ASSERT = "qt qt / QUALIFICATION_TEAM account_circle";
    private static final String LOGIN_TEXT_SERVICE_ASSERT = "service service / SERVICE account_circle";
    private static final String LOGIN_MATCH  = "Substrate DB";
    private static final String SETTINGS = "settings";
    private static WebDriverWait wait;
    private static WebDriver driver;

    @FindBy(xpath = "//button//div[@class=\"mat-menu-ripple mat-ripple\"]")
    private WebElement logOutButton;
    @FindBy (xpath = "//button[@class='delete-btn mat-raised-button mat-warn ng-star-inserted']")
    private WebElement deleteButton;
    @FindBy (xpath = "//button//mat-icon[contains(text(),'account_circle')]")
    private WebElement avatar;




    private LoginPage loginPage;
    private GridPage gridPage;
    private SettingsPage settingsPage;

   @BeforeMethod
    private void init ( ) {
        setProperty ( "webdriver.chrome.driver" , "C:\\Program Files\\ChromeDriver2\\chromedriver.exe" );
        driver = new ChromeDriver ();
        driver.manage ().window ().maximize ();
        this.driver.get ( URL );
        driver.manage ().timeouts ().implicitlyWait ( 25 , TimeUnit.SECONDS );
        //dataPage = new DataPage ( driver );
        loginPage = new LoginPage ( driver );
    }



    @Test(testName = "Page Title")
    public void pageTitleMethod ( ) {
        Assert.assertEquals ( LOGIN_MATCH , loginPage.getPageTitle () );
    }

    @Test(testName = "LOGIN")
    public void loginAdmin (/*String login, String password*/ ) throws InterruptedException {
        username ="sa";
        password="sa";
        gridPage=loginPage.login ( username , password );

       Assert.assertEquals (LOGIN_TEXT_ADMIN_ASSERT, gridPage.correctUser.getText() );
       Assert.assertEquals(SETTINGS, loginPage.settings.getText());
       Assert.assertTrue (loginPage.settingsPresent ());
       gridPage.grid ();

    }
    @Test (testName = "LOGIN", dependsOnMethods = "loginAdmin")
    public void loginQT ( ) throws InterruptedException {

        username ="qual";
        password="pa$$word";
        gridPage=loginPage.login ( username , password );

        Assert.assertEquals (LOGIN_TEXT_QT_ASSERT, gridPage.correctUser.getText() );
        Assert.assertEquals(SETTINGS, loginPage.settings.getText());
        System.out.println (loginPage.settings.getText ());
        Assert.assertTrue (loginPage.settingsPresent ());
    }

    @Test (testName = "LOGIN"/*, dependsOnMethods = "loginQT"*/)
    public void loginService ( ) throws InterruptedException {

        username ="service";
        password="pa$$word";
        gridPage=loginPage.login ( username , password );
        Assert.assertEquals (LOGIN_TEXT_SERVICE_ASSERT, gridPage.correctUserService.getText() );
        System.out.println (gridPage.correctUserService.getText ());
        Assert.assertFalse ( loginPage.settingsPresent () );

    }

    @Test(testName = "LOGIN", dependsOnMethods = "loginService")
    public void loginInvalidLogin ( ) throws InterruptedException {

        username ="pa$$word";
        password="admin";
        Assert.assertEquals (NOT_LOGED_IN_URL, driver.getCurrentUrl());
    }

    @Test (testName = "LOGIN")
    public void loginAsInactiveUser ( ) throws InterruptedException {

        username ="name-TEST";
        password="pa$$word";
        Assert.assertEquals (NOT_LOGED_IN_URL, driver.getCurrentUrl());
    }

    @Test (testName = "User creation")
    public void userCreatiion () throws InterruptedException {
loginAdmin ();
Thread.sleep ( 3000 );
settingsPage.popup ();



    }


    @AfterMethod
    public void tearDown ( ) {
        driver.close ();
    }

    @DataProvider (name="loginCreds")
    public Object [][] credsData(){
        return new Object [][] {
                {"sa","sa"},
                {"qual","pa$$word"},
                {"service", "pa$$word"}
        };
    }
}
