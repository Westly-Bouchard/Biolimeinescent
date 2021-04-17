package org.firstinspires.ftc.teamcode.commands.actions;

import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.MechanismEngine;

public class FlipIntake extends Command {
    private enum state {
        EXTENDED,
        RETRACTED,
        TOGGLE
    }

    private state action;

    private Intake intake = MechanismEngine.getInstance().getMechanism(Intake.class);

    public FlipIntake() {
        action = state.TOGGLE;
    }

    public FlipIntake(boolean extend) {
        if (extend) {
            action = state.EXTENDED;
        } else {
            action = state.RETRACTED;
        }
    }

    public void initialize() {}

    public void execute() {
        switch (action) {
            case EXTENDED:
                intake.extend();
                break;
            case RETRACTED:
                intake.retract();
                break;
            case TOGGLE:
                intake.toggle();
                break;
        }
    }

    public boolean isFinished() {
        return true;
    }

    public void end() {}
}
