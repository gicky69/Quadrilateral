import javax.swing.*;
import java.awt.*;

public class Main implements Runnable {
    JFrame Frame;

    // Main Menu
    MainMenu MainMenu;

    GamePanel GamePanel;
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

        Frame.setTitle("Super Quadrilateral");
        Frame.setSize(1280, 720);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setLayout(null);
        Frame.setLocationRelativeTo(null);
        Frame.setResizable(false);

        PauseMenu.setBounds(540, 25, 200,650);
        PauseMenu.setLayout(null);
        PauseMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        PauseL.setBounds(75,25,100,50);

        PauseMenu.add(PauseL);

        PauseMenu.setVisible(false);

        IGTimerP.setBounds(640,0,100,50);
        IGTimerP.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        IGTimerP.setBackground(Color.GRAY);
        IGTimerL.setText("");
        IGTimerP.add(IGTimerL);

        Frame.add(MainMenu.MainMenu);

        Frame.setVisible(true);

        if (MainMenu.Play.isEnabled()) {
            MainMenu.Play.addActionListener(e -> {
                Frame.remove(MainMenu.MainMenu);
                Frame.add(IGTimerP);
                Frame.add(PauseMenu);
                Frame.add(GamePanel.GamePanel);
                Frame.add(GamePanel.CoinsPanel);
                Frame.setVisible(true);
                GamePanel.GamePanel.requestFocusInWindow();
                GamePanel.GamePanel.setFocusable(true);


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