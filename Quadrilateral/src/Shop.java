import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Shop {
    JPanel ShopPanel;
    JLabel ShopLabel;
    JButton Upgrade1;
    JButton Upgrade2;
    JButton Upgrade3;

    JPanel UpgradesPanel;

    public Shop() {
        ShopPanel = new JPanel();
        ShopLabel = new JLabel("Upgrades");

        UpgradesPanel = new JPanel();
        Upgrade1 = new JButton("Upgrade 1");
        Upgrade2 = new JButton("Upgrade 2");
        Upgrade3 = new JButton("Upgrade 3");

        ShopPanel.setBounds(0,0,1280,720);
        ShopPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        ShopPanel.setBackground(Color.WHITE);

        ShopLabel.setBounds(640,100,100,100);
        ShopLabel.setBackground(Color.GRAY);

        UpgradesPanel.setBounds(300,200,800,500);
        UpgradesPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        UpgradesPanel.setBackground(Color.GRAY);
        UpgradesPanel.setLayout(null);

        Upgrade1.setBounds(150,300,50,50);
        Upgrade2.setBounds(350,300,50,50);
        Upgrade3.setBounds(500,300,50,50);

        ShopPanel.add(ShopLabel);
        ShopPanel.add(UpgradesPanel);

        UpgradesPanel.add(Upgrade1);
        UpgradesPanel.add(Upgrade2);
        UpgradesPanel.add(Upgrade3);

        ShopPanel.setLayout(null);
        ShopPanel.setVisible(false);
    }
}
