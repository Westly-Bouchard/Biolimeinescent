package org.firstinspires.ftc.teamcode.commands.auto;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.framework.PIDHandler;
import org.firstinspires.ftc.teamcode.framework.util.Timekeeper;
import org.firstinspires.ftc.teamcode.mechanisms.DriveTrain;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.MechanismEngine;
import org.firstinspires.ftc.teamcode.framework.Constants;


public class DriveToDistance extends Command {

    private PIDHandler pid = new PIDHandler(Constants.kDistanceP, Constants.kDistanceI, Constants.kDistanceD);
    private Timekeeper timekeeper;
    private DriveTrain driveTrain = MechanismEngine.getInstance().getMechanism(DriveTrain.class);

    double distance_offset, distance_target, ending_speed;
    double starting_speed, starting_distance, speed, previous_time;

    public DriveToDistance(Timekeeper timekeeper, double distance_input) {
        Requires(driveTrain);
        distance_offset = distance_input;
        ending_speed = 0;
        this.timekeeper = timekeeper;
    }

    public DriveToDistance(Timekeeper timekeeper, double distance_input, double ending_speed_input) {
        Requires(driveTrain);
        distance_offset = distance_input;
        ending_speed = ending_speed_input;
        this.timekeeper = timekeeper;
    }

    public void initialize() {
        starting_distance = driveTrain.getDistance();
        distance_target = starting_distance + distance_offset;
        starting_speed = driveTrain.getSpeed();

        if (ending_speed == 0) {
            pid.resetI();
            pid.resetD();
        }

        previous_time = timekeeper.getRuntime();
    }

    public void execute() {
        if (ending_speed == 0) {
            speed = pid.getPID(driveTrain.getDistance(), distance_target, timekeeper.getRuntime() - previous_time);
        } else {
            speed = starting_speed + ((ending_speed - starting_speed) * ((driveTrain.getDistance() - starting_distance) / (distance_target - starting_distance)));
        }

        driveTrain.driveAll(speed, 0, 0);
        previous_time = timekeeper.getRuntime();
    }

    public boolean isFinished() {
        return Math.abs(distance_target - driveTrain.getDistance()) < Constants.kDistanceFinishedThreshold;
    }

    public void end() {
        driveTrain.driveAll(ending_speed, 0, 0);
    }
}
