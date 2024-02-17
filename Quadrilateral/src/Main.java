import javax.swing.*;
import java.awt.*;

public class Main implements Runnable {

    JFrame Frame;
    JLabel Bounds1;
    JLabel Bounds2;
    JLabel Bounds3;
    JLabel Bounds4;

    // Main Menu
    MainMenu MainMenu;

    GamePanel GamePanel;
    Player Player;
    Thread GameThread;
    Shop ShopPanel;

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

        // Main Menu
        MainMenu = new MainMenu();

        // Game
        GamePanel = new GamePanel();
        ShopPanel = new Shop();
        PauseMenu = new JPanel();
        PauseL = new JLabel("Paused");


        // Timer
        IGTimerP = new JPanel();
        IGTimerL = new JLabel();

        Frame.setTitle("Dodge It!");
        Frame.setSize(1280, 720);
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

        IGTimerP.setBounds(640,0,100,50);
        IGTimerP.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        IGTimerP.setBackground(Color.GRAY);
        Frame.getContentPane().setBackground(new Color(80,88,109));
        IGTimerL.setText("");
        IGTimerP.add(IGTimerL);

        Frame.add(MainMenu.MainMenu);

        Frame.setVisible(true);

        if (MainMenu.Play.isEnabled()) {
            MainMenu.Play.addActionListener(e -> {
                Frame.remove(MainMenu.MainMenu);
                Frame.add(Bounds4);
                Frame.add(Bounds1);
                Frame.add(Bounds2);
                Frame.add(Bounds3);

                Frame.add(Player.Player);
                Frame.add(Player.PlayerHitbox);
                Frame.add(IGTimerP);
                Frame.add(PauseMenu);
                Frame.add(GamePanel.GamePanel);
                Frame.add(GamePanel.CoinsPanel);
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
            IGTimerL.setText(String.format("%02d:%02d", minutes, sec));
        });
    }

    public static void main(String[] args) {
        new Main();
    }

    public void start() {
        GameThread = new Thread(this);
        GameThread.start();
    }

    public void run() {
        while(true) {
            if (!GamePanel.isPaused){
                IGTimer.start();
                PauseMenu.setVisible(false);
                Player.update(this);
                GamePanel.update(this);

                GamePanel.CoinsLabel.setText("Coins: " + Player.Coins);
                GamePanel.HealthLabel.setText("Health: " + Player.Health);
                Frame.repaint();

//                if ((seconds + 2) % 10 == 0){
//                    Frame.add(ShopPanel.ShopPanel);
//                    ShopPanel.ShopPanel.setVisible(true);
//                    GamePanel.GamePanel.setVisible(false);
//                    IGTimer.stop();
//                }
            } else {
                PauseMenu.setVisible(true);
                IGTimer.stop();
            }
            try {
                Thread.sleep(1000/60);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}