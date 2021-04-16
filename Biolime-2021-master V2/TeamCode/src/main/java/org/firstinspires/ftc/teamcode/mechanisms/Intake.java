package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.mechanisms.devicehandlers.*;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.Mechanism;
import org.firstinspires.ftc.teamcode.framework.Constants;

public class Intake extends Mechanism {

    private boolean extended = false;

    private DCMotorHandler motor = new DCMotorHandler("intakeMotor", false, true);
    private ServoHandler servo = new ServoHandler("intakeServo");

    public void init(HardwareMap hwMap) {
        motor.init(hwMap);
        servo.init(hwMap);
    }

    public void setMotorPower(double power) {
        motor.setPower(power);
    }

    public void extend() {
        servo.setPosition(Constants.INTAKE_EXTENDED_POSITION);
        extended = true;
    }

    public void retract() {
        servo.setPosition(Constants.INTAKE_RETRACTED_POSITION);
        extended = false;
    }

    public void setPosition(double pos) {
        servo.setPosition(pos);
    }

    public void toggle()  {
        if (extended) {
            retract();
        } else {
            extend();
        }
    }
}
