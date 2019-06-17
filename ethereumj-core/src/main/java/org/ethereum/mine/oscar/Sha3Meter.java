package org.ethereum.mine.oscar;

import org.ethereum.crypto.HashUtil;

public class Sha3Meter {
    public static void main(String[] args) {
        byte[] value = {1, 2, 3};
        value  = HashUtil.sha3(value);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            value  = HashUtil.sha512(value);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Sha3 took " + (endTime - startTime) + " millis.");

    }
}

