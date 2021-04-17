package org.firstinspires.ftc.teamcode.commands.auto;

import org.firstinspires.ftc.teamcode.commands.actions.*;
import org.firstinspires.ftc.teamcode.commands.basecommands.CommandGroup;
import org.firstinspires.ftc.teamcode.framework.Constants;
import org.firstinspires.ftc.teamcode.framework.util.Timekeeper;

public class ThreeRingDozer extends CommandGroup {
    private double delay = 1;
    private double shootDuration = 5;

    public ThreeRingDozer(Timekeeper timekeeper) {
        addParallel(new RunStorage(Constants.kStorageIdlePower));
        addSequential(new ResetOdometry(50, -63, 0));
        addSequential(new DriveToPoint(timekeeper, 57, -24, 15, 0.4));
        addParallel(new FlipIntake(true));
        addSequential(new DriveToPoint(timekeeper, 60, 30, 0, 0.7));
        addSequential(new DriveToPoint(timekeeper, 60, 54, 0));
        addSequential(new DriveToPoint(timekeeper, 39, 20, 20, -1));
        addSequential(new DriveToPoint(timekeeper, 36, -1, Constants.kShootAngle));
        //addSequential(new Shoot(timekeeper, 5));


        addSequential(new StopStorage());
        addParallel(new PrepareShooter(timekeeper, shootDuration + delay));
        addSequential(new Sleep(timekeeper,delay));
        addSequential(new FeedShooter(timekeeper,shootDuration));

    }
}
