package Nothing;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class EmptyBox extends JDialog implements ActionListener, MouseListener, ListSelectionListener, FileFilter {

    private static final long serialVersionUID = -8942290231204795032L;
    JDialog empbox, jplsleep, opbox, canbox, edlbox, slpbox, hmbox;
    Dialog adsf;
    JLabel canmsg, canmsg2, canmsg3;
    JLabel opmsg, slpmsg;
    JLabel empmsg, empimgicon;
    JButton opclose, empclose, canclose, canok, hmclose;
    JButton edledit;
    JButton edlrename, edldelete, edlhide;
    JButton slpclose, slpnext;
    JButton rateup, ratedown, ratenormal;
    static JRadioButton plshut, plsyshut, pldisable;
    ButtonGroup slpbgrup;
    JTextField playlistnameinput;
    JList edllist;
    static public String plist = null;
    JLabel myp;
    File filename;
    public static boolean sleepon = false;
    static public float rate = 1.0f, vary = 1.0f;
    static Play ply = new Play();
    Color empbtc, canbtc, edlbtc, slpbtc, opbtc, hmbtc, allbtc = Color.cyan, allb = Color.red, allr = Color.black;
    PlayerDesign pd;
    SelectTheams st;
    static boolean plyer, plsys;
    static String plistname[] = null, path[], name[];
    static Set pathset, nameset;
    static int idex = -1, state;
    Object obj;
    static boolean flag = false;

//To show empty message=====================================================================================		
    public void empty(String notice) {
        empbox = new JDialog();

        Container con = getContentPane();
        con.setBackground(Color.darkGray);
        con.setLayout(null);
        empmsg = new JLabel(notice);
        empmsg.setForeground(Color.white);
        empmsg.setBounds(60, 20, 200, 20);
        empimgicon = new JLabel(new ImageIcon(MyPlayer.imgpath + "error.png"));
        empimgicon.setBounds(5, 5, 50, 50);
        con.add(empmsg);
        con.add(empimgicon);
        empclose = new JButton("OK");
        empclose.setBounds(90, 60, 70, 20);
        con.add(empclose);
        empbtc = empclose.getBackground();
        empclose.addActionListener(this);
        empclose.addMouseListener(this);
        empmsg.addMouseListener(this);
        MyPlayer.mp.frame.disable();
        setResizable(false);
        setSize(240, 130);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }
//Player Rate Implementation=============================================================================================>>>>>>>>>>>>>>>>>>>>>

    public void playerRate() {

        empbox = new JDialog();

        /*
         *Play.playMP3.setRate(0.05f);
         */

        Container con = getContentPane();
        con.setBackground(Color.darkGray);
        con.setLayout(null);

        empmsg = new JLabel("PLAY RATE CHANGE");
        empmsg.setForeground(Color.white);
        empmsg.setBounds(60, 20, 200, 20);

        rateup = new JButton("INCREASE");
        rateup.setBounds(65, 50, 100, 20);

        ratenormal = new JButton("NORMAL");
        ratenormal.setBounds(65, 80, 100, 20);

        ratedown = new JButton("DECREASE");
        ratedown.setBounds(65, 110, 100, 20);

        empclose = new JButton("OK");
        empclose.setBounds(80, 150, 70, 20);

        con.add(empmsg);
        con.add(rateup);
        con.add(ratedown);
        con.add(ratenormal);
        con.add(empclose);

        empbtc = empclose.getBackground();
        empbtc = rateup.getBackground();
        empbtc = ratedown.getBackground();
        empbtc = ratenormal.getBackground();
        rateup.addActionListener(this);
        rateup.addMouseListener(this);
        ratedown.addActionListener(this);
        ratedown.addMouseListener(this);
        ratenormal.addActionListener(this);
        ratenormal.addMouseListener(this);
        empclose.addActionListener(this);
        empclose.addMouseListener(this);
        empmsg.addMouseListener(this);

        MyPlayer.mp.frame.disable();
        setResizable(false);
        setSize(240, 230);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setVisible(true);

    }
// Can save files===============================================================================================		

    public void canSave(String gname) {
        canbox = new JDialog();
        Container cancon = getContentPane();
        cancon.setBackground(Color.darkGray);
        cancon.setLayout(null);
        canmsg = new JLabel(gname);
        canmsg.setForeground(Color.white);
        canmsg.setBounds(20, 10, 200, 20);
        canmsg2 = new JLabel("PLAYLIST NAME TO SAVE");
        canmsg2.setForeground(Color.white);
        canmsg2.setBounds(20, 30, 200, 20);
        canmsg3 = new JLabel("WHICH DOES NOT EXIST.");
        canmsg3.setForeground(Color.white);
        canmsg3.setBounds(20, 50, 200, 20);
        playlistnameinput = new JTextField();
        playlistnameinput.setBounds(20, 70, 140, 20);
        cancon.add(canmsg);
        cancon.add(canmsg2);
        cancon.add(canmsg3);
        cancon.add(playlistnameinput);
        canclose = new JButton("cancel");
        canclose.setBounds(80, 100, 80, 20);

        canok = new JButton("ok");
        canok.setBounds(20, 100, 50, 20);
        cancon.add(canclose);
        cancon.add(canok);
        canbtc = canok.getBackground();

        canclose.addActionListener(this);
        canclose.addMouseListener(this);
        canok.addActionListener(this);
        canok.addMouseListener(this);
        MyPlayer.mp.frame.disable();
        canmsg.addMouseListener(this);
        canmsg2.addMouseListener(this);
        canmsg3.addMouseListener(this);
        setResizable(false);
        setSize(190, 180);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }
// Startup dialog======================================================================================================================		

    public void openWindow() {
        opbox = new JDialog();
        opbox.setTitle("Hello user");

        Container opcon = getContentPane();
        opcon.setLayout(null);
        opcon.setBackground(Color.DARK_GRAY);
        String imgpath = MyPlayer.imgpath;

        opmsg = new JLabel("WELCOME TO");
        opmsg.setForeground(Color.white);
        opmsg.setBounds(110, 10, 200, 20);
        opcon.add(opmsg);
        int x = 590;
        int y = -20;

        JLabel jearth = new JLabel(new ImageIcon(imgpath + "earth.gif"));
        jearth.setBounds(720 - x, 0 - y, 60, 90);
        JLabel jy = new JLabel(new ImageIcon(imgpath + "yy.gif"));
        jy.setBounds(698 - x, 50 - y, 70, 90);
        JLabel jm = new JLabel(new ImageIcon(imgpath + "music.gif"));
        jm.setBounds(720 - x, 90 - y, 60, 90);
        myp = new JLabel("My Player");
        myp.setForeground(Color.white);
        myp.setBounds(710 - x, 150 - y, 100, 50);

        opcon.add(jearth);
        opcon.add(jm);
        opcon.add(jy);
        opcon.add(myp);
        MyPlayer.mp.frame.disable();
        opclose = new JButton("GO");
        opclose.setBounds(110, 220, 70, 20);
        opcon.add(opclose);
        opbtc = opclose.getBackground();
        opclose.addActionListener(this);
        opclose.addMouseListener(this);
        opmsg.addMouseListener(this);
        myp.addMouseListener(this);
        setResizable(false);
        setSize(300, 300);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }
//Edit PlayList====================================================================================================================

    public void editList() {
        edlbox = new JDialog();

        Container edlcon = getContentPane();
        JPanel edlpanel = new JPanel();
        edlpanel.setBackground(Color.darkGray);
        edlpanel.setLayout(new FlowLayout());
        edledit = new JButton("Edit");
        edlrename = new JButton("Rename");
        edldelete = new JButton("Delete");
        edlhide = new JButton("Hide");
        edllist = new JList(plistname);
        edllist.addListSelectionListener(this);
        edllist.setVisibleRowCount(10);
        edllist.setFixedCellHeight(20);
        edllist.setFixedCellWidth(280);
        edlbtc = edledit.getBackground();
        //add controller
        edledit.addActionListener(this);
        edlrename.addActionListener(this);
        edldelete.addActionListener(this);
        edlhide.addActionListener(this);

        edledit.addMouseListener(this);
        edlrename.addMouseListener(this);
        edldelete.addMouseListener(this);
        edlhide.addMouseListener(this);

        //adding component		
        edlcon.add(edlpanel);
        edlpanel.add(new JScrollPane(edllist));
        MyPlayer.mp.frame.disable();
        edlpanel.add(edledit);
        edlpanel.add(edlrename);
        edlpanel.add(edldelete);
        edlpanel.add(edlhide);
        setResizable(false);
        setSize(320, 300);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }
    //Sleep button action=======================================================================================================================

    public void sleepmodule() {
        slpbox = new JDialog();

        Container slpcon = getContentPane();
        slpcon.setBackground(Color.darkGray);
        slpcon.setLayout(null);
        slpbgrup = new ButtonGroup();

        slpmsg = new JLabel("TURN OFF");
        slpmsg.setForeground(Color.red);
        slpmsg.setBounds(80, 3, 100, 20);

        plshut = new JRadioButton("PLAYER", true);
        plshut.setForeground(Color.white);
        plshut.setBackground(Color.darkGray);
        plshut.setBounds(20, 30, 100, 20);

        plsyshut = new JRadioButton("PLAYER + SYSTEM");
        plsyshut.setForeground(Color.white);
        plsyshut.setBackground(Color.darkGray);
        plsyshut.setBounds(20, 50, 150, 20);

        pldisable = new JRadioButton("DISABLE SLEEP");
        pldisable.setForeground(Color.white);
        pldisable.setBackground(Color.darkGray);
        pldisable.setBounds(20, 70, 150, 20);


        slpnext = new JButton("next>");
        slpnext.setBounds(20, 100, 80, 20);

        slpclose = new JButton("cancel");
        slpclose.setBounds(110, 100, 80, 20);

        slpbtc = slpclose.getBackground();
        slpbgrup.add(plshut);
        slpbgrup.add(plsyshut);
        slpbgrup.add(pldisable);

        slpcon.add(slpmsg);
        slpcon.add(plshut);
        slpcon.add(plsyshut);
        slpcon.add(slpnext);
        slpcon.add(slpclose);
        slpcon.add(pldisable);

        MyPlayer.mp.frame.disable();
        slpclose.addActionListener(this);
        plshut.addActionListener(this);
        plsyshut.addActionListener(this);
        slpnext.addActionListener(this);
        pldisable.addActionListener(this);

        slpclose.addMouseListener(this);
        slpnext.addMouseListener(this);
        plshut.addMouseListener(this);
        plsyshut.addMouseListener(this);
        pldisable.addMouseListener(this);

        setResizable(false);
        setSize(220, 160);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }

//Help Section================================================================================================================================
    public void helpmodule(String imagepath) {
        hmbox = new JDialog();
        //hmbox.setFocusableWindowState(false);           			
        Container hmcone = getContentPane();
        hmcone.setLayout(null);
        hmcone.setBackground(Color.DARK_GRAY);
        JLabel txtimg = new JLabel(new ImageIcon(imagepath));
        txtimg.setBounds(5, 0, 480, 400);
        hmcone.add(txtimg);
        hmclose = new JButton("EXIT");
        hmclose.setBounds(210, 390, 70, 20);
        hmcone.add(hmclose);
        hmbtc = hmclose.getBackground();
        hmclose.addActionListener(this);
        hmclose.addMouseListener(this);
        MyPlayer.mp.frame.disable();
        setResizable(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(490, 445);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }

//Help Section================================================================================================================================
    public void aboutUs(String imagepath) {

        hmbox = new JDialog();


        Container hmcone = getContentPane();
        hmcone.setLayout(null);
        hmcone.setBackground(Color.DARK_GRAY);

        JLabel txtimg = new JLabel(new ImageIcon(imagepath));
        txtimg.setBounds(5, 0, 330, 450);

        hmcone.add(txtimg);

        hmclose = new JButton("EXIT");
        hmclose.setBounds(110, 451, 70, 20);
        hmcone.add(hmclose);
        hmbtc = hmclose.getBackground();
        hmclose.addActionListener(this);
        hmclose.addMouseListener(this);
        MyPlayer.mp.frame.disable();
        setResizable(false);
        setSize(340, 500);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }

    //implementation of FileFilter method accept===========================================================================================
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }
        String name = f.getName().toLowerCase();
        return name.endsWith("abi");
    }//end accept

