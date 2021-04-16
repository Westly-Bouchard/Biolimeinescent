package org.firstinspires.ftc.teamcode.commands.actions;

import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.framework.Constants;
import org.firstinspires.ftc.teamcode.mechanisms.Storage;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.MechanismEngine;

public class RunStorage extends Command {

    private Storage storage = MechanismEngine.getInstance().getMechanism(Storage.class);

    public RunStorage() {
        Requires(storage);
    }

    public void initialize() {}

    public void execute() {
        storage.setMotorPower(Constants.kStorageRunPower);
    }

    public boolean isFinished() {
        return false;
    }

    public void end() {
        storage.setMotorPower(0);
    }
}
