//THIS CLASS IS BASICALLY FOR OVER ALL DESIGN OF THE MYPLAYER	
package Nothing;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.*;
import Nothing.Play;
import javax.swing.filechooser.FileFilter;
import javax.media.*;

public class PlayerDesign extends JFrame implements ActionListener, WindowListener, ChangeListener, MouseListener, Runnable {

    private static final long serialVersionUID = 5242600474143713744L;
    static JWindow ww = new JWindow();
    public static String mediapath = null;
    public static JLayeredPane j1;
    JMenuBar menubar;
    JMenu file, tool, playlist, help;
    JMenuItem fopen, fsave, fload, fexit;
    JMenuItem tgoonline, tcomment;
    static JMenuItem pcreation, pedit, premove, pdelete;
    JMenuItem hhelpdoc, haboutus;
    static JSlider jslider;
    JRadioButton jrepeat;
    JRadioButton jnormal;
    JRadioButton jrandom;
    JButton jprogsleep;
    JButton jtheams;
    JButton jtrackmix;
    static JButton jplaylist;
    JButton jsetrate;
    JButton jequilizer;
    static JButton jplay;
    static JButton jpause;
    static JButton jstop;
    JButton jnext;
    JButton jprev;
    JButton jup;
    JButton jdown;
    JToggleButton jmute;
    JLabel jearth;
    JLabel jy;
    JLabel jm;
    JLabel myp;
    JLabel dplaylistname, dtrackname, dtrackno, dmediatime;
    static JLabel dvplaylistname, dvtrackname, dvtrackno, dvmediatime;
    ButtonGroup bg;
    String imgpath = MyPlayer.imgpath;
    String dvplname = "--", dvtkname = "--", dvtkno = "--", dvmdtime = "--";
    URL url1;
    Component comp;
    int decy = 35, songlen;
    public JFileChooser jfd, loadplaylist;
    JProgressBar proc;
    float g = 0.05f, level = 0f;
    float a = 0.5f, b = 0.5f, c = 0.9f;
    long slidervalue;
    public static int setslider = 0;
    int volstate = 0;
    public static int verify1 = 0;
    JPanel panel;
    static Play pl;
    Color bc;
    Thread runslider;
    int i;
    static public boolean newfile = false;
    MyPlayer mp = new MyPlayer();
//constructor==============================================================================================		
    public PlayerDesign() {
        super("THIS IS \"NOTHING\"v2.0(Beta)");
        pl = new Play();
    }
//Background Image========================================================================================= 		 
    public void PlayerDesignMethod(String car, boolean bg) {
        String bgimg = car;
        ImageIcon image = new ImageIcon(bgimg);
        JLabel background = new JLabel(image);
        background.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
        j1 = getLayeredPane();
        j1.add(background, new Integer(Integer.MIN_VALUE));
        if (bg) {
            j1.moveToFront(background);
        }
    }
//Components Declaration====================================================================================

    public void DefineComponents() {
        panel = new JPanel(null);
        panel.setOpaque(false);
        setContentPane(panel);

        menubar = new JMenuBar();
        menubar.setBackground(Color.DARK_GRAY);
        file = new JMenu("File");
        file.setToolTipText("File Menu");
        file.setMnemonic(KeyEvent.VK_F);
        file.setForeground(Color.lightGray);
        file.setBackground(Color.darkGray);

        fopen = new JMenuItem("Open");
        fopen.setMnemonic(KeyEvent.VK_O);
        KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_O, Event.CTRL_MASK);
        fopen.setAccelerator(ks);

