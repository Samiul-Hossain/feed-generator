package com.solvians.showcase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Stream;

public class CertificateUpdateGenerator {
    private final int threads;
    private final int quotes;

    public CertificateUpdateGenerator(int threads, int quotes) {
        this.threads = threads;
        this.quotes = quotes;
    }

    public Stream<CertificateUpdate> generateQuotes() {
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        List<Future<List<CertificateUpdate>>> futures = new ArrayList<>();

        int baseQuotesPerThread = quotes / threads;
        int remainder = quotes % threads;

        for (int i = 0; i < threads; i++) {
            // Distribute the remainder across the first 'remainder' threads
            int countForThread = baseQuotesPerThread + (i < remainder ? 1 : 0);
            futures.add(executor.submit(new CertificateUpdateTask(countForThread)));
        }

        // Collect results
        List<CertificateUpdate> allUpdates = new ArrayList<>();
        for (Future<List<CertificateUpdate>> future : futures) {
            try {
                allUpdates.addAll(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
        return allUpdates.stream();
    }
}
