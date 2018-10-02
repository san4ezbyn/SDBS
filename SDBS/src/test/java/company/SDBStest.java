/*
package company;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Random;

//import static company.LoginPage.correctUser;
import static java.lang.System.setProperty;

public class SDBStest {

    private static final String URL          = "http://47.254.147.178";
    private static final String username     = "sa";
    private static final String password     = "sa";

    private static final String LOGIN_MATCH  = "Substrate DB";

    private LoginPage           loginPage;

    */
/*@DataProvider (name="loginCreds")
    public Object [][] credsData(){
        Object [][] creds = new Object [1][2];
        creds[0][0] = "sa";
        creds[0][1] = "sa";
        creds[1][0] = "admin";
        creds[1][1] = "pa$$word";
        creds[2][0] = "qual";
        creds[2][1] = "pa$$word";
        creds[3][0] = "service";
        creds[3][1] = "pa$$word";

        return creds;
    }*//*


   */
/* @DataProvider (name="loginInvalidCreds")
    public Object [][] invCredsData(){
        Object [][] creds = new Object [2][2];
        creds[0][0] = "sasa";
        creds[0][1] = "as";
        creds[1][0] = "notActive";
        creds[1][1] = "pa$$word";

        return creds;
    }

    @DataProvider (name="userRoles")
    public Object [] userRolesData(){
        Object [] roles = new Object [3];
        roles[0] = "ADMIN";
        roles[0] = "QUALIFICATION TEAM";
        roles[1]= "SERVICE";

        return roles;
    }*//*


    private static WebDriverWait wait;
    private static WebDriver driver;

    @BeforeClass
    private void init ( ) {
        setProperty ( "webdriver.chrome.driver" , "C:\\Program Files\\ChromeDriver\\chromedriver.exe" );
        driver = new ChromeDriver ();
        driver.manage ().window ().maximize ();
        this.driver.get ( URL );
        loginPage = new LoginPage ( driver );
    }


    @Test(testName = "Page Title")
    public void pageTitleMethod ( ) {
        Assert.assertEquals ( LOGIN_MATCH , loginPage.getPageTitle () );
        System.out.println (driver.getTitle ());
        System.out.println (loginPage.getPageTitle ());
        System.out.println ("Hello");
    }

    @Test(*/
/*dataProvider = "loginCreds"*//*
*/
/*, dependsOnMethods = "pageTitleMethod"*//*
)
    public void loginAdmin (String username, String password ) throws InterruptedException {

        loginPage.login ( username , password );


        */
/*System.out.println (driver.getTitle ());
        System.out.println (correctUser.getText ());*//*

              // Assert.assertEquals ( userRolesData () , correctUser.getText () );
      */
/*  Assert.assertEquals ( "ADMIN" , correctUser.getText () );
        Assert.assertEquals ( "QUALIFICATION TEAM" , correctUser.getText () );
        Assert.assertEquals ( "SERVICE" , correctUser.getText () );*//*

    }

    @Test(*/
/*dataProvider = "loginInvalidCreds"*//*
 */
/*dependsOnMethods = "loginMethod"*//*
)
    public void loginInvMethod (String username, String password ) throws InterruptedException {

        loginPage.login ( "" , "");
        Assert.assertEquals ( LOGIN_MATCH , loginPage.getPageTitle () );

        */
/*inboxPage.waitUserLogoToAppear ();
        System.out.println (inboxPage.userLogoName.getText ());
         Assert.assertEquals ( user , loginPage.correctUser.getText() );
        Assert.assertEquals ( CORRECT_USER , inboxPage.userLogoName.getText () );*//*

    }






@AfterMethod

public void tearDown2 ( ) {
    driver.close ();
}
    @AfterTest
    public void tearDown ( ) {
        driver.close ();
    }

}
*/
