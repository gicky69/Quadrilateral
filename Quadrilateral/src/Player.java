// Todo Proper Movement
// TODO Fix Shoot
// TODO Add more Enemies

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import javax.sound.sampled.*;

public class Player implements KeyListener {
    JLabel Player;
    JLabel PlayerHitbox;
    JLabel NewEnemy;
    Image PlayerImage = new ImageIcon("Quadrilateral/src/Images/Player-Idlet.gif").getImage().getScaledInstance(64,64,Image.SCALE_DEFAULT);
    Image PlayerImageIdle = new ImageIcon("Quadrilateral/src/Images/Player-Idlet.gif").getImage().getScaledInstance(64,64,Image.SCALE_DEFAULT);
    Image PlayerWalkingRight = new ImageIcon("Quadrilateral/src/Images/Player-MoveRight.gif").getImage().getScaledInstance(64,64,Image.SCALE_DEFAULT);
    Image PlayerWalkingLeft = new ImageIcon("Quadrilateral/src/Images/Player-MoveLeft.gif").getImage().getScaledInstance(64,64,Image.SCALE_DEFAULT);
    Image PlayerJumpingImage = new ImageIcon("Quadrilateral/src/Images/Player-Jump.gif").getImage().getScaledInstance(64,64,Image.SCALE_DEFAULT);
    ImageIcon PlayerWalkingRightIcon = new ImageIcon(PlayerWalkingRight);
    ImageIcon PlayerMovingLeftIcon = new ImageIcon(PlayerWalkingLeft);
    ImageIcon PlayerJumpinngIcon = new ImageIcon(PlayerJumpingImage);
    ImageIcon PlayerIcon = new ImageIcon(PlayerImage);
    ImageIcon PlayerIconIdle = new ImageIcon(PlayerImageIdle);

    // Sound
    int jsfx = 0;
    String jfx = "Quadrilateral/src/Sounds/Jump/JSFX1.wav";
    String jfx2 = "Quadrilateral/src/Sounds/Jump/JSFX2.wav";
    String jfx3 = "Quadrilateral/src/Sounds/Jump/JSFX3.wav";

    String kfx = "Quadrilateral/src/Sounds/keyclick.wav";
    static boolean kfxclick = false;
    //


    // Player Attributes
    int PosX = 1280/2;
    int PosY = 720/2;

    int oldPosX = PosX;
    int oldPosY = PosY;
    int DirX;
    int DirY;
    static int Coins = 0;
    static int Health = 100;
    boolean isDodge;
    boolean isSpacebarSpammed = false;
    boolean hasEnemySpawned = false;
    boolean isDead = false;
    boolean MovingLeft = false;
    boolean MovingRight = false;
    Timer DodgeTime;
    //
    Random random;

    Timer IdleTimer;
    public Player() {
        Player = new JLabel();
        PlayerHitbox = new JLabel();
        NewEnemy = new JLabel();
        NewEnemy.setVisible(false);

        Player.setHorizontalAlignment(JLabel.CENTER);
        Player.setVerticalAlignment(JLabel.CENTER);
        Player.setIcon(PlayerIcon);

        random = new Random();

        Player.setBounds(800/2,700/2,64,64);
        PlayerHitbox.setBounds(800/2,700/2,64,64);

        isDodge = false;

        Player.setLayout(null);
        Player.setVisible(true);

        DodgeTime = new Timer(550, e2 ->{
            isDodge = false;
            Player.setIcon(PlayerIcon);
        });
        DodgeTime.setRepeats(false);
//        PlayerHitbox.setBorder(BorderFactory.createLineBorder(Color.RED));

//        IdleTimer = new Timer(1000, e1 -> {
//            Player.setIcon(PlayerIconIdle);
//        });
    }


