package org.firstinspires.ftc.teamcode.commands.auto;

import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.framework.PIDHandler;
import org.firstinspires.ftc.teamcode.framework.util.Timekeeper;
import org.firstinspires.ftc.teamcode.mechanisms.DriveTrain;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.MechanismEngine;
import org.firstinspires.ftc.teamcode.framework.Constants;

public class DriveToPoint extends Command {

    private DriveTrain driveTrain = MechanismEngine.getInstance().getMechanism(DriveTrain.class);
    private PIDHandler angle_PID = new PIDHandler(Constants.kAngleP, Constants.kAngleI, Constants.kAngleD);
    private PIDHandler offset_PID = new PIDHandler(Constants.x_offset_kP, Constants.x_offset_kI, Constants.x_offset_kD);
    private PIDHandler distance_PID = new PIDHandler(Constants.kDistanceP, Constants.kDistanceI, Constants.kDistanceD);

    private Timekeeper timekeeper;

    double x_target, z_target, angle_target, starting_speed, ending_speed;
    double x_difference, z_difference, translated_x, translated_z, starting_translated_z;
    double linear_speed, rotational_speed, PID_angle_target, previous_time;

    public DriveToPoint(Timekeeper timekeeper, double x_target_input, double z_target_input, double angle_target_input) {
        Requires(driveTrain);
        this.timekeeper = timekeeper;
        x_target = x_target_input;
        z_target = z_target_input;

        angle_target = ((angle_target_input - driveTrain.getTrueHeading() + 180) % 360) + 360 % 360;
        ending_speed = 0;
    }

    public DriveToPoint(Timekeeper timekeeper, double x_target_input, double z_target_input, double angle_target_input, double ending_speed_input) {
        Requires(driveTrain);
        this.timekeeper = timekeeper;
        x_target = x_target_input;
        z_target = z_target_input;
        angle_target = ((angle_target_input - driveTrain.getTrueHeading() + 180) % 360) + 360 % 360;
        ending_speed = ending_speed_input;
    }

    public void CalculatePosition() {
        x_difference = driveTrain.getX() - x_target;
        z_difference = driveTrain.getZ() - z_target;

        translated_x = x_difference * Math.cos(angle_target * Math.PI / 180) - z_difference * Math.sin(angle_target * Math.PI / 180);
        translated_z = x_difference * Math.sin(angle_target * Math.PI / 180) + z_difference * Math.cos(angle_target * Math.PI / 180);
    }

    public void initialize() {
        CalculatePosition();
        starting_speed = driveTrain.getSpeed();

        starting_translated_z = translated_z;

        angle_PID.resetI();
        angle_PID.resetD();

        offset_PID.resetI();
        offset_PID.resetD();

        if (ending_speed == 0) {
            distance_PID.resetI();
            distance_PID.resetD();
        }

        previous_time = timekeeper.getRuntime();
    }

    public void execute() {
        CalculatePosition();
        if (ending_speed == 0) {
            linear_speed = distance_PID.getPID(translated_z, 0, timekeeper.getRuntime() - previous_time);
            if (Math.abs(linear_speed) > Math.abs(starting_speed)) {
                linear_speed = Math.abs(starting_speed) * (Math.abs(linear_speed) / linear_speed);
            }
        } else {
            linear_speed = ending_speed - ((ending_speed - starting_speed) * (Math.min(Math.abs(translated_z), Math.abs(starting_translated_z)) / Math.abs(starting_translated_z)));
        }

        PID_angle_target = Math.atan(offset_PID.getPID(-translated_x * Math.abs(translated_z) / translated_z, 0, timekeeper.getRuntime() - previous_time)) * 180 / Math.PI;
        rotational_speed = angle_PID.getPID(driveTrain.getTrueHeading(), angle_target + PID_angle_target, timekeeper.getRuntime() - previous_time);
        driveTrain.driveAll(linear_speed, 0, rotational_speed);

        previous_time = timekeeper.getRuntime();
    }

    public boolean isFinished() {
        if (ending_speed == 0) {
            return (Math.abs(translated_z) < Constants.kDistanceFinishedThreshold);
        } else {
            return ((translated_z * starting_translated_z) < 0);
        }
    }

    public void end() {
        driveTrain.driveAll(ending_speed, 0, 0);
    }
}
