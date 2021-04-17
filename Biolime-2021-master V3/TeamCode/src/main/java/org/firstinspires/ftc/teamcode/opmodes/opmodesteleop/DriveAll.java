package org.firstinspires.ftc.teamcode.opmodes.opmodesteleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.commands.actions.*;
import org.firstinspires.ftc.teamcode.commands.looped.*;
import org.firstinspires.ftc.teamcode.framework.Constants;
import org.firstinspires.ftc.teamcode.mechanisms.Shooter;

@TeleOp
public class DriveAll extends TeleOpModeWrapper {

    @Override
    public void teleOpInit() {
        //scheduler.enableDebugTelemetry();
        scheduler.add(new ToggleStorageBlocker(true));
        //scheduler.add(new FlipIntake(true));
        scheduler.add(new ResetOdometry(0, 0, 180));
    }

    @Override
    public void teleOpLoop() {
        scheduler.add(new Drive(DriverLeftXAxis, DriverLeftYAxis, DriverRightXAxis, DriverRightBumper));
        scheduler.add(new StorageIdle(DriverRightBumper));
        scheduler.add(new ShooterTelemetry());

        DriverRightBumper.whileHeld(new AngleAlign(timekeeper, DriverLeftXAxis, DriverLeftYAxis));
        DriverLeftBumper.whileHeld(new RunStorage(Constants.kStorageShootPower));

        DriverBButton.whenPressed(new ResetOdometry(0, 0, 0));

        OperatorBButton.whileHeld(new RunIntake(Constants.kIntakeRunPower));
        OperatorBButton.whileHeld(new RunStorage(Constants.kStorageRunPower));

        OperatorAButton.whileHeld(new RunStorage(Constants.kStorageReversePower));
        OperatorAButton.whileHeld(new RunIntake(Constants.kIntakeReversePower));

        //OperatorYButton.whenPressed(new ToggleWobbleClaw());
        OperatorXButton.whenPressed(new ToggleStorageBlocker());

        OperatorRightBumper.whileHeld(new AngleAlign(timekeeper, DriverLeftXAxis, DriverLeftYAxis));
        OperatorLeftBumper.whenPressed(new FlipIntake());

        OperatorDPadRight.whenPressed(new AdjustShooterRPM(false));
        OperatorDPadLeft.whenPressed(new AdjustShooterRPM(true));
        OperatorDPadUp.whenPressed(new ToggleShooterProfile(true));
        OperatorDPdDown.whenPressed(new ToggleShooterProfile(false));
    }
}
