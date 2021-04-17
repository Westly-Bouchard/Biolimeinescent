package org.firstinspires.ftc.teamcode.commands.actions;

import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.MechanismEngine;

import org.firstinspires.ftc.teamcode.framework.Constants;

public class RunIntake extends Command {

    private Intake intake = MechanismEngine.getInstance().getMechanism(Intake.class);

    public RunIntake() {
        Requires(intake);
    }

    public void initialize() {}

    public void execute() {
        intake.setMotorPower(Constants.kIntakeRunPower);
    }

    public boolean isFinished() {
        return false;
    }

    public void end() {
        intake.setMotorPower(0);
    }
}