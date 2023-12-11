import javafx.geometry.Rectangle2D;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class AnimatedThings {

    private double x, y;
    private ImageView sprite;
    private int attitude;
    private int index;
    private int maxIndex;
    private double windowSizeX, windowSizeY;
    private double offsetX, offsetY;

    private static final int DURATION_FACTOR = 85000000;
    private static final int ATTITUDE_OFFSET = 150; //y-offset between vertical sprites

    private ColorAdjust pixColor; //Color change functionality

    public AnimatedThings(double x, double y, String filename, int attitude, int index, int maxIndex, double windowSizeX, double windowSizeY,
                         double offsetX, double offsetY) {
        this.x = x;
        this.y = y;
        this.attitude = attitude;
        this.index = index;
        this.maxIndex = maxIndex;
        this.windowSizeX = windowSizeX;
        this.windowSizeY = windowSizeY;
        this.offsetX = offsetX;

        // Loading the image
        Image image = new Image(filename);
        sprite = new ImageView(image);
        this.sprite.setViewport(new Rectangle2D(offsetX * (index+1) + index * windowSizeX,(offsetY + windowSizeY) * attitude , windowSizeX, windowSizeY));
        this.sprite.setX(x);
        this.sprite.setY(y);

        pixColor = new ColorAdjust();
        sprite.setEffect(pixColor);

    }

    public ImageView getSprite() {
        return sprite;
    }

    public void setIndex(int i){
        index = i;
    }

    public void updateAnimation(long time, boolean jumping, boolean falling) {
        if(jumping && !falling){
            attitude = 1;
            this.setIndex(0);
            this.sprite.setViewport(new Rectangle2D(offsetX * index, ATTITUDE_OFFSET * attitude, windowSizeX, windowSizeY));

        } else if (!jumping && falling) {
            attitude = 1;
            this.setIndex(1);
            this.sprite.setViewport(new Rectangle2D(offsetX * index, ATTITUDE_OFFSET * attitude, windowSizeX, windowSizeY));
        }
        else {
            attitude = 0;
            this.setIndex((int) (time / DURATION_FACTOR % maxIndex)); //Determines the displayed frame index
            this.sprite.setViewport(new Rectangle2D(offsetX * index, ATTITUDE_OFFSET * attitude, windowSizeX, windowSizeY));
        }

    }

    public void colorChange(double hue){
        pixColor.setHue(hue);
    }






}
