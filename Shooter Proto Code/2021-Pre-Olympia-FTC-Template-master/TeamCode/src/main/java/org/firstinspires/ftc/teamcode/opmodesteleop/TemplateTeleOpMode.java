package org.firstinspires.ftc.teamcode.opmodesteleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class TemplateTeleOpMode extends OpMode {

    @Override
    public void init() {

    }

    public void loop() {
        telemetry.addData("test", "yes");
    }
}
