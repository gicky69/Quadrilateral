import javax.swing.*;
import java.awt.*;

public class Bullet {
    JLabel bullet;
    int x, y;
    int dx, dy;
    int speed = 5;

    public Bullet(int x, int y, int dx, int dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;

        bullet = new JLabel();
        bullet.setBounds(x, y, 5, 5);
        bullet.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
    }

    public void update() {
        x += dx * speed;
        y += dy * speed;
        bullet.setBounds(x, y, 5, 5);
    }
}