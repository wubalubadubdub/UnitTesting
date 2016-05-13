package test;

/**
 * Created by bearg on 5/12/2016.
 */
public class LeapYear {


    public static boolean isLeapYear(final int year) {

        if (isDivisible(year, 4)) {

            if (isDivisible(year, 100)) {

                return isDivisible(year, 400); // if divisible by 100, only leap year
                // if also divisible by 400
            }

            return true; // divisible by 4, but not 100 is always leap year
        }

        return false; // not divisible by 4 is never leap year
    }

    private static boolean isDivisible(final int year, final int number) {
        return year % number == 0;
    }
}
