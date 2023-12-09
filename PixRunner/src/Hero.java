
public class Hero extends AnimatedThings{

    private int x;

    public Hero(double x, double y, String filename, int attitude, int index, int duration, int maxIndex, double windowSizeX, double windowSizeY, double offsetX, double offsetY) {
        super(x, y, filename, attitude, index, duration, maxIndex, windowSizeX, windowSizeY, offsetX, offsetY);
    }

    public double getX() {
        return x;
    }




}


//To spice things up, the Camera is bound to the Hero via a spring-mass like equation system.
//So the camera has to memorise the reference to the Hero in order to calculate the return Force.
//In physics game simulation, we’ll never implement the solved equation. It’s far less time-consuming to
//implements the equation itself. Here’s the set of equation we wan’t to implements in one dimension :
//
//ax =(k/m).(xhero − x) + (f/m)vx
//        δvx = ax.δt
//δx = vx.δt
//
//
//On figure 5 on page 7, you’ll see an example of this equation implemented. Each sample being separated by 16 millisecond, you’ll see that in this configuration ( k/m = 1; f/m = 1.2), it takes almost 3 seconds to see the hero. This is too slow and you should find the right value.
//
//• Implements these 6 equations in the update method inside the Camera class.
//        • Modify the camera at the creation of the game to be slightly of the Hero.
//• Test your spring effect.
//
//        • Inspired by this example, modify the Hero so that he really runs, first with a constant speed.