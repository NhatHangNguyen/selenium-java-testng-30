package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_00_Template {

    // 1 - Setup: OS/ Browser/ Web/ Page/ Data/ Variable/ Object
    WebDriver driver;

    @BeforeClass
    public void initialBrowser(){

        driver = new ChromeDriver();

        driver.get("https://demo.nopcommerce.com");

    }

    // 2 - Action/ Execute: Tương tác lên element nào/ nhập liệu/ verify/...
    @Test
    public void TC_01_Register(){

    }

    @Test
    public void TC_02_Login(){

    }

    @AfterClass
    // 3 - Clean: Delete data test/ account/ close browser/...
    public void cleanBrowser(){
        driver.quit();
    }
}