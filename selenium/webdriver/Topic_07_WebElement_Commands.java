package webdriver;

import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_07_WebElement_Commands {

    // Chứa các hàm để tương tác với browser
    WebDriver driver;

    WebElement element;

    @Test
    public void TC_01_WebElement() {
        driver.findElement(By.xpath("")).click();

        element = driver.findElement(By.xpath(""));

        // Click vào các element dạng: button/ checkbox/ radio/ link/ image/ icon....
        element.click();

        // Nhập liệu các element dang: textbox/ textarea/ dropdow (edit)
        // Xoá dữ liệu trước khi sendkey
        element.clear();
        element.sendKeys("test@gmail.com");
        element.sendKeys(Keys.COMMAND);

        driver.findElement(By.id("div.login-page"))
                .findElement(By.cssSelector("div.customer-blocks"))
                .findElement(By.id("Email"));

        driver.findElement(By.cssSelector("div.login-page div.customer-blocks input#Email"));

        // Tác dụng vs form (SignUp/ Login/ Search/...)
        // thẻ form
        driver.findElement(By.id("Email")).sendKeys("");
        driver.findElement(By.id("Password")).sendKeys("");
        driver.findElement(By.id("Password")).submit();

        // Áp dụng cho tất cả các loai element
        // Kiểm tra 1 element có hiện thị hay không
        // Size > 0: Width/ Height > 0
        // Nhìn thấy/ thao tác được
        element.isDisplayed();

        Assert.assertTrue(element.isDisplayed());
        Assert.assertFalse(element.isDisplayed());

        // Áp dụng cho duy nhất 3 loại: checkbox/ radio/ dropdown (default)
        // Kiểm tra 1 element đã được chọn rồi hay chưa chọn
        element.isSelected();

        // Áp dụng cho tất cả các loại
        // Kiểm tra 1 element có bị disable hay không (read only)
        element.isEnabled();

        element.getCssValue("background-color");

        // GUI: Font/ Size/ Color/ Position/ Location/...
        element.getCssValue("font-size");

        // Áp dụng cho element chứa text (Link/ Button/ Header/ Label/...)
        element.getText();

        element.getAttribute("placeholder");

        // Chiều rộng/ cao của browser?
        Dimension dimensionBrowser = driver.manage().window().getSize();

        // Chiều rộng/ cao của element?
        Dimension dimensionElement =  element.getSize();

        Point pointBrowser = driver.manage().window().getPosition();

        // Vị trí của element so với viewport
        Point pointElement = element.getLocation();

        Rectangle rectangle = element.getRect();

        // Size
        rectangle.getWidth();
        rectangle.getHeight();
        rectangle.getDimension();

        // Location
        rectangle.getX();
        rectangle.getY();
        rectangle.getPoint();

        // Lấy ra cái thẻ html của element đó
        // Element A
        String tagname = driver.findElement(By.cssSelector("#Firstname")).getTagName();

        // Element B
        driver.findElement(By.xpath("//" + tagname + "[@id='LastName']"));

        element.getAccessibleName();

        element.getAriaRole();

        element.getDomAttribute("data-val-required");

        element.getDomProperty("formAction");

        // Popup
        element.getShadowRoot();

        // Framework: HTML report
        element.getScreenshotAs(OutputType.FILE);
        element.getScreenshotAs(OutputType.BYTES);
        element.getScreenshotAs(OutputType.BASE64);
    }
}