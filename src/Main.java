import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Main extends JPanel {

    public static final int FRAMEWIDTH = 1400, FRAMEHEIGHT = 600;
    private Timer timer;

    //buffered images
    private BufferedImage bird;
    private BufferedImage ground;
    private BufferedImage city;

    private ArrayList<Sprite> pipes;

    public Main(){

        try {
            bird = ImageIO.read(new File("res/" + "bird.png"));
            ground = ImageIO.read(new File("res/" + "ground.png"));
            city = ImageIO.read(new File("res/" + "citybackground.png"));
        }catch (Exception e){e.printStackTrace();}


        pipes = new ArrayList();
        //pair 1
        pipes.add(new Pipe(1100,350,Sprite.WEST,0));
        pipes.add(new Pipe(1100,0,Sprite.WEST,1));
        //pair 2
        pipes.add(new Pipe(1400,200,Sprite.WEST,2));
        pipes.add(new Pipe(1400,0,Sprite.WEST,3));
        //pair 3
        pipes.add(new Pipe(800,300,Sprite.WEST,4));
        pipes.add(new Pipe(800,0,Sprite.WEST,5));
        //pair 4
        pipes.add(new Pipe(425,350,Sprite.WEST,0));
        pipes.add(new Pipe(425,0,Sprite.WEST,1));


        timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

//                boolean dead = false;
//                for(Sprite pipe : pipes){
//                   if(bird.intersects(pipe)){
//                       dead = true;
//                       timer.stop();
//                   }
//                }


                for(Sprite pipe: pipes)
                    pipe.update();


                repaint(); //always last line!!
            }
        });
        timer.start();

    }



    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //drawing the background
        g2.drawImage(ground,0,500,null);
        g2.drawImage(city,0,0,null);

        //drawing the pipes
        for(Sprite pipe : pipes)
            pipe.draw(g2);

        //drawing the score
        g2.setColor(Color.white);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
        g2.drawString("SCORE: ",35,50);

        //drawing the bird
        g2.drawImage(bird,75,250,null);

    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Flappy Bird!!");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(0,0,FRAMEWIDTH,FRAMEHEIGHT+22);
        window.setBackground(Color.cyan);
        Main panel = new Main();
        panel.setSize(1000,600);

        panel.setFocusable(true);
        panel.grabFocus();
        window.add(panel);
        window.setVisible(true);
        window.setResizable(false);
    }

}
