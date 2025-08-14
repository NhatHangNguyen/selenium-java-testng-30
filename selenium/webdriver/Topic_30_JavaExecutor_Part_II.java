package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_30_JavaExecutor_Part_II {

    WebDriver driver;
    JavascriptExecutor jsExecutor;
    WebDriverWait explicitWait;
    String email;

    @BeforeClass
    public void initialBrowser(){

        driver = new ChromeDriver();
        jsExecutor = (JavascriptExecutor) driver; // Ép kiểu tường minh
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        email = "automation" + new Random().nextInt(9999) + "@gmail.com";

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_TechPanda() throws InterruptedException {
        jsExecutor.executeScript("window.location = 'https://live.techpanda.org/'");
        explicitWait.until(ExpectedConditions.urlToBe("https://live.techpanda.org/"));

        String techPandaDomain = (String) jsExecutor.executeScript("return document.domain;");
        Assert.assertEquals(techPandaDomain,"live.techpanda.org");

        String homePageURL = (String) jsExecutor.executeScript("return document.URL;");
        Assert.assertEquals(homePageURL,"https://live.techpanda.org/");

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Mobile']")));
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='Mobile']")));

        explicitWait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button")));
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(
                By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button")));
        Thread.sleep(3000);

        String samsungText = (String) jsExecutor.executeScript("return document.documentElement.innerText;");
        Assert.assertTrue(samsungText.contains("Samsung Galaxy was added to your shopping cart."));

        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='Customer Service']")));
        Thread.sleep(3000);

        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector("input#newsletter")));
        Thread.sleep(3000);
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + email + "')", driver.findElement(By.cssSelector("input#newsletter")));

        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("button[title='Subscribe']")));
        Thread.sleep(5000);

        String subscriptionText = (String) jsExecutor.executeScript("return document.documentElement.innerText;");
        Assert.assertTrue(subscriptionText.contains("Thank you for your subscription."));

        jsExecutor.executeScript("window.location = 'https://www.facebook.com/'");
        explicitWait.until(ExpectedConditions.urlToBe("https://www.facebook.com/"));
    }

    @Test
    public void TC_02_TechPanda() throws InterruptedException {
        navigateToUrlByJS("https://live.techpanda.org/");

        Assert.assertEquals(getDomain(),"live.techpanda.org");
        Assert.assertEquals(getPageURL(),"https://live.techpanda.org/");

        clickToElementByJS("//a[text()='Mobile']");

        clickToElementByJS("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");

        Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));

        clickToElementByJS("//a[text()='Customer Service']");

        scrollToElementOnTop("//input[@id='newsletter']");

        setAttributeInDOM("//input[@id='newsletter']", "value", email);

        clickToElementByJS("//button[@title='Subscribe']");
        Thread.sleep(3000);

        Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));

        navigateToUrlByJS("https://www.facebook.com/");
    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }

    public Object executeForBrowser(String javaScript) {
        return jsExecutor.executeScript(javaScript);
    }

    public String getPageURL() {
        return (String) jsExecutor.executeScript("return document.URL");
    }

    public String getDomain() {
        return (String) jsExecutor.executeScript("return document.domain");
    }

    public String getInnerText() {
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextInInnerText(String textExpected) {
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void sleepInSecond(int timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void navigateToUrlByJS(String url) {
        jsExecutor.executeScript("window.location = '" + url + "'");
        sleepInSecond(3);
    }

    public void hightlightElement(String locator) {
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(String locator) {
        jsExecutor.executeScript("arguments[0].click();", getElement(locator));
        sleepInSecond(3);
    }

    public String getElementTextByJS(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].textContent;", getElement(locator));
    }

    public void scrollToElementOnTop(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void scrollToElementOnDown(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
    }

    public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
        jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    public void sendkeyToElementByJS(String locator, String value) {
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
    }

    public String getAttributeInDOM(String locator, String attributeName) {
        return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
    }

    public String getElementValidationMessage(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public boolean isImageLoaded(String locator) {
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
        return status;
    }
}