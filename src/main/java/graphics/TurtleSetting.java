package graphics;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class TurtleSetting {
    private Point2D position;
    private Color color;
    private boolean isDrawing;
    private double angle;
    private int thickness;

    public TurtleSetting copy() {
        return new TurtleSetting()
                .setPosition(new Point2D(position.getX(), position.getY()))
                .setColor(color.interpolate(Color.BLACK, 0))
                .setDrawing(isDrawing).setAngle(angle).setThickness(thickness);
    }

    public Point2D getPosition() {
        return position;
    }

    public TurtleSetting setPosition(Point2D position) {
        this.position = position;
        return this;
    }

    public Color getColor() {
        return color;
    }

    public TurtleSetting setColor(Color color) {
        this.color = color;
        return this;
    }

    public boolean isDrawing() {
        return isDrawing;
    }

    public TurtleSetting setDrawing(boolean drawing) {
        isDrawing = drawing;
        return this;
    }

    public double getAngle() {
        return angle;
    }

    public TurtleSetting setAngle(double angle) {
        this.angle = angle;
        return this;
    }

    public int getThickness() {
        return thickness;
    }

    public TurtleSetting setThickness(int thickness) {
        this.thickness = thickness;
        return this;
    }
}
