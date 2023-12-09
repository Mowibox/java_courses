import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;


public class GameScene extends Scene {
    private Camera camera;
    private Group parent;

    //Adding background
    private StaticThings bgLeft, bgRight;
    private StaticThings heart;

    private int numberOfLives = 3;

    //Hero creation
    Hero pix;

    //Macros for images paths
    public static final String BACKGROUND_PATH = "file:img/background.png";
    public static final String HEART_PATH = "file:img/heart.png";
    public static final String PIX_SPRITE_SHEET = "file:img/pixspritesheet.png";

    private static final int DURATION_FACTOR = 3200000;

    private double hue = 0;

    public GameScene(Group parent, double v, double v1, Camera camera) {
        super(parent, v, v1);
        this.parent = parent;
        this.camera = camera;

        //Allow to display the background images
        this.bgLeft = new StaticThings(0,0,BACKGROUND_PATH);
        this.bgRight = new StaticThings(800,0,BACKGROUND_PATH);
        this.parent.getChildren().add(this.bgLeft.getSprite());
        this.parent.getChildren().add(this.bgRight.getSprite());

        //Lives displaying
        for(int i = 0; i < numberOfLives; i++) {
            this.heart = new StaticThings(70*i, 0, HEART_PATH);
            this.parent.getChildren().add(this.heart.getSprite());
        }

        //Hero creation
        this.pix = new Hero(50, 260, PIX_SPRITE_SHEET, 0, 0, 6, 85, 100,85,0);
        this.parent.getChildren().add(pix.getSprite());

        this.setOnKeyPressed(event -> {handleKeyPress(event.getCode());
        hue = handleKeyPress(event.getCode());
        // heart.changeColor(hue); //To fix later
        });



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
        time = (time / DURATION_FACTOR) % 800; //Loop is managed with the %

        //Left shifting
        long bgLeftX = -time;
        long bgRightX = 800 - time;
        this.bgLeft.update(bgLeftX);
        this.bgRight.update(bgRightX);


    }

    private double handleKeyPress(KeyCode code) {
        double hue = switch (code) {
            case X -> {
                System.out.println("Orange Pix");
                yield 0.25;
            }
            case C -> {
                System.out.println("Yellow Pix");
                yield 0.55;
            }
            case V -> {
                System.out.println("Green Pix");
                yield 0.9;
            }
            case B -> {
                System.out.println("Purple Pix");
                yield -0.25;
            }
            case N -> {
                System.out.println("Normal Pix");
                yield 0;
            }
            default -> 0;
        };
        pix.colorChange(hue);
        return hue;
    }



    public Camera gettingCamera() {
        return camera;
    }
}