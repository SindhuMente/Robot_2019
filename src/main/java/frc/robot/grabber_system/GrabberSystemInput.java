package frc.robot.grabber_system;

public class GrabberSystemInput {
    public final boolean clawOpen;
    public final double intakePower;

    public GrabberSystemInput(boolean clawOpen, double intakePower) {
        this.clawOpen = clawOpen;
        this.intakePower = intakePower;
    }
}