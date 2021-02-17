package x.serlab.plugin;

import com.fasterxml.jackson.databind.ObjectMapper;
import x.serlab.fileutils.JsonConverter;
import x.serlab.jpa.Products;
import x.serlab.spi.URLHandler;
import x.serlab.jpa.DAO;
import x.serlab.jpa.DAOImpl;

import java.io.IOException;
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
    public String handlePost(String body) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        Products product = mapper.readValue(body, Products.class);
        DAO dao = new DAOImpl();
        dao.createNew(product);

        List<Products> products = dao.printAll();
        System.out.println(products);

        JsonConverter converter = new JsonConverter();
        var json = converter.convertToJson(products);

        return json;
    }
}
