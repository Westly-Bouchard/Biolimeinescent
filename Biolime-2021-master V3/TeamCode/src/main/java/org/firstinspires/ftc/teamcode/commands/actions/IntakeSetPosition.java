package org.firstinspires.ftc.teamcode.commands.actions;

import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.MechanismEngine;

public class IntakeSetPosition extends Command {

    private Intake intake = MechanismEngine.getInstance().getMechanism(Intake.class);
    double position;

    public IntakeSetPosition(double pos) {
        Requires(intake);
        this.position = pos;
    }

    public void initialize() {}

    public void execute() {
        intake.setPosition(position);
    }

    public boolean isFinished() { return true; }

    public void end() {}
}
