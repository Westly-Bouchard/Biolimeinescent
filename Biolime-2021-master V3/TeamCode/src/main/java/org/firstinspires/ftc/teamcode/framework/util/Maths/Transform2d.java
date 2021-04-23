package org.firstinspires.ftc.teamcode.framework.util.Maths;

public class Transform2d {
    private final Translation2d translation;
    private final Rotation2d rotation;


    public Transform2d(Pose2d initial, Pose2d last) {
        this.translation = last.getTranslation().minus(initial.getTranslation()).rotateBy(initial.getRotation().unaryMinus());
        this.rotation = last.getRotation().minus(initial.getRotation());
    }

    public Transform2d(Translation2d translation, Rotation2d rotation) {
        this.translation = translation;
        this.rotation = rotation;
    }

    public Transform2d() {
        translation = new Translation2d();
        rotation = new Rotation2d();
    }

    public Transform2d times(double scalar) {
        return new Transform2d(translation.times(scalar), rotation.times(scalar));
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

    public Transform2d inverse() {
        return new Transform2d(getTranslation().unaryMinus().rotateBy(getRotation().unaryMinus()), getRotation().unaryMinus());
    }
}
