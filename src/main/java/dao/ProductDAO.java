package dao;
import model.Product;
import java.util.List;

/**
 *
 * La fel ca in client, doar apelam metode mostenite din AbstractDAO: (la fel de multe ca in client aici)
 *
 */

public class ProductDAO extends AbstractDao<Product>{
    public ProductDAO()
    {
    }

    //Find:
    public Product findProductById(int id)
    {
        return findById(id);
    }

    //Find all:
    public List<Product> findAllProductsById()
    {
        return findAllById();
    }

    //Insert:
    public int insertProduct(Product product)
    {
        return insert(product);
    }

    //Update:
    public int updateProduct(Product product)
    {
        return update(product);
    }

    //Delete:
    public int deleteProductById(int id) { return deleteById(id); }
}
