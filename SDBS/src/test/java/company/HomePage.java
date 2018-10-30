package company;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;




    WebDriverWait wait;

    public HomePage ( WebDriver driver ) {
        this.driver = driver;
        wait = new WebDriverWait ( driver , 60 , 1000 );
        PageFactory.initElements ( this.driver , this );
    }

public HomePage homePage (){

        return new HomePage (this.driver);
}


}
