package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;


public class Topic_02_Selenium_Locator {

    // 1 - Setup: OS/ Browser/ Web/ Page/ Data/ Variable/ Object
    // Khai báo
    WebDriver driver;

    String fullName = "Selenium Locator";

    @BeforeClass
    public void initialBrowser(){
        // Mở browser lên
        // Khởi tạo biên driver lên
        driver = new ChromeDriver();

        // Mở app lên đến màn hình login
        driver.get("https://demo.nopcommerce.com/register");
    }

    // 2 - Action/ Execute: Tương tác lên element nào/ nhập liệu/ verify/...

    public void TC_00_Base(){
        // Tương tác lên email Address textbox

        // HTML Source Code: // Tagname - Attribute - Value (// Thẻ - Thuộc tính - giá trị thuộc tính)

        // XPath: //tagname[@attribute='value']

        // CSS: tagname[attribute='value']

        // Tương tác lên Email Address textbox
        // 8 loại locator để tìm Email Address này

        // Sau dấu . gọi hàm/ biến của thư viện đó ra

        // Tìm 1 element
         driver.findElement(By.id(""));

        // 1 - Thao tác lên luôn (dùng 1 lần)
        // Nếu chỉ dùng 1 lần thì không cần khai báo biến
        driver.findElement(By.id("")).click();

        // 2 - Lưu dữ liệu lại (dùng niều lần)
        // Dùng nhiều lần thì nên khai báo
        WebElement emailTextBox = driver.findElement(By.id(""));
        emailTextBox.clear();
        emailTextBox.sendKeys("");
        emailTextBox.isDisplayed();

        // Tìm nhiều element giống nhau
        driver.findElements(By.cssSelector(""));
        List<WebElement> textboxes = driver.findElements(By.cssSelector(""));

    }

    @Test
    public void TC_01_ID() {
        driver.findElement(By.id("small-searchterms")).sendKeys("Macbook");

        driver.findElement(By.id("FirstName")).sendKeys("Automation");
    }

    @Test
    public void TC_02_Class() {
        // Giá trị trong class mà không có khoảng trắng (lấy toàn bộ)
        // Giá trị có khoảng trắng (lấy phần nào là duy nhất)
        driver.findElement(By.className("register-next-step-button")).click();
    }

    @Test
    public void TC_03_Name(){
        driver.findElement(By.name("DateOfBirthDay"));
        driver.findElement(By.name("DateOfBirthMonth"));
        driver.findElement(By.name("DateOfBirthYear"));
    }

    @Test
    public void TC_04_LinkText(){
        // Chỉ làm việc với element là link và có text
        // Thẻ a và có thuộc tính href
        // Phải lấy hết toàn bộ text không chừa cái nào hết (tuyệt đối)
        driver.findElement(By.linkText("Register"));

        driver.findElement(By.linkText("Log in"));

        driver.findElement(By.linkText("Wishlist"));
    }

    @Test
    public void TC_05_Partial_Link_Text(){
        // Chỉ làm việc vs element là link
        // Có thể lấy hết toàn bộ text hoặc 1 phần (hay dùng)
        driver.findElement(By.partialLinkText("Register"));

        driver.findElement(By.partialLinkText("Digital"));

        driver.findElement(By.partialLinkText("downloads"));
    }

    @Test
    public void TC_06_Tagname(){
        // Tên thẻ (HTML)
        // Tìm tất cả các element giống nhau (thẻ của component giống nhau)
        // Tất cả các textbox/ checkbox/ radio/ link/ button/....
        driver.findElements(By.tagName("button"));
        driver.findElements(By.tagName("input"));
        driver.findElements(By.tagName("label"));
    }

    @Test
    public void TC_07_Css(){
        driver.findElement(By.cssSelector("input#Company"));
        driver.findElement(By.cssSelector("#Company"));
        driver.findElement(By.cssSelector("input[id='Company']"));

        driver.findElement(By.cssSelector("button.register-next-step-button"));
        driver.findElement(By.cssSelector("button[class='button-1 register-next-step-button']"));

        driver.findElement(By.cssSelector("select[name='DateOfBirthDay']"));
        driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']"));
        driver.findElement(By.cssSelector("select[name='DateOfBirthYear']"));

        // Css kết hợp với link text
        driver.findElement(By.cssSelector("a[href='/register?returnUrl=%2F']"));
        driver.findElement(By.cssSelector("a[href='/login?returnUrl=%2F']"));

        // Css kết hợp với partial link text
        driver.findElement(By.cssSelector("a[href*='register?']"));
        driver.findElement(By.cssSelector("a[href*='login?']"));

        // Css kết hợp với tagname
        driver.findElement(By.cssSelector("a"));
        driver.findElement(By.cssSelector("button"));
        driver.findElement(By.cssSelector("input"));
    }

    @Test
    public void TC_08_XPath(){
        driver.findElement(By.xpath("//input[@id='small-searchterms']"));
        driver.findElement(By.xpath("//input[@id='Password']"));
        driver.findElement(By.xpath("//input[@id='Company']"));

        driver.findElement(By.xpath("//button[@class='button-1 register-next-step-button']"));
        driver.findElement(By.xpath("//button[contains(@class,'register-next-step-button')]"));

        driver.findElement(By.xpath("//select[@name='DateOfBirthDay']"));
        driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']"));
        driver.findElement(By.xpath("//select[@name='DateOfBirthYear']"));

        // Lấy toàn bộ link text
        driver.findElement(By.xpath("//a[text()='Register']"));
        driver.findElement(By.xpath("//a[text()='Log in']"));
        driver.findElement(By.xpath("//a[text()='Shipping & returns']"));

        // Lấy partial link text
        driver.findElement(By.xpath("//a[contains(text(),'Register')]"));
        driver.findElement(By.xpath("//a[contains(text(),'Shipping')]"));

        driver.findElement(By.xpath("//a"));
        driver.findElement(By.xpath("//button"));
        driver.findElement(By.xpath("//a"));
        driver.findElement(By.xpath("//input"));
    }

    @AfterClass
    // 3 - Clean: Delete data test/ account/ close browser/...
    public void cleanBrowser(){
        driver.quit();
    }
}