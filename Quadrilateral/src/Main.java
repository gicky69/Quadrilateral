import javax.swing.*;
import java.awt.*;

public class Main implements Runnable {

    // Number Images
    ImageIcon[] numberIcons = {
            new ImageIcon(new ImageIcon("Quadrilateral/src/Images/Numbers/0.png").getImage().getScaledInstance(24, 32, Image.SCALE_DEFAULT)),
            new ImageIcon(new ImageIcon("Quadrilateral/src/Images/Numbers/1.png").getImage().getScaledInstance(24, 32, Image.SCALE_DEFAULT)),
            new ImageIcon(new ImageIcon("Quadrilateral/src/Images/Numbers/2.png").getImage().getScaledInstance(24, 32, Image.SCALE_DEFAULT)),
            new ImageIcon(new ImageIcon("Quadrilateral/src/Images/Numbers/3.png").getImage().getScaledInstance(24, 32, Image.SCALE_DEFAULT)),
            new ImageIcon(new ImageIcon("Quadrilateral/src/Images/Numbers/4.png").getImage().getScaledInstance(24, 32, Image.SCALE_DEFAULT)),
            new ImageIcon(new ImageIcon("Quadrilateral/src/Images/Numbers/5.png").getImage().getScaledInstance(24, 32, Image.SCALE_DEFAULT)),
            new ImageIcon(new ImageIcon("Quadrilateral/src/Images/Numbers/6.png").getImage().getScaledInstance(24, 32, Image.SCALE_DEFAULT)),
            new ImageIcon(new ImageIcon("Quadrilateral/src/Images/Numbers/7.png").getImage().getScaledInstance(24, 32, Image.SCALE_DEFAULT)),
            new ImageIcon(new ImageIcon("Quadrilateral/src/Images/Numbers/8.png").getImage().getScaledInstance(24, 32, Image.SCALE_DEFAULT)),
            new ImageIcon(new ImageIcon("Quadrilateral/src/Images/Numbers/9.png").getImage().getScaledInstance(24, 32, Image.SCALE_DEFAULT))
    };

    ImageIcon icon = new ImageIcon("Quadrilateral/src/Images/FunTalon.png");

    JLabel[] digitLabels;
    JFrame Frame;
    JLabel CoinCount;
    JLabel Bounds1;
    JLabel Bounds2;
    JLabel Bounds3;
    JLabel Bounds4;

    // Main Menu
    MainMenu MainMenu;
    EndPanel EndPanel;

    // Game
    GamePanel GamePanel;
    WOD WOD;
    CoinDrops CoinDrops;
    Bomb Bomb;
    Bomb2 Bomb2;
    Nuke Nuke;
    Beams Beam;
    Sniper Sniper;
    Sniper2 Sniper2;
    Player Player;
    Charger Charger;
    Thread GameThread;
    //

    JPanel PauseMenu;
    JLabel PauseL;
    Timer IGTimer;
    int seconds = 0;
    JPanel IGTimerP;
    JLabel IGTimerL;