        fsave = new JMenuItem("Save");
        fsave.setMnemonic(KeyEvent.VK_S);
        ks = KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK);
        fsave.setAccelerator(ks);

        fload = new JMenuItem("Load");
        fload.setMnemonic(KeyEvent.VK_L);
        ks = KeyStroke.getKeyStroke(KeyEvent.VK_L, Event.CTRL_MASK);
        fload.setAccelerator(ks);

        fexit = new JMenuItem("Exit");
        fexit.setMnemonic(KeyEvent.VK_Q);
        ks = KeyStroke.getKeyStroke(KeyEvent.VK_Q, Event.CTRL_MASK);
        fexit.setAccelerator(ks);

        tool = new JMenu("Tool");
        tool.setForeground(Color.lightGray);
        tool.setBackground(Color.darkGray);
        tgoonline = new JMenuItem("Go-Online");
        tcomment = new JMenuItem("Comment");

        jslider = new JSlider();

        playlist = new JMenu("PlayList");
        playlist.setToolTipText("Play List Menu");
        playlist.setMnemonic(KeyEvent.VK_P);
        playlist.setForeground(Color.lightGray);
        playlist.setBackground(Color.darkGray);
        pcreation = new JMenuItem("PlayList Creation");
        pcreation.setMnemonic(KeyEvent.VK_C);
        ks = KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK);
        pcreation.setAccelerator(ks);

        pedit = new JMenuItem("Edit PlayList");
        pedit.setMnemonic(KeyEvent.VK_E);
        ks = KeyStroke.getKeyStroke(KeyEvent.VK_E, Event.CTRL_MASK);
        pedit.setAccelerator(ks);


        pdelete = new JMenuItem("Delete PlayList");

        help = new JMenu("Help ?");
        help.setToolTipText("Help Menu");
        help.setMnemonic(KeyEvent.VK_H);
        help.setForeground(Color.lightGray);
        help.setBackground(Color.darkGray);
        hhelpdoc = new JMenuItem("Help");
        hhelpdoc.setMnemonic(KeyEvent.VK_H);
        ks = KeyStroke.getKeyStroke(KeyEvent.VK_H, Event.CTRL_MASK);
        hhelpdoc.setAccelerator(ks);

        haboutus = new JMenuItem("About Us");
        haboutus.setMnemonic(KeyEvent.VK_U);
        ks = KeyStroke.getKeyStroke(KeyEvent.VK_U, Event.CTRL_MASK);
        haboutus.setAccelerator(ks);

        //radio buttons             
        jnormal = new JRadioButton("NORMAL", true);
        jnormal.setToolTipText("Normal Order");

        jnormal.setBounds(50, 70 - decy, 100, 20);
        jnormal.setOpaque(false);

        jrepeat = new JRadioButton("REPEAT");
        jrepeat.setToolTipText("Repeat Order");

        jrepeat.setBounds(50, 100 - decy, 100, 20);
        jrepeat.setOpaque(false);

        jrandom = new JRadioButton("RANDOM");
        jrandom.setToolTipText("Random Order");
        jrandom.setBounds(50, 130 - decy, 100, 20);
        jrandom.setOpaque(false);
//buttons
        jprogsleep = new JButton("SLEEP");
        jprogsleep.setToolTipText("Set Sleep");
        jprogsleep.setBounds(250, 70 - decy, 100, 20);

        jtheams = new JButton("THEMES");
        jtheams.setToolTipText("Change Theam");
        jtheams.setBounds(250, 100 - decy, 100, 20);

        jtrackmix = new JButton("TRACK MIX");
        jtrackmix.setBounds(250, 130 - decy, 100, 20);

        jplaylist = new JButton("PLAYLIST");
        jplaylist.setToolTipText("Show Play List");
        jplaylist.setBounds(450, 70 - decy, 100, 20);

        jsetrate = new JButton("PLAYRATE");
        jsetrate.setBounds(450, 100 - decy, 100, 20);

        jequilizer = new JButton("EQUILIZER");
        jequilizer.setBounds(450, 130 - decy, 100, 20);
