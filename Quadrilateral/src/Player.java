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

    Image PlayerImage = new ImageIcon("Quadrilateral/src/Images/Slime.png").getImage().getScaledInstance(64,64,Image.SCALE_DEFAULT);
    Image PlayerImageIdle = new ImageIcon("Quadrilateral/src/Images/Player-Idle.gif").getImage().getScaledInstance(64,64,Image.SCALE_DEFAULT);
    Image PlayerWalkingRight = new ImageIcon("Quadrilateral/src/Images/Slime-MoveRight.gif").getImage().getScaledInstance(64,64,Image.SCALE_DEFAULT);
    Image PlayerWalkingLeft = new ImageIcon("Quadrilateral/src/Images/Slime-MoveLeft.gif").getImage().getScaledInstance(64,64,Image.SCALE_DEFAULT);
    Image PlayerDashing = new ImageIcon("Quadrilateral/src/Images/Player-dash.gif").getImage().getScaledInstance(64,64,Image.SCALE_DEFAULT);
    Image PlayerJumpingImage = new ImageIcon("Quadrilateral/src/Images/Slime-Jump.gif").getImage().getScaledInstance(64,64,Image.SCALE_DEFAULT);
    ImageIcon PlayerWalkingRightIcon = new ImageIcon(PlayerWalkingRight);
    ImageIcon PlayerMovingLeftIcon = new ImageIcon(PlayerWalkingLeft);
    ImageIcon PlayerDashingIcon = new ImageIcon(PlayerDashing);
    ImageIcon PlayerJumpinngIcon = new ImageIcon(PlayerJumpingImage);
    ImageIcon PlayerIcon = new ImageIcon(PlayerImage);
    ImageIcon PlayerIconIdle = new ImageIcon(PlayerImageIdle);


    // Player Attributes
    int PosX = 1280/2;
    int PosY = 720/2;
    int JumpY = 6;
    int DirX;
    int DirY;
    int gravity = 2;
    int prevPosY;
    static int Coins = 0;
    static int Health = 100;
    boolean isAttacking;
    boolean isDodge;
    boolean isSpacebarSpammed = true;
    boolean vulnerability = false;
    int EnemysKilled = 0;
    Timer DodgeTime;

    boolean isFalling = false;
    //
    GamePanel gamePanel;

    // Attacks
    Melee Melee;
    //
    Random random;

    Timer IdleTimer;
    public Player(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        Player = new JLabel();
        Player.setHorizontalAlignment(JLabel.CENTER);
        Player.setVerticalAlignment(JLabel.CENTER);
        Player.setIcon(PlayerIcon);

        Melee = new Melee();
        random = new Random();

        Player.setBounds(800/2,700/2,64,64);
        Player.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        isAttacking = false;
        isDodge = false;

        Player.setLayout(null);
        Player.setVisible(true);

//        IdleTimer = new Timer(1000, e1 -> {
//            Player.setIcon(PlayerIconIdle);
//        });
    }


    public void update(GamePanel GamePanel) {

        int oldPosX = PosX;
        int oldPosY = PosY;
        prevPosY = PosY;

        PosX = Player.getX();
        PosY = Player.getY();

        PosX += DirX;
        PosY += DirY;

        if (isFalling) {
            PosY += gravity;  // Apply gravity only if the player is falling
        }

        Player.setBounds(PosX, PosY, 64, 64);


        // Player Collides with Walls
        for (int i = 0; i < GamePanel.Walls.size(); i++) {
            if (GamePanel.Walls.get(i).getBounds().intersects(Player.getBounds())) {
                PosX = oldPosX;
                PosY = oldPosY;
            }
        }

        if (!isDodge) {
            Player.setBounds(PosX, PosY, 64, 64);
        }

        // Player Dies
        if (Health <= 0) {
            Health = 0;
            Player.setVisible(false);
            System.out.println("GAME OVER NIGGA");
        }

//        IdleTimer.start();
    }

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
            Player.setIcon(PlayerMovingLeftIcon);
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            DirX = 3;
            Player.setIcon(PlayerWalkingRightIcon);
        }

        // Melee Attack
//        if (e.getKeyCode() == KeyEvent.VK_J && !isAttacking){
//            if (isDodge){
//                return;
//            }
//
//            isAttacking = true;
//            Melee.Melee.setVisible(true);
//            if (DirX == 3){
//                Melee.Melee.setBounds(PosX+15,PosY-7,20,25);
//            }
//            if (DirX == -3){
//                Melee.Melee.setBounds(PosX-25,PosY-7,20,25);
//            }
//            if (DirY == 3){
//                Melee.Melee.setBounds(PosX-7,PosY+15,25,20);
//            }
//            if (DirY == -3){
//                Melee.Melee.setBounds(PosX-7,PosY-25,25,20);
//            }
//
//            // Error Checking
//            if (DirX == 0 && DirY == 0){
//                Melee.Melee.setBounds(PosX+15,PosY-7,20,25);
//            }
//
//            Timer timer = new Timer(100, KeyEvent -> {
//                Melee.Melee.setBounds(1600,1600,20,25);
//                Melee.Melee.setVisible(false);
//                isAttacking = false;
//            });
//            timer.setRepeats(false);
//            timer.start();
//        }
//         Dash
//        if (e.getKeyCode() == KeyEvent.VK_SPACE && !isDodge && isSpacebarSpammed){
//            isSpacebarSpammed = false;
//            isDodge = true;
//
//            DodgeTime = new Timer(500, e2 ->{
//                isDodge = false;
//            });
//
//            DodgeTime.setRepeats(false);
//            DodgeTime.start();
//
//            int oldDirX = DirX;
//            int oldDirY = DirY;
//
//            // Direction of Dash
//            if (oldDirX > 0){
//                DirX = 6;
//            }
//            if (oldDirX < 0){
//                DirX = -6;
//            }
//            if (oldDirY < 0){
//                DirY = -6;
//            }
//            if (oldDirY > 0){
//                DirY = 6;
//            }
//            if (oldDirX == 0 && oldDirY == 0) {
//                DirX = 6;
//            }
//
//            Player.setIcon(PlayerDashingIcon);
//            Timer timer = new Timer(250, e1 -> {
//                DirX = oldDirX;
//                DirY = oldDirY;
//            });
//            timer.setRepeats(false);
//            timer.start();
//        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            isSpacebarSpammed = true;
            isDodge = true;
            Player.setIcon(PlayerJumpinngIcon);
            Player.setBounds(Player.getX(), Player.getY(), 64, 64);

            DodgeTime = new Timer(700, e2 ->{

                isDodge = false;
                Player.setIcon(PlayerIcon);
                Player.setBounds(Player.getX(), Player.getY(), 64, 64);
            });
            DodgeTime.setRepeats(false);
            DodgeTime.start();
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
            Player.setIcon(PlayerIcon);
        }
        if(e.getKeyCode() == KeyEvent.VK_D) {
            DirX = 0;
            Player.setIcon(PlayerIcon);
        }

        if (e.getKeyCode() == KeyEvent.VK_J){
            isAttacking = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            isSpacebarSpammed = true;
        }
    }
}
