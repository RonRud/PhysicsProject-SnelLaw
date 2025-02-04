
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private double impactAngle = 0;
    private double refrectionAngle = 0;
    private double n1 = 1; // refractive index in
    private double n2 = 1.5; // refractive index out

    private double radiantImpactAngle;
    private double radiantRefractionAngle;

    private ShyTextField tImpactAngle;
    private ShyTextField tRefractionAngle;
    private ShyTextField tRefractiveIndexIn;
    private ShyTextField tRefractiveIndexOut;

    private boolean isEnteredImpactAngle = true;
    private boolean isEnteredRefractiveAngle = true;
    private boolean isEnteredN1 = true; //did the user Enter Refractive Index In
    private boolean isEnteredN2 = true; //did the user Enter Refractive Index Out

    private class ButtonsPane extends JPanel {
        ButtonsPane() {
            this.setVisible(true);
            this.setSize(1000,200);
            this.setPreferredSize(new Dimension(1000,150));

            tImpactAngle = new ShyTextField("Enter impact Angle",19);
            tImpactAngle.setFont(new Font("David", Font.PLAIN, 30));
            this.add(tImpactAngle);

            tRefractionAngle = new ShyTextField("Enter reflection Angle",19);
            tRefractionAngle.setFont(new Font("David", Font.PLAIN, 30));
            this.add(tRefractionAngle);

            tRefractiveIndexIn = new ShyTextField("Enter refractive index (n1)", 19);
            tRefractiveIndexIn.setFont(new Font("David", Font.PLAIN, 30));
            this.add(tRefractiveIndexIn);

            tRefractiveIndexOut = new ShyTextField("Enter refractive index (n2)",19);
            tRefractiveIndexOut.setFont(new Font("David", Font.PLAIN, 30));
            this.add(tRefractiveIndexOut);
        }
    }

    public GUI() {
        this.setSize(1000,700);
        this.setLayout(new FlowLayout());
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        ButtonsPane ButtonsPaneInstanse = new ButtonsPane();
        this.add(ButtonsPaneInstanse);

        DiagramPanel J = new DiagramPanel(); //TODO custom Panel for graphics
        MyButton start = new MyButton("START",J);

        start.setPreferredSize(new Dimension(500,50));
        //start.setSize(500,50);
        //J.setSize(1000,400);

        start.setFont(new Font("David", Font.BOLD, 36));
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //impactAngle = Integer.parseInt(tImpactAngle.getText());
                //refrectionAngle = Integer.parseInt(tRefractionAngle.getText());
                //refractiveIndex = Integer.parseInt(tRefractiveIndexOut.getText());
                if(inputChecker()) { // puts all inputs in the private variabeles and checks if they're correct
                    System.out.println("true");
                    radiantImpactAngle = Math.toRadians(impactAngle);
                    radiantRefractionAngle = Math.toRadians(refrectionAngle);

                    if(whatToDo()) {
                        System.out.println("true");
                        System.out.println(impactAngle + " " + refrectionAngle + " " + n1 + " " + n2);
                        start.diagramPanel.newPaint();
                    }
                }
                isEnteredImpactAngle = true; // sets the booleans to default to prevent mistakes in retry
                isEnteredRefractiveAngle = true;
                isEnteredN1 = true;
                isEnteredN2 = true;
                //TODO print the diagram
            }
        });
        this.add(start);
        this.add(J);
        SwingUtilities.updateComponentTreeUI(this);
    }
    
    public boolean inputChecker() {
        // this function sets the user input into the private variables, it returns a boolean about the input validation
        if(tImpactAngle.getText() .equals("Enter impact Angle") || tImpactAngle.getText().equals(""))
        { isEnteredImpactAngle = false; } //saves inputs that weren't entered for later check
        else {
            try {
                impactAngle = Double.parseDouble(tImpactAngle.getText());
                if((0 > impactAngle) || (impactAngle > 180)) { // checks if its ok (between 0 - 180)
                    JOptionPane.showMessageDialog(this, "impact angle needs to be between 0-180", "", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Impact angle invalid", "", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        if(tRefractionAngle.getText() .equals("Enter reflection Angle") || tRefractionAngle.getText() .equals(""))
        {isEnteredRefractiveAngle = false; } //saves inputs that weren't entered for later check
        else {
            try {
                refrectionAngle = Double.parseDouble(tRefractionAngle.getText());
                if((0 > refrectionAngle) || (refrectionAngle > 180)) { // checks if its ok
                    JOptionPane.showMessageDialog(this, "reflaction angle needs to be between 0-180", "", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "reflaction angle invalid", "", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        if(tRefractiveIndexIn.getText() .equals("Enter refractive index (n1)") || tRefractiveIndexIn.getText() .equals(""))
        { isEnteredN1 = false; }
        else {
            try {
                n1 = Double.parseDouble(tRefractiveIndexIn.getText());
                if(1 > n1) { //check if n's ok
                    JOptionPane.showMessageDialog(this, "n1 needs to be at least 1","", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "n1 is invalid", "", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        if(tRefractiveIndexOut.getText() .equals("Enter refractive index (n2)") || tRefractiveIndexOut.getText().equals(""))
        { isEnteredN2 = false; }
        else {
            try {
                n2 = Double.parseDouble(tRefractiveIndexOut.getText());
                if(1 > n2) { //check if n's ok
                    JOptionPane.showMessageDialog(this, "n2 needs to be at least 1","", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "n2 is invalid", "", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }


    public boolean whatToDo() { //checks which input was received and decides what to do (returns a boolean for if the program can proceed to printing)
                                //everything is calculated with Snel's law - n1 * sin(ImpactAngle) = n2 * sin(RefractionAngle)
                                //sin() expects radians (Math.toRadiants(degrees))
        if(isEnteredN1) { //n1 true
            if(isEnteredN2) { //n1,n2 true
                if(isEnteredImpactAngle) { //n1,n2,ImpactAngle true
                    if(isEnteredRefractiveAngle) {// all true FINAL
                        if((n1 * Math.sin(radiantImpactAngle)) == n2 * Math.sin(radiantRefractionAngle)) //checks if input was ok
                            return true;
                        else {
                            JOptionPane.showMessageDialog(this, "your input cannot be true check");//TODO add
                            return false;
                        }
                    } else { //n1,n2,ImpactAngle true , RefractiveAngle false FINAL
                        radiantRefractionAngle = Math.asin((Math.sin(radiantImpactAngle) * n1) / n2); //calculates refractionAngle
                        refrectionAngle = Math.toDegrees(radiantRefractionAngle);
                        return true;
                    }
                }else { //n1,n2 true , ImpactAngle false
                    if(isEnteredRefractiveAngle) { //n1,n2,RefractiveAngle true , ImpactAngle false FINAL
                        radiantImpactAngle = Math.asin((Math.sin(radiantRefractionAngle) * n2)) / n1; // calculates impactAngle
                        impactAngle = Math.toDegrees(radiantImpactAngle);
                        return true;
                    }
                    else { //n1,n2 true , ImpactAngle,RefractiveAngle false FINAL
                        //TODO add a massage here that displays an image explaining how differences in n1 to n2 effects the angles
                        return false;
                    }
                }
            }else { //n1=true, n2=false
                if(isEnteredImpactAngle) { //n1,ImpactAngle true , n2 false
                    if(isEnteredRefractiveAngle) { //n1,ImpactAngle,RefractiveAngle true , n2 false FINAL
                        n2 = Math.sin(radiantImpactAngle) * n1 / radiantRefractionAngle; // calculates n2
                        return  true;
                    }
                    else { //n1,ImpactAngle true , n2,RefractiveAngle false FINAL
                        JOptionPane.showMessageDialog(this, "not enough info!", "ENTER MORE", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                } else { // n1 true , n2,ImpactAngle false
                    if(isEnteredRefractiveAngle) { //n1,RefractiveAngle true , n2,ImpactAngle false FINAL
                        JOptionPane.showMessageDialog(this, "not enough info!", "ENTER MORE", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                    else { //n1 true , n2,ImpactAngle,RefractiveAngle false FINAL
                        JOptionPane.showMessageDialog(this, "not enough info!", "ENTER MORE", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                }
            }
        }else { //n1=false
            if(isEnteredN2) { //n2 true , n1 false
                if(isEnteredImpactAngle) { //n2,ImpactAngle true , n1 false
                    if(isEnteredRefractiveAngle) { //n2,ImpactAngle,RefractiveAngle true , n1 false FINAL
                        n1 = (Math.sin(radiantRefractionAngle) * n2) / Math.sin(radiantImpactAngle); //caculates n1
                        return true;
                    }else { //n2 true,ImpactAngle , n1,RefractiveAngle false FINAL
                        JOptionPane.showMessageDialog(this, "Info", "assuming n1 = 1",JOptionPane.INFORMATION_MESSAGE);
                        radiantRefractionAngle = Math.asin(Math.sin(radiantImpactAngle)/n2); //calculates refrectionAngle assuming n1 = 1
                        refrectionAngle = Math.toDegrees(radiantRefractionAngle);
                        return true;
                    }
                } else { //n2 true , n1,ImpactAngle false
                    if(isEnteredRefractiveAngle) { //n2,RefractiveAngle true , n1,ImpactAngle false FINAL
                        JOptionPane.showMessageDialog(this, "Info", "assuming n1 = 1",JOptionPane.INFORMATION_MESSAGE);
                        radiantImpactAngle = Math.sin(radiantRefractionAngle) * n2; //calculates impactAngle assuming n1 = 1 and therefore not diving in anything
                        impactAngle = Math.toDegrees(radiantImpactAngle);
                        return true;
                    }else { //n2 true , all others are false FINAL
                        JOptionPane.showMessageDialog(this, "not enough info!", "ENTER MORE", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                }
            }else { // n1,n2 false
                if(isEnteredImpactAngle) { //ImpactAngle true , n1,n2 false
                    if(isEnteredRefractiveAngle) {//ImpactAngle,RefractiveAngle true , n1,n2 false FINAL
                        JOptionPane.showMessageDialog(this, "Info", "assuming n1 = 1",JOptionPane.INFORMATION_MESSAGE);
                        n2 = Math.sin(radiantImpactAngle) / Math.sin(radiantRefractionAngle); //calculates n2 assuming n1 = 1
                        return true;
                    }else { //ImpactAngle true , n1,n2,RefractiveAngle false FINAL
                        JOptionPane.showMessageDialog(this, "not enough info!", "ENTER MORE", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                } else { //n1,n2,ImpactAngle false
                    if(isEnteredRefractiveAngle) { //RefractiveAngle true , n1,n2,ImpactAngle FINAL
                        JOptionPane.showMessageDialog(this, "not enough info!", "ENTER MORE", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }else { //all false FINAL
                        JOptionPane.showMessageDialog(this, "not enough info!", "you need to enter numbers in the text boxs",JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                }
            }
        }
    }
    
    public double getImpactAngle() {
        return impactAngle;
    }
    
    public double getRefractionAngle() {
        return refrectionAngle;
    }
    
    public double getN1() {
        return n1;
    }
    
    public double getN2() {
        return n2;
    }
    
    public double getRadiantImpactAngle() {
        return radiantImpactAngle;
    }
    
    public double getRadiantRefractionAngle() {
        return radiantRefractionAngle;
    }
    
    public boolean isEnteredImpactAngle() {
        return isEnteredImpactAngle;
    }
    
    public boolean isEnteredRefractiveAngle() {
        return isEnteredRefractiveAngle;
    }
    
    public boolean isEnteredN1() {
        return isEnteredN1;
    }
    
    public boolean isEnteredN2() {
        return isEnteredN2;
    }
}