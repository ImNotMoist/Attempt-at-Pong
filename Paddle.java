
/**
 * Write a description of class Paddle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Paddle
{
    private double[] reflector;
    private int direction;
    private Rectangle wall;
    int spacing;
    int type;
    public Paddle(int side, int t, int b, int l, int r)
    {
        type = side;
        spacing = 30;
        int w = 5;
        int h = 80;
        reflector = new double[] {-1, 1};
        if (side < 0)
        {
            wall = new Rectangle(l + spacing, (t + b - h)/2, w, h);
        }
        else 
        {
            wall = new Rectangle(r - spacing - w, (t + b - h)/2, w, h);
        }
        direction = 0;
    }
    
    public Rectangle getWall()
    {
        return wall;
    }
    
    public double[] getReflector()
    {
        return reflector;
    }
    
    public void setDirection(int way)
    {
        direction = way;
    }
    
    public void move(int t, int b, int l, int r)
    {
        wall.translate(0, direction);
        if (wall.y < t) {
            if (type < 0)
                wall.setLocation(l + spacing, t);
            else
                wall.setLocation(r - spacing - wall.width, t);
        }
        if (wall.y + wall.height > b) {
            if (type < 0)
                wall.setLocation(l + spacing, b - wall.height);
            else
                wall.setLocation(r - spacing - wall.width, b - wall.height);
        }
    }
}
