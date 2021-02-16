package x.serlab.fileutils;

import com.google.gson.Gson;
import x.serlab.jpa.Products;

import java.util.ArrayList;
import java.util.List;


public class TestConverter {
    public static void main(String[] args) {
        Gson gson = new Gson();

        Products products = new Products(4, "Juice", 20);

        List<Products> productsList = new ArrayList<>();
        productsList.add(products);

        JsonConverter converter = new JsonConverter();
        converter.convertToJson(productsList);
        var json = converter.convertToJson(productsList);
        System.out.println(json);

        var object = converter.convertFromJson(json);
        System.out.println(object);

    }
}
