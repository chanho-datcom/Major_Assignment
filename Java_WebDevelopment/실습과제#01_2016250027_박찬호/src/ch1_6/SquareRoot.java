package ch1_6;

import java.util.Scanner;
public class SquareRoot {
    public static void main(String[] args){
        int number;
        double n;

        Scanner d = new Scanner(System.in);
        System.out.println("숫자를 입력하세요:");
        number = d.nextInt();
        n = Math.sqrt(number);
        System.out.println("by 2016250027 박찬호" + n);
    }
}
