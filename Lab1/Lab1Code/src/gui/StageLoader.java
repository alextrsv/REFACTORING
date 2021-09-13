package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StageLoader {

    static void loadStage(Stage stage, String sceneFxml){
        Parent root = null;
        try {
            root = FXMLLoader.load(StageLoader.class.getResource(sceneFxml));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Hello World");
        stage.setScene(new Scene(root, 1200, 700));
    }
}
