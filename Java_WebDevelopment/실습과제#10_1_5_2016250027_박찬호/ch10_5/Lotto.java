package ch10_5;

import java.util.HashSet;
import java.util.ArrayList;

public class Lotto {
    public static void main(String [] args){
        HashSet<Integer> hs = new HashSet<>();
        while(hs.size() < 6){
            double rannum = Math.random();
            int lotnum = (int)(rannum * 45 + 1);
            if(hs.contains(lotnum)){
                continue;
            }
            else{
                hs.add(lotnum);
            }
        }
        ArrayList a = new ArrayList();
        a.addAll(hs);
        System.out.println("로또 번호는" + a);
        System.out.println("2016250027 박찬호");
    }



}
