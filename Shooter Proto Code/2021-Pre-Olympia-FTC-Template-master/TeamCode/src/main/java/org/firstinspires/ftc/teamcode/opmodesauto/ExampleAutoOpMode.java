package org.firstinspires.ftc.teamcode.opmodesauto;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

//We don't need it here, but here's the include statement for Telemetry
import org.firstinspires.ftc.robotcore.external.Telemetry;

import org.firstinspires.ftc.teamcode.mechanisms.ExampleRobot;

import static org.firstinspires.ftc.teamcode.util.Constants.*;

@Autonomous()
public class ExampleAutoOpMode extends OpMode {
    private ExampleRobot robot = new ExampleRobot();
    //The runtime is a measure of time since the OpMode was initialized. I'm using
    //it here for purposes of the logic to swap between steps.
    private ElapsedTime runtime = new ElapsedTime();

    private int step = 0;

    public void init() {
        robot.init(hardwareMap);
        runtime.reset();
    }

    public void loop() {
        switch(step) {
            case 0:
                //The telemetry is that output thing I was talking about. It'll display the values
                //you tell it to on the driver station phone.
                telemetry.addData("Auto Step", "0");

                //Drive the ExampleMotor forward for 3 seconds
                if (runtime.seconds() <= 3) {
                    robot.setExampleMotorSpeed(1);
                } else {
                    robot.setExampleMotorSpeed(0);
                    step = 1;
                }
                break;

            case 1: //What it Does:
                telemetry.addData("Auto Step", "1");

                //Drive the ExampleMotor backward at half speed for 3 seconds
                if (runtime.seconds() < 8) {
                    robot.setExampleMotorSpeed(-0.5);
                } else {
                    robot.setExampleMotorSpeed(0);
                    step = 2;
                }
                break;
            case 2:
                telemetry.addData("Auto Step", "2");

                //Open the servo and then end the auto
                robot.openExampleServo();
                step = 99;
                break;
        }
    }
}
