package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_16_Custom_Checkbox_Radio {

    WebDriver driver;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void initialBrowser(){

        driver = new ChromeDriver();
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_05_Ubuntu() {
        driver.get("https://login.ubuntu.com/");

        // Thẻ input: dùng để click
        // Dùng để verify: isSelected()

        //By newUserRadio = By.cssSelector("input#id_new_user");

        // 1 - Dùng thẻ input để click -> Lỗi
        // 2 - Dùng thẻ input này để verify -> Pass
        // driver.findElement(newUserRadio).click();
        // Assert.assertFalse(driver.findElement(newUserRadio).isSelected());

        // By newUserRadio = By.cssSelector("label.new_user");
        // 2 - Dùng 1 thẻ khác input để click -> Pass
        // Dùng thẻ đó để verify -> Fail
        // iSelected() -> Dùng cho thẻ input/option
        // driver.findElement(newUserRadio).click();
        // Assert.assertTrue(driver.findElement(newUserRadio).isSelected());

        // 3 - Dùng 1 thẻ khác input để click -> Pass
        // Dùng thẻ input này để verify -> Pass
        //By newUserRadioLabel = By.cssSelector("label.new-user");
        //By newUserRadioInput = By.cssSelector("input#id_new_user");

        //driver.findElement(newUserRadioLabel).click();
        //Assert.assertTrue(driver.findElement(newUserRadioInput).isSelected());

        // 4 - Dùng duy nhất thẻ input để click/ verify dùng JS Executor
        By newUserRadioInput = By.cssSelector("input#id_new_user");

        jsExecutor.executeScript("arguments[0].click();", driver.findElement(newUserRadioInput));
        Assert.assertTrue(driver.findElement(newUserRadioInput).isSelected());

        By termCheckbox = By.cssSelector("input#id_accept_tos");

        jsExecutor.executeScript("arguments[0].click();", driver.findElement(termCheckbox));
        Assert.assertTrue(driver.findElement(termCheckbox).isSelected());
    }

    @Test
    public void TC_06_Docs(){
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");

        By hcmRadio = By.xpath("//div[@aria-label='Hồ Chí Minh']");
        By quangNoodle = By.xpath("//div[@aria-label='Mì Quảng']");

        driver.findElement(hcmRadio).click();
        Assert.assertEquals(driver.findElement(hcmRadio).getAttribute("aria-checked"), "true");

        // Check
        if (driver.findElement(quangNoodle).getAttribute("aria-checked").equals("false")){
            driver.findElement(quangNoodle).click();
        }
        Assert.assertEquals(driver.findElement(quangNoodle).getAttribute("aria-checked"),"true");

        // Uncheck
        if (driver.findElement(quangNoodle).getAttribute("aria-checked").equals("true")){
            driver.findElement(quangNoodle).click();
        }
        Assert.assertEquals(driver.findElement(quangNoodle).getAttribute("aria-checked"),"false");
    }

    @AfterClass
    public void cleanBrowser(){
        //driver.quit();
    }
}