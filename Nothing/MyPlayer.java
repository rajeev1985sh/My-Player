//MAIN CLASS OF MYPLAYER
package Nothing;

import javax.swing.*;
import java.awt.*;

//BIG BOSS CLASS
public class MyPlayer {

    PlayerDesign frame;
    static MyPlayer mp;
    static int width, height;
    static Window ww[];
    //static String imgpath="C:/Documents and Settings/Administrator/Eclipseworkspace/Nothing/src/Nothing/Images/";
    static String imgpath = "C:/rjvdata/work/projects/myplayer data/Nothing/Images/";
    static String backimage;

    public void Player(String ss, boolean dc) {
        if (mp.frame == null) {
            System.out.println("NULLPOINTEREXCEPTION");
        } else {
            mp.frame.PlayerDesignMethod(ss, dc);
        }
    }

    public void refresh(String labelname, boolean lb) {
        if (mp.frame == null) {
            System.out.println("NULLPOINTEREXCEPTION");
        } else
	                 ;
    }

    public void runSlider() {
        mp.frame.moveSlider();
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        mp = new MyPlayer();
        String path = MyPlayer.imgpath + "car theams/Black and White.JPG";
        backimage = path;

        mp.frame = new PlayerDesign();
        ww = mp.frame.getWindows();
        JWindow jw = new JWindow();
        Dimension dim = jw.getToolkit().getScreenSize();
        width = dim.width;
        height = dim.height;
        mp.frame.PlayerDesignMethod(path, false);
        mp.frame.DefineComponents();
        mp.frame.AddAllComponents();
        //mp.frame.moveSlider();
        //frame.windowOpened(null);
        mp.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mp.frame.setSize(806, 420);
        mp.frame.setResizable(false);
        mp.frame.setVisible(true);


    }
}
