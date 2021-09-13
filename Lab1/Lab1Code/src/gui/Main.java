package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("\\fxml\\single.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1200, 700));
        primaryStage.show();
    }

    static int modeIndx = 0;

    public static int getModeIndx() {
        return modeIndx;
    }

    public static void setModeIndx(int modeIndx) {
        Main.modeIndx = modeIndx;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
