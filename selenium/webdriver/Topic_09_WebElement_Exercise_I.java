package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_09_WebElement_Exercise_I {

    WebDriver driver;

    @BeforeClass
    public void initialBrowser(){

        driver = new EdgeDriver();
    }

    @Test
    public void TC_01_Displayed() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        WebElement emailTextbox = driver.findElement(By.cssSelector("input#mail"));

        if (emailTextbox.isDisplayed()) {
            System.out.println("Email Textbox is displayed");
            emailTextbox.sendKeys("Automation Testing");
        } else {
            System.out.println("Email Textbox isn't displayed");
        }

        WebElement ageUnder18RadioButton = driver.findElement(By.xpath("//label[text()='Under 18']"));

        if (ageUnder18RadioButton.isDisplayed()) {
            System.out.println("Age Under 18 Radio button is displayed");
            ageUnder18RadioButton.click();
        } else {
            System.out.println("Age Under 18 Radio button  is not displayed");
        }

        WebElement educationTextbox = driver.findElement(By.cssSelector("textarea#edu"));

        if (educationTextbox.isDisplayed()) {
            System.out.println("Education is displayed");
            educationTextbox.sendKeys("Automation Testing");
        } else {
            System.out.println("Education is not displayed");
        }

        WebElement user05Text = driver.findElement(By.xpath("//h5[contains(text(), 'User5')]"));

        if (user05Text.isDisplayed()){
            System.out.println("User05 text is displayed");
        } else {
            System.out.println("User05 text is not displayed");
        }

        // Assert.assertFalse(driver.findElement(By.xpath("//h5[contains(text(), 'User5')]")).isDisplayed());
    }

    @Test
    public void TC_02_Enabled() {
        // isDisplayed: kiểm tra bất kì 1 element nào có thể tương tác lên được # ngược lại với read-only (disable)
        // Hiện thị: có thể nhìnt thấy - có kích thước cụ thể

        driver.get("https://automationfc.github.io/basic-form/index.html");

        WebElement emailTextbox = driver.findElement(By.cssSelector("input#mail"));

        if (emailTextbox.isEnabled()) {
            System.out.println("Email Textbox is enabled");
        } else {
            System.out.println("Email Textbox is disabled");
        }

        WebElement ageUnder18RadioButton = driver.findElement(By.xpath("//label[text()='Under 18']"));

        if (ageUnder18RadioButton.isEnabled()) {
            System.out.println("Age Under 18 Radio button is enabled");
        } else {
            System.out.println("Age Under 18 Radio button  is disabled");
        }

        WebElement educationTextbox = driver.findElement(By.cssSelector("textarea#edu"));

        if (educationTextbox.isEnabled()) {
            System.out.println("Education is enabled");
        } else {
            System.out.println("Education is disabled");
        }

        WebElement jobRole01Textbox = driver.findElement(By.cssSelector("select#job1"));
        WebElement jobRole02Textbox = driver.findElement(By.cssSelector("select#job2"));

        if (jobRole01Textbox.isEnabled() && jobRole02Textbox.isEnabled()) {
            System.out.println("Both Job Role 01 and Job Role 02 are enabled");
        } else {
            System.out.println("One or both Job Roles are disabled");
        }

        WebElement interestsDevelopmentCheckbox = driver.findElement(By.cssSelector("input#development"));

        if (interestsDevelopmentCheckbox.isEnabled()) {
            System.out.println("Interests (Development) is enabled");
        } else {
            System.out.println("Interests (Development) is disabled");
        }

        WebElement slide01 = driver.findElement(By.cssSelector("input#slider-1"));

        if (slide01.isEnabled()) {
            System.out.println("Slide 01 is enabled");
        } else {
            System.out.println("Slide 01 is disabled");
        }

        WebElement password = driver.findElement(By.cssSelector("input#disable_password"));

        if (password.isEnabled()) {
            System.out.println("Password is enabled");
        } else {
            System.out.println("Password is disabled");
        }

        WebElement age = driver.findElement(By.xpath("//label[text()='Radio button is disabled']"));

        if (age.isEnabled()) {
            System.out.println("Age is enabled");
        } else {
            System.out.println("Age is disabled");
        }

        WebElement biography = driver.findElement(By.xpath("//textarea[@disabled='disabled']"));

        if (biography.isEnabled()) {
            System.out.println("Biography is enabled");
        } else {
            System.out.println("biography is disabled");
        }

        WebElement jobRole03Textbox = driver.findElement(By.cssSelector("select#job3"));

        if (jobRole03Textbox.isEnabled()) {
            System.out.println("Job Role 03 is enabled");
        } else {
            System.out.println("Job Roles 03 is disabled");
        }

        WebElement interestsCheckbox = driver.findElement(By.cssSelector("input#radio-disabled"));

        if (interestsCheckbox.isEnabled()) {
            System.out.println("Interests Checkbox is enabled");
        } else {
            System.out.println("Interests Checkbox is disabled");
        }

        WebElement slide02 = driver.findElement(By.cssSelector("input#slider-2"));

        if (slide02.isEnabled()) {
            System.out.println("Slide 02 is enabled");
        } else {
            System.out.println("Slide 02 is disabled");
        }
    }

    @Test
    public void TC_03_Selected(){
        // isSelected: Kiểm tra 1 element được chọn thành công (apply: Radio/ Checkbox/ Dropdown)
        driver.get("https://automationfc.github.io/basic-form/index.html");

        WebElement ageUnder18RadioButton = driver.findElement(By.cssSelector("input#under_18"));

        if (ageUnder18RadioButton.isSelected()){
            System.out.println("Age Under 18 Radio Button is selected");
        } else {
            System.out.println("Age Under 18 Radio Button is de-selected");
        }

        WebElement languaguesJavaCheckbox = driver.findElement(By.cssSelector("input#java"));

        if (languaguesJavaCheckbox.isSelected()){
            System.out.println("Languagues Java Checkbox is selected");
        } else {
            System.out.println("Languagues Java Checkbox is de-selected");
        }

        ageUnder18RadioButton.click();
        languaguesJavaCheckbox.click();

        if (ageUnder18RadioButton.isSelected()){
            System.out.println("Age Under 18 Radio Button is selected");
        } else {
            System.out.println("Age Under 18 Radio Button is de-selected");
        }

        if (languaguesJavaCheckbox.isSelected()){
            System.out.println("Languagues Java Checkbox is selected");
        } else {
            System.out.println("Languagues Java Checkbox is de-selected");
        }
    }

    @Test
    public void TC_04_MailChimp_Register_Validate(){
        driver.get("https://login.mailchimp.com/signup/");

        driver.findElement(By.cssSelector("input#email")).sendKeys("automationfc.vn@gmail.com");
        driver.findElement(By.cssSelector("input#email")).sendKeys(Keys.TAB);

        // Only number
        driver.findElement(By.id("new_password")).sendKeys("12345");
        driver.findElement(By.id("new_password")).sendKeys(Keys.TAB);

        // Validate
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        // Only lower text
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("auto");
        driver.findElement(By.id("new_password")).sendKeys(Keys.TAB);

        // Validate
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.not-completed")).isDisplayed());

        // Only upper text
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("AUTO");
        driver.findElement(By.id("new_password")).sendKeys(Keys.TAB);

        // Validate
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.not-completed")).isDisplayed());

        // Only specical text
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("$#%^$%#");
        driver.findElement(By.id("new_password")).sendKeys(Keys.TAB);

        // Validate
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        // Only > 8 character
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("Auto123$#@");
        driver.findElement(By.id("new_password")).sendKeys(Keys.TAB);

        // Validate
        Assert.assertFalse(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());
    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}