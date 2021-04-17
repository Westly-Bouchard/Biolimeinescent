package org.firstinspires.ftc.teamcode.commands.auto;

import org.firstinspires.ftc.teamcode.commands.basecommands.CommandGroup;
import org.firstinspires.ftc.teamcode.framework.util.Timekeeper;

public class Shoot extends CommandGroup {
    private double delay = 1;

    public Shoot(Timekeeper timekeeper, double shootDuration) {
        addSequential(new StopStorage());
        addParallel(new PrepareShooter(timekeeper, shootDuration + delay));
        addSequential(new Sleep(timekeeper,delay));
        addSequential(new FeedShooter(timekeeper,shootDuration));
    }
}
