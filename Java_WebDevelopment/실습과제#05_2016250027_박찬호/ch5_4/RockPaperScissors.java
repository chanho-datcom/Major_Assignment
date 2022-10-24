package ch5_4;
import java.util.Scanner;

public class RockPaperScissors {
    public static void main(String[] args){

        boolean exit = true;
        System.out.println("/t<<가위 바위 보  게임>>");
        while(exit){
            System.out.println("가위(1), 바위(2), 보(3), 종료(0) 입력 : ");
            Input num = new Input();

            if(num.plnum == 0){
                break;
            }

            if (num.randomNum == 1){
                if (num.plnum == 3){
                    System.out.println("Computer value : " + num.randomNum);
                    System.out.println("컴퓨터가 가위를 냈습니다. 당신이 졌습니다.");
                }
                if (num.plnum == 1 ){
                    System.out.println("Computer value : " + num.randomNum);
                    System.out.println("컴퓨터가 가위를 냈습니다. 당신과 비겼습니다.");
                }
                if (num.plnum == 2){
                    System.out.println("Computer value : " + num.randomNum);
                    System.out.println("컴퓨터가 가위를 냈습니다. 당신이 이겼습니다.");
                }
            }
            if (num.randomNum == 2){
                if (num.plnum == 1){
                    System.out.println("Computer value : " + num.randomNum);
                    System.out.println("컴퓨터가 바위를 냈습니다. 당신이 졌습니다.");
                }
                if (num.plnum == 2){
                    System.out.println("Computer value : " + num.randomNum);
                    System.out.println("컴퓨터가 바위를 냈습니다. 당신과 비겼습니다.");
                }
                if (num.plnum == 3){
                    System.out.println("Computer value : " + num.randomNum);
                    System.out.println("컴퓨터가 바위를 냈습니다. 당신이 이겼습니다.");
                }
            }
            if (num.randomNum == 3){
                if (num.plnum == 2){
                    System.out.println("Computer value : " + num.randomNum);
                    System.out.println("컴퓨터가 보를 냈습니다. 당신이 졌습니다.");
                }
                if (num.plnum == 3){
                    System.out.println("Computer value : " + num.randomNum);
                    System.out.println("컴퓨터가 보를 냈습니다. 당신과 비겼습니다.");
                }
                if (num.plnum == 1){
                    System.out.println("Computer value : " + num.randomNum);
                    System.out.println("컴퓨터가 보를 냈습니다. 당신이 이겼습니다.");
                }
            }
        }
        System.out.println("수고하셨습니다!\n2016250027 박찬호");
    }
}
