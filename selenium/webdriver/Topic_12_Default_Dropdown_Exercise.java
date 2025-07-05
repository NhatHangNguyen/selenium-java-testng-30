package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Topic_12_Default_Dropdown_Exercise {

    WebDriver driver;

    Select select;

    Random rand;

    String emailAddress;

    @BeforeClass
    public void initialBrowser(){

        driver = new FirefoxDriver();

        rand = new Random();

        emailAddress = "johnnguyen" + rand.nextInt(1000) + "@gmail.com";
    }

    @Test
    public void TC_01_Facebook_SignUp() throws InterruptedException {

        // Access the page
        driver.get("https://www.facebook.com/reg/");
        Thread.sleep(3000);

       // Input the valid informations in the form
        // 1 - First name field
        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("John");

        // 2 - Surname
        driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Nguyen");

        // 3 - Date of birth
        // Day field
       select = new Select(driver.findElement(By.cssSelector("select#day")));
       select.selectByVisibleText("18");
       Assert.assertEquals(select.getFirstSelectedOption().getText(), "18");
       Assert.assertFalse(select.isMultiple());
       Assert.assertEquals(select.getOptions().size(),31);
       Thread.sleep(3000);

       // Month field
        select = new Select(driver.findElement(By.cssSelector("select#month")));
        select.selectByVisibleText("Jun");
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Jun");
        Assert.assertFalse(select.isMultiple());
        Assert.assertEquals(select.getOptions().size(), 12);
        Thread.sleep(3000);

       // Year field
        select = new Select(driver.findElement(By.cssSelector("select#year")));
        select.selectByVisibleText("2000");
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "2000");
        Assert.assertFalse(select.isMultiple());
        Assert.assertEquals(select.getOptions().size(), 121);
        Thread.sleep(3000);

        // 4 - Gender
        driver.findElement(By.xpath("//label[text()='Female']")).click();

        // 5 - Email address
        driver.findElement(By.xpath("//div[text()='Mobile number or email address']/following-sibling::input")).sendKeys(emailAddress);

        // 6 - Password field
        driver.findElement(By.xpath("//div[text()='New password']/following-sibling::input")).sendKeys("1234567890@Ac");

        // Click "Sign Up" button
        driver.findElement(By.xpath("//a[text()='Already have an account?']/parent::div//preceding-sibling::div/button[text()='Sign Up']")).click();
    }

    @Test
    public void TC_02_Role_Location() throws InterruptedException {
        // Access the page
        driver.get("https://rode.com/en/support/where-to-buy");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement allowAllBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Allow All']")));
        allowAllBtn.click();

        // Check the dropdown does not support multiple select attribute
        select = new Select(driver.findElement(By.cssSelector("select#country")));
        Assert.assertFalse(select.isMultiple());

        // Select "Vietnam" value in dropdown and "HO CHI MINH" values in City textbox
        // 1 - Dropdown
        select.selectByVisibleText("Vietnam");
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Vietnam");

        // 2 - Textbox
        driver.findElement(By.cssSelector("input#map_search_query")).sendKeys("HO CHI MINH");

        // Click on the Search button
        driver.findElement(By.cssSelector("button.btn")).click();

        // Verify how many dealers
        // print out all dealers name
        List<WebElement> dealers = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//h3[text()='Dealers']/following-sibling::div//div[@title='click to show on map']//h4")
        ));

        Assert.assertEquals(dealers.size(), 16, "Dealer count is not 16 as expected!");

        System.out.println("=== List of dealers ===");
        for (WebElement dealer : dealers) {
            System.out.println(dealer.getText());
        }
        System.out.println("=======================");
    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}