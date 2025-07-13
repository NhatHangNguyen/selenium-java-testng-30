package webdriver;

import org.openqa.selenium.By;
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
import java.util.Arrays;
import java.util.List;


public class Topic_14_Custom_Dropdown_Exercise {

    WebDriver driver;

    WebDriverWait explicitWait;

    @BeforeClass
    public void initialBrowser(){

        driver = new ChromeDriver();

        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_JQuery_Honda() throws InterruptedException {
        driver.get("https://www.honda.com.vn/o-to/du-toan-chi-phi");

        // Chọn xe
        selectItemInCustomDropdown("button#selectize-input", "div.dropdown div.dropdown-menu a", "BR-V L (Trắng ngà)");
        Assert.assertEquals(driver.findElement(By.cssSelector("button#selectize-input")).getText(),"BR-V L (Trắng ngà)");

        // Chọn tỉnh/thành phố
        selectItemInCustomDropdown("select#province", "select#province option", "Nghệ An");

        Select selectProvince = new Select(driver.findElement(By.cssSelector("select#province")));
        String selectedProvince = selectProvince.getFirstSelectedOption().getText();
        Assert.assertEquals(selectedProvince, "Nghệ An");

        //  Chọn khu vực
        selectItemInCustomDropdown("select.registration","select.registration option", "Khu vực II");

        // Chờ đến khi option 'Khu vực I' thực sự xuất hiện trong dropdown
        explicitWait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("select.registration"), "Khu vực II"));

        Select selectRegistration = new Select(driver.findElement(By.cssSelector("select.registration")));
        String selectedRegistration = selectRegistration.getFirstSelectedOption().getText();
        Assert.assertEquals(selectedRegistration, "Khu vực II");
    }

    @Test
    public void TC_02_Multiple_Select() throws InterruptedException {
        driver.get("https://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");

        // Ex1: Basic select
        Select select = new Select(driver.findElement(By.xpath("//label[normalize-space()='Basic Select']/following-sibling::div/select")));
        select.selectByVisibleText("March");
        Assert.assertEquals(select.getFirstSelectedOption().getText(),"March");

        // Ex 2: Multiple Select
        // Case 1: chọn 3 tháng → hiện tên
        clearDropdownSelection(1);
        List<String> case1 = Arrays.asList("June", "July", "March");
        selectMonthsFromDropdown(case1, 1);
        Assert.assertEquals(
                driver.findElement(By.xpath("(//button[contains(@class, 'ms-choice')])[1]/span")).getText().trim(),
                "March, June, July"
        );

        // Case 2: chọn > 3 tháng → hiện "x of 12 selected"
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.ByClassName.xpath("(//button[contains(@class, 'ms-choice')])[1]"))).click();
        Thread.sleep(500);
        clearDropdownSelection(1);
        List<String> case2 = Arrays.asList("June", "July", "March", "May");
        selectMonthsFromDropdown(case2, 1);
        Assert.assertEquals(
                driver.findElement(By.xpath("(//button[contains(@class, 'ms-choice')])[1]/span")).getText().trim(),
                case2.size() + " of 12 selected"
        );

        // Case 3: chọn đủ 12 tháng
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.ByClassName.xpath("(//button[contains(@class, 'ms-choice')])[1]"))).click();
        Thread.sleep(500);
        clearDropdownSelection(1);
        List<String> case3 = Arrays.asList(
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        );
        selectMonthsFromDropdown(case3, 1);
        Assert.assertEquals(
                driver.findElement(By.xpath("(//button[contains(@class, 'ms-choice')])[1]/span")).getText().trim(),
                "All selected"
        );
    }

    private void selectItemInCustomDropdown(String parentCss, String  childCss, String textItem) throws InterruptedException {
        // Xác định phần tử dropdown
        WebElement dropdownElement = explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(parentCss)));

        // Case 1: Default dropdown
        if (dropdownElement.getTagName().equals("select")) {
            Select select = new Select(dropdownElement);
            select.selectByVisibleText(textItem);
            return;
        }

        // Case 2: Custom dropdown list
        explicitWait.until(ExpectedConditions.elementToBeClickable(dropdownElement)).click();
        Thread.sleep(500);

        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childCss)));
        for(WebElement item: allItems) {
            if (item.getText().equals(textItem)) {
                item.click();
                break;
            }
        }
    }

    private void selectMonthsFromDropdown(List<String> monthsToSelect, int dropdownIndex) throws InterruptedException {
        // Phân loại theo số lượng tháng mà user muốn click
        if (monthsToSelect.size() == 12){
            System.out.println("Selected all 12 months");
        } else if (monthsToSelect.size() > 3) {
            System.out.println("Selectes more than 3 months");
        } else {
            System.out.println("Selected 3 or fewer months");
        }

        // Click on dropdown
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.ByClassName.xpath("(//button[contains(@class, 'ms-choice')])[1]"))).click();
        Thread.sleep(500);

        // Lấy toàn bộ các item trong dropdown
        // Chờ cho các items trong dropdown hiện thị và có thể clickable
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("(//button[contains(@class, 'ms-choice')])[1]/following-sibling::div/ul/li")));
        for(WebElement item: allItems){
            String text = item.getText().trim();
            if(monthsToSelect.contains(text)){
                item.click();
            }
        }
    }

    private void clearDropdownSelection(int dropdownIndex) throws InterruptedException {
        // Click mở lại dropdown
        WebElement dropdown = explicitWait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(@class, 'ms-choice')])[" + dropdownIndex + "]"))
        );
        dropdown.click();
        Thread.sleep(300);

        // Click vào Select all 2 lần để clear
        WebElement selectAll = explicitWait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//button[contains(@class, 'ms-choice')])[" + dropdownIndex + "]/following-sibling::div//li[contains(., '[Select all]')]")
        ));
        selectAll.click(); // chọn tất cả
        Thread.sleep(200);
        selectAll.click(); // bỏ chọn tất cả

        // Đóng dropdown
        dropdown.click();
    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}