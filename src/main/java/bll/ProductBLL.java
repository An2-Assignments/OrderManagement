package bll;

import bll.validators.*;
import dao.ProductDAO;
import model.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 * Validam fieldurile din product, inainte sa le folosim pentru operatii. Aproape identic cu client.
 *
 */

public class ProductBLL {
    private List<Validator<Product>> validators;
    ProductDAO operatiiSql;

    //Analog cu client, la toate metodele;
    public ProductBLL() {
        operatiiSql = new ProductDAO();
        validators = new ArrayList<Validator<Product>>();
        validators.add(new PriceValidator());
        validators.add(new QuantityValidator());
    }

    //Find:
    public Product findProductByIdBLL(int id) {
        Product pr = operatiiSql.findProductById(id);
        if (pr == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        System.out.println("Am gasit: " + pr.toString());
        return pr;
    }

    //Find all:
    public List<Product> findAllProductsByIdBLL() {
        List<Product> pr = operatiiSql.findAllProductsById();
        if (pr == null) {
            throw new NoSuchElementException("The products were not found!");
        }
        System.out.println("Am gasit produsele:");
        for(Product p: pr)
        {
            System.out.println(p.toString());
        }
        System.out.println("");
        return pr;
    }

    //Insert:
    public int insertProductBLL(Product product) {
        for (Validator<Product> p : validators) {
            p.validate(product);
        }
        System.out.println("Am inserat: " + product.toString());
        return operatiiSql.insertProduct(product);
    }

    //Update:
    public int updateProductBLL(Product product) {
        for (Validator<Product> p : validators) {
            p.validate(product);
        }
        System.out.println("Am updatat: " + product.toString());
        return operatiiSql.updateProduct(product);
    }

    //Delete:
    public void deleteProductByIdBLL(int id) {
        int pr = operatiiSql.deleteProductById(id);
        if (pr == -1) {
            throw new NoSuchElementException("The product with id =" + id + " was not found to be deleted!");
        }
        System.out.println("Am sters produsul.");
    }
}
