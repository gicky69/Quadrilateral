import javax.swing.*;
import java.awt.*;

public class MainMenu {
    JPanel MainMenu;
    JButton Play;
    JButton HowToPlay;
    JButton Exit;

    public MainMenu() {
        MainMenu = new JPanel();
        Play = new JButton("Play");
        HowToPlay = new JButton("How To Play");
        Exit = new JButton("Exit");

        MainMenu.setBounds(0,0,1280,720);
        MainMenu.setBackground(Color.BLUE);
        MainMenu.setLayout(null);

        Play.setBounds(600,250,100,50);
        Play.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        HowToPlay.setBounds(575,310,150,50);
        HowToPlay.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        Exit.setBounds(600,450,100,50);
        Exit.setBorder(BorderFactory.createLineBorder(Color.BLACK));




        Play.addActionListener(e -> {
            MainMenu.setVisible(false);
        });

        MainMenu.add(Play);
        MainMenu.add(HowToPlay);
        MainMenu.add(Exit);

        MainMenu.setVisible(true);
    }
}
