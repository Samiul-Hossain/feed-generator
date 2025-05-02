package com.solvians.showcase;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class CertificateUpdateTest {
    @Test
    void testIsinFormat() {
        CertificateUpdate cu = new CertificateUpdate();
        String isin = cu.getIsin();
        assertEquals(12, isin.length(), "ISIN should be 12 characters long");
        assertTrue(isin.matches("^[A-Z]{2}[0-9]{9}[0-9]$"), "ISIN should match expected pattern");
    }

    @Test
    void testPriceRanges() {
        CertificateUpdate cu = new CertificateUpdate();
        assertTrue(cu.getBidPrice() >= 100.00 && cu.getBidPrice() <= 200.00, "Bid price out of range");
        assertTrue(cu.getAskPrice() >= 100.00 && cu.getAskPrice() <= 200.00, "Ask price out of range");
    }

    @Test
    void testSizeRanges() {
        CertificateUpdate cu = new CertificateUpdate();
        assertTrue(cu.getBidSize() >= 1000 && cu.getBidSize() <= 5000, "Bid size out of range");
        assertTrue(cu.getAskSize() >= 1000 && cu.getAskSize() <= 10000, "Ask size out of range");
    }

    @Test
    void testMaturityDateRange() {
        CertificateUpdate cu = new CertificateUpdate();
        LocalDate now = LocalDate.now();
        LocalDate maturity = cu.getMaturityDate();
        assertFalse(maturity.isBefore(now.plusDays(1)), "Maturity date is too soon");
        assertFalse(maturity.isAfter(now.plusDays(730)), "Maturity date is too far in the future");
    }
}
