import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.util.Random;
import java.awt.*;

public class CoinDrops {
    JPanel CoinPanel;
    JLabel CoinDrops;
    JLabel CoinHitBox;
    Image CoinImage = new ImageIcon("Quadrilateral/src/Images/Coins-Idle.gif").getImage().getScaledInstance(48,48,Image.SCALE_DEFAULT);
    Image CoinAppearImage = new ImageIcon("Quadrilateral/src/Images/Coins-Appear.gif").getImage().getScaledInstance(48,48,Image.SCALE_DEFAULT);
    Image CoinIdleImage = new ImageIcon("Quadrilateral/src/Images/Coins-Idle.gif").getImage().getScaledInstance(48,48,Image.SCALE_DEFAULT);
    ImageIcon CoinAppearIcon = new ImageIcon(CoinAppearImage);
    ImageIcon CoinIdleIcon = new ImageIcon(CoinIdleImage);
    ImageIcon CoinIcon = new ImageIcon(CoinImage);

    int CoinGifDuration = 510;

    // SFX
    String csfx1 = "Quadrilateral/src/Sounds/clink10.wav";
    String csfx2 = "Quadrilateral/src/Sounds/clink11.wav";
    String csfx3 = "Quadrilateral/src/Sounds/clink12.wav";
    String ccsfx1 = "Quadrilateral/src/Sounds/CSFX3.wav";
    int csfx = 0;
    //
    Random rand;
    int x;
    int y;
    boolean isCollected = false;
    Timer CoinIdleDelay;
    Timer CoinVulnerability;

    public CoinDrops(Main MF) {
        CoinPanel = new JPanel();
        CoinDrops = new JLabel();
        CoinHitBox = new JLabel();

        rand = new Random();

        x = rand.nextInt(735) + 290;
        y = rand.nextInt(608) + 30;

        CoinDrops.setBounds(x,y,64,64);
        CoinHitBox.setBounds(x+15,y+23,18,20);
//        CoinHitBox.setBorder(BorderFactory.createLineBorder(Color.RED));
        CoinDrops.setIcon(CoinAppearIcon);

        CoinIdleDelay = new Timer(CoinGifDuration, e -> {
            CoinDrops.setIcon(CoinIdleIcon);
            ((Timer)e.getSource()).stop();
        });

        CoinIdleDelay.start();

        CoinVulnerability = new Timer(150, e -> {
            isCollected = false;
            ((Timer)e.getSource()).stop();
        });

        CoinDrops.setVisible(true);
    }

    public void spawn(){
        CoinVulnerability.start();
        CoinDrops.setIcon(CoinAppearIcon);
        int cx = rand.nextInt(635) + 290;
        int cy = rand.nextInt(408) + 30;
        CoinIdleDelay.start();

        CoinDrops.setBounds(cx, cy, 64, 64);
        CoinHitBox.setBounds(cx+10,cy+17,28,28);

        CoinDrops.setVisible(true);

        if (CoinDrops.isVisible()){
            csfx++;
            if (csfx >= 3){
                csfx = 0;
            }

            if (csfx == 0){
                PlayMusic(csfx1);
            }
            else if (csfx == 1){
                PlayMusic(csfx2);
            }
            else if (csfx == 2){
                PlayMusic(csfx3);
            }
        }
    }

    public void collected(Main MF) {
        if (CoinDrops.isVisible() && !isCollected){
            CoinDrops.setIcon(CoinIdleIcon);
            isCollected = true;

            if (isCollected){
                CoinDrops.setVisible(false);
                spawn();
                PlayMusic(ccsfx1);
            }
        }
    }

    public static void PlayMusic(String filepath) {
        try {
            File musicFile = new File(filepath);

            if (musicFile.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-15.0f);
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
        spawn();
    }
}
