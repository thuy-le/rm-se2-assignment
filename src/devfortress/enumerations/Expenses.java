package devfortress.enumerations;

/**
 *
 * @author btqua_000
 */
public enum Expenses {

    PC("PC", 2000),
    BEAR("Bear", 5),
    COFFEE("Coffee", 5),
    REDBULL("Red Bull", 5),
    PIZZA("Pizza", 20);
    private String name;
    private int cost;

    private Expenses(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public int getCost() {
        return this.cost;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
