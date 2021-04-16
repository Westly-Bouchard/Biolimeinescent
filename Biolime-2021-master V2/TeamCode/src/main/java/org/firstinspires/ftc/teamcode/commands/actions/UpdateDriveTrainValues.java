package org.firstinspires.ftc.teamcode.commands.actions;

import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.mechanisms.DriveTrain;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.MechanismEngine;

public class UpdateDriveTrainValues extends Command {

    private DriveTrain driveTrain = MechanismEngine.getInstance().getMechanism(DriveTrain.class);

    public UpdateDriveTrainValues() {
        Requires(driveTrain);
    }

    public void initialize() {}

    public void execute() {
        driveTrain.adjustTrueHeading();
        driveTrain.incrementXZ();
    }

    public boolean isFinished() { return true; }

    public void end() {}
}
