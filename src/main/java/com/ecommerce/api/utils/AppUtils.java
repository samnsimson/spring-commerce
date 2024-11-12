package com.ecommerce.api.utils;

import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class AppUtils {
    public static String randomId() {
        long timestamp = System.currentTimeMillis();
        int randomNum = ThreadLocalRandom.current().nextInt(1000, 9999);
        return timestamp + String.valueOf(randomNum);
    }
}
