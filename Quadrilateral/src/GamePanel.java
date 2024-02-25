import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GamePanel implements KeyListener {
    JPanel GamePanel;
    JLabel BGImage = new JLabel();
    Image BG = new ImageIcon("Quadrilateral/src/Images/MAP.png").getImage().getScaledInstance(800,670,Image.SCALE_DEFAULT);

    boolean isPaused = false;
    JPanel CoinsPanel;
    JLabel CoinsLabel;
    JLabel HealthLabel;

    int csfx = 0;
    //

    JPanel MapPanel;

    // Coins
    int TimerCoins;
    Timer CoinsDelay;
    //

    // Enemy
    Beams Beams;
    Timer ShooterSpawnDelay;


    public GamePanel() {
        GamePanel = new JPanel();
        BGImage = new JLabel();
        MapPanel = new JPanel();

        BGImage.setIcon(new ImageIcon(BG));
        BGImage.setBounds(0,0,800,670);

        GamePanel.setBounds(280,140,800,670);
        GamePanel.setBackground(new Color(80,88,109));
        GamePanel.setOpaque(false);
        GamePanel.setLayout(null);

        GamePanel.add(BGImage);

        // Delay for Coin Spawn
        ShooterSpawnDelay = new Timer(5000, e ->{
           Beams.spawn();
        });

        GamePanel.setFocusable(true);
        GamePanel.requestFocusInWindow();
        GamePanel.setVisible(true);
    }

    @Override
    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
            isPaused = !isPaused;
        }
    }
    @Override
    public void keyReleased(KeyEvent e){

    }
    @Override
    public void keyTyped(KeyEvent e){

    }
}
