package org.firstinspires.ftc.teamcode.commands.looped;

import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.framework.Constants;
import org.firstinspires.ftc.teamcode.mechanisms.Storage;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.MechanismEngine;
import org.firstinspires.ftc.teamcode.framework.controllers.Button;

public class StorageIdle extends Command {

    private Storage storage = MechanismEngine.getInstance().getMechanism(Storage.class);

    private Button RB;

    public StorageIdle(Button rBumper) {
        this.RB = rBumper;
        Requires(storage);
    }

    public void initialize() {}

    public void execute() {
        if (RB.get() == true) {
            storage.setMotorPower(0);
        } else {
            storage.setMotorPower(Constants.kStorageIdlePower);
        }
    }

    public boolean isFinished() {
        return false;
    }

    public void end() {
        storage.setMotorPower(0);
    }
}
