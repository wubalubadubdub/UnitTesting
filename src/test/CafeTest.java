package test;

import main.Cafe;
import main.Coffee;
import main.CoffeeType;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static main.CoffeeType.*;
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
        // composition of matchers to assert that coffee has a property beans
        // that is equal to the number of beans required for espresso
        assertBeansEqual(coffee, Espresso);
        assertMilkEqual(coffee, Espresso);

        assertEquals("Wrong coffee type", Espresso, coffee.getType());

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
        Coffee coffee = cafe.brew(Latte);

        // then
        assertEquals("Wrong coffee type", Latte, coffee.getType());

        assertBeansEqual(coffee, Latte);
        assertMilkEqual(coffee, Latte);
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
        cafe.brew(Latte);

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

    private void assertBeansEqual(Coffee coffee, CoffeeType coffeeType) {

        assertThat(coffee, Matchers.hasProperty("beans",
                Matchers.equalTo(coffeeType.getRequiredBeans())));
    }

    private void assertMilkEqual(Coffee coffee, CoffeeType coffeeType) {

        assertThat(coffee, Matchers.hasProperty("milk",
                Matchers.equalTo(coffeeType.getRequiredMilk())));

    }
}
