import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class GameScene extends Scene {
    private final Camera camera;

    public GameScene(Pane root, Camera camera) {
        super(root);
        this.camera = camera;
    }

    public Camera gettingCamera() {
        return camera;
    }
}