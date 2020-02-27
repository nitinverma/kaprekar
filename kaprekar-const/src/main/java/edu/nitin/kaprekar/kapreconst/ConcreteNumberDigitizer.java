package edu.nitin.kaprekar.kapreconst;

import java.util.ArrayList;

public class ConcreteNumberDigitizer implements NumberDigitizer {
    @Override
    public DigitizedNumber digitize(final long number, final long radix) {
        final ArrayList<Long> digits = new ArrayList<>();
        boolean negative = false;
        long inputNumber = number;

        if (inputNumber < 0) {
            negative = true;
            inputNumber = -1 * inputNumber;
        }

        while (inputNumber != 0) {
            digits.add(inputNumber % radix);
            inputNumber = inputNumber / radix;
        }

        final Long [] digitsArray = new Long[digits.size()];
        return new DigitizedNumber(digits.toArray(digitsArray), negative, radix);
    }
}
