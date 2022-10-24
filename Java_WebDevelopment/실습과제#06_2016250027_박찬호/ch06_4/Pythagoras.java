package ch06_4;

public class Pythagoras {
    public  static void main(String[] args){
        int a,b,c;
        int cnt=0;

        for (a = 1; a<100; a++)
            for (b = 1; b<100; b++)
                for (c = 1; c<100; c++)
                    if ((a>b) && (a*a + b*b == c*c)){
                        System.out.println("a="+ a + "b="+ b + "c=" + c);
                        cnt++;
                    }
                    else if ((a<b) && (a*a + b*b == c*c)){
                        System.out.println("a="+ a + "b="+ b + "c=" + c);
                        cnt++;
                    }

        System.out.println("삼각형 개수 : " +cnt + " 2016250027 박찬호");

    }
}
