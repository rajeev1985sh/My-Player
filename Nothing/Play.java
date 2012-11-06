//THIS CLASS IS BASICALLY FOR PLAYING THE SONG
package Nothing;

import javax.media.*;
import java.net.URL;
import java.awt.Color;
import java.io.*;
import java.util.*;
import javax.swing.JLabel;
import javax.media.protocol.DataSource;
import javax.media.control.FramePositioningControl;

public class Play implements ControllerListener, GainChangeListener {

    private static URL url;
    private MediaLocator mediaLocator;
    String m_type = null;
    FramePositioningControl fpc = null;
    public static Player playMP3;
    final static int NORMAL = 1;
    final static int REPEAT = 2;
    final static int RANDOM = 3;
    static boolean SPLAY = false;
    static boolean SPAUSE = false;
    static boolean SSTOP = false;
    static int sincr = 0;
    static int p = 0;
    static int chksldr = 6;
    static Time time = new Time(0);
    static String path;
    static int order = 1;
    static float volume = 0.2f;
    static int counter = 0;
    static int count = 0;
    static int[] store = new int[1];
    static String names[];
    static Set namesset = new LinkedHashSet();
    static Set pathsset = new LinkedHashSet();
    String add;
    static String paths[];
    Clock clk;
    Time time11;
    Time dd;
    MyPlayer mp = new MyPlayer();
    int min, sec;
    static String showplname = "";
    VideoCaller eb;
    File add_file = new File("C:/Documents and Settings/Abhi_2/workspace/Nothing/src/Nothing/Images/car theams");
    static int auto_count = 0;
//song address in string==================================================================================	

    public static void mp3files(File[] files) {
        SSTOP = true;
        SPLAY = false;
        //namesset=new LinkedHashSet();
        //pathsset=new LinkedHashSet();
        for (int i = 0; i < files.length; i++) {
            if (namesset.add(files[i].getName())) {
                pathsset.add(files[i].getAbsolutePath());
            }
            System.out.println(namesset.size());
            System.out.println(pathsset.size());
        }
        Play.count = namesset.size();
        //System.out.println(count);
        paths = new String[pathsset.size()];
        names = new String[namesset.size()];
        Iterator itr1 = pathsset.iterator();
        Iterator itr2 = namesset.iterator();

        int i = 0;
        while (itr1.hasNext()) {
            paths[i++] = (String) itr1.next();
        }
        i = 0;
        while (itr2.hasNext()) {
            names[i++] = (String) itr2.next();
            path = paths[0];
        }
        path = paths[0];
        count = files.length;
        store = new int[count];
        counter = Play.counter;
    }

// Removing song============================================================================================
    void removeSong(int idex) {
        String str = paths[idex];
        pathsset.remove(str);
        str = names[idex];
        namesset.remove(str);

        paths = new String[namesset.size()];
        names = new String[namesset.size()];

        Iterator itr1 = pathsset.iterator();
        Iterator itr2 = namesset.iterator();

        int i = 0;
        while (itr1.hasNext()) {
            paths[i++] = (String) itr1.next();
        }
        i = 0;
        while (itr2.hasNext()) {
            names[i++] = (String) itr2.next();
        }
    }

//Allocate Memory space and create player=====================================================================	 
    public void locate(String mp3) {
        if (playMP3 != null) {
            System.out.println("deallocate");
            playMP3.stop();
            playMP3.deallocate();
            playMP3.close();
        }
        try {
            Play.url = new URL(mp3);
            System.out.println(mp3);
        } catch (java.net.MalformedURLException e) {
            System.out.println(e.toString());
        }

        try {
            mediaLocator = new MediaLocator(url);
            DataSource ds = Manager.createDataSource(mediaLocator);
            //Seek seek = new Seek();

            System.out.println("content type " + ds.getContentType());
            m_type = ds.getContentType().substring(0, 5);
            //System.out.println();
            playMP3 = Manager.createPlayer(ds);
            //fpc = (FramePositioningControl)playMP3.getControl("javax.media.control.MpegAudioControl");
            //Control cc[] = playMP3.getControls();
            //System.out.println(playMP3.getRate());

        } catch (java.io.IOException e) {
            System.out.println(e.toString());
            System.out.println("A");
        } catch (javax.media.NoPlayerException e) {
            System.out.println(e.toString());
            System.out.println("B");
        } //catch(CannotRealizeException cre) {
        // cre.printStackTrace();
        //next();
        //return;
        //}
        catch (Throwable th) {
            System.out.println("D");
            next();
            System.out.println("D1");
        }

        playMP3.addControllerListener(this);
    }

// Load File in Playlist======================================================================================	 
    public static void loadFiles(File file) {
        try {
            System.out.println(file.getName());
            showplname = file.getName().substring(0, file.getName().lastIndexOf('.'));
            FileReader fread = new FileReader(file);
            pathsset.clear();
            namesset.clear();
            int ch = fread.read();
            System.out.println(pathsset.size());
            if (ch != '<') {
                System.out.println("blank");
                Play.path = null;
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
                    pathsset.add(songadd);
                    namesset.add(songname);
                    songadd = "";
                    songname = "";
                    ch = fread.read();
                }
            }
            Iterator itr1 = pathsset.iterator();
            Iterator itr2 = namesset.iterator();
            paths = new String[pathsset.size()];
            names = new String[namesset.size()];

            int i = 0;
            while (itr1.hasNext() && itr2.hasNext()) {
                names[i] = (String) itr2.next();
                paths[i++] = (String) itr1.next();
            }
            count = paths.length;
            System.out.println(pathsset.size());
            Play.path = paths[0];
            Play.counter = 0;
        } catch (IOException ioe) {
            ioe.getMessage();
        } finally {
        }
    }

