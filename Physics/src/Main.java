import javax.swing.*;

public class Main extends JFrame {
    private static GUI g;
    
    public static void main(String[] args) {
        g = new GUI();
    }
    
    public static GUI getG() {
        return g;
    }
}
