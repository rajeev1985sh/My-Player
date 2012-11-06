package Nothing;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.awt.*;
import java.awt.event.*;

public class SelectTheams extends JDialog implements ActionListener, MouseListener, Runnable, ListSelectionListener, FileFilter {

    private static final long serialVersionUID = 1411715968437988341L;
    JDialog sldialog, plssleep, eslbox, psssleep;
    JPanel slpanel, eslpanel;
    JLabel plsmsg, pssmsg;
    JButton plsback, plsclose, plsfinal, slapply, slhide, pssback, pssclose, pssfinal;
    JButton eslsave, esladd, eslremove, eslhide;
    JRadioButton plsuser, plscurr, pssuser, psscurr;
    ButtonGroup plsbggrup, pssbggrup;
    JTextField plsuserinput, pssuserinput;
    JList sllist, esllist;
    Container eslcon, slcon;
    File file;
    JFileChooser jfl;
    Color eslbtc, slbtc, plsbtc, pssbtc, allbtc = Color.cyan, allp = Color.red, allr = Color.black;
    URL filepath;
    String ppp, sub;
    JCheckBox check_box = null;
    public static boolean check_auto = false;
    EmptyBox eb;
    static int itime = 0;
    static boolean syssleep = false;
    static String imagename[];
    static int idex = -1, esong = -1;
//Edit Songs in Play list===========================================================================================
    //@SuppressWarnings("deprecation")

    public void editSongList() {
        eslbox = new JDialog();
        eslcon = getContentPane();
        eslpanel = new JPanel();
        eslpanel.setBackground(Color.darkGray);
        eslpanel.setLayout(new FlowLayout());

        eslsave = new JButton("save");
        esladd = new JButton("Add");
        eslremove = new JButton("Remove");
        eslhide = new JButton("Hide");
        try {
            esllist = new JList(EmptyBox.name);
        } catch (NullPointerException np) {
            System.out.println("nullpointer");
        }
        esllist.addListSelectionListener(this);
        esllist.setVisibleRowCount(10);
        esllist.setFixedCellHeight(20);
        esllist.setFixedCellWidth(280);
        eslbtc = eslhide.getBackground();
//add controller
        eslsave.addActionListener(this);
        esladd.addActionListener(this);
        eslremove.addActionListener(this);
        eslhide.addActionListener(this);

        eslsave.addMouseListener(this);
        esladd.addMouseListener(this);
        eslremove.addMouseListener(this);
        eslhide.addMouseListener(this);
//adding component		
        eslcon.add(eslpanel);
        eslpanel.add(new JScrollPane(esllist));

        eslpanel.add(eslsave);
        eslpanel.add(esladd);
        eslpanel.add(eslremove);
        eslpanel.add(eslhide);
        MyPlayer.mp.frame.disable();
        setSize(320, 300);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }

//Constructor
//select method======================================================================================================   
    public void select() {
        check_box = new JCheckBox();
        if (check_auto == true) {
            check_box.setSelected(true);
        }
        JLabel jl = new JLabel("Check Auto");
        jl.setForeground(Color.WHITE);
        sldialog = new JDialog();
        slcon = getContentPane();
        slpanel = new JPanel();
        slpanel.setBackground(Color.darkGray);
        slpanel.setLayout(new FlowLayout());

        slapply = new JButton("Apply");
        slhide = new JButton("Hide");
        sllist = new JList(imagename);
        sllist.addListSelectionListener(this);
        sllist.setVisibleRowCount(10);
        sllist.setFixedCellHeight(20);
        sllist.setFixedCellWidth(280);
        slbtc = slapply.getBackground();

//add controller
        slapply.addActionListener(this);
        slhide.addActionListener(this);
        slapply.addMouseListener(this);
        slhide.addMouseListener(this);
        check_box.addActionListener(this);

//adding component		
        slcon.add(slpanel);
        slpanel.add(new JScrollPane(sllist));
        slpanel.add(slapply);
        slpanel.add(slhide);
        slpanel.add(check_box);
        slpanel.add(jl);
        check_box.setLocation(25, 280);

        MyPlayer.mp.frame.disable();
        setSize(320, 300);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }
//  =====================================================================================================================================  
    //player sleep section======================================================================================================================

