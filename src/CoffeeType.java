/**
 * Created by bearg on 5/12/2016.
 * Describes type of coffee we're going to serve.
 * requiredBeans is the actual number of beans to use.
 * requiredMilk is the mass in g of milk to use.
 */
public enum CoffeeType {

    Espresso(7, 0),
    Latte(7, 227),
    FilterCoffee(10, 0);

    private final int requiredBeans;
    private final int requiredMilk;

    CoffeeType(int requiredBeans, int requiredMilk) {

        this.requiredBeans = requiredBeans;
        this.requiredMilk = requiredMilk;
    }

    public int getRequiredBeans() {

        return requiredBeans;
    }

    public int getRequiredMilk() {

        return requiredMilk;
    }
}
