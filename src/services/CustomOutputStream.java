package services;

import javafx.scene.control.TextArea;

import java.io.OutputStream;

public class CustomOutputStream extends OutputStream {

    private TextArea textArea;

    public CustomOutputStream(TextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void write(int b)
    {
        // redirects data to the text area
        textArea.appendText(String.valueOf((char)b));
        // scrolls the text area to the end of data
//        textArea.positionCaret(textArea.getDocument().getLenght());
    }
}