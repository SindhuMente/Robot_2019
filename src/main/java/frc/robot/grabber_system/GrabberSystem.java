package frc.robot.grabber_system;

public class GrabberSystem {
    private final double m_intakeScaler;
    private final double m_clawScaler;

    public GrabberSystem(double intakeScaler, double clawScaler) {
        m_intakeScaler = intakeScaler;
        m_clawScaler = clawScaler;
    }
    public GrabberSystemOutput process(GrabberSystemInput input) {
        double intakePower = input.intakePower * m_intakeScaler;
        double clawPower;

        if(input.clawOpen) {
            clawPower = m_clawScaler;
        } else {
            clawPower = -m_clawScaler;
        }
        return new GrabberSystemOutput(clawPower, intakePower);
    }
}