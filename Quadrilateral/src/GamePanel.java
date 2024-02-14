import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GamePanel implements KeyListener {
    JPanel GamePanel;

    // Colors
    Color BGColor = new Color(45, 50, 80);

    boolean isPaused = false;
    Player Player;

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
    Image WODImage = new ImageIcon("Quadrilateral/src/Images/WOD.png").getImage().getScaledInstance(32,32,Image.SCALE_DEFAULT);
    Image WODTPImage = new ImageIcon("Quadrilateral/src/Images/WODTP.png").getImage().getScaledInstance(32,32,Image.SCALE_DEFAULT);
    ImageIcon WODIcon = new ImageIcon(WODImage);
    ImageIcon WODTPIcon = new ImageIcon(WODTPImage);
    Image WODHEADImage = new ImageIcon("Quadrilateral/src/Images/MAPHEAD1.gif").getImage().getScaledInstance(32,32,Image.SCALE_DEFAULT);
    Image WODHeadImageBtm = new ImageIcon("Quadrilateral/src/Images/MAPHEAD2.gif").getImage().getScaledInstance(32,32,Image.SCALE_DEFAULT);
    ImageIcon WODHEADIcon = new ImageIcon(WODHEADImage);
    ImageIcon WODHeadIconBtm = new ImageIcon(WODHeadImageBtm);

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

    // Coins
    Image CoinAppearImage = new ImageIcon("Quadrilateral/src/Images/Smoke-Pop.gif").getImage().getScaledInstance(64,64,Image.SCALE_DEFAULT);
    Image CoinIdleImage = new ImageIcon("Quadrilateral/src/Images/Smoke-Idle.gif").getImage().getScaledInstance(64,64,Image.SCALE_DEFAULT);
    ImageIcon CoinAppearIcon = new ImageIcon(CoinAppearImage);
    ImageIcon CoinIdleIcon = new ImageIcon(CoinIdleImage);
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
    Beams Beams;
    Sniper Sniper;
    Timer ShooterSpawnDelay;


    public GamePanel() {
        GamePanel = new JPanel();
        CoinDrops = new CoinDrops();
        Player = new Player(this);
//        Enemy = new Enemy();
        MapPanel = new JPanel();
        Sniper = new Sniper();

        CoinsPanel = new JPanel();
        CoinsLabel = new JLabel();
        HealthLabel = new JLabel();
        Bomb = new Bomb();
        Beams = new Beams();

        rand = new Random();

        GamePanel.setBounds(290,30,800,670);
        GamePanel.setLayout(null);

        GamePanel.add(Player.Player);
        GamePanel.setComponentZOrder(Player.Player, 0);
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
        CoinDrops.CoinDrops.setIcon(CoinIdleIcon);

        BombRandomSpawn = new Timer(300, e -> {
            BombTimer -= 1;
            System.out.println(BombTimer);
            GamePanel.setComponentZOrder(Bomb.Bomb, 1);

            if (BombTimer <= 0){
                speed = rand.nextInt(6) + 2;
                Bomb.randomSpawn();
                BombTimer = 10;
            }
        });

        Timer CoinIdleDelay = new Timer(1350, e -> {
            CoinDrops.CoinDrops.setIcon(CoinIdleIcon);
            ((Timer)e.getSource()).stop();
        });

        // Delay for Coin Spawn
        CoinsDelay = new Timer(300, e -> {
            TimerCoins -= 1;
            System.out.println(TimerCoins);

            if (TimerCoins <= 0){
                CoinDrops.isCollected = false;
                GamePanel.add(CoinDrops.CoinDrops);
                CoinDrops.CoinDrops.setIcon(CoinAppearIcon);
                int cx = rand.nextInt(635) + 32;
                int cy = rand.nextInt(408) + 32;
                CoinIdleDelay.start();

                CoinDrops.CoinDrops.setBounds(cx, cy, 64, 64);
                CoinDrops.CoinDrops.setVisible(true);

                GamePanel.setComponentZOrder(CoinDrops.CoinDrops, 1);
                GamePanel.revalidate();
                GamePanel.repaint();
                CoinsDelay.stop();
            }
        });
        ShooterSpawnDelay = new Timer(5000, e ->{
           Beams.spawn();
        });

        Bomb.randomSpawn();
        generate();

        lvl = rand.nextInt(4)+1;
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
                Wall.setIcon(WODIcon);
                Wall.setVisible(true);
                GamePanel.add(Wall);
                WODs.add(Wall);
            }
            if (WOD[i] == 2) {
                JLabel Wall = new JLabel();
                Wall.setIcon(WODHEADIcon);
                Wall.setVisible(true);
                GamePanel.add(Wall);
                WODs.add(Wall);
            }
            if (WOD[i] == 3) {
                JLabel Wall = new JLabel();
                Wall.setIcon(WODHeadIconBtm);
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
            GamePanel.setComponentZOrder(WODs.get(i), 0);
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
                    wod.setIcon(WODTPIcon);
                    wod.setLocation(wod.getX(), wod.getY() + speed);
                    break;
                case 3:
                    wod.setLocation(wod.getX() - speed, wod.getY());
                    break;
                case 4:
                    wod.setIcon(WODTPIcon);
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
                // randomize lvl
                lvl = rand.nextInt(4)+1;
                int time = rand.nextInt(4001) + 50;
                Timer createWod = new Timer(time, e -> {
                    createWOD(lvl);
                    ((Timer)e.getSource()).stop();
                });
                createWod.start();
                break;
            }
        }
        GamePanel.revalidate();
        GamePanel.repaint();
    }
    public void update(Main MF) {
        Player.update(this);
//        Enemy.update(Player);
        Beams.update(this);
        Sniper.update();
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

            TimerCoins = 10;

            Player.Coins += 100;

            CoinsDelay.start();
        }
        // Bomb Explored Anywhere, Starts at 25 secs
        if (MF.seconds != 0 && MF.seconds % 20 == 0) {
            if (!hasRun) {
                if (!BombRandomSpawn.isRunning()) {
                    GamePanel.add(Bomb.Bomb);
                    Bomb.randomSpawn();
                    BombRandomSpawn.start();
                }
                hasRun = true;
            }
        } else {
            hasRun = false;
        }
        if (MF.seconds != 0 && MF.seconds % 35 == 0) {
            if (!hasRun2) {
                GamePanel.add(Beams.Shooter);
                GamePanel.add(Beams.Beam);
                GamePanel.add(Beams.ShootingArea);
                GamePanel.setComponentZOrder(Beams.Shooter, 1);
                GamePanel.setComponentZOrder(Beams.Beam, 1);
                GamePanel.setComponentZOrder(Beams.ShootingArea, 1);
                Beams.Shooter.setVisible(true);
                hasRun2 = true;

                if (!Beams.spawn){
                    Beams.spawn = true;
                    ShooterSpawnDelay.start();
                }

                if (!Beams.isShooting) {
                    Beams.ShooterTimer.start();
                }
            }
        } else {
            hasRun2 = false;
        }
        if (MF.seconds != 0 && MF.seconds % 15 == 0) {
            if (!hasRun3) {
                GamePanel.add(Sniper.Sniper);
                GamePanel.add(Sniper.Bullet);
                GamePanel.setComponentZOrder(Sniper.Sniper, 0);
                GamePanel.setComponentZOrder(Sniper.Bullet, 1);
                Sniper.Sniper.setVisible(true);

                hasRun3 = true;

                if (!Sniper.isShooting) {
                    Sniper.shoot(Player);
                }
            }
        }
        Sniper.Sniper.setLocation(Player.Player.getX(), Sniper.Sniper.getY());
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
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            GamePanel.setComponentZOrder(Player.Player, 0);
        }
    }
    @Override
    public void keyTyped(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            GamePanel.setComponentZOrder(Player.Player, 1);
        }
    }
}
