import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Random;

public class Sniper {
    JLabel Sniper;
    JLabel Bullet;
    Image SniperImage = new ImageIcon("Quadrilateral/src/Images/Shooter.gif").getImage().getScaledInstance(64,64,Image.SCALE_DEFAULT);
    Image BulletImage = new ImageIcon("Quadrilateral/src/Images/Bone.gif").getImage().getScaledInstance(64,64,Image.SCALE_DEFAULT);
    ImageIcon BulletIcon = new ImageIcon(BulletImage);
    ImageIcon SniperIcon = new ImageIcon(SniperImage);
    int Sx;
    int Sy;
    int dx;
    int dy;
    double dir;

    boolean isShooting = false;
    boolean start = false;
    boolean shoots = false;
    Random rand;
    Timer ShooterTimer;
    Timer SpawnDelayTimer;
    Timer BulletDelay;

    // Sounds
    String sfx1 = "Quadrilateral/src/Sounds/Game/shoot1.wav";


    public Sniper(Player Player) {
        Sniper = new JLabel();
        Bullet = new JLabel();
        rand = new Random();

        Sniper.setIcon(SniperIcon);

//        Sniper.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        Sniper.setVisible(false);
        Sniper.setBounds(0,0,64,64);

//        Bullet.setBorder(BorderFactory.createLineBorder(Color.RED));
        BulletDelay = new Timer(1000, e -> {
            if (Bullet.isVisible()) {
                Bullet.setVisible(false);
                ((Timer)e.getSource()).stop();
            }
        });

        ShooterTimer = new Timer(1000, e -> {
            if (Sniper.isVisible() && start){
                shoots = true;
                PlayMusic(sfx1);
                Bullet.setBounds(Sniper.getX()+16, Sniper.getY()+16, 64, 64);
                Bullet.setIcon(BulletIcon);
                Bullet.setVisible(true);
                BulletDelay.start();
                ((Timer)e.getSource()).stop();
            }
        });

        SpawnDelayTimer = new Timer(3000, e -> {
            if (start) {
                spawn();
                ShooterTimer.start();
            }
        });
    }

    public void update(Player player) {
        if (Sniper.isVisible()){
            if (shoots) {
                dx = player.oldPosX - Sniper.getX();
                dy = player.oldPosY - Sniper.getY();

                dir = Math.atan2(dy,dx);

                if (player.Coins == 25){
                    Bullet.setLocation(Bullet.getX() + (int)(Math.cos(dir) * 17), Bullet.getY() + (int)(Math.sin(dir) * 17));
                }
                else {
                    Bullet.setLocation(Bullet.getX() + (int)(Math.cos(dir) * 13), Bullet.getY() + (int)(Math.sin(dir) * 13));
                }

            }
        }

        if (start == true){
            SpawnDelayTimer.start();
        }
    }

    public void spawn() {
        SpawnDelayTimer.start();
        Sx = rand.nextInt(540)+260;
        Sy = rand.nextInt(550)+120;

        Sniper.setBounds(Sx,Sy,64,64);
        Sniper.setVisible(true);
    }

    public void reset() {
        Sniper.setVisible(false);
        Bullet.setVisible(false);
        start = false;
        shoots = false;
    }

    public static void PlayMusic(String filepath) {
        try {
            File musicFile = new File(filepath);

            if (musicFile.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);

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
