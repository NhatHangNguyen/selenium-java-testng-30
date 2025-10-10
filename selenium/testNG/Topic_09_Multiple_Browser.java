package testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class Topic_09_Multiple_Browser {
    WebDriver driver;
    By emailTextbox = By.cssSelector("input#email");
    By passwordTextbox = By.cssSelector("input#pass");
    By loginButton = By.cssSelector("button#send2");
    String userName = "selenium_11_01@gmail.com";
    String password = "111111";
    String domainUrl;


    @BeforeClass
    @Parameters({"server","browser"})
    public void beforeClass(String serverName, @Optional("Firefox") String browserName) {
        if (serverName.equalsIgnoreCase("Dev")){
            domainUrl = "http://dev.techpanda.org";
        } else if (serverName.equalsIgnoreCase("Testing")) {
            domainUrl = "http://testing.techpanda.org";
        } else if (serverName.equalsIgnoreCase("Staging")) {
            domainUrl = "http://staging.techpanda.org";
        } else if (serverName.equalsIgnoreCase("Production")) {
            domainUrl = "http://live.techpanda.org";
        } else {
            throw new RuntimeException("Server name is not valid!!");
        }

        // Switch-Case
        switch (browserName) {
            case "Chrome":
                driver = new ChromeDriver();
                break;
            case "Edge":
                driver = new EdgeDriver();
                break;
            case "Firefox":
                driver = new FirefoxDriver();
                break;
            default:
                new RuntimeException("Browser name is not valid!!!");
        }

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void loginOnMultipleBrowser() {
        driver.get(domainUrl + "/index.php/customer/account/login/");

        driver.findElement(emailTextbox).sendKeys(userName);
        driver.findElement(passwordTextbox).sendKeys(password);
        driver.findElement(loginButton).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(userName));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
