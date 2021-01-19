package com.studentapp.utils;

import java.util.Random;

public class TestUtils {
    public static String getRandomVuale(){
        Random random= new Random();
        int randomInt = random.nextInt(100000);
        return String.valueOf(randomInt);
    }
}
