package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_04_Run_On_Browser_Version_3x {

    WebDriver driver;

    @Test
    public void TC_01_Run_On_Firefox(){
        driver = new FirefoxDriver();

        driver.get("http://demo.nopcommerce.com/");

        driver.quit();
    }

    @Test
    public void TC_02_Run_On_Chrome(){
        driver = new ChromeDriver();

        driver.get("http://demo.nopcommerce.com/");

        driver.quit();
    }

    @Test
    public void TC_03_Run_On_Chrome(){
        driver = new EdgeDriver();

        driver.get("http://demo.nopcommerce.com/");

        driver.quit();
    }
}