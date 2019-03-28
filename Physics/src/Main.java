import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {
    public static void main(String[] args) {
        Main m = new Main();
        m.setVisible(true);
        m.setSize(500, 500);
        m.setDefaultCloseOperation(EXIT_ON_CLOSE);
        GUI gui = new GUI();
        m.add(gui);
    }
    
}
