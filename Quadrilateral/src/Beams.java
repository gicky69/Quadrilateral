import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Beams {
    JLabel Shooter;
    JLabel Beam;
    JLabel ShootingArea;

    int x;
    int y;
    int direction;
    boolean spawn = false;
    boolean isShooting = false;
    Random rand;
    Timer BeamTimer;
    Timer ShooterTimer;

    public Beams() {
        Beam = new JLabel();
        Shooter = new JLabel();
        rand = new Random();
        ShootingArea = new JLabel();

        ShootingArea.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        Shooter.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        Beam.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

        ShootingArea.setBounds(410,335,10,10);

        Shooter.setVisible(false);
        Beam.setVisible(false);

        ShooterTimer = new Timer(1000, e -> {
            isShooting = true;
            shoot(direction);
        });

        spawn();
    }

    public void update(GamePanel GP){
    }

    public void spawn() {
        Shooter.setVisible(true);
        Beam.setVisible(false);

        x = rand.nextInt(735) + 32;
        y = rand.nextInt(608) + 32;

        Shooter.setBounds(x,y,32,32);

        // if Shooter Spawn in Top
        if (Shooter.getX() > ShootingArea.getX()){
            direction = 2;
        }

        else if (Shooter.getX() < ShootingArea.getX()){
            direction = 1;
        }

        // If Shooter spawn in Bottom
        if (Shooter.getY() > ShootingArea.getY()){
            direction = 4;
        }

        else if (Shooter.getY() < ShootingArea.getY()){
            direction = 3;
        }

        // If Shooter spawn in Top Left
        if (Shooter.getX() < ShootingArea.getX() && Shooter.getY() < ShootingArea.getY()){
            direction = 1;
        }

        // If Shooter spawn in Top Right
        if (Shooter.getX() > ShootingArea.getX() && Shooter.getY() < ShootingArea.getY()){
            direction = 2;
        }

        // If Shooter spawn in Bottom Left
        if (Shooter.getX() < ShootingArea.getX() && Shooter.getY() > ShootingArea.getY()){
            direction = 1;
        }

        // If Shooter spawn in Bottom Right
        if (Shooter.getX() > ShootingArea.getX() && Shooter.getY() > ShootingArea.getY()){
            direction = 2;
        }
    }

    public void shoot(int dir) {
        switch(dir) {
            case 1:
                BeamTimer = new Timer(700, e -> {
                    Beam.setVisible(true);
                    Beam.setBounds(Shooter.getX()+32,Shooter.getY(), 350, 32);
//                    if (Beam.getX() > 800) {
//                        BeamTimer.stop();
//                        Beam.setVisible(false);
//                    }
                });
                BeamTimer.setRepeats(false);
                BeamTimer.start();
                break;
            case 2:
                BeamTimer = new Timer(700, e -> {
                    Beam.setVisible(true);
                    Beam.setBounds(Shooter.getX()-350,Shooter.getY(), 350, 32);
//                    if (Beam.getX() < 0) {
//                        BeamTimer.stop();
//                        Beam.setVisible(false);
//                    }
                });
                BeamTimer.setRepeats(false);
                BeamTimer.start();
                break;
            case 3:
                BeamTimer = new Timer(700, e -> {
                    Beam.setVisible(true);
                    Beam.setBounds(Shooter.getX(), Shooter.getY() + 32, 32, 350);
                });
                BeamTimer.setRepeats(false);
                BeamTimer.start();
                break;
            case 4:
                BeamTimer = new Timer(700, e -> {
                    Beam.setVisible(true);
                    Beam.setBounds(Shooter.getX(), Shooter.getY() - 350, 32, 350);
                });
                BeamTimer.setRepeats(false);
                BeamTimer.start();
                break;
        }

        isShooting = false;
        spawn = false;
    }
}
