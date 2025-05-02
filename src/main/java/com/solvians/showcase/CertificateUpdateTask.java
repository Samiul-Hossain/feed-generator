package com.solvians.showcase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class CertificateUpdateTask implements Callable<List<CertificateUpdate>> {

    private final int count;

    public CertificateUpdateTask(int count) {
        this.count = count;
    }

    @Override
    public List<CertificateUpdate> call() {
        List<CertificateUpdate> updates = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            updates.add(new CertificateUpdate());
        }
        return updates;
    }
}
