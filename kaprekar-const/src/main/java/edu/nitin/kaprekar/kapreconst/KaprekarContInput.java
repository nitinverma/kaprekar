package edu.nitin.kaprekar.kapreconst;

public class KaprekarContInput {
    private long radix;
    private long number;
    private int maxDigitCount;

    public KaprekarContInput(long number) {
        this(number, 10, 4);
    }

    public KaprekarContInput(long number, long radix, int maxDigitCount) {
        this.number = number;
        this.radix = radix;
        this.maxDigitCount = maxDigitCount;
    }

    public long getRadix() {
        return radix;
    }

    public long getNumber() {
        return number;
    }

    public int getMaxDigitCount() {
        return maxDigitCount;
    }
}
