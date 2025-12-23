package org.example.tsplviewer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/editor.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 1500, 900);
        stage.setScene(scene);
        stage.setTitle("TSPL Viewer");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
