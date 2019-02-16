package frc.robot.grabber_system;

public class GrabberSystemInput {
    public final boolean clawOpen;
    public final boolean clawClose;
    public final double intakePower;

    public GrabberSystemInput(boolean clawOpen, boolean clawClose, double intakePower) {
        this.clawOpen = clawOpen;
        this.clawClose = clawClose;
        this.intakePower = intakePower;
    }
}