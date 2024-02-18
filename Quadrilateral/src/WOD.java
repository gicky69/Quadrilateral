import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class WOD {
    JLabel WOD;
    Main MF;
    ImageIcon WODIcon1 = new ImageIcon("Quadrilateral/src/Images/Walls/Wall1.gif");
    ImageIcon WODIcon2 = new ImageIcon("Quadrilateral/src/Images/Walls/Wall2.gif");
    Random rand;
    int pos;
    int prevPos = -1;
    int speed = 2;

    public WOD() {
        WOD = new JLabel();

        rand = new Random();
        WOD.setVisible(true);

        randomSpawn();
    }

    public void update() {
        switch (pos) {
            case 1:
                WOD.setBounds(WOD.getX() + speed, WOD.getY(), 32, 660);
                WOD.setIcon(WODIcon1);
                break;
            case 2:
                WOD.setBounds(WOD.getX(), WOD.getY() - speed, 820, 32);
                WOD.setIcon(WODIcon2);
                break;
            case 3:
                WOD.setBounds(WOD.getX() - speed, WOD.getY(), 32, 660);
                WOD.setIcon(WODIcon1);
                break;
            case 4:
                WOD.setBounds(WOD.getX(), WOD.getY() + speed, 820, 32);
                WOD.setIcon(WODIcon2);
                break;
        }

        if (
                pos == 1 && WOD.getX() > 1250
                || pos == 3 && WOD.getX() < 100
                || pos == 2 && WOD.getY() < 0
                || pos == 4 && WOD.getY() > 660) {
            randomSpawn();
        }
    }

    public void create(int pos) {
        switch (pos) {
            case 1:
                WOD.setBounds(270,15,32,660);
                break;
            case 2:
                WOD.setBounds(278,630,820,32);
                break;
            case 3:
                WOD.setBounds(1200,10,32,660);
                break;
            case 4:
                WOD.setBounds(278,0,820,32);
                break;
        }
    }

    public void randomSpawn() {
        do {
            pos = rand.nextInt(4) + 1;
        } while (pos == prevPos);

        pos = rand.nextInt(4) + 1;
        prevPos = pos;
        System.out.println(pos);
        create(pos);
    }
}