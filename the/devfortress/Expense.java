/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package the.devfortress;

/**
 *
 * @author PC
 */
public enum Expense {
    HARDWARE(1),
    COFFEE(2),
    PIZZA(3),
    REDBULL(4),
    BEER(5);
    
    private int price;

    private Expense(int price) {
        this.price = price;
    }

    public int getPrice(){
        return price;
    }
}
