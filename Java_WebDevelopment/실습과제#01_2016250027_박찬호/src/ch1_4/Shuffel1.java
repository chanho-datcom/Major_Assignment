package ch1_4;

public class Shuffel1 {
    public static void main(String[] args) {
        int x=3;
        System.out.print("2016250027 박찬호\n");
        while(x>0){
            if(x>2) {
                System.out.print("a");
            }
            x = x-1;
            System.out.print("-");
            if(x==2) {
                System.out.print("b c");
            }
            if(x==1) {
                System.out.print("d");
                x=x-1;
            }

        }
    }
}
