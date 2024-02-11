import javax.swing.*;
import java.util.Random;
import java.awt.*;

public class CoinDrops {
    JLabel CoinDrops;
    Image CoinImage = new ImageIcon("D:\\Carl2\\coding\\Quadrilateral\\Quadrilateral\\src\\Images\\Smoke-Idle.gif").getImage().getScaledInstance(64,64,Image.SCALE_DEFAULT);
    ImageIcon CoinIcon = new ImageIcon(CoinImage);
    Random Rand;
    int x;
    int y;
    boolean isCollected = false;

    public CoinDrops() {
        CoinDrops = new JLabel();

        Rand = new Random();

        CoinDrops.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

        x = Rand.nextInt(735) + 32;
        y = Rand.nextInt(608) + 32;

        CoinDrops.setBounds(x,y,64,64);
    }
}
