package ch8_2;

import java.util.Scanner;

public class clacArea {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int Width, Height, Radian;
        double Pi = Math.PI;

        double RectArea, TriArea, CirArea;
        System.out.print("Rectangle의 Width와 Height를 입력하시오. : ");
        Width = sc.nextInt();
        Height = sc.nextInt();
        Rectangle c1 = new Rectangle(Width, Height);

        System.out.print("Triangle의 Width와 Height를 입력하시오. : ");
        Width = sc.nextInt();
        Height = sc.nextInt();
        Triangle c2 = new Triangle(Width, Height);

        System.out.print("Circle의 Radian을 입력하시오. : ");
        Radian = sc.nextInt();
        Circle c3 = new Circle(Radian);

        System.out.print(c1);
        System.out.print(c2);
        System.out.print(c3);

        System.out.print("\n2016250027 박찬호");
    }
}
