import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class StaticThings {

    private double sizeX, sizeY;
    ImageView imageView;

    private ColorAdjust colorAdjust; //Color change functionality

    public StaticThings(double sizeX, double sizeY, String filename){
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        //Image creation
        Image image = new Image(filename);
        this.imageView = new ImageView(image);
        this.imageView.setX(sizeX);
        this.imageView.setY(sizeY);

        colorAdjust = new ColorAdjust();
        imageView.setEffect(colorAdjust);

    }
    public ImageView getSprite(){
        return imageView;
    }

    public void update(long posX)  {
        //Giving a new position for the background
        sizeX = posX;
        this.imageView.setX(sizeX);

    }

    public double getX() {
        return sizeX;
    }

    public double getY() {
        return sizeY;
    }

    public void changeColor(double hue) {
        colorAdjust.setHue(hue);
    }
}