// buttons             
        jearth = new JLabel(new ImageIcon(imgpath + "earth.gif"));
        jearth.setBounds(720, 0, 60, 90);
        jy = new JLabel(new ImageIcon(imgpath + "yy.gif"));
        jy.setBounds(698, 50, 70, 90);
        jm = new JLabel(new ImageIcon(imgpath + "music.gif"));
        jm.setBounds(720, 90, 60, 90);
        myp = new JLabel("My Player");
        myp.setForeground(Color.white);
        myp.setBounds(720, 150, 100, 50);

        dplaylistname = new JLabel("PLAYLIST");
        dplaylistname.setForeground(Color.cyan);
        dplaylistname.setBounds(220, 230, 100, 20);

        dvplaylistname = new JLabel(dvplname);
        dvplaylistname.setForeground(Color.cyan);
        dvplaylistname.setBounds(420, 230, 150, 20);

        dtrackname = new JLabel("TRACK");
        dtrackname.setForeground(Color.cyan);
        dtrackname.setBounds(220, 250, 150, 20);

        dvtrackname = new JLabel(dvtkname);
        dvtrackname.setForeground(Color.cyan);
        dvtrackname.setBounds(420, 250, 150, 20);

        dtrackno = new JLabel("TRACK NO.");
        dtrackno.setForeground(Color.cyan);
        dtrackno.setBounds(220, 270, 100, 20);

        dvtrackno = new JLabel(dvtkno);
        dvtrackno.setForeground(Color.cyan);
        dvtrackno.setBounds(420, 270, 150, 20);

        dmediatime = new JLabel("TRACK TIME");
        dmediatime.setForeground(Color.cyan);
        dmediatime.setBounds(220, 290, 100, 20);

        dvmediatime = new JLabel(dvmdtime);
        dvmediatime.setForeground(Color.cyan);
        dvmediatime.setBounds(420, 290, 150, 20);

//adding button to the panel
        jplay = new JButton(new ImageIcon(imgpath + "play.png"));
        jplay.setToolTipText("Play");
        //jplay.setOpaque(false);
        jplay.setBounds(141, 288, 20, 20);

        jpause = new JButton(new ImageIcon(imgpath + "pause.png"));
        jpause.setToolTipText("Pause");
        // jpause.setOpaque(false);
        jpause.setBounds(141, 258, 20, 20);

        jstop = new JButton(new ImageIcon(imgpath + "stop.png"));
        jstop.setToolTipText("Stop");
        //jstop.setOpaque(false);
        jstop.setBounds(141, 320, 20, 20);

        jnext = new JButton(new ImageIcon(imgpath + "next.png"));
        jnext.setToolTipText("Next Track");
        //jnext.setOpaque(false);
        jnext.setBounds(171, 288, 20, 20);

        jprev = new JButton(new ImageIcon(imgpath + "prev.png"));
        jprev.setToolTipText("Previous Track");
        //jprev.setOpaque(false);
        jprev.setBounds(111, 288, 20, 20);

        jup = new JButton(new ImageIcon(imgpath + "up.png"));
        jup.setToolTipText("Volume Up");
        jup.setBounds(635 - 5, 255 + 12, 30, 30);

        jdown = new JButton(new ImageIcon(imgpath + "down.png"));
        jdown.setToolTipText("Volume Down");
        jdown.setBounds(635 - 5, 295 + 16, 30, 30);

        jmute = new JToggleButton(new ImageIcon(imgpath + "mut.png"));
        jmute.setToolTipText("Mute Volume");
        jmute.setBounds(637, 297, 17, 15);

        jplay.setPreferredSize(new Dimension(20, 20));
        jstop.setPreferredSize(new Dimension(20, 20));
        jpause.setPreferredSize(new Dimension(20, 20));
        jnext.setPreferredSize(new Dimension(20, 20));
        jprev.setPreferredSize(new Dimension(20, 20));
        //jplaylist.setPreferredSize(new Dimension())
        jslider.setBounds(210, 328, 365, 15);
        jslider.setForeground(Color.CYAN);
        jslider.setMaximum(3);
        jslider.setValue(0);
//progresbar	    
        proc = new JProgressBar(1, 0, 100);
        proc.setBounds(665, 255 + 12, 10, 75);
        proc.setForeground(Color.cyan);
        proc.setValue(20);

    }
