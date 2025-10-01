package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_41_Page_Ready {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void initialBrowser(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Example() throws InterruptedException {
        driver.get("https://admin-demo.nopcommerce.com");

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#Email")));

        driver.findElement(By.cssSelector("input#Email")).clear();
        driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");
        driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");

        driver.findElement(By.cssSelector("button.login-button")).click();

        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#ajaxBusy"))));

        driver.findElement(By.xpath("//a[text()='Logout']")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("div.login-page form")).isDisplayed());
    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}