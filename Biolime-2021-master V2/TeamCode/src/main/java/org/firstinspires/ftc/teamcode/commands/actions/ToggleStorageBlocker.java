package org.firstinspires.ftc.teamcode.commands.actions;

import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.mechanisms.Storage;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.MechanismEngine;

public class ToggleStorageBlocker extends Command {
    private enum state {
        EXTENDED,
        RETRACTED,
        TOGGLE
    }

    private state action;

    private Storage storage = MechanismEngine.getInstance().getMechanism(Storage.class);

    public ToggleStorageBlocker() {
        action = state.TOGGLE;
    }

    public ToggleStorageBlocker(boolean extend) {
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
                storage.extend();
                break;
            case RETRACTED:
                storage.retract();
                break;
            case TOGGLE:
                storage.toggle();
                break;
        }
    }

    public boolean isFinished() {
        return true;
    }

    public void end() {}
}
