package testNG;

import org.testng.annotations.Test;

public class Topic_12_DependOnTest {

    @Test(groups = "create")
    public void TC_01_Create_New_Product(){
        System.out.println("TC_01_Create_New_Product");

        //Assert.assertTrue(false);
    }

    @Test(dependsOnMethods = "TC_01_Create_New_Product")
    public void TC_02_View_Product(){
        System.out.println("TC_02_View_Product");
    }

    @Test(dependsOnMethods = "TC_01_Create_New_Product")
    public void TC_03_Edit_Product(){
        System.out.println("TC_03_Edit_Product");
    }

    @Test(dependsOnMethods = "TC_01_Create_New_Product")
    public void TC_04_Move_Product(){
        System.out.println("TC_04_Move_Product");
    }

    @Test(dependsOnMethods = "TC_01_Create_New_Product")
    public void TC_05_Delete_Product(){
        System.out.println("TC_05_Delete_Product");
    }
}
