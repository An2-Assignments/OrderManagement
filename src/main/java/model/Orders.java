package model;

/**
 *
 * Analog cu client, nimic de adaugat; Fieldurile sunt specifice, se explica in documentatie;
 *
 */

//Analog cu clientul
public class Orders {
    private int idOrders;
    private int idClient;
    private int idProdus;
    private int desiredQuantity;

    public Orders (int idOrders, int idClient, int idProdus, int desiredQuantity) {
        super();
        this.idOrders = idOrders;
        this.idClient = idClient;
        this.idProdus = idProdus;
        this.desiredQuantity = desiredQuantity;
    }

    public Orders (int idClient, int idProdus, int desiredQuantity) {
        super();
        this.idClient = idClient;
        this.idProdus = idProdus;
        this.desiredQuantity = desiredQuantity;
    }

    public Orders ()
    {

    }

    public int getIdOrders() {
        return idOrders;
    }
    public void setIdOrders(int idOrders) {
        this.idOrders = idOrders;
    }
    public int getIdClient() {
        return idClient;
    }
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
    public int getIdProdus() {
        return idProdus;
    }
    public void setIdProdus(int idProdus) {
        this.idProdus = idProdus;
    }
    public int getDesiredQuantity() {
        return desiredQuantity;
    }
    public void setDesiredQuantity(int desiredQuantity) {
        this.desiredQuantity = desiredQuantity;
    }

    @Override
    public String toString() {
        return "Orders [idorders=" + idOrders + ", idClient=" + idClient+
                ", idProdus=" + idProdus + ", desiredQuantity=" + desiredQuantity + "]";
    }
}

