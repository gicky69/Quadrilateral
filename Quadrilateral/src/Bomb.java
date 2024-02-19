import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Bomb {
    JLabel Bomb;
    Image BombImage = new ImageIcon("Quadrilateral/src/Images/Bomb.gif").getImage().getScaledInstance(64,64,Image.SCALE_DEFAULT);
    ImageIcon BombIcon = new ImageIcon(BombImage);
    JLabel BombExplosion;
    Random Rand;
    int x;
    int y;
    boolean hasExploded = false;
    boolean start = false;
    int BombDuration = 2750;
    Timer BombRandomSpawn;
    Timer ExplodeTimer;

    public Bomb() {
        Bomb = new JLabel();
        BombExplosion = new JLabel();
        BombExplosion.setBorder(BorderFactory.createLineBorder(Color.RED));
        BombExplosion.setVisible(false);
        Bomb.setIcon(BombIcon);
        Rand = new Random();
        Bomb.setVisible(true);

        Timer delay = new Timer(1500, e2 -> {
            BombExplosion.setVisible(false);
            hasExploded = false;
            ((Timer)e2.getSource()).stop();
        });


        ExplodeTimer = new Timer(2250, e -> {
            BombExplosion.setVisible(true);
            hasExploded = true;
            delay.start();
            Timer bdelay = new Timer(500, e2 -> {
                Bomb.setVisible(false);
                ((Timer)e2.getSource()).stop();
            });
            bdelay.start();

            ((Timer)e.getSource()).stop();
        });
        if (!hasExploded) {
            BombRandomSpawn = new Timer(BombDuration, e -> {
                randomSpawn();
                ((Timer)e.getSource()).stop();
            });

            System.out.println("Bomb Exploded");
            BombRandomSpawn.start();
            ExplodeTimer.start();
        }
    }

    public void update() {
        if (hasExploded) {
            BombExplosion.setVisible(true);
        }

        if (start) {
            BombRandomSpawn.start();
        }
    }


    public void randomSpawn() {
        if (!hasExploded){
            BombExplosion.setVisible(false);
            Bomb.setVisible(true);
            BombRandomSpawn.start();
            ExplodeTimer.start();

            System.out.println("Bomb Spawned");

            x = Rand.nextInt(735) + 290;
            y = Rand.nextInt(608) + 30;
            Bomb.setBounds(x,y,64,64);
            BombExplosion.setBounds(Bomb.getX()-64, Bomb.getY()-48, Bomb.getWidth()+128, Bomb.getHeight()+128);
        }
    }
}
