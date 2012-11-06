package Nothing;

import javax.media.*;
import java.net.URL;
import java.awt.Color;
import java.awt.Component;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.*;
import Nothing.Play;
import javax.swing.filechooser.FileFilter;
import javax.swing.JWindow;

public class VideoCaller extends JFrame implements WindowListener, MouseListener {

    public boolean check_click = false;
    private Component comp = null;
    Container con = null;

    public VideoCaller(String filename) {
        super(filename);
    }

    public void create() {
        con = getContentPane();
        //con.setBackground(Color.darkGray);
        con.setLayout(new BorderLayout());
        setLocation((MyPlayer.width / 2) - 75, (MyPlayer.height / 2) - 50);
        setSize(600, 330);
        setVisible(true);
    }

    public void videotry() {
        //con.setName("hello");
        System.out.print("calling at many times");
        if (comp != null) {
            con.removeAll();
            //con.removeNotify();
        }
        comp = Play.playMP3.getVisualComponent();
        //Dimension dim = comp.getPreferredSize();
        //comp.setName("video11");
        //System.out.println(dim.toString());
        //Component comp1= Play.playMP3.getControlPanelComponent();
        //comp.setBounds(100,100,dim.width,dim.height);
        con.add(comp);
        con.validate();

        addWindowListener(this);
        comp.addMouseListener(this);

    }

    public void windowActivated(WindowEvent arg0) {
        // TODO Auto-generated method stub
    }

    public void windowClosed(WindowEvent arg0) {
        // TODO Auto-generated method stub
    }

    public void windowClosing(WindowEvent arg0) {
        PlayerDesign.pl.stop();

    }

    public void windowDeactivated(WindowEvent arg0) {
        // TODO Auto-generated method stub
    }

    public void windowDeiconified(WindowEvent arg0) {
        // TODO Auto-generated method stub
    }

    public void windowIconified(WindowEvent arg0) {
        // TODO Auto-generated method stub
    }

    public void windowOpened(WindowEvent arg0) {
        // TODO Auto-generated method stub
    }

    public void mouseClicked(MouseEvent click) {
        if (click.getComponent() == comp) {
            int clicks = click.getClickCount();
            //System.out.println(MouseEvent.MOUSE_CLICKED);
            System.out.println("clicks " + clicks);
            if (clicks == 1) {
                if (check_click == false) {
                    //dispose();
                    check_click = true;
                    PlayerDesign.pl.pause();
                } else {
                    check_click = false;
                    PlayerDesign.jplay.setEnabled(false);
                    PlayerDesign.pl.play();
                }
            }
        }
    }

    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }
}
