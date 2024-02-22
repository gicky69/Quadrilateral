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
    Image PlayerDeathImage = new ImageIcon("Quadrilateral/src/Images/Death/death.gif").getImage().getScaledInstance(64,64,Image.SCALE_DEFAULT);
    Image PlayerDeathAppearImage = new ImageIcon("Quadrilateral/src/Images/Death/death-appear.gif").getImage().getScaledInstance(64,64,Image.SCALE_DEFAULT);
    Image PlayerDeathIdleImage = new ImageIcon("Quadrilateral/src/Images/Death/death-idle.gif").getImage().getScaledInstance(64,64,Image.SCALE_DEFAULT);
    ImageIcon PlayerWalkingRightIcon = new ImageIcon(PlayerWalkingRight);
    ImageIcon PlayerMovingLeftIcon = new ImageIcon(PlayerWalkingLeft);
    ImageIcon PlayerJumpinngIcon = new ImageIcon(PlayerJumpingImage);
    ImageIcon PlayerIcon = new ImageIcon(PlayerImage);
    ImageIcon PlayerDeathIcon = new ImageIcon(PlayerDeathImage);
    ImageIcon PlayerDeathAppearIcon = new ImageIcon(PlayerDeathAppearImage);
    ImageIcon deathIcon = new ImageIcon(PlayerDeathIdleImage);
    ImageIcon PlayerIconIdle = new ImageIcon(PlayerImageIdle);

    // Sound
    int jsfx = 0;
    String jfx = "Quadrilateral/src/Sounds/Jump/JSFX1.wav";
    String jfx2 = "Quadrilateral/src/Sounds/Jump/JSFX2.wav";
    String jfx3 = "Quadrilateral/src/Sounds/Jump/JSFX3.wav";

    String kfx = "Quadrilateral/src/Sounds/keyclick.wav";
    String dfx = "Quadrilateral/src/Sounds/Game/deathwav.wav";
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

    //
    int speed = 1;
    long lastTime = System.nanoTime();
    double delta = 0.0;
    double ns = 1000000000.0 / 60.0;
    //
    boolean isDodge;
    boolean isSpacebarSpammed = false;
    boolean isDead = false;
    boolean MovingLeft = false;
    boolean MovingUp = false;
    boolean MovingDown = false;
    boolean MovingRight = false;
    boolean Jump = false;
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

        Player.setBounds(650,631,64,64);
        PlayerHitbox.setBounds(800/2,700/2,64,64);

        isDodge = false;

        Player.setLayout(null);
        Player.setVisible(true);

        DodgeTime = new Timer(520, e2 ->{
            isDodge = false;
            Player.setIcon(PlayerIcon);
        });
        DodgeTime.setRepeats(false);
//        PlayerHitbox.setBorder(BorderFactory.createLineBorder(Color.RED));

    }


    public void update(Main MF) {
        if (isDead){
            return;
        }

        if (MovingUp) {
            DirY = -3;
        }
        if (MovingDown) {
            DirY = 3;
        }
        if (MovingLeft) {
            DirX = -3;
        }
        if (MovingRight) {
            DirX = 3;
        }

        long now = System.nanoTime();
        delta += (now - lastTime) / ns;
        lastTime = now;

        if (!isDead){

            oldPosX = PosX;
            oldPosY = PosY;

            if (delta >= 1) {
                PosX += DirX * speed;
                PosY += DirY * speed;
                delta--;
            }

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
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (!isDead){
            if (e.getKeyCode() == KeyEvent.VK_W) {
                MovingUp = true;
                if (!kfxclick){
                    PlayMusic(kfx);
                    kfxclick = true;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                MovingDown = true;
                if (!kfxclick){
                    PlayMusic(kfx);
                    kfxclick = true;
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_A) {
                MovingLeft = true;
                if (!kfxclick){
                    PlayMusic(kfx);
                    kfxclick = true;
                }
                if (!isDodge) { // Check if player is not dodging
                    Player.setIcon(PlayerMovingLeftIcon);
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                MovingRight = true;
                if (!kfxclick){
                    PlayMusic(kfx);
                    kfxclick = true;
                }
                if (!isDodge) {
                    Player.setIcon(PlayerWalkingRightIcon);
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_SPACE && !isDodge && !isSpacebarSpammed) {
                isSpacebarSpammed = true;
                isDodge = true;
                Jump = true;

                if (Jump){
                    Player.setIcon(PlayerJumpinngIcon);
                }

                if (isDodge) {
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
    }
    @Override
    public void keyReleased(KeyEvent e){
        if (!isDead){
            if(e.getKeyCode() == KeyEvent.VK_W) {
                DirY = 0;
                kfxclick = false;
                MovingUp = false;
            }
            if(e.getKeyCode() == KeyEvent.VK_S) {
                DirY = 0;
                kfxclick = false;
                MovingDown = false;
            }
            if(e.getKeyCode() == KeyEvent.VK_A) {
                if (!isDodge){
                    Player.setIcon(PlayerIcon);
                }
                DirX = 0;
                MovingLeft = false;
                kfxclick = false;
            }
            if(e.getKeyCode() == KeyEvent.VK_D) {
                if (!isDodge) {
                    Player.setIcon(PlayerIcon);
                }
                DirX = 0;
                MovingRight = false;
                kfxclick = false;
            }

            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                isSpacebarSpammed = false;
                Jump = false;
            }
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
                if (filepath.equals("Quadrilateral/src/Sounds/deathwav.wav")) {
                    gainControl.setValue(0.0f);
                }
                else {
                    gainControl.setValue(0.0f);
                }
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

     public void reset() {
        System.out.println("Player Reset");

        Player.setVisible(true);
        Player.setIcon(PlayerIcon);
        PosX = 650;
        PosY = 300;
        Player.setBounds(650,300,64,64);
        PlayerHitbox.setBounds(800/2,700/2,64,64);

        DirX = 0;
        DirY = 0;

        isDead = false;
        isDodge = false;

        Coins = 0;
    }
}
