package org.firstinspires.ftc.teamcode.framework.util.Odometry;

import org.firstinspires.ftc.teamcode.framework.util.Maths.Pose2d;
import org.firstinspires.ftc.teamcode.framework.util.Maths.Rotation2d;
import org.firstinspires.ftc.teamcode.framework.util.Maths.Twist2d;

public class MecanumDriveOdometry {
    private final MecanumDriveKinematics kinematics;
    private Pose2d poseInches;
    private double prevTimeSeconds = 0;

    private Rotation2d gyroOffset;
    private Rotation2d previousAngle;

    public MecanumDriveOdometry(MecanumDriveKinematics kinematics, Rotation2d gyroAngle, Pose2d initialPoseInches) {
        this.kinematics = kinematics;
        this.poseInches = initialPoseInches;
        this.gyroOffset = poseInches.getRotation().minus(gyroAngle);
        this.previousAngle = initialPoseInches.getRotation();
    }

    public MecanumDriveOdometry(MecanumDriveKinematics kinematics, Rotation2d gyroAngle) {
        this(kinematics, gyroAngle, new Pose2d());
    }

    public void resetPosition(Pose2d poseInches, Rotation2d gyroAngle) {
        this.poseInches = poseInches;
        this.previousAngle = poseInches.getRotation();
        this.gyroOffset = poseInches.getRotation().minus(gyroAngle);
    }

    public Pose2d getPoseInches() {
        return poseInches;
    }

    public Pose2d updateWithTime(
            double currentTimeSeconds, Rotation2d gyroAngle, MecanumDriveWheelSpeeds wheelSpeeds) {
        double period = prevTimeSeconds >= 0 ? currentTimeSeconds - prevTimeSeconds : 0.0;
        prevTimeSeconds = currentTimeSeconds;

        Rotation2d angle = gyroAngle.plus(gyroOffset);

        ChassisSpeeds chassisState = kinematics.toChassisSpeeds(wheelSpeeds);
        Pose2d newPose =
                poseInches.exp(
                        new Twist2d(
                                chassisState.vxInchesPerSecond * period,
                                chassisState.vyInchesPerSecond * period,
                                angle.minus(previousAngle).getRadians()));

        previousAngle = angle;
        poseInches = new Pose2d(newPose.getTranslation(), angle);
        return poseInches;
    }

//    public Pose2d update(Rotation2d gyroAngle, MecanumDriveWheelSpeeds wheelSpeeds) {
//        return updateWithTime(WPIUtilJNI.now() * 1.0e-6, gyroAngle, wheelSpeeds);
//    }
}
