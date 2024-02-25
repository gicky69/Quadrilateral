import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class WOD {
    JLabel WOD;
    JLabel Indicator;
    Main MF;
    ImageIcon WODIcon1 = new ImageIcon("Quadrilateral/src/Images/Walls/Wall1.gif");
    ImageIcon WODIcon2 = new ImageIcon("Quadrilateral/src/Images/Walls/Wall2.gif");
    ImageIcon Indicator1 = new ImageIcon("Quadrilateral/src/Images/Indicator1.gif");
    ImageIcon Indicator2 = new ImageIcon("Quadrilateral/src/Images/Indicator2.gif");
    ImageIcon Indicator3 = new ImageIcon("Quadrilateral/src/Images/Indicator3.gif");
    ImageIcon Indicator4 = new ImageIcon("Quadrilateral/src/Images/Indicator4.gif");

    Random rand;
    int pos;
    int prevPos = -1;
    int speed = 3;
    boolean start = false;

    Timer SpawnTimer;
    Timer StartTimer;
    Timer speedIncreaseTimer;

    public WOD() {
        WOD = new JLabel();
        Indicator = new JLabel();

//        Indicator.setBorder(BorderFactory.createLineBorder(Color.RED));
        Indicator.setVisible(true);

        rand = new Random();
        WOD.setVisible(true);
        StartTimer = new Timer(1500, e -> {
            WOD.setVisible(true);
            start = true;
            ((Timer)e.getSource()).stop();
        });

        SpawnTimer = new Timer(2000, e -> {
            create(pos);
            ((Timer)e.getSource()).stop();
            StartTimer.start();
        });

        randomSpawn();
    }

    public void update() {
        System.out.println(pos);
        if (start){
            switch (pos) {
                case 1:
                    if (start){
                        WOD.setLocation(WOD.getX() + speed, WOD.getY());
                        WOD.setIcon(WODIcon1);
                    }
                    break;
                case 2:
                    if (start){
                        WOD.setLocation(WOD.getX(), WOD.getY() - speed);
                        WOD.setIcon(WODIcon2);
                    }
                    break;
                case 3:
                    if (start){
                        WOD.setLocation(WOD.getX() - speed, WOD.getY());
                        WOD.setIcon(WODIcon1);
                    }
                    break;
                case 4:
                    if (start){
                        WOD.setLocation(WOD.getX(), WOD.getY() + speed);
                        WOD.setIcon(WODIcon2);
                    }
                    break;
            }
        }

        if (
                pos == 1 && WOD.getX() > 1300
                        || pos == 3 && WOD.getX() < -38
                        || pos == 2 && WOD.getY() < -38
                        || pos == 4 && WOD.getY() > 952) {
            start = false;
            randomSpawn();
        }
    }

    public void create(int pos) {
        switch (pos) {
            case 1:
                Indicator.setBounds(20, 450, 30, 64);
                Indicator.setIcon(Indicator1);

                WOD.setBounds(-38,120,38,730);
                break;
            case 2:
                Indicator.setBounds(650, 850, 64, 30);
                Indicator.setIcon(Indicator4);
                WOD.setBounds(200,952,900,38);
                break;
            case 3:
                Indicator.setBounds(1230, 450, 30, 64);
                Indicator.setIcon(Indicator2);
                WOD.setBounds(1500,110,38,730);
                break;
            case 4:
                Indicator.setBounds(650, 10, 64, 30);
                Indicator.setIcon(Indicator3);
                WOD.setBounds(200,-38,900,38);
                break;
        }
    }

    public void randomSpawn() {
        do {
            pos = rand.nextInt(4) + 1;
        } while (pos == prevPos);

        prevPos = pos;
        SpawnTimer.start();
    }

    public void reset() {
        WOD.setVisible(false);
        WOD.setBounds(-100,-100,0,0);
        randomSpawn();
        start = false;
    }
}