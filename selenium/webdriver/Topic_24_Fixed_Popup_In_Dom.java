package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_24_Fixed_Popup_In_Dom {

    WebDriver driver;

    @BeforeClass
    public void initialBrowser(){

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_NgoaiNgu24h() throws InterruptedException {
        driver.get("https://ngoaingu24h.vn/");

        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        Thread.sleep(3000);

        By loginPopup = By.cssSelector("div#custom-dialog>div.MuiDialog-container div.MuiPaper-root");

        // Kiểm tra 1 element hiện thị có trong HTML
        // Hiện thị trên UI -> true
        // Không hiện thị trên UI -> false
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

        driver.findElement(By.cssSelector("input[autocomplete='username']")).sendKeys("automationfc");
        driver.findElement(By.cssSelector("input[autocomplete='new-password']")).sendKeys("automationfc");
        Thread.sleep(3000);

        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", loginButton);

        //Assert.assertTrue(driver.findElement(By.cssSelector("div#notistack-snackbar.Snackbarltem-message")).isDisplayed());

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("https://ngoaingu24h.vn/?"));

        Assert.assertTrue(driver.getCurrentUrl().contains("https://ngoaingu24h.vn/?"));
    }

    @Test
    public void TC_02_Kyna() throws InterruptedException {
        driver.get("https://skills.kynaenglish.vn/dang-nhap");

        By loginPopup = By.cssSelector("div#k-popup-account-login-mb>div.modal-dialog");

        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

        driver.findElement(By.cssSelector("input#user-login")).sendKeys("automationfc");
        driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#btn-submit-login")).click();
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(),"Sai tên đăng nhập hoặc mật khẩu");
    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}