package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import static org.firstinspires.ftc.teamcode.util.Constants.*;

//Note that it isn't public; this is a constructor, not an instance. I'd recommend you make all mechanisms like this.
public class ExampleServo {
    private Servo exampleServo;

    void init(HardwareMap hwMap) {
        //Tells the servo where in the hwMap it should pull its name from. When you make more of these, use
        //"mechanismType.class" instead of "Servo.class"; the name is whatever it is in the hardwareMap.
        exampleServo = hwMap.get(Servo.class, "claw");
    }

    //Angle values are between 0 and 1 and represent a percentage of rotation on the servo's set range.
    void setAngle(double angle) {
        exampleServo.setPosition(angle);
    }

    void open() {
        exampleServo.setPosition(EXAMPLE_SERVO_OPEN_POSITION);
    }

    void close() {
        exampleServo.setPosition(EXAMPLE_SERVO_CLOSED_POSITION);
    }
}
