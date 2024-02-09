import javax.swing.*;
import java.util.Random;
import java.awt.*;

public class CoinDrops {
    JLabel CoinDrops;
    Random Rand;
    int x;
    int y;
    boolean isCollected = false;

    public CoinDrops() {
        CoinDrops = new JLabel();
        Rand = new Random();

        CoinDrops.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

        x = Rand.nextInt(500) + 100;
        y = Rand.nextInt(500) + 100;

        CoinDrops.setBounds(x,y,32,32);
    }
}
