
/**
 * Write a description of class Ball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Ball
{
    private Rectangle ball;
    private double[] vector;
    public Ball(int t, int b, int l, int r, double mag)
    {
        double angle = ((Math.random()*30+30)
            + 45 + 45*Math.pow(-1,(int)(Math.random()*2)))*
            Math.pow(-1,(int)(Math.random()*2));
        //System.out.println(angle);
        angle = angle*2*Math.PI/360.0;
        resetPos(t,b,l,r);
        resetVector(angle, mag);
    }
    
    public Rectangle getBall()
    {
        return ball;
    }
    
    public void resetPos(int t, int b, int l, int r)
    {
        double variation = 30;
        ball = new Rectangle((l + r - 5)/2 + 
            (int)((Math.random()*variation*2) - variation),
            (t + b - 5)/2 + 
            (int)((Math.random()*variation*2) - variation), 
            5, 5);
    }
    
    public void resetVector(double angle, double mag)
    {
        vector = new double[] {mag*Math.cos(angle), mag*Math.sin(angle)};
    }
    
    public boolean isCollide(Rectangle wall)
    {
        return ball.intersects(wall);
    }
    
    /**
     * Changes ball's speed to mag and keeps angle 
     */
    public void accelerate(double mag)
    {
        double speed = getSpeed();
        for (int i = 0; i < vector.length; i++) {
            vector[i] = vector[i]*mag/speed;
        }
    }
    
    /**
     * Returns speed
     */
    public double getSpeed()
    {
        double mag = 0;
        for (int i = 0; i < vector.length; i++) {
            mag += Math.pow(vector[i], 2);
        }
        return Math.pow(mag, .5);
    }
    
    /**
     * 
     */
    public void setVector(double[] part)
    {
        vector = part;
    }
    
    public double[] reflect(double[] mirror)
    {
        double[] part = new double[2];
        for (int i = 0; i < part.length; i++) {
            part[i] = vector[i]*mirror[i];
        }
        return part;
    }
    
    public void cruise()
    {
        ball.translate((int)vector[0], (int)vector[1]);
    }
}
