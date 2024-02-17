import javax.swing.*;
import java.awt.*;

public class Sniper {
    JLabel Sniper;
    JLabel Bullet;
    Image SniperImage = new ImageIcon("Quadrilateral/src/Images/Skell-atk.gif").getImage().getScaledInstance(64,64,Image.SCALE_DEFAULT);
    Image BulletImage = new ImageIcon("Quadrilateral/src/Images/Bone.gif").getImage().getScaledInstance(64,64,Image.SCALE_DEFAULT);
    ImageIcon BulletIcon = new ImageIcon(BulletImage);
    ImageIcon SniperIcon = new ImageIcon(SniperImage);
    int Sx;
    int x;
    int Sy;
    int y;

    boolean isShooting = false;
    Timer ShooterTimer;

    public Sniper() {
        Sniper = new JLabel();
        Bullet = new JLabel();
        Sniper.setIcon(SniperIcon);
        Bullet.setIcon(BulletIcon);

//        Sniper.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        Sniper.setVisible(false);
        Sniper.setBounds(0,0,64,64);

//        Bullet.setBorder(BorderFactory.createLineBorder(Color.RED));
        Bullet.setVisible(false);
    }

    public void update() {
        Bullet.setBounds(Bullet.getX(), Bullet.getY()+13, 64,64);

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
            Bullet.setBounds(Sniper.getX()+10, Sniper.getY()+16, 64, 64);
            Bullet.setBorder(BorderFactory.createLineBorder(Color.RED));
        });
        ShooterTimer.start();
    }
}
