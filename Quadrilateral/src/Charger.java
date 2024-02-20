import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Charger {
    JLabel Charger;
    Timer ChargerTimeSpawn;
    Timer ChargeTimer;
    Timer ChargerDelay;
    boolean start = false;
    int velocity = 3;

    public Charger() {
        Charger = new JLabel();
        ImageIcon ChargerIcon = new ImageIcon("Quadrilateral/src/Images/Charger.gif");
        Charger.setIcon(ChargerIcon);
        Charger.setBorder(BorderFactory.createLineBorder(Color.RED));
        Charger.setBounds(0,0,48,48);
        Charger.setVisible(false);

        ChargerTimeSpawn = new Timer(1500, e -> {
            randomSpawn();
            ((Timer)e.getSource()).stop();
        });

        ChargeTimer = new Timer(1000, e -> {
           if (Charger.isVisible()){
                start = true;
                ChargerDelay.start();
           }
            ((Timer)e.getSource()).stop();
        });

        ChargerDelay = new Timer(1000, e -> {
            Charger.setVisible(false);
            start = false;
            ChargerTimeSpawn.start();
            ((Timer)e.getSource()).stop();
        });
        ChargerTimeSpawn.start();
    }

    public void update(Player player) {
        int dy = player.oldPosY - Charger.getY();
        int dx = player.oldPosX - Charger.getX();

        double dir = Math.atan2(dy,dx);

        double drift = (Math.random() - 0.5) / 3;
        dir += drift;

        if (Charger.isVisible()){
            if (start) {

                Charger.setLocation((int) ((Charger.getX() + Math.cos(dir) * 4)), (int) (Charger.getY() + Math.sin(dir) * 4));
            }

        }
    }

    public void randomSpawn(){
        int x = (int) (Math.random() * 800);
        int y = (int) (Math.random() * 600);
        Charger.setBounds(x, y, 64, 64);
        ChargeTimer.start();
        Charger.setVisible(true);
    }
}
