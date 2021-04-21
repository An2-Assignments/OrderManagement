package model;

/**
 *
 * Analog cu clientul, nimic de adaugat
 *
 */

//Analog cu client, la fiecare metoda; (iara un tabel)
public class Product {
    private int idproduct;
    private int quantity;
    private String name;
    private int price;

    public Product(int idproduct, int quantity, String name, int price) {
        super();
        this.idproduct = idproduct;
        this.quantity = quantity;
        this.name = name;
        this.price = price;
    }

    public Product(int quantity, String name, int price) {
        super();
        this.quantity = quantity;
        this.name = name;
        this.price = price;
    }

    public Product()
    {

    }

    public int getIdproduct() {
        return idproduct;
    }
    public void setIdproduct(int idproduct) {
        this.idproduct = idproduct;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product [idproduct=" + idproduct + ", quantity=" + quantity +
                ", name=" + name + ", price=" + price + "]";
    }
}

