package webdriver;

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

import java.io.File;
import java.time.Duration;

public class Topic_38_Explicit_Ajax {
    WebDriver driver;
    WebDriverWait explicitWait;

    String uploadFolderPath = System.getProperty("user.dir") + File.separator + "uploadFiles" + File.separator;

    String hoiAn = "Hoi An.jpg";
    String haGiang = "Ha Giang.jpg";
    String phuQuoc = "Phu Quoc.jpg";

    String hoiAnPath = uploadFolderPath + hoiAn;
    String haGiangPath = uploadFolderPath + haGiang;
    String phuQuocPath = uploadFolderPath + phuQuoc;

    @BeforeClass
    public void initialBrowser(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Calendar(){
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

        // Wait and verify Calendar element is displayed
        Assert.assertTrue(explicitWait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("div#ctl00_ContentPlaceholder1_Panel1"))).isDisplayed());

        // Wait and verify text
        Assert.assertTrue(explicitWait.until(ExpectedConditions
                .textToBe(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"),"No Selected Dates to display.")));

        // Wait and click to element
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td/a[text()='13']"))).click();

        // Wait and verify ajax loading invisible
        Assert.assertTrue(explicitWait.until(ExpectedConditions
                .invisibilityOfElementLocated(By.cssSelector("div[id$='RadCalendar1']>div.raDiv"))));

        // Wait and verify text
        Assert.assertTrue(explicitWait.until(ExpectedConditions
                .textToBe(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"),"Saturday, September 13, 2025")));
    }

    @Test
    public void TC_02_UploadFile() throws InterruptedException {
        driver.get("https://gofile.io/?t=uploadFiles");

        By fileManagerButton = By.xpath("//button[contains(normalize-space(.), \"File Manager\")]");

        // Wait and verify File Manger button is displayed
        Assert.assertTrue(explicitWait.until(ExpectedConditions
                .visibilityOfElementLocated(fileManagerButton)).isDisplayed());

        // Click on File Manager
        WebElement fileManagerBtn = explicitWait.until(ExpectedConditions.elementToBeClickable(fileManagerButton));
        fileManagerBtn.click();

        // Wait and verify text
        Assert.assertTrue(explicitWait.until(ExpectedConditions
                .textToBe(By.cssSelector("div#filemanager_itemslist_empty>p"),"No items to display")));

        // Upload files and verify file upload successful
        By uploadFileButton = By.cssSelector("button#filemanager_mainbuttons_uploadFiles");

        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(uploadFileButton)).isDisplayed());

        WebElement uploadFileBtn = explicitWait.until(ExpectedConditions.elementToBeClickable(uploadFileButton));
        uploadFileBtn.click();

        // Load file lên - 1 LẦN LOAD NHIỀU FILES
        By inputFileBy = By.xpath("//input[@type='file']");

        WebElement input = explicitWait.until(ExpectedConditions.presenceOfElementLocated(inputFileBy));
        input.sendKeys(String.join("\n", hoiAnPath, haGiangPath, phuQuocPath));

        // Chờ trang chuyển sang trang thư mục sau khi upload (GoFile đổi URL sang /d/...)
        explicitWait.until(ExpectedConditions.urlMatches(".*/d/.*"));

        // Verify file được load lên thành công
        Assert.assertTrue(explicitWait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//a[contains(@class, 'item_open') and text()='" + hoiAn + "']"))).isDisplayed());

        Assert.assertTrue(explicitWait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//a[contains(@class, 'item_open') and text()='" + haGiang + "']"))).isDisplayed());

        Assert.assertTrue(explicitWait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//a[contains(@class, 'item_open') and text()='" + phuQuoc + "']"))).isDisplayed());
    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}