//Adding,Listener========================================================================================== 		 

    public void AddAllComponents() {
        panel.add(menubar);
        setJMenuBar(menubar);

        menubar.add(file);
        //menubar.add(tool); 
        menubar.add(playlist);
        menubar.add(help);

        file.add(fopen);
        file.add(fsave);
        file.add(fload);
        file.addSeparator();
        file.add(fexit);

        tool.add(tgoonline);
        tool.add(tcomment);

        playlist.add(pcreation);
        playlist.add(pedit);
        // playlist.add(premove);
        //playlist.add(pdelete);

        help.add(hhelpdoc);
        help.add(haboutus);

        bg = new ButtonGroup();
        bg.add(jnormal);
        bg.add(jrepeat);
        bg.add(jrandom);

        panel.add(jslider);
        panel.add(jplay);
        panel.add(jpause);
        panel.add(jstop);
        panel.add(jnext);
        panel.add(jprev);
        panel.add(jrepeat);
        panel.add(jnormal);
        panel.add(jrandom);
        panel.add(jprogsleep);
        panel.add(jtheams);
        panel.add(jtrackmix);
        panel.add(jplaylist);
        panel.add(jequilizer);
        panel.add(jsetrate);
        panel.add(proc);
        //panel.add(Play.playMP3.getVisualComponent());

        panel.add(jearth);
        panel.add(jm);
        panel.add(jy);
        panel.add(myp);
        panel.add(jup);
        panel.add(jdown);
        panel.add(jmute);
        panel.add(dplaylistname);
        panel.add(dtrackname);
        panel.add(dtrackno);
        panel.add(dmediatime);
        panel.add(dvplaylistname);
        panel.add(dvtrackname);
        panel.add(dvtrackno);
        panel.add(dvmediatime);
        pack();
//add Action Listener
        jplay.addActionListener(this);
        jstop.addActionListener(this);
        fexit.addActionListener(this);
        fopen.addActionListener(this);
        fsave.addActionListener(this);
        fload.addActionListener(this);
        pcreation.addActionListener(this);
        pedit.addActionListener(this);
        pdelete.addActionListener(this);
        jup.addActionListener(this);
        jdown.addActionListener(this);
        jpause.addActionListener(this);
        jtheams.addActionListener(this);
        jplaylist.addActionListener(this);
        //jslider.addChangeListener(this);
        jnext.addActionListener(this);
        jprev.addActionListener(this);
        jnormal.addActionListener(this);
        jrepeat.addActionListener(this);
        jrandom.addActionListener(this);
        jmute.addActionListener(this);
        jequilizer.addActionListener(this);
        jprogsleep.addActionListener(this);
        hhelpdoc.addActionListener(this);
        haboutus.addActionListener(this);
        jtrackmix.addActionListener(this);
        jsetrate.addActionListener(this);
        tgoonline.addActionListener(this);
        tcomment.addActionListener(this);
        addWindowListener(this);

        //add Mouse Listener            
        jplay.addMouseListener(this);
        jstop.addMouseListener(this);
        jpause.addMouseListener(this);
        jnext.addMouseListener(this);
        jprev.addMouseListener(this);
        jup.addMouseListener(this);
        jdown.addMouseListener(this);
        jprogsleep.addMouseListener(this);
        jtheams.addMouseListener(this);
        jtrackmix.addMouseListener(this);
        jplaylist.addMouseListener(this);
        jequilizer.addMouseListener(this);
        jsetrate.addMouseListener(this);
        jslider.addMouseListener(this);
        file.addMouseListener(this);
        playlist.addMouseListener(this);
        help.addMouseListener(this);
        myp.addMouseListener(this);
        jnormal.addMouseListener(this);
        jrepeat.addMouseListener(this);
        jrandom.addMouseListener(this);
        tool.addMouseListener(this);
        tgoonline.addActionListener(this);
        tcomment.addActionListener(this);

    }
//method for calling thread ==============================================================================================================		 

    public void moveSlider() {

        runslider = new Thread(this, "rajeev");
        runslider.setDaemon(true);
        runslider.setPriority(6);
        runslider.start();
    }

    public void run() {
        sliderRunning();
    }
//testing slider===================================================================================================

    public void sliderRunning() {

        for (i = Play.sincr; i <= setslider; i++) {
            if (Play.SSTOP) {
                Play.sincr = 0;
                break;
            }
            if (Play.SPAUSE) {
                Play.sincr = i;
                break;
            }
            if (Play.SPLAY) {
                try {
                    jslider.setValue(i);
                    Thread.sleep(999);
                } catch (Exception e) {
                }
            }
        }
    }
