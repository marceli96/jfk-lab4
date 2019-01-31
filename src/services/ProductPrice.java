package services;

import data.Product;
import javafx.scene.control.TextArea;
import org.jruby.embed.ScriptingContainer;

import java.io.PrintStream;

public class ProductPrice {
    private TextArea textArea;

    public ProductPrice(TextArea textArea) {
        this.textArea = textArea;
    }

    public void increaseDecrease(Product[] products, Double percent, Integer sign) {

        PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
        System.setOut(printStream);
        System.setErr(printStream);

        ScriptingContainer container = new ScriptingContainer();

        String script =
                "class Product\n" +
                    "attr_accessor :id, :name, :price, :amount\n" +
                "end\n" +
                "def method(mArray, percent, sign)\n" +
                        "    \n" +
                        "    if sign == 0\n" +
                        "        percent = 1 + percent / 100\n" +
                        "    else\n" +
                        "        percent = 1 - percent / 100\n" +
                        "    end\n" +
                        "    \n" +
                        "    newProducts = []\n" +
                        "    for array in mArray\n" +
                        "        newProduct = Product.new()\n" +
                        "    \n" +
                        "        newProduct.price = (array.price*percent).round(2)\n" +
                        "        newProduct.name = array.name\n" +
                        "        newProduct.id = array.id\n" +
                        "        newProduct.amount = array.amount\n" +
                        "        newProducts.push(newProduct)\n" +
                        "    end\n" +
                        "\n" +
                        "    for product in newProducts\n" +
                        "        puts \"new prod.: \"\n" +
                        "        puts product.id\n" +
                        "        puts product.price\n" +
                        "        puts product.name\n" +
                        "        puts product.amount\n" +
                        "        puts \"\"\n" +
                        "    end\n" +
                "end";
        Object receiver = container.runScriptlet(script);
        Object[] args = new Object[3];
        args[0] = products;
        args[1] = percent;
        args[2] = sign;
        container.callMethod(receiver, "method", args, null);
    }
}
