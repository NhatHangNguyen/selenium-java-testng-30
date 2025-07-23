package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.*;
import java.nio.charset.Charset;
import java.time.Duration;
import org.openqa.selenium.support.Color;

public class Topic_22_Action_Part_III {

    WebDriver driver;
    Actions action;
    String osName = System.getProperty("os.name");
    Keys keys;
    String projectPath = System.getProperty("user.dir");
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void initialBrowser(){

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

        action = new Actions(driver);
        if (osName.startsWith("Windows")){
            keys = Keys.CONTROL;
        } else {
            keys = Keys.COMMAND;
        }

        // keys = osName.startsWith("Windows") ? keys.CONTROL : Keys.COMMAND; (Cách 2 toán tử ba ngôi)

        jsExecutor = (JavascriptExecutor) driver;
    }

    @Test
    public void TC_07_Click_And_Hold_Block() throws InterruptedException {
        driver.get("https://swisnl.github.io/jQuery-contextMenu/demo.html");

        // Click chuột phải vào button
        action.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
        Thread.sleep(3000);

        By quitContextBy = By.cssSelector("li.context-menu-icon-quit");

        Assert.assertTrue(driver.findElement(quitContextBy).isDisplayed());

        // Hover mouse
        action.moveToElement(driver.findElement(quitContextBy)).perform();
        Thread.sleep(3000);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());

        // Click Quit
        action.click(driver.findElement(quitContextBy)).perform();
        Thread.sleep(3000);

        driver.switchTo().alert().accept();

        Assert.assertFalse(driver.findElement(quitContextBy).isDisplayed());
    }

    @Test
    public void TC_08_Drap_Drop_HTML4() throws InterruptedException {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");

        WebElement sourceCircle = driver.findElement(By.cssSelector("div#draggable"));
        WebElement targetCircle = driver.findElement(By.cssSelector("div#droptarget"));

        action.dragAndDrop(sourceCircle, targetCircle).perform();
        Thread.sleep(3000);

        Assert.assertEquals(targetCircle.getText(),"You did great!");

        Assert.assertEquals(Color.fromString(targetCircle.getCssValue("background-color")).asHex().toUpperCase(),"#03A9F4");
    }

    @Test
    public void TC_09_Drap_Drop_HTML5_JQuery() throws InterruptedException, IOException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");

        // Site k support JQuery
        //String jqueryLibraries = getContentFile(projectPath + "/dragDrop/jQueryLid.js");
        //jsExecutor.executeScript(jqueryLibraries);

        String jqueryDragDropContent = getContentFile(projectPath + "/dragDrop/dragAndDrop.js");

        // Drag A to B
        jsExecutor.executeScript(jqueryDragDropContent);
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"B");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"A");

        // Drag B to A
        jsExecutor.executeScript(jqueryDragDropContent);
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"A");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"B");
    }

    @Test
    public void TC_10_Drap_Drop_HTML5_Java_Robot() throws InterruptedException, IOException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");

        // Site k support JQuery
        //String jqueryLibraries = getContentFile(projectPath + "/dragDrop/jQueryLid.js");
        //jsExecutor.executeScript(jqueryLibraries);

        String jqueryDragDropContent = getContentFile(projectPath + "/dragDrop/dragAndDrop.js");

        // Drag A to B
        jsExecutor.executeScript(jqueryDragDropContent);
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"B");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"A");

        // Drag B to A
        jsExecutor.executeScript(jqueryDragDropContent);
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"A");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"B");
    }

    public String getContentFile(String filePath) throws IOException {
        Charset cs = Charset.forName("UTF-8");
        FileInputStream stream = new FileInputStream(filePath);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } finally {
            stream.close();
        }
    }

    public void dragAndDropHTML5ByXpath(String sourceLocator, String targetLocator) throws AWTException {

        WebElement source = driver.findElement(By.xpath(sourceLocator));
        WebElement target = driver.findElement(By.xpath(targetLocator));

        // Setup robot
        Robot robot = new Robot();
        robot.setAutoDelay(500);

        // Get size of elements
        Dimension sourceSize = source.getSize();
        Dimension targetSize = target.getSize();

        // Get center distance
        int xCentreSource = sourceSize.width / 2;
        int yCentreSource = sourceSize.height / 2;
        int xCentreTarget = targetSize.width / 2;
        int yCentreTarget = targetSize.height / 2;

        Point sourceLocation = source.getLocation();
        Point targetLocation = target.getLocation();

        // Make Mouse coordinate center of element
        sourceLocation.x += 20 + xCentreSource;
        sourceLocation.y += 110 + yCentreSource;
        targetLocation.x += 20 + xCentreTarget;
        targetLocation.y += 110 + yCentreTarget;

        // Move mouse to drag from location
        robot.mouseMove(sourceLocation.x, sourceLocation.y);

        // Click and drag
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

        // Move to final position
        robot.mouseMove(targetLocation.x, targetLocation.y);

        // Drop
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}