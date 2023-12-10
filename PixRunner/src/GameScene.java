import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class GameScene extends Scene {
    private Camera camera;
    private Group parent;

    //Adding background
    private StaticThings bgLeft, bgRight;
    private StaticThings heart;

    private int numberOfLives = 3;

    //Hero creation
    Hero pix;

    //Macros for images/music paths
    public static final String BACKGROUND_PATH = "file:img/background.png";
    public static final String HEART_PATH = "file:img/heart.png";
    public static final String PIX_SPRITE_SHEET = "file:img/pixspritesheet.png";
    public static final String MUSIC_PATH = "file:/C:/Workspace/Git_folders/java_courses/PixRunner/sound/theme.mp3";
    public static final String SOUND_PATH = "file:/C:/Workspace/Git_folders/java_courses/PixRunner/sound/colorswap.mp3";

    public static final String JUMP_SOUND = "file:/C:/Workspace/Git_folders/java_courses/PixRunner/sound/jump.wav";

    private static final int DURATION_FACTOR = 3200000;

    private double hue;
    private MediaPlayer mediaPlayer, mediaPlayer2, mediaPlayer3;

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

        this.setOnKeyPressed(event -> {handleKeyPress(event.getCode(), hue);
        hue = handleKeyPress(event.getCode(), hue);
        // heart.changeColor(hue); //To fix later
        });

        //Music management
        Media music = new Media(MUSIC_PATH);
        mediaPlayer = new MediaPlayer(music);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); //Music loop
        mediaPlayer.setVolume(0.5);
        mediaPlayer.play();



        this.setOnMouseClicked( (event)->{
            System.out.println("Jump");
            Media jumpSound = new Media(JUMP_SOUND);
            mediaPlayer3 = new MediaPlayer(jumpSound);
            mediaPlayer3.setVolume(0.7);
            mediaPlayer3.play();

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

    private double handleKeyPress(KeyCode code, double currentHue) {
        Media sound = new Media(SOUND_PATH);
        mediaPlayer2 = new MediaPlayer(sound);
        double hue = switch (code) {
            case X -> {
                System.out.println("Orange Pix");
                mediaPlayer2.play();
                yield 0.25;
            }
            case C -> {
                System.out.println("Yellow Pix");
                mediaPlayer2.play();
                yield 0.55;
            }
            case V -> {
                System.out.println("Green Pix");
                mediaPlayer2.play();
                yield 0.9;
            }
            case B -> {
                System.out.println("Purple Pix");
                mediaPlayer2.play();
                yield -0.25;
            }
            case N -> {
                System.out.println("Normal Pix");
                mediaPlayer2.play();
                yield 0;
            }
            default -> currentHue;
        };
        pix.colorChange(hue);
        return hue;
    }



    public Camera gettingCamera() {
        return camera;
    }
}