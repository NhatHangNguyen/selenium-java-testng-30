package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_10_TextBox_TextArea_Exercise {

    WebDriver driver;

    String firstName, lastName, emailAddress, password, fullName;

    @BeforeClass
    public void initialBrowser(){

        driver = new FirefoxDriver();

        firstName = "Joe";
        lastName = "Biden";
        fullName = firstName + " " + lastName;
        emailAddress = "joebiden" + "@gmail.com";
        password = "123456789";
    }

    @Test
    public void TC_01_(){
        driver.get("https://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

       driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstName);
       driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastName);
       driver.findElement(By.cssSelector("input#email_address")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys(password);

        driver.findElement(By.xpath("//button[@title='Register']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),
                "Thank you for registering with Main Website Store.");

        String contactInformationText = driver.findElement(By.xpath(
                "//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        System.out.println(contactInformationText);

        // Tương đối
        Assert.assertTrue(contactInformationText.contains(fullName) && contactInformationText.contains(emailAddress)); // Full name + Email
    }


    @Test
    public void TC_02_Login(){

    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}