    public void playerSleep() {

        plssleep = new JDialog();
        Container plscon = getContentPane();
        plscon.setBackground(Color.darkGray);
        plscon.setLayout(null);
        plsbggrup = new ButtonGroup();

        plsmsg = new JLabel("PLAYER TURN OFF");
        plsmsg.setForeground(Color.red);
        plsmsg.setBounds(80, 3, 150, 20);

        plsuser = new JRadioButton("ENTER WITHIN 1 TO 120 MINUTES", true);
        plsuser.setForeground(Color.white);
        plsuser.setBackground(Color.darkGray);
        plsuser.setBounds(20, 30, 250, 20);

        plsuserinput = new JTextField("1 TO 120");
        plsuserinput.setForeground(Color.gray);
        plsuserinput.setBounds(100, 60, 50, 20);

        plscurr = new JRadioButton("AFTER CURRENT PLAYLIST");
        plscurr.setForeground(Color.white);
        plscurr.setBackground(Color.darkGray);
        plscurr.setBounds(20, 90, 200, 20);

        plsback = new JButton("<back");
        plsback.setBounds(5, 120, 80, 20);

        plsfinal = new JButton("finish");
        plsfinal.setBounds(90, 120, 80, 20);

        plsclose = new JButton("cancel");
        plsclose.setBounds(175, 120, 80, 20);

        plsbggrup.add(plsuser);
        plsbggrup.add(plscurr);

        plscon.add(plsmsg);
        plscon.add(plsuser);
        plscon.add(plscurr);
        plscon.add(plsback);
        plscon.add(plsfinal);
        plscon.add(plsuserinput);
        plscon.add(plsclose);

        plsbtc = plsclose.getBackground();
        plsclose.addActionListener(this);
        plsuser.addActionListener(this);
        plscurr.addActionListener(this);
        plsback.addActionListener(this);
        plsfinal.addActionListener(this);

        plsclose.addMouseListener(this);
        plsuser.addMouseListener(this);
        plscurr.addMouseListener(this);
        plsback.addMouseListener(this);
        plsfinal.addMouseListener(this);
        MyPlayer.mp.frame.disable();
        setSize(270, 180);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }
//player+system sleep section==============================================================================================================

    public void playerSystemSleep() {
        psssleep = new JDialog();
        Container psscon = getContentPane();
        psscon.setBackground(Color.darkGray);
        psscon.setLayout(null);
        pssbggrup = new ButtonGroup();

        pssmsg = new JLabel("PLAYER + SYSTEM TURN OFF");
        pssmsg.setForeground(Color.red);
        pssmsg.setBounds(55, 3, 200, 20);

        pssuser = new JRadioButton("ENTER WITHIN 1 TO 120 MINUTES", true);
        pssuser.setForeground(Color.white);
        pssuser.setBackground(Color.darkGray);
        pssuser.setBounds(20, 30, 250, 20);

        plsuserinput = new JTextField("1 TO 120", "1 TO 120".length());
        plsuserinput.setForeground(Color.gray);
        plsuserinput.setBounds(100, 60, 70, 20);


        psscurr = new JRadioButton("AFTER CURRENT PLAYLIST");
        psscurr.setForeground(Color.white);
        psscurr.setBackground(Color.darkGray);
        psscurr.setBounds(20, 90, 200, 20);

        pssback = new JButton("<back");
        pssback.setBounds(5, 120, 80, 20);

        pssfinal = new JButton("finish");
        pssfinal.setBounds(90, 120, 80, 20);

        pssclose = new JButton("cancel");
        pssclose.setBounds(175, 120, 80, 20);

        pssbggrup.add(pssuser);
        pssbggrup.add(psscurr);

        psscon.add(pssmsg);
        psscon.add(pssuser);
        psscon.add(psscurr);
        psscon.add(pssback);
        psscon.add(pssfinal);
        psscon.add(plsuserinput);
        psscon.add(pssclose);

        pssbtc = pssclose.getBackground();
        pssclose.addActionListener(this);
        pssuser.addActionListener(this);
        psscurr.addActionListener(this);
        pssback.addActionListener(this);
        pssfinal.addActionListener(this);

        pssclose.addMouseListener(this);
        pssuser.addMouseListener(this);
        psscurr.addMouseListener(this);
        pssback.addMouseListener(this);
        pssfinal.addMouseListener(this);
        MyPlayer.mp.frame.disable();
        setSize(270, 180);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }
// implementing filefilter=====================================================================================================================    

    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }
        String name = f.getName().toLowerCase();
        return name.endsWith("jpg");
    }//end accept

