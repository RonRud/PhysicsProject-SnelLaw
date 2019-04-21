import javax.swing.*;
import java.awt.*;

public class DiagramPanel extends JPanel {
    private double impactAngle = 0;
    private double refrectionAngle = 0;
    private double n1 = 0;
    private double n2 = 0;

    DiagramPanel() {
          this.setPreferredSize(new Dimension(1000,400));
          this.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        if((impactAngle + refrectionAngle + n1 + n2) != 0) {
            g.drawLine(0,200,1000,200);
            g.setFont(new Font("David", Font.PLAIN, 36));
            g.drawString("N1 = " + n1,800,250);
            g.drawString("N2 = " + n2,800, 150);
        }
    }

    public void newPaint(double impactAngle, double refrectionAngle, double n1, double n2) {
        this.impactAngle = impactAngle;
        this.refrectionAngle = refrectionAngle;
        this.n1 = n1;
        this.n2 = n2;
        repaint();
    }
}
