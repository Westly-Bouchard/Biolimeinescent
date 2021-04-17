package org.firstinspires.ftc.teamcode.commands.auto;

import org.firstinspires.ftc.teamcode.commands.actions.*;
import org.firstinspires.ftc.teamcode.commands.basecommands.CommandGroup;
import org.firstinspires.ftc.teamcode.framework.Constants;
import org.firstinspires.ftc.teamcode.framework.util.Timekeeper;

public class SevenRingDoubleDozer extends CommandGroup {
    public SevenRingDoubleDozer(Timekeeper timekeeper) {
        addSequential(new SevenRingDozer(timekeeper));
        addParallel(new FlipIntake(false));
        addSequential(new DriveToPoint(timekeeper, 10, -42, 30, -1));
        addSequential(new DriveToPoint(timekeeper, 0, -50, 30));
        addSequential(new DriveToPoint(timekeeper, 20, -51, 80, 0.4));
        addSequential(new DriveToPoint(timekeeper, 50, -20, 45, 0.7));
        addSequential(new DriveToPoint(timekeeper, 60, 50, 0));
        addSequential(new DriveToPoint(timekeeper, 60, 50, 0));
    }
}
