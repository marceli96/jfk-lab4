package services;

import data.Product;
import org.python.core.PyInteger;
import org.python.util.PythonInterpreter;

import java.util.ArrayList;

public class ShowProducts {

    public static void main(String[] args) {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product(1, "test1", 2.2, 12));
        products.add(new Product(2, "test2", 12.2, 5));
        products.add(new Product(3, "test3", 5.2, 14));
        products.add(new Product(4, "test4", 7.2, 2));
        products.add(new Product(5, "test5", 8.2, 3));
        products.add(new Product(6, "test6", 9.2, 20));

        underLimit(products, 10);
    }

    public static void underLimit(ArrayList<Product> products, int limitValue) {
        PythonInterpreter interpreter = new PythonInterpreter();

        String code = "for product in products:\n" +
                "\tif product.amount < limitValue:" +
                "\t\tprint product.name";


        interpreter.set("products", products);
        interpreter.set("limitValue", new PyInteger(limitValue));
        interpreter.exec(code);
    }
}
