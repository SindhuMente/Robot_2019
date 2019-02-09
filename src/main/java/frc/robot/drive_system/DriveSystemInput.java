package frc.robot.drive_system;

public class DriveSystemInput {
    public final double leftPower;
    public final boolean leftSlow;
    public final boolean leftTurbo;
    public final double rightPower;
    public final boolean rightSlow;
    public final boolean rightTurbo;

    public DriveSystemInput(double leftPower, 
                            boolean leftSlow, 
                            boolean leftTurbo, 
                            double rightPower, 
                            boolean rightSlow, 
                            boolean rightTurbo) {
        this.leftPower = leftPower;
        this.leftSlow = leftSlow;
        this.leftTurbo = leftTurbo;
        this.rightPower = rightPower;
        this.rightSlow = rightSlow;
        this.rightTurbo = rightTurbo;

    }
}