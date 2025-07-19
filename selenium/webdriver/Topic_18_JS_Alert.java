package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_18_JS_Alert {

    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void initialBrowser(){

        driver = new ChromeDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_07_Accept_Alert() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        Thread.sleep(3000);

        Alert alert = driver.switchTo().alert();

        Assert.assertEquals(alert.getText(),"I am a JS Alert");

        alert.accept();

        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You clicked an alert successfully");

//        // Accept Alert
//        driver.switchTo().alert().accept();
//        alert.accept();
//
//        // Cancel Alert
//        driver.switchTo().alert().dismiss();
//        alert.dismiss();
//
//        // Get text ben trong Alert (Description)
//        String text = driver.switchTo().alert().getText();
//        alert.getText();
//
//        // Enter text vao Alert
//        driver.switchTo().alert().sendKeys("");
//        alert.sendKeys("");
    }

    @Test
    public void TC_08_Confirm_Alert() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        WebElement jSConfirmBtn = driver.findElement(By.xpath("//button[text()='Click for JS Confirm']"));

        // Click Ok button
        jSConfirmBtn.click();

        // Vừa wait alert present và vừa switch qua
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(alert.getText(),"I am a JS Confirm");

        alert.accept();

        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You clicked: Ok");

        // Click Cancel button
        jSConfirmBtn.click();
        alert = explicitWait.until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(alert.getText(),"I am a JS Confirm");

        alert.dismiss();

        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You clicked: Cancel");
    }

    @Test
    public void TC_09_Alert(){

        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(alert.getText(), "I am a JS prompt");

        String inputText = "Automation FC";

        alert.sendKeys(inputText);

        alert.accept();

        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: " + inputText);
    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}