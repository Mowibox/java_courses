import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;

public class HelloWorld extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Group root = new Group();
        Scene scene = new Scene(root, 300,350);

        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args){
        launch(args);
    }

}
