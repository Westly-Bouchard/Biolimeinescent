package org.firstinspires.ftc.teamcode.commands.auto;

import org.firstinspires.ftc.teamcode.commands.actions.*;
import org.firstinspires.ftc.teamcode.commands.basecommands.CommandGroup;
import org.firstinspires.ftc.teamcode.framework.Constants;
import org.firstinspires.ftc.teamcode.framework.util.Timekeeper;

public class SevenRingDozer extends CommandGroup {
    public SevenRingDozer(Timekeeper timekeeper) {
        //addSequential(new ThreeRingDozer(timekeeper));

        addParallel(new RunStorage(Constants.kStorageIdlePower));
        addSequential(new ResetOdometry(50, -63, 0));
        addSequential(new DriveToPoint(timekeeper, 57, -24, 15, 0.6));
        addParallel(new FlipIntake(true));
        addSequential(new DriveToPoint(timekeeper, 60, 30, 0, 0.8));
        addSequential(new DriveToPoint(timekeeper, 60, 50, 0));
        addSequential(new DriveToPoint(timekeeper, 39, 20, 20, -1));
        addSequential(new DriveToPoint(timekeeper, 36, -2, Constants.kShootAngle));
        addSequential(new AdjustShooterRPM(true));
        addSequential(new Shoot(timekeeper, 4));

        /*addSequential(new StopStorage());
        addParallel(new PrepareShooter(timekeeper, shootDuration + delay));
        addSequential(new Sleep(timekeeper,delay));
        addSequential(new FeedShooter(timekeeper,shootDuration));*/

        addParallel(new RunIntake(Constants.kIntakeRunPower));
        addSequential(new DriveToPoint(timekeeper, 38, -10, 0, -0.4));
        addSequential(new DriveToPoint(timekeeper, 40, -18, 0));
        addParallel(new RunStorage(Constants.kStorageRunPower));
        addSequential(new Sleep(timekeeper, 2));
        addSequential(new DriveToPoint(timekeeper, 38, -10, 0, 0.6));
        addSequential(new DriveToPoint(timekeeper, 36, -4, Constants.kShootAngle));
        addParallel(new RunIntake(0));
        addSequential(new StopStorage());
        addSequential(new ResetShooterRPMAdjust());
        addSequential(new AdjustShooterRPM(false));
        addSequential(new Shoot(timekeeper, 3));

        addParallel(new RunIntake(Constants.kIntakeRunPower));
        addSequential(new DriveToPoint(timekeeper, 38, -30, 0, -0.6));
        addSequential(new DriveToPoint(timekeeper, 40, -40, 0));
        addSequential(new Sleep(timekeeper, 2));
        addSequential(new DriveToPoint(timekeeper, 38, -10, 0, 0.6));
        addSequential(new DriveToPoint(timekeeper, 36, -6, Constants.kShootAngle));
        addParallel(new RunIntake(0));
        addSequential(new Shoot(timekeeper, 3));
        addSequential(new TurnToAngle(timekeeper, 180));
    }
}
