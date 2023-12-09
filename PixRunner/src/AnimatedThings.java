import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class AnimatedThings {

    private double x, y;
    private ImageView sprite;
    private int attitude;
    private int index;
    private int duration;
    private int maxIndex;
    private double windowSizeX, windowSizeY;
    private double offsetX, offsetY;

    private static final int DURATION_FACTOR = 85000000;
    private static final int ATTITUDE_OFFSET = 150;

    public AnimatedThings(double x, double y, String filename, int attitude, int index,
                         int duration, int maxIndex, double windowSizeX, double windowSizeY,
                         double offsetX, double offsetY) {
        this.x = x;
        this.y = y;
        this.attitude = attitude;
        this.index = index;
        this.duration = duration;
        this.maxIndex = 6;
        this.windowSizeX = windowSizeX;
        this.windowSizeY = windowSizeY;
        this.offsetX = offsetX;
        this.offsetY = offsetY;

        // Loading the image
        Image image = new Image(filename);
        sprite = new ImageView(image);
        this.sprite.setViewport(new Rectangle2D(offsetX * (index+1) + index * windowSizeX,(offsetY + windowSizeY) * attitude , windowSizeX, windowSizeY));
        this.sprite.setX(x);
        this.sprite.setY(y);


    }

    public void setIndex(int i){
        index = i;
    }

    public ImageView getSprite() {
        return sprite;
    }

    public void updateAnimation(long time) {

        this.setIndex((int) (time/DURATION_FACTOR%maxIndex));

            this.sprite.setViewport(new Rectangle2D(offsetX*index,ATTITUDE_OFFSET*attitude,windowSizeX,windowSizeY));

    }



}
