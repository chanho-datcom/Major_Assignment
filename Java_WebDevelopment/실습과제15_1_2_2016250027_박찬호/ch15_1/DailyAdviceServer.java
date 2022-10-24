package ch15_1;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class DailyAdviceServer {
    String[] adviceList = {"Take smaller bites","Go for the tight jean. no they do Not make you olook fat",
            "one word: inapproprirate", "just for today, be honest. tell your boss what you really think",
            "you might want to rethink that haircut"};

    public void go(){
        try{
            ServerSocket serverSocket = new ServerSocket(5000);

            while(true){
                Socket sock = serverSocket.accept();
                PrintWriter writer = new PrintWriter(sock.getOutputStream());
                String advice = getAdvice();
                writer.println(advice);
                writer.close();
                System.out.println(advice);
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private  String getAdvice(){
        int random = (int)(Math.random() * adviceList.length);
        return adviceList[random];
    }

    public static  void main(String[] args){
        DailyAdviceServer server = new DailyAdviceServer();
        server.go();
    }
}
