package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.framework.Constants;
import org.firstinspires.ftc.teamcode.mechanisms.devicehandlers.DCMotorHandler;
import org.firstinspires.ftc.teamcode.mechanisms.devicehandlers.ServoHandler;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.Mechanism;

public class Storage extends Mechanism {

    private boolean blockerPos = false;

    private DCMotorHandler motor = new DCMotorHandler("storageMotor", true, true);
    private ServoHandler servo = new ServoHandler("storageServo");

    public void init(HardwareMap hwMap) {
        motor.init(hwMap);
        servo.init(hwMap);
    }

    public void setMotorPower(double power) {
        motor.setPower(power);
    }

    public void extend() {
        servo.setPosition(Constants.STORAGE_EXTENDED_POSITION);
        blockerPos = true;
    }

    public void retract() {
        servo.setPosition(Constants.STORAGE_RETRACED_POSITION);
        blockerPos = false;
    }

    public void toggle() {
        if (blockerPos) {
            retract();
        } else {
            extend();
        }
    }
}
