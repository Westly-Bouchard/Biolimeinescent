package org.firstinspires.ftc.teamcode.commands.looped;

import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.framework.util.TelemetryHandler;
import org.firstinspires.ftc.teamcode.mechanisms.DriveTrain;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.MechanismEngine;

public class DriveTelemetry extends Command {

    private DriveTrain driveTrain = MechanismEngine.getInstance().getMechanism(DriveTrain.class);

    public DriveTelemetry() {}

    public void initialize() {}

    public void execute() {
        TelemetryHandler.getInstance().getTelemetry().addData("X", driveTrain.getX());
        TelemetryHandler.getInstance().getTelemetry().addData("Z", driveTrain.getZ());
        TelemetryHandler.getInstance().getTelemetry().addData("Heading", driveTrain.getTrueHeading());
        TelemetryHandler.getInstance().getTelemetry().addData("Distance", driveTrain.getDistance());
    }

    public boolean isFinished() {
        return false;
    }

    public void end() {}
}