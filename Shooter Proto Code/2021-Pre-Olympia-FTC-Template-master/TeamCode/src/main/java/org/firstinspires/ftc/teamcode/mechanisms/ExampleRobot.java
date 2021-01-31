package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry; //This will come in handy at some point. Ask me about data readouts when you need them.

import static org.firstinspires.ftc.teamcode.util.Constants.*;

//Remember: the way this file is written requires you to write methods that just run the underlying
//methods of the mechanisms. Perhaps it could be omitted, or written a different way.
//Additionally, note the fact that this file is public, but the mechanisms aren't. Perhaps it'll be
//important for getting around the ExampleRobot class someday...
public class ExampleRobot {
    //Hint: perhaps these objects could be made public to prevent the need for passthrough methods...
    private ExampleMotor exampleMotor = new ExampleMotor();
    private ExampleServo exampleServo = new ExampleServo();

    public void init(HardwareMap hwMap) {
        exampleMotor.init(hwMap);
        exampleServo.init(hwMap);
    }

    //See how annoyingly pointless these passthroughs are? If only there were another way...
    public void setExampleMotorSpeed(double speed) {
        exampleMotor.setSpeed(speed);
    }

    public void openExampleServo() {
        exampleServo.open();
    }

    public void closeExampleServo() {
        exampleServo.close();
    }
}
