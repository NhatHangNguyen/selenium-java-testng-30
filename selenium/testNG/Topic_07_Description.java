package testNG;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Topic_07_Description {
    // Tên testcase = hàm/ fuction/ method của Java
    // Theo convention của từng ngôn ngữ

    // Chú thích/ diễn giả/ note

    @Test(description = "Jira#1368 - User can create new product and verify created success")
    public void Product_01_CreateNewProduct(){
        System.out.println("shouldBeCreateNewProduct");
    }

    @Test
    public void Product_02_BeViewProduct(){
        System.out.println("shouldBeViewProduct");
    }

    @Test(enabled = false)
    public void Product_03_EditProduct(){
        System.out.println("shouldBeEditProduct");
    }

    @Test
    public void Product_04_DeleteProduct(){
        System.out.println("shouldBeDeleteProduct");
    }
}
