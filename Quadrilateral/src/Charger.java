import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Charger {
    Image ChargerImg1 = new ImageIcon("Quadrilateral/src/Images/Charger/charger1-idlle.gif").getImage().getScaledInstance(48,48,Image.SCALE_DEFAULT);
    Image ChargerImg2 = new ImageIcon("Quadrilateral/src/Images/Charger/charger2-idle.gif").getImage().getScaledInstance(48,48,Image.SCALE_DEFAULT);
    Image ChargerImg3 = new ImageIcon("Quadrilateral/src/Images/Charger/charger3-idle.gif").getImage().getScaledInstance(48,48,Image.SCALE_DEFAULT);
    Image ChargerImg4 = new ImageIcon("Quadrilateral/src/Images/Charger/charger4-idle.gif").getImage().getScaledInstance(48,48,Image.SCALE_DEFAULT);
    Image ChargerImg1F = new ImageIcon("Quadrilateral/src/Images/Charger/charger1-fly.gif").getImage().getScaledInstance(48,48,Image.SCALE_DEFAULT);
    Image ChargerImg2F = new ImageIcon("Quadrilateral/src/Images/Charger/charger2-fly.gif").getImage().getScaledInstance(48,48,Image.SCALE_DEFAULT);
    Image ChargerImg3F = new ImageIcon("Quadrilateral/src/Images/Charger/charger3-fly.gif").getImage().getScaledInstance(48,48,Image.SCALE_DEFAULT);
    Image ChargerImg4F = new ImageIcon("Quadrilateral/src/Images/Charger/charger4-fly.gif").getImage().getScaledInstance(48,48,Image.SCALE_DEFAULT);


    ImageIcon ChargerIcon1 = new ImageIcon(ChargerImg1);
    ImageIcon ChargerIcon2 = new ImageIcon(ChargerImg2);
    ImageIcon ChargerIcon3 = new ImageIcon(ChargerImg3);
    ImageIcon ChargerIcon4 = new ImageIcon(ChargerImg4);

    ImageIcon ChargerIcon1F = new ImageIcon(ChargerImg1F);
    ImageIcon ChargerIcon2F = new ImageIcon(ChargerImg2F);
    ImageIcon ChargerIcon3F = new ImageIcon(ChargerImg3F);
    ImageIcon ChargerIcon4F = new ImageIcon(ChargerImg4F);

    JLabel Charger;
    Timer ChargerTimeSpawn;
    Timer ChargeTimer;
    Timer ChargerDelay;
    boolean start = false;
    boolean willAttack = false;
    boolean isTimerStarted = false;
    int velocity = 10;
    int dy = 0, dx = 0;
    int px, py;
    int pos;
    Random rand;

    public Charger() {
        Charger = new JLabel();
        rand = new Random();
        Charger.setBounds(0,0,48,48);
//        Charger.setBorder(BorderFactory.createLineBorder(Color.RED));
        Charger.setVisible(false);

        ChargerTimeSpawn = new Timer(1500, e -> {
            if (!Charger.isVisible()) {
                randomSpawn();
                Charger.setVisible(true);
                ((Timer)e.getSource()).stop();
            }
        });

        ChargeTimer = new Timer(1500, e -> {
            willAttack = true;
            ChargerDelay.start();
            ((Timer)e.getSource()).stop();
        });

        ChargerDelay = new Timer(2500, e -> {
            if (Charger.isVisible()){
                Charger.setVisible(false);
                ChargerTimeSpawn.start();
                isTimerStarted = false;
                ((Timer)e.getSource()).stop();
            }
        });
    }

    public void update(Player player) {
        if (Charger.isVisible()){
            if (start) {
                if (willAttack) {
                    switch (pos) {
                        case 1:
                            Charger.setLocation(Charger.getX() + velocity, Charger.getY() + velocity);
                            Charger.setIcon(ChargerIcon1F);
                            break;
                        case 2:
                            Charger.setLocation(Charger.getX() + velocity, Charger.getY() - velocity);
                            Charger.setIcon(ChargerIcon3F);
                            break;
                        case 3:
                            Charger.setLocation(Charger.getX() - velocity, Charger.getY() + velocity);
                            Charger.setIcon(ChargerIcon2F);
                            break;
                        case 4:
                            Charger.setLocation(Charger.getX() - velocity, Charger.getY() - velocity);
                            Charger.setIcon(ChargerIcon4F);
                            break;
                    }
                }
            }
        }

        if (start) {
            ChargerTimeSpawn.start();
            isTimerStarted = true;
        }
    }

    public void randomSpawn() {
        willAttack = false;
        pos = rand.nextInt(4) + 1;
        Charger.setVisible(true);
        switch (pos) {
            case 1:
                Charger.setBounds(242,5,48,48);
                Charger.setIcon(ChargerIcon1);
                break;
            case 2:
                Charger.setBounds(242,620,48,48);
                Charger.setIcon(ChargerIcon3);
                break;
            case 3:
                Charger.setBounds(1100,5,48,48);
                Charger.setIcon(ChargerIcon2);
                break;
            case 4:
                Charger.setBounds(1100,620,48,48);
                Charger.setIcon(ChargerIcon4);
                break;
        }
        ChargeTimer.start();
        ChargerDelay.start();
    }

    public void reset() {
        Charger.setVisible(false);
        start = false;
    }
}
