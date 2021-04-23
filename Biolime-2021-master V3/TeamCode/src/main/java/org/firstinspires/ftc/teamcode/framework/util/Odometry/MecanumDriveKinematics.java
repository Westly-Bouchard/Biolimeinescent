package org.firstinspires.ftc.teamcode.framework.util.Odometry;

import org.firstinspires.ftc.teamcode.framework.util.Maths.Translation2d;
import org.ejml.simple.SimpleMatrix;

public class MecanumDriveKinematics {
    private SimpleMatrix inverseKinematics;
    private final SimpleMatrix forwardKinematics;


    private final Translation2d frontLeftWheelInches;
    private final Translation2d frontRightWheelInches;
    private final Translation2d rearLeftWheelInches;
    private final Translation2d rearRightWheelInches;

    private Translation2d prevCoR = new Translation2d();

    public MecanumDriveKinematics(Translation2d frontLeftWheelInches, Translation2d frontRightWheelInches, Translation2d rearLeftWheelInches, Translation2d rearRightWheelInches) {
        this.frontLeftWheelInches = frontLeftWheelInches;
        this.frontRightWheelInches = frontRightWheelInches;
        this.rearLeftWheelInches = rearLeftWheelInches;
        this.rearRightWheelInches = rearRightWheelInches;

        inverseKinematics = new SimpleMatrix(4, 3);

        setInverseKinematics(frontLeftWheelInches, frontRightWheelInches, rearLeftWheelInches, rearRightWheelInches);
        forwardKinematics = inverseKinematics.pseudoInverse();
    }

    public MecanumDriveWheelSpeeds toWheelSpeeds(ChassisSpeeds chassisSpeeds, Translation2d centerOfRotationInches) {
        if (!centerOfRotationInches.equals(prevCoR)) {
            Translation2d fl = frontLeftWheelInches.minus(centerOfRotationInches);
            Translation2d fr = frontRightWheelInches.minus(centerOfRotationInches);
            Translation2d rl = rearLeftWheelInches.minus(centerOfRotationInches);
            Translation2d rr = rearRightWheelInches.minus(centerOfRotationInches);

            setInverseKinematics(fl, fr, rl, rr);
            prevCoR = centerOfRotationInches;
        }

        SimpleMatrix chassisSpeedsVector = new SimpleMatrix(3, 1);
        chassisSpeedsVector.setColumn(
                0,
                0,
                chassisSpeeds.vxInchesPerSecond,
                chassisSpeeds.vyInchesPerSecond,
                chassisSpeeds.omegaRadiansPerSecond);

        SimpleMatrix wheelsMatrix = inverseKinematics.mult(chassisSpeedsVector);
        return new MecanumDriveWheelSpeeds(
                wheelsMatrix.get(0, 0),
                wheelsMatrix.get(1, 0),
                wheelsMatrix.get(2, 0),
                wheelsMatrix.get(3, 0));
    }

    public MecanumDriveWheelSpeeds toWheelSpeeds(ChassisSpeeds chassisSpeeds) {
        return toWheelSpeeds(chassisSpeeds, new Translation2d());
    }

    public ChassisSpeeds toChassisSpeeds(MecanumDriveWheelSpeeds wheelSpeeds) {
        SimpleMatrix wheelSpeedsMatrix = new SimpleMatrix(4, 1);
        wheelSpeedsMatrix.setColumn(
                0,
                0,
                wheelSpeeds.frontLeftInchesPerSecond,
                wheelSpeeds.frontRightInchesPerSecond,
                wheelSpeeds.rearLeftInchesPerSecond,
                wheelSpeeds.rearRightInchesPerSecond);
        SimpleMatrix chassisSpeedsVector = forwardKinematics.mult(wheelSpeedsMatrix);

        return new ChassisSpeeds(
                chassisSpeedsVector.get(0, 0),
                chassisSpeedsVector.get(1, 0),
                chassisSpeedsVector.get(2, 0)
        );
    }

    public void setInverseKinematics( Translation2d fl, Translation2d fr, Translation2d rl, Translation2d rr) {
        inverseKinematics.setRow(0, 0, 1, -1, -(fl.getX() + fl.getY()));
        inverseKinematics.setRow(1, 0, 1, 1, fr.getX() - fr.getY());
        inverseKinematics.setRow(2, 0, 1, 1, rl.getX() - rl.getY());
        inverseKinematics.setRow(3, 0, 1, -1, -(rr.getX() + rr.getY()));
//        inverseKinematics = inverseKinematics.scale(1.0 / Math.sqrt(2));
// This is commented beacuse it breaks the model, I don't know why it does, it just does.
    }
}