    public void update(Main MF) {
        if (Coins == 15 && !hasEnemySpawned) {
            NewEnemy.setVisible(true);
            if (NewEnemy.isVisible()) {
                System.out.println("+ enemies");
                NewEnemy.setText("+ sniper");
                NewEnemy.setBounds(Player.getX()+32, Player.getY()-15, 20, 10);

                Timer NewEnemyTimer = new Timer(2000, e -> {
                    NewEnemy.setVisible(false);
                    ((Timer)e.getSource()).stop();
                });
                NewEnemyTimer.start();
            }
            hasEnemySpawned = true; // Set the flag to true after the enemy has spawned
        }

        oldPosX = PosX;
        oldPosY = PosY;

        PosX = Player.getX();
        PosY = Player.getY();

        PosX += DirX;
        PosY += DirY;

        Player.setBounds(PosX, PosY, 64, 64);
        PlayerHitbox.setBounds(PosX+20, PosY+38, 24, 25);

        if (    PlayerHitbox.getBounds().intersects(MF.Bounds1.getBounds()) ||
                PlayerHitbox.getBounds().intersects(MF.Bounds2.getBounds()) ||
                PlayerHitbox.getBounds().intersects(MF.Bounds3.getBounds()) ||
                PlayerHitbox.getBounds().intersects(MF.Bounds4.getBounds()))
        {
            PosX = oldPosX;
            PosY = oldPosY;
            Player.setBounds(PosX, PosY, 64, 64);
            PlayerHitbox.setBounds(PosX+20, PosY+38, 24, 25);
        }

        if (MovingLeft && !isDodge) {
            Player.setIcon(PlayerMovingLeftIcon);
        }
        if (MovingRight && !isDodge) {
            Player.setIcon(PlayerWalkingRightIcon);
        }

        // Player Dies
        if (Health <= 0) {
            Health = 0;
            Player.setVisible(false);
            System.out.println("GAME OVER NIGGA");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_W) {
            DirY = -3;
            if (!kfxclick){
                PlayMusic(kfx);
                kfxclick = true;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            DirY = 3;
            if (!kfxclick){
                PlayMusic(kfx);
                kfxclick = true;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            DirX = -3;
            if (!kfxclick){
                PlayMusic(kfx);
                kfxclick = true;
            }
            if (!isDodge) { // Check if player is not dodging
                Player.setIcon(PlayerMovingLeftIcon);
                MovingLeft = true;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            DirX = 3;
            if (!kfxclick){
                PlayMusic(kfx);
                kfxclick = true;
            }
            if (!isDodge) { // Check if player is not dodging
                Player.setIcon(PlayerWalkingRightIcon);
                MovingRight = true;
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE && !isDodge && !isSpacebarSpammed) {
            isSpacebarSpammed = true;
            isDodge = true;

            if (isDodge) {
                Player.setIcon(PlayerJumpinngIcon);
                if (jsfx >= 3) { jsfx = 0; }
                if (jsfx == 0) {
                    PlayMusic(jfx);
                    jsfx++;
                }
                else if (jsfx == 1) {
                    PlayMusic(jfx2);
                    jsfx++;
                }
                else if (jsfx == 2) {
                    PlayMusic(jfx3);
                    jsfx++;
                }
            }


            Player.setBounds(Player.getX(), Player.getY(), 64, 64);

            DodgeTime.start();
        }

    }
    @Override
    public void keyReleased(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_W) {
            DirY = 0;
            kfxclick = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_S) {
            DirY = 0;
            kfxclick = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_A) {
            DirX = 0;
            Player.setIcon(PlayerIcon);
            MovingLeft = false;
            kfxclick = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_D) {
            DirX = 0;
            Player.setIcon(PlayerIcon);
            MovingRight = false;
            kfxclick = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            isSpacebarSpammed = false;
        }
    }

    public static void PlayMusic(String filepath) {
        // if key is pressed
        try {
            File musicFile = new File(filepath);

            if (musicFile.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-5.0f);
                clip.start();
            }
            else {
                System.out.println("File not found");
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
