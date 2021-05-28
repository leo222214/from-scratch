// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.DoubleSupplier;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.commands.DrivetrainCommand;
import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DrivetrainSubsystem m_drivetrainSubsystem;

  private final DrivetrainCommand m_drivetrainCommand;

  private final SpeedControllerGroup m_leftMotors, m_rightMotors;

  private final XboxController m_xboxController;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    m_rightMotors = new SpeedControllerGroup(new WPI_TalonSRX(Constants.BOTTOM_RIGHT_DRIVE_PORT), new WPI_TalonSRX(Constants.TOP_RIGHT_DRIVE_PORT));
    m_leftMotors = new SpeedControllerGroup(new WPI_TalonSRX(Constants.BOTTOM_LEFT_DRIVE_PORT), new WPI_TalonSRX(Constants.TOP_LEFT_DRIVE_PORT));

    m_xboxController = new XboxController(0);
    DoubleSupplier xSpeedSupplier = () -> m_xboxController.getY(Hand.kLeft);
    DoubleSupplier xRotationSpeed = () -> m_xboxController.getX(Hand.kRight);

    m_drivetrainSubsystem = new DrivetrainSubsystem(m_leftMotors, m_rightMotors);
    m_drivetrainCommand = new DrivetrainCommand(m_drivetrainSubsystem, xSpeedSupplier, xRotationSpeed);

    m_drivetrainSubsystem.setDefaultCommand(m_drivetrainCommand);



    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An DrivetrainCommand will run in autonomous
    return null;
  }
}
