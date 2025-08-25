package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;


public class Topic_33_Wait_FindElement {

    WebDriver driver;

    @BeforeClass
    public void initialBrowser(){

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Set total time = 13s
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(13));

        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
    }

    @Test
    public void TC_01_FindElement(){

        // 1 - Nếu tìm thấy duy nhất 1 element
        // Trả về đúng element đó
        // Không cần chờ hết time của implicit
        driver.findElement(By.cssSelector("input#FirstName"));

        // 2 - Nếu tìm thấy nhiều hơn 1 element
        // Chỉ thao tác với element đầu tiên
        // Lưu ý là khi lấy locator nên check trước
        driver.findElement(By.cssSelector("input[type='text']")).sendKeys("Testing");

        // 3 - Nếu không tìm thấy element nào
        // Mới đầu vào findElement nhưng không thấy:
        // Tìm lại mà thấy element thì không cần chờ hết tổng time còn lại
        // Tìm lại mà không thấy hêt tổng time 13s thì đánh fail test case
        // Show lỗi: NoSuchElementException
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(13));
        // Từ đây trở xuống

        // Đã gắn lại giá trị mới con 13 không còn ý nghĩa nữa
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //Từ đây trở xuống

        driver.findElement(By.cssSelector("input#RememberMe"));

        // Gán lại giá trị mới rồi
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
        // Từ đây trở xuống
    }

    @Test
    public void TC_02_FindElements(){
        List<WebElement> elements = null;

        // 1 - Nếu tìm thấy duy nhất 1 element
        // Trả về đúng 1 cái
        elements = driver.findElements(By.cssSelector("input#FirstName"));
        System.out.println(elements.size());

        // 2 - Nếu tìm thấy nhiều hơn 1 element
        // Trả về hết toàn bộ các element matching
        elements = driver.findElements(By.cssSelector("input[type='text']"));
        System.out.println(elements.size());

        // 3 - Nếu không tìm thấy element nào
        // Mới đầu vào findElement nhưng không thấy:
        // Tìm lại mà thấy element thì không cần chờ hết tổng time còn lại
        // Tìm lại mà không thấy hêt tổng time 13s thì:
        // + Trả về List Element = 0
        // + KHÔNG ĐÁNG FAIL TESTCASE
        elements = driver.findElements(By.cssSelector("input#RememberMe"));
        System.out.println(elements.size());
        Assert.assertEquals(elements.size(),0);

        //.....
    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}