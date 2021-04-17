package org.firstinspires.ftc.teamcode.commands.looped;

import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.framework.util.TelemetryHandler;
import org.firstinspires.ftc.teamcode.mechanisms.Shooter;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.MechanismEngine;

public class ShooterTelemetry extends Command {

    private Shooter shooter = MechanismEngine.getInstance().getMechanism(Shooter.class);

    public ShooterTelemetry() {}

    public void initialize() {}

    public void execute() {
        TelemetryHandler.getInstance().getTelemetry().addData("Shooter Power", shooter.getProfSpeed());
    }

    public boolean isFinished() {
        return false;
    }

    public void end() {}
}