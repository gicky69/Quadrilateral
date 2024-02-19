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

    // Colors

    boolean isPaused = false;

    List<JLabel> Walls = new ArrayList<>();
    List<JLabel> WODs = new ArrayList<>();

    Random rand;
    int lvl;

    // BOMB
    Bomb Bomb;
    int BombTimer;
    Timer BombRandomSpawn;
    boolean hasRun = false;
    boolean hasRun2 = false;
    boolean hasRun3 = false;
    //

    // 1 = Wall, 0 = None
    int Map1[] = {
            1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,5,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,
            6,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,
    };

    int WOD[] = {
            2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,3,3,3,3,
    };

    // Map 0 1 2 3 4
    Image MapRightImage = new ImageIcon("Quadrilateral/src/Images/MAP2.png").getImage().getScaledInstance(32,32,Image.SCALE_DEFAULT);
    Image MapFloorImage = new ImageIcon("Quadrilateral/src/Images/MAP0.png").getImage().getScaledInstance(32,32,Image.SCALE_DEFAULT);
    Image MapBottomImage = new ImageIcon("Quadrilateral/src/Images/MAP3.png").getImage().getScaledInstance(32,32,Image.SCALE_DEFAULT);
    Image MapCornerImage = new ImageIcon("Quadrilateral/src/Images/MAP4.png").getImage().getScaledInstance(32,32,Image.SCALE_DEFAULT);
    Image MapEndImage = new ImageIcon("Quadrilateral/src/Images/MAP5.png").getImage().getScaledInstance(32,32,Image.SCALE_DEFAULT);
    Image MapEndBottomImage = new ImageIcon("Quadrilateral/src/Images/MAP6.png").getImage().getScaledInstance(32,32,Image.SCALE_DEFAULT);
    ImageIcon MapRightIcon = new ImageIcon(MapRightImage);
    ImageIcon MapFloorIcon = new ImageIcon(MapFloorImage);
    ImageIcon MapBottomIcon = new ImageIcon(MapBottomImage);
    ImageIcon MapCornerIcon = new ImageIcon(MapCornerImage);
    ImageIcon MapEndIcon = new ImageIcon(MapEndImage);
    ImageIcon MapEndBottomIcon = new ImageIcon(MapEndBottomImage);

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
//        Enemy = new Enemy();
        MapPanel = new JPanel();

        CoinsPanel = new JPanel();
        CoinsLabel = new JLabel();
        HealthLabel = new JLabel();
        rand = new Random();

        GamePanel.setBounds(290,30,800,670);
        GamePanel.setBackground(new Color(80,88,109));
        GamePanel.setLayout(null);

//        CoinsLabel.setText("Coins : " + player.Coins);

        HealthLabel.setBounds(0,0,100,30);
//        HealthLabel.setText("Health : " + player.Health);
        CoinsPanel.add(HealthLabel);
        CoinsPanel.add(CoinsLabel);



        // Delay for Coin Spawn
        ShooterSpawnDelay = new Timer(5000, e ->{
           Beams.spawn();
        });

        generate();

        GamePanel.setFocusable(true);
        GamePanel.requestFocusInWindow();
        GamePanel.setVisible(true);
    }


    public void update(Main MF, Player Player) {
//        Enemy.update(Player);
    }
    public void generate() {
        for (int i=0;i<Map1.length;i++){
            if (Map1[i] == 0) {
                JLabel Wall = new JLabel();
                Wall.setIcon(MapFloorIcon);
                Wall.setBounds((i%25)*32,(i/25)*32,32,32);
                Wall.setVisible(true);
                GamePanel.add(Wall);
            }
            if (Map1[i] == 1) {
                JLabel Wall = new JLabel();
                Wall.setIcon(MapFloorIcon);
                Wall.setBounds((i%25)*32,(i/25)*32,32,32);
                Wall.setVisible(true);
                GamePanel.add(Wall);
                Walls.add(Wall);
            }
            if (Map1[i] == 2) {
                JLabel Wall = new JLabel();
                Wall.setIcon(MapRightIcon);
                Wall.setBounds((i%25)*32,(i/25)*32,32,32);
                Wall.setVisible(true);
                GamePanel.add(Wall);
                Walls.add(Wall);
            }
            if (Map1[i] == 3) {
                JLabel Wall = new JLabel();
                Wall.setIcon(MapBottomIcon);
                Wall.setBounds((i%25)*32,(i/25)*32,32,32);
                Wall.setVisible(true);
                GamePanel.add(Wall);
                Walls.add(Wall);
            }
            if (Map1[i] == 4) {
                JLabel Wall = new JLabel();
                Wall.setIcon(MapCornerIcon);
                Wall.setBounds((i%25)*32,(i/25)*32,32,32);
                Wall.setVisible(true);
                GamePanel.add(Wall);
                Walls.add(Wall);
            }

            if (Map1[i] == 5) {
                JLabel Wall = new JLabel();
                Wall.setIcon(MapEndIcon);
                Wall.setBounds((i%25)*32,(i/25)*32,32,32);
                Wall.setVisible(true);
                GamePanel.add(Wall);
                Walls.add(Wall);
            }
            if (Map1[i] == 6) {
                JLabel Wall = new JLabel();
                Wall.setIcon(MapEndBottomIcon);
                Wall.setBounds((i%25)*32,(i/25)*32,32,32);
                Wall.setVisible(true);
                GamePanel.add(Wall);
                Walls.add(Wall);
            }
        }
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
