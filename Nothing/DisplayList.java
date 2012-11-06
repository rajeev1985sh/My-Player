package Nothing;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.util.Iterator;
import javax.swing.filechooser.FileFilter;

public class DisplayList extends JDialog implements ActionListener, ListSelectionListener, MouseListener {

    private static final long serialVersionUID = -5888458341510383164L;
    Container con;
    JDialog jdisplaybox;
    JButton jpplay, jpadd, jpremove, jphide;
    JList jplist;
    JPanel panel;
    int idex = -1;
    static public JFileChooser jfd;
    Color dspbtc, allbtc = Color.cyan, allp = Color.red, allr = Color.black;
    DisplayList dlist;
    SelectTheams st;
//Display======================================================================================================================	

    public void display() {
//Object creation		
        if (Play.pathsset.size() < 0) {
            EmptyBox op = new EmptyBox();
            op.setLocation(MyPlayer.width / 2, MyPlayer.height / 2);
            op.empty("Please select the MP3 file");
        } else {
            jdisplaybox = new JDialog();
            con = getContentPane();
            panel = new JPanel();
            panel.setBackground(Color.darkGray);
            panel.setLayout(new FlowLayout());

            jpplay = new JButton("Play");
            jpadd = new JButton("Add");
            jpremove = new JButton("Remove");
            jphide = new JButton("Hide");
            jplist = new JList(Play.names);
            jplist.addListSelectionListener(this);
            jplist.setVisibleRowCount(10);
            jplist.setFixedCellHeight(20);
            jplist.setFixedCellWidth(280);
//add controller
            jpplay.addActionListener(this);
            jpadd.addActionListener(this);
            jpremove.addActionListener(this);
            jphide.addActionListener(this);

            jpplay.addMouseListener(this);
            jpadd.addMouseListener(this);
            jpremove.addMouseListener(this);
            jphide.addMouseListener(this);
            dspbtc = jpplay.getBackground();
//adding component		
            con.add(panel);
            panel.add(new JScrollPane(jplist));

            panel.add(jpplay);
            panel.add(jpadd);
            panel.add(jpremove);
            panel.add(jphide);
            MyPlayer.mp.frame.disable();
            setSize(320, 300);
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            setVisible(true);
        }

    }
//jpplayAction method======================================================================================================	

    public void jpplayAction() {
        if (idex > -1) {
            Play.counter = idex;
            Play.SSTOP = true;
            Thread.yield();
            Play.path = Play.paths[Play.counter];
            PlayerDesign.pl.locate("file:///" + Play.path);
            PlayerDesign.pl.play();
        }
    }
//jaddAction() Method===========================================================================================================	

    public void jaddAction() {
        if (MyPlayer.mp.frame.jfd == null && jfd == null) {
            MyPlayer.mp.frame.jfd = new JFileChooser();
            jfd = MyPlayer.mp.frame.jfd;
        }
        jfd.setFileFilter(new mp3Filter("media files"));
        jfd.setMultiSelectionEnabled(true);
        int ret = jfd.showOpenDialog(this);
        if (ret == JFileChooser.CANCEL_OPTION) {
            return;
        }
        File[] file = jfd.getSelectedFiles();
        if (ret == JFileChooser.APPROVE_OPTION) {
            Play.mp3files(file);
        }
        dispose();
        PlayerDesign.jplaylist.doClick();
        return;
    }
//jpremoveAction()==============================================================================================================	

    public void jpremoveAction() {
        if (idex > -1) {
            PlayerDesign.pl.removeSong(idex);
            if (idex == Play.counter) {
                PlayerDesign.pl.stop();
                Play.path = null;
                int s = Play.pathsset.size();
                Play.counter = (int) (Math.random() * s);
                if (Play.counter < s) {
                    Play.path = Play.paths[Play.counter];
                    PlayerDesign.pl.locate("file:///" + Play.path);
                    Play.p = 0;
                    PlayerDesign.pl.play();
                }
                dispose();
                PlayerDesign.jplaylist.doClick();
                return;
            }
            if (idex < Play.counter) {
                Play.counter--;
            }
            dispose();
            PlayerDesign.jplaylist.doClick();
        }
    }
//Calling editSongList again()=====================================================================================================

    public static void calleditlist() {
        SelectTheams st = new SelectTheams();
        st.setLocation(MyPlayer.width / 2, MyPlayer.height / 2);
        st.editSongList();
    }

//valueChanged=====================================================================================================================	
    public void valueChanged(ListSelectionEvent e) {
        idex = jplist.getSelectedIndex();
    }
//Action Events=====================================================================================================================	

    public void actionPerformed(ActionEvent act) {

        if (act.getSource() == jpplay) {
            jpplayAction();
            MyPlayer.mp.frame.enable();
        }
        if (act.getSource() == jpadd) {
            jaddAction();
            //MyPlayer.mp.frame.enable();
        }
        if (act.getSource() == jpremove) {
            jpremoveAction();
            //MyPlayer.mp.frame.enable();
        }
        if (act.getSource() == jphide) {
            dispose();
            MyPlayer.mp.frame.enable();
        }

    }

    public void mouseClicked(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    public void mouseEntered(MouseEvent me) {
        if (me.getSource() == jpplay) {
            jpplay.setBackground(allbtc);
        }
        if (me.getSource() == jpadd) {
            jpadd.setBackground(allbtc);
        }
        if (me.getSource() == jpremove) {
            jpremove.setBackground(allbtc);
        }
        if (me.getSource() == jphide) {
            jphide.setBackground(allbtc);
        }
    }

    public void mouseExited(MouseEvent mx) {
        if (mx.getSource() == jpplay) {
            jpplay.setBackground(dspbtc);
        }
        if (mx.getSource() == jpadd) {
            jpadd.setBackground(dspbtc);
        }
        if (mx.getSource() == jpremove) {
            jpremove.setBackground(dspbtc);
        }
        if (mx.getSource() == jphide) {
            jphide.setBackground(dspbtc);
        }


    }

    public void mousePressed(MouseEvent mp) {
        if (mp.getSource() == jpplay) {
            jpplay.setForeground(allp);
        }
        if (mp.getSource() == jpadd) {
            jpadd.setForeground(allp);
        }
        if (mp.getSource() == jpremove) {
            jpremove.setForeground(allp);
        }
        if (mp.getSource() == jphide) {
            jphide.setForeground(allp);
        }

    }

    public void mouseReleased(MouseEvent mr) {
        if (mr.getSource() == jpplay) {
            jpplay.setForeground(allr);
        }
        if (mr.getSource() == jpadd) {
            jpadd.setForeground(allr);
        }
        if (mr.getSource() == jpremove) {
            jpremove.setForeground(allr);
        }
        if (mr.getSource() == jphide) {
            jphide.setForeground(allr);
        }
    }
}
