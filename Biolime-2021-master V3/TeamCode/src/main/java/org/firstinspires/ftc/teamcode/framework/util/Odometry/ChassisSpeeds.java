package org.firstinspires.ftc.teamcode.framework.util.Odometry;

import org.firstinspires.ftc.teamcode.framework.util.Maths.Rotation2d;

public class ChassisSpeeds {

    public double vxInchesPerSecond;
    public double vyInchesPerSecond;
    public double omegaRadiansPerSecond;

    public ChassisSpeeds() {}

    public ChassisSpeeds(double vxInchesPerSecond, double vyInchesPerSecond, double omegaRadiansPerSecond) {
        this.vxInchesPerSecond = vxInchesPerSecond;
        this.vyInchesPerSecond = vyInchesPerSecond;
        this.omegaRadiansPerSecond = omegaRadiansPerSecond;
    }

    public static ChassisSpeeds fromFieldRelativeSpeeds(double vxInchesPerSecond, double vyInchesPerSecond, double omegaRadiansPerSecond, Rotation2d robotAngle) {
        return new ChassisSpeeds(
                vxInchesPerSecond * robotAngle.getCos() + vyInchesPerSecond * robotAngle.getSin(),
                -vxInchesPerSecond * robotAngle.getSin() + vyInchesPerSecond * robotAngle.getCos(),
                omegaRadiansPerSecond);
    }
}
