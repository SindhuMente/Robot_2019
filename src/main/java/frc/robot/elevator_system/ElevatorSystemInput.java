package frc.robot.elevator_system;

public class ElevatorSystemInput {
    public final double mainPower;
    public final boolean backUp;
    public final boolean backDown;

    public ElevatorSystemInput(double mainPower, boolean backUp, boolean backDown) {
        this.mainPower = mainPower;
        this.backUp = backUp;
        this.backDown = backDown;
    }

}