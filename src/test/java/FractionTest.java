import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class FractionTest {

    @Test
    public void fractionOutput() {
        Fraction fraction = new Fraction();
        fraction.Denominator = 9.6;
        fraction.Numerator = 3.3;
        fraction.fractionOutput();
    }

    @Test
    public void getFractionValue() {
        Fraction fraction = new Fraction();
        fraction.Denominator = 3.3;
        fraction.Numerator = 4.1;
        double expected = 3.3/4.1;
        double actual = fraction.GetFractionValue();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void readFractionFromFile() {
        Fraction fraction = new Fraction();
        fraction.readFractionFromFile();
        Fraction expected = new Fraction();
        expected.Numerator = 6;
        expected.Denominator = 11;
        Assert.assertEquals(expected, fraction);
    }
}