//Loag iamges for theams================================================================================================================================
    public void loadTheam() {
        File fdpath = new File(MyPlayer.imgpath + "car theams/");
        File[] images = fdpath.listFiles(new SelectTheams());
        imagename = new String[images.length];
        for (int i = 0; i < images.length; i++) {
            imagename[i] = images[i].getName();
            imagename[i] = imagename[i].substring(0, imagename[i].length() - 4);

        }
    }
//imagecaller method======================================================================================================    

    public void imageCaller() {
        if (idex > -1) {
            String name = MyPlayer.imgpath + "car theams/" + imagename[idex] + ".jpg";
            MyPlayer.backimage = name;
            MyPlayer mp = new MyPlayer();
            mp.Player(name, true);
            dispose();
        }
    }
//Close Player or System by input time=========================================================================================================== 

    public void byTime() {
        String time = plsuserinput.getText();
        try {
            itime = Integer.parseInt(time);
        } catch (NumberFormatException nfe) {
            eb = new EmptyBox();
            eb.empty("   Enter Correct Time");
            eb.setLocation(MyPlayer.width / 2, MyPlayer.height / 2);
            return;
        }
        if (!(itime <= 120 && itime > 0)) {
            eb = new EmptyBox();
            eb.empty("Enter Time Between 1-120");
            eb.setLocation(MyPlayer.width / 2, MyPlayer.height / 2);
            return;
        }
        new Thread(new SelectTheams(), "Sleep").start();
        EmptyBox.sleepon = true;
        MyPlayer.mp.frame.enable();
    }

