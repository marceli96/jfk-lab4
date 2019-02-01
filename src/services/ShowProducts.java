package services;

import data.Product;
import org.python.core.PyInteger;
import org.python.util.PythonInterpreter;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class ShowProducts {
    OutputStream outputStream;

    public ShowProducts(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void underLimit(ArrayList<Product> products, int limitValue) {
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.setOut(new PrintStream(outputStream));
        interpreter.setErr(new PrintStream(outputStream));

        String code = "for product in products:\n" +
                "\tif product.amount < limitValue:" +
                "\t\tprint product.name.encode('utf-8').strip()";


        interpreter.set("products", products);
        interpreter.set("limitValue", new PyInteger(limitValue));
        interpreter.exec(code);
    }
}
