package ch8_2;

public class Rectangle {
    int Width, Height;

    public Rectangle(int W, int H) {
        Width = W;
        Height = H;
    }

    public int RectangleArea() {
        return Width * Height;
    }

    public String toString() {
        return "Rectangle의 값은 : " + RectangleArea();
    }
}