//Load play lists in menu=================================================================================================================
    public void loadEditList() {
        File fdpath = new File("C:/MyPlayList");
        File[] plists = fdpath.listFiles(new EmptyBox());
        plistname = new String[plists.length];
        for (int i = 0; i < plists.length; i++) {
            plistname[i] = plists[i].getName();
            plistname[i] = plistname[i].substring(0, plistname[i].length() - 4);

        }
    }

//Rename Play List===========================================================================================================================
    public void renameList() {
        if (idex > -1) {
            EmptyBox opp = new EmptyBox();
            opp.setLocation(MyPlayer.width / 2, MyPlayer.height / 2);
            opp.canSave("PLEASE ENTER RENAME");
        }
    }

//Rename File=================================================================================================================================
    public void renameFile() {

        String plname = plistname[idex] + ".abi";
        File abspath = new File("C:/MyPlayList/" + plname);
        String newname = playlistnameinput.getText();
        System.out.println(newname.length());
        if (newname.length() <= 0 || newname.charAt(0) == 32) {
            dispose();
            EmptyBox em = new EmptyBox();
            em.setLocation(MyPlayer.width / 2, MyPlayer.height / 2);
            em.empty("Enter Correct Name");
            return;
        }
        File newabspath = new File("C:/MyPlayList/" + newname + ".abi");
        abspath.renameTo(newabspath);
        dispose();
        PlayerDesign.pedit.doClick();

    }
