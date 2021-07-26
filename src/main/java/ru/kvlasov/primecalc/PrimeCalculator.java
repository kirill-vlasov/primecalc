package ru.kvlasov.primecalc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrimeCalculator {

    public List<Long> calc(long count) {
        if (count < 2) {
            return Collections.emptyList();
        }

        List<Long> result = new ArrayList<>();

        for (long i = 2; i <= count; i++) {
            if (isPrimeNumber(i, 100)) {
                result.add(i);
            }
        }

        return result;
    }

    /**
     * Check if a number is a prime.
     *
     * @param candidate Number to be tested.
     * @param accuracy Number of tests to be performed.
     * @return true if the candidate is probably a prime, false if the candidate
     *          is certainly no prime.
     */
    private boolean isPrimeNumber(long candidate, long accuracy) {
        if (candidate < 2) {
            return false;
        }

        if (candidate == 2) {
            return true;
        }

        long d, s;

        for (d = 0, s = 1; (d & 1) == 0; s++) {
            d = (candidate - 1) / fastPow(2, s);
        }

        verification: for (long i = 0; i < accuracy; i++) {
            // random base in the range [2, n-1]
            long base = (long) ((Math.random() * (candidate - 3)) + 2);

            long x = fastPow(base, d, candidate);

            if (x == 1 || x == (candidate - 1))
                continue verification;

            for (long j = 0; j < (s - 1); j++) {
                x = fastPow(x, 2, candidate);
                if (x == 1)
                    return false;
                if (x == (candidate - 1))
                    continue verification;
            }

            return false;
        }

        return true;
    }

    /**
     * Returns the value of the first argument raised to the power of the second
     * argument.
     *
     * @param base
     * @param exponent
     * @return the value base^exponent
     */
    private static long fastPow(long base, long exponent) {
        int shift = 63; // bit position
        long result = base; // (1 * 1) * base = base

        // Skip all leading 0 bits and the most significant 1 bit.
        while (((exponent >> shift--) & 1) == 0)
            ;

        while (shift >= 0) {
            result = result * result;
            if (((exponent >> shift--) & 1) == 1)
                result = result * base;
        }

        return result;
    }

    /**
     * Returns the value of the first argument raised to the power of the second
     * argument modulo the third argument.
     *
     * @param base
     * @param exponent
     * @param modulo
     * @return the value base^exponent % modulo
     */
    private static long fastPow(long base, long exponent, long modulo) {
        int shift = 63; // bit position
        long result = base; // (1 * 1) * base = base

        // Skip all leading 0 bits and the most significant 1 bit.
        while (((exponent >> shift--) & 1) == 0)
            ;

        while (shift >= 0) {
            result = (result * result) % modulo;
            if (((exponent >> shift--) & 1) == 1)
                result = (result * base) % modulo;
        }

        return result;
    }

}
