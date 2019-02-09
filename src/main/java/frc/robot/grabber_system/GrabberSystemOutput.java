package frc.robot.grabber_system;


public class GrabberSystemOutput {
    public final double clawPower;
    public final double intakePower;

    public GrabberSystemOutput(double clawPower, double intakePower) {
        this.clawPower = clawPower;
        this.intakePower = intakePower;
    }
}