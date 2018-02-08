package gui;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import graphics.JTurtle;
import javafx.scene.paint.Color;
import lsystem.LSystem;

import java.util.HashSet;
import java.util.Set;

public class Controller {
    @FXML
    private Canvas canvas;

    public void initialize() {
        canvas.setWidth(5000);
        canvas.setHeight(5000);

        final GraphicsContext gfx = canvas.getGraphicsContext2D();
        final JTurtle turtle = new JTurtle(new Point2D(canvas.getWidth() / 2, canvas.getHeight() / 2), 90);

        turtle.setGraphics(gfx);
        turtle.penDown();

        final Set<Character> alphabet = new HashSet<>();

        alphabet.add('F');
        alphabet.add('f');
        alphabet.add('+');
        alphabet.add('-');
        alphabet.add('[');
        alphabet.add(']');

        final LSystem koch_island = new LSystem(alphabet, "F-F-F-F");
        koch_island.addProduction("F", "F+FF-FF-F-F+F+FF-F-F+F+FF+FF-F");

        final LSystem koch_snowflake = new LSystem(alphabet, "-F");
        koch_snowflake.addProduction("F", "F+F-F-F+F");

        final LSystem kelp_one = new LSystem(alphabet, "F");
        kelp_one.addProduction("F", "F[+F]F[-F]F");

        final LSystem kelp_two = new LSystem(alphabet, "F");
        kelp_two.addProduction("F", "FF-[-F+F+F]+[+F-F-F]");

        turtle.changeColor(Color.GREEN);
        drawProduction(kelp_two.generateNthProduction(4), turtle, 6, 27.5f);
    }

    private void drawProduction(String s, JTurtle t, int l, float ang) {
        for (int i = 0; i < s.length(); i++) {
            switch(s.charAt(i)) {
                case 'F':
                    t.penDown();
                    t.move(l);
                    break;
                case 'f':
                    t.penUp();
                    t.move(l);
                    break;
                case '+':
                    t.turn(ang);
                    break;
                case '-':
                    t.turn(-ang);
                    break;
                case '[':
                    t.pushSetting();
                    break;
                case ']':
                    t.popSetting();
                    break;
                default:
                    break;
            }
        }
    }
}
