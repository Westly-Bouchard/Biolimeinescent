package org.firstinspires.ftc.teamcode.commands.actions;

import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.mechanisms.Shooter;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.MechanismEngine;

public class ResetShooterRPMAdjust extends Command {

    private Shooter shooter = MechanismEngine.getInstance().getMechanism(Shooter.class);

    public void initialize() {}

    public void execute() { shooter.resetRPMAdjustment(); }

    public boolean isFinished() { return true; }

    public void end() {}
}
