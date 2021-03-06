/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Joystick.ButtonType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.cameraserver.CameraServer;

import frc.robot.drive_system.DriveSystem;
import frc.robot.drive_system.DriveSystemInput;
import frc.robot.drive_system.DriveSystemOutput;
import frc.robot.elevator_system.ElevatorSystem;
import frc.robot.elevator_system.ElevatorSystemInput;
import frc.robot.elevator_system.ElevatorSystemOutput;
import frc.robot.grabber_system.GrabberSystem;
import frc.robot.grabber_system.GrabberSystemInput;
import frc.robot.grabber_system.GrabberSystemOutput;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private SpeedControllerGroup m_leftDrive;
  private SpeedControllerGroup m_rightDrive;
  private PWMVictorSPX m_frontLeftDrive;
  private PWMVictorSPX m_frontRightDrive;
  private PWMVictorSPX m_backLeftDrive;
  private PWMVictorSPX m_backRightDrive;
  private DifferentialDrive m_myRobot;
  private PWMTalonSRX m_mainWinch;
  private PWMTalonSRX m_backWinch;
  private PWMVictorSPX m_intake;
  private Spark m_claw;
  private Joystick m_leftStick;
  private Joystick m_rightStick;
  private Joystick m_controller;
  private DigitalInput m_mainWinchUpperLimit;
  private DigitalInput m_mainWinchLowerLimit;
  private SpeedScaler m_speedScaler;
  private DriveSystem m_driveSystem;
  private ElevatorSystem m_elevatorSystem;
  private GrabberSystem m_grabberSystem;

  @Override
  public void robotInit() {
    m_frontLeftDrive = new PWMVictorSPX(Config.FRONT_LEFT_PWM_PORT);
    m_frontRightDrive = new PWMVictorSPX(Config.FRONT_RIGHT_PWM_PORT);
    m_backLeftDrive = new PWMVictorSPX(Config.BACK_LEFT_PWM_PORT);
    m_backRightDrive = new PWMVictorSPX(Config.BACK_RIGHT_PWM_PORT);
    m_leftDrive = new SpeedControllerGroup(m_frontLeftDrive, m_backLeftDrive);
    m_rightDrive = new SpeedControllerGroup(m_frontRightDrive, m_backRightDrive);
    m_myRobot = new DifferentialDrive(m_leftDrive, m_rightDrive);
    m_mainWinch = new PWMTalonSRX(Config.MAIN_WINCH_PORT);
    m_claw = new Spark(Config.CLAW_PWM_PORT);
    m_intake = new PWMVictorSPX(Config.INTAKE_PWM_PORT);
    m_backWinch = new PWMTalonSRX(Config.BACK_WINCH_PORT);

    m_mainWinchUpperLimit = new DigitalInput(Config.MAIN_WINCH_UPPER_LIMIT_DIO_PORT);
    m_mainWinchLowerLimit = new DigitalInput(Config.MAIN_WINCH_LOWER_LIMIT_DIO_PORT);

    m_leftStick = new Joystick(Config.LEFT_JOYSTICK_PORT);
    m_rightStick = new Joystick(Config.RIGHT_JOYSTICK_PORT);
    m_controller = new Joystick(Config.CONTROLLER_JOYSTICK_PORT);
    m_speedScaler = new SpeedScaler(Config.SLOW_SPEED, Config.NORMAL_SPEED, Config.TURBO_SPEED);
    m_driveSystem = new DriveSystem(m_speedScaler);
    m_elevatorSystem = new ElevatorSystem(Config.MAIN_WINCH_SCALE, Config.BACK_WINCH_SCALE);
    m_grabberSystem = new GrabberSystem(Config.INTAKE_SCALE, Config.CLAW_SCALE);

    m_frontLeftDrive.setInverted(Config.INVERT_FRONT_LEFT);
    m_frontRightDrive.setInverted(Config.INVERT_FRONT_RIGHT);
    m_backLeftDrive.setInverted(Config.INVERT_BACK_LEFT);
    m_backRightDrive.setInverted(Config.INVERT_BACK_RIGHT);
    m_mainWinch.setInverted(Config.INVERT_MAIN_WINCH);
    m_claw.setInverted(Config.INVERT_CLAW);
    m_backWinch.setInverted(Config.INVERT_BACK_WINCH);
    m_intake.setInverted(Config.INVERT_INTAKE);

    CameraServer.getInstance().startAutomaticCapture(0);
    CameraServer.getInstance().startAutomaticCapture(1);
  }

  @Override
  public void teleopPeriodic() {


    writeDriveSystemOutput(m_driveSystem.process(readDriveSystemInput()));
    writeElevatorSystemOutput(m_elevatorSystem.process(readElevatorSystemInput()));
    writeGrabberSystemOutput(m_grabberSystem.process(readGrabberSystemInput()));

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
    System.out.printf("Setting drive power to left %f right %f%n", output.leftPower, output.rightPower);
    m_myRobot.tankDrive(output.leftPower, output.rightPower);
  }
  public ElevatorSystemInput readElevatorSystemInput() {
    double mainWinchPower = m_controller.getRawAxis(Config.MAIN_WINCH_AXIS_ID);
    boolean backWinchUp = m_controller.getRawButton(Config.BACK_WINCH_UP_BUTTON_ID);
    boolean backWinchDown = m_controller.getRawButton(Config.BACK_WINCH_DOWN_BUTTON_ID);
    boolean upperLimit = Config.INVERT_MAIN_WINCH_UPPER_LIMIT ^ m_mainWinchUpperLimit.get();
    boolean lowerLimit = Config.INVERT_MAIN_WINCH_LOWER_LIMIT ^ m_mainWinchLowerLimit.get();
    boolean forwardLimit = Config.MAIN_WINCH_FORWARD_UP ? upperLimit : lowerLimit;
    boolean reverseLimit = Config.MAIN_WINCH_FORWARD_UP ? lowerLimit : upperLimit;


    return new ElevatorSystemInput(mainWinchPower, backWinchUp, backWinchDown, forwardLimit, reverseLimit);
  }
  public void writeElevatorSystemOutput(ElevatorSystemOutput output) {
    System.out.printf("Setting winch power to main %f back %f%n", output.mainPower, output.backPower);
    m_mainWinch.set(output.mainPower);
    m_backWinch.set(output.backPower);
  }
  public GrabberSystemInput readGrabberSystemInput () {
    boolean clawOpen = m_controller.getRawButton(Config.CLAW_OPEN_BUTTON_ID);
    boolean clawClose = m_controller.getRawButton(Config.CLAW_CLOSE_BUTTON_ID); 
    double intakePower = m_controller.getRawAxis(Config.INTAKE_AXIS_ID);
    return new GrabberSystemInput(clawOpen, clawClose, intakePower);
  }
  public void writeGrabberSystemOutput (GrabberSystemOutput output) {
    System.out.printf("Setting grabber power to intake %f claw %f%n", output.intakePower, output.clawPower);
    m_intake.set(output.intakePower);
    m_claw.set(output.clawPower);
  }
}
