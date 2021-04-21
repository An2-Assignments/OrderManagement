package bll.validators;
import model.Product;

/**
 *
 * Validator de pret, astfel incat sa nu fie nici prea mare, nici prea mic (nici negativ)
 *
 */

public class PriceValidator implements Validator<Product> {
    private static final int MIN_PRICE = 0;
    private static final int MAX_PRICE = 1000000;

    public void validate(Product t) {
        if (t.getPrice() < MIN_PRICE|| t.getPrice() > MAX_PRICE) {
            throw new IllegalArgumentException("The price limit is not respected!");
        }
    }
}

