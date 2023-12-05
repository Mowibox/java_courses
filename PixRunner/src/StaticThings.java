import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class StaticThings {

    private double sizeX, sizeY;
    ImageView imageView;

    public StaticThings(double sizeX, double sizeY, String filename){
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        //Image creation
        Image image = new Image(filename);
        this.imageView = new ImageView(image);
        this.imageView.setX(sizeX);
        this.imageView.setY(sizeY);


    }
    public ImageView getSprite(){
        return imageView;
    }

}
