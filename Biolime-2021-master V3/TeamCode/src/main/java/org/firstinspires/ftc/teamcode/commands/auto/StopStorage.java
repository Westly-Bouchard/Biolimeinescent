package org.firstinspires.ftc.teamcode.commands.auto;

import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.mechanisms.Storage;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.MechanismEngine;

public class StopStorage extends Command {

    private Storage storage = MechanismEngine.getInstance().getMechanism(Storage.class);

    public StopStorage() {
        Requires(storage);
    }

    public void initialize() {}

    public void execute() {
        storage.setMotorPower(0);
    }

    public boolean isFinished() {
        return true;
    }

    public void end() {}
}
