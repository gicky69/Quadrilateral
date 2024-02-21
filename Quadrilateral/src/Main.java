import javax.swing.*;
import java.awt.*;

public class Main implements Runnable {

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
    Beams Beam;
    Sniper Sniper;
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

        Bounds1 = new JLabel();
        Bounds2 = new JLabel();
        Bounds3 = new JLabel();
        Bounds4 = new JLabel();

        CoinCount = new JLabel();

        // Main Menu
        MainMenu = new MainMenu();

        // Game
        GamePanel = new GamePanel();
        WOD = new WOD();
        CoinDrops = new CoinDrops(this);
        PauseMenu = new JPanel();
        PauseL = new JLabel("Paused");
        Bomb = new Bomb();
        Sniper = new Sniper(Player);
        Charger = new Charger();
        Beam = new Beams();
        //

        // End Panel
        EndPanel = new EndPanel(this);


        // Timer
        IGTimerP = new JPanel();
        IGTimerL = new JLabel();

        Frame.setTitle("Dodge It!");
        Frame.setSize(1280, 760);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setLayout(null);
        Frame.setLocationRelativeTo(null);
        Frame.setResizable(false);

        Bounds1.setBounds(290,0,800,10);

        Bounds2.setBounds(260,20,10,800);

        Bounds3.setBounds(1085,20,10,800);

        Bounds4.setBounds(290,660,800,10);

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
        IGTimerL.setText("");
        IGTimerP.add(IGTimerL);

        Frame.add(MainMenu.MainMenu);
        Frame.repaint();

        Frame.setVisible(true);

        if (MainMenu.Play.isEnabled()) {
            MainMenu.Play.addActionListener(e -> {

                Frame.remove(MainMenu.MainMenu);

                Frame.add(EndPanel.EndPanel);

                Frame.add(Bounds4);
                Frame.add(Bounds1);
                Frame.add(Bounds2);
                Frame.add(Bounds3);

                Frame.add(Player.NewEnemy);
                Frame.add(Player.Player);
                Frame.add(Player.PlayerHitbox);
                Frame.add(WOD.Indicator);
                Frame.add(WOD.WOD);

                Frame.add(Sniper.Bullet);
                Frame.add(Charger.Charger);

//                Frame.add(Beam.Shooter);
//                Frame.add(Beam.Beam);
                Frame.add(Sniper.Sniper);

                Frame.add(CoinDrops.CoinDrops);
                Frame.add(CoinDrops.CoinHitBox);

                Frame.add(Bomb.BombHitbox);
                Frame.add(Bomb.BombExplosion);
                Frame.add(Bomb.Bomb);

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
                    if (Player.Coins == 10) {
                        Sniper.start = true;
                        WOD.speed = 5;
                        Bomb.BombDuration = 2000;
                    }
                    if (Player.Coins == 15) {
                        Charger.start = true;
                        WOD.speed = 6;
                    }

                    Sniper.update(Player);
                    Charger.update(Player);
                    WOD.update();

                    Bomb.update();

                    if (Player.PlayerHitbox.getBounds().intersects(CoinDrops.CoinHitBox.getBounds()) && !Player.isDodge){
                        CoinDrops.collected(this);
                        Player.Coins += 1;
                        CoinCount.setText("" + Player.Coins);
                    }

                    // Player Dead
                    if (
                            (    Player.PlayerHitbox.getBounds().intersects(WOD.WOD.getBounds()) && !Player.isDodge)
                             || (Player.PlayerHitbox.getBounds().intersects(Bomb.BombHitbox.getBounds()) && !Player.isDodge && Bomb.BombExplosion.isVisible())
                             || (Player.PlayerHitbox.getBounds().intersects(Sniper.Bullet.getBounds()) && !Player.isDodge && Sniper.Bullet.isVisible())
                             || (Player.PlayerHitbox.getBounds().intersects(Charger.Charger.getBounds()) && !Player.isDodge && Charger.Charger.isVisible())) {
                        Player.isDead = true;
                        if (Player.isDead) {
                            Player.PlayMusic(Player.dfx);

                            Player.Player.setIcon(Player.PlayerDeathAppearIcon);
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