package org.firstinspires.ftc.teamcode.commands.auto;

import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.framework.Constants;
import org.firstinspires.ftc.teamcode.framework.PIDHandler;
import org.firstinspires.ftc.teamcode.framework.util.TelemetryHandler;
import org.firstinspires.ftc.teamcode.framework.util.Timekeeper;
import org.firstinspires.ftc.teamcode.mechanisms.DriveTrain;
import org.firstinspires.ftc.teamcode.mechanisms.Shooter;
import org.firstinspires.ftc.teamcode.mechanisms.Storage;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.MechanismEngine;

public class PrepareShooter extends Command {

    private DriveTrain driveTrain = MechanismEngine.getInstance().getMechanism(DriveTrain.class);
    private Shooter shooter = MechanismEngine.getInstance().getMechanism(Shooter.class);
    private Storage storage = MechanismEngine.getInstance().getMechanism(Storage.class);

    private Timekeeper timekeeper;

    private PIDHandler m_pid = new PIDHandler(Constants.kAngleP, Constants.kAngleI, Constants.kAngleD);
    private double starting_time, previous_time, duration;

    public PrepareShooter(Timekeeper timekeeper, double timeRequired) {
        Requires(driveTrain);
        Requires(shooter);
        this.timekeeper = timekeeper;
        this.duration = timeRequired;
    }

    public void initialize() {
        shooter.setPIDCoefficients(Constants.kShooterP, Constants.kShooterI, Constants.kShooterD, Constants.kShooterF);
        starting_time = timekeeper.getRuntime();
        previous_time = starting_time;
    }

    public void execute() {
        storage.retract();
        shooter.setProfSpeed();
        driveTrain.driveAll(0, 0, m_pid.getPID(driveTrain.getTrueHeading(), Constants.kShootAngle, timekeeper.getRuntime() - previous_time));
        previous_time = timekeeper.getRuntime();
        TelemetryHandler.getInstance().getTelemetry().addData("Time", timekeeper.getRuntime() - starting_time);
    }

    public boolean isFinished() {
        if (timekeeper.getRuntime() - starting_time > duration) {
            return true;
        }
        return false;
    }

    public void end() {
        storage.extend();
        shooter.setSpeed(0);
        driveTrain.driveAll(0, 0, 0);
    };
}
