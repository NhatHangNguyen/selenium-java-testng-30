package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.Random;

public class Topic_10_TextBox_TextArea_Exercise {

    WebDriver driver;

    Random rand;

    String firstName, lastName, emailAddress, password, fullName,
            userNameLogIn, passwordLogIn, employeeId, firstNameEmployee, lastNameEmployee, passwordEmployee;

    @BeforeClass
    public void initialBrowser(){

        driver = new ChromeDriver();
        rand = new Random();

        firstName = "Joe";
        lastName = "Biden";
        fullName = firstName + " " + lastName;
        emailAddress = "joebiden" + rand.nextInt(99999) + "@gmail.com";
        password = "123456789";
        userNameLogIn = "Admin";
        passwordLogIn = "admin123";
        firstNameEmployee = "Automation";
        lastNameEmployee = "FC";
        passwordEmployee = "12345678aa";

        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_TechPanda() throws InterruptedException {
        driver.get("https://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

       driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstName);
       driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastName);
       driver.findElement(By.cssSelector("input#email_address")).sendKeys(emailAddress);
       driver.findElement(By.cssSelector("input#password")).sendKeys(password);
       driver.findElement(By.cssSelector("input#confirmation")).sendKeys(password);

       driver.findElement(By.xpath("//button[@title='Register']")).click();

        Thread.sleep(30000);

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement insecureFormBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button#proceed-button")));
            insecureFormBtn.click();
        } catch (TimeoutException ignored) {
        }

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),
                "Thank you for registering with Main Website Store.");

        String contactInformationText = driver.findElement(By.xpath(
                "//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        System.out.println(contactInformationText);

        // Tương đối
        Assert.assertTrue(contactInformationText.contains(fullName) && contactInformationText.contains(emailAddress)); // Full name + Email

        driver.findElement(By.xpath("//h3[text()='Contact Information']/following-sibling::a")).click();

        Assert.assertEquals(driver.findElement(By.id("firstname")).getAttribute("value"),firstName);
        Assert.assertEquals(driver.findElement(By.id("lastname")).getAttribute("value"),lastName);
        Assert.assertEquals(driver.findElement(By.id("email")).getAttribute("value"),emailAddress);

        // Product Review
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        driver.findElement(By.xpath("//h2/a[@title='Samsung Galaxy']")).click();
        driver.findElement(By.xpath("//a[text()='Add Your Review']")).click();
        driver.findElement(By.cssSelector("input[id='Quality 1_5']")).click();
        driver.findElement(By.cssSelector("textarea#review_field")).sendKeys("Good application\n" +
                "Pretty easy to navigate.");
        driver.findElement(By.cssSelector("input#summary_field")).sendKeys("Good Phone");
        driver.findElement(By.cssSelector("input#nickname_field")).clear();
        driver.findElement(By.cssSelector("input#nickname_field")).sendKeys("automationfc");
        driver.findElement(By.xpath("//button[@title='Submit Review']")).click();

        Thread.sleep(30000);

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement insecureFormBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button#proceed-button")));
            insecureFormBtn.click();
        } catch (TimeoutException ignored) {
        }

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"Your review has been accepted for moderation.");

        // Logout
        driver.findElement(By.cssSelector("div.account-cart-wrapper>a")).click();
        driver.findElement(By.xpath("//a[@title='Log Out']")).click();

        Thread.sleep(6000);

        Assert.assertEquals(driver.getCurrentUrl(), "https://live.techpanda.org/index.php/");
    }

    @Test
    public void TC_02_OrangeHrm(){

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(userNameLogIn);

        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(passwordLogIn);

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        driver.findElement(By.xpath("//span[text()='PIM']")).click();

        driver.findElement(By.xpath("//a[text()='Add Employee']")).click();

        driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(firstNameEmployee);

        driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys(lastNameEmployee);

        // Lấy giá trị từ textbox Employee Id
        employeeId = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"))
                .getAttribute("value");

        driver.findElement(By.xpath("//p[text()='Create Login Details']/following-sibling::div")).click();

        driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys("afc" + employeeId);

        driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys(passwordEmployee);

        driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys(passwordEmployee);

        driver.findElement(By.xpath("//button[text()=' Save ']")).click();

        String actualFirstName = driver.findElement(By.xpath("//input[@name='firstName']")).getAttribute("value");
        String actualLastName = driver.findElement(By.xpath("//input[@name='lastName']")).getAttribute("value");
        String actualEmployeeId = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div//input"))
                .getAttribute("value");

        Assert.assertEquals(actualFirstName, firstNameEmployee);
        Assert.assertEquals(actualLastName, lastNameEmployee);
        Assert.assertEquals(actualEmployeeId, employeeId);

        driver.findElement(By.xpath("//a[text()='Immigration']")).click();

        driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button")).click();

        driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys("40517-402-96-7202");

        driver.findElement(By.xpath("//textarea[@placeholder='Type Comments here']")).sendKeys("This is generated data \n" +
                "of real people");

        driver.findElement(By.xpath("//button[@type='submit']")).click();






    }



    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}