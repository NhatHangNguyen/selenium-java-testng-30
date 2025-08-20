package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class Topic_31_Upload_File {

    WebDriver driver;
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Single_File() throws InterruptedException {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        By inputBy = By.xpath("//input[@type='file']");

        // Load file lên - MỖI LẦN LOAD 1 FILE
        driver.findElement(inputBy).sendKeys(hoiAnPath);
        Thread.sleep(2000);

        driver.findElement(inputBy).sendKeys(haGiangPath);
        Thread.sleep(2000);

        driver.findElement(inputBy).sendKeys(phuQuocPath);
        Thread.sleep(2000);

        // Verify file được load lên
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + hoiAn + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + haGiang + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + phuQuoc + "']")).isDisplayed());

        // Click upload cho từng file
        List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));

        for(WebElement startButton : startButtons){
            startButton.click();
            Thread.sleep(2000);
        }

        // Verify các file được upload thành công
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + hoiAn + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + haGiang + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + phuQuoc + "']")).isDisplayed());
    }

    @Test
    public void TC_02_Multiple_File() throws InterruptedException {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        By inputBy = By.xpath("//input[@type='file']");

        // Load file lên - 1 LẦN LOAD NHIỀU FILES
        driver.findElement(inputBy).sendKeys(hoiAnPath + "\n" + haGiangPath + "\n" + phuQuocPath);
        Thread.sleep(2000);

        // Verify file được load lên
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + hoiAn + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + haGiang + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + phuQuoc + "']")).isDisplayed());

        // Click upload cho từng file
        List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));

        for(WebElement startButton : startButtons){
            startButton.click();
            Thread.sleep(2000);
        }

        // Verify các file được upload thành công
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + hoiAn + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + haGiang + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + phuQuoc + "']")).isDisplayed());
    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}