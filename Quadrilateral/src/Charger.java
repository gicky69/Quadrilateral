import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Random;

public class Charger {
    Image ChargerImg1 = new ImageIcon("Quadrilateral/src/Images/Charger/charger1-idlle.gif").getImage().getScaledInstance(48,48,Image.SCALE_DEFAULT);
    Image ChargerImg2 = new ImageIcon("Quadrilateral/src/Images/Charger/charger2-idle.gif").getImage().getScaledInstance(48,48,Image.SCALE_DEFAULT);
    Image ChargerImg3 = new ImageIcon("Quadrilateral/src/Images/Charger/charger3-idle.gif").getImage().getScaledInstance(48,48,Image.SCALE_DEFAULT);
    Image ChargerImg4 = new ImageIcon("Quadrilateral/src/Images/Charger/charger4-idle.gif").getImage().getScaledInstance(48,48,Image.SCALE_DEFAULT);
    Image ChargerImg1C = new ImageIcon("Quadrilateral/src/Images/Charger/charger1-charge.gif").getImage().getScaledInstance(48,48,Image.SCALE_DEFAULT);
    Image ChargerImg2C = new ImageIcon("Quadrilateral/src/Images/Charger/charger2-charge.gif").getImage().getScaledInstance(48,48,Image.SCALE_DEFAULT);
    Image ChargerImg3C = new ImageIcon("Quadrilateral/src/Images/Charger/charger3-charge.gif").getImage().getScaledInstance(48,48,Image.SCALE_DEFAULT);
    Image ChargerImg4C = new ImageIcon("Quadrilateral/src/Images/Charger/charger4-charge.gif").getImage().getScaledInstance(48,48,Image.SCALE_DEFAULT);
    Image ChargerImg1F = new ImageIcon("Quadrilateral/src/Images/Charger/charger1-fly.gif").getImage().getScaledInstance(48,48,Image.SCALE_DEFAULT);
    Image ChargerImg2F = new ImageIcon("Quadrilateral/src/Images/Charger/charger2-fly.gif").getImage().getScaledInstance(48,48,Image.SCALE_DEFAULT);
    Image ChargerImg3F = new ImageIcon("Quadrilateral/src/Images/Charger/charger3-fly.gif").getImage().getScaledInstance(48,48,Image.SCALE_DEFAULT);
    Image ChargerImg4F = new ImageIcon("Quadrilateral/src/Images/Charger/charger4-fly.gif").getImage().getScaledInstance(48,48,Image.SCALE_DEFAULT);


    ImageIcon ChargerIcon1 = new ImageIcon(ChargerImg1);
    ImageIcon ChargerIcon2 = new ImageIcon(ChargerImg2);
    ImageIcon ChargerIcon3 = new ImageIcon(ChargerImg3);
    ImageIcon ChargerIcon4 = new ImageIcon(ChargerImg4);

    ImageIcon ChargerIcon1C = new ImageIcon(ChargerImg1C);
    ImageIcon ChargerIcon2C = new ImageIcon(ChargerImg2C);
    ImageIcon ChargerIcon3C = new ImageIcon(ChargerImg3C);
    ImageIcon ChargerIcon4C = new ImageIcon(ChargerImg4C);

    ImageIcon ChargerIcon1F = new ImageIcon(ChargerImg1F);
    ImageIcon ChargerIcon2F = new ImageIcon(ChargerImg2F);
    ImageIcon ChargerIcon3F = new ImageIcon(ChargerImg3F);
    ImageIcon ChargerIcon4F = new ImageIcon(ChargerImg4F);

    JLabel Charger;
    JLabel ChargerHitBox;
    Timer ChargerTimeSpawn;
    Timer ChargeTimer;
    Timer ChargerDelay;
    boolean start = false;
    int velocity = 10;
    int dy = 0, dx = 0;
    int px, py;
    int pos;
    Random rand;

    // Sfx
    String csfx = "Quadrilateral/src/Sounds/Game/charge.wav";

    public Charger() {
        Charger = new JLabel();
        ChargerHitBox = new JLabel();
        rand = new Random();

        ChargerHitBox.setBounds(0,0,48,48);
        Charger.setBounds(0,0,48,48);
//        Charger.setBorder(BorderFactory.createLineBorder(Color.RED));
        Charger.setVisible(false);


        // Spawn Timer
        ChargerTimeSpawn = new Timer(1500, e -> {
            if (start) {
                randomSpawn();
                Charger.setVisible(true);
                ((Timer)e.getSource()).stop();
            }
        });

        // Charge Start Timer
        ChargeTimer = new Timer(1000, e -> {
           if (Charger.isVisible()){
               PlayMusic(csfx);
               ChargerDelay.start();
           }
            ((Timer)e.getSource()).stop();
        });

        // Charge Delay Timer
        ChargerDelay = new Timer(4000, e -> {
            Charger.setVisible(false);
            ChargerTimeSpawn.start();
            ((Timer)e.getSource()).stop();
        });
    }

    public void update(Player player) {
        if (!ChargeTimer.isRunning()){
            if (start) {
                switch (pos) {
                    case 1:
                        Charger.setLocation(Charger.getX() + velocity, Charger.getY() + velocity);
                        ChargerHitBox.setBounds(Charger.getX()+13, Charger.getY()+14, 32, 32);
                        Charger.setIcon(ChargerIcon1F);
                        break;
                    case 2:
                        Charger.setLocation(Charger.getX() + velocity, Charger.getY() - velocity);
                        ChargerHitBox.setBounds(Charger.getX()+13, Charger.getY()+8, 32, 28);
                        Charger.setIcon(ChargerIcon3F);
                        break;
                    case 3:
                        Charger.setLocation(Charger.getX() - velocity, Charger.getY() + velocity);
                        ChargerHitBox.setBounds(Charger.getX()+2, Charger.getY()+14, 32, 32);
                        Charger.setIcon(ChargerIcon2F);
                        break;
                    case 4:
                        Charger.setLocation(Charger.getX() - velocity, Charger.getY() - velocity);
                        ChargerHitBox.setBounds(Charger.getX()+2, Charger.getY()+8, 32, 28);
                        Charger.setIcon(ChargerIcon4F);
                        break;
                }

                ChargerDelay.start();
            }
        }
    }



    public void randomSpawn() {
        if (start) {
            pos = rand.nextInt(4) + 1;
            Charger.setVisible(true);
            System.out.println("Charger Spawned at " + pos);
            switch (pos) {
                case 1:
                    Charger.setBounds(242,120,48,48);
                    Charger.setIcon(ChargerIcon1);
                    break;
                case 2:
                    Charger.setBounds(242,750,48,48);
                    Charger.setIcon(ChargerIcon3);
                    break;
                case 3:
                    Charger.setBounds(1050,120,48,48);
                    Charger.setIcon(ChargerIcon2);
                    break;
                case 4:
                    Charger.setBounds(1050,750,48,48);
                    Charger.setIcon(ChargerIcon4);
                    break;
            }
            ChargeTimer.start();
        }
    }

    public void reset() {
        Charger.setVisible(false);
        start = false;
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
