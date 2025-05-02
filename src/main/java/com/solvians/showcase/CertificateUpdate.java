package com.solvians.showcase;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;
public class CertificateUpdate {

    private final long timestamp;
    private final String isin;
    private final double bidPrice;
    private final int bidSize;
    private final double askPrice;
    private final int askSize;
    private final LocalDate matureDate;

    public CertificateUpdate() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        this.timestamp = System.currentTimeMillis();
        this.isin = ISINGenerator.generateISIN();
        this.bidPrice = round(random.nextDouble(100.00, 200.01));
        this.bidSize = random.nextInt(1000, 5001);
        this.askPrice = round(random.nextDouble(100.00, 200.01));
        this.askSize = random.nextInt(1000, 10001);
        this.matureDate = LocalDate.now().plusDays(random.nextInt(1, 731));
    }

    private static double round(double value) {
        return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP).doubleValue(); //BigDecimal used for greater precision
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getIsin() {
        return isin;
    }

    public double getBidPrice() {
        return bidPrice;
    }

    public int getBidSize() {
        return bidSize;
    }

    public double getAskPrice() {
        return askPrice;
    }

    public int getAskSize() {
        return askSize;
    }

    public LocalDate getMaturityDate() {
        return matureDate;
    }

    public String toString() {
        return String.format("%d,%s,%.2f,%d,%.2f,%d,%s",
                timestamp, isin, bidPrice, bidSize, askPrice, askSize, matureDate);
    }
}