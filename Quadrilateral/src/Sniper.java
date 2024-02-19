import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Sniper {
    JLabel Sniper;
    JLabel Bullet;
    Image SniperImage = new ImageIcon("Quadrilateral/src/Images/Skell-atk.gif").getImage().getScaledInstance(64,64,Image.SCALE_DEFAULT);
    Image BulletImage = new ImageIcon("Quadrilateral/src/Images/Bone.gif").getImage().getScaledInstance(64,64,Image.SCALE_DEFAULT);
    ImageIcon BulletIcon = new ImageIcon(BulletImage);
    ImageIcon SniperIcon = new ImageIcon(SniperImage);
    int Sx;
    int Sy;
    int dx;
    int dy;
    double dir;

    boolean isShooting = false;
    boolean start = false;
    boolean shoots = false;
    Random rand;
    Timer ShooterTimer;
    Timer SpawnDelayTimer;


    public Sniper(Player Player) {
        Sniper = new JLabel();
        Bullet = new JLabel();
        rand = new Random();

        Sniper.setIcon(SniperIcon);

//        Sniper.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        Sniper.setVisible(false);
        Sniper.setBounds(0,0,64,64);

//        Bullet.setBorder(BorderFactory.createLineBorder(Color.RED));

        ShooterTimer = new Timer(1000, e -> {
            if (Sniper.isVisible()){
                shoots = true;
                Bullet.setBounds(Sniper.getX(), Sniper.getY(), 64, 64);
                Bullet.setIcon(BulletIcon);
                Bullet.setVisible(true);
                ((Timer)e.getSource()).stop();
            }
        });
        ShooterTimer.start();

        SpawnDelayTimer = new Timer(3000, e -> {
            start = true;
            spawn();
        });
    }

    public void update(Player player) {
        if (Sniper.isVisible()){
            if (shoots) {
                dx = player.oldPosX - Sniper.getX();
                dy = player.oldPosY - Sniper.getY();

                dir = Math.atan2(dy,dx);

                Bullet.setLocation(Bullet.getX() + (int)(Math.cos(dir) * 13), Bullet.getY() + (int)(Math.sin(dir) * 13));
            }
        }

        if (start == true){
            Sniper.setVisible(true);
        }
    }

    public void spawn() {
        SpawnDelayTimer.start();
        Sx = rand.nextInt(735) + 290;
        Sy = rand.nextInt(608) + 30;

        Sniper.setBounds(Sx,Sy,64,64);
        Sniper.setVisible(true);
        ShooterTimer.start();
    }
}
