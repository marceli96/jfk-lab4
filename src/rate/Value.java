package rate;

import data.Product;
import org.jruby.embed.ScriptingContainer;

import java.io.OutputStream;
import java.io.PrintStream;

public class Value
{
    private OutputStream outputStream;

    Value(OutputStream outputStream)
    {
        this.outputStream = outputStream;
    }
//      jRuby
    public void count(Product[] products)
    {
        PrintStream printStream = new PrintStream(outputStream);

        ScriptingContainer container = new ScriptingContainer();
        container.setOutput(printStream);
        container.setError(printStream);

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
