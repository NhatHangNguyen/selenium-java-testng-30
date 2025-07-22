package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_21_Action_Part_II {

    WebDriver driver;
    Actions action;
    String osName = System.getProperty("os.name");
    Keys keys;

    @BeforeClass
    public void initialBrowser(){

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

        action = new Actions(driver);
        if (osName.startsWith("Windows")){
            keys = Keys.CONTROL;
        } else {
            keys = Keys.COMMAND;
        }

        // keys = osName.startsWith("Windows") ? keys.CONTROL : Keys.COMMAND; (Cách 2 toán tử ba ngôi)
    }

    @Test
    public void TC_04_Click_And_Hold_Block() throws InterruptedException {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
        Assert.assertEquals(allNumber.size(), 20);

        action.clickAndHold(allNumber.get(0)) //Click vào số 1 và giuwx chuột
                .moveToElement(allNumber.get(3)) // Di chuột tới số 4
                .release() // Nhả chuột trái ra (kết thúc cho sự kiện click & hold)
                .perform(); // Thực thi các câu lệnh trên (nếu k có thì k thực thi)

        List<WebElement> allNumberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
        Assert.assertEquals(allNumberSelected.size(),4);
    }

    @Test
    public void TC_05_Click_And_Hold_Random() throws InterruptedException {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
        Assert.assertEquals(allNumber.size(),20);

        // Nhấn phím Ctrl xuống (chưa nhả ra)
        action.keyDown(Keys.CONTROL).perform();

        action.click(allNumber.get(0))
                .click(allNumber.get(3))
                .click(allNumber.get(7))
                .click(allNumber.get(10))
                .click(allNumber.get(13))
                .click(allNumber.get(17))
                .pause(Duration.ofSeconds(3))
                .perform();

        // Nhả phím Ctrl ra
        action.keyUp(Keys.CONTROL).perform();

        List<WebElement> allNumberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
        Assert.assertEquals(allNumberSelected.size(),6);
    }

    @Test
    public void TC_06_Double_Click() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();

        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(),"Hello Automation Guys!");
    }
    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}