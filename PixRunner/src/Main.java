import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("PixRunner");
        primaryStage.getIcons().add(new Image("file:img/pix.png"));
        Group root = new Group();
        //Pane pane = new Pane(root);
        //Scene gameScene = new Scene(pane, 600, 400, true);

        Camera camera = new Camera(0, 0);
        GameScene gameScene = new GameScene(root, 800,400,camera);

        primaryStage.setScene(gameScene);
        System.out.println(gameScene.gettingCamera());
        primaryStage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long time) {
                gameScene.update(time);
                gameScene.pix.updateAnimation(time, gameScene.jumping, gameScene.falling);
                gameScene.render();

            }
        };
        timer.start();


    }

    public static void main(String[] args) {
        launch(args);
    }
}

