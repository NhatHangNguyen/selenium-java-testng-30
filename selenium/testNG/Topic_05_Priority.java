package testNG;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Topic_05_Priority {
    WebDriver driver;

    //Priority
//    @Test(priority = 0)
//    public void shouldBeCreateNewProduct(){
//        System.out.println("shouldBeCreateNewProduct");
//    }
//
//    @Test(priority = 1)
//    public void shouldBeViewProduct(){
//        System.out.println("shouldBeViewProduct");
//    }
//
//    @Test(priority = 2)
//    public void shouldBeEditProduct(){
//        System.out.println("shouldBeEditProduct");
//    }
//
//    @Test(priority = 3)
//    public void shouldBeDeleteProduct(){
//        System.out.println("shouldBeDeleteProduct");
//    }


    // Follow chữ cái
    @Test
    public void Product_01_CreateNewProduct(){
        System.out.println("shouldBeCreateNewProduct");
    }

    @Test
    public void Product_02_BeViewProduct(){
        System.out.println("shouldBeViewProduct");
    }

    @Test
    public void Product_03_EditProduct(){
        System.out.println("shouldBeEditProduct");
    }

    @Test
    public void Product_04_DeleteProduct(){
        System.out.println("shouldBeDeleteProduct");
    }
}
