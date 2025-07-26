package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_25_Fixed_Popup_Not_In_Dom {

    WebDriver driver;

    @BeforeClass
    public void initialBrowser(){

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_03_Tiki() throws InterruptedException {
        driver.get("https://tiki.vn/");
        Thread.sleep(3000);

        // Popup 01 - Marketing
        // Hiện thị cố định khi vừa mới mở site
        // Khi đóng lại thì không còn trong HTML nữa
        // Khi page thì lại hiện ra

        By marketingPopup = By.cssSelector("div#VIP_BUNDLE");

        // Hiện thị cố định khi vừa mới mở site
        Assert.assertEquals(driver.findElements(marketingPopup).size(),1);

        driver.findElement(By.cssSelector("div#VIP_BUNDLE img[alt='close-icon']")).click();
        Thread.sleep(3000);

        // Kiểm tra không hiện thị => Khi đóng lại thì không còn trong HTML nữa
        // Assert.assertFalse(driver.findElement(marketingPopup).isDisplayed());
        Assert.assertEquals(driver.findElements(marketingPopup).size(),0);

        // Click on "Tài khoản" button
        By loginPopup = By.cssSelector("div[data-view-id='header_header_account_container']");
        driver.findElement(loginPopup).click();
        Thread.sleep(3000);

        // "Login" Popup hiện thị
        // Kiểm tra element có trong cây HTML (present)
        // Không chắc chắn được nó hiện thị hay không
        By accountPopup = By.cssSelector("div.ReactModal__Content");
        Assert.assertEquals(driver.findElements((accountPopup)).size(),1);

        driver.findElement(By.cssSelector(".login-with-email")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='email']/parent::div/following-sibling::span[1]")).getText(),"Email không được để trống");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='email']/parent::div/following-sibling::span[2]")).getText(),"Mật khẩu không được để trống");

        driver.findElement(By.cssSelector("img.close-img")).click();
        Thread.sleep(3000);

        // Kiểm tra popup không còn hiện thị (không còn trong DOM/HTML)
        Assert.assertEquals(driver.findElements(accountPopup).size(),0);
    }

    @Test
    public void TC_04_Facebook() throws InterruptedException {
        // Access in "facebook.com" page
        driver.get("https://www.facebook.com/");

        // Click on "Create New Account" button
        By createNewAccountButton = By.cssSelector("a[data-testid='open-registration-form-button']");
        driver.findElement(createNewAccountButton).click();
        Thread.sleep(2000);

        // Verify Register popup displayed
        By createNewAccountPopup = By.xpath("//div[@id='content']/child::div/descendant::div/child::div[@id='reg_box']");
        Assert.assertEquals(driver.findElements(createNewAccountPopup).size(),1);

        // Click X to close popup
        driver.navigate().back();

        // Verify Register popup disappear
        Assert.assertEquals(driver.findElements(createNewAccountPopup).size(),0);
    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}