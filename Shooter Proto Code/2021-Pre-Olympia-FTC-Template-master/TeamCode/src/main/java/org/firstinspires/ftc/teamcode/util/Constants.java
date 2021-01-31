package org.firstinspires.ftc.teamcode.util;

//This is the class where you define all of the constants for tuning and stuff.
//For example, maybe you have a maximum motor speed, or a tuning value.
//Perhaps a scalar for some servo moving.
//IDK, you do you. Just put all of the *constant* values that don't change in here.

/*
  <------------INCLUDE LINE - NONSTANDARD--------------------------->
    import static org.firstinspires.ftc.teamcode.util.Constants.*;
  <----------------------------------------------------------------->
*/

public class Constants {
    //Section 1 (comment roughly where each cluster of variables is used
    //makes them easier to look for later, trust me.)
    public static final int THE_FUNNY_NUMBER = 420;
    public static final int THE_OTHER_FUNNY_NUMBER = 69;

    //public means globally available to the whole filesystem
    //static means only one of this variable exists
    //final means this variable cannot be changed, this is the final iteration of it
    //int means it's an integer

    //ExampleServo values
    public static final double EXAMPLE_SERVO_OPEN_POSITION = 0.0;
    public static final double EXAMPLE_SERVO_CLOSED_POSITION = 1;

    //ExampleMotor values
    public static final double EXAMPLE_MOTOR_MAX_SPEED = 1;

    //Joystick values
    public static final double CONTROLLER_1_DEADZONE = 0.05; //5% deadzone
    public static final double CONTROLLER_2_DEADZONE = 0.05; //5% deadzone

    public static final int X_AXIS = 0; //Just an ID for axes, use this number for DeadzonedAxis.
    public static final int Y_AXIS = 1;

}
