// Todo Proper Movement
// TODO Fix Shoot
// TODO Add more Enemies

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class Player implements KeyListener {
    JLabel Player;

    // Player Attributes
    int PosX = 1280/2;
    int PosY = 720/2;
    int DirX;
    int DirY;
    static int Coins = 0;
    static int Health = 100;
    boolean isAttacking;
    boolean isDodge;
    boolean isSpacebarDown = false;
    boolean isSpacebarSpammed = true;
    boolean vulnerability = false;
    int EnemysKilled = 0;
    Timer DodgeTime;
    //

    // Enemies
    Enemy Enemy;
    int EPosX;
    int EPosY;
    GamePanel gamePanel;
    //

    // Attacks
    Melee EMelee;
    Melee Melee;
    //
    Random random;
    public Player(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        Player = new JLabel();

        Melee = new Melee();
        random = new Random();

        Player.setBounds(800/2,700/2,32,32);
        Player.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        isAttacking = false;
        isDodge = false;

        Player.setLayout(null);
        Player.setVisible(true);
    }

    public void update(GamePanel GamePanel) {

        int oldPosX = PosX;
        int oldPosY = PosY;

        PosX = Player.getX();
        PosY = Player.getY();

        PosX += DirX;
        PosY += DirY;

        Player.setBounds(PosX, PosY, 32, 32);

        // Player Collides with Walls
        for (int i = 0; i < GamePanel.Walls.size(); i++) {
            if (GamePanel.Walls.get(i).getBounds().intersects(Player.getBounds())) {
                PosX = oldPosX;
                PosY = oldPosY;
            }
        }

        Player.setBounds(PosX, PosY, 32, 32);

        // Player Dies
        if (Health <= 0) {
            Health = 0;
            Player.setVisible(false);
            System.out.println("GAME OVER NIGGA");
        }
    }

    // Todo Fix Shoot

    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (gamePanel.isPaused) {
            return;
        }

        if (e.getKeyCode() == KeyEvent.VK_W) {
            DirY = -3;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            DirY = 3;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            DirX = -3;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            DirX = 3;
        }

        // Melee Attack
        if (e.getKeyCode() == KeyEvent.VK_J && !isAttacking){
            if (isDodge){
                return;
            }

            isAttacking = true;
            Melee.Melee.setVisible(true);
            if (DirX == 3){
                Melee.Melee.setBounds(PosX+15,PosY-7,20,25);
            }
            if (DirX == -3){
                Melee.Melee.setBounds(PosX-25,PosY-7,20,25);
            }
            if (DirY == 3){
                Melee.Melee.setBounds(PosX-7,PosY+15,25,20);
            }
            if (DirY == -3){
                Melee.Melee.setBounds(PosX-7,PosY-25,25,20);
            }

            // Error Checking
            if (DirX == 0 && DirY == 0){
                Melee.Melee.setBounds(PosX+15,PosY-7,20,25);
            }

            Timer timer = new Timer(100, KeyEvent -> {
                Melee.Melee.setBounds(1600,1600,20,25);
                Melee.Melee.setVisible(false);
                isAttacking = false;
            });
            timer.setRepeats(false);
            timer.start();
        }
        // Dash
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !isDodge && !isSpacebarDown && isSpacebarSpammed){
            isSpacebarDown = true;
            isSpacebarSpammed = false;
            isDodge = true;

            DodgeTime = new Timer(500, e2 ->{
                isDodge = false;
            });

            DodgeTime.setRepeats(false);
            DodgeTime.start();

            int oldDirX = DirX;
            int oldDirY = DirY;

            // Direction of Dash
            if (oldDirX > 0){
                DirX = 6;
            }
            if (oldDirX < 0){
                DirX = -6;
            }
            if (oldDirY < 0){
                DirY = -6;
            }
            if (oldDirY > 0){
                DirY = 6;
            }
            if (oldDirX == 0 && oldDirY == 0) {
                DirX = 6;
            }

            Timer timer = new Timer(250, e1 -> {
                DirX = oldDirX;
                DirY = oldDirY;
            });
            timer.setRepeats(false);
            timer.start();
        }

    }
    @Override
    public void keyReleased(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_W) {
            DirY = 0;
        }
        if(e.getKeyCode() == KeyEvent.VK_S) {
            DirY = 0;
        }
        if(e.getKeyCode() == KeyEvent.VK_A) {
            DirX = 0;
        }
        if(e.getKeyCode() == KeyEvent.VK_D) {
            DirX = 0;
        }

        if (e.getKeyCode() == KeyEvent.VK_J){
            isAttacking = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            isSpacebarDown = false;
            isSpacebarSpammed = true;
        }
    }
}
