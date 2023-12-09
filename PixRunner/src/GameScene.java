import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

public class GameScene extends Scene {
    private Camera camera;
    private Group parent;

    //Adding background
    private StaticThings bgLeft, bgRight;
    private StaticThings heart;

    private int numberOfLives = 3;

    //Hero creation
    Hero pix;

    private int index = 0;

    public GameScene(Group parent, double v, double v1, Camera camera) {
        super(parent, v, v1);
        this.parent = parent;
        this.camera = camera;
        this.bgLeft = new StaticThings(0,0,"file:img/desert.png");
        this.bgRight = new StaticThings(800,0,"file:img/desert.png");
        //Allow to display the background images
        this.parent.getChildren().add(this.bgLeft.getSprite());
        this.parent.getChildren().add(this.bgRight.getSprite());

        //Lives displaying
        for(int i = 0; i < numberOfLives; i++) {
            this.heart = new StaticThings(70*i, 0, "file:img/heart.png");
            this.parent.getChildren().add(this.heart.getSprite());
        }

        this.pix = new Hero(50, 260, "file:img/heros.png", 0, 0, 100, 3, 85, 100,85,0);
        this.parent.getChildren().add(pix.getSprite());

    }

    public void render() {
        double cameraX = camera.getX();
        double cameraY = camera.getY();

        for (Node node : parent.getChildren()) {
            if (node instanceof ImageView) {
                ImageView sprite = (ImageView) node;
                double originalX = sprite.getX();
                double originalY = sprite.getY();

                sprite.setX(originalX - cameraX);
                sprite.setY(originalY - cameraY);
            }
        }
    }

    public void update(long time) {
        time = (time / 3200000) % 800;
        long bgLeftX = -time;
        long bgRightX = 800 - time;
        this.bgLeft.update(bgLeftX);
        this.bgRight.update(bgRightX);


    }

    public Camera gettingCamera() {
        return camera;
    }
}