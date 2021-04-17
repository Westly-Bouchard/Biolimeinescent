package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.mechanisms.devicehandlers.*;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.Mechanism;
import org.firstinspires.ftc.teamcode.framework.Constants;

public class Intake extends Mechanism {

    private boolean extended = false;

    private DCMotorHandler motor = new DCMotorHandler("intakeMotor", false, true);
    private ServoHandler leftServo = new ServoHandler("leftIntakeServo");
    private ServoHandler rightServo = new ServoHandler("rightIntakeServo");

    public void init(HardwareMap hwMap) {
        motor.init(hwMap);
        leftServo.init(hwMap);
        rightServo.init(hwMap);
    }

    public void setMotorPower(double power) {
        motor.setPower(power);
    }

    public void extend() {
        setPosition(Constants.INTAKE_EXTENDED_POSITION);
        extended = true;
    }

    public void retract() {
        setPosition(Constants.INTAKE_RETRACTED_POSITION);
        extended = false;
    }

    public void setPosition(double pos) {
        leftServo.setPosition(pos);
        rightServo.setPosition(1 - pos);
    }

    public void toggle()  {
        if (extended) {
            retract();
        } else {
            extend();
        }
    }
}
