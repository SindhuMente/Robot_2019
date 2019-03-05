package frc.robot;

public class Config {
    
    // Drive motors

    public static final double SLOW_SPEED = 0.25;
    public static final double NORMAL_SPEED = 0.5;
    public static final double TURBO_SPEED = 1;

    //      Left side

    public static final int LEFT_JOYSTICK_PORT = 0;

    public static final int FRONT_LEFT_PWM_PORT = 0;
    public static final boolean INVERT_FRONT_LEFT = true;

    public static final int BACK_LEFT_PWM_PORT = 1;
    public static final boolean INVERT_BACK_LEFT = true;

    //      Right side

    public static final int RIGHT_JOYSTICK_PORT = 1;

    public static final int FRONT_RIGHT_PWM_PORT = 2;
    public static final boolean INVERT_FRONT_RIGHT = true;

    public static final int BACK_RIGHT_PWM_PORT = 3;
    public static final boolean INVERT_BACK_RIGHT = true;


    // Upper Mechanisms

    public static final int CONTROLLER_JOYSTICK_PORT = 2;

    //      Elevator

    //          Main winch

    public static final int MAIN_WINCH_AXIS_ID = 1;
    public static final int MAIN_WINCH_UPPER_LIMIT_DIO_PORT = 0;
    public static final boolean INVERT_MAIN_WINCH_UPPER_LIMIT = true;
    public static final int MAIN_WINCH_LOWER_LIMIT_DIO_PORT = 1;
    public static final boolean INVERT_MAIN_WINCH_LOWER_LIMIT = true;
    public static final boolean MAIN_WINCH_FORWARD_UP = true;
    public static final int MAIN_WINCH_PORT = 9;
    public static final boolean INVERT_MAIN_WINCH = false;
    public static final double MAIN_WINCH_SCALE = 1;

    //          Back winch

    public static final int BACK_WINCH_UP_BUTTON_ID = 5;
    public static final int BACK_WINCH_DOWN_BUTTON_ID = 7;
    public static final int BACK_WINCH_PORT = 8;
    public static final boolean INVERT_BACK_WINCH = false;
    public static final double BACK_WINCH_SCALE = 1;

    //      Grabber

    //          Hatch claw

    public static final int CLAW_OPEN_BUTTON_ID = 6;
    public static final int CLAW_CLOSE_BUTTON_ID = 8;
    public static final int CLAW_PWM_PORT = 6;
    public static final boolean INVERT_CLAW = false;
    public static final double CLAW_SCALE = 1;

    //          Ball intake roller

    public static final int INTAKE_AXIS_ID = 3;
    public static final int INTAKE_PWM_PORT = 5;
    public static final boolean INVERT_INTAKE = true;
    public static final double INTAKE_SCALE = 1;

}