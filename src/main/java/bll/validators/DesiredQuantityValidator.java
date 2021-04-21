package bll.validators;
import model.Orders;

/**
 *
 * Un validator pentru limitele cantitatii dorite de client. Daca este mai mica decat 0 sau mult prea mare,
 * se arunca o exceptie, prinsa in gui;
 *
 */

public class DesiredQuantityValidator implements Validator<Orders> {
    private static final int MIN_QUANTITY = 0;
    private static final int MAX_QUANTITY = 1000000;

    public void validate(Orders t) {
        if (t.getDesiredQuantity() < MIN_QUANTITY|| t.getDesiredQuantity() > MAX_QUANTITY) {
            throw new IllegalArgumentException("The desired quantity limit is not respected!");
        }
    }
}



