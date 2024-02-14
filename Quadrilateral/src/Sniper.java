import javax.swing.*;
import java.awt.*;

public class Sniper {
    JLabel Sniper;
    JLabel Bullet;
    int Sx;
    int x;
    int Sy;
    int y;

    boolean isShooting = false;
    Timer ShooterTimer;

    public Sniper() {
        Sniper = new JLabel();
        Bullet = new JLabel();

        Sniper.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        Sniper.setVisible(false);
        Sniper.setBounds(0,0,32,32);

        Bullet.setBorder(BorderFactory.createLineBorder(Color.RED));
        Bullet.setVisible(false);
    }

    public void update() {
        Bullet.setBounds(Bullet.getX(), Bullet.getY()+15, 16,16);

        if (Bullet.getY() >=500) {
            Bullet.setVisible(false);
        }
    }

    public void shoot(Player player) {
        x = player.Player.getX();
        y = player.Player.getY();

        Sniper.setVisible(true);

        ShooterTimer = new Timer(3000, e -> {
            // Just shoot the bullet downwards
            Bullet.setVisible(true);
            Bullet.setBounds(Sniper.getX()+10, Sniper.getY()+16, 16, 16);
            Bullet.setBorder(BorderFactory.createLineBorder(Color.RED));
        });
        ShooterTimer.start();
    }
}
