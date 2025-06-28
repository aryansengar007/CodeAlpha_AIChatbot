package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static final boolean USE_DARK_THEME = true;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/chatbot.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 600, 700);

        if (USE_DARK_THEME) {
            scene.getStylesheets().add(getClass().getResource("/style-dark.css").toExternalForm());
        } else {
            scene.getStylesheets().add(getClass().getResource("/style-light.css").toExternalForm());
        }

        stage.setTitle("AI Chatbot");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}