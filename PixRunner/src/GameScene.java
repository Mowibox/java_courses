import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class GameScene extends Scene {
    private Camera camera;
    private Group parent;

    //Adding background
    private StaticThings bgLeft, bgRight;

    public GameScene(Group parent, double v, double v1, Camera camera) {
        super(parent, v, v1);
        this.parent = parent;
        this.camera = camera;
        this.bgLeft = new StaticThings(0,0,"file:img/desert.png");
        this.bgRight = new StaticThings(800,0,"file:img/desert.png");

        //Allow to display the background images
        this.parent.getChildren().add(this.bgLeft.getSprite());
        this.parent.getChildren().add(this.bgRight.getSprite());
    }

    public Camera gettingCamera() {
        return camera;
    }
}