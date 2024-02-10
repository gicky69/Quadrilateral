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

    boolean isShooting = false;
    Random rand;
    int Direction;
    Timer BeamTimer;
    Timer ShooterTimer;
    Timer ShooterCooldown;
    Timer ShooterSpawn;


    public Beams() {
        Beam = new JLabel();
        Shooter = new JLabel();
        rand = new Random();
        ShootingArea = new JLabel();

        ShootingArea.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        Shooter.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        Beam.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

        ShootingArea.setBounds(150,150,500,350);

        Shooter.setVisible(false);
        Beam.setVisible(false);

        spawn();
    }

    public void spawn() {
        Shooter.setVisible(true);

        x = rand.nextInt(735) + 32;
        y = rand.nextInt(608) + 32;

        direction = rand.nextInt(4) + 1;

        Shooter.setBounds(x,y,32,32);

        if (Shooter.isVisible()){
            ShooterTimer = new Timer(1000, e -> {
                isShooting = true;
                shoot(direction);
            });
            ShooterTimer.start();
        }
    }

    public void shoot(int dir) {
        switch(dir) {
            case 1:
                BeamTimer = new Timer(700, e -> {
                    Beam.setVisible(true);
                    Beam.setBounds(Shooter.getX()+32,Shooter.getY(), 300, 32);
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
                    Beam.setBounds(Shooter.getX()-300,Shooter.getY(), 300, 32);
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
                    Beam.setBounds(Shooter.getX(), Shooter.getY() + 32, 32, 300);
                });
                BeamTimer.setRepeats(false);
                BeamTimer.start();
                break;
            case 4:
                BeamTimer = new Timer(700, e -> {
                    Beam.setVisible(true);
                    Beam.setBounds(Shooter.getX(), Shooter.getY() - 300, 32, 300);
                });
                BeamTimer.setRepeats(false);
                BeamTimer.start();
                break;
        }

    }
}
