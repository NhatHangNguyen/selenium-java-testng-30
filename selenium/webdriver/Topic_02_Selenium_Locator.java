package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_02_Selenium_Locator {

    // 1 - Setup: OS/ Browser/ Web/ Page/ Data/ Variable/ Object
    // Khai báo
    WebDriver driver;

    WebDriver secondDriver;

    @BeforeClass
    public void initialBrowser(){
        // Mở browser lên
        // Khởi tạo biên driver lên
        driver = new ChromeDriver();

        secondDriver = new ChromeDriver();

        // Mở app lên đến màn hình login
        driver.get("https://demo.nopcommerce.com/login");
    }

    // 2 - Action/ Execute: Tương tác lên element nào/ nhập liệu/ verify/...
    @Test
    public void TC_01_(){
        // Tương tác lên email Address textbox

        // HTML Source Code: // Tagname - Attribute - Value (// Thẻ - Thuộc tính - giá trị thuộc tính)

        // XPath: //tagname[@attribute='value']

        // CSS: tagname[attribute='value']

        // Tương tác lên Email Address textbox
        // 8 loại locator để tìm Email Address này
        driver.findElement(By.id(""));
    }

    @Test
    public void TC_02_Login(){

    }

    @AfterClass
    // 3 - Clean: Delete data test/ account/ close browser/...
    public void cleanBrowser(){
        driver.quit();
    }
}