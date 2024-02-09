import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GamePanel implements KeyListener {
    JPanel GamePanel;
    boolean isPaused = false;
    Player Player;

    List<JLabel> Walls = new ArrayList<>();
    List<JLabel> WODs = new ArrayList<>();

    Random rand;
    int lvl = 1;

    // BOMB
    Bomb Bomb;
    int BombTimer;
    Timer BombRandomSpawn;
    boolean hasRun = false;
    //

    // 1 = Wall, 0 = None
    int Map1[] = {
            1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
    };

    int WOD[] = {
            1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
    };

    JPanel CoinsPanel;
    JLabel CoinsLabel;
    JLabel HealthLabel;

    JPanel MapPanel;

    // Coins
    CoinDrops CoinDrops;
    int TimerCoins;
    Timer CoinsDelay;

    // Multiplier
    int speed = 2;

    // Enemy
    Enemy Enemy;


    public GamePanel() {
        GamePanel = new JPanel();
        CoinDrops = new CoinDrops();
        Player = new Player(this);
        Enemy = new Enemy();
        MapPanel = new JPanel();
        CoinsPanel = new JPanel();
        CoinsLabel = new JLabel();
        HealthLabel = new JLabel();
        Bomb = new Bomb();

        rand = new Random();

        GamePanel.setBounds(290,10,800,700);
        GamePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        GamePanel.setBackground(Color.BLACK);
        GamePanel.setLayout(null);

        GamePanel.add(Enemy.Enemy);
        GamePanel.add(Player.Player);
        GamePanel.add(Player.Melee.Melee);
        GamePanel.addKeyListener(this);
        GamePanel.addKeyListener(Player);

        // CoinsPanel
        CoinsPanel.setBounds(0,0,100,50);
        CoinsPanel.setVisible(true);

        CoinsLabel.setBounds(0,30,100,30);
        CoinsLabel.setText("Coins : " + Player.Coins);

        HealthLabel.setBounds(0,0,100,30);
        HealthLabel.setText("Health : " + Player.Health);
        CoinsPanel.add(HealthLabel);
        CoinsPanel.add(CoinsLabel);
        GamePanel.add(CoinDrops.CoinDrops);

        // Bomb
        GamePanel.add(Bomb.Bomb);
        BombRandomSpawn = new Timer(1000, e -> {
            BombTimer -= 1;
            System.out.println(BombTimer);

            if (BombTimer <= 0){
                Bomb.randomSpawn();
                BombTimer = 10;
            }
        });

        // Delay for Coin Spawn
        CoinsDelay = new Timer(300, e -> {
            TimerCoins -= 1;
            System.out.println(TimerCoins);

            if (TimerCoins <= 0){
                CoinDrops.isCollected = false;
                int cx = 100 + rand.nextInt(500);
                int cy = 100 + rand.nextInt(500);

                CoinDrops.CoinDrops.setBounds(cx, cy, 32, 32);
                CoinDrops.CoinDrops.setVisible(true);
                GamePanel.add(CoinDrops.CoinDrops);
                CoinsDelay.stop();
            }
        });


        Bomb.randomSpawn();
        generate();
        createWOD(lvl);
//        generateWOD();
        GamePanel.setFocusable(true);
        GamePanel.requestFocusInWindow();
        GamePanel.setVisible(true);
    }

    public void createWOD(int pos) {
        for (int i=0;i<25;i++){
            if (WOD[i] == 1) {
                JLabel Wall = new JLabel();
                Wall.setBorder(BorderFactory.createLineBorder(Color.RED));
                Wall.setBackground(Color.RED);
                Wall.setVisible(true);
                GamePanel.add(Wall);
                WODs.add(Wall);
            }

            switch(pos) {
                case 1:
                    WODs.get(i).setBounds(0, i*32, 32, 32);
                    break;
                case 2:
                    WODs.get(i).setBounds(i*32, 0, 32, 32);
                    break;
                case 3:
                    WODs.get(i).setBounds(800, i*32, 32, 32);
                    break;
                case 4:
                    WODs.get(i).setBounds(i*32, 700, 32, 32);
                    break;
            }
        }

        GamePanel.revalidate();
        GamePanel.repaint();
    }

    public void moveWOD() {
        for (int i=WODs.size()-1;i>=0;i--){
            JLabel wod = WODs.get(i);

            switch(lvl) {
                case 1:
                    wod.setLocation(wod.getX() + speed, wod.getY());
                    break;
                case 2:
                    wod.setLocation(wod.getX(), wod.getY() + speed);
                    break;
                case 3:
                    wod.setLocation(wod.getX() - speed, wod.getY());
                    break;
                case 4:
                    wod.setLocation(wod.getX(), wod.getY() - speed);
                    break;
            }

            // Check if the WOD has reached the end of the panel
            if      ((lvl == 1 && wod.getX() > 850) ||
                    (lvl == 2 && wod.getY() > 750) ||
                    (lvl == 3 && wod.getX() < -50) ||
                    (lvl == 4 && wod.getY() < -50)) {
                wod.setLocation(-500, 0);
                GamePanel.remove(wod);
                wod.setVisible(false);
                WODs.clear();
                lvl = (lvl % 4) + 1;
                createWOD(lvl);
                break;
            }
        }
        GamePanel.revalidate();
        GamePanel.repaint();
    }
    public void update(Main MF) {
        Player.update(this);
        Enemy.update(Player);
        moveWOD();


//        for (int i=0;i<WODs.size();i++){
//            if (WODs.get(i).getBounds().intersects(Player.Player.getBounds())){
//                if (!Player.isDodge && !Player.vulnerability) {
//                    Player.Health -= 10;
//                    Player.vulnerability = true;
//
//                    Timer ResetVulnerability = new Timer (1500, e -> {
//                        Player.vulnerability = false;
//                        ((Timer)e.getSource()).stop();
//                    });
//
//                    ResetVulnerability.start();
//                }
//            }
//        }

        if (Player.Player.getBounds().intersects(CoinDrops.CoinDrops.getBounds()) && !CoinDrops.isCollected) {
            GamePanel.remove(CoinDrops.CoinDrops);
            CoinDrops.CoinDrops.setVisible(false);
            CoinDrops.isCollected = true;

            int cx = 100 + rand.nextInt(500);

            int cy = 100 + rand.nextInt(500);
            TimerCoins = 10;


            Player.Coins += 100;

            CoinsDelay.start();
        }

        // Bomb Explored Anywhere

        if (MF.seconds / 30 == 1) {
            if (!hasRun) {
                speed += 1;
                if (!BombRandomSpawn.isRunning()) {
                    Bomb.randomSpawn();
                    BombRandomSpawn.start();
                }
                hasRun = true;
            }
        } else {
            hasRun = false;
        }
    }
    public void generate() {
        for (int i=0;i<Map1.length;i++){
            if (Map1[i] == 1) {
                JLabel Wall = new JLabel();
                Wall.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                Wall.setBackground(Color.WHITE);
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
