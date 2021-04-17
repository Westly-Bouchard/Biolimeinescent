package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.hardware.bosch.BNO055IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.mechanisms.devicehandlers.DCMotorWithEncoderHandler;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.Mechanism;
import org.firstinspires.ftc.teamcode.framework.Constants;

public class DriveTrain extends Mechanism {

    private BNO055IMU imu;

    private double left_distance, right_distance, starting_distance, previous_distance;
    double x_position, z_position, linear_displacement;

    private double left_speed = 0;
    private double right_speed = 0;

    private double currentHeading, lastHeading, trueHeading, headingChange;

    DCMotorWithEncoderHandler frontL = new DCMotorWithEncoderHandler ("frontL", true, true);
    DCMotorWithEncoderHandler frontR = new DCMotorWithEncoderHandler ("frontR", false, true);
    DCMotorWithEncoderHandler backL = new DCMotorWithEncoderHandler ("backL", true, true);
    DCMotorWithEncoderHandler backR = new DCMotorWithEncoderHandler ("backR", false, true);


    public void init(HardwareMap hwMap) {
        frontL.init(hwMap);
        frontR.init(hwMap);
        backL.init(hwMap);
        backR.init(hwMap);

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
        backL.setPower(bL / largest);
        backR.setPower(bR / largest);
    }

    public void driveAll(double fSpeed, double sSpeed, double rSpeed) {
        left_speed = fSpeed;
        right_speed = fSpeed;
        setSpeeds((fSpeed + Constants.kfrontWheelModifier * sSpeed + rSpeed), (fSpeed - Constants.kfrontWheelModifier * sSpeed - rSpeed), (fSpeed - sSpeed + rSpeed), (fSpeed + sSpeed - rSpeed));
    }

    public void tankDrive(double lSpeed, double rSpeed) {
        setSpeeds(lSpeed, rSpeed, lSpeed, rSpeed);
        left_speed = lSpeed;
        right_speed = rSpeed;
    }

    public double getSpeed() {
        return (left_speed + right_speed) / 2;
    }

    public double getHeading() {
        return -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
    }

    public void setTrueHeading(double heading){
        this.trueHeading = heading;
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

    public void setLastHeading() {
        lastHeading = getHeading();
    }

    public double getTrueHeading() {
        return trueHeading;
    }

    public double getDistance() {
        left_distance = (frontL.getEncoderValue() + backL.getEncoderValue()) / 2;
        right_distance = -(frontR.getEncoderValue() + backR.getEncoderValue()) / 2;
        return ((left_distance + right_distance) / 2) * Constants.kDrivetrainWheelDiameter * Math.PI / Constants.kYJacketTicksPerRevolution;
    }


    public void incrementXZ() {
        if (getTrueHeading() - lastHeading == 0) {
            linear_displacement = getDistance() - previous_distance;
        } else {
            linear_displacement = 2 * (getDistance() - previous_distance) * Math.abs(Math.sin((getTrueHeading() - lastHeading) * Math.PI / 360) / ((getTrueHeading() - lastHeading) * Math.PI / 180));
        }

        x_position += Math.sin((getTrueHeading() + lastHeading) * Math.PI / 360) * linear_displacement;
        z_position += Math.cos((getTrueHeading() + lastHeading) * Math.PI / 360) * linear_displacement;

        previous_distance = getDistance();
        lastHeading = getTrueHeading();
    }


    public void setXZ(double x_input, double z_input) {
        x_position = x_input;
        z_position = z_input;
    }

    public double getX() {
        return x_position;
    }

    public double getZ() {
        return z_position;
    }
}
