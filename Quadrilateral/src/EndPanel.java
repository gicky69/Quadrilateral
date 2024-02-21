import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndPanel {
    JPanel EndPanel;
    JLabel EndLabel;
    JButton Restart;
    Main mainInstance; // Instance of Main class

    Timer EndTimer;
    boolean hasEnded = false;

    public EndPanel(Main mainInstance) {
        this.mainInstance = mainInstance; // Assign the passed instance to the local instance

        EndPanel = new JPanel();
        Restart = new JButton("Restart");
        EndLabel = new JLabel("Game Over");

        EndPanel.setBounds(345,20,700,700);
        EndPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        EndPanel.setOpaque(true);
        EndPanel.setBackground(new Color(6,19,79,50));

        EndLabel.setBounds(320,150,100,100);
        EndLabel.setForeground(Color.WHITE);
        EndLabel.setBackground(Color.WHITE);

        Restart.setBounds(350,300,100,50);

        EndPanel.add(Restart);
        EndPanel.add(EndLabel);

        EndTimer = new Timer(1000, e ->  {
            EndPanel.setVisible(true);
            ((Timer)e.getSource()).stop();
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

    private void restartGame() {
        EndTimer.stop();

        EndPanel.setVisible(false);

        mainInstance.seconds = 0;

        // Reset player
        mainInstance.Player.reset();
        mainInstance.Player.Player.setVisible(true);

        // Reset Enemies
        mainInstance.Bomb.reset();
        mainInstance.Sniper.reset();
        mainInstance.Charger.reset();
        mainInstance.Beam.reset();

        // Reset Coins
        mainInstance.CoinDrops.reset();

        // Reset WOD
        mainInstance.WOD.reset();

        // Reset EndPanel

        // Reset GamePanel
        mainInstance.GamePanel.generate();

        // Reset GameThread
    }
}
