import javax.swing.*;
import java.awt.*;

public class EndPanel {
    JPanel EndPanel;
    JLabel EndLabel;

    public EndPanel() {
        EndPanel = new JPanel();
        EndLabel = new JLabel("Game Over");

        EndPanel.setBounds(345,0,700,700);
        EndPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        EndPanel.setOpaque(true);
        EndPanel.setBackground(new Color(6,19,79,50));

        EndLabel.setBounds(320,150,100,100);
        EndLabel.setForeground(Color.WHITE);
        EndLabel.setBackground(Color.WHITE);

        EndPanel.add(EndLabel);

        EndPanel.setLayout(null);
        EndPanel.setVisible(false);
    }
}
