package org.firstinspires.ftc.teamcode.framework;

/*
  <------------INCLUDE LINE - NONSTANDARD-------------------------------->
    import static org.firstinspires.ftc.teamcode.framework.Constants.*;
  <---------------------------------------------------------------------->
*/

//This is where you'll put all the constants for your mechanisms and stuff. The framework constants are for Olympia and Olympia **ONLY**, you shouldn't touch them.
public class Constants {
    //Drivetrain modifiers
    public static final double kfrontWheelModifier = 0.8;
    public static final double kStrafeModifier = 1;

    //Intake positions
    public static final double INTAKE_EXTENDED_POSITION = 0.8;
    public static final double INTAKE_RETRACTED_POSITION = 0.2;
    public static final double kIntakeRunPower = 1;
    public static final double kIntakeReversePower = -1;

    //Storage positions
    public static final double STORAGE_EXTENDED_POSITION = 0.07;
    public static final double STORAGE_RETRACED_POSITION = 0.81;
    public static final double kStorageIdlePower = 0.4;
    public static final double kStorageRunPower = 1;
    public static final double kStorageShootPower = 0.8;
    public static final double kStorageReversePower = -0.3;

    //Shooter Constants:
    public static final double kShooterLvl3Rpm = 0.8;
    public static final double kShooterPwrshtRpm = 0.74;
    public static final double kShooterRPMAdjustment = 0.02;
    public static final double kShootAngle = -13.5;

    public static final double kShooterP = 0;
    public static final double kShooterI = 0;
    public static final double kShooterD = 0;
    public static final double kShooterF = 0;

    public enum ShooterProfiles {
        LVL3,
        POWERSHOT
    }

    public static final double WOBBLE_OPEN_POSITION = 0;
    public static final double WOBBLE_CLOSED_POSITION = 1;

    //For the getDistance method:
    public static final double kDrivetrainWheelDiameter = 96/25.4; //in
    public static final double kYJacketTicksPerRevolution = (1+(46/17))*(1+(46/11))*28*-0.98;
    
    //Angle PID Values:
    public static final double kAngleP = 0.04;
    public static final double kAngleI = 0;
    public static final double kAngleD = 0;

    //IsFinished thresholds:
    public static final double kAngleFinishedThreshold = 1;
    public static final double kDistanceFinishedThreshold = 1;

    //Distance PID Values:
    public static final double kDistanceP = 0.1;
    public static final double kDistanceI = 0;
    public static final double kDistanceD = 0;

    //Offset PID Values:
    public static final double x_offset_kP = 0.04;
    public static final double x_offset_kI = 0;
    public static final double x_offset_kD = 0;
}
