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
    int BombDuration = 2720;
    Timer BombRandomSpawn;

    public Bomb() {
        Bomb = new JLabel();
        Bomb.setIcon(BombIcon);
        Rand = new Random();
        Bomb.setVisible(true);

        BombRandomSpawn = new Timer(BombDuration, e -> {
            randomSpawn();
            ((Timer)e.getSource()).stop();
        });
    }


    public void randomSpawn() {
        BombRandomSpawn.start();
        Bomb.setVisible(true);
        x = Rand.nextInt(735) + 290;
        y = Rand.nextInt(608) + 30;
        Bomb.setBounds(x,y,64,64);
    }
}
