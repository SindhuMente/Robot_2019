package frc.robot.elevator_system;

public class ElevatorSystem {
    private final double m_mainScale;
    private final double m_backScale;
    public ElevatorSystem(double mainScale, double backScale) {
        m_mainScale = mainScale;
        m_backScale = backScale;
    }
    public ElevatorSystemOutput process(ElevatorSystemInput input) {
        double mainPower = input.mainPower * m_mainScale;
        double backPower;

        if(input.backUp) {
            backPower = m_backScale;
        } else if(input.backDown) {
            backPower = -m_backScale;
        } else {
            backPower = 0;
        }
        return new ElevatorSystemOutput(mainPower, backPower);
    }
}