package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;


public class Topic_27_Windows_Tab {

    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_13_Github() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        // Lấy ra ID của tab/ window mà driver đang active mà tại page đó
        String githubWindowID = driver.getWindowHandle();

        // Click on "Google" link -> nó sẽ bậy lên 1 tab mới và tự nhảy qua
        // Nhưng về code selenium thì driver không tự nhảy qua. Nó vẫn ở tab cũ
        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        Thread.sleep(3000);

        // Switch qua tab Google
        switchToWindowById(githubWindowID);
        Thread.sleep(3000);

        String googleWindowID = driver.getWindowHandle();

        driver.findElement(By.cssSelector("textarea[name='q']")).sendKeys("Selenium WebDriver");
        Thread.sleep(3000);

        // Switch về tab Github
        switchToWindowById(googleWindowID);

        // Click vào Fb link -> nó sẽ bật lên 1 tab mới và tự nhảy qua
        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        Thread.sleep(3000);

        switchToWindowByTitle("Facebook – log in or sign up");

        // Quay về github
        switchToWindowByTitle("Selenium WebDriver");

        // Click vào TIKI link -> nó sẽ bật lên 1 tab mới và tự nhảy qua
        driver.findElement(By.xpath("//a[text()='TIKI']")).click();
        Thread.sleep(3000);

        switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
        Thread.sleep(3000);

        closeAllWindowWithoutParent(githubWindowID);

        // Sau khi chạy hết thì sẽ đóng hết các tab/ window ngoại trừ Github
        // Driver nó đang active/ đứng ở window/ tab nào ???
    }

    private void closeAllWindowWithoutParent(String githubWindowID) throws InterruptedException {
        // lấy hết toàn bộ các ID của window/ tab
        Set<String> allWindowIDs = driver.getWindowHandles();

        // dùng vòng lặp duyệt qua từng ID
        for (String id : allWindowIDs) {
            if (!id.equals(githubWindowID)) {
                driver.switchTo().window(id);
                Thread.sleep(2000);
                driver.close();
            }
        }

        // Switch vao window duy nhất còn lại
        driver.switchTo().window(githubWindowID);
    }

    private void switchToWindowByTitle(String expectedPageTitle) throws InterruptedException {
        // lấy hết toàn bộ các ID của window/ tab
        Set<String> allWindowIDs = driver.getWindowHandles();

        // dùng vòng lặp duyệt qua từng ID
        for (String id : allWindowIDs) {
            // mỗi lần duyệt qua sẽ cho nó switch vào trước
            driver.switchTo().window(id);
            Thread.sleep(3000);

            // Get ra title của window/ tab hiện tại
            String pageTitle = driver.getTitle();

            // Kiểm tra title
            if (pageTitle.equals(expectedPageTitle)) {
                break;
            }
        }
    }

    // Chỉ đúng vs 2 Window/ Tab
    private void switchToWindowById(String windowID) {
        //Lấy ra hết tất cả các ID cuae window/ tab hiện tại
        Set<String> allWindowIDs = driver.getWindowHandles();

        // Dùng vòng lặp để duyệt qua từng ID một
        for (String id : allWindowIDs) {
            // Kiểm tra đk: nếu ID nào mà khác với ID mong đợi thì switch qua
            if (!id.equals(windowID)) {
                driver.switchTo().window(id);
            }
        }
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }
}