//jplay action===========================================================================================

    public void jplayaction() {
        if ((Play.path == null && Play.playMP3 == null) || Play.path == null) {
            pl.stop();
            EmptyBox op = new EmptyBox();
            op.setLocation(MyPlayer.width / 2, MyPlayer.height / 2);
            op.empty("PLEASE SELECT MEDIA FILE");
        } else {
            pl.locate("file:///" + Play.path);
            pl.play();

            jplay.setEnabled(false);
        }
    }
// implementing filefilter=====================================================================================================================    

    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }
        String name = f.getName().toLowerCase();
        return name.endsWith("mp3");
    }//end accept

//fopen action==============================================================================================
    public void fopenaction() {
        System.out.println(jfd);
        if (jfd == null && DisplayList.jfd == null) {
            jfd = new JFileChooser();
            DisplayList.jfd = jfd;
        }
        jfd.setFileFilter(new mp3Filter("media files"));
        //jfd.addChoosableFileFilter(new PlayerDesign());
        jfd.setMultiSelectionEnabled(true);

        int ret = jfd.showOpenDialog(this);
        if (ret == JFileChooser.CANCEL_OPTION) {
            return;
        }
        File[] file = jfd.getSelectedFiles();
        if (ret == JFileChooser.APPROVE_OPTION) {
            Play.mp3files(file);
        }
        jplay.doClick();
        return;
    }
//fsave action====================================================================================================

    public void fsaveaction() {
        if (Play.namesset.size() == 0) {
            EmptyBox op = new EmptyBox();
            op.setLocation(MyPlayer.width / 2, MyPlayer.height / 2);
            op.empty("THERE IS NOTHING TO SAVE");
        } else {
            File createfolder = new File("c:/MyPlayList/");
            createfolder.mkdir();
            EmptyBox opp = new EmptyBox();
            opp.setLocation(MyPlayer.width / 2, MyPlayer.height / 2);
            opp.canSave("PLEASE ENTER CURRENT");
        }
    }
//fload action=============================================================================================

    public void floadaction() {
        loadplaylist = new JFileChooser("c:/MyPlayList/");
        loadplaylist.setFileFilter(new mp3Filter("abi files"));
        loadplaylist.setMultiSelectionEnabled(false);

        int ret = loadplaylist.showOpenDialog(this);
        if (ret == JFileChooser.CANCEL_OPTION) {
            return;
        }
        File file = loadplaylist.getSelectedFile();
        if (ret == JFileChooser.APPROVE_OPTION) {
            Play.loadFiles(file);
        }
        jplay.setEnabled(true);
        jplay.doClick();
        return;
    }
//pcreate action==========================================================================================

    public void pcreationaction() {
        newfile = true;
        File createfolder = new File("c:/MyPlayList/");
        createfolder.mkdir();
        EmptyBox opp = new EmptyBox();
        opp.setLocation(MyPlayer.width / 2, MyPlayer.height / 2);
        opp.canSave("      PLEASE ENTER THE");
    }
//pedit action============================================================================================

    public void peditaction() {
        EmptyBox opp = new EmptyBox();
        opp.setLocation(MyPlayer.width / 2, MyPlayer.height / 2);
        opp.loadEditList();
        opp.editList();
    }
//jtheams action==========================================================================================

    public void jtheamsaction() {
        SelectTheams theam = new SelectTheams();
        theam.setLocation(MyPlayer.width / 2, MyPlayer.height / 2);
        theam.loadTheam();
        theam.select();
    }
//jplaylist action=======================================================================================

    public void jplaylistaction() {
        if (Play.path == null && Play.playMP3 == null) {
            EmptyBox op = new EmptyBox();
            op.setLocation(MyPlayer.width / 2, MyPlayer.height / 2);
            op.empty("PLEASE SELECT MEDIA FILE");
        } else {
            DisplayList dpl = new DisplayList();
            dpl.display();
            dpl.setLocation(MyPlayer.width / 2, MyPlayer.height / 2);

        }

    }
