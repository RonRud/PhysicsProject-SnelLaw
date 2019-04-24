import javax.swing.*;
import java.awt.*;

public class DiagramPanel extends JPanel {
    private double radiantImpactAngle = 0;
    private double radiantRefrectionAngle = 0;
    private double n1 = 0;
    private double n2 = 0;
    private double impactAngle;
    private double refrectionAngle;

    DiagramPanel() {
          this.setPreferredSize(new Dimension(1000,500));
          this.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        if((radiantImpactAngle + radiantRefrectionAngle + n1 + n2) != 0) {
            g.drawLine(0,250,1000,250);
            g.setFont(new Font("David", Font.PLAIN, 36));

            for(int i=0;i< 500;i += 10) {
                if(i%20 == 0){
                    g.drawLine(500,i,500,i+10);
                }
            }

            int o = (int)(Math.tan(radiantImpactAngle) * 250); //draws the line for impact of light
            g.drawLine(500,250,500 + o,0);

            int upParameterForDirection = (int)(Math.tan(Math.toRadians(45- impactAngle)) * 25);
            g.drawLine(500 + (o/2),125,500 + (o/2) + 25, 125 - upParameterForDirection);
            g.drawArc(500,200,o/5,50,(int)impactAngle,(int)impactAngle);

            g.drawString("N1 = " + n1,800,300);
            g.drawString("N2 = " + n2,800, 200);
        }
    }

    public void newPaint(double impactAngle, double refrectionAngle, double radiantImpactAngle, double radiantRefrectionAngle, double n1, double n2) {
        this.radiantImpactAngle = radiantImpactAngle;
        this.radiantRefrectionAngle = radiantRefrectionAngle;
        this.n1 = n1;
        this.n2 = n2;
        this.impactAngle = impactAngle;
        this.refrectionAngle = refrectionAngle;
        repaint();
    }
}
