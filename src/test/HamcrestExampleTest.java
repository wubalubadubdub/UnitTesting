package test;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bearg on 5/12/2016.
 */
public class HamcrestExampleTest {

    @Test
    public void mapShouldContainValue() {

        Map<String, Integer> values = getValues();
        // Assert.assertThat(values, Matchers.hasKey("b"));
    }

    private Map<String, Integer> getValues() {

        final HashMap<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        return map;
    }
}
