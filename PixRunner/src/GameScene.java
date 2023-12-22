import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class GameScene extends Scene {
    private Camera camera;
    private Group parent;

    //Adding background
    private StaticThings bgLeft, bgRight;

    private int numberOfLives = 3;
    private List<StaticThings> hearts = new ArrayList<>();
    double vy;

    //Hero creation
    Hero pix;

    //Macros for images/music paths
    public static final String BACKGROUND_PATH = "file:img/background.png";
    public static final String HEART_PATH = "file:img/heart.png";
    public static final String PIX_SPRITE_SHEET = "file:img/pixspritesheet.png";
    public static final String BUTTONS_PATH = "file:img/buttons.png";
    public static String MUSIC_PATH, SOUND_PATH, JUMP_SOUND;

    String os = System.getProperty("os.name").toLowerCase();

    static {
        String os = System.getProperty("os.name").toLowerCase();
        // Use different path separators based on the operating system
        String pathSeparator = os.contains("win") ? "\\" : "/";

        MUSIC_PATH = Paths.get("sound" + pathSeparator + "theme.mp3").toUri().toString();
        SOUND_PATH = Paths.get("sound" + pathSeparator + "colorswap.mp3").toUri().toString();
        JUMP_SOUND = Paths.get("sound" + pathSeparator + "jump.wav").toUri().toString();
    }
    private MediaPlayer mediaPlayer, mediaPlayer2, mediaPlayer3;

    private static final int DURATION_FACTOR = 3200000;
    private static final int GROUND = 550;

    boolean jumping = false;
    boolean falling = false;
    int doubleJump = 1;

    private StaticThings buttons;
    private double hue;



    public GameScene(Group parent, double v, double v1, Camera camera) {
        super(parent, v, v1);
        this.parent = parent;
        this.camera = camera;

        //Allow to display the background images
        this.bgLeft = new StaticThings(0,0,BACKGROUND_PATH);
        this.bgRight = new StaticThings(1360,0,BACKGROUND_PATH);
        this.parent.getChildren().add(this.bgLeft.getSprite());
        this.parent.getChildren().add(this.bgRight.getSprite());

        initializeHearts();

        //Hero (Pix) creation
        this.pix = new Hero(50, GROUND, PIX_SPRITE_SHEET, 0, 0, 6, 85, 100,85,0);
        this.parent.getChildren().add(pix.getSprite());

        //Pix color changing management
        this.setOnKeyPressed(event -> {handleKeyPress(event.getCode(), hue);
        hue = handleKeyPress(event.getCode(), hue);
        });

        //Buttons
        this.buttons = new StaticThings(0,0,BUTTONS_PATH);
        this.parent.getChildren().add(this.buttons.getSprite());

        //Music management
        Media music = new Media(MUSIC_PATH);
        mediaPlayer = new MediaPlayer(music);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); //Music loop
        mediaPlayer.setVolume(0.5);
        mediaPlayer.play();

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
        long elapsedTime = (time / DURATION_FACTOR) % 1360; //Loop is managed with the %

        //Left shifting
        long bgLeftX = -elapsedTime;
        long bgRightX = 1360 - elapsedTime;
        this.bgLeft.update(bgLeftX);
        this.bgRight.update(bgRightX);

        updateJump();
        setNumberOfLives(numberOfLives);

    }

    public void jump() {
        if (!jumping && !falling) {
            jumping = true;
            //Vertical burst
            vy = os.contains("win") ? -13 : -4;

        }
    }

    public void updateJump() {
        if (jumping && !falling) {
            if (doubleJump >= 1){
                jumping = false;
                doubleJump--;
            }
            if (vy > 0){
                falling = true;
            }
        }
        //Gravity effect
        vy += os.contains("win") ? 0.35 : 0.036;

        pix.getSprite().setY(pix.getSprite().getY() + vy);

        if(pix.getSprite().getY() >= GROUND){
                pix.getSprite().setY(GROUND);
                doubleJump = 1;
                falling = false;
        }
    }

    private double handleKeyPress(KeyCode code, double currentHue) {
        Media sound = new Media(SOUND_PATH);
        mediaPlayer2 = new MediaPlayer(sound);
        double hue = switch (code) {
            case G -> {
                System.out.println("Orange Pix");
                mediaPlayer2.play();
                yield 0.25;
            }
            case H -> {
                System.out.println("Yellow Pix");
                mediaPlayer2.play();
                yield 0.55;
            }
            case J -> {
                System.out.println("Green Pix");
                mediaPlayer2.play();
                yield 0.9;
            }
            case V -> {
                System.out.println("Blue Pix");
                mediaPlayer2.play();
                yield -0.70;
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
        

        if (code == KeyCode.SPACE || code == KeyCode.UP) {
            if (!jumping && !falling) {
                System.out.println("Jump");
                Media jumpSound = new Media(JUMP_SOUND);
                mediaPlayer3 = new MediaPlayer(jumpSound);
                mediaPlayer3.setVolume(0.3);
                mediaPlayer3.play();
                jump();
            }
        }

        return hue;
    }

    //Life management
    private void initializeHearts() {
        for (int i = 0; i < numberOfLives; i++) {
            StaticThings heart = new StaticThings(70 * i, 0, HEART_PATH);
            parent.getChildren().add(heart.getSprite());
            hearts.add(heart);
        }
    }

    private void updateHearts() {
        parent.getChildren().removeAll(hearts.stream().map(StaticThings::getSprite).toList());
        hearts.clear();
        for (int i = 0; i < numberOfLives; i++) {
            StaticThings heart = new StaticThings(70 * i, 0, HEART_PATH);
            heart.changeColor(hue);
            parent.getChildren().add(heart.getSprite());
            hearts.add(heart);
        }
    }

    public void setNumberOfLives(int newNumberOfLives) {
        numberOfLives = newNumberOfLives;
        updateHearts();
    }

}