//delete method=================================================================================================================

    public void deletePlfile() {
        if (idex > -1) {
            String dfile = plistname[idex] + ".abi";
            File abspath = new File("C:/MyPlayList/" + dfile);
            System.out.println(abspath.delete());
            dispose();
            PlayerDesign.pedit.doClick();
        }
    }

// Edit Play List...==========================================================================================================
    public void editPlList() {
        if (idex > -1) {
            plist = plistname[idex] + ".abi";
            File pfile = new File("C:/MyPlayList/" + plist);
            pathset = new LinkedHashSet();
            nameset = new LinkedHashSet();
            name = new String[0];
            try {
                FileReader fread = new FileReader(pfile);
                int ch = fread.read();
                if (ch != '<' || ch == -1) {
                    return;
                }
                String songadd = "";
                String songname = "";
                while (ch != -1) {
                    if (ch == '<') {
                        ch = fread.read();
                        while (ch != '>') {
                            songadd += (char) ch;
                            ch = fread.read();
                        }
                        songname = songadd.substring(songadd.indexOf('?') + 1, songadd.length());
                        songadd = songadd.substring(0, songadd.indexOf('?'));
                        pathset.add(songadd);
                        nameset.add(songname);
                        songadd = "";
                        songname = "";
                        ch = fread.read();
                    }
                }

                Iterator itr1 = pathset.iterator();
                Iterator itr2 = nameset.iterator();
                path = new String[pathset.size()];
                name = new String[nameset.size()];

                int i = 0;
                while (itr1.hasNext() && itr2.hasNext()) {
                    name[i] = (String) itr2.next();
                    path[i++] = (String) itr1.next();
                }
            } catch (IOException ioe) {
                ioe.getMessage();
            } finally {
            }
        }

    }