//jup action==============================================================================================

    public void jupaction() {
        if (jmute.isSelected()) {
            jmute.doClick();
        }
        int state = pl.getstate();
        if (state == javax.media.Controller.Started || state == javax.media.Controller.Prefetched) {
            if (g <= 1.0f) {
                g = Play.playMP3.getGainControl().getLevel();
                g = g + 0.05f;
                float me = g * 100;
                volstate = (int) me;
                if (volstate >= 50 && volstate < 80) {
                    proc.setForeground(Color.green);
                } else if (volstate >= 80) {
                    proc.setForeground(Color.red);
                }
                proc.setValue(volstate);
                pl.gain(g);
            } else {
                System.out.println("Volume is full");
            }
        }
    }
//jdown action===============================================================================================

    public void jdownaction() {
        if (jmute.isSelected()) {
            jmute.doClick();
        }
        int state = pl.getstate();
        if (state == javax.media.Controller.Started || state == javax.media.Controller.Prefetched) {
            if (g >= 0.0f) {
                g = Play.playMP3.getGainControl().getLevel();
                g = g - 0.05f;
                float me = g * 100;
                volstate = (int) me;
                if (volstate >= 80 && volstate <= 100) {
                    proc.setForeground(Color.red);
                } else if (volstate < 80 && volstate >= 50) {
                    proc.setForeground(Color.green);
                } else if (volstate < 50 && volstate >= 0) {
                    proc.setForeground(Color.CYAN);
                }
                proc.setValue(volstate);
                pl.gain(g);
            } else {
                System.out.println("Volume is mute");
            }
        }
    }
//jmute action============================================================================================

    public void jmuteaction() {
        if (Play.path == null && Play.playMP3 == null) {
            return;
        } else {
            boolean m = jmute.isSelected();
            if (m == true) {
                level = g;
                pl.gain(0.0f);
                proc.setValue(0);
            }
            if (m == false) {
                pl.gain(level);
                float me = level * 100;
                volstate = (int) me;
                proc.setValue(volstate);
            }
        }
    }
//========================================================================================================	
// controll button actions

    public void actionPerformed(ActionEvent e) {

//Play Button	
        if (e.getSource() == jplay) {
            jplayaction();
        }
//Stop Button	
        if (e.getSource() == jstop) {
            pl.stop();
        }
//Pause Button    
        if (e.getSource() == jpause) {
            pl.pause();
            //setSliderMax();
        }
//Exit in File menu
        if (e.getSource() == fexit) {
            System.exit(0);
        }
//Open in File menu    
        if (e.getSource() == fopen) {
            fopenaction();
        }
//Save playlist in File menu
        if (e.getSource() == fsave) {
            fsaveaction();
        }

// Load Playlist in File Menu 
        if (e.getSource() == fload) {
            floadaction();
        }
// Tool_online 
        if (e.getSource() == tgoonline) {
            if (verify1 == 0) {
                //IMusic imusic=new IMusic();
                //imusic.browseme();
                //imusic.setLocation(MyPlayer.width/2,MyPlayer.height/2);
                verify1 = 1;
            }
        }
// Tool_comments    
        if (e.getSource() == tcomment) {
        }
// Create PlayList
        if (e.getSource() == pcreation) {
            pcreationaction();
        }

// Edit PlayList
        if (e.getSource() == pedit) {
            peditaction();
        }
//Themes Button    
        if (e.getSource() == jtheams) {
            jtheamsaction();
        }

//Display Playlist
        if (e.getSource() == jplaylist) {
            jplaylistaction();
        }

//volume control Up Button
        if (e.getSource() == jup) {
            jupaction();
        }
//volume control down Button     
        if (e.getSource() == jdown) {
            jdownaction();
        }
//Mute Button
        if (e.getSource() == jmute) {
            jmuteaction();
        }
//Next song Button
        if (e.getSource() == jnext) {
            pl.next();
        }
//Previous song Button     
        if (e.getSource() == jprev) {
            pl.previous();
        }
//Select Normal Radio Button     
        if (e.getSource() == jnormal) {
            Play.order = 1;
        }
//Select Repeat Radio Button     
        if (e.getSource() == jrepeat) {
            Play.order = 2;
        }
//Select Random Radio Button
        if (e.getSource() == jrandom) {
            Play.order = 3;
        }
//Select TrackMix Button
        if (e.getSource() == jtrackmix) {
            EmptyBox eb = new EmptyBox();
            eb.setLocation(MyPlayer.width / 2, MyPlayer.height / 2);
            eb.empty("Under Construction Work");
        }
//Select jequilizer Button
        if (e.getSource() == jequilizer) {

            //JOptionPane.showMessageDialog(MyPlayer.mp.frame,
            //"Under Construction Work");

            EmptyBox eb = new EmptyBox();
            eb.setLocation(MyPlayer.width / 2, MyPlayer.height / 2);
            eb.empty("Under Construction Work");
        }
//Select jplayrate Button
        if (e.getSource() == jsetrate) {


            EmptyBox eb = new EmptyBox();
            eb.setLocation(MyPlayer.width / 2, MyPlayer.height / 2);
            eb.playerRate();

        }
//Select Sleep Button     
        if (e.getSource() == jprogsleep) {
            EmptyBox eb = new EmptyBox();
            eb.setLocation(MyPlayer.width / 2, MyPlayer.height / 2);
            eb.sleepmodule();

        }
        if (e.getSource() == hhelpdoc) {
            EmptyBox oo = new EmptyBox();
            oo.setLocation(MyPlayer.width / 2, this.getY());
            oo.helpmodule(MyPlayer.imgpath + "help.jpg");
        }
        if (e.getSource() == haboutus) {
            EmptyBox oo = new EmptyBox();
            oo.setLocation(MyPlayer.width / 2, this.getY());
            oo.aboutUs(MyPlayer.imgpath + "aboutus.jpg");
        }
    }

