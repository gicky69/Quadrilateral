import javax.swing.*;
import java.awt.*;

public class    MainMenu {
    JPanel MainMenu;
    JButton Play;
    Image WKeyImage = new ImageIcon("Quadrilateral/src/Images/pixil-gif-drawing-2.gif").getImage().getScaledInstance(64,64,Image.SCALE_DEFAULT);
    Image AKeyImage = new ImageIcon("Quadrilateral/src/Images/pixil-gif-drawingA.gif").getImage().getScaledInstance(64,64,Image.SCALE_DEFAULT);
    ImageIcon AKeyIcon = new ImageIcon(AKeyImage);
    ImageIcon WKeyIcon = new ImageIcon(WKeyImage);
    JButton HowToPlay;
    JButton Exit;

    public MainMenu() {
        MainMenu = new JPanel();
        Play = new JButton("Play");
        Play.setIcon(WKeyIcon);
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
