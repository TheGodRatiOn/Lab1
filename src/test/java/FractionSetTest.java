import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FractionSetTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void add() {
        Fraction fraction = new Fraction();
        Fraction fraction1 = new Fraction();
        Fraction fraction2 = new Fraction();

        fraction.Numerator = 1;
        fraction.Denominator = 2;
        fraction1.Numerator = 3;
        fraction1.Denominator = 4;
        fraction2.Numerator = 5;
        fraction2.Denominator = 6;

        FractionSet fractionSet = new FractionSet();
        fractionSet.add(fraction);
        //fractionSet.add(fraction1);
        //fractionSet.add(fraction2);

        Assert.assertEquals(fractionSet, fraction1);
    }
}