//songLength===================================================================================================	 
    public String showTime() {
        Time b = playMP3.getDuration();
        sec = (int) b.getSeconds();
        min = sec / 60;
        sec = sec % 60;
        return min + " : " + sec;
    }

//Play Song method==============================================================================================
    public void play() {
        EmptyBox.vary = 1.0f;
        //PlayerDesign.jplay.disable();
        PlayerDesign.jpause.setEnabled(true);
        PlayerDesign.jstop.setEnabled(true);
        SSTOP = true;
        SPLAY = false;
        SPAUSE = true;
        try {
            Thread.sleep(1);
        } catch (InterruptedException ire) {
            ire.printStackTrace();
        }
        if (eb != null) {
            eb.check_click = false;
        }
        try {
            playMP3.realize();
        } catch (Throwable th) {
            System.out.println("AA");
        }
        if (m_type.equals("audio")) {
            if (eb != null) {
                eb.dispose();
                eb = null;
            }
        }
        if (p == 0) {
            try {

                playMP3.start();
            } catch (Throwable th) {
                System.out.println(th.toString());
                System.out.println("C");
                next();
            }
        }
    }

//stop song method===========================================================================================
    public void stop() {
        System.out.println("closing");
        PlayerDesign.jplay.setEnabled(true);
        PlayerDesign.jpause.setEnabled(false);
        PlayerDesign.jstop.setEnabled(false);
        p = 0;
        Play.sincr = 0;
        SSTOP = true;
        if (playMP3 != null) {
            playMP3.stop();
            //playMP3.deallocate();
            if (eb != null) {
                eb.dispose();
            }
            eb = null;
            playMP3.close();
            time = new Time(0);
            //playMP3=null;
        }
    }

//pause song method==========================================================================================
    public void pause() {
        PlayerDesign.jpause.setEnabled(false);
        PlayerDesign.jstop.setEnabled(true);
        PlayerDesign.jplay.setEnabled(true);
        p = 1;
        SPAUSE = true;
        if (playMP3 != null) {
            time = playMP3.getMediaTime();
            playMP3.stop();
            //playMP3.deallocate();
            //playMP3.close();
        }
        if (eb != null) {
            eb.check_click = true;
        }
    }

//go to next track==============================================================================================
    public void next() {
        if (SelectTheams.check_auto) {
            setAutoTheme();
        }
        PlayerDesign.jplay.setEnabled(false);
        p = 0;
        sincr = 0;
        SSTOP = true;
        SPLAY = false;
        Thread.yield();
        if (playMP3 != null) {
            System.out.println("playmp3");
            switch (order) {

                case NORMAL:
                case REPEAT:
                    repeatCounter(1);
                    break;
                case RANDOM:
                    randomCounter();
                    break;

            }
            if (counter >= 0 && counter < namesset.size()) {
                playMP3.stop();
                //playMP3.deallocate();
                //playMP3.close();
                path = paths[counter];
                locate("file:///" + path);
                play();
            }
        }

    }

//go to previous track=========================================================================================	 
    public void previous() {
        if (SelectTheams.check_auto) {
            setAutoTheme();
        }
        PlayerDesign.jplay.setEnabled(false);
        p = 0;
        sincr = 0;
        SSTOP = true;
        SPLAY = false;
        Thread.yield();
        if (playMP3 != null) {

            if (counter == 0) {
                counter = namesset.size();
            }
            switch (order) {

                case NORMAL:
                case REPEAT:
                    repeatCounter(-1);
                    break;
                case RANDOM:
                    randomCounter();
                    break;

            }

            if (counter >= 0 && counter < namesset.size()) {
                playMP3.stop();
                //playMP3.deallocate();
                playMP3.close();
                System.out.println(counter);
                path = paths[counter];
                System.out.println(path);
                locate("file:///" + path);
                play();
            }
        }

    }
// Get State of Player===========================================================================================

    public int getstate() {
        if (playMP3 == null) {
            return 0;
        } else {
            return playMP3.getState();
        }
    }

