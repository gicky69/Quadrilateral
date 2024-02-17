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
    int gravity = 2;
    int prevPosY;
    static int Coins = 0;
    static int Health = 100;
    boolean isAttacking;
    boolean isDodge;
    boolean isSpacebarSpammed = false;
    boolean vulnerability = false;
    boolean MovingLeft = false;
    boolean MovingRight = false;
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
    public Player() {
        Player = new JLabel();
        PlayerHitbox = new JLabel();
        Player.setHorizontalAlignment(JLabel.CENTER);
        Player.setVerticalAlignment(JLabel.CENTER);
        Player.setIcon(PlayerIcon);

        Melee = new Melee();
        random = new Random();

        Player.setBounds(800/2,700/2,64,64);
        PlayerHitbox.setBounds(800/2,700/2,64,64);

        isAttacking = false;
        isDodge = false;

        Player.setLayout(null);
        Player.setVisible(true);

        DodgeTime = new Timer(650, e2 ->{
            isDodge = false;
            Player.setIcon(PlayerIcon);
        });
        DodgeTime.setRepeats(false);
        PlayerHitbox.setBorder(BorderFactory.createLineBorder(Color.RED));

//        IdleTimer = new Timer(1000, e1 -> {
//            Player.setIcon(PlayerIconIdle);
//        });
    }


    public void update(Main MF) {

        oldPosX = PosX;
        oldPosY = PosY;

        PosX = Player.getX();
        PosY = Player.getY();

        PosX += DirX;
        PosY += DirY;

        Player.setBounds(PosX, PosY, 64, 64);
        PlayerHitbox.setBounds(PosX+16, PosY+32, 32, 32);

        if (MF.Bounds1.getBounds().intersects(PlayerHitbox.getBounds())) {
            PosX = oldPosX;
            PosY = oldPosY;
            Player.setBounds(PosX, PosY, 64, 64);
            PlayerHitbox.setBounds(PosX+16, PosY+32, 32, 32);
        }

        if (MF.Bounds2.getBounds().intersects(PlayerHitbox.getBounds())) {
            PosX = oldPosX;
            PosY = oldPosY;
            Player.setBounds(PosX, PosY, 64, 64);
            PlayerHitbox.setBounds(PosX+16, PosY+32, 32, 32);
        }

        if (MF.Bounds3.getBounds().intersects(PlayerHitbox.getBounds())) {
            PosX = oldPosX;
            PosY = oldPosY;
            Player.setBounds(PosX, PosY, 64, 64);
            PlayerHitbox.setBounds(PosX+16, PosY+32, 32, 32);
        }

        if (MF.Bounds4.getBounds().intersects(PlayerHitbox.getBounds())) {
            PosX = oldPosX;
            PosY = oldPosY;
            Player.setBounds(PosX, PosY, 64, 64);
            PlayerHitbox.setBounds(PosX+16, PosY+32, 32, 32);
        }

//        if (!isDodge) {
//            Player.setBounds(PosX, PosY, 64, 64);
//            PlayerHitbox.setBounds(PosX, PosY, 32, 32);
//        }
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

        if (e.getKeyCode() == KeyEvent.VK_J){
            isAttacking = false;
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
                gainControl.setValue(-10.0f);
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
