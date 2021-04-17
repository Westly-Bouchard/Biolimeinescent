package org.firstinspires.ftc.teamcode.commands.auto;

import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.framework.Constants;
import org.firstinspires.ftc.teamcode.mechanisms.Storage;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.MechanismEngine;
import org.firstinspires.ftc.teamcode.framework.util.Timekeeper;

public class FeedShooter extends Command {

    private Storage storage = MechanismEngine.getInstance().getMechanism(Storage.class);

    private double duration, startingTime;

    private Timekeeper timekeeper;

    public FeedShooter(Timekeeper timekeeper, double timeRequired) {
        Requires(storage);
        this.duration = timeRequired;
        this.timekeeper = timekeeper;
    }

    public void initialize() {
        Requires(storage);
        startingTime = timekeeper.getRuntime();
    }

    public void execute() {
        storage.setMotorPower(Constants.kStorageShootPower);
    }

    public boolean isFinished() {
        if (timekeeper.getRuntime() - startingTime > duration) {
            return true;
        }
        return false;
    }

    public void end() {
        storage.setMotorPower(Constants.kStorageIdlePower);
    }
}
