package javaSDET;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.*;

public class Topic_01_DataType {
    //  2 nhóm kiểu dữ liệu

    // Cách khai báo:
    // Access Modifier: phạm vi truy cập (private/ public/ protected/ default)
    // 1 - Access Modifier - Kiểu dữ liệu - Tên biến - Giá trị của biến (Ngoài hàm/ Trong hàm đều được)
    public char cName = 'b';

    // 2.1 - Access Modifier - Tên biến
    public char cAddress;

    // 2.2 - Tên biến - Giá trị gán sau (Hàm)
    public void clickToElement() {
        cAddress = 'c';
    }


    // Nhóm 1 - Kiểu dữ liệu nguyên thuỷ
    // char: Kiểu kí tự (character)
    // Khi gán giá trị (khởi tạo) thì nằm trong dấu nháy đơn (')
    char cZip = 'b';

    // byte/ short/ int/ long: số nguyên
    // Khi gán giá trị (khởi tạo) thì không nằm trong dấu gì
    byte bNumber = -120;

    short sNumber = 1200;

    int iNumber = 350000;

    long lNumber = 234240234;

    // float/ double: số thực
    // Khi gán giá trị (khởi tạo) thì không nằm trong dấu gì\
    float fNumber = 15.7F;
    double dNumber = 15.7;

    // boolean: logic
    // Khi gán giá trị (khởi tạo) thì không nằm trong dấu gì
    boolean gender = true;

    // Nhóm 2 - Kiểu dữ liệu tham chiếu
    // String: chuỗi
    // Khi gán giá trị (khởi tạo) thì nằm trong dấu nháy đôi (")
    String fullName = "Automation FC";

    // Class
    FirefoxDriver fDriver = new FirefoxDriver();

    Actions actions = new Actions(fDriver);

    Topic_01_DataType topic01 = new Topic_01_DataType();

    // Interface
    WebDriver driver;

    JavascriptExecutor jsExecutor;

    // Array
    String[] studentName = {"Hien", "Nam", "An"};

    Integer[] studentPhone = {9876352, 987453856, 945763276};

    // List/ Set/ Queue
    List<String> studentAddress = new ArrayList<String>();

    List<String> studentCity = new LinkedList<String>();

    // Map
    Map<String, Integer> zip = new HashMap<String, Integer>();

    // Object
    Object name = "Nam";

    Object phone = "15668";

    Object isDisplayed = true;

    // Convention: Quy ước khi lập trình
    // Tên biến/ tên hàm: viết dưới dạng camel case
    // Chữ cáu đầu tiên luôn viết thường
    // name/ address/ city/ phone/ zipCode
    // clickToElement/ getUserName/ getPhoneNumber/ selectItemInDropdown
}
