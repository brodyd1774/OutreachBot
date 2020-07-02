/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class DriveTrain extends SubsystemBase {

  private CANSparkMax DTLeftNeo, DTLeftNeo2, DTRightNeo, DTRightNeo2;

  public DriveTrain(CANSparkMax leftNeo, CANSparkMax rightNeo, CANSparkMax leftNeo2, CANSparkMax rightNeo2) {
  
    this.DTLeftNeo = leftNeo;
    this.DTRightNeo = rightNeo;
    this.DTLeftNeo2 = leftNeo2;
    this.DTRightNeo2 = rightNeo2;

    this.DTLeftNeo.setInverted(false);
    this.DTRightNeo.setInverted(false);
    this.DTLeftNeo2.setInverted(false);
    this.DTRightNeo2.setInverted(false);

    this.DTLeftNeo2.follow(DTLeftNeo, false);
    this.DTRightNeo2.follow(DTRightNeo, false);

  }

  public void setBothSpeed(double leftPower, double rightPower) {

    DTLeftNeo.set(leftPower);
    DTRightNeo.set(rightPower);

  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
