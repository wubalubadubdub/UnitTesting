package test;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bearg on 5/12/2016.
 */
public class HamcrestExampleTest {

    @Test
    public void mapShouldContainValue() {

        Map<String, Integer> values = getValues();
        Assert.assertThat(values, Matchers.hasKey("B"));
    }

    @Test
    public void listOrderingIsIrrelevant() {

        List<Integer> numbers = getNumbers();

        // can check whether ordered collection has a value anywhere in it
        Assert.assertThat(numbers, Matchers.hasItem(5));

    }

    private List<Integer> getNumbers() {

        return Arrays.asList(1, 2, 3, 5, 4);
    }

    private Map<String, Integer> getValues() {

        final HashMap<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        return map;
    }
}
