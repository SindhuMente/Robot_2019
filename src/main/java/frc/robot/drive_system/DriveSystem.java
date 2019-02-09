package frc.robot.drive_system;
import frc.robot.SpeedScaler; 

public class DriveSystem { 
    SpeedScaler m_speedScaler;
    public DriveSystem(SpeedScaler speedScaler) {
        m_speedScaler = speedScaler;
    }

    public DriveSystemOutput process(DriveSystemInput input) {
        double leftPower = m_speedScaler.speedForMode(input.leftTurbo, input.leftSlow)*input.leftPower;
        double rightPower = m_speedScaler.speedForMode(input.rightTurbo, input.rightSlow)*input.rightPower;
        return new DriveSystemOutput(leftPower, rightPower);
    }
}