// Save file==============================================================================================================================

    void saveFile() {
        String str = playlistnameinput.getText();
        System.out.println(str);
        if (str.length() > 0 && !(str.charAt(0) == 32)) {
            filename = new File("c:/MyplayList/" + str + ".abi");
            if (filename.exists()) {
                playlistnameinput.setText("");
            } else {
                try {
                    filename.createNewFile();
                    if (PlayerDesign.newfile) {
                        PlayerDesign.newfile = false;
                        return;
                    }
                    String wstr = "";
                    FileWriter filewriter = new FileWriter(filename);

                    Iterator itr1 = Play.pathsset.iterator();
                    Iterator itr2 = Play.namesset.iterator();
                    while (itr1.hasNext() && itr2.hasNext()) {
                        wstr += "<" + (String) itr1.next() + "?" + (String) itr2.next() + ">";
                    }
                    filewriter.write(wstr, 0, wstr.length());
                    filewriter.close();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } finally {
                    dispose();
                }
            }
        } else {
            dispose();
            EmptyBox em = new EmptyBox();
            em.setLocation(MyPlayer.width / 2, MyPlayer.height / 2);
            em.empty("Enter Correct Name");
        }
    }
//valueChanged=====================================================================================================================	

    public void valueChanged(ListSelectionEvent e) {
        idex = edllist.getSelectedIndex();
    }
