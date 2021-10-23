package com.voleer.test.ui.core;

public class RandomDataGenerator {
    public static String randomString(int max)
    {

        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(max);

        for (int i = 0; i < max; i++) {
            int index = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();
    }
}
