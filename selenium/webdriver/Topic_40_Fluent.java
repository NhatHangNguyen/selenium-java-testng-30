package webdriver;

import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.NoSuchElementException;

public class Topic_40_Fluent {
    WebDriver driver;


    @BeforeClass
    public void initialBrowser(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Example(){
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        findElement(By.id("email"));
    }

    @Test
    public void TC_02_Dynamic_Loading_01(){
        driver.get("https://automationfc.github.io/dynamic-loading/");

        findElement(By.cssSelector("div#start>button")).click();

        Assert.assertEquals(getElementText(By.cssSelector("div#finish>h4")),"Hello World!");
    }

    @Test
    public void TC_02_Dynamic_Loading_02(){
        driver.get("https://automationfc.github.io/dynamic-loading/");

        findElement(By.cssSelector("div#start>button")).click();

        Assert.assertTrue(isElementDisplayed(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")));
    }

    @Test
    public void TC_03_CountDown(){
        driver.get("https://automationfc.github.io/fluent-wait/");

        WebElement countDownTime = findElement(By.id("javascript_countdown_time"));

        Assert.assertTrue(isSecondMatching(countDownTime));
    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }

    // Viết 1 hàm để tìm element vs timeout/ polling tự set
    // Đk của hàm sẽ là findElement: kiểu trả về của hàm apply
    // findElement thì cần có driver: tham số của hàm apply
    public WebElement findElement(By by){
        FluentWait<WebDriver> driverFluentWait = new FluentWait<>(driver);

        driverFluentWait.withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class);

        return driverFluentWait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(by);
            }
        });
    }

    // Kiểm tra 1 element hiện thị
    // isDisplayed = boolean (By)
    public boolean isElementDisplayed(By by){
        FluentWait<WebDriver> driverFluentWait = new FluentWait<>(driver);

        driverFluentWait.withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        return driverFluentWait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return driver.findElement(by).isDisplayed();
            }
        });
    }

    // Kiểm tra 1 element hiện thị
    // isDisplayed = boolean (WebElement)
    public boolean isElementDisplayed(WebElement element){
        FluentWait<WebElement> driverFluentWait = new FluentWait<>(element);

        driverFluentWait.withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        return driverFluentWait.until(new Function<WebElement, Boolean>() {
            @Override
            public Boolean apply(WebElement element) {
                return element.isDisplayed();
            }
        });
    }

    // Lấy ra 1 text của element
    // getText = String
    public String getElementText(By by){
        FluentWait<WebDriver> driverFluentWait = new FluentWait<>(driver);

        driverFluentWait.withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        return driverFluentWait.until(new Function<WebDriver, String>() {
            @Override
            public String apply(WebDriver driver) {
                String text = driver.findElement(by).getText();
                if (!text.isEmpty()) {
                    return text;
                }
                return null; // để FluentWait tiếp tục polling
            }
                //return driver.findElement(by).getText();
        });
    }

    public boolean isSecondMatching(WebElement element){
        FluentWait<WebElement> driverFluentWait = new FluentWait<>(element);

        driverFluentWait.withTimeout(Duration.ofSeconds(12))
                .pollingEvery(Duration.ofMillis(200)) // 1s kiểm tra đc 5 lần
                .ignoring(NoSuchElementException.class);

        return driverFluentWait.until(new Function<WebElement, Boolean>() {
            @Override
            public Boolean apply(WebElement element) {
                return element.getText().endsWith("00");
            }
        });
    }
}