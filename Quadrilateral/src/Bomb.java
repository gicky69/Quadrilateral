import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Bomb {
    JLabel Bomb;
    Image BombImage = new ImageIcon("Quadrilateral/src/Images/pixil-gif-drawing-bomb.gif").getImage().getScaledInstance(64,64,Image.SCALE_DEFAULT);
    ImageIcon BombIcon = new ImageIcon(BombImage);
    JLabel BombExplosion;
    Random Rand;
    int x;
    int y;
    boolean hasExploded = false;

    public Bomb() {
        Bomb = new JLabel();
        Bomb.setIcon(BombIcon);
        Rand = new Random();

        Bomb.setBorder(BorderFactory.createLineBorder(Color.ORANGE));

        x = Rand.nextInt(500) + 100;
        y = Rand.nextInt(500) + 100;

        Bomb.setBounds(x,y,64,64);
        Bomb.setVisible(false);
    }

    public void randomSpawn() {
        Bomb.setVisible(true);
        x = Rand.nextInt(500) + 100;
        y = Rand.nextInt(500) + 100;
        Bomb.setBounds(x,y,64,64);
    }

    public void explode() {
        BombExplosion = new JLabel();
        BombExplosion.setBorder(BorderFactory.createLineBorder(Color.RED));
        BombExplosion.setBounds(x,y,100,100);
        BombExplosion.setVisible(true);
        hasExploded = true;
    }
}
