package org.firstinspires.ftc.teamcode.framework.util.Odometry;


import java.util.stream.DoubleStream;

public class MecanumDriveWheelSpeeds {
    public double frontLeftInchesPerSecond;
    public double frontRightInchesPerSecond;
    public double rearLeftInchesPerSecond;
    public double rearRightInchesPerSecond;

    public MecanumDriveWheelSpeeds() {}

    public MecanumDriveWheelSpeeds(double frontLeftInchesPerSecond, double frontRightInchesPerSecond, double rearLeftInchesPerSecond, double rearRightInchesPerSecond) {
        this.frontLeftInchesPerSecond = frontLeftInchesPerSecond;
        this.frontRightInchesPerSecond = frontRightInchesPerSecond;
        this.rearLeftInchesPerSecond = rearLeftInchesPerSecond;
        this.rearRightInchesPerSecond = rearRightInchesPerSecond;
    }

//Commented out because this app doesn't have the required API level perms for android (I don't completely know how it works, but it doesn't let me use DoubleStream)
//    public void normalize(double attainableMaxSpeedInchesPerSecond) {
//        double realMaxSpeed =
//                DoubleStream.of(
//                        frontLeftInchesPerSecond,
//                        frontRightInchesPerSecond,
//                        rearLeftInchesPerSecond,
//                        rearRightInchesPerSecond)
//                        .max()
//                        .getAsDouble();
//
//        if (realMaxSpeed > attainableMaxSpeedMetersPerSecond) {
//            frontLeftMetersPerSecond =
//                    frontLeftMetersPerSecond / realMaxSpeed * attainableMaxSpeedMetersPerSecond;
//            frontRightMetersPerSecond =
//                    frontRightMetersPerSecond / realMaxSpeed * attainableMaxSpeedMetersPerSecond;
//            rearLeftMetersPerSecond =
//                    rearLeftMetersPerSecond / realMaxSpeed * attainableMaxSpeedMetersPerSecond;
//            rearRightMetersPerSecond =
//                    rearRightMetersPerSecond / realMaxSpeed * attainableMaxSpeedMetersPerSecond;
//        }
//    }
}
