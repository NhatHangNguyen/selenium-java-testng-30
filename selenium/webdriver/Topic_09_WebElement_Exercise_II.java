package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
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


public class Topic_09_WebElement_Exercise_II {

    WebDriver driver;

    @BeforeClass
    public void initialBrowser(){

        driver = new ChromeDriver();
    }

    @Test
    public void Login_01_Empty_Email_Password() throws InterruptedException {

        // Access into the page
        driver.get("https://live.techpanda.org/");

        // Click on "My account" link to go to login page
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

        // Click Login button
        driver.findElement(By.cssSelector("button#send2")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Verify the error message at "Email address" field
        WebElement emailError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#advice-required-entry-email")));
        Assert.assertEquals(emailError.getText(), "This is a required field.");

        // Verify the error message at "Password" field
        WebElement passError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#advice-required-entry-pass")));
        Assert.assertEquals(passError.getText(), "This is a required field.");
    }

    @Test
    public void Login_02_Invalid_Email() throws InterruptedException {

        // Access into the page
        driver.get("https://live.techpanda.org/");

        // Click on "My account" link to go to login page
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

        // Enter an invalid email
        driver.findElement(By.cssSelector("input#email")).sendKeys("1234432342@123543");

        // Enter a valid password
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123456");

        // Click Login button
        driver.findElement(By.cssSelector("button#send2")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Verify the error message at "Email address" field
        WebElement emailErrorField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#advice-validate-email-email")));
        Assert.assertEquals(emailErrorField.getText(), "Please enter a valid email address. For example johndoe@domain.com.");
    }

    @Test
    public void Login_03_Invalid_Password() throws InterruptedException {

        // Access into the page
        driver.get("https://live.techpanda.org/");

        // Click on "My account" link to go to login page
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

        // Enter a valid email
        driver.findElement(By.cssSelector("input#email")).sendKeys("automationfc@gmail.com");

        // Enter an invalid password
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123");

        // Click Login button
        driver.findElement(By.cssSelector("button#send2")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Verify the error message at "Password" field
        WebElement passwordErrorField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#advice-validate-password-pass")));
        Assert.assertEquals(passwordErrorField.getText(),"Please enter 6 or more characters without leading or trailing spaces.");
    }

    @Test
    public void Login_04_Incorrect_Email_Password() throws InterruptedException {

        // Access into the page
        driver.get("http://live.techpanda.org/");

        // Click on "My account" link to go to login page
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

        // Enter a valid email
        driver.findElement(By.cssSelector("input#email")).sendKeys("qaautomationfc@gmail.com");

        // Enter an invalid password
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123123456");

        // Click Login button
        driver.findElement(By.cssSelector("button#send2")).click();

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement insecureFormBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button#proceed-button")));
            insecureFormBtn.click();
        } catch (TimeoutException ignored) {
        }

        // Verify the error message
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.error-msg span")));
        Assert.assertEquals(errorMsg.getText(), "Invalid login or password.");
    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}