    public Main() {
        Frame = new JFrame();
        Player = new Player();

        Frame.setIconImage(icon.getImage());

        Bounds1 = new JLabel();
        Bounds2 = new JLabel();
        Bounds3 = new JLabel();
        Bounds4 = new JLabel();

//        Bounds1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//        Bounds2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//        Bounds3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//        Bounds4.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        CoinCount = new JLabel();

        digitLabels = new JLabel[3]; // Assuming the maximum coin count is 999

        for (int i = 0; i < digitLabels.length; i++) {
            digitLabels[i] = new JLabel();
            digitLabels[i].setHorizontalAlignment(JLabel.CENTER);
            digitLabels[i].setVerticalAlignment(JLabel.CENTER);
            digitLabels[i].setBounds(660 + i * 24, 100, 24, 32); // Adjust the position and size as needed
            Frame.add(digitLabels[i]);
        }

        // Main Menu
        MainMenu = new MainMenu();

        // Game
        GamePanel = new GamePanel();
        WOD = new WOD();
        CoinDrops = new CoinDrops(this);
        PauseMenu = new JPanel();
        PauseL = new JLabel("Paused");
        Nuke = new Nuke();
        Bomb = new Bomb();
        Bomb2 = new Bomb2();
        Sniper = new Sniper(Player);
        Sniper2 = new Sniper2(Player);
        Charger = new Charger();
        Beam = new Beams();
        //

        // End Panel
        EndPanel = new EndPanel(this);


        // Timer
        IGTimerP = new JPanel();
        IGTimerL = new JLabel();

        Frame.setTitle("Fun Talon!");
        Frame.setSize(1280, 960);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setLayout(null);
        Frame.setLocationRelativeTo(null);
        Frame.setResizable(false);

        Bounds1.setBounds(260,110,800,10);
        Bounds2.setBounds(1080,20,10,800);
        Bounds3.setBounds(260,20,10,800);
        Bounds4.setBounds(260,805,800,10);

        PauseMenu.setBounds(540, 25, 200,650);
        PauseMenu.setLayout(null);
        PauseMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        PauseL.setBounds(75,25,100,50);

        PauseMenu.add(PauseL);

        PauseMenu.setVisible(false);

        CoinCount.setBounds(690,5,50,10);
        CoinCount.setText("");

        IGTimerP.setBounds(640,0,100,30);
        IGTimerP.setBackground(new Color(80,88,109));
        Frame.getContentPane().setBackground(new Color(80,88,109));
        IGTimerP.add(IGTimerL);


        Frame.repaint();

        Frame.setVisible(true);

        if (MainMenu.Play.isEnabled()) {
            Frame.add(MainMenu.MainMenu);
            Frame.add(Player.Player);

            Frame.add(WOD.Indicator);
            Frame.add(WOD.WOD);

            Frame.add(CoinDrops.CoinDrops);
            Frame.add(CoinDrops.CoinHitBox);

            Frame.add(GamePanel.GamePanel);
            MainMenu.Play.addActionListener(e -> {
                MainMenu.PlayMusic(MainMenu.Sfx);
                Frame.remove(MainMenu.MainMenu);
                Frame.remove(GamePanel.GamePanel);
                Frame.remove(WOD.WOD);

                Frame.add(EndPanel.EndPanel);

                Frame.add(WOD.WOD);

                Frame.add(Bounds4);
                Frame.add(Bounds1);
                Frame.add(Bounds2);
                Frame.add(Bounds3);

                Frame.add(Player.NewEnemy);
                Frame.add(Player.PlayerHitbox);

                Frame.add(Sniper.Bullet);
                Frame.add(Charger.Charger);
                Frame.add(Charger.ChargerHitBox);

//                Frame.add(Beam.Shooter);
//                Frame.add(Beam.Beam);
                Frame.add(Sniper.Sniper);
                Frame.add(Sniper2.Sniper);
                Frame.add(Sniper2.Bullet);

                Frame.add(Nuke.Bomb);
                Frame.add(Nuke.BombExplosion);
                Frame.add(Nuke.BombHitbox);

                Frame.add(Bomb.BombHitbox);
                Frame.add(Bomb.BombExplosion);
                Frame.add(Bomb.Bomb);

                Frame.add(Bomb2.BombHitbox);
                Frame.add(Bomb2.BombExplosion);
                Frame.add(Bomb2.Bomb);

                Frame.add(CoinCount);
                Frame.add(IGTimerP);
                Frame.add(PauseMenu);

                Frame.add(GamePanel.GamePanel);

                Frame.setVisible(true);
                Frame.addKeyListener(Player);
                Frame.setFocusable(true);
                Frame.requestFocusInWindow();
                start();
            });
        }

        IGTimer = new Timer(1000, e -> {
            seconds += 1;
            int minutes = seconds / 60;
            int sec = seconds % 60;
        });
    }

    public static void main(String[] args) {
        new Main();
    }

    public void start() {
        if (GameThread != null) {
            stop();
        }
        GameThread = new Thread(this);
        GameThread.start();
    }

    public void stop() {
        if (GameThread != null) {
            GameThread.interrupt();
            GameThread = null;
        }
    }

