import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class    MainMenu {
    JPanel MainMenu;
    JLabel MainMenuBG;
    JLabel MainMenuTitle;
    JLabel MainMeneIndc;
    JButton Play;
    Image WKeyImage = new ImageIcon("Quadrilateral/src/Images/pixil-gif-drawing-2.gif").getImage().getScaledInstance(64,64,Image.SCALE_DEFAULT);
    Image AKeyImage = new ImageIcon("Quadrilateral/src/Images/pixil-gif-drawingA.gif").getImage().getScaledInstance(64,64,Image.SCALE_DEFAULT);
    Image BgTitleImg = new ImageIcon("Quadrilateral/src/Images/MainMenu/bgtitle.gif").getImage().getScaledInstance(191,240,Image.SCALE_DEFAULT);
    Image BgIndcImg = new ImageIcon("Quadrilateral/src/Images/MainMenu/indbg.gif").getImage().getScaledInstance(68,54,Image.SCALE_DEFAULT);
    Image PlayButtonImage1 = new ImageIcon("Quadrilateral/src/Images/PlayButton1.png").getImage().getScaledInstance(100,50,Image.SCALE_DEFAULT);
    Image PlayButtonImage2 = new ImageIcon("Quadrilateral/src/Images/PlayButton2.png").getImage().getScaledInstance(100,50,Image.SCALE_DEFAULT);
    Image MainMenuImage = new ImageIcon("Quadrilateral/src/Images/MainMenu/mainmenu.gif").getImage().getScaledInstance(1280,720,Image.SCALE_DEFAULT);
    ImageIcon BgTitelIcon = new ImageIcon(BgTitleImg);
    ImageIcon BgIndcIcon = new ImageIcon(BgIndcImg);
    ImageIcon MainMenuIcon = new ImageIcon(MainMenuImage);
    ImageIcon AKeyIcon = new ImageIcon(AKeyImage);
    ImageIcon PlayButtonIcon1 = new ImageIcon(PlayButtonImage1);
    ImageIcon WKeyIcon = new ImageIcon(WKeyImage);
    JButton HowToPlay;
    JButton Exit;

    public MainMenu() {
        MainMenu = new JPanel();
        MainMenuBG = new JLabel();
        MainMenuTitle = new JLabel();
        MainMeneIndc = new JLabel();

        MainMenuBG.setIcon(MainMenuIcon);

        Play = new JButton("Play");
        Play.setHorizontalAlignment(JLabel.CENTER);
        Play.setVerticalAlignment(JLabel.CENTER);
        HowToPlay = new JButton("How To Play");
        Exit = new JButton("Exit");

        MainMenu.setBounds(0,0,1280,720);
        MainMenu.setLayout(null);

        MainMenuTitle.setBounds(560,-10,191,240);
        MainMenuTitle.setIcon(BgTitelIcon);

        MainMeneIndc.setBounds(50,300,68,54);
        MainMeneIndc.setIcon(BgIndcIcon);

        MainMenuBG.setBounds(0,0,1280,720);

        Play.setBounds(606,250,100,50);
        Play.setIcon(PlayButtonIcon1);
        Play.setOpaque(false);
        Play.setContentAreaFilled(false);
        Play.setBorderPainted(false);
        Play.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        HowToPlay.setBounds(575,310,150,50);
        HowToPlay.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        Exit.setBounds(600,450,100,50);
        Exit.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        Play.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Play.setIcon(new ImageIcon(PlayButtonImage2));
                Play.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Play.setIcon(new ImageIcon(PlayButtonImage1));
                Play.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        Play.addActionListener(e -> {
            MainMenu.setVisible(false);
        });

        MainMenu.add(MainMenuTitle);
        MainMenu.add(MainMeneIndc);
        MainMenu.add(Play);
        MainMenu.add(HowToPlay);
        MainMenu.add(Exit);
        MainMenu.add(MainMenuBG);




        MainMenu.setVisible(true);
    }
}
