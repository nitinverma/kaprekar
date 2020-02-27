package edu.nitin.kaprekar.kapreconst;

import java.util.*;

public class DigitizedNumber implements Cloneable, Comparable<DigitizedNumber> {
    private static final NumberDigitizer digitizer = new ConcreteNumberDigitizer();
    private Long[] digits;
    private long radix;
    private boolean negative;

    public static List<DigitizedNumber> list(final int maxDigit, final long radix) {
        final List<DigitizedNumber> list = new ArrayList<>();
        long maxNumber = (long) (Math.pow(radix, maxDigit) - 1);
        for (long i = -maxNumber; i <= maxNumber; i++) {
            list.add(digitizer.digitize(i, radix));
        }
        return list;
    }

    public DigitizedNumber(final Long[] digits, final boolean negative, final long radix) {
        this.radix = radix;
        this.negative = negative;
        int index = 0;
        this.digits = new Long[digits.length];
        for (Long digit : digits) {
            if (digit == null) {
                digits[index] = 0l;
                digit = 0l;
            }
            if (digit >= radix) {
                throw new RuntimeException("Digit (" + digit + ") >= Radix (" + radix + ")");
            } else if (digit < 0) {
                throw new RuntimeException("Digit (" + digit + ") < Zero");
            }
            this.digits[index++] = digit;
        }
    }

    public long getRadix() {
        return radix;
    }

    public Long[] getDigits() {
        return digits;
    }

    public int getDigitCount() {
        int digitCount = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            long digit = digits[i];
            if (digit > 0) {
                digitCount = i + 1;
                break;
            }
        }
        return digitCount;
    }

    public long getNumber() {
        long number = 0;
        final long digitCount = getDigitCount();
        for (int i = 0; i < digitCount; i++) {
            number = number + (long) (digits[i] * Math.pow(getRadix(), i));
        }
        if (isNegative()) {
            number = number * -1;
        }
        return number;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        if (isNegative()) {
            stringBuilder.append("{-");
        } else {
            stringBuilder.append("{");
        }

        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 10) {
                stringBuilder.append(digits[i]);
            } else {
                stringBuilder.append("[").append(digits[i]).append("]");
            }
        }
        stringBuilder.append("} base ").append(getRadix());
        if (radix != 10) {
            stringBuilder.append(" [{").append(getNumber()).append("} base 10]");
        }
        return stringBuilder.toString();
    }

    public DigitizedNumber kaprekarEntry(final int maxDigitCount) throws CloneNotSupportedException {
        final Set<DigitizedNumber> digitizedNumberSet = new TreeSet<>(new Comparator<DigitizedNumber>() {
            @Override
            public int compare(DigitizedNumber o1, DigitizedNumber o2) {
                return Long.compare(o1.getNumber(), o2.getNumber());
            }
        });
        DigitizedNumber a;
        DigitizedNumber b = this;
        do {
            a = b;
            digitizedNumberSet.add(a);
            b = a.deduceAscendingDescending(maxDigitCount);
            System.out.println("---> "+ b);
        } while (!digitizedNumberSet.contains(b));
        final DigitizedNumber kaprekarEntry = b;
        final List<DigitizedNumber> kaprekarSequence = new ArrayList<>();

        do {
            a = b;
            digitizedNumberSet.add(a);
            b = a.deduceAscendingDescending(maxDigitCount);
            kaprekarSequence.add(b);
        } while (!kaprekarEntry.equals(b));

        Collections.sort(kaprekarSequence);

        System.out.println("KaprekarSequence for " + this + "---> [" + kaprekarSequence.size()+ "] \u0001" + kaprekarSequence);
        return kaprekarEntry;
    }

    public DigitizedNumber deduceAscendingDescending(final int maxDigitCount) throws CloneNotSupportedException {
        final DigitizedNumber ascending = padClone(maxDigitCount);
        ascending.ascendingSort();
        System.out.println("ascendingSort " + ascending);
        final DigitizedNumber descending = padClone(maxDigitCount);
        descending.descendingSort();
        System.out.println("descendingSort " + descending);
        if (ascending.getNumber() > descending.getNumber()) {
            return digitizer.digitize(ascending.getNumber() - descending.getNumber(), radix);
        } else {
            return digitizer.digitize(descending.getNumber() - ascending.getNumber(), radix);
        }
    }

    public boolean isNegative() {
        return negative;
    }

    public void ascendingSort() {
        sort();
    }

    public void descendingSort() {
        sort(new Comparator<Long>() {
            @Override
            public int compare(final Long o1, final Long o2) {
                if (o1.equals(o2)) {
                    return 0;
                } else if (o1 > o2) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
    }

    public void sort() {
        sort(null);
    }

    public void sort(final Comparator<Long> longComparator) {
        if (longComparator == null) {
            Arrays.sort(digits);
        } else {
            Arrays.sort(digits, longComparator);
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return cloneMe();
    }

    public DigitizedNumber cloneMe() {
        final Long[] destination = new Long[digits.length];
        System.arraycopy(this.digits, 0, destination, 0, this.digits.length);
        return new DigitizedNumber(destination, this.negative, this.radix);
    }

    public DigitizedNumber padClone(final int maxDigitCount) throws CloneNotSupportedException {
        if (getDigitCount() > maxDigitCount) {
            throw new CloneNotSupportedException("DigitCount > maxDigitCount");
        }
        final Long[] destination = new Long[maxDigitCount];
        System.arraycopy(this.digits, 0, destination, 0, getDigitCount());
        return new DigitizedNumber(destination, this.negative, this.radix);
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof DigitizedNumber) {
            return this.equals((DigitizedNumber)obj);
        } else {
            return false;
        }
    }

    public boolean equals(final DigitizedNumber digitizedNumber) {
        return getNumber() == digitizedNumber.getNumber();
    }

    @Override
    public int compareTo(DigitizedNumber o) {
        final Long myn = getNumber();
        final Long on = o.getNumber();
        return myn.compareTo(on);
    }
}
