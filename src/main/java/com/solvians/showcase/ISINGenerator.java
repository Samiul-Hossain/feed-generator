package com.solvians.showcase;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class ISINGenerator {
    private static final Map<Character, Integer> CHAR_MAP = new HashMap<>();

    static {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < chars.length(); i++) {
            CHAR_MAP.put(chars.charAt(i), 10 + i);
        }
    }

    public static String generateISIN() {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        //(random number from 1-25 + ASCII value of A) converted to character
        String prefix = "" + (char) ('A' + rand.nextInt(26)) + (char) ('A' + rand.nextInt(26));
        //random number 0 to 999,999,999 then added zero padding to ensure 9 digits
        String body = String.format("%09d", rand.nextLong(1_000_000_000L));
        String partial = prefix + body;
        int checkDigit = calculateCheckDigit(partial);
        //11 character from partial + 1 character from checkDigit = 12 character long unique ISIN
        return partial + checkDigit;
    }

    public static int calculateCheckDigit(String partialISIN) {
        StringBuilder stringBuilder = new StringBuilder();
        //loop through and convert letters to their respective number in CHAR_MAP
        for (char c : partialISIN.toCharArray()) {
            if (Character.isLetter(c)) {
                stringBuilder.append(CHAR_MAP.get(c));
            } else {
                stringBuilder.append(c);
            }
        }

        String numStr = stringBuilder.toString();
        int sum = 0;
        boolean isDouble = true;
        //iterate from right, double every other value by switching the flag from true -> false -> true.
        for (int i = numStr.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(numStr.charAt(i));
            if (isDouble) digit *= 2;
            //splitting double digits like 13 is 1 + 3
            sum += digit / 10 + digit % 10;
            isDouble = !isDouble;
        }

        int checkDigit = (10 - (sum%10)) % 10;

        return checkDigit;
    }
}