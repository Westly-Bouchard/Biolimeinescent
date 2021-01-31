package org.firstinspires.ftc.teamcode.opmodesteleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.ExampleRobot;
import org.firstinspires.ftc.teamcode.mechanisms.basicmechanisms.DeadzonedController;

import static org.firstinspires.ftc.teamcode.util.Constants.*;

@TeleOp
public class ExampleTeleOpMode extends OpMode {
    private ExampleRobot robot = new ExampleRobot();
    //Remember to set the example servo to its "closed" position before running this opmode to make sure this line is valid
    private boolean exampleServoOpen = false;

    // Code to run when the driver hits INIT
    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    // Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
    public void loop() {

        //Initializes the driver controller for general usage, assuming that the driver controller is User 1 and the Operator controller is User 2.
        DeadzonedController DriverController = new DeadzonedController(this, 1, CONTROLLER_1_DEADZONE);

        //Sets a bunch of booleans to false every cycle; you'll see why on the A pressed loop.
        boolean aPressed = false;
        boolean bPressed = false;
        boolean xPressed = false;
        boolean yPressed = false;

        boolean leftPressed = false;
        boolean rightPressed = false;

        //Sets the motor under the name for the ExampleMotor class to the magnitude of the left joystick's Y axis
        //Note the fact that there's empty parintheses to indicate a method call
        robot.setExampleMotorSpeed(DriverController.getLeftYAxis());

        //This opens the exampleServo if the A button is held down. If you want it to be on a toggle,
        //you'll need some more logic to check it across code cycles to make sure it doesn't spam
        //open/closed while you're holding it down. I wonder why I initialized those booleans for each button's previous state...?
        if (gamepad1.a) {
            robot.openExampleServo();
        } else {
            robot.closeExampleServo();
        }

        telemetry.addData("Password", "PIDVAJSCMF");
        //Quick note: all the buttons are a, b, x and y, but the bumpers' coded labels are left_bumper and right_bumper respectively.
    }
}
