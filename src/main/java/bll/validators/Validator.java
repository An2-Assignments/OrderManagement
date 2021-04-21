package bll.validators;

/**
 *
 * Interfata ce da metoda validate claselor validator, si cu aceasta metoda se fac testele;
 *
 * @param <T> Un parametru generic, folosim pentru tabele; (poate primi oricare tabele)
 */
public interface Validator<T>
{
    public void validate(T t);
}

