import java.awt.*;

public class Bird extends Sprite{

    public Bird(){
        super(400,400,SOUTH);
        setPic("bird.png",SOUTH);
        setSpeed(20);
    }

    public boolean intersects(Sprite other) {
        return getBoundingRectangle().intersects(other.getBoundingRectangle());
    }

    @Override
    public void update(){
    }

}