//window actions===========================================================================================		
    public void windowOpened(WindowEvent wv) {
        setLocation(MyPlayer.width / 6, MyPlayer.height / 4);
        EmptyBox op = new EmptyBox();
        op.setLocation((int) (MyPlayer.width / 2.7), MyPlayer.height / 3);
        MyPlayer.width = this.getX() + this.getWidth() / 2;
        MyPlayer.height = this.getY() + this.getHeight() / 2;
        MyPlayer.width *= 1.5;
        MyPlayer.height *= 1.5;
        op.openWindow();
        System.out.println("welcome to My Player");

    }

    public void windowActivated(WindowEvent arg0) {
    }

    public void windowClosed(WindowEvent arg0) {
    }

    public void windowClosing(WindowEvent arg0) {

        System.out.println("Thank you for usinig");
    }

    public void windowDeactivated(WindowEvent wee) {
    }

    public void windowDeiconified(WindowEvent wcc) {
    }

    public void windowIconified(WindowEvent arg0) {
    }

    public void stateChanged(ChangeEvent ss) {
        JSlider source = (JSlider) ss.getSource();
        //jslider.setValue(2);
        if (source == jslider) //System.out.println("true");
        {
            if (!source.getValueIsAdjusting()) {
                int fps = (int) source.getValue();
                Time t = new Time(fps);

                Play.playMP3.setMediaTime(t);



                System.out.println("Slider adjusting " + fps);
                i = fps;
            }
        }
    }

