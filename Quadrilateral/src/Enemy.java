import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Enemy {
    JLabel Enemy;
    JLabel Melee;
    ImageIcon EnemyIcon = new ImageIcon("D:\\Carl2\\coding\\Quadrilateral\\Quadrilateral\\Images\\Slime.gif");
    JLabel ATKRange;
    Random random;

    int enemyX; // Changed to double
    int enemyY; // Changed to double
    int speed = 1; // Speed of the enemy
    int dirATKX = 0;
    int dirATKY = 0;
    int dashdirX = 0;
    int dashdirY = 0;
    boolean isAttacking; // New variable to track if the enemy is attacking
    boolean hasAttacked;

    public Enemy() {
        Enemy = new JLabel();
        Melee = new JLabel();
        Melee.setBorder(BorderFactory.createLineBorder(Color.RED));
        Melee.setBackground(Color.RED);
        Melee.setVisible(false);

        Enemy.setHorizontalAlignment(JLabel.CENTER);
        Enemy.setVerticalAlignment(JLabel.CENTER);
        Enemy.setIcon(EnemyIcon);

        ATKRange = new JLabel();
        random = new Random();

        Enemy.setBorder(BorderFactory.createLineBorder(Color.RED));
        Enemy.setBackground(Color.RED);

        int EX = random.nextInt(500);
        int EY = random.nextInt(500);

        Enemy.setBounds(EX, EY, 32, 32);
        Enemy.setLayout(null);
        Enemy.setVisible(true);

        isAttacking = false;
    }

    public void update(Player player) {
        int playerX = player.Player.getX();
        int playerY = player.Player.getY();

        enemyX = Enemy.getX();
        enemyY = Enemy.getY();

        // Only update the enemy's position if it's not attacking
        if (!isAttacking) {
            if (playerX != enemyX) {
                if (playerX >= enemyX) {
                    enemyX += speed;
                }
                else if (playerX <= enemyX) {
                    enemyX -= speed;
                }
            }

            if (playerY != enemyY) {
                if (playerY >= enemyY) {
                    enemyY += speed;
                }
                else if (playerY <= enemyY) {
                    enemyY -= speed;
                }
            }

            Enemy.setBounds(enemyX,enemyY, 32, 32);
        }

        int distance = (int) Math.sqrt(Math.pow(playerX - enemyX, 2) + Math.pow(playerY - enemyY, 2));
        if (distance <= 70 && !hasAttacked) {
            attack(player);
        }
    }

    public void attack(Player player) {
        isAttacking = true;
        hasAttacked = true;

        int playerX = player.Player.getX();
        int playerY = player.Player.getY();

        int eatkX = Enemy.getX();
        int eatkY = Enemy.getY();

        dirATKY = 0;
        dirATKX = 0;

        // Attack the player
        // Direction
        if (playerX > enemyX) {
            dashdirX += 7;
            dirATKX += 32;
        }
        else if (playerX < enemyX) {
            dashdirX -= 7;
            dirATKX -= 32;
        }

        if (playerY > enemyY) {
            dashdirY += 7;
            dirATKY += 32;
        }
        else if (playerY < enemyY) {
            dashdirY -= 7;
            dirATKY -= 32;
        }

        // Directional && Enemy Dash Towards the Player
        Timer ATKDelay = new Timer(450, e -> {
            Melee.setBounds(enemyX+dirATKX, enemyY+dirATKY, 32, 32);
        });
        ATKDelay.setRepeats(false);
        ATKDelay.start();

        Melee.setVisible(true);

        // Reset Timer
        Timer attackTimer = new Timer(1000, e -> {
            isAttacking = false;
            hasAttacked = false;
            Melee.setVisible(false);
            Melee.setBounds(-100,0,0,0);
        });
        attackTimer.setRepeats(false);
        attackTimer.start();
    }
}