//Setting rate================================================================================================================================

    public void MediaRateChange() {
        empbox = new JDialog();

        Container con = getContentPane();
        con.setBackground(Color.darkGray);
        con.setLayout(null);
        empmsg = new JLabel("Play RateChange");
        empmsg.setForeground(Color.white);
        empmsg.setBounds(60, 20, 200, 20);
        /*
        empimgicon=new JLabel(new ImageIcon(MyPlayer.imgpath+"error.png"));
        empimgicon.setBounds(5, 5, 50, 50);
        con.add(empmsg);
        con.add(empimgicon);
        empclose=new JButton("OK");
        empclose.setBounds(90,60,70,20);
        con.add(empclose);
        empbtc=empclose.getBackground();
        empclose.addActionListener(this);
        empclose.addMouseListener(this);
        empmsg.addMouseListener(this);
        MyPlayer.mp.frame.disable();
         */
        setResizable(false);
        setSize(240, 130);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }

//Event Actions===============================================================================================================================		
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == edledit) {
            editPlList();
            dispose();
            st = new SelectTheams();
            st.setLocation(MyPlayer.width / 2, MyPlayer.height / 2);
            st.editSongList();
            //MyPlayer.mp.frame.enable();
        }

        if (ae.getSource() == rateup) {
            state = ply.getstate();
            if (state == javax.media.Controller.Started || state == javax.media.Controller.Prefetched) {

                if (vary < 1.9f) {
                    vary = vary + 0.1f;
                    Play.playMP3.setRate(vary);
                    System.out.println("current rate:" + vary);
                } else {
                    System.out.println("Full speed");
                }
            }
        }

        if (ae.getSource() == ratedown) {
            state = ply.getstate();
            if (state == javax.media.Controller.Started || state == javax.media.Controller.Prefetched) {

                if (vary > 0.09f) {
                    vary = vary - 0.1f;
                    Play.playMP3.setRate(vary);
                    System.out.println("current rate:" + vary);
                } else {
                    System.out.println("lowest speed");
                }
            }
        }

        if (ae.getSource() == ratenormal) {
            state = ply.getstate();
            if (state == javax.media.Controller.Started || state == javax.media.Controller.Prefetched) {

                Play.playMP3.setRate(1.0f);
                vary = 1.0f;
            }
        }

        if (ae.getSource() == pldisable) {
            sleepon = false;
            dispose();
            MyPlayer.mp.frame.enable();
        }
        if (ae.getSource() == edlrename) {
            dispose();
            renameList();
            flag = true;
            //MyPlayer.mp.frame.enable();
        }
        if (ae.getSource() == edldelete) {
            deletePlfile();
            //MyPlayer.mp.frame.enable();
        }
        if (ae.getSource() == edlhide) {
            dispose();
            MyPlayer.mp.frame.enable();
        }
        if (ae.getSource() == opclose) {
            dispose();
            MyPlayer.mp.frame.enable();
        }
        if (ae.getSource() == canclose) {
            dispose();
            MyPlayer.mp.frame.enable();
        }
        if (ae.getSource() == hmclose) {
            dispose();
            MyPlayer.mp.frame.enable();
        }

        if (ae.getSource() == empclose) {
            dispose();
            MyPlayer.mp.frame.enable();
        }
        if (ae.getSource() == canok) {
            if (flag) {
                renameFile();
                flag = false;
                MyPlayer.mp.frame.enable();
            } else {
                saveFile();
            }
            MyPlayer.mp.frame.enable();
        }
        if (ae.getSource() == slpclose) {
            dispose();
            MyPlayer.mp.frame.enable();
        }
        if (ae.getSource() == slpnext) {
            if (plshut.isSelected()) {
                plyer = true;
                plsys = false;
                st = new SelectTheams();
                st.setLocation(MyPlayer.width / 2, MyPlayer.height / 2);
                st.playerSleep();
                dispose();
                //MyPlayer.mp.frame.enable();
            }
            if (plsyshut.isSelected()) {
                plyer = false;
                plsys = true;
                st = new SelectTheams();
                st.setLocation(MyPlayer.width / 2, MyPlayer.height / 2);
                st.playerSystemSleep();
                dispose();
                //MyPlayer.mp.frame.enable();
            }
        }
    }
