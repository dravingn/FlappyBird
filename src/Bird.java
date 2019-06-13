import java.awt.*;

public class Bird extends Sprite{

    public Bird(){
        super(75,250,SOUTH);
        setPic("bird.png",SOUTH);
        setSpeed(4);
    }



    @Override
    public void update(){
    }

}