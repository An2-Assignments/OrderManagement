package bll.validators;
import model.Client;

/**
 *
 * Validator pentru varsta clientului. Daca nu se incadreaza, se arunca exceptie ce este prinsa in GUI
 *
 */

//Validare in functie de daca se incadreaza in varsta:
public class ClientAgeValidator implements Validator<Client> {
    private static final int MIN_AGE = 14;
    private static final int MAX_AGE = 80;

    public void validate(Client t) {
        if (t.getAge() < MIN_AGE || t.getAge() > MAX_AGE) {
            throw new IllegalArgumentException("The Client Age limit is not respected!");
        }
    }
}

