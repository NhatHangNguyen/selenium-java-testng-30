package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class Topic_28_Windows_Tab_Part_II {

    WebDriver driver;

    Select select;

    @BeforeClass
    public void initialBrowser() {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_14_TechPanda() throws InterruptedException {
        // 1 - Access to website page
        driver.get("http://live.techpanda.org/");

        // 2 - Click on Mobile tab
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        Thread.sleep(3000);

        // 3 - Add Sony Xperia into "Add to Compare" button
        // 3.1 - Verify text is displayed "The product Sony Xperia has been added to comparison list."
        addProductToCompare("//a[@title='Sony Xperia']/ancestor::div[@class='product-info']//a[text()='Add to Compare']");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The product Sony Xperia has been added to comparison list.");

        // 4 - Add Samsung Galaxy into "Add to Compare" button
        // 4.1 - Verify text is displayed "The product Samsung Galaxy  has been added to comparison list."
        addProductToCompare("//a[@title='Samsung Galaxy']/ancestor::div[@class='product-info']//a[text()='Add to Compare']");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The product Samsung Galaxy has been added to comparison list.");

        // 5  - Click on "Compare" button
        driver.findElement(By.cssSelector("button[title='Compare']")).click();
        Thread.sleep(3000);

        // 6- Switch to a new window
        // 6.1 - Get ID của window mà driver đang active mà tại page đó
        // 6.2 - Switch qua new window includes 2 products just added to compare
        String parentWindowID = driver.getWindowHandle();
        switchToWindowById(parentWindowID);
        Thread.sleep(3000);

        // 7 - Verify title của cửa sổ bằng: Products Comparison List - Magento Commerce
        Assert.assertEquals(driver.getTitle(),"Products Comparison List - Magento Commerce");

        // 8 - Close tab va chuyen ve parent window
        driver.findElement(By.cssSelector("button[title='Close Window']")).click();
        driver.switchTo().window(parentWindowID);
        Thread.sleep(3000);

        // 9 - Click "Clear All" link va accept button
        driver.findElement(By.xpath("//a[text()='Clear All']")).click();
        Thread.sleep(2000);

        Assert.assertEquals(driver.switchTo().alert().getText(),"Are you sure you would like to remove all products from your comparison?");
        driver.switchTo().alert().accept();

        // 10 - Verify msg dispaly: The comparison list was cleared.
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The comparison list was cleared.");
    }

    @Test
    public void TC_15_Cambridge() throws InterruptedException {
        driver.get("https://dictionary.cambridge.org/vi/");

        driver.findElement(By.xpath("//div[@class='pr hdib lpr-5' and @amp-access='NOT loggedIn']//span[text()='Đăng nhập']")).click();
        Thread.sleep(3000);

        switchToWindowByTitle("Login");
        Thread.sleep(3000);

        driver.findElement(By.cssSelector("input[value='Log in']")).click();
        Thread.sleep(3000);

        Assert.assertTrue(driver.findElement(By.xpath("//input[@aria-label='Email']/following-sibling::span[text()='This field is required']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//input[@aria-label='Password']/following-sibling::span[text()='This field is required']")).isDisplayed());

        driver.close();

        switchToWindowByTitle("Cambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa");

        driver.findElement(By.cssSelector("input#searchword")).sendKeys("automation");
        driver.findElement(By.cssSelector("button[aria-label='Search']")).click();
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.xpath("(//div[@class='di-title']/span[contains(@class,'headword')])[1]/span")).getText(),"automation");
    }

    @Test
    public void TC_16_Harvard() throws InterruptedException {
        driver.get("https://courses.dce.harvard.edu/");

        driver.findElement(By.cssSelector("a[data-action='login']")).click();
        Thread.sleep(2000);

        String parentWindowID = driver.getWindowHandle();
        switchToWindowById(parentWindowID);
        Thread.sleep(3000);

        Assert.assertTrue(driver.getTitle().contains("Login Portal"));

        driver.close();

        switchToWindowByTitle("DCE Course Search");

        // Chờ popup hiển thị
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#sam-wait")));

        ((JavascriptExecutor) driver).executeScript(
                "document.querySelector('div#sam-wait').remove();"
        );

        driver.navigate().refresh();

        WebElement keywordElement = driver.findElement(By.cssSelector("input#crit-keyword"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", keywordElement);
        Thread.sleep(3000);
        keywordElement.sendKeys("Data Science: An Artificial Ecosystem");

        WebElement srcdbElement = driver.findElement(By.cssSelector("select#crit-srcdb"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", srcdbElement);
        Thread.sleep(10000);
        Select selectSrcdb = new Select(srcdbElement);
        selectSrcdb.selectByVisibleText("Harvard Summer School 2025");
        Thread.sleep(2000);

        // Scroll tới dropdown kế tiếp và chọn option
        WebElement summerSchoolElement = driver.findElement(By.cssSelector("select#crit-summer_school"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", summerSchoolElement);
        Thread.sleep(10000);
        Select selectSummerSchool = new Select(summerSchoolElement);
        selectSummerSchool.selectByVisibleText("Harvard College");
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("button#search-button")).click();
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("span.result__headline span.result__title")).getText(),"Data Science: An Artificial Ecosystem");
    }

    private void addProductToCompare(String xpathExpression) throws InterruptedException {
        driver.findElement(By.xpath(xpathExpression)).click();
        Thread.sleep(3000);
    }

    private void switchToWindowByTitle(String expectedPageTitle) throws InterruptedException {
        // lấy hết toàn bộ các ID của window/ tab
        Set<String> allWindowIDs = driver.getWindowHandles();

        // dùng vòng lặp duyệt qua từng ID
        for (String id : allWindowIDs){
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

    private void switchToWindowById(String windowId){
        Set<String> allWindowIDs = driver.getWindowHandles();
        for(String id : allWindowIDs){
            if(!id.equals(windowId)){
                driver.switchTo().window(id);
            }
        }
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }
}