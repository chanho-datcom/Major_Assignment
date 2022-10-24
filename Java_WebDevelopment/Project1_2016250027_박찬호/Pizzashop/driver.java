package Pizzashop;
/*
작성자 : 박찬호
e-mail : qkrcksgh156@naver.com
강좌명 : 자바프로그래밍
Assignment: Programming Assignment 1
Due date: 05/18/2020
File: driver.java
Purpose: Java application that implements an online specialty pizza
shop
Compiler/IDE: Java SE Development Kit 8u181/IntelliJ IDEA
Operating system: MS Windows 10
 */
import java.util.Scanner;

public class driver {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] Menu = new String[] {"Meat Lover", "Vegetarian", "Hawaiian", "Philly Steak", "BBQ Chicken"}; //메뉴 배열 생성
        String[] Size = new String[] {"Personal", "Medium", "Large", "Extra Large"};// 사이즈 배열 생성
        double[] Price = new double[] {10.0, 14.5, 19.0, 23.5}; // 사이즈별 가격 배열 생성
        double Menum = 0, Siznum = 0, Quantity = 1;//변수 선언
        double Amount;//변수 선언
        int i=0;//변수 선언

        OrderItem[] item = new OrderItem[300];//객체 배열 크기 초기화

        System.out.println("**************************************\n");
        System.out.println("Welcome to Chanho's Pizza of West Chester!\n2016250027 박찬호\n");
        System.out.println("**************************************\n");



        while(true) { // 반복문 1 시작
            OrderItem.displayPizzaMenu(); // 피자메뉴 디스플레이 함수
            System.out.println("Your Choice (1-6)?");
            Menum = sc.nextDouble();// 선택 입력 받기
            if (Menum != 6 & Menum < 6 & Menum > 0 & Menum % 1 == 0) {// 6이 아니고 0보다 크며 1로 나누어떨어지면 참
                OrderItem.getPizzaChoice(); // 사이즈 및 가격 디스플레이 함수
                    while (true) {
                        if(Quantity % 1 == 0 & Quantity != 0) { // size조건문 수량이 1로 나누어떨어지고 0이 아니면 참
                            System.out.println("Your Choice (1-4)?");
                            Siznum = sc.nextDouble();// 사이즈 입력 받기
                        }
                        if (Siznum < 5 & Siznum > 0 & Siznum % 1 == 0) { // 5보다 작고 0보다 크며 1로 나우어떨어지면 참
                            System.out.print("How many " + Size[(int) Siznum - 1] + " pizzas?");
                            Quantity = sc.nextDouble(); // 수량 입력 받기
                            if (Quantity > 0 & Quantity % 1 == 0) { // 0보다 크고 1로 나누어떨어지면 참
                                item[i] = new OrderItem(Menu[(int) Menum - 1], Size[(int) Siznum - 1], (int) Quantity, ((double) Price[(int) Siznum - 1] * Quantity));//item배열에 orederitem으로 네가지 속성을 결합
                                System.out.println("Your current order total is $ " + OrderItem.amount());// 현재까지의 총액수 출력
                                i++;
                                break;
                            } else if (Quantity == 0) { // 수량이 0이면 참
                                System.out.print("Quantity must be greater than 0(zero)\n");
                            } else if (Quantity < 0 | Quantity % 1 != 0) { // 수량이 0보다 작거나 1로 나누어떨어지지 않으면 참
                                System.out.print("Please enter a valid number.\n");
                            }
                        } else { //size 조건문이 아닐때
                            System.out.print("Please enter a valid number.\n");
                            System.out.println("**************************************\n");

                        }

                    }


            } else if (Menum == 6) { // 입력 받은 메뉴 선택지가 6일때
                String total = String.format("%7s %7s %7s %7s", "Type", "Size", "Quantity", "Price");//7칸 씩 차지하여 출력
                System.out.println(total);
                System.out.println("**************************************\n");
                for(int j = 0 ; j < item.length ; j++){ // item 배열만큼 반복
                    if (item[j] == null){ break; } // null값 일시 프로그램 종료
                    System.out.println(item[j].toString()); // toString문을 이용하여 item안에 저장되있는 것들을 출력
                }
                System.out.println("Order Total : $ " + OrderItem.amount());
                System.out.println("********************************************");
                System.out.println("How do you wish to pay for your order?");
                System.out.println("(Enter 1 for cash or 2 for credit.");
                OrderItem.Payment();
                break; // 반복문 나가기


            } else {
                System.out.print(Menum + " is not a valid choice! Please enter 1~6\n");
            }
        }
    }
}
