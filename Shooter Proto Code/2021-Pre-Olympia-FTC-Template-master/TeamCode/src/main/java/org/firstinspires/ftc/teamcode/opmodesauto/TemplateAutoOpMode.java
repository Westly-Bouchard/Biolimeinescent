package org.firstinspires.ftc.teamcode.opmodesauto;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

//We don't need it here, but here's the include statement for Telemetry
import org.firstinspires.ftc.robotcore.external.Telemetry;

import org.firstinspires.ftc.teamcode.mechanisms.ExampleRobot;

import static org.firstinspires.ftc.teamcode.util.Constants.*;

@Autonomous()
public class TemplateAutoOpMode extends OpMode {
    private int step = 0;

    public void init() {

    }

    public void loop() {
        //Copypasta more steps as necessary; our autos typically have more than 10
        switch(step) {
            case 0:
                telemetry.addData("Auto Step", "0");

                step = 1;
                break;

            case 1: //What it Does:
                telemetry.addData("Auto Step", "1");

                step = 2;
                break;
            case 2:
                telemetry.addData("Auto Step", "2");

                step = 99;
                break;
        }
    }
}
