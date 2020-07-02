/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private final CANSparkMax CAN10 = new CANSparkMax(10);
  private final CANSparkMax CAN11 = new CANSparkMax(11);
  private final CANSparkMax CAN12 = new CANSparkMax(12);
  private final CANSparkMax CAN13 = new CANSparkMax(12);

  private final TalonSRX CAN20 = new TalonSRX(20);
  private final VictorSPX CAN21 = new VictorSPX(21);

  private final DriveTrain driveTrain = new DriveTrain(CAN10, CAN11, CAN12, CAN13);
  private final Shooter Shooter = new Shooter(CAN20, CAN21);

  private final XboxController driver = new XboxController(0);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    XboxControllerTrigger leftY = new XboxControllerTrigger(driver, XboxController.Axis.kLeftY.value);
    XboxControllerTrigger rightX = new XboxControllerTrigger(driver, XboxController.Axis.kRightX.value);

    XboxControllerTrigger dLT = new XboxControllerTrigger(driver, XboxController.Axis.kLeftTrigger.value);

    dLT.toggleWhenPressed(Shooter);

    leftY.or(rightX)
      .whileActiveContinuous(() -> sriveTrain.setBothSpeed( 
        -driver.getRawAxis(XboxController.Axis.kLeftY.value) - driver.getRawAxis(XboxController.Axis.kRightX.value),
        -driver.getRawAxis(XboxController.Axis.kLeftY.value) + driver.getRawAxis(XboxController.Axis.kRightX.value)),
        driveTrain)
        .whenInactive(() -> driveTrain.setBothSpeed(0,0), driveTrain);
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
   
  }
}
