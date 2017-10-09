
/**
 * Write a description of class Control here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.*;
public interface Control
{    
    public int getLeftX();
    public int getTop();
    public int getBot();
    public int getRightX();
    public void moveByRect();
    public Rectangle getRect();
}
