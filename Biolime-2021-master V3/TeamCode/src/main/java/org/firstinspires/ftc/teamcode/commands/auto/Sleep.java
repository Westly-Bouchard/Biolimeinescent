package org.firstinspires.ftc.teamcode.commands.auto;

import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.framework.util.Timekeeper;

public class Sleep extends Command {
    private double sleepTime, initialTime;

    private Timekeeper timekeeper;

    public Sleep(Timekeeper timekeeper, double sleepTime) {
        this.timekeeper = timekeeper;
        this.sleepTime = sleepTime;
    }

    public void initialize() {
        initialTime = timekeeper.getRuntime();
    }

    public void execute() {}

    public boolean isFinished() {
        if (timekeeper.getRuntime() - initialTime > sleepTime) {
            return true;
        }
        return false;
    }

    public void end() {}
}