//run method to create new thread================================================================================================================= 
    public void run() {
        for (int i = 0; EmptyBox.sleepon && i < itime * 6; i++) {
            try {
                Thread.sleep(999 * 10);
            } catch (InterruptedException ire) {
                ire.printStackTrace();
            }
        }
        if (!EmptyBox.sleepon) {
            return;
        }
        PlayerDesign.pl.stop();
        if (EmptyBox.plsys) {
            Runtime rt = Runtime.getRuntime();
            try {
                rt.exec("shutdown -s -f");
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        System.exit(0);
    }
//By Play List==================================================================================================================================    

    public static void byPlaylist() {
        if (syssleep && EmptyBox.sleepon) {
            PlayerDesign.pl.stop();

            if (EmptyBox.plsys) {
                Runtime rt = Runtime.getRuntime();
                try {
                    rt.exec("shutdown -s -f");
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
            System.exit(0);
        }
    }
    //====================================================================================================================================   

    public void eslRemoveSong() {
        if (SelectTheams.esong > -1) {
            String str = EmptyBox.path[SelectTheams.esong];
            EmptyBox.pathset.remove(str);
            str = EmptyBox.name[SelectTheams.esong];
            EmptyBox.nameset.remove(str);

            EmptyBox.path = new String[EmptyBox.nameset.size()];
            EmptyBox.name = new String[EmptyBox.nameset.size()];

            Iterator itr1 = EmptyBox.pathset.iterator();
            Iterator itr2 = EmptyBox.nameset.iterator();

            int i = 0;
            while (itr1.hasNext()) {
                EmptyBox.path[i++] = (String) itr1.next();
            }
            i = 0;
            while (itr2.hasNext()) {
                EmptyBox.name[i++] = (String) itr2.next();
            }
        }
    }
    //=================================================================================================================================== 

    public void eslAddSong() {
        JFileChooser jfd = new JFileChooser("G:/latest hindi/zz rjvfav/");
        jfd.setMultiSelectionEnabled(true);

        int ret = jfd.showOpenDialog(this);
        if (ret == JFileChooser.CANCEL_OPTION) {
            return;
        }
        File[] file = jfd.getSelectedFiles();
        if (ret == JFileChooser.APPROVE_OPTION) {

            for (int i = 0; i < file.length; i++) {
                EmptyBox.nameset.add(file[i].getName());
                EmptyBox.pathset.add(file[i].getAbsolutePath());
            }
            EmptyBox.path = new String[EmptyBox.nameset.size()];
            EmptyBox.name = new String[EmptyBox.nameset.size()];

            Iterator itr1 = EmptyBox.pathset.iterator();
            Iterator itr2 = EmptyBox.nameset.iterator();

            int i = 0;
            while (itr1.hasNext()) {
                EmptyBox.path[i++] = (String) itr1.next();
            }
            i = 0;
            while (itr2.hasNext()) {
                EmptyBox.name[i++] = (String) itr2.next();
            }
        }
    }
//=====================================================================================================================================    

    public void eslSaveList() {

        File filename = new File("c:/MyplayList/" + EmptyBox.plist);
        try {
            filename.createNewFile();
            String wstr = "";
            FileWriter filewriter = new FileWriter(filename);

            Iterator itr1 = EmptyBox.pathset.iterator();
            Iterator itr2 = EmptyBox.nameset.iterator();
            while (itr1.hasNext() && itr2.hasNext()) {
                wstr += "<" + (String) itr1.next() + "?" + (String) itr2.next() + ">";
            }
            filewriter.write(wstr, 0, wstr.length());
            filewriter.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            dispose();
        }
    }
//List Change Event====================================================================================================================    

    public void valueChanged(ListSelectionEvent lse) {
        if (lse.getSource() == sllist) {
            idex = sllist.getSelectedIndex();
        }
        if (lse.getSource() == esllist) {
            esong = esllist.getSelectedIndex();
        }
    }
//Action Performed=====================================================================================================================   

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == check_box) {
            check_auto = check_box.isSelected();
            System.out.println("auto check " + check_auto);
        }
        if (ae.getSource() == eslsave) {
            eslSaveList();
            dispose();
            MyPlayer.mp.frame.enable();
        }
        if (ae.getSource() == esladd) {
            eslAddSong();
            dispose();
            MyPlayer.mp.frame.enable();
            DisplayList.calleditlist();
        }
        if (ae.getSource() == eslremove) {
            eslRemoveSong();
            dispose();
            MyPlayer.mp.frame.enable();
            DisplayList.calleditlist();
        }
        if (ae.getSource() == eslhide) {
            dispose();
            MyPlayer.mp.frame.enable();
        }

        if (ae.getSource() == slapply) {
            MyPlayer.mp.frame.enable();
            imageCaller();
        }

        if (ae.getSource() == slhide) {
            dispose();
            MyPlayer.mp.frame.enable();
        }

        if (ae.getSource() == plsclose) {
            dispose();
            MyPlayer.mp.frame.enable();
        }
        if (ae.getSource() == pssclose) {
            dispose();
            MyPlayer.mp.frame.enable();
        }
        if (ae.getSource() == plsback) {
            eb = new EmptyBox();
            eb.setLocation(MyPlayer.width / 2, MyPlayer.height / 2);
            eb.sleepmodule();
            dispose();
            //MyPlayer.mp.frame.enable();
        }
        if (ae.getSource() == pssback) {
            eb = new EmptyBox();
            eb.setLocation(MyPlayer.width / 2, MyPlayer.height / 2);
            eb.sleepmodule();
            dispose();
            //MyPlayer.mp.frame.enable();
        }
        if (ae.getSource() == plsfinal) {
            if (plsuser.isSelected()) {
                byTime();
                dispose();

            }
            if (plscurr.isSelected()) {
                syssleep = true;
                dispose();
                MyPlayer.mp.frame.enable();
            }
        }
        if (ae.getSource() == pssfinal) {
            if (pssuser.isSelected()) {
                byTime();
                dispose();
                //MyPlayer.mp.frame.enable();
            }
            if (psscurr.isSelected()) {
                syssleep = true;
                dispose();
                MyPlayer.mp.frame.enable();
            }
        }
        if (ae.getSource() == plsuser) {
            plsuserinput.enable();

        }
        if (ae.getSource() == plscurr) {
            plsuserinput.disable();

        }
        if (ae.getSource() == pssuser) {
            pssuserinput.enable();
        }
        if (ae.getSource() == psscurr) {
            pssuserinput.disable();
        }
    }
//Mouse Action Performed=================================================================================================================	

    public void mouseClicked(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    public void mouseEntered(MouseEvent me) {
        if (me.getSource() == eslsave) {
            eslsave.setBackground(allbtc);
        }
        if (me.getSource() == esladd) {
            esladd.setBackground(allbtc);
        }
        if (me.getSource() == eslremove) {
            eslremove.setBackground(allbtc);
        }
        if (me.getSource() == eslhide) {
            eslhide.setBackground(allbtc);
        }
        if (me.getSource() == slapply) {
            slapply.setBackground(allbtc);
        }
        if (me.getSource() == plsclose) {
            plsclose.setBackground(allbtc);
        }
        if (me.getSource() == pssclose) {
            pssclose.setBackground(allbtc);
        }
        if (me.getSource() == plsback) {
            plsback.setBackground(allbtc);
        }
        if (me.getSource() == plsfinal) {
            plsfinal.setBackground(allbtc);
        }
        if (me.getSource() == pssback) {
            pssback.setBackground(allbtc);
        }
        if (me.getSource() == pssfinal) {
            pssfinal.setBackground(allbtc);
        }
        if (me.getSource() == slapply) {
            slapply.setBackground(allbtc);
        }
        if (me.getSource() == slhide) {
            slhide.setBackground(allbtc);
        }

    }

    public void mouseExited(MouseEvent mx) {
        if (mx.getSource() == eslsave) {
            eslsave.setBackground(eslbtc);
        }
        if (mx.getSource() == esladd) {
            esladd.setBackground(eslbtc);
        }
        if (mx.getSource() == eslremove) {
            eslremove.setBackground(eslbtc);
        }
        if (mx.getSource() == eslhide) {
            eslhide.setBackground(eslbtc);
        }
        if (mx.getSource() == slapply) {
            slapply.setBackground(slbtc);
        }
        if (mx.getSource() == plsclose) {
            plsclose.setBackground(plsbtc);
        }
        if (mx.getSource() == pssclose) {
            pssclose.setBackground(pssbtc);
        }
        if (mx.getSource() == plsback) {
            plsback.setBackground(plsbtc);
        }
        if (mx.getSource() == plsfinal) {
            plsfinal.setBackground(plsbtc);
        }
        if (mx.getSource() == pssback) {
            pssback.setBackground(pssbtc);
        }
        if (mx.getSource() == pssfinal) {
            pssfinal.setBackground(pssbtc);
        }
        if (mx.getSource() == slapply) {
            slapply.setBackground(slbtc);
        }
        if (mx.getSource() == slhide) {
            slhide.setBackground(slbtc);
        }
    }

    public void mousePressed(MouseEvent mp) {
        if (mp.getSource() == eslsave) {
            eslsave.setForeground(allp);
        }
        if (mp.getSource() == esladd) {
            esladd.setForeground(allp);
        }
        if (mp.getSource() == eslremove) {
            eslremove.setForeground(allp);
        }
        if (mp.getSource() == eslhide) {
            eslhide.setForeground(allp);
        }
        if (mp.getSource() == slapply) {
            slapply.setForeground(allp);
        }
        if (mp.getSource() == plsclose) {
            plsclose.setForeground(allp);
        }
        if (mp.getSource() == pssclose) {
            pssclose.setForeground(allp);
        }
        if (mp.getSource() == plsback) {
            plsback.setForeground(allp);
        }
        if (mp.getSource() == plsfinal) {
            plsfinal.setForeground(allp);
        }
        if (mp.getSource() == pssback) {
            pssback.setForeground(allp);
        }
        if (mp.getSource() == pssfinal) {
            pssfinal.setForeground(allp);
        }
        if (mp.getSource() == slapply) {
            slapply.setForeground(allp);
        }
        if (mp.getSource() == slhide) {
            slhide.setForeground(allp);
        }
    }

    public void mouseReleased(MouseEvent mr) {
        if (mr.getSource() == eslsave) {
            eslsave.setForeground(allr);
        }
        if (mr.getSource() == esladd) {
            esladd.setForeground(allr);
        }
        if (mr.getSource() == eslremove) {
            eslremove.setForeground(allr);
        }
        if (mr.getSource() == eslhide) {
            eslhide.setForeground(allr);
        }
        if (mr.getSource() == slapply) {
            slapply.setForeground(allr);
        }
        if (mr.getSource() == plsclose) {
            plsclose.setForeground(allr);
        }
        if (mr.getSource() == pssclose) {
            pssclose.setForeground(allr);
        }
        if (mr.getSource() == plsback) {
            plsback.setForeground(allr);
        }
        if (mr.getSource() == plsfinal) {
            plsfinal.setForeground(allr);
        }
        if (mr.getSource() == pssback) {
            pssback.setForeground(allr);
        }
        if (mr.getSource() == pssfinal) {
            pssfinal.setForeground(allr);
        }
        if (mr.getSource() == slapply) {
            slapply.setForeground(allr);
        }
        if (mr.getSource() == slhide) {
            slhide.setForeground(allr);
        }
    }
}