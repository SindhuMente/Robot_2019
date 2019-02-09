/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Joystick.ButtonType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.cameraserver.CameraServer;

import frc.robot.drive_system.DriveSystem;
import frc.robot.drive_system.DriveSystemInput;
import frc.robot.drive_system.DriveSystemOutput;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private DifferentialDrive m_myRobot;
  private Joystick m_leftStick;
  private Joystick m_rightStick;
  private SpeedScaler m_speedScaler;
  private DriveSystem m_driveSystem;

  @Override
  public void robotInit() {
    m_myRobot = new DifferentialDrive(new PWMVictorSPX(Config.LEFT_PWM_PORT), new PWMVictorSPX(Config.RIGHT_PWM_PORT));
    m_leftStick = new Joystick(Config.LEFT_JOYSTICK_PORT);
    m_rightStick = new Joystick(Config.RIGHT_JOYSTICK_PORT);
    m_speedScaler = new SpeedScaler(Config.SLOW_SPEED, Config.NORMAL_SPEED, Config.TURBO_SPEED);
    CameraServer.getInstance().startAutomaticCapture();
  }

  @Override
  public void teleopPeriodic() {


    writeDriveSystemOutput(m_driveSystem.process(readDriveSystemInput()));

  }
  public DriveSystemInput readDriveSystemInput() {
    boolean leftTrigger = m_leftStick.getButton(ButtonType.kTrigger);
    boolean rightTrigger = m_rightStick.getButton(ButtonType.kTrigger);
    boolean leftTop = m_leftStick.getButton(ButtonType.kTop);
    boolean rightTop = m_rightStick.getButton(ButtonType.kTop);
    double leftPower = m_leftStick.getY();
    double rightPower = m_rightStick.getY();
    return new DriveSystemInput(leftPower, leftTop, leftTrigger, rightPower, rightTop, rightTrigger);
  }
  public void writeDriveSystemOutput(DriveSystemOutput output) {
    m_myRobot.tankDrive(output.leftPower, output.rightPower);
  }
}