//mouse event actions===========================================================================================================         

    public void mouseClicked(MouseEvent arg0) {
    }

    public void mouseEntered(MouseEvent ag) {
        if (ag.getSource() == slpclose) {
            slpclose.setBackground(allbtc);
        }
        if (ag.getSource() == slpnext) {
            slpnext.setBackground(allbtc);
        }
        if (ag.getSource() == rateup) {
            rateup.setBackground(allbtc);
        }
        if (ag.getSource() == ratedown) {
            ratedown.setBackground(allbtc);
        }
        if (ag.getSource() == ratenormal) {
            ratenormal.setBackground(allbtc);
        }
        if (ag.getSource() == empclose) {
            empclose.setBackground(allbtc);
        }
        if (ag.getSource() == edledit) {
            edledit.setBackground(allbtc);
        }
        if (ag.getSource() == edlrename) {
            edlrename.setBackground(allbtc);
        }
        if (ag.getSource() == edldelete) {
            edldelete.setBackground(allbtc);
        }
        if (ag.getSource() == edlhide) {
            edlhide.setBackground(allbtc);
        }
        if (ag.getSource() == canok) {
            canok.setBackground(allbtc);
        }
        if (ag.getSource() == canclose) {
            canclose.setBackground(allbtc);
        }
        if (ag.getSource() == hmclose) {
            hmclose.setBackground(allbtc);
        }
        if (ag.getSource() == opclose) {
            opclose.setBackground(allbtc);
        }
        if (ag.getSource() == pldisable) {
            pldisable.setForeground(Color.green);
        }
        if (ag.getSource() == plshut) {
            plshut.setForeground(Color.green);
        }
        if (ag.getSource() == plsyshut) {
            plsyshut.setForeground(Color.green);
        }
        if (ag.getSource() == canmsg) {
            canmsg.setForeground(Color.orange);
        }
        if (ag.getSource() == canmsg2) {
            canmsg2.setForeground(Color.gray);
        }
        if (ag.getSource() == canmsg3) {
            canmsg3.setForeground(Color.green);
        }
        if (ag.getSource() == opmsg) {
            opmsg.setForeground(Color.ORANGE);
        }
        if (ag.getSource() == myp) {
            myp.setForeground(Color.green);
        }
        if (ag.getSource() == empmsg) {
            empmsg.setForeground(Color.green);
        }
    }

    public void mouseExited(MouseEvent me) {
        if (me.getSource() == empclose) {
            empclose.setBackground(empbtc);
        }
        if (me.getSource() == rateup) {
            rateup.setBackground(empbtc);
        }
        if (me.getSource() == ratedown) {
            ratedown.setBackground(empbtc);
        }
        if (me.getSource() == ratenormal) {
            ratenormal.setBackground(empbtc);
        }
        if (me.getSource() == opclose) {
            opclose.setBackground(opbtc);
        }
        if (me.getSource() == canclose) {
            canclose.setBackground(canbtc);
        }
        if (me.getSource() == canok) {
            canok.setBackground(canbtc);
        }
        if (me.getSource() == edledit) {
            edledit.setBackground(edlbtc);
        }
        if (me.getSource() == edlrename) {
            edlrename.setBackground(edlbtc);
        }
        if (me.getSource() == edldelete) {
            edldelete.setBackground(edlbtc);
        }
        if (me.getSource() == edlhide) {
            edlhide.setBackground(edlbtc);
        }
        if (me.getSource() == slpclose) {
            slpclose.setBackground(slpbtc);
        }
        if (me.getSource() == slpnext) {
            slpnext.setBackground(slpbtc);
        }
        if (me.getSource() == hmclose) {
            hmclose.setBackground(hmbtc);
        }

        if (me.getSource() == pldisable) {
            pldisable.setForeground(Color.white);
        }
        if (me.getSource() == plshut) {
            plshut.setForeground(Color.white);
        }
        if (me.getSource() == plsyshut) {
            plsyshut.setForeground(Color.white);
        }
        if (me.getSource() == empmsg) {
            empmsg.setForeground(Color.white);
        }
        if (me.getSource() == opmsg) {
            opmsg.setForeground(Color.white);
        }
        if (me.getSource() == myp) {
            myp.setForeground(Color.white);
        }
        if (me.getSource() == canmsg) {
            canmsg.setForeground(Color.white);
        }
        if (me.getSource() == canmsg2) {
            canmsg2.setForeground(Color.white);
        }
        if (me.getSource() == canmsg3) {
            canmsg3.setForeground(Color.white);
        }
    }

    public void mousePressed(MouseEvent mp) {
        if (mp.getSource() == slpclose) {
            slpclose.setForeground(allb);
        }
        if (mp.getSource() == slpnext) {
            slpnext.setForeground(allb);
        }
        if (mp.getSource() == empclose) {
            empclose.setForeground(allb);
        }
        if (mp.getSource() == rateup) {
            rateup.setForeground(allb);
        }
        if (mp.getSource() == ratedown) {
            ratedown.setForeground(allb);
        }
        if (mp.getSource() == ratenormal) {
            ratenormal.setForeground(allb);
        }
        if (mp.getSource() == edledit) {
            edledit.setForeground(allb);
        }
        if (mp.getSource() == edlrename) {
            edlrename.setForeground(allb);
        }
        if (mp.getSource() == edldelete) {
            edldelete.setForeground(allb);
        }
        if (mp.getSource() == edlhide) {
            edlhide.setForeground(allb);
        }
        if (mp.getSource() == canok) {
            canok.setForeground(allb);
        }
        if (mp.getSource() == canclose) {
            canclose.setForeground(allb);
        }
        if (mp.getSource() == hmclose) {
            hmclose.setForeground(allb);
        }
        if (mp.getSource() == opclose) {
            opclose.setForeground(allb);
        }
    }

    public void mouseReleased(MouseEvent mr) {
        if (mr.getSource() == slpclose) {
            slpclose.setForeground(allr);
        }
        if (mr.getSource() == slpnext) {
            slpnext.setForeground(allr);
        }
        if (mr.getSource() == empclose) {
            empclose.setForeground(allr);
        }
        if (mr.getSource() == rateup) {
            rateup.setForeground(allr);
        }
        if (mr.getSource() == ratedown) {
            ratedown.setForeground(allr);
        }
        if (mr.getSource() == ratenormal) {
            ratenormal.setForeground(allr);
        }

        if (mr.getSource() == edledit) {
            edledit.setForeground(allr);
        }
        if (mr.getSource() == edlrename) {
            edlrename.setForeground(allr);
        }
        if (mr.getSource() == edldelete) {
            edldelete.setForeground(allr);
        }
        if (mr.getSource() == edlhide) {
            edlhide.setForeground(allr);
        }
        if (mr.getSource() == canok) {
            canok.setForeground(allr);
        }
        if (mr.getSource() == canclose) {
            canclose.setForeground(allr);
        }
        if (mr.getSource() == hmclose) {
            hmclose.setForeground(allr);
        }
        if (mr.getSource() == opclose) {
            opclose.setForeground(allr);
        }
    }
}
