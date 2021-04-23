package org.firstinspires.ftc.teamcode.framework.util.Maths;

public class Translation2d {
    private final double x;
    private final double y;

    public Translation2d() {
        this(0.0, 0.0);
    }

    public Translation2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Translation2d(double distance, Rotation2d angle) {
        this.x = distance * angle.getCos();
        this.y = distance * angle.getSin();
    }

    public double getDistance(Translation2d other) {
        return Math.hypot(other.x - this.x, other.y - this.y);
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getNorm() {
        return Math.hypot(this.x, this.y);
    }

    public Translation2d rotateBy(Rotation2d other) {
        return new Translation2d(x * other.getCos() - y * other.getSin(), x * other.getSin() + y * other.getCos());
    }

    public Translation2d plus(Translation2d other) {
        return new Translation2d(this.x + other.x, this.y + other.y);
    }

    public Translation2d minus(Translation2d other) {
        return new Translation2d(this.x - other.x, this.y - other.y);
    }

    public Translation2d unaryMinus() {
        return new Translation2d(-this.x, -this.y);
    }

    public Translation2d times(double scalar) {
        return new Translation2d(this.x * scalar, this.y * scalar);
    }

    public Translation2d div(double scalar) {
        return new Translation2d(this.x / scalar, this.y / scalar);
    }
}
