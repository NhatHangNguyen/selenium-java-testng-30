package javaSDET;

import org.testng.annotations.Test;

import java.util.Random;

public class Topic_05_Random {

    // Global
    String prefixEmail = "joebiden";

    String postfixEmail = "@gmail.com"; // Web Mail Serve 

    String fullEmail = prefixEmail + postfixEmail;

    @Test
    public void testEmail() {
        Random rand = new Random();

        // Local
        //String fullEmail = prefixEmail + rand.nextInt() postfixEmail;

    }
}
