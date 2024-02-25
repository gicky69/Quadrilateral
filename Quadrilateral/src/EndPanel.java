import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EndPanel {
    Image GameOverImg = new ImageIcon("Quadrilateral/src/Images/EndPanel/GO.png").getImage().getScaledInstance(230,50, Image.SCALE_SMOOTH);
    ImageIcon RestartIcon = new ImageIcon("Quadrilateral/src/Images/EndPanel/restart1.png");
    ImageIcon Restart2Icon = new ImageIcon("Quadrilateral/src/Images/EndPanel/restart2.png");
    ImageIcon ScoreIcon = new ImageIcon("Quadrilateral/src/Images/EndPanel/sc.png");
    ImageIcon EPIcon = new ImageIcon("Quadrilateral/src/Images/EndPanel/Ep.png");
    ImageIcon nums[] = {
            new ImageIcon("Quadrilateral/src/Images/Numbers/0.png"),
            new ImageIcon("Quadrilateral/src/Images/Numbers/1.png"),
            new ImageIcon("Quadrilateral/src/Images/Numbers/2.png"),
            new ImageIcon("Quadrilateral/src/Images/Numbers/3.png"),
            new ImageIcon("Quadrilateral/src/Images/Numbers/4.png"),
            new ImageIcon("Quadrilateral/src/Images/Numbers/5.png"),
            new ImageIcon("Quadrilateral/src/Images/Numbers/6.png"),
            new ImageIcon("Quadrilateral/src/Images/Numbers/7.png"),
            new ImageIcon("Quadrilateral/src/Images/Numbers/8.png"),
            new ImageIcon("Quadrilateral/src/Images/Numbers/9.png")
    };

    ImageIcon GameOver = new ImageIcon(GameOverImg);

    JPanel EndPanel;
    JLabel EndBgLabel;
    JLabel EndLabel;
    JLabel[] ScorePLabel;
    JLabel ScoreLabel;
    JButton Restart;
    Main mainInstance; // Instance of Main class

    Timer EndTimer;

    public EndPanel(Main mainInstance) {
        this.mainInstance = mainInstance; // Assign the passed instance to the local instance

        EndPanel = new JPanel();
        EndBgLabel = new JLabel(EPIcon);
        Restart = new JButton(RestartIcon);
        EndLabel = new JLabel(GameOver);
        ScoreLabel = new JLabel(ScoreIcon);

        ScorePLabel = new JLabel[3];
        for (int i=0;i< ScorePLabel.length;i++) {
            ScorePLabel[i] = new JLabel();
            ScorePLabel[i].setVisible(false);
            ScorePLabel[i].setBounds(700 + (i*16), 400, 16, 24);
            EndPanel.add(ScorePLabel[i]);
        }

        EndBgLabel.setBounds(0,0,1280,960);

        EndPanel.setBounds(0,0,1280,960);
        EndPanel.setOpaque(false);

        EndLabel.setBounds(560,300,230,50);
        EndLabel.setForeground(Color.WHITE);

        ScoreLabel.setBounds(600,400,100,24);

        Restart.setBorderPainted(false);
        Restart.setBackground(new Color(80,88,109));
        Restart.setContentAreaFilled(false);
        Restart.setFocusPainted(false);
        Restart.setBounds(620,460,100,24);


        EndPanel.add(Restart);
        EndPanel.add(ScoreLabel);
        EndPanel.add(EndLabel);
        EndPanel.add(EndBgLabel);

        EndTimer = new Timer(1000, e ->  {
            EndPanel.setVisible(true);
            ((Timer)e.getSource()).stop();
        });

        Restart.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                Restart.setIcon(Restart2Icon);
                Restart.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            public void mouseExited(MouseEvent evt) {
                Restart.setIcon(RestartIcon);
                Restart.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        Restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });

        EndPanel.setLayout(null);
        EndPanel.setVisible(false);
    }

    public void update() {
        String score = String.format("%01d", mainInstance.Player.Coins);
        for (int i=0;i<score.length();i++) {
            ScorePLabel[i].setIcon(nums[Character.getNumericValue(score.charAt(i))]);
            ScorePLabel[i].setVisible(true);

            ScorePLabel[i].revalidate();
            ScorePLabel[i].repaint();
        }
    }

    private void restartGame() {
        EndTimer.stop();

        EndPanel.setVisible(false);

        mainInstance.seconds = 0;
        mainInstance.digitLabels[0].setIcon(null);
        mainInstance.digitLabels[1].setIcon(null);
        mainInstance.digitLabels[2].setIcon(null);

        // Reset player
        mainInstance.Player.reset();
        mainInstance.Player.Player.setVisible(true);

        // Reset Enemies
        mainInstance.Bomb.reset();
        mainInstance.Bomb2.reset();
        mainInstance.Nuke.reset();
        mainInstance.Sniper.reset();
        mainInstance.Sniper2.reset();
        mainInstance.Charger.reset();
        mainInstance.Beam.reset();

        // Reset Coins
        mainInstance.CoinDrops.reset();

        // Reset WOD
        mainInstance.WOD.reset();

        // Reset EndPanel
    }
}
