package org.firstinspires.ftc.teamcode.commands.auto;

import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.framework.Constants;
import org.firstinspires.ftc.teamcode.framework.PIDHandler;
import org.firstinspires.ftc.teamcode.framework.util.Timekeeper;
import org.firstinspires.ftc.teamcode.mechanisms.OdometryDriveTrain;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.MechanismEngine;

public class TurnToAngle extends Command {

    private OdometryDriveTrain driveTrain = MechanismEngine.getInstance().getMechanism(OdometryDriveTrain.class);
    private PIDHandler pid = new PIDHandler(Constants.kAngleP, Constants.kAngleI, Constants.kAngleD);
    private double targetAngle, angleError;
    private Timekeeper timekeeper;
    double previous_timer = 0;


    public TurnToAngle(Timekeeper timekeeper, double angle) {
        Requires(driveTrain);
        this.targetAngle = angle;
        this.timekeeper = timekeeper;
    }

    public void initialize() {
        previous_timer = timekeeper.getRuntime();
        targetAngle = (((((targetAngle - driveTrain.getTrueHeading() + 180) % 360) + 360) % 360) - 180) + driveTrain.getTrueHeading();
    }

    public void execute() {
        driveTrain.driveAll(0, 0, pid.getPID(driveTrain.getTrueHeading(), targetAngle, timekeeper.getRuntime() - previous_timer));
        previous_timer = timekeeper.getRuntime();
    }

    public boolean isFinished() {
        return Math.abs(driveTrain.getTrueHeading() - this.targetAngle) < Constants.kAngleFinishedThreshold;
    }

    public void end() {
        driveTrain.driveAll(0, 0, 0);
    }
}
