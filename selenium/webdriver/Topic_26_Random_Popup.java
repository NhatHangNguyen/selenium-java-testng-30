package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_26_Random_Popup {

    WebDriver driver;

    @BeforeClass
    public void initialBrowser(){

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @Test
    public void TC_05_Vnk_Edu_In_Dom() throws InterruptedException {
        driver.get("https://vnk.edu.vn/");

        By marketingPopup = By.cssSelector("div.custom-position");

        // Kiểm tra popup trong 2 trường hợp
        // Có xuất hiện - đóng popup đi - chuyển sang step 3
        if(driver.findElements(marketingPopup).size() > 0 && driver.findElements(marketingPopup).get(0).isDisplayed()){
            driver.findElement(By.cssSelector("div.custom-position button[aria-label='Close']")).click();
            Thread.sleep(300);

            // Verify popup k con hien thi -> Còn trong HTML -> isDisplayed được
            Assert.assertFalse(driver.findElement(marketingPopup).isDisplayed());
        }

        // Không xuất hiện  - chuyển sáng step 3 (Element của popup vẫn còn trong DOM)
        driver.findElement(By.xpath("//ul[@id='mega-menu-primary']//a[text()='Liên hệ']")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("div.title-content>h1")).isDisplayed());
    }

    @Test
    public void TC_05_1_Kmplayer_In_Dom() throws InterruptedException {
        driver.get("https://www.kmplayer.com/home");

        By kmPlexPopup = By.cssSelector("div.pop-conts");

        // Kiểm tra popup trong 2 trường hợp
        // Có xuất hiện - đóng popup đi - chuyển sang step 3
        if(driver.findElements(kmPlexPopup).size() > 0 && driver.findElements(kmPlexPopup).get(0).isDisplayed()){
            driver.findElement(By.cssSelector("div.close")).click();
            Thread.sleep(3000);

            // Verify popup k con hien thi -> Còn trong HTML -> isDisplayed được
            Assert.assertFalse(driver.findElement(kmPlexPopup).isDisplayed());
        }

        // Không xuất hiện  - chuyển sáng step 3 (Element của popup vẫn còn trong DOM)
        driver.findElement(By.cssSelector("li.faq")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("div.v_bg>div")).isDisplayed());
    }

    @Test
    public void TC_06_JavaCodeGeeks_Not_In_Dom() throws InterruptedException {
        driver.get("https://www.javacodegeeks.com/");

        By newletterPopup = By.xpath("//div[@data-title='Newsletter-Books Anime Brief' and not(contains(@style, 'display:none'))]");

        // Hiện thị thì close đi rooif action tiếp (có element nhưng phải hiện thị trong HTML)
        if (driver.findElements(newletterPopup).size() > 0 && driver.findElements(newletterPopup).get(0).isDisplayed()){
            System.out.println("--------GO TO IF------------");
            driver.findElement(By.xpath("//div[@data-title='Newsletter-Books Anime Brief' and not(contains(@style,'display:none'))]//a[text()='×']")).click();
            Thread.sleep(3000);

            // Verify popup k con hien thi -> Không còn trong HTML -> getSize ra
            Assert.assertEquals(driver.findElements(newletterPopup).size(), 0);
        }

        // Không hiện thị thì actions tiếp lun
        System.out.println("--------IGNORE IF------------");
        driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile");
        driver.findElement(By.cssSelector("form#search span.tie-icon-search")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("header>h1.page-title")).isDisplayed());
    }

    @Test
    public void TC_07_deHieu_In_Dom() throws InterruptedException {
        driver.get("https://dehieu.vn/");

        By registerPopup = By.cssSelector("div.modal-content");

        // Hiện thị thì close đi rooif action tiếp (có element nhưng phải hiện thị trong HTML)
        if (driver.findElements(registerPopup).size() > 0 && driver.findElements(registerPopup).get(0).isDisplayed()){
            driver.findElement(By.cssSelector("div.modal-content button.close")).click();
            Thread.sleep(3000);

            // Verify popup k con hien thi -> Còn trong HTML -> isDisplayed được
            Assert.assertFalse(driver.findElement(registerPopup).isDisplayed());
        }

        // Không hiện thị thì actions tiếp lun
        driver.findElement(By.cssSelector("input.search-form")).sendKeys("Đọc bản vẽ và bóc tách khối lượng cơ điện");
        driver.findElement(By.cssSelector("button.header-search")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("div.container h4")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.cssSelector("div.container h4")).getText(), "Tìm thấy 1 khóa học với từ khóa \"Đọc bản vẽ và bóc tách khối lượng cơ điện\"");
    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}