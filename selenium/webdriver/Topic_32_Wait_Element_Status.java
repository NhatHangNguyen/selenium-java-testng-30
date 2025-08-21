package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_32_Wait_Element_Status {

    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void initialBrowser(){

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Visible(){
        driver.get("https://www.facebook.com/");

        // 1 - Element có trên UI và có trong cây HTML
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
    }

    @Test
    public void TC_02_Invisible(){
        driver.get("https://www.facebook.com/");

        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();

        // Wait cho Email Address textbox được visible
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='reg_email__']")));

        // 2 - Element không có trên UI nhưng vẫn có trong cây HTML
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));

        driver.navigate().back();

        // 3 - Element không có trên UI và không có trong HTML
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));
    }

    @Test
    public void TC_03_Presence(){
        driver.get("https://www.facebook.com/");

        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();

        // Wait cho Email Address textbox được visible
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='reg_email__']")));

        // Điều kiện mồi để cho Confirm Email được xuất hiện có trên UI
        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("hang@gmai.com");

        // 1 - Element có trên UI và có trong cây HTML
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("input[name='reg_email_confirmation__']")));

        // Điều kiện mồi để cho Confirm Email không còn xuất hiện có trên UI
        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();

        // 2 - Element không có trên UI nhưng vẫn có trong cây HTML
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("input[name='reg_email_confirmation__']")));
    }

    @Test
    public void TC_04_Staleness(){
        driver.get("https://www.facebook.com/");

        // Click để mở popup ra
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();

        // Wait cho Email Address textbox được visible
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='reg_email__']")));

        // Tại thời điểm này confirm email đang có mặt trong HTML
        WebElement confirmEmail = driver.findElement(By.cssSelector("input[name='reg_email_confirmation__']"));

        // Back để đóng popup lại
        driver.navigate().back();

        // 3 - Element không có trên UI và không có trong HTML
        explicitWait.until(ExpectedConditions.stalenessOf(confirmEmail));
    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}