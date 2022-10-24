package ch3_6;

import java.util.Scanner;

public class PlanetWeight {
    public static void main(String[] args){
        String planetname;
        double weight;
        String yn;
        int i = 0;

        while(true) {
            Scanner pl = new Scanner(System.in);
            System.out.println("please enter any planet name?");
            planetname = pl.next();
            System.out.println("please enter its weight?");
            weight = pl.nextDouble();
            System.out.println("Yor weight on " + planetname + " is " + weight);

            System.out.println("continue(y/n)?");
            yn = pl.next();
            if("n".contentEquals(yn)){
                break;
            }
            if("N".contentEquals(yn)){
                break;
            }
        }
        System.out.println("Thank you for working!");
    }
}
