package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.framework.Constants;
import org.firstinspires.ftc.teamcode.framework.util.Maths.Rotation2d;
import org.firstinspires.ftc.teamcode.framework.util.Maths.Pose2d;
import org.firstinspires.ftc.teamcode.framework.util.Maths.Translation2d;
import org.firstinspires.ftc.teamcode.framework.util.Odometry.MecanumDriveKinematics;
import org.firstinspires.ftc.teamcode.framework.util.Odometry.MecanumDriveOdometry;
import org.firstinspires.ftc.teamcode.framework.util.Odometry.MecanumDriveWheelSpeeds;
import org.firstinspires.ftc.teamcode.mechanisms.devicehandlers.DCMotorWithEncoderHandler;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.Mechanism;

public class OdometryDriveTrain extends Mechanism {

    private BNO055IMU imu;

    private DCMotorWithEncoderHandler frontL = new DCMotorWithEncoderHandler("frontL", false, true);
    private DCMotorWithEncoderHandler frontR = new DCMotorWithEncoderHandler("frontL", false, true);
    private DCMotorWithEncoderHandler rearL = new DCMotorWithEncoderHandler("backL", false, true);
    private DCMotorWithEncoderHandler rearR = new DCMotorWithEncoderHandler("backR", false, true);

    private double currentHeading, lastHeading, trueHeading, headingChange;
    private double previousTime = 0;

    private Pose2d position;

    private double strafeSpeed = 0;
    private double speed = 0;

    //Locations (in inches) of the wheels from the center of the robot:
    private Translation2d frontLeftLocation = new Translation2d(0, 0);
    private Translation2d frontRightLocation = new Translation2d(0, 0);
    private Translation2d rearLeftLocation = new Translation2d(0, 0);
    private Translation2d rearRightLocation = new Translation2d(0, 0);

    private MecanumDriveWheelSpeeds wheelSpeeds;

    private MecanumDriveKinematics kinematics = new MecanumDriveKinematics(frontLeftLocation, frontRightLocation, rearLeftLocation, rearRightLocation);

    private MecanumDriveOdometry odometry = new MecanumDriveOdometry(kinematics, Rotation2d.fromDegrees(getTrueHeading()));

    public void init(HardwareMap hwMap) {
        frontL.init(hwMap);
        frontR.init(hwMap);
        rearL.init(hwMap);
        rearR.init(hwMap);

        position = new Pose2d(new Translation2d(0, 0), new Rotation2d(0));

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled = false;
        parameters.mode = BNO055IMU.SensorMode.IMU;

        imu = hwMap.get(BNO055IMU.class, "imu");

        imu.initialize(parameters);
    }

    public void setSpeeds(double fL, double fR, double bL, double bR) {
        double largest = 1.0;

        largest = Math.max(largest, Math.abs(fL));
        largest = Math.max(largest, Math.abs(fR));
        largest = Math.max(largest, Math.abs(bL));
        largest = Math.max(largest, Math.abs(bR));

        frontL.setPower(fL / largest);
        frontR.setPower(fR / largest);
        rearL.setPower(bL / largest);
        rearR.setPower(bR / largest);
    }

    public void driveAll(double fSpeed, double sSpeed, double rSpeed) {
        setSpeeds((fSpeed + sSpeed + rSpeed), (fSpeed - sSpeed - rSpeed), (fSpeed - sSpeed + rSpeed), (fSpeed + sSpeed - rSpeed));
        speed = fSpeed;
        strafeSpeed = sSpeed;
    }

    public void driveShoot(double fSpeed, double sSpeed) {
        setSpeeds(fSpeed + sSpeed, fSpeed - sSpeed, fSpeed - sSpeed, fSpeed + sSpeed);
    }

    public double getHeading() {
        return -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
    }

    public void calculateOdometry(double currentTime) {
        double flInchPerSec = frontL.getEncoderValue() * Constants.kDrivetrainWheelDiameter * Math.PI / Constants.kYJacketTicksPerRevolution / (currentTime - previousTime);
        double frInchPerSec = frontR.getEncoderValue() * Constants.kDrivetrainWheelDiameter * Math.PI / Constants.kYJacketTicksPerRevolution / (currentTime - previousTime);
        double rlInchPerSec = rearL.getEncoderValue() * Constants.kDrivetrainWheelDiameter * Math.PI / Constants.kYJacketTicksPerRevolution / (currentTime - previousTime);
        double rrInchPerSec = rearR.getEncoderValue() * Constants.kDrivetrainWheelDiameter * Math.PI / Constants.kYJacketTicksPerRevolution / (currentTime - previousTime);

        this.wheelSpeeds = new MecanumDriveWheelSpeeds(flInchPerSec, frInchPerSec, rlInchPerSec, rrInchPerSec);

        position = odometry.updateWithTime(currentTime, new Rotation2d(getTrueHeading()), wheelSpeeds);
        previousTime = currentTime;
    }

    public void adjustTrueHeading() { // To be called periodically in the OpMode
        currentHeading = getHeading();
        headingChange = currentHeading - lastHeading;
        trueHeading += headingChange;
        if (headingChange > 180) {
            trueHeading -= 360;
        } else if (headingChange < -180) {
            trueHeading += 360;
        }
        lastHeading = currentHeading;
    }

    public double getTrueHeading() {
        return trueHeading;
    }
    public double getX() { return position.getX(); }
    public double getZ() { return position.getY(); }
    public void setXZ(double x, double z) { odometry.resetPosition(new Pose2d(x, z, new Rotation2d()), new Rotation2d(getTrueHeading())); }
    public double getStrafeSpeed() { return strafeSpeed; }
    public double getSpeed() { return speed; }
}
