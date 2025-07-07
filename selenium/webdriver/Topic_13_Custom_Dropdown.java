package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;


public class Topic_13_Custom_Dropdown {

    WebDriver driver;

    @BeforeClass
    public void initialBrowser(){

        driver = new FirefoxDriver();

        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_(){
        // Hành vi (behavior) để thao tác lên Dropdown
        // 1 - Chờ cho dropdown có thể thao tác lên được (clickable)
        // 2 - Click element nào để nó xổ ra cái dropdown ra
        // 3 - Chờ cho tất cả các items được load ra (presence)
        // 4 - Tìm cho items nào đúng với mong đợi
        // 5-  Click lên item đó
    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}