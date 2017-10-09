
/**
 * Write a description of class Wall1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Game extends JComponent
{
    public static void main (String args[])
    {
        JFrame frame = new JFrame();
        frame.setSize(1000,500);
        int frameW = frame.getWidth();
        int frameH = frame.getHeight();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Stage play = new Stage(frameW, frameH);
        frame.add(play);
        frame.addKeyListener(play);
        frame.setVisible(true);
        class BallBounce implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                play.bounce();
                play.accelerate(.000);
            }
        }
        
        ActionListener listener1 = new BallBounce();
        Timer t1 = new Timer(4, listener1);
        t1.start();
        class WallMove implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                play.run();
            }
        }
        ActionListener listener2 = new WallMove();
        Timer t2 = new Timer(4, listener2);
        t2.start();
        int place = 0;
        boolean cont = true;
        play.align(frameW, frameH);
        while (cont) {
            if (frameW != frame.getWidth() || frameH != frame.getHeight()) {
                frameW = frame.getWidth();
                frameH = frame.getHeight();
                play.align(frameW, frameH);
            }
            play.repaint();
            place = play.track();
            if (place != 0) {
                if (place < 0) {
                    play.score1++;
                    if (play.score1 >= 11)
                    {
                        cont = false;
                    }
                }
                if (place > 0) {
                    play.score2++;
                    if (play.score2 >= 11)
                    {
                        cont = false;
                    }
                }
                play.resetBall(frameW, frameH);
                play.repaint();
                place = 0;
            }
            
        }
        
    }
}
