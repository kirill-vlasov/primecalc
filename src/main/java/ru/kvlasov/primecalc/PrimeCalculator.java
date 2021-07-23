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
            if (isPrimeNumber(i)) {
                result.add(i);
            }
        }

        return result;
    }

    private boolean isPrimeNumber(int i) {
        // проверить, делится ли число без остатка
        for (int j=2; j <= i/j; j++) {
            // если число делится без остатка, значит, оно не простое
            if ((i % j) == 0)
                return false;
        }
        return true;
    }

}
