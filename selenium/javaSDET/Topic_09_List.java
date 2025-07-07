package javaSDET;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.*;

public class Topic_09_List {

    public static void main(String[] args) {
        // Java Collection

        WebDriver driver;

        driver = new FirefoxDriver();

        ArrayList<String> address = new ArrayList<>();

        Vector<Float> studentPoint = new Vector<>();

        LinkedList<Integer> studentAge = new LinkedList<>();

        Stack<Boolean> stack = new Stack<>();

        List<String> studentName = new Stack<>();
        address.add("Ho Chi Minh");
        address.add("Ha Noi");
        address.add("Da Nang");
        address.add("Hai Phong");
        address.add("Can Tho");
        address.add("Dong Nai");

        // Lấy ra 1 element cụ thể
        System.out.println(address.get(0));
        System.out.println(address.get(2));
        System.out.println(address.get(5));
        System.out.println(address.get(3));

        // Lấy ra toàn bộ
        for (int i = 0; i < address.size(); i++){
            System.out.println(address.get(i));
        }

        // Lấy ra toàn bộ
        for (String a: address){
            System.out.println(a);
        }
    }
}
