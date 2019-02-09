package frc.robot.drive_system;

public class DriveSystemOutput {
    public final double leftPower;
    public final double rightPower;

    public DriveSystemOutput(double leftPower, double rightPower) {
        this.leftPower = leftPower;
        this.rightPower = rightPower;
    }
}