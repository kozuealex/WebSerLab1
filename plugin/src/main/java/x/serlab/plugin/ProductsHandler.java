package x.serlab.plugin;

import x.serlab.fileutils.JsonConverter;
import x.serlab.jpa.Products;
import x.serlab.spi.URLHandler;
import x.serlab.jpa.DAO;
import x.serlab.jpa.DAOImpl;

import java.util.ArrayList;
import java.util.List;

public class ProductsHandler implements URLHandler {

    private int idNumber = 0;

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    @Override
    public String handleURL() {

        DAO dao = new DAOImpl();
        List<Products> products;

        if(idNumber > 0) {
            products = dao.findById(idNumber);
        } else {
            products = dao.printAll();
        }

        System.out.println(products);

        JsonConverter converter = new JsonConverter();
        var json = converter.convertToJson(products);

        return json;
    }

    @Override
    public String handlePost(String body) {

        DAO dao = new DAOImpl();
        JsonConverter converter = new JsonConverter();




        List<Products> printProducts;
        printProducts = dao.printAll();

        var json = converter.convertToJson(printProducts);

        return json;

    }
}
