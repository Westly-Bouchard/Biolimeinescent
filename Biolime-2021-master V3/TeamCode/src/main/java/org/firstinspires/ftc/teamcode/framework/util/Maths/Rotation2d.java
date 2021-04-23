package org.firstinspires.ftc.teamcode.framework.util.Maths;

public class Rotation2d {
    private double value;
    private double cos;
    private double sin;

    public Rotation2d() {
        this.value = 0.0;
        this.cos = 1.0;
        this.sin = 0.0;
    }

    public Rotation2d(double value) {
        this.value = value;
        this.cos = Math.cos(value);
        this.sin = Math.sin(value);
    }

    public Rotation2d(double x, double y) {
        double magnitude = Math.hypot(x, y);
        if (magnitude > 1e-6) {
            this.sin = y / magnitude;
            this.cos = x / magnitude;
        } else {
            this.sin = 0.0;
            this.cos = 1.0;
        }
        this.value = Math.atan2(sin, cos);
    }

    public static Rotation2d fromDegrees(double degrees) {
        return new Rotation2d(Math.toRadians(degrees));
    }

    public Rotation2d rotateBy(Rotation2d other) {
        return new Rotation2d(this.cos * other.cos - sin * other.sin, cos * other.sin + sin * other.cos);
    }

    public Rotation2d plus(Rotation2d other) {
        return rotateBy(other);
    }

    public Rotation2d minus(Rotation2d other) {
        return rotateBy(other.unaryMinus());
    }

    public Rotation2d unaryMinus() {
        return new Rotation2d(-this.value);
    }

    public Rotation2d times(double scalar) {
        return new Rotation2d(this.value * scalar);
    }

    public double getRadians() {
        return this.value;
    }

    public double getDegrees() {
        return Math.toDegrees(this.value);
    }

    public double getCos() {
        return this.cos;
    }

    public double getSin() {
        return this.sin;
    }

    public double getTan() {
        return this.sin / this.cos;
    }
}