package org.firstinspires.ftc.teamcode.commands.actions;

import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.mechanisms.OdometryDriveTrain;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.MechanismEngine;

public class ResetOdometry extends Command {

    private OdometryDriveTrain driveTrain = MechanismEngine.getInstance().getMechanism(OdometryDriveTrain.class);

    private double x, z, a;

    public ResetOdometry(double xInput, double zInput, double aInput) {
        this.x = xInput;
        this.z = zInput;
        this.a = aInput;
    }

    public void initialize() {}

    public void execute() {
        driveTrain.setTrueHeading(a);
        driveTrain.setXZ(x, z);
    }

    public boolean isFinished() {
        return true;
    }

    public void end() {}
}
