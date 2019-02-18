package frc.robot.elevator_system;

public class ElevatorSystemInput {
    public final double mainPower;
    public final boolean backUp;
    public final boolean backDown;
    public final boolean forwardLimit;
    public final boolean reverseLimit;

    public ElevatorSystemInput(double mainPower, boolean backUp, boolean backDown, boolean forwardLimit, boolean reverseLimit) {
        this.mainPower = mainPower;
        this.backUp = backUp;
        this.backDown = backDown;
        this.forwardLimit = forwardLimit;
        this.reverseLimit = reverseLimit;
    }

}