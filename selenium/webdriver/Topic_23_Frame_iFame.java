package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_23_Frame_iFame {

    WebDriver driver;

    @BeforeClass
    public void initialBrowser(){

        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_10_Iframe_ToiDiCodeDao(){
        driver.get("https://toidicodedao.com/");

        driver.switchTo().frame(driver.findElement(By.cssSelector("div.fb_iframe_widget iframe")));

        Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Tôi đi code dạo']/parent::div/following-sibling::div[text()]")).getText(),"397,423 followers");
    }

    @Test
    public void TC_11_iFrame_FormSite() throws InterruptedException {
        // Trang HTML A
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");

        driver.findElement(By.cssSelector("img[alt='Campus Safety Survey']")).click();
        Thread.sleep(3000);

        // Switch qua iFrame
        // Index: page hiện tại có nhìu iframe/
        // Frame đầu tiên sẽ có index = 0 và tăng dần lên
        // Khi thêm mới/ update lại/ xoá bớt đi thì đổi index của các iframe
        // driver.switchTo().frame(0);

        // Id hoặc name (page có frame/iframe chỉ có id/ name)
        // driver.switchTo().frame("frame-one85593366");

        // WebElement (Có thể cover 2 cách trên)
        driver.switchTo().frame(driver.findElement(By.cssSelector("div#formTemplateContainer>iframe")));
        Thread.sleep(3000);

        // Element thuoc trang HTML B
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2"))).selectByVisibleText("Sophomore");
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-3"))).selectByVisibleText("West Dorm");
        driver.findElement(By.xpath("//label[@for='RESULT_RadioButton-4_1']")).click();
        Thread.sleep(3000);

        // Từ B quay lại A
        driver.switchTo().defaultContent();

        // drive đã quay lại A
        driver.findElement(By.cssSelector("a.menu-item-login.fs-btn--transparent-kashmir")).click();

        driver.findElement(By.cssSelector("button#login")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#message-error")).getText(), "Username and password are both required.");

        // A qua B
        driver.switchTo().frame(0);

        // B qua C
        driver.switchTo().frame(0);

        // C quay lai B
        driver.switchTo().parentFrame();

        // B quay lai A
        driver.switchTo().defaultContent();
    }

    @Test
    public void TC_11_1_iFrame_jQueryUi() throws InterruptedException {
        driver.get("https://jqueryui.com/dialog/");

        driver.switchTo().frame(driver.findElement(By.cssSelector("div#content-wrapper iframe")));
        Thread.sleep(3000);

        driver.findElement(By.cssSelector("span.ui-icon-closethick")).click();
        Thread.sleep(3000);

        Assert.assertFalse(driver.findElement(By.cssSelector("div#dialog")).isDisplayed());
    }

    @Test
    public void TC_11_2_iFrame_reCaptCha() throws InterruptedException {
        driver.get("https://www.google.com/recaptcha/api2/demo");

        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[title='reCAPTCHA']")));

        driver.findElement(By.cssSelector("div.recaptcha-checkbox-border")).click();
        Thread.sleep(3000);
    }

    @Test
    public void TC_11_3_iFrame_embedGoogleMap() throws InterruptedException {
        driver.get("https://www.embedgooglemap.net/");

        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#gmap_canvas")));
    }

    @Test
    public void TC_12_Frame() throws InterruptedException {
        driver.get("https://netbanking.hdfcbank.com/netbanking/");

        driver.switchTo().frame("login_page");

        driver.findElement(By.cssSelector("input[name='fldLoginUserId']")).sendKeys("automationfc");
        Thread.sleep(3000);

        driver.findElement(By.cssSelector("a.login-btn")).click();

        driver.switchTo().defaultContent();// Quay về trang chính
        Thread.sleep(10000);

        driver.findElement(By.cssSelector("input#keyboard")).sendKeys("123456789");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("a#loginBtn")).click();
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("p.error-msg")).getText(),"Customer ID/IPIN (Password) is invalid. Please try again.");
    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}