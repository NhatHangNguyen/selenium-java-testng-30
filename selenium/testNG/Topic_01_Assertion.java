package testNG;

import org.testng.Assert;

public class Topic_01_Assertion {

    public static void main(String[] args) {
        // Cung cấp 3 hàm chính để kiểm tra tính đúng đắn của dữ liệu
        boolean gender = 3 < 5;


        // Kiểm tra dữ liệu nó phải ĐÚNG
        Assert.assertTrue(gender);

        Assert.assertTrue(true);

        // Kiểm tra dữ liệu nó phải SAI
        Assert.assertFalse(3 > 5);

        Assert.assertFalse(false);


        // Kiểm tra dữ liệu nó bằng vs mong đợi (ACTUAL - EXPECTED)
        // Kiểu dữ liệu giống nhau
        // Giá trị của dữ liệu giống nhau
        Assert.assertEquals(5, 6);
        Assert.assertEquals("Name", "NAME");
        Assert.assertEquals(5, "NAME");
    }

}
