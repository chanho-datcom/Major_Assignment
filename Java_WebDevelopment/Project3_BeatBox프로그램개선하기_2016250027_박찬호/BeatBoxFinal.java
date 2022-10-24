package ch16_3;

import javax.sound.midi.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.*;

public class BeatBoxFinal {

    JPanel mainPanel;
    JList incomingList;
    JTextField userMessage;
    ArrayList<JCheckBox> checkboxList;
    int nextNum;
    ObjectOutputStream out;
    ObjectInputStream in;
    Vector<String> listVector = new Vector<String>();
    String userName;
    HashMap<String, boolean[]> otherSeqsMap = new HashMap<String, boolean[]>();
    Sequencer sequencer;
    Sequence sequence;
    Sequence mySequence = null;
    Track track;
    JFrame theFrame;

    String[] instrumentNames = {"Bass Drum", "Closed Hi-Hat", "Open Hi-Hat", "Acoustic Snare", "Crash Cymbal",
            "Hand Clap", "High Tom", "Hi Bongo", "Maracas", "Whistle", "Low Conga", "Cowbell", "Vibraslap",
            "Low-mid Tom", "High Agogo", "Open Hi Conga"};


    int[] instruments = {35,42,46,38,49,39,50,60,70,72,64,56,58,47,67,63};

    public static void main(String[] args){
        String UserArgs = (String) JOptionPane.showInputDialog(null, "사용자 이름을 입력해주세요 취소를 누르면 디폴드로 시작합니다.", "주의문", JOptionPane.QUESTION_MESSAGE);
        if(UserArgs == null||UserArgs.equals("Default")){
            new BeatBoxFinal().startUp(UserArgs);
        }
        else {
            new BeatBoxFinal().startUp(UserArgs);
        }
    }

    public void startUp(String name){
        userName = name;

        try{
            Socket sock = new Socket("192.168.219.101", 4242);
            out = new ObjectOutputStream(sock.getOutputStream());
            in = new ObjectInputStream(sock.getInputStream());
            Thread remote = new Thread(new RemoteReader());
            remote.start();
        }catch(Exception ex){
            System.out.println("Couldn't connect - You'll have to play alone.");
        }
        setUpMidi();
        buildGUI();
    }

    public void buildGUI(){
        theFrame = new JFrame("Cyber BeatBox");
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BorderLayout layout = new BorderLayout();
        JPanel background = new JPanel(layout);

        background.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        checkboxList = new ArrayList<JCheckBox>();
        Box buttonBox = new Box(BoxLayout.Y_AXIS);

        JButton start = new JButton("Start");
        start.addActionListener(new MyStartListener());
        buttonBox.add(start);

        JButton stop = new JButton("Stop");
        stop.addActionListener(new MyStopListener());
        buttonBox.add(stop);

        JButton upTempo = new JButton("Tempo up");
        upTempo.addActionListener(new MyUpTempoListener());
        buttonBox.add(upTempo);

        JButton downTemp = new JButton("Tempo down");
        downTemp.addActionListener(new MyDownTempoListener());
        buttonBox.add(downTemp);

        JButton Ran = new JButton("Random");
        Ran.addActionListener(new MyrandomListener());
        buttonBox.add(Ran);

        JButton sendit = new JButton("Sendit");
        sendit.addActionListener(new MySendListener());
        buttonBox.add(sendit);



        userMessage = new JTextField();
        buttonBox.add(userMessage);


        incomingList = new JList();
        incomingList.addListSelectionListener(new MyListSelectionListener());
        incomingList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane theList = new JScrollPane(incomingList);
        buttonBox.add(theList);

        incomingList.setListData(listVector);

        Box nameBox = new Box(BoxLayout.Y_AXIS);
        for(int i = 0; i < 16; i++){
            nameBox.add(new Label(instrumentNames[i]));
        }
        background.add(BorderLayout.EAST, buttonBox);
        background.add(BorderLayout.WEST, nameBox);

        theFrame.getContentPane().add(background);

        GridLayout grid = new GridLayout(16,16);
        grid.setVgap(1);
        grid.setHgap(2);
        mainPanel = new JPanel(grid);
        background.add(BorderLayout.CENTER, mainPanel);


        for (int i = 0; i < 256; i++){
            JCheckBox c = new JCheckBox();
            c.setSelected(false);
            checkboxList.add(c);
            mainPanel.add(c);
        }



        theFrame.setBounds(50,50,300,300);
        theFrame.pack();
        theFrame.setVisible(true);
    }


    public void setUpMidi(){

        try{
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequence = new Sequence(Sequence.PPQ, 4);
            track = sequence.createTrack();
            sequencer.setTempoInBPM(120);
        } catch (Exception e){ e.printStackTrace(); }
    }



