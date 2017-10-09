
/**
 * Write a description of class Stage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Stage extends JComponent implements KeyListener
{
    private Ball missile;
    private Paddle[] shields;
    private Rectangle background;
    private Rectangle[] walls;
    private int top, bottom, left, right;
    private double speed;
    public int score1, score2;
    public Stage(int w, int h)
    {
        score1 = score2 = 0;
        top = 0 + 100;
        bottom = h - 50;
        left = 0 + 50;
        right = w - 50;
        speed = 4;
        missile = new Ball(top, bottom, left, right, speed);
        shields = new Paddle[] {new Paddle(-1, top, bottom, left, right), 
            new Paddle(1, top, bottom, left, right)};
        walls = new Rectangle[] {new Rectangle(0, top, w, -50), 
            new Rectangle(0, bottom, w, 50), 
            new Rectangle(left, 0, -50, h), 
            new Rectangle(right, 0, 50, h)};
        background = new Rectangle(0,0,w,h);
    }
    
    /**
     * Moves paddles around.
     */
    public void run()
    {
        for (Paddle a: shields) {
            a.move(top, bottom, left, right);
        }
        repaint();
    }
    
    public void accelerate(double deltaMag)
    {
        missile.accelerate(speed+=deltaMag);
    }
    
    public int track()
    {
        if (missile.getBall().x <= left)
            return 1;
        if (missile.getBall().x >= right)
            return -1;
        return 0;
    }
    
    public void bounce()
    {
        missile.cruise();
        if (missile.getBall().y <= top|| 
            missile.getBall().y + missile.getBall().height >= bottom) {
            missile.setVector(missile.reflect(new double[] {1, -1}));
        }
            
        for (Paddle a: shields) {
            if (missile.isCollide(a.getWall())) {
                missile.setVector(missile.reflect(a.getReflector()));
            }
        }
    }
    
    public void align(int w, int h)
    {
        int space = 40;
        top = 0 + 200 + space;
        bottom = h - space;
        left = 0 + space;
        right = w - space;
        walls = new Rectangle[] {new Rectangle(0, top - space, w, space), 
            new Rectangle(0, bottom, w, space), 
            new Rectangle(left - space, top, space, h), 
            new Rectangle(right, top, space, h)};
        shields = new Paddle[] {new Paddle(-1, top, bottom, left, right), 
            new Paddle(1, top, bottom, left, right)};
        background = new Rectangle(0,0,w,h);
        run();
        repaint();
    }
    
    public void resetBall(int w, int h)
    {
        missile = new Ball(top, bottom, left, right, speed);
        speed = 4;
    }
    
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_W){
            shields[0].setDirection(-1);
        }
        if(key == KeyEvent.VK_S){
            shields[0].setDirection(1);
        }
        if(key == KeyEvent.VK_UP){
            shields[1].setDirection(-1);
        }
        if(key == KeyEvent.VK_DOWN){
            shields[1].setDirection(1);
        }
    }
    
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_W || key == KeyEvent.VK_S){
            shields[0].setDirection(0);
        }
        if(key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN){
            shields[1].setDirection(0);
        }
    }

    public void keyTyped(KeyEvent e)
    {
        //no functions
    }
    
    public void paintComponent (Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.black);
        g2.fill(background);
        g2.setColor(Color.white);
        g2.fill(missile.getBall());
        for (Paddle a: shields) {
            g2.fill(a.getWall());
        }
        for (Rectangle a: walls) {
            g2.fill(a);
        }
        g2.drawString("P1: " + score1, left + 35, top - 50);
        g2.drawString("P2: " + score2, right - 55, top - 50);
    }
    
}

