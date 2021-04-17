package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.framework.Constants;
import org.firstinspires.ftc.teamcode.mechanisms.devicehandlers.DCMotorHandler;
import org.firstinspires.ftc.teamcode.mechanisms.devicehandlers.ServoHandler;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.Mechanism;

public class Wobble extends Mechanism {

    private boolean open = false;

    private ServoHandler servo = new ServoHandler("wobbleServo");

    public void init(HardwareMap hwMap) {
        servo.init(hwMap);
    }

    public void open() {
        servo.setPosition(Constants.WOBBLE_OPEN_POSITION);
        open = true;
    }

    public void close() {
        servo.setPosition(Constants.WOBBLE_CLOSED_POSITION);
        open = false;
    }

    public void toggle() {
        if (open) {
            close();
        } else {
            open();
        }
    }
}
