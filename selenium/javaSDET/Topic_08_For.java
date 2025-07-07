package javaSDET;

import java.util.ArrayList;
import java.util.List;

public class Topic_08_For {

    public static void main(String[] args) {
        int number = 100;

        // Biểu thức vòng lặp (loop)
        // for (class - iterator)
        for(int i = 0; i < number; i++){
            if (i == 5){
                System.out.println(i);
                break; // thoát khỏi vòng lặp
            }
        }

        // Collection: List/ Set/ Queue/ Map
        List<String> name = new ArrayList<String>();
        name.add("Manual Testing");
        name.add("Automation Testing");
        name.add("Regression Testing");
        name.add("UI Testing");
        name.add("API Testing");
        name.add("Mobile Testing");

        // for-each
        for(String a: name){
            System.out.println(a);
            if (a.equals("Automation Testing")){
                System.out.println("Interview");
            }
        }

        int i = 100;
        // while
        while (i < number){
            System.out.println(i);
            i++;
        }

        // do-while (ít nhất chạy 1 lần)
        do { // Action trc
            System.out.println(i);
        } while (i < number); // Kiểm tra điều kiện sau
    }
}
