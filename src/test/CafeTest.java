package test;

import main.Cafe;
import main.Coffee;
import main.CoffeeType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static main.CoffeeType.Espresso;
import static org.junit.Assert.*;

/**
 * Created by bearg on 5/12/2016.
 */
public class CafeTest {

    public static final int ESPRESSO_BEANS  = Espresso.getRequiredBeans();
    public static final int NO_MILK  = 0;
    public static final int NO_BEANS  = 0;

    private Cafe cafe;

    @Before
    public void before() {

        cafe = new Cafe();

        // not every test needs beans, so we don't call
        // provideCafeWithBeans() here
    }

    // tests pass by default
    @Test
    public void canBrewEspresso() {

        // given -- set up preconditions for test
        provideBeansToCafe();

        // when -- the behavior is executed
        Coffee coffee = cafe.brew(Espresso);

        // then -- check the outcome
        // check that coffee is of correct type
        assertEquals("Wrong coffee type", Espresso, coffee.getType());
        assertEquals("Wrong amount of milk", NO_MILK, coffee.getMilk());
        assertEquals("Wrong number of beans",  ESPRESSO_BEANS, coffee.getBeans());

        // bad! if bean restocking code breaks, you don't want test relating
        // to brewing espresso fail. wouldn't know whether test failed because
        // bean stock didn't update correctly or coffee wasn't brewed correctly
        // Assert.assertEquals(0, cafe.getBeansInStock());

    }



    @Test
    public void canBrewLatte() {

        // given
        provideBeansToCafe();
        cafe.restockMilk(227);

        // when
        Coffee coffee = cafe.brew(CoffeeType.Latte);

        // then
        assertEquals("Wrong coffee type", CoffeeType.Latte, coffee.getType());
        assertEquals("Wrong number of beans", 7, coffee.getBeans());
        assertEquals("Wrong amount of milk", 227, coffee.getMilk());
    }

    @Test
    public void brewingEspressoConsumesBeans() {

        // given -- set up preconditions for test
        provideBeansToCafe();

        // when -- the behavior is executed
        Coffee coffee = cafe.brew(Espresso);

        // then -- make sure bean count has been reduced by 7 down to 0
        assertEquals(NO_BEANS, cafe.getBeansInStock());
    }

    // then
    @Test(expected = IllegalStateException.class)
    public void lattesRequireMilk() {

        // given
        provideBeansToCafe();

        // when
        cafe.brew(CoffeeType.Latte);

        // the above makes the test fail, but we actually want the test
        // to fail if we try and brew a Latte with not enough milk in stock.
        // to accomplish this, we put the then clause with the Test annotation,
        // saying that this exception should be thrown
    }

    // make sure we can't restock with 0 milk
    @Test(expected = IllegalArgumentException.class)
    public void mustRestockMilk() {

        // when
        cafe.restockMilk(NO_MILK);
    }

    // make sure we can't restock with 0 beans
    @Test(expected = IllegalArgumentException.class)
    public void mustRestockBeans() {

        cafe.restockBeans(NO_BEANS);
    }

    // setup code for the given of our tests
    private void provideBeansToCafe() {

         cafe.restockBeans(ESPRESSO_BEANS); // works for espresso and latte, but not filter

    }
}
