package ch16_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SimpleChatClient {
    JTextArea incoming;
    JTextField outgoing;
    BufferedReader reader;
    PrintWriter writer;
    Socket socket;

    public static void main(String[] args) {
        SimpleChatClient client = new SimpleChatClient();
        client.go();
    }

    public void go(){
        JFrame frame = new JFrame("SimpleChat Client");
        JPanel mainPanel = new JPanel();
        incoming = new JTextArea(15,20);
        incoming.setLineWrap(true);
        incoming.setWrapStyleWord(true);
        incoming.setEditable(false);
        outgoing = new JTextField(20);
        JScrollPane qScroller = new JScrollPane(incoming);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new SendButtonLstener());
        mainPanel.add(qScroller);
        mainPanel.add(outgoing);
        mainPanel.add(sendButton);
        setUpNetworking();

        Thread readerThread = new Thread(new IncomingReader());
        readerThread.start();

        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(400,500);
        frame.setVisible(true);
    }

    private  void setUpNetworking(){
        try{
            socket = new Socket("127.0.0.1", 5050);
            InputStreamReader streamReader = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader(streamReader);
            writer = new PrintWriter(socket.getOutputStream());
            System.out.println("networking established");
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public class SendButtonLstener implements ActionListener {
        public void actionPerformed(ActionEvent ev){
            try{
                writer.println(outgoing.getText());
                writer.flush();
            }catch (Exception ex){
                ex.printStackTrace();
            }
            outgoing.setText("");
            outgoing.requestFocus();
        }
    }

    public class IncomingReader implements Runnable{
        public void run(){
            String message;
            try{
                while((message = reader.readLine()) != null){
                    System.out.println("Read : " + message);
                    incoming.append(message + "\n");
                }
            }catch (Exception ex) {ex.printStackTrace();}
        }
    }
}
