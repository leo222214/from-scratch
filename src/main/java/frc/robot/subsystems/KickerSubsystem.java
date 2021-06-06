// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class KickerSubsystem extends SubsystemBase {
  private PIDController m_PIDController;
  private SpeedController m_kicker;
  private KickerState m_kickerState;
  private AnalogPotentiometer m_analogPotentiometer;

  enum KickerState {
    WaitingToKick,
    Extending,
    Retracting;
  }

  /** Creates a new KickerSubsystem. */
  public KickerSubsystem(WPI_TalonSRX kicker) {
    m_kicker = kicker;
    m_PIDController = new PIDController(0.06, 0, 0);
    m_analogPotentiometer = new AnalogPotentiometer(0, 72);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    switch (m_kickerState) {
      case WaitingToKick:
        break;
      case Extending:
        m_PIDController.setSetpoint(52);
        if (m_PIDController.atSetpoint()) {
          m_kickerState = KickerState.Retracting;
        }
        break;
      case Retracting:
        m_PIDController.setSetpoint(37);
        if (m_PIDController.atSetpoint()) {
          m_kickerState = KickerState.WaitingToKick;
        }
        break;
    }

    double position = m_analogPotentiometer.get();
    m_kicker.set(m_PIDController.calculate(position));
  }

  public void kick() {
    m_kickerState = KickerState.Extending;


  }
}

