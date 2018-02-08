import graphics.JTurtle;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Point2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lsystem.LSystem;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomSampler extends Application {
    @Override
    public void start(Stage stage) {
        final int canvas_height = 1080;
        final int canvas_width = 1920;
        final Canvas canvas = new Canvas(canvas_width, canvas_height);
        final GraphicsContext gfx = canvas.getGraphicsContext2D();
        final SnapshotParameters snap_params = new SnapshotParameters();
        final Set<Character> valid_chars = new HashSet<>();
        Runnable r;

        valid_chars.add('F');
        valid_chars.add('T');
        valid_chars.add('-');
        valid_chars.add('+');
        valid_chars.add('[');
        valid_chars.add(']');

        for (int i = 0; i < 10; i++) {
            final LSystem l = new LSystem(valid_chars, "F-F-F-F");
            final String current_production = generateRandomFractalProduction();
            l.addProduction("F", current_production);

            for (int n = 2; n <= 4; n++) {
                final int N = n;

                r = () -> {
                    final JTurtle turtle = new JTurtle(new Point2D(canvas_width / 2, canvas_height / 2), 0);

                    turtle.setGraphics(gfx);

                    drawProduction(turtle, l.generateNthProduction(N));

                    gfx.setStroke(Color.RED);
                    gfx.strokeText(
                            String.format("F -> %s, theta: %d, len: %d, prod_iter: %d",
                                    current_production, 90, 5, N
                            ),
                            0, 30
                    );
                };

                saveImage(r, canvas, snap_params, String.format("%d-%d.png", i, n));

                gfx.clearRect(0, 0, canvas_width, canvas_height);
            }
        }

        System.exit(0);
    }

    private static void drawProduction(JTurtle turtle, String production) {
        turtle.penDown();
        for (int i = 0; i < production.length(); i++) {
            switch(production.charAt(i)) {
                case 'F':
                    turtle.move(5); break;
                case 'T':
                    turtle.changeColor(Color.RED);
                    turtle.move(5);
                    turtle.changeColor(Color.BLACK);
                    break;
                case '-':
                    turtle.turn(-90); break;
                case '+':
                    turtle.turn(90); break;
                case '[':
                    turtle.pushSetting(); break;
                case ']':
                    turtle.popSetting(); break;
                default: break;
            }
        }
    }

    private static void saveImage(Runnable r, Canvas c, SnapshotParameters snap, String img_name) {
        long before = System.nanoTime();
        r.run();
        System.out.println("drawing: " + (System.nanoTime() - before));

        try {
            ImageIO.write(SwingFXUtils.fromFXImage(c.snapshot(snap, null), null), "png", new File(img_name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String generateRandomFractalProduction() {
        final StringBuilder builder = new StringBuilder();
        final Random random = new Random();
        final int clumps = random.nextInt(8) + 3;

        /* number of F clumps to create [3, 10] */
        for (int i = 0; i < clumps; i++) {
            final int num_F = random.nextInt(4) + 1; // [1, 2]

            if (i == 1) {
                builder.append('[');
                builder.append(random.nextBoolean() ? '-' : '+');
            }

            if (i == 3) {
                builder.append(((((']')))));
            }

            for (int f = 0; f < num_F; f++) {
                builder.append('F');
            }

            if (Math.random() > 0.25) {
                builder.append('T');
            }

            final int num_s = random.nextInt(2) + 1;

            for (int s = 0; s < num_s; s++) {
                if (random.nextBoolean()) {
                    builder.append('+');
                } else {
                    builder.append('-');
                }
            }
        }

        return builder.toString();
    }
}
