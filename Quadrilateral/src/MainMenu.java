import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class    MainMenu {
    JPanel MainMenu;
    JLabel MainMenuBG;
    JLabel MainMenuTitle;
    JLabel MainMeneIndc;
    JButton Play;
    Image WKeyImage = new ImageIcon("Quadrilateral/src/Images/pixil-gif-drawing-2.gif").getImage().getScaledInstance(64,64,Image.SCALE_DEFAULT);
    Image AKeyImage = new ImageIcon("Quadrilateral/src/Images/pixil-gif-drawingA.gif").getImage().getScaledInstance(64,64,Image.SCALE_DEFAULT);
    Image BgIndcImg = new ImageIcon("Quadrilateral/src/Images/MainMenu/indbg.gif").getImage().getScaledInstance(68,54,Image.SCALE_DEFAULT);
    Image PlayButtonImage1 = new ImageIcon("Quadrilateral/src/Images/Buttons/PlayButt1.png").getImage().getScaledInstance(100,50,Image.SCALE_DEFAULT);
    Image PlayButtonImage2 = new ImageIcon("Quadrilateral/src/Images/Buttons/PlayButt2.png").getImage();
    Image HTPButtonImage1 = new ImageIcon("Quadrilateral/src/Images/Buttons/HTPButt.png").getImage();
    Image HTPButtonImage2 = new ImageIcon("Quadrilateral/src/Images/Buttons/HTPButts.png").getImage();
    Image ExitButtonImage1 = new ImageIcon("Quadrilateral/src/Images/Buttons/ExitButt.png").getImage().getScaledInstance(80,32,Image.SCALE_DEFAULT);
    Image ExitButtonImage2 = new ImageIcon("Quadrilateral/src/Images/Buttons/ExitButts.png").getImage();
    ImageIcon BgTitelIcon = new ImageIcon("Quadrilateral/src/Images/MainMenu/bgtitle.png");
    ImageIcon BgIndcIcon = new ImageIcon(BgIndcImg);
    ImageIcon AKeyIcon = new ImageIcon(AKeyImage);
    ImageIcon PlayButtonIcon1 = new ImageIcon(PlayButtonImage1);
    ImageIcon PlayButtonIcon2 = new ImageIcon(PlayButtonImage2);
    ImageIcon HTPButtonIcon1 = new ImageIcon(HTPButtonImage1);
    ImageIcon HTPButtonIcon2 = new ImageIcon(HTPButtonImage2);
    ImageIcon ExitButtonIcon1 = new ImageIcon(ExitButtonImage1);
    ImageIcon ExitButtonIcon2 = new ImageIcon(ExitButtonImage2);
    ImageIcon WKeyIcon = new ImageIcon(WKeyImage);
    JButton HowToPlay;
    JButton Exit;

    String Sfx = "Quadrilateral/src/Sounds/start.wav";

    public MainMenu() {
        MainMenu = new JPanel();
        MainMenuBG = new JLabel();
        MainMenuTitle = new JLabel();
        MainMeneIndc = new JLabel();

        MainMenuBG.setHorizontalAlignment(JLabel.CENTER);
        MainMenuBG.setVerticalAlignment(JLabel.CENTER);

        Play = new JButton();
        Play.setHorizontalAlignment(JLabel.CENTER);
        Play.setVerticalAlignment(JLabel.CENTER);
        HowToPlay = new JButton();
        Exit = new JButton();

        MainMenu.setBounds(0,0,1280,720);
        MainMenu.setLayout(null);
        MainMenu.setOpaque(false);

        MainMenuTitle.setBounds(550,150,300,100);
        MainMenuTitle.setIcon(BgTitelIcon);

        MainMeneIndc.setBounds(50,300,68,54);
        MainMeneIndc.setIcon(BgIndcIcon);

        Play.setBounds(595,300,160,64);
        Play.setIcon(PlayButtonIcon1);
        Play.setHorizontalAlignment(JLabel.CENTER);
        Play.setVerticalAlignment(JLabel.CENTER);
        Play.setOpaque(false);
        Play.setContentAreaFilled(false);
        Play.setBorderPainted(false);
        Play.setBorder(null);
        Play.setBackground(Color.GRAY);

        HowToPlay.setBounds(580,400,195,49);
        HowToPlay.setIcon(HTPButtonIcon1);
        HowToPlay.setOpaque(false);
        HowToPlay.setContentAreaFilled(false);
        HowToPlay.setBorderPainted(false);
        HowToPlay.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        Exit.setBounds(630,460,95,32);
        Exit.setIcon(ExitButtonIcon1);
        Exit.setOpaque(false);
        Exit.setContentAreaFilled(false);
        Exit.setBorderPainted(false);
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
        HowToPlay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                HowToPlay.setCursor(new Cursor(Cursor.HAND_CURSOR));
                HowToPlay.setIcon(new ImageIcon(HTPButtonImage2));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                HowToPlay.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                HowToPlay.setIcon(new ImageIcon(HTPButtonImage1));
            }
        });
        Exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Exit.setIcon(ExitButtonIcon2);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Exit.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                Exit.setIcon(ExitButtonIcon1);
            }
        });
        Play.addActionListener(e -> {
            MainMenu.setVisible(false);
        });

        MainMenu.add(Play);
        MainMenu.add(HowToPlay);
        MainMenu.add(Exit);
        MainMenu.add(MainMenuTitle);




        MainMenu.setVisible(true);
    }

    public static void PlayMusic(String filepath) {
        // if key is pressed
        try {
            File musicFile = new File(filepath);

            if (musicFile.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-7.0f);
                clip.start();
            }
            else {
                System.out.println("File not found");
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
