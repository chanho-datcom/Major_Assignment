package ch8_2;

public class Triangle {
    int Width, Height;

    public Triangle(int W, int H) {
        Width = W;
        Height = H;
    }

    public double TriangleArea() {
        return (Width * Height) / 2;
    }

    public String toString() {
        return "\nTriangle의 값은 : " + TriangleArea();
    }
}
