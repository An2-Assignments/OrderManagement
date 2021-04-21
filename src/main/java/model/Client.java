package model;

/**
 *
 * O clasa de client simpla. Este exact ca in SQL, avem aceeasi nume de tabela, aceleasi
 * nume de fielduri, si aceleasi tipuri, altfel nu ar functiona.
 * Avem nevoie de setteri si getteri pentru java reflection, si de asemenea de mai
 * multe versiuni de constructori.
 *
 */

public class Client {
    //Fieldurile
    private int idclient;
    private String name;
    private String address;
    private String email;
    private int age;

    //3 tipuri de constructori: cu id, fara id, fara parametrii:
    public Client(int idclient, String name, String address, String email, int age) {
        super();
        this.idclient = idclient;
        this.name = name;
        this.address = address;
        this.email = email;
        this.age = age;
    }

    public Client(String name, String address, String email, int age) {
        super();
        this.name = name;
        this.address = address;
        this.email = email;
        this.age = age;
    }

    public Client() {

    }

    //Setteri si getteri:
    public int getIdclient() { return idclient; }
    public void setIdclient(int idclient) { this.idclient = idclient; }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    //O metoda de afisare a clasei, suprascrisa:
    @Override
    public String toString() {
        return "Client [idclient=" + idclient + ", name=" + name +
                ", address=" + address + ", email=" + email + ", age=" + age + "]";
    }
}

