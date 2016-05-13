package test;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by bearg on 5/12/2016.
 * A year is a leap year if it's divisible by 4,
 * except for century years, unless they're also
 * divisible by 400. e.g. 2000 was a leap year but
 * 2100 won't be.
 */
public class LeapYearTest {

    @Test
    public void leapYearsAreDivisibleByFour() {

        Assert.assertTrue(LeapYear.isLeapYear(2016));
    }

    // negative test case -- should be false
    @Test
    public void normalYearsNotDivisibleByFour() {

        Assert.assertFalse(LeapYear.isLeapYear(3));
    }

    @Test
    public void leapYearsNotDivisibleBy100() {

        Assert.assertFalse(LeapYear.isLeapYear(1900));
    }

    @Test
    public void leapsYearsAreDivisibleBy400() {

        Assert.assertTrue(LeapYear.isLeapYear(2000));
    }
}
