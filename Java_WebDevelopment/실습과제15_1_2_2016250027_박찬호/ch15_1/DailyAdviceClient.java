package ch15_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class DailyAdviceClient {
    public void go(){
        try{
            Socket s = new Socket("127.0.0.1",  5000);

            InputStreamReader streamReader = new InputStreamReader(s.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);

            String advice = reader.readLine();
            System.out.println("Today you should:" + advice);
            System.out.println("2016250027 박찬호");
            reader.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public static void main(String[] args){
        DailyAdviceClient client = new DailyAdviceClient();
        client.go();
    }
}
