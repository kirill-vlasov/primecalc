package ru.kvlasov.primecalc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrimeCalculator {

    public List<Integer> calc(Integer count) {
        if (count < 2) {
            return Collections.emptyList();
        }

        List<Integer> result = new ArrayList<>();
        result.add(2);

        for (int i = 3; i <= count; i++) {
            if (isPrimeNumber(i, result)) {
                result.add(i);
            }
        }

        return result;
    }

    private boolean isPrimeNumber(int n, List<Integer> primes) {
        double sqrt = Math.sqrt(n);
        for (int prime : primes) {
            if (prime > sqrt) {
                return true;
            }
            if (n % prime == 0) {
                return false;
            }
        }
        return true;
    }

}
