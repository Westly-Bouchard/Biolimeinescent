package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import static org.firstinspires.ftc.teamcode.util.Constants.*;

//Note that it isn't public; this is a constructor, not an instance. I'd recommend you make all mechanisms like this.
class ExampleMotor {
    //Hint: Remember you can initialize and run multiple motors on the same file (DriveTrain...?)
    private DcMotor exampleMotor;

    void init(HardwareMap hwMap) {
        //Tells the motor where in the hwMap it should pull its name from. When you make more of these, use
        //"mechanismType.class" instead of "DcMotor.class"; the name is whatever it is in the hardwareMap.
        exampleMotor = hwMap.get(DcMotor.class, "example_motor");
        //Tells it it should run without an encoder; this is actually important, as otherwise
        //it'll try to base its speed on encoder feedback and throw everything out of wack
        exampleMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //Sets the motor to brake mode, i.e. when it has zero power it'll actively resist movement
        exampleMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    void setSpeed(double speed) {
        //Pulls the max speed from the constants map, sets the motor power to it multiplied by the input
        exampleMotor.setPower(speed * EXAMPLE_MOTOR_MAX_SPEED);
    }
}
