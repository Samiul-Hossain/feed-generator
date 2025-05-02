package com.solvians.showcase;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ISINGeneratorTest {

    @Test
    public void testISINLength() {
        String isin = ISINGenerator.generateISIN();
        assertEquals(12, isin.length(), "ISIN should be 12 characters long.");
    }

    @Test
    public void testISINFormat() {
        String isin = ISINGenerator.generateISIN();
        assertTrue(isin.matches("[A-Z]{2}[0-9A-Z]{9}[0-9]"), "ISIN should match expected pattern.");
    }

    @Test
    public void testISINPrefix() {
        String isin = ISINGenerator.generateISIN();
        String prefix = isin.substring(0, 2);
        assertTrue(prefix.matches("[A-Z]{2}"), "ISIN prefix should contain two uppercase letters");
    }

    @Test
    public void testISINBody() {
        String isin = ISINGenerator.generateISIN();
        String body = isin.substring(2, 11);
        assertTrue(body.matches("\\d{9}"), "ISIN body should be 9 digits");
    }

    @Test
    public void testISINCheckDigitCalculation() {
        //DE123456789 from problem statement
        int checkDigit = ISINGenerator.calculateCheckDigit("DE123456789");
        assertEquals(6, checkDigit, "'DE123456789' check digit should be 6");
    }

    @Test
    public void testMultipleGeneratedISIN() {
        Set<String> isins = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            String isin = ISINGenerator.generateISIN();
            assertTrue(isins.add(isin), "Duplicate ISIN found: " + isin);
        }
        assertEquals(100, isins.size());
    }
}
