package org.ethereum.mine.oscar;

import org.ethereum.crypto.HashUtil;
import org.ethereum.mine.EthashAlgo;
import org.ethereum.mine.EthashParams;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static java.lang.System.arraycopy;
import static org.ethereum.util.ByteUtil.bytesToInts;
import static org.ethereum.util.ByteUtil.xor;

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

//    public static void main(String[] args) {
//        System.out.println("dataset " + getDataSetSize());
//        System.out.println("cache " + getCacheSize());
//    }
//
//    private static long getDataSetSize() {
//        long sz = (long) (Math.pow(2, 30) + Math.pow(2, 23) * (7816637 / 30000));
//        sz -= 128;
//        while (!isPrime(sz / 128)) {
//            sz -= 2 * 128;
//        }
//        return sz;
//    }
//
//    private static long getCacheSize() {
//        long sz = (long) (Math.pow(2, 24) + Math.pow(2, 17) * (7816637 / 30000));
//        sz -= 64;
//        while (!isPrime(sz / 64)) {
//            sz -= 2 * 64;
//        }
//        return sz;
////        sz = CACHE_BYTES_INIT + CACHE_BYTES_GROWTH * (block_number // EPOCH_LENGTH)
////        sz -= HASH_BYTES
////        while not isprime(sz / HASH_BYTES):
////        sz -= 2 * HASH_BYTES
////        return sz
//    }
//
//
//    public static boolean isPrime(long number) {
//        return number > 2
//                && LongStream.rangeClosed(2, (int) Math.sqrt(number))
//                .noneMatch(n -> (number % n == 0));
//    }
//
//
//    public static final long WORD_BYTES = 4; //                    # bytes in word
//    public static final long DATASET_BYTES_INIT = (long) Math.pow(2, 30); //        # bytes in dataset at genesis
//    public static final long DATASET_BYTES_GROWTH = (long) Math.pow(2, 23); //      # dataset growth per epoch
//    public static final long CACHE_BYTES_INIT = (long) Math.pow(2, 24); //          # bytes in cache at genesis
//    public static final long CACHE_BYTES_GROWTH = (long) Math.pow(2, 17); //        # cache growth per epoch
//    public static final long CACHE_MULTIPLIER=1024; //             # Size of the DAG relative to the cache
//    public static final long EPOCH_LENGTH = 30000; //              # blocks per epoch
//    public static final long MIX_BYTES = 128; //                   # width of mix
//    public static final long HASH_BYTES = 64; //                   # hash length in bytes
//    public static final long DATASET_PARENTS = 256; //             # number of parents of each dataset element
//    public static final long CACHE_ROUNDS = 3; //                  # number of rounds in cache production
//    public static final long ACCESSES = 64; //                     # number of accesses in hashimoto loop
//
//    public static int[] makeCache(long cacheSize, byte[] seed) {
//        byte[][] bytes = makeCacheBytes(cacheSize, seed);
//        int[] ret = new int[bytes.length * bytes[0].length / 4];
//        int[] ints = new int[bytes[0].length / 4];
//        for (int i = 0; i < bytes.length; i++) {
//            bytesToInts(bytes[i], ints, false);
//            arraycopy(ints, 0, ret, i * ints.length, ints.length);
//        }
//        return ret;
//    }
//
//    private static byte[][] makeCacheBytes(long cacheSize, byte[] seed) {
//        int n = (int) (cacheSize / HASH_BYTES);
//        byte[][] o = new byte[n][];
//        o[0] = HashUtil.sha512(seed);
//        for (int i = 1; i < n; i++) {
//            o[i] = HashUtil.sha512(o[i - 1]);
//        }
//
//        for (int cacheRound = 0; cacheRound < CACHE_ROUNDS; cacheRound++) {
//            for (int i = 0; i < n; i++) {
//                int v = remainderUnsigned(getWord(o[i], 0), n);
//                o[i] = HashUtil.sha512(xor(o[(i - 1 + n) % n], o[v]));
//            }
//        }
//        return o;
//    }
//
//    public static int remainderUnsigned(int dividend, int divisor) {
//        if (divisor >= 0) {
//            if (dividend >= 0) {
//                return dividend % divisor;
//            }
//            // The implementation is a Java port of algorithm described in the book
//            // "Hacker's Delight" (section "Unsigned short division from signed division").
//            int q = ((dividend >>> 1) / divisor) << 1;
//            dividend -= q * divisor;
//            if (dividend < 0 || dividend >= divisor) {
//                dividend -= divisor;
//            }
//            return dividend;
//        }
//        return dividend >= 0 || dividend < divisor ? dividend : dividend - divisor;
//    }
//
//
//    public static int getWord(byte[] arr, int wordOff) {
//        return ByteBuffer.wrap(arr, wordOff * 4, 4).order(ByteOrder.LITTLE_ENDIAN).getInt();
//    }


}

