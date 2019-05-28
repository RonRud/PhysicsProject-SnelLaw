import javax.swing.*;
import java.awt.*;

public class DiagramPanel extends JPanel {
    private double radiantImpactAngle = 0;
    private double radiantRefrectionAngle = 0;
    private double n1 = 0;
    private double n2 = 0;
    private double impactAngle;
    private double refrectionAngle;
    private GUI g;

    DiagramPanel() {
          this.setPreferredSize(new Dimension(1000,500));
          this.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        if ((radiantImpactAngle + radiantRefrectionAngle + n1 + n2) != 0) {
            g.drawLine(0, 250, 1000, 250);
            g.setFont(new Font("David", Font.PLAIN, 36));

            for (int i = 0; i < 500; i += 10) {
                if (i % 20 == 0) {
                    g.drawLine(500, i, 500, i + 10);
                }
            }

            int impactLineSideParameter = (int) (Math.tan(radiantImpactAngle) * 250); //draws the line for impact of light
            g.drawLine(500, 250, 500 + impactLineSideParameter, 0);

            //int upParameterForDirection = (int)(Math.tan(Math.toRadians(45- impactAngle)) * 25); old version works (backup)
            //g.drawLine(500 + (o/2),125,500 + (o/2) + 25, 125 - upParameterForDirection); ^^^
            int impactLineRightNewX = (int) (Math.cos(Math.toRadians(45 - impactAngle)) * 25 + 500 + impactLineSideParameter / 2); //calculates what to add to x and adds it to x
            int impactLineRightNewY = (int) (125 - Math.sin(Math.toRadians(45 - impactAngle)) * 25); //calculates what to add to y (lowers because y starts from the top) and adds it to y
            g.drawLine(500 + impactLineSideParameter / 2, 125, impactLineRightNewX, impactLineRightNewY);
            //g.drawArc(500,200,o/5,50,0,90 + (int)impactAngle);

            int impactLineLeftNewX = (int)(Math.cos(Math.toRadians(135 - impactAngle)) * 25 + 500 + (impactLineSideParameter / 2)); //calculates what to add to x and adds it to x
            int imapctLinLeftNewY = (int) (125 - Math.sin(Math.toRadians(135 - impactAngle)) * 25); //calculates what to add to y (lowers because y starts from the top) and adds it to y
            g.drawLine(500 + (impactLineSideParameter / 2), 125, impactLineLeftNewX, imapctLinLeftNewY);

            g.drawString("N1 = " + n1, 800, 200);
            g.drawString("N2 = " + n2, 800, 300);
            //display formula for user
            g.setFont(new Font("David", Font.PLAIN, 24));
            g.drawString("N1*sin(impactAngle) = N2*sin(refractionAngle)", 500, 320); // Refraction instead of Refrection but I put it wrong for consistency's sake
            
            if ((Math.sin(radiantImpactAngle) * n1/n2) > 1) { //works when full return
                g.drawLine(500, 250, 500 - impactLineSideParameter, 0); //draws the return line assuming it's the same angle the distance will be o
                int returnLineLeftNewX = (int) (500 - impactLineSideParameter / 2 - Math.cos(Math.toRadians(45 - impactAngle)) * 25);
                int returnLineLeftNewY = (int) (125 - Math.sin(Math.toRadians(45 - impactAngle)) * 25);
                g.drawLine(500 - impactLineSideParameter / 2, 125, returnLineLeftNewX, returnLineLeftNewY);
    
                //int returnLineRightNewX = (int) (Math.cos(Math.toRadians(135 - impactAngle)) * 25 + 500 + (o / 2));
                //int returnLineRightNewY = (int) (125 - Math.sin(Math.toRadians(135 - impactAngle)) * 25);
            }else {
                int refractionLineSideParameter = (int) (Math.tan(radiantRefrectionAngle) * 250);
                g.drawLine(500, 250, 500 - refractionLineSideParameter, 500);

                int refractionLineLeftNewX = (int) (500 - (refractionLineSideParameter/2) + Math.sin(Math.toRadians(45 +refrectionAngle)) * 25);
                int refractionLineLeftNewY = (int) (375 - (Math.cos(Math.toRadians(45 +refrectionAngle)) * 25));
                g.drawLine(500 - refractionLineSideParameter/2, 375, refractionLineLeftNewX, refractionLineLeftNewY);
            }
        }
    }
    public void newPaint() {
        this.g = Main.getG();
        this.radiantImpactAngle = g.getRadiantImpactAngle();
        this.radiantRefrectionAngle = g.getRadiantRefractionAngle();
        this.n1 = g.getN1();
        this.n2 = g.getN2();
        this.impactAngle = g.getImpactAngle();
        this.refrectionAngle = g.getRefractionAngle();
        repaint();
    }
}
