import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Enemy {
    JLabel Enemy;
    JLabel ATKRange;
    Random random;

    int enemyX; // Changed to double
    int enemyY; // Changed to double
    int speed = 1; // Speed of the enemy
    boolean isAttacking; // New variable to track if the enemy is attacking
    boolean hasAttacked;

    public Enemy() {
        Enemy = new JLabel();
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
        hasAttacked = false;
    }

    public void update(Player player) {
        int playerX = player.Player.getX();
        int playerY = player.Player.getY();

        enemyX = Enemy.getX();
        enemyY = Enemy.getY();

        // Only update the enemy's position if it's not attacking
        if (!isAttacking) {
            if (playerX > enemyX) {
                enemyX += speed;
            }
            else if (playerX < enemyX) {
                enemyX -= speed;
            }

            if (playerY > enemyY) {
                enemyY += speed;
            }
            else if (playerY < enemyY) {
                enemyY -= speed;
            }

            Enemy.setBounds(enemyX,enemyY, 32, 32);
        }

        int distance = (int) Math.sqrt(Math.pow(playerX - enemyX, 2) + Math.pow(playerY - enemyY, 2));
        if (distance <= 70 && !hasAttacked) {
            attack(player);
        }
    }

    public void attack(Player player) {
        // Start Attack
        isAttacking = true;
        hasAttacked = true;

        if (isAttacking) {
            // Calculate direction from enemy to player
            int direction = (int) ((Math.toDegrees(Math.atan2(player.Player.getY() - Enemy.getY(), player.Player.getX() - Enemy.getX())) + 360 + 90) % 360);

            // Enemy Jump towards the Direction
            

            // Update the enemy's position
            Enemy.setBounds(enemyX, enemyY, 32, 32);

            // Stop attacking after a delay
            Timer AttackCooldown = new Timer(1000, e -> {
                isAttacking = false;
                resetAttack();
            });
            AttackCooldown.setRepeats(false);
            AttackCooldown.start();
        }
    }
    public void resetAttack() {
        hasAttacked = false;
    }
}