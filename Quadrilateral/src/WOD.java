import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WOD {
    JLabel WOD;
    int a;
    List<JLabel> WODs = new ArrayList<JLabel>();
    int Map1[] = {
            1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,5,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,
            6,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,
    };
    int WODD[] = {
            2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,3,3,3,3,
    };

    Image WODImage = new ImageIcon("Quadrilateral/src/Images/WOD.png").getImage().getScaledInstance(32,32,Image.SCALE_DEFAULT);
    Image WODTPImage = new ImageIcon("Quadrilateral/src/Images/WODTP.png").getImage().getScaledInstance(32,32,Image.SCALE_DEFAULT);
    Image WODHEADImage = new ImageIcon("Quadrilateral/src/Images/MAPHEAD1.gif").getImage().getScaledInstance(32,32,Image.SCALE_DEFAULT);
    Image WODHeadImageBtm = new ImageIcon("Quadrilateral/src/Images/MAPHEAD2.gif").getImage().getScaledInstance(32,32,Image.SCALE_DEFAULT);
    ImageIcon WODHEADIcon = new ImageIcon(WODHEADImage);
    ImageIcon WODHeadIconBtm = new ImageIcon(WODHeadImageBtm);
    ImageIcon WODIcon = new ImageIcon(WODImage);
    ImageIcon WODTPIcon = new ImageIcon(WODTPImage);

    Random rand;

    int speed = 2;

    Main MF;
    public WOD(Main mf) {
        WOD = new JLabel();
        rand = new Random();
        this.MF = mf;
        a = rand.nextInt(4)+1;

        createWOD(a);
        moveWOD(a);
    }

    public void update() {
        moveWOD(a);
    }

    public void createWOD(int pos) {
        for (int i=0;i<25;i++){
            if (WODD[i] == 1) {
                JLabel Wall = new JLabel();
                Wall.setIcon(WODIcon);
                Wall.setVisible(true);
                MF.Frame.add(Wall);
                WODs.add(Wall);
            }
            if (WODD[i] == 2) {
                JLabel Wall = new JLabel();
                Wall.setIcon(WODHEADIcon);
                Wall.setVisible(true);
                MF.Frame.add(Wall);
                WODs.add(Wall);
            }
            if (WODD[i] == 3) {
                JLabel Wall = new JLabel();
                Wall.setIcon(WODHeadIconBtm);
                Wall.setVisible(true);
                MF.Frame.add(Wall);
                WODs.add(Wall);
            }

            switch(pos) {
                case 1:
                    WODs.get(i).setBounds(290, (i*32), 32, 32);
                    break;
                case 2:
                    WODs.get(i).setBounds(290 + (i*32), 30, 32, 32);
                    break;
                case 3:
                    WODs.get(i).setBounds(800, 30 + (i*32), 32, 32);
                    break;
                case 4:
                    WODs.get(i).setBounds(290 + (i*32), 670, 32, 32);
                    break;
            }
            moveWOD(pos);
        }

        MF.Frame.revalidate();
        MF.Frame.repaint();
    }

    public void moveWOD(int lvl) {
        for (int i=WODs.size()-1;i>=0;i--){
            JLabel wod = WODs.get(i);

            switch(lvl) {
                case 1:
                    wod.setLocation(wod.getX() + speed, wod.getY());
                    break;
                case 2:
                    wod.setIcon(WODTPIcon);
                    wod.setLocation(wod.getX(), wod.getY() + speed);
                    break;
                case 3:
                    wod.setLocation(wod.getX() - speed, wod.getY());
                    break;
                case 4:
                    wod.setIcon(WODTPIcon);
                    wod.setLocation(wod.getX(), wod.getY() - speed);
                    break;
            }

            // Check if the WOD has reached the end of the panel
            if      ((lvl == 1 && wod.getX() > 850) ||
                    (lvl == 2 && wod.getY() > 670) ||
                    (lvl == 3 && wod.getX() < 0) ||
                    (lvl == 4 && wod.getY() < 30)) {
                wod.setLocation(-500, 0);
                MF.Frame.remove(wod);
                wod.setVisible(false);
                WODs.remove(wod); // Remove wod from WODs list
                WODs.clear();
                // randomize lvl
                int b = rand.nextInt(4)+1;
                int time = rand.nextInt(4001) + 50;
                Timer createWod = new Timer(time, e -> {
                    createWOD(b);
                    ((Timer)e.getSource()).stop();
                });
                createWod.start();
                break;
            }
        }
        MF.Frame.revalidate();
        MF.Frame.repaint();
    }
}