    public void buildTrackAndStart(){


        ArrayList<Integer> trackList = null;


        sequence.deleteTrack(track);
        track = sequence.createTrack();


        for (int i = 0; i < 16; i++){
            trackList = new ArrayList<Integer>();


            for (int j = 0; j < 16; j++){
                JCheckBox jc = (JCheckBox) checkboxList.get(j + (16*i));

                if( jc.isSelected()){

                    int key = instruments[i];
                    trackList.add(key);
                } else {

                    trackList.add(null);
                }
            }

            makeTracks(trackList);
        }
        track.add(makeEvent(192,9,1,0,15));
        try{
            sequencer.setSequence(sequence);

            sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);


            sequencer.start();
            sequencer.setTempoInBPM(120);
        } catch (Exception e) { e.printStackTrace(); }
    }





    public class MyStartListener implements ActionListener {
        public void actionPerformed(ActionEvent a){
            buildTrackAndStart();
        }
    }

    public class MyStopListener implements ActionListener {
        public void actionPerformed(ActionEvent a){
            sequencer.stop();
        }
    }


    public class MyUpTempoListener implements ActionListener {
        public void actionPerformed(ActionEvent a){
            float tempoFactor = sequencer.getTempoFactor();
            sequencer.setTempoFactor((float) (tempoFactor * 1.03));
        }
    }

    public class MyDownTempoListener implements ActionListener {
        public void actionPerformed(ActionEvent a){
            float tempoFactor = sequencer.getTempoFactor();
            sequencer.setTempoFactor((float) (tempoFactor * .97));
        }
    }



    public class MySendListener implements ActionListener{


        public void actionPerformed(ActionEvent a){


            boolean[] checkboxState = new boolean[256];

            for(int i = 0; i < 256; i++){


                JCheckBox check = (JCheckBox) checkboxList.get(i);
                if(check.isSelected()){
                    checkboxState[i] = true;
                }
            }

            try{
                out.writeObject(userName + nextNum++ + ": " + userMessage.getText());
                out.writeObject(checkboxState);
            }catch(Exception ex){
                System.out.println("Sorry dude, Couldn't send it to the server.");
            }
        }
    }



    public class MyListSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent le){
            int result = JOptionPane.showConfirmDialog(null,"현재 패턴을 저장하시겠습니까", "확인문", JOptionPane.YES_NO_OPTION);
            if ( result == JOptionPane.CLOSED_OPTION){ }
            else if(result == JOptionPane.YES_OPTION){
                if(!le.getValueIsAdjusting()){
                    String selected = (String) incomingList.getSelectedValue();
                    if(selected != null){

                        boolean[] selectedState = (boolean[]) otherSeqsMap.get(selected);
                        changeSequence(selectedState);
                        sequencer.stop();
                        buildTrackAndStart();
                    }
                }
            }

        }
    }

    public class MyrandomListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            for(int i = 0; i < 256 ; i++){
                Random rand = new Random();
                int a = rand.nextInt();
                JCheckBox check = (JCheckBox) checkboxList.get(i);
                if(a % 2 == 0){
                    check.setSelected(true);
                }else{
                    check.setSelected(false);
                }
            }
        }
    }


    public class RemoteReader implements Runnable{
        boolean[] checkboxState = null;
        String nameToShow = null;
        Object obj = null;
        public void run(){
            try{
                while((obj = in.readObject()) != null){
                    System.out.println("Got an object from server");
                    System.out.println(obj.getClass());
                    String nameToShow = String.valueOf(obj);
                    checkboxState = (boolean[]) in.readObject();
                    otherSeqsMap.put(nameToShow, checkboxState);
                    listVector.add(nameToShow);
                    incomingList.setListData(listVector);

                }
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

    public class MyPlayMineListener implements ActionListener{
        public void actionPerformed(ActionEvent a){
            if(mySequence != null){

                sequence = mySequence;
            }
        }
    }


    public void changeSequence(boolean[] checkboxState){
        for (int i = 0; i < 256; i++){
            JCheckBox check = (JCheckBox) checkboxList.get(i);
            if(checkboxState[i]){
                check.setSelected(true);
            }else {
                check.setSelected(false);
            }
        }
    }


    public void makeTracks(ArrayList list){
        Iterator it = list.iterator();
        for (int i = 0; i < 16; i++){

            Integer num = (Integer) it.next();
            if(num != null){
                int numKey = num.intValue();

                track.add(makeEvent(144,9,numKey, 100, i));
                track.add(makeEvent(128,9,numKey, 100, i + 1));
            }
        }
    }


    public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick){
        MidiEvent event = null;
        try {
            ShortMessage a = new ShortMessage();
            a.setMessage(comd, chan, one, two);
            event = new MidiEvent(a, tick);
        } catch(Exception e) { e.printStackTrace(); }
        return event;
    }
}