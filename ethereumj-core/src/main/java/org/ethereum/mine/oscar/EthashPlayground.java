package org.ethereum.mine.oscar;

import org.ethereum.mine.EthashAlgo;
import org.ethereum.mine.EthashParams;


public class EthashPlayground {
    public static void main(String[] args) {
        EthashParams ethashParams = new EthashParams();
        EthashAlgo ethashAlgo = new EthashAlgo(ethashParams);

        long blockNumber = 7816637;

        System.out.println("dataset " + ethashParams.getFullSize(blockNumber));
        long cacheSize = ethashParams.getCacheSize(blockNumber);
        System.out.println("cache " + cacheSize);

        byte[] seed = ethashAlgo.getSeedHash(blockNumber);
        long startTime = System.currentTimeMillis();
        int[] cache = ethashAlgo.makeCache(cacheSize, seed);
        long endTime = System.currentTimeMillis();
        System.out.println("makeCache took " + (endTime - startTime) + " millis.");
        System.out.println("makeCache output size " + cache.length);

    }



}

