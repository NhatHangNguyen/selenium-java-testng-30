package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class Topic_06_WebBrowser_Commands {

    WebDriver driver;

    @BeforeClass
    public void initialBrowser() throws MalformedURLException {

        // Run with browser
        driver = new ChromeDriver();
        driver = new FirefoxDriver();
        driver = new SafariDriver();
        driver = new EdgeDriver();
        driver = new InternetExplorerDriver();

        // Remote (Grid/ Docker/ Cloud Testing)
        // Networking (LAN/ WAN/ IP/ Port)
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://localhost:4444"), chromeOptions);
    }

    @Test
    public void TC_01_() {
        // Mở ra 1 URL bất kì (lưu ý phải bắt đầu bằng http/ https)
        driver.get("https://demo.nopcommerce.com");
        driver.get("https://live.techpanda.org/index.php/catalog/seo_sitemap/category/");

        // Đóng browser (active tabs và windows)
        driver.close();

        // Đóng browser (bao gồm tất cả các tabs/ windows)
        driver.quit();

        // Lấy ra title của page hiện tại
        // 1 - Lưu dữ liệu lại rồi kiểm tra sau
        String homePageTitle = driver.getTitle();
        Assert.assertEquals(homePageTitle, "nopCommerce demo store. Home page title");
        Assert.assertTrue(homePageTitle.contains("demo store"));

        // 2 - Kiểm tra trực tiếp
        // Kiểm tra tương đối
        Assert.assertEquals(driver.getTitle(), "nopCommerce demo store. Home page title");

        // Lấy ra URL của page hiện tại
        Assert.assertEquals(driver.getCurrentUrl(), "https://demo.nopcommerce.com/cart");

        // Lấy ra Page Source Code
        String homePageSourceCode =  driver.getPageSource();

        // Kiểm tra tuơng đối
        Assert.assertTrue(homePageSourceCode.contains("Conditions of Use"));

        // Lấy ra ID của tab/ window đang active
        driver.getWindowHandle();

        // Lấy ra tất cả ID của tất cả các tab/ window đang có
        driver.getWindowHandles();

        // Đi tìm 1 element
        driver.findElement(By.xpath(""));

        // Đi tìm n elements
        driver.findElements(By.xpath(""));

        // Selenium ver 3
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.DAYS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.HOURS);

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.MINUTES);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.MILLISECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.MICROSECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.NANOSECONDS);

        // Selenium ver 4
        // Dùng để chờ cho việc tìm element (findElement/ findElements)
        driver.manage().timeouts().implicitlyWait(Duration.ofDays(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofHours(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofNanos(15));

        // Dùng để chờ cho việc page đươc load xong
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));

        // Dùng để chờ cho 1 đoạn script được thực thi xong
        // JavascriptExecutor - js
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(15));

        // Thu nhỏ về taskbar để chạy
        driver.manage().window().minimize();

        // Phóng to lên  (vẫn còn có taskbar)
        driver.manage().window().maximize();

        // Tràn màn hình (không có taskbar)
        driver.manage().window().fullscreen();

        // Responsive
        driver.manage().window().setSize(new Dimension(1920,1080));
        driver.manage().window().getSize();

        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().getPosition();

        // Test GUI: Graphic User Interface
        // Front/ Color/ Size/ Position/ Location/...
    }


    @AfterClass
    public void cleanBrowser () {
        driver.quit();
    }
}