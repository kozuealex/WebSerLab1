package x.serlab.plugin;

import x.serlab.fileutils.JsonConverter;
import x.serlab.jpa.Products;
import x.serlab.spi.URLHandler;

import java.util.ArrayList;
import java.util.List;

public class ProductsHandler implements URLHandler {

    @Override
    public String handleURL() {

        List<Products> products = new ArrayList<>();

        products.add(new Products(1, "Apple", 20));
        products.add(new Products(2, "Orange", 25));


        JsonConverter converter = new JsonConverter();
        var json = converter.convertToJson(products);

        return json;
    }
}
