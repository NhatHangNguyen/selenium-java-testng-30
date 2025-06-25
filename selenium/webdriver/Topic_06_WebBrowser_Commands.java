package webdriver;

import org.checkerframework.framework.qual.TargetLocations;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.annotation.Target;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class Topic_06_WebBrowser_Commands {

    WebDriver driver;
    private Iterable<? extends Cookie> cookies;

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
    public void TC_01_() throws MalformedURLException {
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

        // Đi tìm 1 element
        driver.findElement(By.xpath(""));

        // Đi tìm n elements
        driver.findElements(By.xpath(""));

        WebDriver.Options options = driver.manage();

        // Selenium ver 3
        options.timeouts().implicitlyWait(15, TimeUnit.DAYS);
        options.timeouts().implicitlyWait(15, TimeUnit.HOURS);

        options.timeouts().implicitlyWait(15, TimeUnit.MINUTES);
        options.timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.MILLISECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.MICROSECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.NANOSECONDS);

        WebDriver.Timeouts timeouts = driver.manage().timeouts();

        // Selenium ver 4
        // Dùng để chờ cho việc tìm element (findElement/ findElements)
        timeouts.implicitlyWait(Duration.ofDays(15));
        timeouts.implicitlyWait(Duration.ofHours(15));
        timeouts.implicitlyWait(Duration.ofMinutes(15));
        timeouts.implicitlyWait(Duration.ofSeconds(15));
        timeouts.implicitlyWait(Duration.ofMillis(15));
        timeouts.implicitlyWait(Duration.ofNanos(15));

        // Dùng để chờ cho việc page đươc load xong
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));

        // Dùng để chờ cho 1 đoạn script được thực thi xong
        // JavascriptExecutor - js
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(15));

        WebDriver.Window window = driver.manage().window();

        // Thu nhỏ về taskbar để chạy
        window.minimize();

        // Phóng to lên  (vẫn còn có taskbar)
        window.maximize();

        // Tràn màn hình (không có taskbar)
        window.fullscreen();

        // Responsive
        driver.manage().window().setSize(new Dimension(1920,1080));
        driver.manage().window().getSize();

        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().getPosition();

        // Test GUI: Graphic User Interface
        // Front/ Color/ Size/ Position/ Location/...

        // Lấy hết tất cả Cookie
        driver.manage().getCookies();

        driver.manage().getCookieNamed("");

        // Xoá hết Cookie
        driver.manage().deleteAllCookies();

        for (Cookie cookie : cookies) {
            // Xoá cookie theo thứ tự
            driver.manage().deleteCookie(cookie);
        }

        // Xoá cookie theo tên
        driver.manage().deleteCookieNamed("");

        // Đến 1 test class khác 02/03/04/... (Không cần login - set cookie đã có vào đây rồi refesh lại)
        for (Cookie cookie : cookies) {
            // Add cookie theo thứ tự
            driver.manage().addCookie(cookie);
        }
        driver.navigate().refresh(); // Login thành công

        Logs log = driver.manage().logs();
        LogEntries logEntries = log.get("BROWSER");

        for(LogEntry logEn: logEntries){
            System.out.println(logEn);
        }

        log.getAvailableLogTypes();

        WebDriver.Navigation navigation = driver.navigate();

        // Refresh/ F5
        navigation.refresh();

        // Quay lại trang trước đó
        navigation.back();

        // Chuyển tiếp tới trang trước đó
        navigation.forward();

        // Mở URL bất kì
        navigation.to("https://demo.nopcommerce.com");

        navigation.to(new URL("https://demo.nopcommerce.com"));

        // Alert/ Iframe/ Window (tab)
        WebDriver.TargetLocator targetLocator = driver.switchTo();

        // Alert
        targetLocator.alert().accept();
        targetLocator.alert().dismiss();

        // Frame/ Iframe
        targetLocator.frame("");
        targetLocator.defaultContent();

        // Window
        targetLocator.window("");

        // Lấy ra ID của tab/ window đang active
        driver.getWindowHandle();

        // Lấy ra tất cả ID của tất cả các tab/ window đang có
        driver.getWindowHandles();
    }


    @AfterClass
    public void cleanBrowser () {
        driver.quit();
    }
}