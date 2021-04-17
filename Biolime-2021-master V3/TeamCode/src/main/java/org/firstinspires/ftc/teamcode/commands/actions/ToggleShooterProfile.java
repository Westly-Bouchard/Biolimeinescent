package org.firstinspires.ftc.teamcode.commands.actions;

import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.mechanisms.Shooter;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.MechanismEngine;

public class ToggleShooterProfile extends Command {
    public Shooter shooter = MechanismEngine.getInstance().getMechanism(Shooter.class);

    private enum state {
        LVL3,
        POWERSHOT,
        TOGGLE
    }

    private state action;

    public ToggleShooterProfile() {
        action = state.TOGGLE;
    }

    public ToggleShooterProfile(boolean highGoal) {
        if (highGoal == true) {
            action = state.LVL3;
        } else {
            action = state.POWERSHOT;
        }
    }

    public void initialize() {}

    public void execute() {
        switch (action) {
            case LVL3:
                shooter.setHighGoal();
                break;
            case POWERSHOT:
                shooter.setPowerShot();
                break;
            case TOGGLE:
                shooter.toggleProfile();
                break;
        }
    }

    public boolean isFinished() {
        return true;
    }

    public void end() {}
}
