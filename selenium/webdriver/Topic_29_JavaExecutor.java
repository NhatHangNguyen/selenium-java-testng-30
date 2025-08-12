package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_29_JavaExecutor {

    WebDriver driver;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void initialBrowser(){

        driver = new ChromeDriver();
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
    }

    @Test
    public void TC_01_(){
        // Click vào 1 element mà đang bị che/ẩn bằng WebElement click();
        driver.findElement(By.cssSelector("ul.top-menu.notmobile a[href='/desktops']")).click();

        // Click vào 1 element bằng JSExecutor nó chẳng quan tâm ẩn/ hiện
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("ul.top-menu.notmobile a[href='/desktops']")));

        // Lấy ra cái title của browser
        driver.getTitle();

        // Lấy ra cái URL
        driver.getCurrentUrl();

        // Lấy ra domain
        System.out.println(jsExecutor.executeScript("return document.domain;"));

        // Lấy ra 1 Web Element
        WebElement emailTextbox = (WebElement) jsExecutor.executeScript("return document.querySelector('input#Email');");

        emailTextbox.sendKeys("automation@gmail.com");

        String loginPageUrl = (String) jsExecutor.executeScript("return document.URL;");

        List<WebElement> emailTypeTextBox = (List<WebElement>) jsExecutor.executeScript("return document.querySelectorAll(\"input[type='email']\");");

        // Scroll tới 1 element nào đó thì s
    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}