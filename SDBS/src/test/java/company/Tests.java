package company;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static java.lang.System.setProperty;

public class Tests {

    private static final String URL              = "http://47.254.147.178";
    private static final String NOT_LOGED_IN_URL = "http://47.254.147.178/login";
    private static String username;
    private static String password;
    private static final String LOGIN_TEXT_ADMIN_ASSERT   = "Superadmin SA / ADMIN account_circle";
    private static final String LOGIN_TEXT_QT_ASSERT      = "qt qt / QUALIFICATION_TEAM account_circle";
    private static final String LOGIN_TEXT_SERVICE_ASSERT = "service service / SERVICE account_circle";
    private static final String LOGIN_MATCH               = "Substrate DB";
    private static final String SETTINGS                  = "settings";
    private static WebDriverWait wait;
    private static WebDriver     driver;

    @FindBy(xpath = "//button//div[@class=\"mat-menu-ripple mat-ripple\"]")
    private WebElement logOutButton;
    @FindBy(xpath = "//button[@class='delete-btn mat-raised-button mat-warn ng-star-inserted']")
    private WebElement deleteButton;
    @FindBy(xpath = "//button//mat-icon[contains(text(),'account_circle')]")
    private WebElement avatar;


    private LoginPage               loginPage;
    private GridPage                gridPage;
    private SettingsPage            settingsPage;
    private SettingsAddCustomerPage settingsAddCustomerPage;
    private ImportPage              importPage;
    private StatusPage              statusPage;
    private HomePage                homePage;

    @BeforeMethod
    private void init ( ) {
        setProperty ( "webdriver.chrome.driver" , "C:\\Program Files\\ChromeDriver2\\chromedriver.exe" );
        driver = new ChromeDriver ();
        driver.manage ().window ().maximize ();
        driver.get ( URL );
        driver.manage ().timeouts ().implicitlyWait ( 25 , TimeUnit.SECONDS );
        //dataPage = new DataPage ( driver );
        loginPage = new LoginPage ( driver );
    }


    @Test(testName = "Page Title")
    public void pageTitleMethod ( ) {
        Assert.assertEquals ( LOGIN_MATCH , loginPage.getPageTitle () );
    }


   /* @Test(testName = "LOGIN" ,dataProvider = "loginCreds")
    public void loginAdmin ( String username, String password, String role) throws InterruptedException {
       *//* username = "sa";
        password = "sa";*//*

        gridPage = loginPage.login (username , password, role );
        Assert.assertEquals ( role , gridPage.getCorrectUser () );
        gridPage.gridLogOut ();
    }*/


    @Test(testName = "LOGIN")
    public void loginAdmin ( ) throws InterruptedException {
        username = "sa";
        password = "sa";

        gridPage = loginPage.login ( username , password , LOGIN_TEXT_ADMIN_ASSERT );
        Assert.assertEquals ( LOGIN_TEXT_ADMIN_ASSERT , gridPage.getCorrectUser () );
        gridPage.gridLogOut ();
    }

    @Test(testName = "LOGIN", dependsOnMethods = "loginAdmin")
    public void loginQT ( ) {

        username = "qual";
        password = "pa$$word";
        gridPage = loginPage.login ( username , password , LOGIN_TEXT_QT_ASSERT );

        Assert.assertEquals ( LOGIN_TEXT_QT_ASSERT , gridPage.getCorrectUser () );
        Assert.assertEquals ( SETTINGS , loginPage.settings.getText () );
        System.out.println ( loginPage.settings.getText () );
        Assert.assertTrue ( loginPage.settingsPresent () );
    }

    @Test(testName = "LOGIN"/*, dependsOnMethods = "loginQT"*/)
    public void loginService ( ) throws InterruptedException {

        username = "service";
        password = "pa$$word";
        gridPage = loginPage.login ( username , password , LOGIN_TEXT_SERVICE_ASSERT );
        Assert.assertEquals ( LOGIN_TEXT_SERVICE_ASSERT , gridPage.correctUserService.getText () );
        Assert.assertFalse ( loginPage.settingsPresent () );

    }

    @Test(testName = "LOGIN", dependsOnMethods = "loginService")
    public void loginInvalidLogin ( ) throws InterruptedException {

        username = "pa$$word";
        password = "admin";
        Assert.assertEquals ( NOT_LOGED_IN_URL , driver.getCurrentUrl () );
    }

    @Test(testName = "LOGIN")
    public void loginAsInactiveUser ( ) throws InterruptedException {

        username = "name-TEST";
        password = "pa$$word";
        Assert.assertEquals ( NOT_LOGED_IN_URL , driver.getCurrentUrl () );
    }

    @Test(testName = "User creation")
    public void userCreatiion ( ) throws InterruptedException {

        username = "sa";
        password = "sa";
        gridPage = loginPage.login ( username , password , LOGIN_TEXT_ADMIN_ASSERT );
        settingsPage = gridPage.grid ();
        settingsAddCustomerPage = settingsPage.popupUser ();
        Assert.assertTrue ( settingsPage.createUser () );
    }

    @Test(testName = "Customer creation")
    public void customerCreatiion ( ) throws InterruptedException {

        username = "sa";
        password = "sa";
        gridPage = loginPage.login ( username , password , LOGIN_TEXT_ADMIN_ASSERT );
        settingsPage = gridPage.grid ();
        settingsAddCustomerPage = settingsPage.popupTab ();
        importPage = settingsAddCustomerPage.popupCus ();
        Assert.assertTrue ( settingsAddCustomerPage.createCustomer () );
    }

    @Test(testName = "File Import")
    public void fileImport ( ) throws InterruptedException {

        username = "sa";
        password = "sa";
        gridPage = loginPage.login ( username , password , LOGIN_TEXT_ADMIN_ASSERT );
        settingsPage = gridPage.grid ();
        settingsAddCustomerPage = settingsPage.popupTab ();
        importPage = settingsAddCustomerPage.custMeth ();
        statusPage = importPage.customerSearch ();
        statusPage = importPage.importFile ();
        statusPage = importPage.countHistoryEntries ();
        Assert.assertTrue ( importPage.compareImportEntries () );
    }

   @Test (testName = "Status change")
    public void statusChange () throws InterruptedException {
        username = "sa";
        password = "sa";
        gridPage = loginPage.login ( username , password , LOGIN_TEXT_ADMIN_ASSERT );
        settingsPage = gridPage.grid ();
        settingsAddCustomerPage = settingsPage.popupTab ();
        importPage = settingsAddCustomerPage.custMeth ();

        statusPage = importPage.customerSearch ();
        statusPage = importPage.goToStatusPageMethod ();
//        todo: crate common set status method

        homePage = statusPage.setStatus ("Verified", "Accepted");
//        TODO: return statuses method
//       Assert
        /*homePage = statusPage.setStatusVerifiedAccepted ();
        homePage = statusPage.setStatusPublicOFFlimits ();
       homePage=statusPage.setStatusVerifiedOFFlimit ();
        homePage=statusPage.setStatusPublicAccepted ();
        homePage=statusPage.setStatusRawData ();*/
    }

    @AfterMethod
    public void tearDown ( ) {

        driver.close ();
    }

    @DataProvider(name = "loginCreds")
    public Object[][] credsData ( ) {
        return new Object[][]{
                {"sa" , "sa" , LOGIN_TEXT_ADMIN_ASSERT} ,
                {"qual" , "pa$$word" , LOGIN_TEXT_QT_ASSERT} ,
                {"service" , "pa$$word" , LOGIN_TEXT_SERVICE_ASSERT}
        };
    }
}
