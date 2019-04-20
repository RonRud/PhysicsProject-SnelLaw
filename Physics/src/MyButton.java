import javax.swing.*;
import java.awt.*;

public class MyButton extends Button {
    private JPanel diagramPanel;
    
    MyButton(String title ,JPanel diagramPanel) {
        super(title);
        this.diagramPanel = diagramPanel;
    }

    public JPanel getInputedClass() {
        return diagramPanel;
    }
}