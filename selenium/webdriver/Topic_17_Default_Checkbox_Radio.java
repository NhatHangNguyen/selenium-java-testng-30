package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_17_Default_Checkbox_Radio {

    WebDriver driver;

    @BeforeClass
    public void initialBrowser(){

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_02_Telerik() throws InterruptedException {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300)");

        // Verify checkbox/ radio button is anabled/ disabled
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Leather trim']/preceding-sibling::span/input")).isEnabled());
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Towbar preparation']/preceding-sibling::span/input")).isEnabled());

        //Verify checkbox/ radio button is selected/ deselected
        // Element HTML: input
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Leather trim']/preceding-sibling::span/input")).isSelected());
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Towbar preparation']/preceding-sibling::span/input")).isSelected());

        // Select to "Dual-zone air conditioning" checkbox
        By duazlZoneAirBy = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input");

        // Scroll xuon them 1 doan
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300)");

        // Nếu như chưa chọn thì mới click vào checkbox
        if(!driver.findElement(duazlZoneAirBy).isSelected()){
            driver.findElement(duazlZoneAirBy).click();
        }
        Assert.assertTrue(driver.findElement(duazlZoneAirBy).isSelected());

        // Deselect to "Dual-zone air conditioning" checkbox (bỏ chọn)
        if(driver.findElement(duazlZoneAirBy).isSelected()){
            driver.findElement(duazlZoneAirBy).click();
        }
        Assert.assertFalse(driver.findElement(duazlZoneAirBy).isSelected());

        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300)");

        By twoPetrol = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input");
        // Nếu như chưa chọn thì mới click vào checkbox
        if(!driver.findElement(twoPetrol).isSelected()){
            driver.findElement(twoPetrol).click();
        }
        Assert.assertTrue(driver.findElement(twoPetrol).isSelected());
    }

    @Test
    public void TC_03_Material() throws InterruptedException {
        driver.get("https://material.angular.dev/components/radio/examples");

        By summerRadio = By.xpath("//input[@value='Summer']");
        if(!driver.findElement(summerRadio).isSelected()){
            driver.findElement(summerRadio).click();
        }
        Assert.assertTrue(driver.findElement(summerRadio).isSelected());

        Thread.sleep(3000);

        driver.get("https://material.angular.dev/components/checkbox/examples");

        By checked = By.xpath("//label[text()='Checked']/preceding-sibling::div/input");
        By indeterminate = By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input");

        if(!driver.findElement(checked).isSelected() && !driver.findElement(indeterminate).isSelected()){
            driver.findElement(checked).click();
            driver.findElement(indeterminate).click();
        }

        Assert.assertTrue(driver.findElement(checked).isSelected());
        Assert.assertTrue(driver.findElement(indeterminate).isSelected());

        Thread.sleep(3000);

        if(driver.findElement(checked).isSelected() && driver.findElement(indeterminate).isSelected()){
            driver.findElement(checked).click();
            driver.findElement(indeterminate).click();
        }

        Assert.assertFalse(driver.findElement(checked).isSelected());
        Assert.assertFalse(driver.findElement(indeterminate).isSelected());
    }

    @Test
    public void TC_04_AutomationFC() {
        driver.get("https://automationfc.github.io/multiple-fields/");

        // Select all checkboxes
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("span.form-checkbox-item>input"));

        // Click all checkboxes (tạo 1 biến số ít để duyệt qua từng items trong mảng )
        for(WebElement checkbox: checkboxes){
            if(!checkbox.isSelected()){
                checkbox.click();
            }
        }

        // Verify all checkboxes selected
        for(WebElement checkbox: checkboxes){
            Assert.assertTrue(checkbox.isSelected());
        }

        // Deselect all checkboxes
        for(WebElement checkbox: checkboxes){
            if(checkbox.isSelected()){
                checkbox.click();
            }
        }

        // Verify all checkboxes deselect
        for(WebElement checkbox: checkboxes){
            Assert.assertFalse(checkbox.isSelected());
        }

        // Select 1 in all + verify
        driver.findElement(By.cssSelector("input[value='Bleeding Disorders']")).click();
        driver.findElement(By.cssSelector("input[value='Neurological Disorders']")).click();
        driver.findElement(By.cssSelector("input[value='Lung Disease']")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("input[value='Bleeding Disorders")).isSelected());
        Assert.assertTrue(driver.findElement(By.cssSelector("input[value='Neurological Disorders")).isSelected());
        Assert.assertTrue(driver.findElement(By.cssSelector("input[value='Lung Disease")).isSelected());

        for (WebElement checkbox: checkboxes){
            if(!checkbox.isSelected() && checkbox.getAttribute("value").equals("Emphysema")){
                checkbox.click();
            }
        }
        Assert.assertTrue(driver.findElement(By.cssSelector("input[value='Emphysema")).isSelected());
    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}