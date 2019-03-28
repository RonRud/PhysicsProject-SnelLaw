import javax.swing.*;
import java.awt.*;

public class MyButton extends Button {
    private JPanel diagramPanel;
    
    MyButton(JPanel diagramPanel) {
        this.diagramPanel = diagramPanel;
    }
}