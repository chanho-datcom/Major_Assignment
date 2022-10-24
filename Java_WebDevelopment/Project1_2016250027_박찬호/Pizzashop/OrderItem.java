package Pizzashop;


import java.lang.*;
import java.util.Scanner;

public class OrderItem {
    String Type;
    String Size;
    int Quantity;
    double Price;
    private static double total_amount ;
    public OrderItem(String Type, String Size, int Quantity, double Price){ //객체배열로 저장가능한 함수 생성
        this.Type = Type;
        this.Size = Size;
        this.Quantity = Quantity;
        this.Price = Price;
        total_amount += Price; // price값을 통해 총금액 구하기
    }

    public static void displayPizzaMenu(){ // 피자메뉴 디스플레이 함수
        int Menum = 0; // 변수 선언
        Scanner sc = new Scanner(System.in);
        String[] Menu = new String[] {"Meat Lover", "Vegetarian", "Hawaiian", "Philly Steak", "BBQ Chicken", "메뉴 나가기"}; // 문자열 배열에 메뉴 항목 저장
        System.out.println("Specialty Pizza Menu\n");
        for(int j=0; j<Menu.length;j++){ // 메뉴 항목수만큼 반복
            System.out.println(j+1 +"."+ Menu[j]);//메뉴 출력
        }

    }

    public static void getPizzaChoice(){//사이즈 및 가격 디스플레이/입려받기
        Scanner sc = new Scanner(System.in);
        int Siznum = 0,  Quantity = 0;
        String[] Size = new String[] {"Personal", "Medium", "Large", "Extra Large"};//사이즈 배열
        double[] Price = new double[] {10.0, 14.5, 19.0, 23.5};// 가격 배열
        System.out.println("**************************************\n");
        System.out.println("Available Size and Price\n");
        System.out.println("**************************************\n");
        for(int k=0; k<Size.length;k++){//사이즈 배열 크기만큼 반복
            System.out.println(k+1 +".\t"+ Size[k] +"\t\t"+ Price[k]);// 사이즈 및 가격 출력
        }
    }
    public String toString() { // 결과를 출력하기위한 toString 출력문이다.
        return Type + " " + Size + "  " + Quantity + "   " + Price;
    }

    public static double amount() {return total_amount;}//총금액 받는 함수



        public static void Payment() {// 결제 함수

            String n, c, e, y; // 변수 선언
            double t;//
            t = amount();//t 변수에 총 금액 저장
            Scanner sc = new Scanner(System.in);
            int pm = sc.nextInt(); // 결제 방법 선택 입력 받기
            if (pm == 2) {
                System.out.println("Please enter your payment Information");

                System.out.println("Card holder name:");
                n = sc.next();//카드 소유주 이름 입력 받기
                System.out.println("Credit card type (e.g... MasterCard)");
                y = sc.next();//카드 타입 입력 받기
                System.out.println("Credit card number (e.g... 5201345098756420)");
                c = sc.next();// 카드 번호 입력 받기
                System.out.println("Expiration date (e.g.. 10/2016)");
                e = sc.next();// 카드 유효기간 입력 받기

                System.out.println("********************************************");
                System.out.println("Credit payment sumary");

                System.out.println("Customer Name : " + n);
                System.out.println("Payment amount : " + t);//                            카드 결제
                System.out.printf("Card number ************%4.4s\n", c); ///                 명세서 출력
                System.out.println("Expiration date = " + e);
            }
                else if (pm == 1) { // 현금 결제 방식
                System.out.printf("Payment amount : $" + amount() + "\n");//현금 결제 명세서 출력
            }
            System.out.println("********************************************");
            System.out.println("Thank you for visiting Chanho's Pizza of West Chester!"); //      방문 감사
            System.out.println("Come back soon!"); //                                             인사 출력
            System.out.println("********************************************");
    }
}
