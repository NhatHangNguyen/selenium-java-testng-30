package javaSDET;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_07_If_Else {

    public static void main(String[] args) {
        WebDriver driver;
        String osName = System.getProperty("os.name");
        String browserName = "IE";
        // Biểu thức điều kiện
        // if (đúng 1 điều kiện)
        if (browserName.equals("IE")){
            System.out.print("Click to SUBMIT button");
        }

        // if-else (có 2 điều kiện)
        if (osName.contains("Windows")){
            System.out.print("Windows OS");
        } else {
            System.out.print("MAC or Linux OS");
        }

        System.out.println(osName);

        // if-else-if-else (nhiều hơn 2 điều kiện)
        if (browserName.equals("Chrome")){
            driver = new ChromeDriver();
        } else if (browserName.equals("Firefox")) {
            driver = new FirefoxDriver();
        } else {
            driver = new EdgeDriver();
        }

        // switch-case
        switch (browserName){
            case "Firefox":
                driver = new FirefoxDriver();
                break;
            case "Chrome":
                driver = new ChromeDriver();
                break;
            default:
                driver = new EdgeDriver();
                break;
        }
    }
}
