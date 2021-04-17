package org.firstinspires.ftc.teamcode.commands.actions;

import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.mechanisms.Wobble;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.MechanismEngine;

public class ToggleWobbleClaw extends Command {

    private Wobble wobble = MechanismEngine.getInstance().getMechanism(Wobble.class);

    public ToggleWobbleClaw() {}

    public void initialize() {}

    public void execute() {
        wobble.toggle();
    }

    public boolean isFinished() {
        return true;
    }

    public void end() {}
}
