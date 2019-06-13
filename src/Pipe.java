import java.awt.*;

public class Pipe extends Sprite{

    public Pipe(int x, int y, int direction, int img){

        super(x,y,direction);

        if(img==0)
            setPic("bottompipe1.png", WEST);
        if(img==1)
            setPic("toppipe1.png", WEST);
        if(img==2)
            setPic("bottompipe2.png", WEST);
        if(img==3)
            setPic("toppipe2.png", WEST);
        if(img==4)
            setPic("bottompipe3.png", WEST);
        if(img==5)
            setPic("toppipe3.png", WEST);


        setSpeed(3);
    }

    @Override
    public void update() {
        super.update();

        if (getLoc().x > Main.FRAMEWIDTH)
            setLoc(new Point(0, getLoc().y));

        if (getLoc().x < 0)
            setLoc(new Point(1400, getLoc().y));

    }

}
