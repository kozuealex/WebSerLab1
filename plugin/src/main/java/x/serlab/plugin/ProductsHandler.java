package x.serlab.plugin;

import x.serlab.fileutils.JsonConverter;
import x.serlab.jpa.Products;
import x.serlab.spi.URLHandler;
import x.serlab.jpa.DAO;
import x.serlab.jpa.DAOImpl;

import java.util.ArrayList;
import java.util.List;

public class ProductsHandler implements URLHandler {

    @Override
    public String handleURL() {

        List<Products> products;
//        products.add(new Products(1, "Apple", 10));

        DAO dao = new DAOImpl();
        products = dao.findById(1);

        JsonConverter converter = new JsonConverter();
        var json = converter.convertToJson(products);

        return json;
    }
}
