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
    public void TC_01_1_TechPanda() throws InterruptedException {
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

    @Test
    public void TC_02_Github() throws InterruptedException {
        driver.get("https://automationfc.github.io/html5/index.html");

        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit']"));

        // Name field - Empty: Click Submit and verify msg display at Name textbox
        submitButton.click();
        Assert.assertEquals(getElementValidationMessage("//input[@id='fname']"), "Please fill out this field.");
        Thread.sleep(3000);

        // Input data into Name and click Submit - verify message display field Password textbox
        String nameFieldData = "Nhat Hang";
        driver.findElement(By.cssSelector("input#fname")).sendKeys(nameFieldData);
        submitButton.click();
        String emptyPasswordMessage = getElementValidationMessage("//input[@id='pass']");
        Assert.assertEquals(emptyPasswordMessage,"Please fill out this field.");
        Thread.sleep(3000);

        // Input data into Password and click Submit - verify message display field Email textbox
        String passwordFieldData = "NhatHang";
        driver.findElement(By.cssSelector("input#pass")).sendKeys(passwordFieldData);
        submitButton.click();
        String emptyEmailMessage = getElementValidationMessage("//input[@id='em']");
        Assert.assertEquals(emptyEmailMessage,"Please fill out this field.");
        Thread.sleep(3000);

        // Email fields
        // Case 1: Invalid "aaa"
        String invalidEmailData = "aaa";
        driver.findElement(By.xpath("//input[@id='em']")).sendKeys(invalidEmailData);
        submitButton.click();
        Thread.sleep(3000);

        String invalidEmailMessage = getElementValidationMessage("//input[@id='em']");

        if (driver.toString().contains("Chrome")) {
            Assert.assertEquals(invalidEmailMessage, "Please include an '@' in the email address. '" + invalidEmailData + "' is missing an '@'.");
        }
        else{
            Assert.assertEquals(invalidEmailMessage, "Please enter an email address.");
        }
        
        // Case 2: Only "aaa@";
        invalidEmailData = "aaa@";
        driver.findElement(By.xpath("//input[@id='em']")).clear();
        driver.findElement(By.xpath("//input[@id='em']")).sendKeys(invalidEmailData);
        submitButton.click();
        Thread.sleep(3000);

        invalidEmailMessage = getElementValidationMessage("//input[@id='em']");

        if (driver.toString().contains("Chrome")) {
            Assert.assertEquals(invalidEmailMessage, "Please enter a part following '@'. '" + invalidEmailData + "' is incomplete.");
        }
        else{
            Assert.assertEquals(invalidEmailMessage, "Please enter an email address.");
        }

        // Case 3: aaa@aaa.
        invalidEmailData = "aaa@aaa.";
        driver.findElement(By.xpath("//input[@id='em']")).clear();
        driver.findElement(By.xpath("//input[@id='em']")).sendKeys(invalidEmailData);
        submitButton.click();
        Thread.sleep(3000);

        invalidEmailMessage = getElementValidationMessage("//input[@id='em']");

        if (driver.toString().contains("Chrome")) {
            Assert.assertEquals(invalidEmailMessage, "'.' is used at a wrong position in '" + invalidEmailData.split("@")[1] + "'.");
        }
        else{
            Assert.assertEquals(invalidEmailMessage, "Please enter an email address.");
        }

        // Case 4: Email - valid
        driver.findElement(By.xpath("//input[@id='em']")).clear();
        driver.findElement(By.xpath("//input[@id='em']")).sendKeys(email);
        submitButton.click();
        Thread.sleep(3000);

        Assert.assertEquals(getElementValidationMessage("//select[@required]"), "Please select an item in the list.");
    }

    @Test
    public void TC_03_Role() {
        driver.get("https://account.rode.com/login");

        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

        // Case 1: Email - Empty
        loginButton.click();
        String emptyEmailMessage = getElementValidationMessage("//input[@id='email']");
        Assert.assertEquals(emptyEmailMessage, "Please fill out this field.");

        // Case 2: Email - Invalid
        String invalidEmailData = "aaa";
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(invalidEmailData);
        loginButton.click();

        String invalidEmailMessage = getElementValidationMessage("//input[@id='email']");

        // Chrome: "Please include an '@' in the email address. 'aaa' is missing an '@'."
        // Firefox: Please enter an email address.
        if (driver.toString().contains("Chrome")) {
            Assert.assertEquals(invalidEmailMessage, "Please include an '@' in the email address. '" + invalidEmailData + "' is missing an '@'.");
        }
        else{
            Assert.assertEquals(invalidEmailMessage, "Please enter an email address.");
        }

        // Case 3: Email - Only @
        invalidEmailData = "aaa@";
        driver.findElement(By.xpath("//input[@id='email']")).clear();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(invalidEmailData);
        loginButton.click();

        invalidEmailMessage = getElementValidationMessage("//input[@id='email']");

        // Chrome: "Please enter a part following '@'. 'aaa@' is incomplete."
        // Firefox: Please enter an email address.
        if (driver.toString().contains("Chrome")) {
            Assert.assertEquals(invalidEmailMessage, "Please enter a part following '@'. '" + invalidEmailData + "' is incomplete.");
        }
        else{
            Assert.assertEquals(invalidEmailMessage, "Please enter an email address.");
        }

        // Case 4: Email - aaa@aaa.
        invalidEmailData = "aaa@aaa.";
        driver.findElement(By.xpath("//input[@id='email']")).clear();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(invalidEmailData);
        loginButton.click();

        invalidEmailMessage = getElementValidationMessage("//input[@id='email']");

        // Chrome: "'.' is used at a wrong position in 'aaa.'."
        // Firefox: Please enter an email address.
        if (driver.toString().contains("Chrome")) {
            Assert.assertEquals(invalidEmailMessage, "'.' is used at a wrong position in '" + invalidEmailData.split("@")[1] + "'.");
        }
        else{
            Assert.assertEquals(invalidEmailMessage, "Please enter an email address.");
        }

        // Case 5: Email - valid
        driver.findElement(By.xpath("//input[@id='email']")).clear();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
        loginButton.click();

        Assert.assertEquals(getElementValidationMessage("//input[@id='password']"), "Please fill out this field.");
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
