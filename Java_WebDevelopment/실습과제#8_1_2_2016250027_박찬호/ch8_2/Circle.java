package ch8_2;

public class Circle {
    int Radian;
    double Pi = Math.PI;
    public Circle(int R) {
        Radian = R;
    }

    public double CircleArea() {
        return (double)(Radian * Radian) * Pi;
    }

    public String toString() {
        return "\nCircle의 값은 : " + CircleArea();
    }
}
