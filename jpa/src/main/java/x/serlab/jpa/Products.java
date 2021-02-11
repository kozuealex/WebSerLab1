package x.serlab.jpa;

public class Products {
    int id;
    String name;
    boolean discontinued;

    public Products(int id, String name, boolean discontinued) {
        this.id = id;
        this.name = name;
        this.discontinued = discontinued;
    }
}
