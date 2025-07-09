package webdriver;

import org.bouncycastle.dvcs.DVCSRequestInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.lang.model.element.Element;
import java.time.Duration;
import java.util.List;


public class Topic_13_Custom_Dropdown {

    WebDriver driver;

    WebDriverWait explicitWait;

    @BeforeClass
    public void initialBrowser(){

        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_JQuery() throws InterruptedException {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

        selectItemInCustomDropdown("span#speed-button", "ul#speed-menu>li>div", "Slower");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Slower");

        selectItemInCustomDropdown("span#files-button", "ul#files-menu>li>div", "Some unknown file");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#files-button>span.ui-selectmenu-text")).getText(), "Some unknown file");

        selectItemInCustomDropdown("span#number-button", "ul#number-menu>li>div", "18");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "18");

        selectItemInCustomDropdown("span#salutation-button", "ul#salutation-menu>li>div", "Prof.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Prof.");

    }

    @Test
    public void TC_02_React() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

        selectItemInCustomDropdown("div.dropdown", "div.item>span", "Matt");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.text")).getText(), "Matt");

        selectItemInCustomDropdown("div.dropdown", "div.item>span", "Christian");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.text")).getText(), "Christian");
    }

    @Test
    public void TC_03_VueJs() throws InterruptedException {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");

        selectItemInCustomDropdown("li.dropdown-toggle", "ul.dropdown-menu>li>a", "Third Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");

        selectItemInCustomDropdown("li.dropdown-toggle", "ul.dropdown-menu>li>a", "Second Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");

        selectItemInCustomDropdown("li.dropdown-toggle", "ul.dropdown-menu>li>a", "First Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");
    }

    private void selectItemInCustomDropdown(String parentCss, String childCss, String textItem) throws InterruptedException {
        // Hành vi (behavior) để thao tác lên Dropdown
        // 1 - Chờ cho dropdown có thể thao tác lên được (clickable)
        // 2 - Click element nào để nó xổ ra cái dropdown ra
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(parentCss))).click();
        Thread.sleep(2000);

        // 3 - Chờ cho tất cả các items được load ra (presence)
        // 4 - Tìm cho items nào đúng với mong đợi
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childCss)));

        // 5 items
        for(WebElement item: allItems){
            if(item.getText().equals(textItem)){
                // 5-  Click lên item đó
                item.click();
                break;
            }
        }
    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}