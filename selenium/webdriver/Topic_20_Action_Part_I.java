package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_20_Action_Part_I {

    WebDriver driver;
    Actions action;

    @BeforeClass
    public void initialBrowser(){

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

        action = new Actions(driver);
    }

    @Test
    public void TC_01_Hover() throws InterruptedException {
        driver.get("https://automationfc.github.io/jquery-tooltip/");

        WebElement ageTextbox = driver.findElement(By.cssSelector("input#age"));

        action.moveToElement(ageTextbox).perform();
        Thread.sleep(2000);

        Assert.assertTrue(driver.findElement(By.cssSelector("div.ui-tooltip-content")).isDisplayed());

        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
    }

    @Test
    public void TC_02_Hover_Myntra() throws InterruptedException {
        driver.get("http://www.myntra.com/");

        action.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-main' and text()='Kids']"))).perform();
        Thread.sleep(2000);

        // Web Element click()
        driver.findElement(By.xpath("//a[@class='desktop-categoryName' and text()='Home & Bath']")).click();

        // Action click()
        //action.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-categoryName' and text()='Home & Bath']"))).perform();

        Assert.assertEquals(driver.findElement(By.cssSelector("span.breadcrumbs-item")).getText(),"Kids Home Bath");
    }

    @Test
    public void TC_03_Hover_Fahasa() throws InterruptedException {
        driver.get("https://www.fahasa.com/");
        Thread.sleep(2000);

        WebElement menuIcon = driver.findElement(By.cssSelector("span.icon_menu"));
        action.moveToElement(menuIcon).perform();
        Thread.sleep(2000);

        driver.findElement(By.xpath("(//span[text()='TÂM LÝ - KĨ NĂNG SỐNG']/ancestor::h3/following-sibling::ul/li/a[text()='Kỹ Năng Sống'])[2]")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("ol.breadcrumb > li.category213 strong")).getText(), "KỸ NĂNG SỐNG");
    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}