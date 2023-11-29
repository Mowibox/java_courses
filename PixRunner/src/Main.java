import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("PixRunner");
        Group root = new Group();
        Pane pane = new Pane(root);
        //Scene gameScene = new Scene(pane, 600, 400, true);

        Camera camera = new Camera(600, 600);
        GameScene gameScene = new GameScene(pane, camera);

        primaryStage.setScene(gameScene);
        System.out.println(gameScene.gettingCamera());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

