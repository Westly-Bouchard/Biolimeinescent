package org.firstinspires.ftc.teamcode.commands.auto;

import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.MechanismEngine;

public class StopIntake extends Command {

    private Intake intake = MechanismEngine.getInstance().getMechanism(Intake.class);

    public StopIntake() {
        Requires(intake);
    }

    public void initialize() {}

    public void execute() {
        intake.setMotorPower(0);
    }

    public boolean isFinished() {
        return true;
    }

    public void end() {}
}