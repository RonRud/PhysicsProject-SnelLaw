import javax.swing.*;
import java.awt.*;

public class DiagramPanel extends JPanel {
    private double impactAngle = 0;
    private double refrectionAngle = 0;
    private double n1 = 0;
    private double n2 = 0;

    DiagramPanel() {
          this.setPreferredSize(new Dimension(1000,500));
          this.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        if((impactAngle + refrectionAngle + n1 + n2) != 0) {
            g.drawLine(0,250,1000,250);
            g.setFont(new Font("David", Font.PLAIN, 36));

            for(int i=0;i< 500;i += 10) {
                if(i%20 == 0){
                    g.drawLine(500,i,500,i+10);
                }
            }

            g.drawString("N1 = " + n1,800,300);
            g.drawString("N2 = " + n2,800, 200);
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
