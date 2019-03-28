import javax.swing.*;
import java.awt.event.*;

public class ShyTextField extends JTextField { //this is a textfield that makes the text disappear when focused
    private String defaultOutputText;
    
    public ShyTextField(String defaultOutputText, int length){
        super(defaultOutputText, length);
        
        this.defaultOutputText = defaultOutputText;
        
        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                ShyTextField.this.setText("");
            }
    
            @Override
            public void focusLost(FocusEvent focusEvent) {
                if(ShyTextField.this.getText().equals("")) {
                    ShyTextField.this.setText(ShyTextField.this.defaultOutputText);
                }
            }
        });
    }
}
