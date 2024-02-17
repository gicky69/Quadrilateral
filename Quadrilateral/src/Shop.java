import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Shop implements ActionListener {
    JPanel ShopPanel;
    JLabel ShopLabel;
    JButton Upgrade1;
    JButton Upgrade2;
    JButton Upgrade3;

    JPanel UpgradesPanel;
    Random rand;
    int speed;

    public Shop() {
        ShopPanel = new JPanel();
        rand = new Random();
        ShopLabel = new JLabel("Upgrades");

        UpgradesPanel = new JPanel();
        Upgrade1 = new JButton("Upgrade 1");
        Upgrade2 = new JButton("Upgrade 2");
        Upgrade3 = new JButton("Upgrade 3");

        ShopPanel.setBounds(0,0,1280,720);
        ShopPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        ShopPanel.setBackground(Color.WHITE);

        ShopLabel.setBounds(630,100,100,100);
        ShopLabel.setBackground(Color.GRAY);

        UpgradesPanel.setBounds(250,200,800,400);
        UpgradesPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        UpgradesPanel.setBackground(Color.GRAY);
        UpgradesPanel.setLayout(null);

        Upgrade1.setBounds(50,50,200,300);
        Upgrade2.setBounds(300,50,200,300);
        Upgrade3.setBounds(550,50,200,300);

        Upgrade1.addActionListener(this);
        Upgrade2.addActionListener(this);
        Upgrade3.addActionListener(this);

        ShopPanel.add(ShopLabel);
        ShopPanel.add(UpgradesPanel);

        UpgradesPanel.add(Upgrade1);
        UpgradesPanel.add(Upgrade2);
        UpgradesPanel.add(Upgrade3);

        ShopPanel.setLayout(null);
        ShopPanel.setVisible(false);

        RandomItems();
    }

    public void update() {
        RandomItems();

    }

    public void RandomItems() {
        int random1 = rand.nextInt(3);
        int random2 = rand.nextInt(3);
        int random3 = rand.nextInt(3);

        switch(random1) {
            case 0:
                Upgrade1.setText("Speed");
                break;
            case 1:
                Upgrade1.setText("Attack");
                break;
            case 2:
                Upgrade1.setText("Upgrade 2");
                break;
        }

        switch (random2) {
            case 0:
                Upgrade2.setText("Speed");
                break;
            case 1:
                Upgrade2.setText("Attack");
                break;
            case 2:
                Upgrade2.setText("Upgrade 2");
                break;
        }

        switch (random3){
            case 0:
                Upgrade3.setText("Speed");
                break;
            case 1:
                Upgrade3.setText("Attack");
                break;
            case 2:
                Upgrade3.setText("Upgrade 2");
                break;
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // if Player Clicks Upgrade 1
        if (e.getSource() == Upgrade1) {
            if (Upgrade1.getText() == "Speed") {
                speed += 1;
            }
        } else if (e.getSource() == Upgrade2) {
            if (Upgrade2.getText() == "Speed") {
                speed += 1;
            }
        } else if (e.getSource() == Upgrade3) {
            if (Upgrade3.getText() == "Speed") {
                speed += 1;
            }
        }


        ShopPanel.repaint();
        ShopPanel.revalidate();
    }
}
