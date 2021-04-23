package org.firstinspires.ftc.teamcode.commands.auto;

import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import static org.firstinspires.ftc.teamcode.framework.Constants;
import org.firstinspires.ftc.teamcode.framework.PIDHandler;
import org.firstinspires.ftc.teamcode.framework.util.Timekeeper;
import org.firstinspires.ftc.teamcode.mechanisms.OdometryDriveTrain;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.MechanismEngine;

public class StrafeToDistance extends Command {

    private PIDHandler pid = new PIDHandler(kStrafeP, kStrafeI, kStrafeD);
    private Timekeeper timekeeper;
    private OdometryDriveTrain driveTrain = MechanismEnginge.getInstance().getMechanism(OdometryDriveTrain.class);

    private double targetDistance, amtStrafed, previousTime, previousX, previousZ;



    public StrafeToDistance(Timekeeper timekeeper, double distance_input) {
        Requires(driveTrain);
        this.targetDistance = distance_input;
        this.timekeeper = timekeeper;
    }

    // public StrafeToDistance(Timekeeper timekeeper, double distance_input, double ending_speed_input) {
    //     Requires(driveTrain);
    //     this.distance_offset = distance_input;
    //     this.ending_speed = ending_speed_input;
    //     this.timekeeper = timekeeper;
    // }

    public void initialize() {
        previousX = driveTrain.getX();
        previousZ = driveTrain.getZ();

        pid.resetI();
        pid.resetD();
        previous_time = timekeeper.getRuntime();
    }

    public void execute() {
        amtStrafed += Math.sqrt(Math.pow((driveTrain.getX() - previousX), 2) + Math.pow((driveTrain.getZ() - previousZ), 2));
        driveTrain.driveAll(0, pid.getPID(amtStrafed, targetDistance, timekeeper.getRuntime() - previousTime), 0);

        previousTime = timekeeper.getRuntime();
        previousX = driveTrain.getX();
        previousZ = driveTrain.getZ();
    }

    public boolean isFinished() {
        return Math.abs(targetDistance - amtStrafed) < kStrafeFinishedThreashold;
    }

    public void end() {
        driveTrain.driveAll(0, 0, 0);
    }
}
