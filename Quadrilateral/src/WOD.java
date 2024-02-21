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
    int speed = 2;
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
            start = true;
            ((Timer)e.getSource()).stop();
        });

        SpawnTimer = new Timer(2000, e -> {
            create(pos);
            ((Timer)e.getSource()).stop();
            StartTimer.start();
        });

        speedIncreaseTimer = new Timer(15000, e -> {
            speed += 1;
            System.out.println("Speed Increased to " + speed);
        });
        speedIncreaseTimer.start();

        randomSpawn();

    }

    public void update() {
        switch (pos) {
            case 1:
                Indicator.setBounds(20, 300, 30, 64);
                Indicator.setIcon(Indicator1);

                if (start == true){
                    WOD.setBounds(WOD.getX() + speed, WOD.getY(), 32, 660);
                    WOD.setIcon(WODIcon1);
                }
                break;
            case 2:
                Indicator.setBounds(650, 630, 64, 30);
                Indicator.setIcon(Indicator4);
                if (start == true){
                    WOD.setBounds(WOD.getX(), WOD.getY() - speed, 820, 32);
                    WOD.setIcon(WODIcon2);
                }
                break;
            case 3:
                Indicator.setBounds(1230, 300, 30, 64);
                Indicator.setIcon(Indicator2);
                if (start == true){
                    WOD.setBounds(WOD.getX() - speed, WOD.getY(), 32, 660);
                    WOD.setIcon(WODIcon1);
                }
                break;
            case 4:
                Indicator.setBounds(660, 10, 64, 30);
                Indicator.setIcon(Indicator3);
                if (start == true){
                    WOD.setBounds(WOD.getX(), WOD.getY() + speed, 820, 32);
                    WOD.setIcon(WODIcon2);
                }
                break;
        }

        if (
                pos == 1 && WOD.getX() > 1300
                || pos == 3 && WOD.getX() < -32
                || pos == 2 && WOD.getY() < -32
                || pos == 4 && WOD.getY() > 690) {
            start = false;
            randomSpawn();
        }
    }

    public void create(int pos) {
        switch (pos) {
            case 1:
                WOD.setBounds(-32,15,32,660);
                break;
            case 2:
                WOD.setBounds(278,690,820,32);
                break;
            case 3:
                WOD.setBounds(1232,10,32,660);
                break;
            case 4:
                WOD.setBounds(278,-32,820,32);
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
}