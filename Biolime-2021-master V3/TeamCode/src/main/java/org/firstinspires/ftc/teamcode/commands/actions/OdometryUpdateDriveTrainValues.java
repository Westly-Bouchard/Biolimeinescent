package org.firstinspires.ftc.teamcode.commands.actions;

import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.framework.util.Timekeeper;
import org.firstinspires.ftc.teamcode.mechanisms.DriveTrain;
import org.firstinspires.ftc.teamcode.mechanisms.OdometryDriveTrain;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.MechanismEngine;

public class OdometryUpdateDriveTrainValues extends Command {

    private OdometryDriveTrain driveTrain = MechanismEngine.getInstance().getMechanism(OdometryDriveTrain.class);
    private Timekeeper timekeeper;

    public OdometryUpdateDriveTrainValues(Timekeeper timekeeper) {
        this.timekeeper = timekeeper;
        Requires(driveTrain);
    }

    public void initialize() {}

    public void execute() {
        driveTrain.adjustTrueHeading();
        driveTrain.calculateOdometry(timekeeper.getRuntime());
    }

    public boolean isFinished() { return true; }

    public void end() {}
}
