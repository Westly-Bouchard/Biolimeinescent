package org.firstinspires.ftc.teamcode.framework.util.Maths;

public class Pose2d {
    private final Translation2d translation;
    private final Rotation2d rotation;

    public Pose2d() {
        translation = new Translation2d();
        rotation = new Rotation2d();
    }

    public Pose2d(Translation2d translation, Rotation2d rotation) {
        this.translation = translation;
        this.rotation = rotation;
    }

    public Pose2d(double x, double y, Rotation2d rotation) {
        this.translation = new Translation2d(x, y);
        this.rotation = rotation;
    }

    public Pose2d plus(Transform2d other) {
        return transformBy(other);
    }

    public Transform2d minus(Pose2d other) {
        final Pose2d pose = this.relativeTo(other);
        return new Transform2d(pose.getTranslation(), pose.getRotation());
    }

    public Translation2d getTranslation() {
        return this.translation;
    }

    public double getX() {
        return this.translation.getX();
    }

    public double getY() {
        return this.translation.getY();
    }

    public Rotation2d getRotation() {
        return this.rotation;
    }

    public Pose2d transformBy(Transform2d other) {
        return new Pose2d(translation.plus(other.getTranslation().rotateBy(rotation)), rotation.plus(other.getRotation()));
    }

    public Pose2d relativeTo(Pose2d other) {
        Transform2d transform = new Transform2d(other, this);
        return new Pose2d(transform.getTranslation(), transform.getRotation());
    }

    public Pose2d exp(Twist2d twist) {
        double dx = twist.dx;
        double dy = twist.dy;
        double dtheta = twist.dtheta;

        double sinTheta = Math.sin(dtheta);
        double cosTheta = Math.cos(dtheta);

        double s;
        double c;
        if (Math.abs(dtheta) < 1E-9) {
            s = 1.0 - 1.0 / 6.0 * dtheta * dtheta;
            c = 0.5 * dtheta;
        } else {
            s = sinTheta / dtheta;
            c = (1 - cosTheta) / dtheta;
        }
        Transform2d transform =
                new Transform2d(
                        new Translation2d(dx * s - dy * c, dx * c + dy * s),
                        new Rotation2d(cosTheta, sinTheta));

        return this.plus(transform);
    }

    public Twist2d log(Pose2d end) {
        final Pose2d transform = end.relativeTo(this);
        final double dtheta = transform.getRotation().getRadians();
        final double halfDtheta = dtheta / 2.0;

        final double cosMinusOne = transform.getRotation().getCos() - 1;

        double halfThetaByTanOfHalfDtheta;
        if (Math.abs(cosMinusOne) < 1E-9) {
            halfThetaByTanOfHalfDtheta = 1.0 - 1.0 / 12.0 * dtheta * dtheta;
        } else {
            halfThetaByTanOfHalfDtheta = -(halfDtheta * transform.getRotation().getSin()) / cosMinusOne;
        }

        Translation2d translationPart =
                transform
                        .getTranslation()
                        .rotateBy(new Rotation2d(halfThetaByTanOfHalfDtheta, -halfDtheta))
                        .times(Math.hypot(halfThetaByTanOfHalfDtheta, halfDtheta));

        return new Twist2d(translationPart.getX(), translationPart.getY(), dtheta);
    }
}
