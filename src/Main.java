import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Main extends JPanel {

    //general environment
    public static final int FRAMEWIDTH = 1400, FRAMEHEIGHT = 600;
    private Timer timer;
    private int speed = 10;
    private boolean[] keys;


    //buffered images
    private Bird bird;
    private BufferedImage ground;
    private BufferedImage city;


    private ArrayList<Sprite> pipes;

    public Main() {
        keys = new boolean[512];

        try {
            ground = ImageIO.read(new File("res/" + "ground.png"));
            city = ImageIO.read(new File("res/" + "citybackground.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        bird = new Bird();

        pipes = new ArrayList();
        //pair 1
        pipes.add(new Pipe(1100, 350, Sprite.WEST, 0));
        pipes.add(new Pipe(1100, 0, Sprite.WEST, 1));
        //pair 2
        pipes.add(new Pipe(1400, 200, Sprite.WEST, 2));
        pipes.add(new Pipe(1400, 0, Sprite.WEST, 3));
        //pair 3
        pipes.add(new Pipe(800, 300, Sprite.WEST, 4));
        pipes.add(new Pipe(800, 0, Sprite.WEST, 5));
        //pair 4
        pipes.add(new Pipe(425, 350, Sprite.WEST, 0));
        pipes.add(new Pipe(425, 0, Sprite.WEST, 1));


        timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                boolean dead = false;
                for(Sprite pipe : pipes){ //if the bird hits a pipe it dies
                   if(bird.intersects(pipe)){
                       dead = true;
                       timer.stop();
                   }
                }

                //if a bird hits the ground it dies
                if(bird.getLoc().y>=470) {
                    dead = true;
                    timer.stop();
                }


                //bird is constantly falling
                bird.getLoc().y += speed;

                for (Sprite pipe : pipes)
                    pipe.update();


                moveBird();

                repaint(); //keep as last line
            }
        });
        timer.start();

        setKeyListener();
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //drawing the background
        g2.drawImage(ground, 0, 500, null);
        g2.drawImage(city, 0, 0, null);

        //drawing the pipes
        for (Sprite pipe : pipes)
            pipe.draw(g2);

        //drawing the score
        g2.setColor(Color.white);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
        g2.drawString("SCORE: ", 35, 50);

        //drawing the bird
        bird.draw(g2);

    }

    public void moveBird() {
        if (keys[KeyEvent.VK_SPACE] || keys[KeyEvent.VK_UP]) {
            bird.setDir(Sprite.SOUTH);
            bird.getLoc().y -= speed;
            bird.update();
            keys[KeyEvent.VK_SPACE] = false;
            keys[KeyEvent.VK_UP] = false;
        }

    }

    public void setKeyListener() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {/*intentionally left blank*/ }

            //when a key is pressed, its boolean is switch to true.
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                keys[keyEvent.getKeyCode()] = true;
            }

            //when a key is released, its boolean is switched to false.
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                keys[keyEvent.getKeyCode()] = false;
            }
        });
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Flappy Bird!!");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(0, 0, FRAMEWIDTH, FRAMEHEIGHT + 22);
        window.setBackground(Color.cyan);
        Main panel = new Main();
        panel.setSize(FRAMEWIDTH, FRAMEHEIGHT);

        panel.setFocusable(true);
        panel.grabFocus();
        window.add(panel);
        window.setVisible(true);
        window.setResizable(false);
    }
}