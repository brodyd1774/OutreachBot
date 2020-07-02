package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase{
    
    private TalonSRX ShooterTalon;
    private VictorSPX ShooterVictor;

    public Shooter(TalonSRX ShooterTalon, VictorSPX ShooterVictor) {
        this.ShooterTalon = ShooterTalon;
        this.ShooterVictor = ShooterVictor;

        this.ShooterTalon.setInverted(false);
        this.ShooterVictor.setInverted(false);

        this.ShooterVictor.follow(ShooterTalon);
    }

    public void setSpeed(double speed) {

        ShooterTalon.set(speed);

    }

    @Override
    public void periodic(){
        setSpeed(1);
    }
}