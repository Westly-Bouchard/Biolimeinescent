package org.firstinspires.ftc.teamcode.commands.looped;

import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.framework.PIDHandler;
import org.firstinspires.ftc.teamcode.framework.controllers.Axis;
import org.firstinspires.ftc.teamcode.framework.controllers.Button;
import org.firstinspires.ftc.teamcode.framework.util.TelemetryHandler;
import org.firstinspires.ftc.teamcode.framework.util.Timekeeper;
import org.firstinspires.ftc.teamcode.mechanisms.DriveTrain;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.MechanismEngine;
import org.firstinspires.ftc.teamcode.framework.Constants;

import java.lang.Math;

public class Drive extends Command {

    private DriveTrain driveTrain = MechanismEngine.getInstance().getMechanism(DriveTrain.class);

    private Axis leftX, leftY, rightX;
    private Button RB;

    private double x, y, a, angular_speed, previous_time;

    private PIDHandler m_pid = new PIDHandler(Constants.kAngleP, Constants.kAngleI, Constants.kAngleD);

    //private Timekeeper timekeeper;

    public Drive(Axis leftAxisX, Axis leftAxisY, Axis rightAxisX, Button rBumper) {
        //this.timekeeper = timeIsFake;
        this.leftX = leftAxisX;
        this.leftY = leftAxisY;
        this.rightX = rightAxisX;
        this.RB = rBumper;
    }

    public void initialize() {
        driveTrain.setTrueHeading(0);
        //previous_time = timekeeper.getRuntime();
    }

    public void execute() {
        a = driveTrain.getTrueHeading() * Math.PI / 180;

        x = leftX.get();
        y = leftY.get();

        //driveTrain.driveAll(y, x, rightX.get());

        if (RB.get() == true) {
            angular_speed = m_pid.getPID((((((driveTrain.getTrueHeading() - Constants.kShootAngle) + 180) % 360) + 360) % 360) - 180, 0, 0);
        } else {
            angular_speed = rightX.get()/2 + Math.pow(rightX.get(), 3)/2;
        }

        driveTrain.driveAll(x*Math.cos(a) - y*Math.sin(a), Constants.kStrafeModifier * (-y*Math.cos(a) - x*Math.sin(a)), angular_speed);
        TelemetryHandler.getInstance().getTelemetry().addData("Distance", driveTrain.getDistance());
        TelemetryHandler.getInstance().getTelemetry().addData("Angle", driveTrain.getTrueHeading());
    }

    public boolean isFinished() { return false; }

    public void end() {
        driveTrain.driveAll(0, 0, 0);
    }
}