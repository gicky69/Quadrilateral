import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Random;

public class Bomb2 {
    JLabel Bomb;
    Image BombImage = new ImageIcon("Quadrilateral/src/Images/Bomb.gif").getImage().getScaledInstance(64,64,Image.SCALE_DEFAULT);
    Image ExplosionImage = new ImageIcon("Quadrilateral/src/Images/Explosion.gif").getImage().getScaledInstance(192,192,Image.SCALE_DEFAULT);
    ImageIcon ExplosionIcon = new ImageIcon(ExplosionImage);
    ImageIcon BombIcon = new ImageIcon(BombImage);

    String bsfx1 = "Quadrilateral/src/Sounds/Game/bomb1.wav";

    JLabel BombExplosion;
    JLabel BombHitbox;
    Random Rand;
    int x;
    int y;
    boolean hasExploded = false;
    boolean start = false;
    int BombDuration = 2890;
    Timer BombRandomSpawn;
    Timer ExplodeTimer;

    public Bomb2() {
        Bomb = new JLabel();
        BombExplosion = new JLabel();
        BombHitbox = new JLabel();

        BombExplosion.setVisible(false);
        Bomb.setIcon(BombIcon);
        Rand = new Random();
        Bomb.setVisible(true);

        Timer delay = new Timer(1200, e2 -> {
            BombExplosion.setVisible(false);
            hasExploded = false;
            ((Timer)e2.getSource()).stop();
        });

        if (Bomb.isVisible()){
            ExplodeTimer = new Timer(2680, e -> {
                BombExplosion.setIcon(ExplosionIcon);
                BombExplosion.setVisible(true);
                hasExploded = true;
                PlayMusic(bsfx1);
                delay.start();
                Timer bdelay = new Timer(500, e2 -> {
                    Bomb.setVisible(false);
                    ((Timer)e2.getSource()).stop();
                });
                bdelay.start();

                ((Timer)e.getSource()).stop();
            });
        }

        if (!hasExploded) {
            BombRandomSpawn = new Timer(BombDuration, e -> {
                randomSpawn();
                ((Timer)e.getSource()).stop();
            });
        }
    }

    public void update() {
        BombHitbox.setBounds(Bomb.getX()-55, Bomb.getY()-50, Bomb.getWidth()+100, Bomb.getHeight()+95);
        if (hasExploded) {
            BombExplosion.setVisible(true);
        }

        if (start) {
            BombRandomSpawn.start();
        }
    }


    public void randomSpawn() {
        if (!hasExploded){
            BombExplosion.setVisible(false);
            Bomb.setVisible(true);
            BombRandomSpawn.start();
            ExplodeTimer.start();

            System.out.println("Bomb Spawned");

            x = Rand.nextInt(540)+260;
            y = Rand.nextInt(550)+120;
            Bomb.setBounds(x,y,64,64);
            BombExplosion.setBounds(Bomb.getX()-64, Bomb.getY()-64, Bomb.getWidth()+128, Bomb.getHeight()+128);
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
                gainControl.setValue(0.0f);
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
        Bomb.setBounds(0,0,0,0);
        BombExplosion.setBounds(0,0,0,0);
        BombHitbox.setBounds(0,0,0,0);

        ExplodeTimer.stop();
        start = false;
    }
}
