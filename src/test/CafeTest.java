package test;

import main.Cafe;
import main.Coffee;
import main.CoffeeType;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by bearg on 5/12/2016.
 */
public class CafeTest {

    // tests pass by default
    @Test
    public void canBrewEspresso() {

        // given -- set up preconditions for test
        Cafe cafe = new Cafe();
        cafe.restockBeans(7);

        // when -- the behavior is executed
        Coffee coffee = cafe.brew(CoffeeType.Espresso);

        // then -- check the outcome
        // check that coffee is of correct type
        Assert.assertEquals(CoffeeType.Espresso, coffee.getType());
        Assert.assertEquals(0, coffee.getMilk());
        Assert.assertEquals(7, coffee.getBeans());

        // bad! if bean restocking code breaks, you don't want test relating
        // to brewing espresso fail. wouldn't know whether test failed because
        // bean stock didn't update correctly or coffee wasn't brewed correctly
        // Assert.assertEquals(0, cafe.getBeansInStock());

    }

    @Test
    public void brewingEspressoConsumesBeans() {

        // given -- set up preconditions for test
        Cafe cafe = new Cafe();
        cafe.restockBeans(7);

        // when -- the behavior is executed
        Coffee coffee = cafe.brew(CoffeeType.Espresso);

        // then -- make sure bean count has been reduced by 7 down to 0
        Assert.assertEquals(0, cafe.getBeansInStock());
    }

    // then
    @Test(expected = IllegalStateException.class)
    public void lattesRequireMilk() {

        // given
        Cafe cafe = new Cafe();
        cafe.restockBeans(7); // latte requires same # of beans as espresso

        // when
        cafe.brew(CoffeeType.Latte);

        // the above makes the test fail, but we actually want the test
        // to fail if we try and brew a Latte with not enough milk in stock.
        // to accomplish this, we put the then clause with the Test annotation,
        // saying that this exception should be thrown
    }
}
