package testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_02_Assert {
    WebDriver driver;

    @Test
    public void assertion(){
        // AssertTrue: Khi kiểm tra 1 điều kiện mong đợi nó sẽ trả về là ĐÚNG
        // Các hàm trả về true/false
        // Selenium: isDisplayed/ isEnabled/ isSelected/ isMultiple
        // User Defined
        Assert.assertTrue(driver.findElement(By.cssSelector("")).isDisplayed());

        // AssertFalse: Khi kiểm tra 1 điều kiện mong đợi nó sẽ trả về là SAI
        // Mong đợi Login button bị disable
        Assert.assertFalse(driver.findElement(By.cssSelector("")).isEnabled());

        // AssertEquals: kiểm tra 1 điều kiện mong đợi (expected) bằng với điều kiện thực tế (actual)
        // getText/ getAttribute/ getCss/ getTitle/ getUrl/...
        Assert.assertEquals(driver.findElement(By.cssSelector("")).getText(),"");
        Assert.assertEquals(driver.findElement(By.cssSelector("")).getAttribute(""),"");
        Assert.assertEquals(driver.findElements(By.xpath("")).size(),"");
    }
}
