package rate;

import data.Product;
import javafx.scene.control.TextArea;
import org.jruby.embed.ScriptingContainer;

import java.io.PrintStream;

public class Value
{
//      jRuby
    public static void count(Product[] products, TextArea textArea)
    {
        PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
        System.setOut(printStream);
        System.setErr(printStream);

        ScriptingContainer container = new ScriptingContainer();

        String script =
                "def countRuby(mArray)\n" +
                    "    \n" +
                    "    value = 0\n" +
                    "    for array in mArray\n" +
                    "    \n" +
                    "       value += array.price*array.amount" +
                    "    end\n" +
                    "\n" +
                    "    " +
                    "    puts \"Value of the shop: \"\n" +
                    "    puts value\n" +
                    "    puts \"\"\n" +
                "end";

        Object receiver = container.runScriptlet(script);
        Object[] args = new Object[1];
        args[0] = products;
        container.callMethod(receiver, "countRuby", args, null);

    }

}
