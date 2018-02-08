package graphics;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;

public class JTurtle {
    private final Stack<TurtleSetting> stack;
    private GraphicsContext gfx;
    private TurtleSetting setting;

    public JTurtle(Point2D start, double angle) {
        this.stack = new Stack<>();
        this.setting = new TurtleSetting();
        this.setting.setPosition(start).setAngle(angle).setColor(Color.BLACK).setThickness(1);
    }

    public JTurtle setGraphics(GraphicsContext gfx) {
        this.gfx = gfx;
        return this;
    }

    public JTurtle penUp() {
        this.setting.setDrawing(false);
        return this;
    }

    public JTurtle penDown() {
        if (this.gfx == null) {
            throw new IllegalStateException("must set graphics before drawing");
        }

        this.setting.setDrawing(true);
        return this;
    }

    public JTurtle changeColor(Color c) {
        if (c == null) {
            throw new IllegalArgumentException();
        }

        this.setting.setColor(c);
        return this;
    }

    public JTurtle changeThickness(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException();
        }

        this.setting.setThickness(i);
        return this;
    }

    private int draw_call_count = 0;
    private int missed_count = 0;

    /**
     * Move the turtle from its current position by d units.
     * @param d The amount by which to move the turtle. Must be positive.
     */
    public JTurtle move(double d) {
        if (d <= 0) {
            return this;
        }

        double radianAngle = Math.toRadians(this.setting.getAngle());
        double oldX = this.setting.getPosition().getX();
        double oldY = this.setting.getPosition().getY();
        double newX = oldX + (d * Math.cos(radianAngle));
        double newY = oldY - (d * Math.sin(radianAngle)); // to make it work with the backwards graphics

        if (this.setting.isDrawing()) {
            gfx.setFill(this.setting.getColor());
            gfx.setLineWidth(this.setting.getThickness());
            gfx.setStroke(this.setting.getColor());
            gfx.strokeLine(oldX, oldY, newX, newY);
        }

        this.setting.setPosition(new Point2D(newX, newY));
        return this;
    }

    /**
     * Turn the turtle by d degrees.
     * @param d The number of degrees by which to turn. Negative is to the left,
     *          positive is to the right.
     */
    public JTurtle turn(double d) {
        double new_angle = this.setting.getAngle() + d;

        if (new_angle > 0) {
            new_angle = new_angle % 360;
        } else {
            new_angle = 360 - Math.abs(new_angle % 360);
        }

        this.setting.setAngle(new_angle);
        return this;
    }

    public JTurtle pushSetting() {
        this.stack.push(this.setting.copy());
        return this;
    }

    public JTurtle popSetting() {
        if (!this.stack.empty()) {
            this.setting = this.stack.pop();
        }
        return this;
    }

    public double getAngle() {
        return this.setting.getAngle();
    }

    public Point2D getPosition() {
        return this.setting.getPosition();
    }
}