import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void init() {
        System.out.println("Before");
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent parentLoginRoot = FXMLLoader.load(getClass().getResource("resources/loginform.fxml"));
        Scene loginScene = new Scene(parentLoginRoot);

        stage.setScene(loginScene);
        stage.show();
    }

    @Override
    public void stop() {
        System.out.println("After");
    }

    public static void main(String args[]) {
        Application.launch();
    }
}