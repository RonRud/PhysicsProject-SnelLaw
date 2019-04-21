import javax.swing.*;
import java.awt.*;

public class MyButton extends Button {
    public DiagramPanel diagramPanel;
    
    MyButton(String title ,DiagramPanel diagramPanel) {
        super(title);
        this.diagramPanel = diagramPanel;
    }

    public JPanel getInputedClass() {
        return diagramPanel;
    }
}