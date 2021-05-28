// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DrivetrainSubsystem extends SubsystemBase {
  private DifferentialDrive m_differentialDrive;
  private double m_xSpeed;
  private double m_rotationSpeed; 
  
  /** Creates a new ExampleSubsystem. */
  public DrivetrainSubsystem(SpeedControllerGroup leftMotors, SpeedControllerGroup rightMotors) {
    m_differentialDrive = new DifferentialDrive(leftMotors, rightMotors);
    
  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    m_differentialDrive.arcadeDrive(m_xSpeed, m_rotationSpeed);    
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void move(double xSpeed, double rotationSpeed) {
    m_xSpeed = xSpeed;
    m_rotationSpeed = rotationSpeed;
  }
}
