package org.firstinspires.ftc.teamcode.commands.actions;


import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.framework.Constants;
import org.firstinspires.ftc.teamcode.framework.PIDHandler;
import org.firstinspires.ftc.teamcode.framework.controllers.Axis;
import org.firstinspires.ftc.teamcode.framework.util.Timekeeper;
import org.firstinspires.ftc.teamcode.mechanisms.DriveTrain;
import org.firstinspires.ftc.teamcode.mechanisms.Shooter;
import org.firstinspires.ftc.teamcode.mechanisms.Storage;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.MechanismEngine;

public class AngleAlign extends Command {

    private DriveTrain driveTrain = MechanismEngine.getInstance().getMechanism(DriveTrain.class);
    private Shooter shooter = MechanismEngine.getInstance().getMechanism(Shooter.class);
    private Storage storage = MechanismEngine.getInstance().getMechanism(Storage.class);

    private Axis leftX, leftY;

    //private Timekeeper timekeeper;

    private PIDHandler m_pid = new PIDHandler(Constants.kAngleP, Constants.kAngleI, Constants.kAngleD);
    private double previous_time = 0;

    public AngleAlign(Timekeeper timekeeper, Axis leftAxisX, Axis leftAxisY) {
        /*this.leftX = leftAxisX;
        this.leftY = leftAxisY;
        this.timekeeper = timekeeper;
        Requires(driveTrain);*/
        Requires(shooter);
    }

    public void initialize() {
        shooter.setPIDCoefficients(Constants.kShooterP, Constants.kShooterI, Constants.kShooterD, Constants.kShooterF);
        storage.retract();
        //previous_time = timekeeper.getRuntime();
    }

    public void execute() {
        shooter.setProfSpeed();
        //driveTrain.driveAll(leftY.get(), leftX.get(), m_pid.getPID(driveTrain.getTrueHeading(), 0, timekeeper.getRuntime() - previous_time));
        //previous_time = timekeeper.getRuntime();
    }

    public boolean isFinished() { return false; }

    public void end() {
        storage.extend();
        shooter.setSpeed(0);
        //driveTrain.setSpeeds(0, 0, 0, 0);
    };
}
