package edu.nitin.kaprekar.kapreconst;


import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TestDigitizedNumber {
    final NumberDigitizer numberDigitizer = new ConcreteNumberDigitizer();
    //@Test
    public void testGetDigitCount() throws CloneNotSupportedException {

        {
            final DigitizedNumber digitizedNumber = numberDigitizer.digitize(898181891818l,1001);
            Assert.assertEquals(digitizedNumber.getDigitCount(), 4);
            Assert.assertEquals(digitizedNumber.getNumber(), 898181891818l);
            System.out.println(digitizedNumber);
            System.out.println("kaprekarEntry " + digitizedNumber.kaprekarEntry(4));
        }
        {
            final DigitizedNumber digitizedNumber = numberDigitizer.digitize(0xFFFFE,0x10);
            Assert.assertEquals(digitizedNumber.getDigitCount(), 5);
            Assert.assertEquals(digitizedNumber.getNumber(), 0xFFFFE);
            System.out.println(digitizedNumber);
            System.out.println("kaprekarEntry " + digitizedNumber.kaprekarEntry(5));
        }

        {
            final DigitizedNumber digitizedNumber = numberDigitizer.digitize(0xFF001,0x10);
            Assert.assertEquals(digitizedNumber.getDigitCount(), 5);
            Assert.assertEquals(digitizedNumber.getNumber(), 0xFF001);
            System.out.println(digitizedNumber);
            System.out.println("kaprekarEntry " + digitizedNumber.kaprekarEntry(5));
        }

        {
            final DigitizedNumber digitizedNumber = numberDigitizer.digitize(0xFFFFF,0x10);
            Assert.assertEquals(digitizedNumber.getDigitCount(), 5);
            Assert.assertEquals(digitizedNumber.getNumber(), 0xFFFFF);
            System.out.println(digitizedNumber);
            System.out.println("kaprekarEntry " + digitizedNumber.kaprekarEntry(5));
        }

        {
            final DigitizedNumber digitizedNumber = numberDigitizer.digitize(-125,10);
            Assert.assertEquals(digitizedNumber.getDigitCount(), 3);
            Assert.assertEquals(digitizedNumber.getNumber(), -125);
            System.out.println(digitizedNumber);
            System.out.println(digitizedNumber.kaprekarEntry(4));
        }


        {
            final DigitizedNumber digitizedNumber = numberDigitizer.digitize(998,10);
            Assert.assertEquals(digitizedNumber.getDigitCount(), 3);
            Assert.assertEquals(digitizedNumber.getNumber(), 998);
            System.out.println(digitizedNumber);
            System.out.println(digitizedNumber.kaprekarEntry(4));
        }

        {
            final DigitizedNumber digitizedNumber = numberDigitizer.digitize(181891818,21);
            Assert.assertEquals(digitizedNumber.getDigitCount(), 7);
            Assert.assertEquals(digitizedNumber.getNumber(), 181891818);
            System.out.println(digitizedNumber);
        }
        {
            final KaprekarContInput input = new KaprekarContInput(0x1D216C, 16, 7);
            final DigitizedNumber digitizedNumber = numberDigitizer.digitize(input.getNumber(), input.getRadix()).padClone(input.getMaxDigitCount());
            digitizedNumber.ascendingSort();
            Assert.assertEquals(digitizedNumber.getDigitCount(), 7);
            Assert.assertEquals(digitizedNumber.getNumber(), 0xDC62110);
            System.out.println(digitizedNumber);
        }
        {
            final DigitizedNumber digitizedNumber = numberDigitizer.digitize(1002100,10);
            Assert.assertEquals(digitizedNumber.getDigitCount(), 7);
            Assert.assertEquals(digitizedNumber.getNumber(), 1002100);
            System.out.println(digitizedNumber);
        }
        {
            final DigitizedNumber digitizedNumber = new DigitizedNumber(
                    new Long[]{1l, 0l, 0l, 0l, 0l, 0l, 0l}, false, 10356
            );
            Assert.assertEquals(digitizedNumber.getDigitCount(), 1);
            Assert.assertEquals(digitizedNumber.getNumber(), 1);
            System.out.println(digitizedNumber);
            System.out.println("kaprekarNumber " + digitizedNumber.kaprekarEntry(4));
        }
        {
            final DigitizedNumber digitizedNumber = new DigitizedNumber(
                    new Long[]{0l, 0l, 0l, 1l, 0l, 0l, 0l}, false, 10
            );
            Assert.assertEquals(digitizedNumber.getDigitCount(), 4);
            Assert.assertEquals(digitizedNumber.getNumber(), 1000);
            System.out.println(digitizedNumber);
            System.out.println("kaprekarEntry " + digitizedNumber.kaprekarEntry(4));
        }
        {
            final DigitizedNumber digitizedNumber = new DigitizedNumber(
                    new Long[]{0l, 0l, 0l, 0l, 0l, 0l, 0l}, false, 10
            );
            Assert.assertEquals(digitizedNumber.getDigitCount(), 0);
            Assert.assertEquals(digitizedNumber.getNumber(), 0);
            System.out.println(digitizedNumber);
        }
        {
            final DigitizedNumber digitizedNumber = numberDigitizer.digitize(8,2);
            Assert.assertEquals(digitizedNumber.getDigitCount(), 4);
            Assert.assertEquals(digitizedNumber.getNumber(), 8);
            System.out.println(digitizedNumber);
            System.out.println("kaprekarEntry " + digitizedNumber.kaprekarEntry(4));
        }

        {
            final DigitizedNumber digitizedNumber = numberDigitizer.digitize(6,2);
            Assert.assertEquals(digitizedNumber.getDigitCount(), 3);
            Assert.assertEquals(digitizedNumber.getNumber(), 6);
            System.out.println(digitizedNumber);
            System.out.println("kaprekarEntry " + digitizedNumber.kaprekarEntry(4));
        }


        {
            final DigitizedNumber digitizedNumber = numberDigitizer.digitize(5,2);
            Assert.assertEquals(digitizedNumber.getDigitCount(), 3);
            Assert.assertEquals(digitizedNumber.getNumber(), 5);
            System.out.println(digitizedNumber);
            System.out.println("kaprekarEntry " + digitizedNumber.kaprekarEntry(4));
        }

        {
            final DigitizedNumber digitizedNumber = numberDigitizer.digitize(-7,2);
            Assert.assertEquals(digitizedNumber.getDigitCount(), 3);
            Assert.assertEquals(digitizedNumber.getNumber(), -7);
            System.out.println(digitizedNumber);
        }

        {
            final DigitizedNumber digitizedNumber = numberDigitizer.digitize(2211,10);
            Assert.assertEquals(digitizedNumber.getDigitCount(), 4);
            Assert.assertEquals(digitizedNumber.getNumber(), 2211);
            System.out.println(digitizedNumber);
            System.out.println("kaprekarEntry " + digitizedNumber.kaprekarEntry(4));
        }
    }

    @Test
    public void test() throws CloneNotSupportedException {
        final int maxDigit = 4;
        final long radix = 10;
        final List<DigitizedNumber> list = DigitizedNumber.list(maxDigit, radix);
        for (final DigitizedNumber digitizedNumber: list) {
            digitizedNumber.kaprekarEntry(maxDigit);
        }
    }
}
