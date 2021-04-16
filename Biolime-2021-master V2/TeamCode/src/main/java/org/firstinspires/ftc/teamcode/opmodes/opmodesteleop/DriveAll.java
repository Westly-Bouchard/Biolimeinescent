package org.firstinspires.ftc.teamcode.opmodes.opmodesteleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.commands.actions.*;
import org.firstinspires.ftc.teamcode.commands.looped.*;
import org.firstinspires.ftc.teamcode.mechanisms.Shooter;

@TeleOp
public class DriveAll extends TeleOpModeWrapper {

    @Override
    public void teleOpInit() {
        //scheduler.enableDebugTelemetry();
        scheduler.add(new ToggleStorageBlocker(true));
    }

    @Override
    public void teleOpLoop() {
        scheduler.add(new Drive(DriverLeftXAxis, DriverLeftYAxis, DriverRightXAxis, DriverRightBumper));
        scheduler.add(new StorageIdle(DriverRightBumper));
        scheduler.add(new ShooterTelemetry());

        DriverRightBumper.whileHeld(new AngleAlign(timekeeper, DriverLeftXAxis, DriverLeftYAxis));
        OperatorRightBumper.whileHeld(new AngleAlign(timekeeper, DriverLeftXAxis, DriverLeftYAxis));
        DriverLeftBumper.whileHeld(new Shoot());

        OperatorBButton.whileHeld(new RunIntake());
        OperatorBButton.whileHeld(new RunStorage());

        OperatorDPadRight.whenPressed(new AdjustShooterRPM(false));
        OperatorDPadLeft.whenPressed(new AdjustShooterRPM(true));
        OperatorDPadUp.whenPressed(new ToggleShooterProfile(true));
        OperatorDPdDown.whenPressed(new ToggleShooterProfile(false));

        DriverBButton.whenPressed(new ToggleShooterProfile());

        OperatorXButton.whenPressed(new ToggleStorageBlocker());
    }
}
