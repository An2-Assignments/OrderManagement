package bll.validators;
import model.Product;

/**
 *
 * Validator pentru quantity aflata in product, sa nu fie nici negativa, nici prea mare;
 *
 */

public class QuantityValidator implements Validator<Product> {
    private static final int MIN_QUANTITY = 0;
    private static final int MAX_QUANTITY = 1000000;

    public void validate(Product t) {
        if (t.getQuantity() < MIN_QUANTITY|| t.getQuantity() > MAX_QUANTITY) {
            throw new IllegalArgumentException("The quantity limit is not respected!");
        }
    }
}

