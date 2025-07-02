package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Default_Dropdown {

    WebDriver driver;

    Select select;

    @BeforeClass
    public void initialBrowser(){

        driver = new FirefoxDriver();
    }

    @Test
    public void TC_01_Facebook_SignUp(){

        driver.get("https://www.facebook.com/reg/");

        //select.selectByIndex(20);
        // 1 - Index
        // Index thay đổi vị trí
        // Đọc code có biết nó là tỉnh nào không? => Chạy fail - > Reproduce lại -> 20 -> Manual test

        //select.selectByValue("9797");
        // 2 - Value
        // Option không bắt buộc phải có attribute value
        // Đọc code có biết nó là tỉnh nào không? -> Chay fail -> Reproduce lại -> 20 -> Manual test
        // Thêm 1 số bước để đi tìm nó là cái gì/ ở đâu

        //select.selectByVisibleText("");
        // 3 - Text
        // Giống End-user chọn
        // Không bị trùng dữ liệu/ không để trống dữ liệu
        // Không thay đổi text nếu đổi vị trí
        // Chạy fail -> Reproduce lại -> 20 -> Manual Test -> Ít mất time hơn

        // Dropdown is display
        select = new Select(driver.findElement(By.cssSelector("select#day")));
        // Chọn 1 item
        select.selectByVisibleText("25");
        // Chọn xong verify đã chọn thành công hay chưa?
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "25");
        // Verify cái dropdown có phái là multiple select hay không?
        // Nếu là multipe -> trả về là true
        // Nếu là single -> trả về là false
        Assert.assertFalse(select.isMultiple());
        // Verify tổng số lượng items trong dropdown này là bao nhiêu?
        Assert.assertEquals(select.getOptions().size(),31);

        select = new Select(driver.findElement(By.cssSelector("select#month")));
        select.selectByVisibleText("Tháng 7");
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Tháng 7");
        Assert.assertFalse(select.isMultiple());
        Assert.assertEquals(select.getOptions().size(),12);

        select = new Select(driver.findElement(By.cssSelector("select#year")));
        select.selectByVisibleText("1998");
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "1998");
        Assert.assertFalse(select.isMultiple());
        Assert.assertEquals(select.getOptions().size(),121);
    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}