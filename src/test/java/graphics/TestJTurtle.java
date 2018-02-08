package graphics;

import javafx.geometry.Point2D;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TestJTurtle {
    private static final double EPSILON = 0.0001;

    @Test
    public void testTurning() {
        JTurtle turtle = new JTurtle(new Point2D(0, 0), 0);
        turtle.turn(5);
        assertEquals(5.0, turtle.getAngle(), EPSILON);
        turtle.turn(10);
        assertEquals(15.0, turtle.getAngle(), EPSILON);
        turtle.turn(100);
        assertEquals(115.0, turtle.getAngle(), EPSILON);
        turtle.turn(275);
        assertEquals(30.0, turtle.getAngle(), EPSILON);
        turtle.turn(720);
        assertEquals(30.0, turtle.getAngle(), EPSILON);
        turtle.turn(-360);
        assertEquals(30.0, turtle.getAngle(), EPSILON);
        turtle.turn(-15);
        assertEquals(15.0, turtle.getAngle(), EPSILON);
        turtle.turn(75);
        assertEquals(90.0, turtle.getAngle(), EPSILON);
        turtle.turn(270);
        assertEquals(0.0, turtle.getAngle(), EPSILON);
        turtle.turn(30).turn(30).turn(-30);
        assertEquals(30.0, turtle.getAngle(), EPSILON);
    }

    @Test
    public void testMoving() {
        JTurtle turtle = new JTurtle(new Point2D(0, 0), 0);
        turtle.move(5);
        assertEquals(5, turtle.getPosition().getX(), EPSILON);
        assertEquals(0, turtle.getPosition().getY(), EPSILON);
        turtle.turn(90);
        assertEquals(5, turtle.getPosition().getX(), EPSILON);
        assertEquals(0, turtle.getPosition().getY(), EPSILON);
        turtle.move(5);
        assertEquals(5, turtle.getPosition().getX(), EPSILON);
        assertEquals(5, turtle.getPosition().getY(), EPSILON);
        turtle.turn(90);
        assertEquals(5, turtle.getPosition().getX(), EPSILON);
        assertEquals(5, turtle.getPosition().getY(), EPSILON);
        turtle.move(5);
        assertEquals(0, turtle.getPosition().getX(), EPSILON);
        assertEquals(5, turtle.getPosition().getY(), EPSILON);
        turtle.turn(90);
        assertEquals(0, turtle.getPosition().getX(), EPSILON);
        assertEquals(5, turtle.getPosition().getY(), EPSILON);
        turtle.move(5);
        assertEquals(0, turtle.getPosition().getX(), EPSILON);
        assertEquals(0, turtle.getPosition().getY(), EPSILON);
        turtle.turn(270);
        turtle.move(11);
        assertEquals(-11, turtle.getPosition().getX(), EPSILON);
        assertEquals(0, turtle.getPosition().getY(), EPSILON);
        turtle.turn(180).move(10);
        assertEquals(-1, turtle.getPosition().getX(), EPSILON);
        assertEquals(0, turtle.getPosition().getY(), EPSILON);
        turtle.move(1);
        assertEquals(0, turtle.getPosition().getX(), EPSILON);
        assertEquals(0, turtle.getPosition().getY(), EPSILON);
    }
}