//Set Auto Theam method==============================================================================================
    public void setAutoTheme() {
        String add[] = add_file.list();
        auto_count++;
        String addd = MyPlayer.imgpath + "car theams/" + add[auto_count % add.length];
        System.out.println(add[auto_count % add.length]);
        MyPlayer.backimage = addd;
        MyPlayer mp = new MyPlayer();
        mp.Player(addd, true);

    }

//get counter for playing song=======================================================================================	 
    public void normalCounter(int i) {
        counter = counter + i;
    }

    public void repeatCounter(int i) {
        counter = counter + i;
        counter = counter % namesset.size();
    }

    public void randomCounter() {
        int index = (int) (Math.random() * namesset.size());
        counter = index;
    }

//doNothing method=============================================================================================
//Player controller listener method================================================================================
    public void controllerUpdate(ControllerEvent e) {
        //System.out.println(e);
        if (e instanceof ResourceUnavailableEvent) {
            System.out.println("ControllerErrorEvent");
        }
        //System.out.println(e.getSourceController().getState());
//set starting volume	 
        if (e instanceof PrefetchCompleteEvent) {
            playMP3.getGainControl().setLevel(volume);
        }

// realized player

        if (e instanceof RealizeCompleteEvent) {
            MyPlayer.mp.frame.panel.remove(PlayerDesign.dvtrackname);
            PlayerDesign.dvtrackname = new JLabel(names[counter]);
            PlayerDesign.dvtrackname.setForeground(Color.cyan);
            PlayerDesign.dvtrackname.setBounds(420, 250, 150, 20);
            MyPlayer.mp.frame.panel.add(PlayerDesign.dvtrackname);

            MyPlayer.mp.frame.panel.remove(PlayerDesign.dvmediatime);
            PlayerDesign.dvmediatime = new JLabel(showTime());
            PlayerDesign.dvmediatime.setForeground(Color.cyan);
            PlayerDesign.dvmediatime.setBounds(420, 290, 150, 20);
            MyPlayer.mp.frame.panel.add(PlayerDesign.dvmediatime);

            MyPlayer.mp.frame.panel.remove(PlayerDesign.dvplaylistname);
            PlayerDesign.dvplaylistname = new JLabel(showplname);
            PlayerDesign.dvplaylistname.setForeground(Color.cyan);
            PlayerDesign.dvplaylistname.setBounds(420, 230, 150, 20);
            MyPlayer.mp.frame.panel.add(PlayerDesign.dvplaylistname);

            MyPlayer.mp.frame.panel.remove(PlayerDesign.dvtrackno);
            PlayerDesign.dvtrackno = new JLabel("" + (counter + 1));
            PlayerDesign.dvtrackno.setForeground(Color.cyan);
            PlayerDesign.dvtrackno.setBounds(420, 270, 150, 20);
            MyPlayer.mp.frame.panel.add(PlayerDesign.dvtrackno);

            MyPlayer.mp.frame.PlayerDesignMethod(MyPlayer.backimage, false);
            Time b = playMP3.getDuration();
            SPLAY = true;
            SPAUSE = false;
            SSTOP = false;
            PlayerDesign.setslider = (int) b.getSeconds();
            PlayerDesign.jslider.setMaximum((int) b.getSeconds());
            mp.runSlider();
            if (p == 1) {
                playMP3.setMediaTime(time);
                playMP3.start();
                p = 0;


            }
        }
        if (e instanceof PrefetchCompleteEvent) {
            if (m_type.equals("video")) {
                if (eb == null) {
                    eb = new VideoCaller(names[counter]);
                    eb.create();
                    eb.videotry();
                } else {
                    eb.setTitle(names[counter]);
                    eb.videotry();
                }
            }
        }

//event on EndOfMediaEvent

        if (e instanceof EndOfMediaEvent) {
            if (SelectTheams.check_auto) {
                setAutoTheme();
            }
            if (eb != null) {
                eb.dispose();
            }

            eb = null;
            //PlayerDesign.jplay.setEnabled(true);
            switch (order) {

                case NORMAL:
                    normalCounter(1);
                    break;
                case REPEAT:
                    repeatCounter(1);
                    break;
                case RANDOM:
                    randomCounter();
                    break;

            }

            if (namesset.size() == counter && order == NORMAL) {
                EmptyBox.sleepon = true;
                SelectTheams.byPlaylist();
                PlayerDesign.jplay.setEnabled(true);
                counter = 1;
            }
            if (counter >= 0 && counter < namesset.size()) {
                playMP3.stop();
                //playMP3.deallocate();
                playMP3.close();
                path = paths[counter];
                locate("file:///" + path);
                play();
            }
        }
    }

//gain method foe control volume====================================================================================
    public void gain(float ad) {
        volume = ad;
        playMP3.getGainControl().addGainChangeListener(this);
        playMP3.getGainControl().setLevel(volume);
    }

    public void gainChange(GainChangeEvent gc) {
    }
}
