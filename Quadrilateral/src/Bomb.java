import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Bomb {
    JLabel Bomb;
    Random Rand;
    int x;
    int y;
    boolean hasExploded = false;

    public Bomb() {
        Bomb = new JLabel();
        Rand = new Random();

        Bomb.setBorder(BorderFactory.createLineBorder(Color.ORANGE));

        x = Rand.nextInt(500) + 100;
        y = Rand.nextInt(500) + 100;

        Bomb.setBounds(x,y,50,50);
        Bomb.setVisible(false);
    }

    public void randomSpawn() {
        Bomb.setVisible(true);
        x = Rand.nextInt(500) + 100;
        y = Rand.nextInt(500) + 100;
        Bomb.setBounds(x,y,100,100);
    }
}