    public void run() {
        while(true) {
            if (EndPanel.Restart.isEnabled()) {
                EndPanel.Restart.addActionListener(e -> {
                    EndPanel.EndPanel.setVisible(false);
                });
            }

            if (!Player.isDead) {
                if (!GamePanel.isPaused) {
                    IGTimer.start();
                    PauseMenu.setVisible(false);
                    Player.update(this);

                    if (Player.Coins == 5){
                        Bomb.start = true;
                        WOD.speed = 4;
                    }
                    if (Player.Coins == 15) {
                        Sniper.start = true;
                        WOD.speed = 5;
                        Bomb.BombDuration = 2000;
                    }
                    if (Player.Coins == 20) {
                        Charger.start = true;
                        Charger.ChargerTimeSpawn.start();
                        WOD.speed = 6;
                    }
                    if (Player.Coins == 25) {
                        Bomb2.start = true;
                        WOD.speed = 7;
                    }
                    if (Player.Coins == 30) {
                        Sniper2.start = true;
                    }
                    if (Player.Coins == 40){
                        Nuke.start = true;
                        WOD.speed = 8;
                    }
                    if (Player.Coins == 50){
                        WOD.speed = 9;
                    }

                    Sniper.update(Player);
                    Sniper2.update(Player);
                    Charger.update(Player);
                    WOD.update();

                    Nuke.update();
                    Bomb.update();
                    Bomb2.update();

                    if (Player.PlayerHitbox.getBounds().intersects(CoinDrops.CoinHitBox.getBounds()) && !Player.vul && !CoinDrops.isCollected){
                        CoinDrops.collected(this);
                        Player.Coins += 1;
                        String coinCountStr = String.format("%01d", Player.Coins); // Format the coin count as a 3-digit string
                        for (int i = 0; i < coinCountStr.length(); i++) {
                            int digit = Character.getNumericValue(coinCountStr.charAt(i));
                            digitLabels[i].setIcon(numberIcons[digit]);
                        }
                    }

                    // Player Dead
                    if (
                            (    Player.PlayerHitbox.getBounds().intersects(WOD.WOD.getBounds()) && !Player.vul)
                             || (Player.PlayerHitbox.getBounds().intersects(Bomb.BombHitbox.getBounds()) && !Player.vul && Bomb.BombExplosion.isVisible())
                             || (Player.PlayerHitbox.getBounds().intersects(Bomb2.BombHitbox.getBounds()) && !Player.vul && Bomb2.BombExplosion.isVisible())
                             || (Player.PlayerHitbox.getBounds().intersects(Sniper.Bullet.getBounds()) && !Player.vul && Sniper.Bullet.isVisible())
                             || (Player.PlayerHitbox.getBounds().intersects(Sniper2.Bullet.getBounds()) && !Player.vul && Sniper2.Bullet.isVisible())
                             || (Player.PlayerHitbox.getBounds().intersects(Nuke.BombHitbox.getBounds()) && !Player.vul && Nuke.BombExplosion.isVisible())
                             || (Player.PlayerHitbox.getBounds().intersects(Charger.ChargerHitBox.getBounds()) && !Player.vul && Charger.Charger.isVisible())) {
                        Player.isDead = true;
                        EndPanel.update();
                        if (Player.isDead) {
                            Player.Player.setIcon(Player.PlayerDeathAppearIcon);
                            WOD.start = false;
                            Player.DodgeTime.stop();
                            Player.PlayMusic(Player.dfx);


                            Timer DeathDelay = new Timer(300, e ->{
                                Player.Player.setIcon(Player.PlayerDeathIcon);
                                ((Timer)e.getSource()).stop();
                            });
                            DeathDelay.start();
                            Timer DeathTimer = new Timer(800, e -> {
                                Player.Player.setVisible(false);
                                ((Timer)e.getSource()).stop();
                            });
                            DeathTimer.start();

                            stop();
                        }
                    }

                    Frame.revalidate();
                    Frame.repaint();

                } else {
                    PauseMenu.setVisible(true);
                    IGTimer.stop();
                }
            } else {
                IGTimer.stop();

                EndPanel.EndTimer.start();
            }
            try {
                Thread.sleep(1000/60);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}