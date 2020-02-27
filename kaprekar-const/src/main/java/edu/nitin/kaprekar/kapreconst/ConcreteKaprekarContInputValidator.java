package edu.nitin.kaprekar.kapreconst;

public class ConcreteKaprekarContInputValidator implements KaprekarContInputValidator {
    private NumberDigitizer numberDigitizer = new ConcreteNumberDigitizer();
    @Override
    public boolean validate(final KaprekarContInput input) {
        final DigitizedNumber digitizedNumber = numberDigitizer.digitize(input.getNumber(), input.getRadix());
        return digitizedNumber.getDigitCount() <= input.getMaxDigitCount();
    }
}
