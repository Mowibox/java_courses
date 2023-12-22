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
        //Title and icons
        primaryStage.setTitle("PixRunner");
        primaryStage.getIcons().add(new Image("file:img/pix.png"));

        Group root = new Group();

        Camera camera = new Camera(0, 0);
        GameScene gameScene = new GameScene(root, 1360,750,camera);

        primaryStage.setScene(gameScene);
        primaryStage.show();

        //Updating game elements
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long time) {
                gameScene.update(time);
                gameScene.pix.updateAnimation(time, gameScene.jumping, gameScene.falling, gameScene.doubleJump, gameScene.vy);
                gameScene.render();
            }
        };

        timer.start();


    }

    public static void main(String[] args) {
        launch(args);
    }
}

