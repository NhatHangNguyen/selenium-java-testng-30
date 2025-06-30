package javaSDET;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_04_And_Or {

    @Test
    public void verifyAnd() {

        String contactInformation = "test1 automation\n" +
                "test1@gmail.com\n" +
                "Change Password";

        Assert.assertTrue(contactInformation.contains("test1 automation") && contactInformation.contains("test1@gmail.com"));

    }
}
