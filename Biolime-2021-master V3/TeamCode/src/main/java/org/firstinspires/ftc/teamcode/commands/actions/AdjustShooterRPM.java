package org.firstinspires.ftc.teamcode.commands.actions;

import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.framework.Constants;
import org.firstinspires.ftc.teamcode.mechanisms.Shooter;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.MechanismEngine;

public class AdjustShooterRPM extends Command {
    private Shooter shooter = MechanismEngine.getInstance().getMechanism(Shooter.class);
    private boolean subtract = false;

    public AdjustShooterRPM(boolean subtract) {
        this.subtract = subtract;
    }

    public void initialize() {}

    public void execute() {
        if (subtract) {
            shooter.changeRPMAdjustment(-Constants.kShooterRPMAdjustment);
        } else {
            shooter.changeRPMAdjustment(Constants.kShooterRPMAdjustment);
        }
    }

    public boolean isFinished() { return true; }

    public void end() {}
}
