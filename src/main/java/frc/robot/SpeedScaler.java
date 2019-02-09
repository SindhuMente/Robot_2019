package frc.robot;

public class SpeedScaler {
    public SpeedScaler(double slowScaler, double normalScaler, double turboScaler) {
        m_slowScaler = slowScaler;
        m_normalScaler = normalScaler;
        m_turboScaler = turboScaler;
    }
    double m_slowScaler;
    double m_normalScaler;
    double m_turboScaler;
    public double speedForMode(boolean turbo, boolean slow) {
        if(turbo) {
          return 1;
        } else if(slow) {
          return 0.25;
        } else {
          return 0.5;
        }
      }
}