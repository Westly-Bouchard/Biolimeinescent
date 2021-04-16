package org.firstinspires.ftc.teamcode.commands.actions;

import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.framework.Constants;
import org.firstinspires.ftc.teamcode.mechanisms.Storage;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.MechanismEngine;

public class Shoot extends Command {

    private Storage storage = MechanismEngine.getInstance().getMechanism(Storage.class);

    public void initialize() {
        Requires(storage);
    }

    public void execute() {
        storage.setMotorPower(Constants.kStorageShootPower);
    }

    public boolean isFinished() {
        return false;
    }

    public void end() {
        storage.setMotorPower(0);
    }
}
