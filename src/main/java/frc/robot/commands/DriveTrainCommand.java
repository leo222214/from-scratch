// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DrivetrainSubsystem;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class DrivetrainCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DrivetrainSubsystem m_subsystem;
  private DoubleSupplier m_xSpeedSupplier, m_rotationSpeedSupplier;

  /**
   * Creates a new DrivetrainCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public DrivetrainCommand(DrivetrainSubsystem subsystem, DoubleSupplier xSpeedSupplier, DoubleSupplier rotationSpeedSupplier) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
    m_xSpeedSupplier = xSpeedSupplier;
    m_rotationSpeedSupplier = rotationSpeedSupplier;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double xSpeed = m_xSpeedSupplier.getAsDouble();
    double rotationSpeed = m_rotationSpeedSupplier.getAsDouble();
    m_subsystem.move(xSpeed, rotationSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.move(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