//Mouse Events================================================================================================		
    public void mouseClicked(MouseEvent mm) {
        if (mm.getSource() == myp) {
            setLocation(MyPlayer.width / 2, MyPlayer.height / 2);
            EmptyBox op = new EmptyBox();
            op.setLocation((int) (MyPlayer.width / 2.7), MyPlayer.height / 2);
            op.openWindow();
        }
    }

    public void mouseEntered(MouseEvent ae) {

        bc = jplay.getBackground();
        if (ae.getSource() == jplay) {
            jplay.setBackground(Color.getHSBColor(a, b, c));
        }
        if (ae.getSource() == jstop) {
            jstop.setBackground(Color.getHSBColor(a, b, c));
        }
        if (ae.getSource() == jnext) {
            jnext.setBackground(Color.getHSBColor(a, b, c));
        }
        if (ae.getSource() == jprev) {
            jprev.setBackground(Color.getHSBColor(a, b, c));
        }
        if (ae.getSource() == jpause) {
            jpause.setBackground(Color.getHSBColor(a, b, c));
        }
        if (ae.getSource() == jup) {
            jup.setBackground(Color.getHSBColor(a, b, c));
        }
        if (ae.getSource() == jdown) {
            jdown.setBackground(Color.getHSBColor(a, b, c));
        }
        if (ae.getSource() == jtheams) {
            jtheams.setBackground(Color.getHSBColor(0, 0, 100));
        }
        if (ae.getSource() == jplaylist) {
            jplaylist.setBackground(Color.getHSBColor(0, 0, 100));
        }
        if (ae.getSource() == jprogsleep) {
            jprogsleep.setBackground(Color.getHSBColor(0, 0, 100));
        }
        if (ae.getSource() == jtrackmix) {
            jtrackmix.setBackground(Color.getHSBColor(0, 0, 100));
        }
        if (ae.getSource() == jsetrate) {
            jsetrate.setBackground(Color.getHSBColor(0, 0, 100));
        }
        if (ae.getSource() == jequilizer) {
            jequilizer.setBackground(Color.getHSBColor(0, 0, 100));
        }
        if (ae.getSource() == jslider) {
            jslider.setBackground(Color.LIGHT_GRAY);
        }
        if (ae.getSource() == file) {
            file.setForeground(Color.cyan);
        }
        if (ae.getSource() == tool) {
            tool.setForeground(Color.cyan);
        }
        if (ae.getSource() == playlist) {
            playlist.setForeground(Color.cyan);
        }
        if (ae.getSource() == help) {
            help.setForeground(Color.cyan);
        }
        if (ae.getSource() == myp) {
            myp.setForeground(Color.red);

        }
        if (ae.getSource() == jnormal) {
            jnormal.setForeground(Color.ORANGE);

        }
        if (ae.getSource() == jrepeat) {
            jrepeat.setForeground(Color.white);
        }
        if (ae.getSource() == jrandom) {
            jrandom.setForeground(Color.green);

        }
    }

    public void mouseExited(MouseEvent arg0) {

        jplay.setBackground(bc);
        jstop.setBackground(bc);
        jnext.setBackground(bc);
        jprev.setBackground(bc);
        jpause.setBackground(bc);
        jup.setBackground(bc);
        jdown.setBackground(bc);
        jtheams.setBackground(bc);
        jplaylist.setBackground(bc);
        jprogsleep.setBackground(bc);
        jtrackmix.setBackground(bc);
        jsetrate.setBackground(bc);
        jequilizer.setBackground(bc);
        jslider.setBackground(bc);
        file.setForeground(bc);
        tool.setForeground(bc);
        playlist.setForeground(bc);
        help.setForeground(bc);
        myp.setForeground(bc);
        jnormal.setForeground(Color.black);
        jrepeat.setForeground(Color.black);
        jrandom.setForeground(Color.black);
    }

    public void mousePressed(MouseEvent arg0) {
    }

    public void mouseReleased(MouseEvent mes) {
        if (mes.getSource() == jslider) {
            if (!jslider.getValueIsAdjusting()) {
                int fps = (int) jslider.getValue();
                Time t = new Time((double) fps);
                System.out.println("time object " + t.toString());
                Play.playMP3.setMediaTime(t);
                System.out.println("Slider adjusting " + fps);
                i = fps;
            }
        }
    }
}

class mp3Filter extends FileFilter {

    String des = "";

    public mp3Filter(String des) {
        this.des = des;
    }

    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        }
        String extension = getExtension(file);
        if ((extension.equals("mp3")) || (extension.equals("wav")) || (extension.equals("mpeg")) || (extension.equals("mpg") || (extension.equals("abi")))) {
            return true;
        }
        return false;
    }

    public String getDescription() {

        return des;
    }

    private String getExtension(File f) {
        String s = f.getName();
        int i = s.lastIndexOf('.');
        if (i > 0 && i < s.length() - 1) {
            return s.substring(i + 1).toLowerCase();
        }
        return "";
    }
}