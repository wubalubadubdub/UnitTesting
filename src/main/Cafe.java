package main;

/**
 * Created by bearg on 5/12/2016.
 */
public final class Cafe {

    private int beansInStock = 0;
    private int milkInStock = 0;

    public Coffee brew(CoffeeType coffeeType) {

        return brew(coffeeType, 1);
    }

    /**
     *
     * @param coffeeType one of the coffee types defined in enum main.CoffeeType.
     *                   determines the number of beans and milks to use.
     * @param quantity the number of coffees to brew
     * @return a coffee instance with the specified type and in the specified qty.
     * One main.Coffee instance represents quantity number of coffees.
     */
    public Coffee brew(CoffeeType coffeeType, int quantity) {

        requirePositive(quantity);

        int requiredBeans = coffeeType.getRequiredBeans() * quantity;
        int requiredMilk = coffeeType.getRequiredMilk() * quantity;

        if (requiredBeans > beansInStock || requiredMilk > milkInStock) {

            throw new IllegalStateException("Insufficient beans or milk");
        }

        beansInStock -= requiredBeans;
        milkInStock -= requiredMilk;

        // return new Coffee(coffeeType,requiredBeans, requiredMilk);
        // used to break a couple tests
        return new Coffee(coffeeType, requiredBeans, requiredMilk);
    }

    private void requirePositive(int value) {

        if (value < 1) {

            throw new IllegalArgumentException("Value must be greater than 0");
        }
    }

    public void restockBeans(int weightInGrams) {

        requirePositive(weightInGrams);
        beansInStock += weightInGrams;
    }



    public void restockMilk(int weightInGrams) {

        requirePositive(weightInGrams);
        milkInStock += weightInGrams;

    }

    public int getBeansInStock() {
        return beansInStock;
    }

    public int getMilkInStock() {
        return milkInStock;
    }
}
