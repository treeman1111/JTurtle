package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;

public class Start extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(Paths.get("src/main/java/gui/view.fxml").toUri().toURL());
        primaryStage.setTitle("Turtle Graphics");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
